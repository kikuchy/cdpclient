package net.kikuchy.cdpclient.domain

import kotlin.Double
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.media: Media
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Media(this))

/**
 * This domain allows detailed inspection of media elements
 */
public class Media(
  private val client: CDPClient
) : Domain {
  public val playerPropertiesChanged: Flow<PlayerPropertiesChangedParameter> = client.events.filter
      {
          it.method == "playerPropertiesChanged"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val playerEventsAdded: Flow<PlayerEventsAddedParameter> = client.events.filter {
          it.method == "playerEventsAdded"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val playerMessagesLogged: Flow<PlayerMessagesLoggedParameter> = client.events.filter {
          it.method == "playerMessagesLogged"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val playerErrorsRaised: Flow<PlayerErrorsRaisedParameter> = client.events.filter {
          it.method == "playerErrorsRaised"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val playersCreated: Flow<PlayersCreatedParameter> = client.events.filter {
          it.method == "playersCreated"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  /**
   * Enables the Media domain
   */
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Media.enable", parameter)
  }

  /**
   * Disables the Media domain.
   */
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Media.disable", parameter)
  }

  /**
   * Have one type per entry in MediaLogRecord::Type
   * Corresponds to kMessage
   */
  @Serializable
  public class PlayerMessage(
    /**
     * Keep in sync with MediaLogMessageLevel
     * We are currently keeping the message level 'error' separate from the
     * PlayerError type because right now they represent different things,
     * this one being a DVLOG(ERROR) style log message that gets printed
     * based on what log level is selected in the UI, and the other is a
     * representation of a media::PipelineStatus object. Soon however we're
     * going to be moving away from using PipelineStatus for errors and
     * introducing a new error type which should hopefully let us integrate
     * the error log level into the PlayerError type.
     */
    public val level: String,
    public val message: String
  )

  /**
   * Corresponds to kMediaPropertyChange
   */
  @Serializable
  public class PlayerProperty(
    public val name: String,
    public val value: String
  )

  /**
   * Corresponds to kMediaEventTriggered
   */
  @Serializable
  public class PlayerEvent(
    public val timestamp: Double,
    public val value: String
  )

  /**
   * Corresponds to kMediaError
   */
  @Serializable
  public class PlayerError(
    public val type: String,
    /**
     * When this switches to using media::Status instead of PipelineStatus
     * we can remove "errorCode" and replace it with the fields from
     * a Status instance. This also seems like a duplicate of the error
     * level enum - there is a todo bug to have that level removed and
     * use this instead. (crbug.com/1068454)
     */
    public val errorCode: String
  )

  /**
   * This can be called multiple times, and can be used to set / override /
   * remove player properties. A null propValue indicates removal.
   */
  public class PlayerPropertiesChangedParameter(
    public val playerId: String,
    public val properties: List<PlayerProperty>
  )

  /**
   * Send events as a list, allowing them to be batched on the browser for less
   * congestion. If batched, events must ALWAYS be in chronological order.
   */
  public class PlayerEventsAddedParameter(
    public val playerId: String,
    public val events: List<PlayerEvent>
  )

  /**
   * Send a list of any messages that need to be delivered.
   */
  public class PlayerMessagesLoggedParameter(
    public val playerId: String,
    public val messages: List<PlayerMessage>
  )

  /**
   * Send a list of any errors that need to be delivered.
   */
  public class PlayerErrorsRaisedParameter(
    public val playerId: String,
    public val errors: List<PlayerError>
  )

  /**
   * Called whenever a player is created, or when a new agent joins and recieves
   * a list of active players. If an agent is restored, it will recieve the full
   * list of player ids and all events again.
   */
  public class PlayersCreatedParameter(
    public val players: List<String>
  )
}
