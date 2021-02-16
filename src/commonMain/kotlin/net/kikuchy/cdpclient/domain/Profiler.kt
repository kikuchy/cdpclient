package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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

public val CDPClient.profiler: Profiler
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Profiler(this))

public class Profiler(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val consoleProfileFinished: Flow<ConsoleProfileFinishedParameter> = client
          .events
          .filter {
              it.method == "consoleProfileFinished"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val consoleProfileStarted: Flow<ConsoleProfileStartedParameter> = client
          .events
          .filter {
              it.method == "consoleProfileStarted"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val preciseCoverageDeltaUpdate: Flow<PreciseCoverageDeltaUpdateParameter> = client
          .events
          .filter {
              it.method == "preciseCoverageDeltaUpdate"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Profiler.disable", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Profiler.enable", parameter)
  }

  /**
   * Collect coverage data for the current isolate. The coverage data may be incomplete due to
   * garbage collection.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getBestEffortCoverage(): GetBestEffortCoverageReturn {
    val parameter = null
    val result = client.callCommand("Profiler.getBestEffortCoverage", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setSamplingInterval(args: SetSamplingIntervalParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Profiler.setSamplingInterval", parameter)
  }

  /**
   * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setSamplingInterval(interval: Int): Unit {
    val parameter = SetSamplingIntervalParameter(interval = interval)
    setSamplingInterval(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun start(): Unit {
    val parameter = null
    client.callCommand("Profiler.start", parameter)
  }

  /**
   * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise
   * code
   * coverage may be incomplete. Enabling prevents running optimized code and resets execution
   * counters.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startPreciseCoverage(args: StartPreciseCoverageParameter):
      StartPreciseCoverageReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Profiler.startPreciseCoverage", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise
   * code
   * coverage may be incomplete. Enabling prevents running optimized code and resets execution
   * counters.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startPreciseCoverage(
    callCount: Boolean? = null,
    detailed: Boolean? = null,
    allowTriggeredUpdates: Boolean? = null
  ): StartPreciseCoverageReturn {
    val parameter = StartPreciseCoverageParameter(callCount = callCount,detailed =
        detailed,allowTriggeredUpdates = allowTriggeredUpdates)
    return startPreciseCoverage(parameter)
  }

  /**
   * Enable type profile.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startTypeProfile(): Unit {
    val parameter = null
    client.callCommand("Profiler.startTypeProfile", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stop(): StopReturn {
    val parameter = null
    val result = client.callCommand("Profiler.stop", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Disable precise code coverage. Disabling releases unnecessary execution count records and
   * allows
   * executing optimized code.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stopPreciseCoverage(): Unit {
    val parameter = null
    client.callCommand("Profiler.stopPreciseCoverage", parameter)
  }

  /**
   * Disable type profile. Disabling releases type profile data collected so far.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stopTypeProfile(): Unit {
    val parameter = null
    client.callCommand("Profiler.stopTypeProfile", parameter)
  }

  /**
   * Collect coverage data for the current isolate, and resets execution counters. Precise code
   * coverage needs to have started.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun takePreciseCoverage(): TakePreciseCoverageReturn {
    val parameter = null
    val result = client.callCommand("Profiler.takePreciseCoverage", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Collect type profile.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun takeTypeProfile(): TakeTypeProfileReturn {
    val parameter = null
    val result = client.callCommand("Profiler.takeTypeProfile", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Enable counters collection.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enableCounters(): Unit {
    val parameter = null
    client.callCommand("Profiler.enableCounters", parameter)
  }

  /**
   * Disable counters collection.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disableCounters(): Unit {
    val parameter = null
    client.callCommand("Profiler.disableCounters", parameter)
  }

  /**
   * Retrieve counters.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getCounters(): GetCountersReturn {
    val parameter = null
    val result = client.callCommand("Profiler.getCounters", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Enable run time call stats collection.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enableRuntimeCallStats(): Unit {
    val parameter = null
    client.callCommand("Profiler.enableRuntimeCallStats", parameter)
  }

  /**
   * Disable run time call stats collection.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disableRuntimeCallStats(): Unit {
    val parameter = null
    client.callCommand("Profiler.disableRuntimeCallStats", parameter)
  }

  /**
   * Retrieve run time call stats.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getRuntimeCallStats(): GetRuntimeCallStatsReturn {
    val parameter = null
    val result = client.callCommand("Profiler.getRuntimeCallStats", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Profile node. Holds callsite information, execution statistics and child nodes.
   */
  @Serializable
  public data class ProfileNode(
    /**
     * Unique id of the node.
     */
    public val id: Int,
    /**
     * Function location.
     */
    public val callFrame: Runtime.CallFrame,
    /**
     * Number of samples where this node was on top of the call stack.
     */
    public val hitCount: Int? = null,
    /**
     * Child node ids.
     */
    public val children: Int? = null,
    /**
     * The reason of being not optimized. The function may be deoptimized or marked as don't
     * optimize.
     */
    public val deoptReason: String? = null,
    /**
     * An array of source position ticks.
     */
    public val positionTicks: List<PositionTickInfo>? = null
  )

  /**
   * Profile.
   */
  @Serializable
  public data class Profile(
    /**
     * The list of profile nodes. First item is the root node.
     */
    public val nodes: List<ProfileNode>,
    /**
     * Profiling start timestamp in microseconds.
     */
    public val startTime: Double,
    /**
     * Profiling end timestamp in microseconds.
     */
    public val endTime: Double,
    /**
     * Ids of samples top nodes.
     */
    public val samples: Int? = null,
    /**
     * Time intervals between adjacent samples in microseconds. The first delta is relative to the
     * profile startTime.
     */
    public val timeDeltas: Int? = null
  )

  /**
   * Specifies a number of samples attributed to a certain source position.
   */
  @Serializable
  public data class PositionTickInfo(
    /**
     * Source line number (1-based).
     */
    public val line: Int,
    /**
     * Number of samples attributed to the source line.
     */
    public val ticks: Int
  )

  /**
   * Coverage data for a source range.
   */
  @Serializable
  public data class CoverageRange(
    /**
     * JavaScript script source offset for the range start.
     */
    public val startOffset: Int,
    /**
     * JavaScript script source offset for the range end.
     */
    public val endOffset: Int,
    /**
     * Collected execution count of the source range.
     */
    public val count: Int
  )

  /**
   * Coverage data for a JavaScript function.
   */
  @Serializable
  public data class FunctionCoverage(
    /**
     * JavaScript function name.
     */
    public val functionName: String,
    /**
     * Source ranges inside the function with coverage data.
     */
    public val ranges: List<CoverageRange>,
    /**
     * Whether coverage data for this function has block granularity.
     */
    public val isBlockCoverage: Boolean
  )

  /**
   * Coverage data for a JavaScript script.
   */
  @Serializable
  public data class ScriptCoverage(
    /**
     * JavaScript script id.
     */
    public val scriptId: String,
    /**
     * JavaScript script name or url.
     */
    public val url: String,
    /**
     * Functions contained in the script that has coverage data.
     */
    public val functions: List<FunctionCoverage>
  )

  /**
   * Describes a type collected during runtime.
   */
  @Serializable
  public data class TypeObject(
    /**
     * Name of a type collected with type profiling.
     */
    public val name: String
  )

  /**
   * Source offset and types for a parameter or return value.
   */
  @Serializable
  public data class TypeProfileEntry(
    /**
     * Source offset of the parameter or end of function for return values.
     */
    public val offset: Int,
    /**
     * The types for this parameter or return value.
     */
    public val types: List<TypeObject>
  )

  /**
   * Type profile data collected during runtime for a JavaScript script.
   */
  @Serializable
  public data class ScriptTypeProfile(
    /**
     * JavaScript script id.
     */
    public val scriptId: String,
    /**
     * JavaScript script name or url.
     */
    public val url: String,
    /**
     * Type profile entries for parameters and return values of the functions in the script.
     */
    public val entries: List<TypeProfileEntry>
  )

  /**
   * Collected counter information.
   */
  @Serializable
  public data class CounterInfo(
    /**
     * Counter name.
     */
    public val name: String,
    /**
     * Counter value.
     */
    public val value: Int
  )

  /**
   * Runtime call counter information.
   */
  @Serializable
  public data class RuntimeCallCounterInfo(
    /**
     * Counter name.
     */
    public val name: String,
    /**
     * Counter value.
     */
    public val value: Double,
    /**
     * Counter time in seconds.
     */
    public val time: Double
  )

  public data class ConsoleProfileFinishedParameter(
    public val id: String,
    /**
     * Location of console.profileEnd().
     */
    public val location: Debugger.Location,
    public val profile: Profile,
    /**
     * Profile title passed as an argument to console.profile().
     */
    public val title: String? = null
  )

  /**
   * Sent when new profile recording is started using console.profile() call.
   */
  public data class ConsoleProfileStartedParameter(
    public val id: String,
    /**
     * Location of console.profile().
     */
    public val location: Debugger.Location,
    /**
     * Profile title passed as an argument to console.profile().
     */
    public val title: String? = null
  )

  /**
   * Reports coverage delta since the last poll (either from an event like this, or from
   * `takePreciseCoverage` for the current isolate. May only be sent if precise code
   * coverage has been started. This event can be trigged by the embedder to, for example,
   * trigger collection of coverage data immediatelly at a certain point in time.
   */
  public data class PreciseCoverageDeltaUpdateParameter(
    /**
     * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
     */
    public val timestamp: Double,
    /**
     * Identifier for distinguishing coverage events.
     */
    public val occassion: String,
    /**
     * Coverage data for the current isolate.
     */
    public val result: List<ScriptCoverage>
  )

  @Serializable
  public data class GetBestEffortCoverageReturn(
    /**
     * Coverage data for the current isolate.
     */
    public val result: List<ScriptCoverage>
  )

  @Serializable
  public data class SetSamplingIntervalParameter(
    /**
     * New sampling interval in microseconds.
     */
    public val interval: Int
  )

  @Serializable
  public data class StartPreciseCoverageParameter(
    /**
     * Collect accurate call counts beyond simple 'covered' or 'not covered'.
     */
    public val callCount: Boolean? = null,
    /**
     * Collect block-based coverage.
     */
    public val detailed: Boolean? = null,
    /**
     * Allow the backend to send updates on its own initiative
     */
    public val allowTriggeredUpdates: Boolean? = null
  )

  @Serializable
  public data class StartPreciseCoverageReturn(
    /**
     * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
     */
    public val timestamp: Double
  )

  @Serializable
  public data class StopReturn(
    /**
     * Recorded profile.
     */
    public val profile: Profile
  )

  @Serializable
  public data class TakePreciseCoverageReturn(
    /**
     * Coverage data for the current isolate.
     */
    public val result: List<ScriptCoverage>,
    /**
     * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
     */
    public val timestamp: Double
  )

  @Serializable
  public data class TakeTypeProfileReturn(
    /**
     * Type profile for all scripts since startTypeProfile() was turned on.
     */
    public val result: List<ScriptTypeProfile>
  )

  @Serializable
  public data class GetCountersReturn(
    /**
     * Collected counters information.
     */
    public val result: List<CounterInfo>
  )

  @Serializable
  public data class GetRuntimeCallStatsReturn(
    /**
     * Collected runtime call counter information.
     */
    public val result: List<RuntimeCallCounterInfo>
  )
}
