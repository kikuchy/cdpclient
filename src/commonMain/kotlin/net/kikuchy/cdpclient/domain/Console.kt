package net.kikuchy.cdpclient.domain

import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.console: Console
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Console(this))

/**
 * This domain is deprecated - use Runtime or Log instead.
 */
public class Console(
  private val client: CDPClient
) : Domain {
  public val messageAdded: Flow<MessageAddedParameter> = client.events.filter {
          it.method == "messageAdded"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  /**
   * Does nothing.
   */
  public suspend fun clearMessages(): Unit {
    val parameter = null
    client.callCommand("Console.clearMessages", parameter)
  }

  /**
   * Disables console domain, prevents further console messages from being reported to the client.
   */
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Console.disable", parameter)
  }

  /**
   * Enables console domain, sends the messages collected so far to the client by means of the
   * `messageAdded` notification.
   */
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Console.enable", parameter)
  }

  /**
   * Console message.
   */
  @Serializable
  public class ConsoleMessage(
    /**
     * Message source.
     */
    public val source: String,
    /**
     * Message severity.
     */
    public val level: String,
    /**
     * Message text.
     */
    public val text: String,
    /**
     * URL of the message origin.
     */
    public val url: String? = null,
    /**
     * Line number in the resource that generated this message (1-based).
     */
    public val line: Int? = null,
    /**
     * Column number in the resource that generated this message (1-based).
     */
    public val column: Int? = null
  )

  /**
   * Issued when new console message is added.
   */
  public class MessageAddedParameter(
    /**
     * Console message that has been added.
     */
    public val message: ConsoleMessage
  )
}
