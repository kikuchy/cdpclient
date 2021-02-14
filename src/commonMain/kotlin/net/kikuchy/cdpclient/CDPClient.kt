package net.kikuchy.cdpclient

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlin.reflect.KClass

class CDPClient(private val wsSession: ClientWebSocketSession) {
    companion object {
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
    internal val events: Flow<Message.Event> = allMessages.filterIsInstance()
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

    @Serializable
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

    @Serializable
    internal class ResponseError(
        val code: Int,
        val message: String,
    ) {
        fun throwAsException() {
            throw CDPErrorException(code, message)
        }
    }

    class CDPErrorException(
        val code: Int,
        val originalMessage: String,
    ) : Exception("Error while calling a command: $originalMessage (code: $code)")
}
