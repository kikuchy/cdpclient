package net.kikuchy.cdpclient

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
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
class CDPClient(private val wsSession: ClientWebSocketSession) {
    companion object {
        /**
         * Generate [CDPClient] with HTTP client.
         *
         * It always try to connect to peer with ws:// scheme.
         */
        suspend fun create(host: String, port: Int, path: String): CDPClient {
            val client = HttpClient {
                install(WebSockets)
            }
            return CDPClient(client.webSocketSession {
                url {
                    this.protocol = URLProtocol.WS
                    this.host = host
                    this.port = port
                    this.path(path)
                }
            })
        }

        suspend fun use(host: String, port: Int, path: String, block: suspend CDPClient.() -> Unit) {
            val client = create(host, port, path)
            client.block()
            client.close()
        }
    }

    private var currentID = 0

    @ExperimentalCoroutinesApi
    private var allMessages = channelFlow<Message> {
        val received: Message = when (val frame = wsSession.incoming.receive()) {
            is Frame.Text -> Json.decodeFromString(frame.readText())
            else -> error("Unsupported websocket frame type: $frame")
        }
        channel.offer(received)
    }

    @ExperimentalCoroutinesApi
    internal val events: Flow<Message.Event> = allMessages.filterIsInstance()

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

    @ExperimentalCoroutinesApi
    internal suspend fun callCommand(method: String, parameter: JsonElement?): JsonElement? {
        val requestID = currentID++
        val jsonString = Json.encodeToString(Request(requestID, method, parameter))
        wsSession.send(jsonString)
        val result = responses.filter { it.id == requestID }.single()
        // TODO: IDの確認
        result.error?.throwAsException()
        return result.result
    }

    suspend fun close() {
        wsSession.close()
    }

    @Serializable
    private class Request(val id: Int, val method: String, val params: JsonElement?)

    @ExperimentalSerializationApi
    @Serializable(with = MessageSerializer::class)
    internal sealed class Message {
        @Serializable
        class Response(
            val id: Int,
            val result: JsonElement? = null,
            val error: ResponseError? = null,
        ) : Message()

        @Serializable
        class Event(
            val method: String,
            val params: JsonElement?
        ) : Message()
    }

    @ExperimentalSerializationApi
    @Serializer(forClass = Message::class)
    private object MessageSerializer: KSerializer<Message> {
        override fun deserialize(decoder: Decoder): Message {
            require(decoder is JsonDecoder)
            val element = decoder.decodeJsonElement()
            require(element is JsonObject)
            return if (element.containsKey("id")) {
                val r: Message.Response = Json.decodeFromJsonElement(Message.Response.serializer(), element)
                r
            } else {
                val e: Message.Event = Json.decodeFromJsonElement(element)
                e
            }
        }

        override val descriptor: SerialDescriptor
            get() = TODO("Not yet implemented")

        override fun serialize(encoder: Encoder, value: Message) {
            TODO("Not yet implemented")
        }
    }

    @Serializable
    internal class ResponseError(
        val code: Int,
        val message: String,
        val data: String?
    ) {
        fun throwAsException() {
            throw CDPErrorException(code, message, data)
        }
    }

    class CDPErrorException(
        val code: Int,
        val originalMessage: String,
        val data: String?
    ) : Exception("Error while calling a command: $originalMessage${data?.let {"($it)"} ?: ""} (code: $code)")
}
