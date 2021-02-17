package net.kikuchy.cdpclient

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * A kind of data frame comes from CDP.
 * It should be [Response] or [Event].
 */
@ExperimentalSerializationApi
@Serializable(with = MessageSerializer::class)
internal sealed class Message {

    /**
     * The response of a command.
     *
     * See also: [Request]
     */
    @Serializable
    class Response(
        val id: Int,
        val result: JsonElement? = null,
        val error: ResponseError? = null,
    ) : Message() {

        /**
         * Representation of the error returns from the broswe.
         */
        @Serializable
        class ResponseError(
            val code: Int,
            val message: String,
            val data: String?
        ) {
            /**
             * Throw this error as [CDPErrorException].
             */
            fun throwAsException() {
                throw CDPErrorException(code, message, data)
            }
        }
    }

    /**
     * Events emitted by the browser.
     */
    @Serializable
    class Event(
        val method: String,
        val params: JsonElement?
    ) : Message()
}

@ExperimentalSerializationApi
@Serializer(forClass = Message::class)
internal object MessageSerializer : KSerializer<Message> {
    override fun deserialize(decoder: Decoder): Message {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        require(element is JsonObject)
        return if (element.containsKey("id")) {
            val r: Message.Response = Json.decodeFromJsonElement(element)
            r
        } else {
            val e: Message.Event = Json.decodeFromJsonElement(element)
            e
        }
    }

    override val descriptor: SerialDescriptor
        get() = error("This serializer is only for deserialization!")

    override fun serialize(encoder: Encoder, value: Message) {
        error("This serializer is only for deserialization!")
    }
}
