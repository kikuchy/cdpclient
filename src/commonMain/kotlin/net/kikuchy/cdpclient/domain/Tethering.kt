package net.kikuchy.cdpclient.domain

import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.tethering: Tethering
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Tethering(this))

/**
 * The Tethering domain defines methods and events for browser port binding.
 */
public class Tethering(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val accepted: Flow<AcceptedParameter> = client
          .events
          .filter {
              it.method == "accepted"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Request browser port binding.
   */
  @ExperimentalCoroutinesApi
  public suspend fun bind(args: BindParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Tethering.bind", parameter)
  }

  public suspend fun bind(port: Int): Unit {
    val parameter = BindParameter(port = port)
    bind(parameter)
  }

  /**
   * Request browser port unbinding.
   */
  @ExperimentalCoroutinesApi
  public suspend fun unbind(args: UnbindParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Tethering.unbind", parameter)
  }

  public suspend fun unbind(port: Int): Unit {
    val parameter = UnbindParameter(port = port)
    unbind(parameter)
  }

  /**
   * Informs that port was successfully bound and got a specified connection id.
   */
  public data class AcceptedParameter(
    /**
     * Port number that was successfully bound.
     */
    public val port: Int,
    /**
     * Connection id to be used.
     */
    public val connectionId: String
  )

  @Serializable
  public data class BindParameter(
    /**
     * Port number to bind.
     */
    public val port: Int
  )

  @Serializable
  public data class UnbindParameter(
    /**
     * Port number to unbind.
     */
    public val port: Int
  )
}
