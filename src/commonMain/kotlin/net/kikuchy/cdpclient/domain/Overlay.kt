package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.overlay: Overlay
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Overlay(this))

/**
 * This domain provides various functionality related to drawing atop the inspected page.
 */
public class Overlay(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val inspectNodeRequested: Flow<InspectNodeRequestedParameter> = client
          .events
          .filter {
              it.method == "inspectNodeRequested"
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
  public val nodeHighlightRequested: Flow<NodeHighlightRequestedParameter> = client
          .events
          .filter {
              it.method == "nodeHighlightRequested"
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
  public val screenshotRequested: Flow<ScreenshotRequestedParameter> = client
          .events
          .filter {
              it.method == "screenshotRequested"
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
  public val inspectModeCanceled: Flow<Unit> = client
          .events
          .filter {
              it.method == "inspectModeCanceled"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Disables domain notifications.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Overlay.disable", parameter)
  }

  /**
   * Enables domain notifications.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Overlay.enable", parameter)
  }

  /**
   * For testing.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getHighlightObjectForTest(args: GetHighlightObjectForTestParameter):
      GetHighlightObjectForTestReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Overlay.getHighlightObjectForTest", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * For testing.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getHighlightObjectForTest(
    nodeId: Int,
    includeDistance: Boolean? = null,
    includeStyle: Boolean? = null,
    colorFormat: ColorFormat? = null,
    showAccessibilityInfo: Boolean? = null
  ): GetHighlightObjectForTestReturn {
    val parameter = GetHighlightObjectForTestParameter(nodeId = nodeId,includeDistance =
        includeDistance,includeStyle = includeStyle,colorFormat = colorFormat,showAccessibilityInfo
        = showAccessibilityInfo)
    return getHighlightObjectForTest(parameter)
  }

  /**
   * For Persistent Grid testing.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getGridHighlightObjectsForTest(args: GetGridHighlightObjectsForTestParameter):
      GetGridHighlightObjectsForTestReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Overlay.getGridHighlightObjectsForTest", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * For Persistent Grid testing.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getGridHighlightObjectsForTest(nodeIds: List<Int>):
      GetGridHighlightObjectsForTestReturn {
    val parameter = GetGridHighlightObjectsForTestParameter(nodeIds = nodeIds)
    return getGridHighlightObjectsForTest(parameter)
  }

  /**
   * For Source Order Viewer testing.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend
      fun getSourceOrderHighlightObjectForTest(args: GetSourceOrderHighlightObjectForTestParameter):
      GetSourceOrderHighlightObjectForTestReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Overlay.getSourceOrderHighlightObjectForTest", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * For Source Order Viewer testing.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getSourceOrderHighlightObjectForTest(nodeId: Int):
      GetSourceOrderHighlightObjectForTestReturn {
    val parameter = GetSourceOrderHighlightObjectForTestParameter(nodeId = nodeId)
    return getSourceOrderHighlightObjectForTest(parameter)
  }

  /**
   * Hides any highlight.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun hideHighlight(): Unit {
    val parameter = null
    client.callCommand("Overlay.hideHighlight", parameter)
  }

  /**
   * Highlights owner element of the frame with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightFrame(args: HighlightFrameParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.highlightFrame", parameter)
  }

  /**
   * Highlights owner element of the frame with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightFrame(
    frameId: String,
    contentColor: DOM.RGBA? = null,
    contentOutlineColor: DOM.RGBA? = null
  ): Unit {
    val parameter = HighlightFrameParameter(frameId = frameId,contentColor =
        contentColor,contentOutlineColor = contentOutlineColor)
    highlightFrame(parameter)
  }

  /**
   * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or
   * objectId must be specified.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightNode(args: HighlightNodeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.highlightNode", parameter)
  }

  /**
   * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or
   * objectId must be specified.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightNode(
    highlightConfig: HighlightConfig,
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null,
    selector: String? = null
  ): Unit {
    val parameter = HighlightNodeParameter(highlightConfig = highlightConfig,nodeId =
        nodeId,backendNodeId = backendNodeId,objectId = objectId,selector = selector)
    highlightNode(parameter)
  }

  /**
   * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightQuad(args: HighlightQuadParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.highlightQuad", parameter)
  }

  /**
   * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightQuad(
    quad: List<Double>,
    color: DOM.RGBA? = null,
    outlineColor: DOM.RGBA? = null
  ): Unit {
    val parameter = HighlightQuadParameter(quad = quad,color = color,outlineColor = outlineColor)
    highlightQuad(parameter)
  }

  /**
   * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightRect(args: HighlightRectParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.highlightRect", parameter)
  }

  /**
   * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightRect(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    color: DOM.RGBA? = null,
    outlineColor: DOM.RGBA? = null
  ): Unit {
    val parameter = HighlightRectParameter(x = x,y = y,width = width,height = height,color =
        color,outlineColor = outlineColor)
    highlightRect(parameter)
  }

  /**
   * Highlights the source order of the children of the DOM node with given id or with the given
   * JavaScript object wrapper. Either nodeId or objectId must be specified.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightSourceOrder(args: HighlightSourceOrderParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.highlightSourceOrder", parameter)
  }

  /**
   * Highlights the source order of the children of the DOM node with given id or with the given
   * JavaScript object wrapper. Either nodeId or objectId must be specified.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightSourceOrder(
    sourceOrderConfig: SourceOrderConfig,
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null
  ): Unit {
    val parameter = HighlightSourceOrderParameter(sourceOrderConfig = sourceOrderConfig,nodeId =
        nodeId,backendNodeId = backendNodeId,objectId = objectId)
    highlightSourceOrder(parameter)
  }

  /**
   * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted.
   * Backend then generates 'inspectNodeRequested' event upon element selection.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setInspectMode(args: SetInspectModeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setInspectMode", parameter)
  }

  /**
   * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted.
   * Backend then generates 'inspectNodeRequested' event upon element selection.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setInspectMode(mode: InspectMode, highlightConfig: HighlightConfig? = null):
      Unit {
    val parameter = SetInspectModeParameter(mode = mode,highlightConfig = highlightConfig)
    setInspectMode(parameter)
  }

  /**
   * Highlights owner element of all frames detected to be ads.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowAdHighlights(args: SetShowAdHighlightsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowAdHighlights", parameter)
  }

  /**
   * Highlights owner element of all frames detected to be ads.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowAdHighlights(show: Boolean): Unit {
    val parameter = SetShowAdHighlightsParameter(show = show)
    setShowAdHighlights(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setPausedInDebuggerMessage(args: SetPausedInDebuggerMessageParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setPausedInDebuggerMessage", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setPausedInDebuggerMessage(message: String? = null): Unit {
    val parameter = SetPausedInDebuggerMessageParameter(message = message)
    setPausedInDebuggerMessage(parameter)
  }

  /**
   * Requests that backend shows debug borders on layers
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowDebugBorders(args: SetShowDebugBordersParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowDebugBorders", parameter)
  }

  /**
   * Requests that backend shows debug borders on layers
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowDebugBorders(show: Boolean): Unit {
    val parameter = SetShowDebugBordersParameter(show = show)
    setShowDebugBorders(parameter)
  }

  /**
   * Requests that backend shows the FPS counter
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowFPSCounter(args: SetShowFPSCounterParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowFPSCounter", parameter)
  }

  /**
   * Requests that backend shows the FPS counter
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowFPSCounter(show: Boolean): Unit {
    val parameter = SetShowFPSCounterParameter(show = show)
    setShowFPSCounter(parameter)
  }

  /**
   * Highlight multiple elements with the CSS Grid overlay.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowGridOverlays(args: SetShowGridOverlaysParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowGridOverlays", parameter)
  }

  /**
   * Highlight multiple elements with the CSS Grid overlay.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowGridOverlays(gridNodeHighlightConfigs: List<GridNodeHighlightConfig>):
      Unit {
    val parameter = SetShowGridOverlaysParameter(gridNodeHighlightConfigs =
        gridNodeHighlightConfigs)
    setShowGridOverlays(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowFlexOverlays(args: SetShowFlexOverlaysParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowFlexOverlays", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowFlexOverlays(flexNodeHighlightConfigs: List<FlexNodeHighlightConfig>):
      Unit {
    val parameter = SetShowFlexOverlaysParameter(flexNodeHighlightConfigs =
        flexNodeHighlightConfigs)
    setShowFlexOverlays(parameter)
  }

  /**
   * Requests that backend shows paint rectangles
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowPaintRects(args: SetShowPaintRectsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowPaintRects", parameter)
  }

  /**
   * Requests that backend shows paint rectangles
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowPaintRects(result: Boolean): Unit {
    val parameter = SetShowPaintRectsParameter(result = result)
    setShowPaintRects(parameter)
  }

  /**
   * Requests that backend shows layout shift regions
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowLayoutShiftRegions(args: SetShowLayoutShiftRegionsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowLayoutShiftRegions", parameter)
  }

  /**
   * Requests that backend shows layout shift regions
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowLayoutShiftRegions(result: Boolean): Unit {
    val parameter = SetShowLayoutShiftRegionsParameter(result = result)
    setShowLayoutShiftRegions(parameter)
  }

  /**
   * Requests that backend shows scroll bottleneck rects
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowScrollBottleneckRects(args: SetShowScrollBottleneckRectsParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowScrollBottleneckRects", parameter)
  }

  /**
   * Requests that backend shows scroll bottleneck rects
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowScrollBottleneckRects(show: Boolean): Unit {
    val parameter = SetShowScrollBottleneckRectsParameter(show = show)
    setShowScrollBottleneckRects(parameter)
  }

  /**
   * Requests that backend shows hit-test borders on layers
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowHitTestBorders(args: SetShowHitTestBordersParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowHitTestBorders", parameter)
  }

  /**
   * Requests that backend shows hit-test borders on layers
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowHitTestBorders(show: Boolean): Unit {
    val parameter = SetShowHitTestBordersParameter(show = show)
    setShowHitTestBorders(parameter)
  }

  /**
   * Request that backend shows an overlay with web vital metrics.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowWebVitals(args: SetShowWebVitalsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowWebVitals", parameter)
  }

  /**
   * Request that backend shows an overlay with web vital metrics.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowWebVitals(show: Boolean): Unit {
    val parameter = SetShowWebVitalsParameter(show = show)
    setShowWebVitals(parameter)
  }

  /**
   * Paints viewport size upon main frame resize.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowViewportSizeOnResize(args: SetShowViewportSizeOnResizeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowViewportSizeOnResize", parameter)
  }

  /**
   * Paints viewport size upon main frame resize.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowViewportSizeOnResize(show: Boolean): Unit {
    val parameter = SetShowViewportSizeOnResizeParameter(show = show)
    setShowViewportSizeOnResize(parameter)
  }

  /**
   * Add a dual screen device hinge
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowHinge(args: SetShowHingeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Overlay.setShowHinge", parameter)
  }

  /**
   * Add a dual screen device hinge
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setShowHinge(hingeConfig: HingeConfig? = null): Unit {
    val parameter = SetShowHingeParameter(hingeConfig = hingeConfig)
    setShowHinge(parameter)
  }

  /**
   * Configuration data for drawing the source order of an elements children.
   */
  @Serializable
  public data class SourceOrderConfig(
    /**
     * the color to outline the givent element in.
     */
    public val parentOutlineColor: DOM.RGBA,
    /**
     * the color to outline the child elements in.
     */
    public val childOutlineColor: DOM.RGBA
  )

  /**
   * Configuration data for the highlighting of Grid elements.
   */
  @Serializable
  public data class GridHighlightConfig(
    /**
     * Whether the extension lines from grid cells to the rulers should be shown (default: false).
     */
    public val showGridExtensionLines: Boolean? = null,
    /**
     * Show Positive line number labels (default: false).
     */
    public val showPositiveLineNumbers: Boolean? = null,
    /**
     * Show Negative line number labels (default: false).
     */
    public val showNegativeLineNumbers: Boolean? = null,
    /**
     * Show area name labels (default: false).
     */
    public val showAreaNames: Boolean? = null,
    /**
     * Show line name labels (default: false).
     */
    public val showLineNames: Boolean? = null,
    /**
     * Show track size labels (default: false).
     */
    public val showTrackSizes: Boolean? = null,
    /**
     * The grid container border highlight color (default: transparent).
     */
    public val gridBorderColor: DOM.RGBA? = null,
    /**
     * The cell border color (default: transparent). Deprecated, please use rowLineColor and
     * columnLineColor instead.
     */
    public val cellBorderColor: DOM.RGBA? = null,
    /**
     * The row line color (default: transparent).
     */
    public val rowLineColor: DOM.RGBA? = null,
    /**
     * The column line color (default: transparent).
     */
    public val columnLineColor: DOM.RGBA? = null,
    /**
     * Whether the grid border is dashed (default: false).
     */
    public val gridBorderDash: Boolean? = null,
    /**
     * Whether the cell border is dashed (default: false). Deprecated, please us rowLineDash and
     * columnLineDash instead.
     */
    public val cellBorderDash: Boolean? = null,
    /**
     * Whether row lines are dashed (default: false).
     */
    public val rowLineDash: Boolean? = null,
    /**
     * Whether column lines are dashed (default: false).
     */
    public val columnLineDash: Boolean? = null,
    /**
     * The row gap highlight fill color (default: transparent).
     */
    public val rowGapColor: DOM.RGBA? = null,
    /**
     * The row gap hatching fill color (default: transparent).
     */
    public val rowHatchColor: DOM.RGBA? = null,
    /**
     * The column gap highlight fill color (default: transparent).
     */
    public val columnGapColor: DOM.RGBA? = null,
    /**
     * The column gap hatching fill color (default: transparent).
     */
    public val columnHatchColor: DOM.RGBA? = null,
    /**
     * The named grid areas border color (Default: transparent).
     */
    public val areaBorderColor: DOM.RGBA? = null,
    /**
     * The grid container background color (Default: transparent).
     */
    public val gridBackgroundColor: DOM.RGBA? = null
  )

  /**
   * Configuration data for the highlighting of Flex container elements.
   */
  @Serializable
  public data class FlexContainerHighlightConfig(
    /**
     * The style of the container border
     */
    public val containerBorder: LineStyle? = null,
    /**
     * The style of the separator between lines
     */
    public val lineSeparator: LineStyle? = null,
    /**
     * The style of the separator between items
     */
    public val itemSeparator: LineStyle? = null,
    /**
     * Style of content-distribution space on the main axis (justify-content).
     */
    public val mainDistributedSpace: BoxStyle? = null,
    /**
     * Style of content-distribution space on the cross axis (align-content).
     */
    public val crossDistributedSpace: BoxStyle? = null,
    /**
     * Style of empty space caused by row gaps (gap/row-gap).
     */
    public val rowGapSpace: BoxStyle? = null,
    /**
     * Style of empty space caused by columns gaps (gap/column-gap).
     */
    public val columnGapSpace: BoxStyle? = null,
    /**
     * Style of the self-alignment line (align-items).
     */
    public val crossAlignment: LineStyle? = null
  )

  /**
   * Configuration data for the highlighting of Flex item elements.
   */
  @Serializable
  public data class FlexItemHighlightConfig(
    /**
     * Style of the box representing the item's base size
     */
    public val baseSizeBox: BoxStyle? = null,
    /**
     * Style of the border around the box representing the item's base size
     */
    public val baseSizeBorder: LineStyle? = null,
    /**
     * Style of the arrow representing if the item grew or shrank
     */
    public val flexibilityArrow: LineStyle? = null
  )

  /**
   * Style information for drawing a line.
   */
  @Serializable
  public data class LineStyle(
    /**
     * The color of the line (default: transparent)
     */
    public val color: DOM.RGBA? = null,
    /**
     * The line pattern (default: solid)
     */
    public val pattern: String? = null
  )

  /**
   * Style information for drawing a box.
   */
  @Serializable
  public data class BoxStyle(
    /**
     * The background color for the box (default: transparent)
     */
    public val fillColor: DOM.RGBA? = null,
    /**
     * The hatching color for the box (default: transparent)
     */
    public val hatchColor: DOM.RGBA? = null
  )

  @Serializable
  public enum class ContrastAlgorithm {
    @SerialName("aa")
    AA,
    @SerialName("aaa")
    AAA,
    @SerialName("apca")
    APCA,
  }

  /**
   * Configuration data for the highlighting of page elements.
   */
  @Serializable
  public data class HighlightConfig(
    /**
     * Whether the node info tooltip should be shown (default: false).
     */
    public val showInfo: Boolean? = null,
    /**
     * Whether the node styles in the tooltip (default: false).
     */
    public val showStyles: Boolean? = null,
    /**
     * Whether the rulers should be shown (default: false).
     */
    public val showRulers: Boolean? = null,
    /**
     * Whether the a11y info should be shown (default: true).
     */
    public val showAccessibilityInfo: Boolean? = null,
    /**
     * Whether the extension lines from node to the rulers should be shown (default: false).
     */
    public val showExtensionLines: Boolean? = null,
    /**
     * The content box highlight fill color (default: transparent).
     */
    public val contentColor: DOM.RGBA? = null,
    /**
     * The padding highlight fill color (default: transparent).
     */
    public val paddingColor: DOM.RGBA? = null,
    /**
     * The border highlight fill color (default: transparent).
     */
    public val borderColor: DOM.RGBA? = null,
    /**
     * The margin highlight fill color (default: transparent).
     */
    public val marginColor: DOM.RGBA? = null,
    /**
     * The event target element highlight fill color (default: transparent).
     */
    public val eventTargetColor: DOM.RGBA? = null,
    /**
     * The shape outside fill color (default: transparent).
     */
    public val shapeColor: DOM.RGBA? = null,
    /**
     * The shape margin fill color (default: transparent).
     */
    public val shapeMarginColor: DOM.RGBA? = null,
    /**
     * The grid layout color (default: transparent).
     */
    public val cssGridColor: DOM.RGBA? = null,
    /**
     * The color format used to format color styles (default: hex).
     */
    public val colorFormat: ColorFormat? = null,
    /**
     * The grid layout highlight configuration (default: all transparent).
     */
    public val gridHighlightConfig: GridHighlightConfig? = null,
    /**
     * The flex container highlight configuration (default: all transparent).
     */
    public val flexContainerHighlightConfig: FlexContainerHighlightConfig? = null,
    /**
     * The flex item highlight configuration (default: all transparent).
     */
    public val flexItemHighlightConfig: FlexItemHighlightConfig? = null,
    /**
     * The contrast algorithm to use for the contrast ratio (default: aa).
     */
    public val contrastAlgorithm: ContrastAlgorithm? = null
  )

  @Serializable
  public enum class ColorFormat {
    @SerialName("rgb")
    RGB,
    @SerialName("hsl")
    HSL,
    @SerialName("hex")
    HEX,
  }

  /**
   * Configurations for Persistent Grid Highlight
   */
  @Serializable
  public data class GridNodeHighlightConfig(
    /**
     * A descriptor for the highlight appearance.
     */
    public val gridHighlightConfig: GridHighlightConfig,
    /**
     * Identifier of the node to highlight.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class FlexNodeHighlightConfig(
    /**
     * A descriptor for the highlight appearance of flex containers.
     */
    public val flexContainerHighlightConfig: FlexContainerHighlightConfig,
    /**
     * Identifier of the node to highlight.
     */
    public val nodeId: Int
  )

  /**
   * Configuration for dual screen hinge
   */
  @Serializable
  public data class HingeConfig(
    /**
     * A rectangle represent hinge
     */
    public val rect: DOM.Rect,
    /**
     * The content box highlight fill color (default: a dark color).
     */
    public val contentColor: DOM.RGBA? = null,
    /**
     * The content box highlight outline color (default: transparent).
     */
    public val outlineColor: DOM.RGBA? = null
  )

  @Serializable
  public enum class InspectMode {
    @SerialName("searchForNode")
    SEARCHFORNODE,
    @SerialName("searchForUAShadowDOM")
    SEARCHFORUASHADOWDOM,
    @SerialName("captureAreaScreenshot")
    CAPTUREAREASCREENSHOT,
    @SerialName("showDistances")
    SHOWDISTANCES,
    @SerialName("none")
    NONE,
  }

  /**
   * Fired when the node should be inspected. This happens after call to `setInspectMode` or when
   * user manually inspects an element.
   */
  public data class InspectNodeRequestedParameter(
    /**
     * Id of the node to inspect.
     */
    public val backendNodeId: Int
  )

  /**
   * Fired when the node should be highlighted. This happens after call to `setInspectMode`.
   */
  public data class NodeHighlightRequestedParameter(
    public val nodeId: Int
  )

  /**
   * Fired when user asks to capture screenshot of some area on the page.
   */
  public data class ScreenshotRequestedParameter(
    /**
     * Viewport to capture, in device independent pixels (dip).
     */
    public val viewport: Page.Viewport
  )

  @Serializable
  public data class GetHighlightObjectForTestParameter(
    /**
     * Id of the node to get highlight object for.
     */
    public val nodeId: Int,
    /**
     * Whether to include distance info.
     */
    public val includeDistance: Boolean? = null,
    /**
     * Whether to include style info.
     */
    public val includeStyle: Boolean? = null,
    /**
     * The color format to get config with (default: hex).
     */
    public val colorFormat: ColorFormat? = null,
    /**
     * Whether to show accessibility info (default: true).
     */
    public val showAccessibilityInfo: Boolean? = null
  )

  @Serializable
  public data class GetHighlightObjectForTestReturn(
    /**
     * Highlight data for the node.
     */
    public val highlight: Map<String, JsonElement>
  )

  @Serializable
  public data class GetGridHighlightObjectsForTestParameter(
    /**
     * Ids of the node to get highlight object for.
     */
    public val nodeIds: List<Int>
  )

  @Serializable
  public data class GetGridHighlightObjectsForTestReturn(
    /**
     * Grid Highlight data for the node ids provided.
     */
    public val highlights: Map<String, JsonElement>
  )

  @Serializable
  public data class GetSourceOrderHighlightObjectForTestParameter(
    /**
     * Id of the node to highlight.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class GetSourceOrderHighlightObjectForTestReturn(
    /**
     * Source order highlight data for the node id provided.
     */
    public val highlight: Map<String, JsonElement>
  )

  @Serializable
  public data class HighlightFrameParameter(
    /**
     * Identifier of the frame to highlight.
     */
    public val frameId: String,
    /**
     * The content box highlight fill color (default: transparent).
     */
    public val contentColor: DOM.RGBA? = null,
    /**
     * The content box highlight outline color (default: transparent).
     */
    public val contentOutlineColor: DOM.RGBA? = null
  )

  @Serializable
  public data class HighlightNodeParameter(
    /**
     * A descriptor for the highlight appearance.
     */
    public val highlightConfig: HighlightConfig,
    /**
     * Identifier of the node to highlight.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node to highlight.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node to be highlighted.
     */
    public val objectId: String? = null,
    /**
     * Selectors to highlight relevant nodes.
     */
    public val selector: String? = null
  )

  @Serializable
  public data class HighlightQuadParameter(
    /**
     * Quad to highlight
     */
    public val quad: List<Double>,
    /**
     * The highlight fill color (default: transparent).
     */
    public val color: DOM.RGBA? = null,
    /**
     * The highlight outline color (default: transparent).
     */
    public val outlineColor: DOM.RGBA? = null
  )

  @Serializable
  public data class HighlightRectParameter(
    /**
     * X coordinate
     */
    public val x: Int,
    /**
     * Y coordinate
     */
    public val y: Int,
    /**
     * Rectangle width
     */
    public val width: Int,
    /**
     * Rectangle height
     */
    public val height: Int,
    /**
     * The highlight fill color (default: transparent).
     */
    public val color: DOM.RGBA? = null,
    /**
     * The highlight outline color (default: transparent).
     */
    public val outlineColor: DOM.RGBA? = null
  )

  @Serializable
  public data class HighlightSourceOrderParameter(
    /**
     * A descriptor for the appearance of the overlay drawing.
     */
    public val sourceOrderConfig: SourceOrderConfig,
    /**
     * Identifier of the node to highlight.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node to highlight.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node to be highlighted.
     */
    public val objectId: String? = null
  )

  @Serializable
  public data class SetInspectModeParameter(
    /**
     * Set an inspection mode.
     */
    public val mode: InspectMode,
    /**
     * A descriptor for the highlight appearance of hovered-over nodes. May be omitted if `enabled
     * == false`.
     */
    public val highlightConfig: HighlightConfig? = null
  )

  @Serializable
  public data class SetShowAdHighlightsParameter(
    /**
     * True for showing ad highlights
     */
    public val show: Boolean
  )

  @Serializable
  public data class SetPausedInDebuggerMessageParameter(
    /**
     * The message to display, also triggers resume and step over controls.
     */
    public val message: String? = null
  )

  @Serializable
  public data class SetShowDebugBordersParameter(
    /**
     * True for showing debug borders
     */
    public val show: Boolean
  )

  @Serializable
  public data class SetShowFPSCounterParameter(
    /**
     * True for showing the FPS counter
     */
    public val show: Boolean
  )

  @Serializable
  public data class SetShowGridOverlaysParameter(
    /**
     * An array of node identifiers and descriptors for the highlight appearance.
     */
    public val gridNodeHighlightConfigs: List<GridNodeHighlightConfig>
  )

  @Serializable
  public data class SetShowFlexOverlaysParameter(
    /**
     * An array of node identifiers and descriptors for the highlight appearance.
     */
    public val flexNodeHighlightConfigs: List<FlexNodeHighlightConfig>
  )

  @Serializable
  public data class SetShowPaintRectsParameter(
    /**
     * True for showing paint rectangles
     */
    public val result: Boolean
  )

  @Serializable
  public data class SetShowLayoutShiftRegionsParameter(
    /**
     * True for showing layout shift regions
     */
    public val result: Boolean
  )

  @Serializable
  public data class SetShowScrollBottleneckRectsParameter(
    /**
     * True for showing scroll bottleneck rects
     */
    public val show: Boolean
  )

  @Serializable
  public data class SetShowHitTestBordersParameter(
    /**
     * True for showing hit-test borders
     */
    public val show: Boolean
  )

  @Serializable
  public data class SetShowWebVitalsParameter(
    public val show: Boolean
  )

  @Serializable
  public data class SetShowViewportSizeOnResizeParameter(
    /**
     * Whether to paint size or not.
     */
    public val show: Boolean
  )

  @Serializable
  public data class SetShowHingeParameter(
    /**
     * hinge data, null means hideHinge
     */
    public val hingeConfig: HingeConfig? = null
  )
}
