package net.kikuchy.cdpclient.domain

import kotlin.Double
import kotlin.Unit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.deviceOrientation: DeviceOrientation
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(DeviceOrientation(this))

public class DeviceOrientation(
  private val client: CDPClient
) : Domain {
  /**
   * Clears the overridden Device Orientation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clearDeviceOrientationOverride(): Unit {
    val parameter = null
    client.callCommand("DeviceOrientation.clearDeviceOrientationOverride", parameter)
  }

  /**
   * Overrides the Device Orientation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setDeviceOrientationOverride(args: SetDeviceOrientationOverrideParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DeviceOrientation.setDeviceOrientationOverride", parameter)
  }

  /**
   * Overrides the Device Orientation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setDeviceOrientationOverride(
    alpha: Double,
    beta: Double,
    gamma: Double
  ): Unit {
    val parameter = SetDeviceOrientationOverrideParameter(alpha = alpha,beta = beta,gamma = gamma)
    setDeviceOrientationOverride(parameter)
  }

  @Serializable
  public data class SetDeviceOrientationOverrideParameter(
    /**
     * Mock alpha
     */
    public val alpha: Double,
    /**
     * Mock beta
     */
    public val beta: Double,
    /**
     * Mock gamma
     */
    public val gamma: Double
  )
}
