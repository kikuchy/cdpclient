package net.kikuchy.cdpclient.domain

import kotlin.String
import kotlin.Unit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.inspector: Inspector
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Inspector(this))

public class Inspector(
  private val client: CDPClient
) : Domain {
  public val detached: Flow<DetachedParameter> = client.events.filter {
          it.method == "detached"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val targetCrashed: Flow<Unit> = client.events.filter {
          it.method == "targetCrashed"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val targetReloadedAfterCrash: Flow<Unit> = client.events.filter {
          it.method == "targetReloadedAfterCrash"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  /**
   * Disables inspector domain notifications.
   */
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Inspector.disable", parameter)
  }

  /**
   * Enables inspector domain notifications.
   */
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Inspector.enable", parameter)
  }

  /**
   * Fired when remote debugging connection is about to be terminated. Contains detach reason.
   */
  public class DetachedParameter(
    /**
     * The reason why connection has been terminated.
     */
    public val reason: String
  )
}
