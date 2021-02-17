package net.kikuchy.cdpclient

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Representation of the request of CDP command.
 *
 * See also: [Message.Response]
 */
@Serializable
internal class Request(val id: Int, val method: String, val params: JsonElement?)