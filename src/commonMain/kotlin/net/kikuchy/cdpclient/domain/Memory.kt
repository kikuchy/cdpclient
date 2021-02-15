package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.memory: Memory
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Memory(this))

public class Memory(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public suspend fun getDOMCounters(): GetDOMCountersReturn {
    val parameter = null
    val result = client.callCommand("Memory.getDOMCounters", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  public suspend fun prepareForLeakDetection(): Unit {
    val parameter = null
    client.callCommand("Memory.prepareForLeakDetection", parameter)
  }

  /**
   * Simulate OomIntervention by purging V8 memory.
   */
  @ExperimentalCoroutinesApi
  public suspend fun forciblyPurgeJavaScriptMemory(): Unit {
    val parameter = null
    client.callCommand("Memory.forciblyPurgeJavaScriptMemory", parameter)
  }

  /**
   * Enable/disable suppressing memory pressure notifications in all processes.
   */
  @ExperimentalCoroutinesApi
  public suspend
      fun setPressureNotificationsSuppressed(args: SetPressureNotificationsSuppressedParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Memory.setPressureNotificationsSuppressed", parameter)
  }

  public suspend fun setPressureNotificationsSuppressed(suppressed: Boolean): Unit {
    val parameter = SetPressureNotificationsSuppressedParameter(suppressed = suppressed)
    setPressureNotificationsSuppressed(parameter)
  }

  /**
   * Simulate a memory pressure notification in all processes.
   */
  @ExperimentalCoroutinesApi
  public suspend fun simulatePressureNotification(args: SimulatePressureNotificationParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Memory.simulatePressureNotification", parameter)
  }

  public suspend fun simulatePressureNotification(level: PressureLevel): Unit {
    val parameter = SimulatePressureNotificationParameter(level = level)
    simulatePressureNotification(parameter)
  }

  /**
   * Start collecting native memory profile.
   */
  @ExperimentalCoroutinesApi
  public suspend fun startSampling(args: StartSamplingParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Memory.startSampling", parameter)
  }

  public suspend fun startSampling(samplingInterval: Int? = null, suppressRandomness: Boolean? =
      null): Unit {
    val parameter = StartSamplingParameter(samplingInterval = samplingInterval,suppressRandomness =
        suppressRandomness)
    startSampling(parameter)
  }

  /**
   * Stop collecting native memory profile.
   */
  @ExperimentalCoroutinesApi
  public suspend fun stopSampling(): Unit {
    val parameter = null
    client.callCommand("Memory.stopSampling", parameter)
  }

  /**
   * Retrieve native memory allocations profile
   * collected since renderer process startup.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getAllTimeSamplingProfile(): GetAllTimeSamplingProfileReturn {
    val parameter = null
    val result = client.callCommand("Memory.getAllTimeSamplingProfile", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Retrieve native memory allocations profile
   * collected since browser process startup.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getBrowserSamplingProfile(): GetBrowserSamplingProfileReturn {
    val parameter = null
    val result = client.callCommand("Memory.getBrowserSamplingProfile", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Retrieve native memory allocations profile collected since last
   * `startSampling` call.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getSamplingProfile(): GetSamplingProfileReturn {
    val parameter = null
    val result = client.callCommand("Memory.getSamplingProfile", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Memory pressure level.
   */
  @Serializable
  public enum class PressureLevel {
    @SerialName("moderate")
    MODERATE,
    @SerialName("critical")
    CRITICAL,
  }

  /**
   * Heap profile sample.
   */
  @Serializable
  public data class SamplingProfileNode(
    /**
     * Size of the sampled allocation.
     */
    public val size: Double,
    /**
     * Total bytes attributed to this sample.
     */
    public val total: Double,
    /**
     * Execution stack at the point of allocation.
     */
    public val stack: String
  )

  /**
   * Array of heap profile samples.
   */
  @Serializable
  public data class SamplingProfile(
    public val samples: List<SamplingProfileNode>,
    public val modules: List<Module>
  )

  /**
   * Executable module information
   */
  @Serializable
  public data class Module(
    /**
     * Name of the module.
     */
    public val name: String,
    /**
     * UUID of the module.
     */
    public val uuid: String,
    /**
     * Base address where the module is loaded into memory. Encoded as a decimal
     * or hexadecimal (0x prefixed) string.
     */
    public val baseAddress: String,
    /**
     * Size of the module in bytes.
     */
    public val size: Double
  )

  @Serializable
  public data class GetDOMCountersReturn(
    public val documents: Int,
    public val nodes: Int,
    public val jsEventListeners: Int
  )

  @Serializable
  public data class SetPressureNotificationsSuppressedParameter(
    /**
     * If true, memory pressure notifications will be suppressed.
     */
    public val suppressed: Boolean
  )

  @Serializable
  public data class SimulatePressureNotificationParameter(
    /**
     * Memory pressure level of the notification.
     */
    public val level: PressureLevel
  )

  @Serializable
  public data class StartSamplingParameter(
    /**
     * Average number of bytes between samples.
     */
    public val samplingInterval: Int? = null,
    /**
     * Do not randomize intervals between samples.
     */
    public val suppressRandomness: Boolean? = null
  )

  @Serializable
  public data class GetAllTimeSamplingProfileReturn(
    public val profile: SamplingProfile
  )

  @Serializable
  public data class GetBrowserSamplingProfileReturn(
    public val profile: SamplingProfile
  )

  @Serializable
  public data class GetSamplingProfileReturn(
    public val profile: SamplingProfile
  )
}
