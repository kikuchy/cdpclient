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

public val CDPClient.heapProfiler: HeapProfiler
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(HeapProfiler(this))

public class HeapProfiler(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val addHeapSnapshotChunk: Flow<AddHeapSnapshotChunkParameter> = client
          .events
          .filter {
              it.method == "addHeapSnapshotChunk"
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
  public val heapStatsUpdate: Flow<HeapStatsUpdateParameter> = client
          .events
          .filter {
              it.method == "heapStatsUpdate"
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
  public val lastSeenObjectId: Flow<LastSeenObjectIdParameter> = client
          .events
          .filter {
              it.method == "lastSeenObjectId"
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
  public val reportHeapSnapshotProgress: Flow<ReportHeapSnapshotProgressParameter> = client
          .events
          .filter {
              it.method == "reportHeapSnapshotProgress"
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
  public val resetProfiles: Flow<Unit> = client
          .events
          .filter {
              it.method == "resetProfiles"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Enables console to refer to the node with given id via $x (see Command Line API for more
   * details
   * $x functions).
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun addInspectedHeapObject(args: AddInspectedHeapObjectParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("HeapProfiler.addInspectedHeapObject", parameter)
  }

  /**
   * Enables console to refer to the node with given id via $x (see Command Line API for more
   * details
   * $x functions).
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun addInspectedHeapObject(heapObjectId: String): Unit {
    val parameter = AddInspectedHeapObjectParameter(heapObjectId = heapObjectId)
    addInspectedHeapObject(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun collectGarbage(): Unit {
    val parameter = null
    client.callCommand("HeapProfiler.collectGarbage", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("HeapProfiler.disable", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("HeapProfiler.enable", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getHeapObjectId(args: GetHeapObjectIdParameter): GetHeapObjectIdReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("HeapProfiler.getHeapObjectId", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getHeapObjectId(objectId: String): GetHeapObjectIdReturn {
    val parameter = GetHeapObjectIdParameter(objectId = objectId)
    return getHeapObjectId(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getObjectByHeapObjectId(args: GetObjectByHeapObjectIdParameter):
      GetObjectByHeapObjectIdReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("HeapProfiler.getObjectByHeapObjectId", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getObjectByHeapObjectId(objectId: String, objectGroup: String? = null):
      GetObjectByHeapObjectIdReturn {
    val parameter = GetObjectByHeapObjectIdParameter(objectId = objectId,objectGroup = objectGroup)
    return getObjectByHeapObjectId(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getSamplingProfile(): GetSamplingProfileReturn {
    val parameter = null
    val result = client.callCommand("HeapProfiler.getSamplingProfile", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startSampling(args: StartSamplingParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("HeapProfiler.startSampling", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startSampling(samplingInterval: Double? = null): Unit {
    val parameter = StartSamplingParameter(samplingInterval = samplingInterval)
    startSampling(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startTrackingHeapObjects(args: StartTrackingHeapObjectsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("HeapProfiler.startTrackingHeapObjects", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startTrackingHeapObjects(trackAllocations: Boolean? = null): Unit {
    val parameter = StartTrackingHeapObjectsParameter(trackAllocations = trackAllocations)
    startTrackingHeapObjects(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stopSampling(): StopSamplingReturn {
    val parameter = null
    val result = client.callCommand("HeapProfiler.stopSampling", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stopTrackingHeapObjects(args: StopTrackingHeapObjectsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("HeapProfiler.stopTrackingHeapObjects", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stopTrackingHeapObjects(reportProgress: Boolean? = null,
      treatGlobalObjectsAsRoots: Boolean? = null): Unit {
    val parameter = StopTrackingHeapObjectsParameter(reportProgress =
        reportProgress,treatGlobalObjectsAsRoots = treatGlobalObjectsAsRoots)
    stopTrackingHeapObjects(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun takeHeapSnapshot(args: TakeHeapSnapshotParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("HeapProfiler.takeHeapSnapshot", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun takeHeapSnapshot(reportProgress: Boolean? = null,
      treatGlobalObjectsAsRoots: Boolean? = null): Unit {
    val parameter = TakeHeapSnapshotParameter(reportProgress =
        reportProgress,treatGlobalObjectsAsRoots = treatGlobalObjectsAsRoots)
    takeHeapSnapshot(parameter)
  }

  /**
   * Sampling Heap Profile node. Holds callsite information, allocation statistics and child nodes.
   */
  @Serializable
  public data class SamplingHeapProfileNode(
    /**
     * Function location.
     */
    public val callFrame: Runtime.CallFrame,
    /**
     * Allocations size in bytes for the node excluding children.
     */
    public val selfSize: Double,
    /**
     * Node id. Ids are unique across all profiles collected between startSampling and stopSampling.
     */
    public val id: Int,
    /**
     * Child nodes.
     */
    public val children: List<SamplingHeapProfileNode>
  )

  /**
   * A single sample from a sampling profile.
   */
  @Serializable
  public data class SamplingHeapProfileSample(
    /**
     * Allocation size in bytes attributed to the sample.
     */
    public val size: Double,
    /**
     * Id of the corresponding profile tree node.
     */
    public val nodeId: Int,
    /**
     * Time-ordered sample ordinal number. It is unique across all profiles retrieved
     * between startSampling and stopSampling.
     */
    public val ordinal: Double
  )

  /**
   * Sampling profile.
   */
  @Serializable
  public data class SamplingHeapProfile(
    public val head: SamplingHeapProfileNode,
    public val samples: List<SamplingHeapProfileSample>
  )

  public data class AddHeapSnapshotChunkParameter(
    public val chunk: String
  )

  /**
   * If heap objects tracking has been started then backend may send update for one or more
   * fragments
   */
  public data class HeapStatsUpdateParameter(
    /**
     * An array of triplets. Each triplet describes a fragment. The first integer is the fragment
     * index, the second integer is a total count of objects for the fragment, the third integer is
     * a total size of the objects for the fragment.
     */
    public val statsUpdate: Int
  )

  /**
   * If heap objects tracking has been started then backend regularly sends a current value for last
   * seen object id and corresponding timestamp. If the were changes in the heap since last event
   * then one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event.
   */
  public data class LastSeenObjectIdParameter(
    public val lastSeenObjectId: Int,
    public val timestamp: Double
  )

  public data class ReportHeapSnapshotProgressParameter(
    public val done: Int,
    public val total: Int,
    public val finished: Boolean? = null
  )

  @Serializable
  public data class AddInspectedHeapObjectParameter(
    /**
     * Heap snapshot object id to be accessible by means of $x command line API.
     */
    public val heapObjectId: String
  )

  @Serializable
  public data class GetHeapObjectIdParameter(
    /**
     * Identifier of the object to get heap object id for.
     */
    public val objectId: String
  )

  @Serializable
  public data class GetHeapObjectIdReturn(
    /**
     * Id of the heap snapshot object corresponding to the passed remote object id.
     */
    public val heapSnapshotObjectId: String
  )

  @Serializable
  public data class GetObjectByHeapObjectIdParameter(
    public val objectId: String,
    /**
     * Symbolic group name that can be used to release multiple objects.
     */
    public val objectGroup: String? = null
  )

  @Serializable
  public data class GetObjectByHeapObjectIdReturn(
    /**
     * Evaluation result.
     */
    public val result: Runtime.RemoteObject
  )

  @Serializable
  public data class GetSamplingProfileReturn(
    /**
     * Return the sampling profile being collected.
     */
    public val profile: SamplingHeapProfile
  )

  @Serializable
  public data class StartSamplingParameter(
    /**
     * Average sample interval in bytes. Poisson distribution is used for the intervals. The
     * default value is 32768 bytes.
     */
    public val samplingInterval: Double? = null
  )

  @Serializable
  public data class StartTrackingHeapObjectsParameter(
    public val trackAllocations: Boolean? = null
  )

  @Serializable
  public data class StopSamplingReturn(
    /**
     * Recorded sampling heap profile.
     */
    public val profile: SamplingHeapProfile
  )

  @Serializable
  public data class StopTrackingHeapObjectsParameter(
    /**
     * If true 'reportHeapSnapshotProgress' events will be generated while snapshot is being taken
     * when the tracking is stopped.
     */
    public val reportProgress: Boolean? = null,
    public val treatGlobalObjectsAsRoots: Boolean? = null
  )

  @Serializable
  public data class TakeHeapSnapshotParameter(
    /**
     * If true 'reportHeapSnapshotProgress' events will be generated while snapshot is being taken.
     */
    public val reportProgress: Boolean? = null,
    /**
     * If true, a raw snapshot without artifical roots will be generated
     */
    public val treatGlobalObjectsAsRoots: Boolean? = null
  )
}
