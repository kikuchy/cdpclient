package net.kikuchy.cdpclient

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.serialization.*
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlin.reflect.KClass

/**
 * Thin wrapper of Chrome DevTools Protocol.
 */
class CDPClient(private val wsSession: ClientWebSocketSession, messageListeningScope: CoroutineScope = GlobalScope) {
    companion object {
        /**
         * Open [block] with [CDPClient].
         *
         * It always try to connect to peer with ws:// scheme.
         */
        @ExperimentalSerializationApi
        @ExperimentalCoroutinesApi
        suspend fun use(
            host: String,
            port: Int,
            path: String,
            messageListeningScope: CoroutineScope = GlobalScope,
            block: suspend CDPClient.() -> Unit,
        ) {
            HttpClient {
                install(WebSockets)
            }.ws({
                url {
                    this.protocol = URLProtocol.WS
                    this.host = host
                    this.port = port
                    this.path(path)
                }
            }) {
                val client = CDPClient(this, messageListeningScope)
                client.block()
                client.close()
            }
        }
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    private val socketSubscription: Job = messageListeningScope.launch {
        for (frame in wsSession.incoming) {
            val received: Message = when (frame) {
                is Frame.Text -> Json.decodeFromString(frame.readText())
                else -> error("Unsupported websocket frame type: $frame")
            }
            allMessages.emit(received)
        }
    }
    private var currentID = 0

    @ExperimentalSerializationApi
    @ExperimentalCoroutinesApi
    private var allMessages = MutableSharedFlow<Message>()

    @ExperimentalSerializationApi
    @ExperimentalCoroutinesApi
    internal val events: Flow<Message.Event> = allMessages.filterIsInstance()

    @ExperimentalSerializationApi
    @ExperimentalCoroutinesApi
    private val responses: Flow<Message.Response> = allMessages.filterIsInstance()

    private val generatedDomains: MutableMap<KClass<out Domain>, Domain> = mutableMapOf()
    internal inline fun <reified T : Domain> getGeneratedDomain(): T? =
        if (generatedDomains.containsKey(T::class)) {
            generatedDomains[T::class] as T
        } else null

    internal inline fun <reified T : Domain> cacheGeneratedDomain(domain: T): T {
        generatedDomains[T::class] = domain
        return domain
    }

    @ExperimentalSerializationApi
    @ExperimentalCoroutinesApi
    internal suspend fun callCommand(method: String, parameter: JsonElement?): JsonElement? {
        val requestID = currentID++
        val jsonString = Json.encodeToString(Request(requestID, method, parameter))
        wsSession.send(jsonString)
        val result = responses.first { it.id == requestID }
        result.error?.throwAsException()
        return result.result
    }

    /**
     * End the session between the browser.
     *
     * It' wont close WebSocket session. Please close that manually.
     */
    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    fun close() {
        socketSubscription.cancel()
    }
}
