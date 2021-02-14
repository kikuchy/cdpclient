package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.layerTree: LayerTree
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(LayerTree(this))

public class LayerTree(
  private val client: CDPClient
) : Domain {
  public val layerPainted: Flow<LayerPaintedParameter> = client.events.filter {
          it.method == "layerPainted"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val layerTreeDidChange: Flow<LayerTreeDidChangeParameter> = client.events.filter {
          it.method == "layerTreeDidChange"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  /**
   * Provides the reasons why the given layer was composited.
   */
  public suspend fun compositingReasons(args: CompositingReasonsParameter):
      CompositingReasonsReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("LayerTree.compositingReasons", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun compositingReasons(layerId: String): CompositingReasonsReturn {
    val parameter = CompositingReasonsParameter(layerId = layerId)
    return compositingReasons(parameter)
  }

  /**
   * Disables compositing tree inspection.
   */
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("LayerTree.disable", parameter)
  }

  /**
   * Enables compositing tree inspection.
   */
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("LayerTree.enable", parameter)
  }

  /**
   * Returns the snapshot identifier.
   */
  public suspend fun loadSnapshot(args: LoadSnapshotParameter): LoadSnapshotReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("LayerTree.loadSnapshot", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun loadSnapshot(tiles: List<PictureTile>): LoadSnapshotReturn {
    val parameter = LoadSnapshotParameter(tiles = tiles)
    return loadSnapshot(parameter)
  }

  /**
   * Returns the layer snapshot identifier.
   */
  public suspend fun makeSnapshot(args: MakeSnapshotParameter): MakeSnapshotReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("LayerTree.makeSnapshot", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun makeSnapshot(layerId: String): MakeSnapshotReturn {
    val parameter = MakeSnapshotParameter(layerId = layerId)
    return makeSnapshot(parameter)
  }

  public suspend fun profileSnapshot(args: ProfileSnapshotParameter): ProfileSnapshotReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("LayerTree.profileSnapshot", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun profileSnapshot(
    snapshotId: String,
    minRepeatCount: Int? = null,
    minDuration: Double? = null,
    clipRect: DOM.Rect? = null
  ): ProfileSnapshotReturn {
    val parameter = ProfileSnapshotParameter(snapshotId = snapshotId,minRepeatCount =
        minRepeatCount,minDuration = minDuration,clipRect = clipRect)
    return profileSnapshot(parameter)
  }

  /**
   * Releases layer snapshot captured by the back-end.
   */
  public suspend fun releaseSnapshot(args: ReleaseSnapshotParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("LayerTree.releaseSnapshot", parameter)
  }

  public suspend fun releaseSnapshot(snapshotId: String): Unit {
    val parameter = ReleaseSnapshotParameter(snapshotId = snapshotId)
    releaseSnapshot(parameter)
  }

  /**
   * Replays the layer snapshot and returns the resulting bitmap.
   */
  public suspend fun replaySnapshot(args: ReplaySnapshotParameter): ReplaySnapshotReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("LayerTree.replaySnapshot", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun replaySnapshot(
    snapshotId: String,
    fromStep: Int? = null,
    toStep: Int? = null,
    scale: Double? = null
  ): ReplaySnapshotReturn {
    val parameter = ReplaySnapshotParameter(snapshotId = snapshotId,fromStep = fromStep,toStep =
        toStep,scale = scale)
    return replaySnapshot(parameter)
  }

  /**
   * Replays the layer snapshot and returns canvas log.
   */
  public suspend fun snapshotCommandLog(args: SnapshotCommandLogParameter):
      SnapshotCommandLogReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("LayerTree.snapshotCommandLog", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun snapshotCommandLog(snapshotId: String): SnapshotCommandLogReturn {
    val parameter = SnapshotCommandLogParameter(snapshotId = snapshotId)
    return snapshotCommandLog(parameter)
  }

  /**
   * Rectangle where scrolling happens on the main thread.
   */
  @Serializable
  public class ScrollRect(
    /**
     * Rectangle itself.
     */
    public val rect: DOM.Rect,
    /**
     * Reason for rectangle to force scrolling on the main thread
     */
    public val type: String
  )

  /**
   * Sticky position constraints.
   */
  @Serializable
  public class StickyPositionConstraint(
    /**
     * Layout rectangle of the sticky element before being shifted
     */
    public val stickyBoxRect: DOM.Rect,
    /**
     * Layout rectangle of the containing block of the sticky element
     */
    public val containingBlockRect: DOM.Rect,
    /**
     * The nearest sticky layer that shifts the sticky box
     */
    public val nearestLayerShiftingStickyBox: String? = null,
    /**
     * The nearest sticky layer that shifts the containing block
     */
    public val nearestLayerShiftingContainingBlock: String? = null
  )

  /**
   * Serialized fragment of layer picture along with its offset within the layer.
   */
  @Serializable
  public class PictureTile(
    /**
     * Offset from owning layer left boundary
     */
    public val x: Double,
    /**
     * Offset from owning layer top boundary
     */
    public val y: Double,
    /**
     * Base64-encoded snapshot data. (Encoded as a base64 string when passed over JSON)
     */
    public val picture: String
  )

  /**
   * Information about a compositing layer.
   */
  @Serializable
  public class Layer(
    /**
     * The unique id for this layer.
     */
    public val layerId: String,
    /**
     * The id of parent (not present for root).
     */
    public val parentLayerId: String? = null,
    /**
     * The backend id for the node associated with this layer.
     */
    public val backendNodeId: Int? = null,
    /**
     * Offset from parent layer, X coordinate.
     */
    public val offsetX: Double,
    /**
     * Offset from parent layer, Y coordinate.
     */
    public val offsetY: Double,
    /**
     * Layer width.
     */
    public val width: Double,
    /**
     * Layer height.
     */
    public val height: Double,
    /**
     * Transformation matrix for layer, default is identity matrix
     */
    public val transform: Double? = null,
    /**
     * Transform anchor point X, absent if no transform specified
     */
    public val anchorX: Double? = null,
    /**
     * Transform anchor point Y, absent if no transform specified
     */
    public val anchorY: Double? = null,
    /**
     * Transform anchor point Z, absent if no transform specified
     */
    public val anchorZ: Double? = null,
    /**
     * Indicates how many time this layer has painted.
     */
    public val paintCount: Int,
    /**
     * Indicates whether this layer hosts any content, rather than being used for
     * transform/scrolling purposes only.
     */
    public val drawsContent: Boolean,
    /**
     * Set if layer is not visible.
     */
    public val invisible: Boolean? = null,
    /**
     * Rectangles scrolling on main thread only.
     */
    public val scrollRects: List<ScrollRect>? = null,
    /**
     * Sticky position constraint information
     */
    public val stickyPositionConstraint: StickyPositionConstraint? = null
  )

  public class LayerPaintedParameter(
    /**
     * The id of the painted layer.
     */
    public val layerId: String,
    /**
     * Clip rectangle.
     */
    public val clip: DOM.Rect
  )

  public class LayerTreeDidChangeParameter(
    /**
     * Layer tree, absent if not in the comspositing mode.
     */
    public val layers: List<Layer>? = null
  )

  @Serializable
  public data class CompositingReasonsParameter(
    /**
     * The id of the layer for which we want to get the reasons it was composited.
     */
    public val layerId: String
  )

  @Serializable
  public data class CompositingReasonsReturn(
    /**
     * A list of strings specifying reasons for the given layer to become composited.
     */
    public val compositingReasons: String,
    /**
     * A list of strings specifying reason IDs for the given layer to become composited.
     */
    public val compositingReasonIds: String
  )

  @Serializable
  public data class LoadSnapshotParameter(
    /**
     * An array of tiles composing the snapshot.
     */
    public val tiles: List<PictureTile>
  )

  @Serializable
  public data class LoadSnapshotReturn(
    /**
     * The id of the snapshot.
     */
    public val snapshotId: String
  )

  @Serializable
  public data class MakeSnapshotParameter(
    /**
     * The id of the layer.
     */
    public val layerId: String
  )

  @Serializable
  public data class MakeSnapshotReturn(
    /**
     * The id of the layer snapshot.
     */
    public val snapshotId: String
  )

  @Serializable
  public data class ProfileSnapshotParameter(
    /**
     * The id of the layer snapshot.
     */
    public val snapshotId: String,
    /**
     * The maximum number of times to replay the snapshot (1, if not specified).
     */
    public val minRepeatCount: Int?,
    /**
     * The minimum duration (in seconds) to replay the snapshot.
     */
    public val minDuration: Double?,
    /**
     * The clip rectangle to apply when replaying the snapshot.
     */
    public val clipRect: DOM.Rect?
  )

  @Serializable
  public data class ProfileSnapshotReturn(
    /**
     * The array of paint profiles, one per run.
     */
    public val timings: List<List<Double>>
  )

  @Serializable
  public data class ReleaseSnapshotParameter(
    /**
     * The id of the layer snapshot.
     */
    public val snapshotId: String
  )

  @Serializable
  public data class ReplaySnapshotParameter(
    /**
     * The id of the layer snapshot.
     */
    public val snapshotId: String,
    /**
     * The first step to replay from (replay from the very start if not specified).
     */
    public val fromStep: Int?,
    /**
     * The last step to replay to (replay till the end if not specified).
     */
    public val toStep: Int?,
    /**
     * The scale to apply while replaying (defaults to 1).
     */
    public val scale: Double?
  )

  @Serializable
  public data class ReplaySnapshotReturn(
    /**
     * A data: URL for resulting image.
     */
    public val dataURL: String
  )

  @Serializable
  public data class SnapshotCommandLogParameter(
    /**
     * The id of the layer snapshot.
     */
    public val snapshotId: String
  )

  @Serializable
  public data class SnapshotCommandLogReturn(
    /**
     * The array of canvas function calls.
     */
    public val commandLog: Map<String, JsonElement>
  )
}
