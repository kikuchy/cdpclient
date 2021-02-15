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
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.performanceTimeline: PerformanceTimeline
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(PerformanceTimeline(this))

/**
 * Reporting of performance timeline events, as specified in
 * https://w3c.github.io/performance-timeline/#dom-performanceobserver.
 */
public class PerformanceTimeline(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val timelineEventAdded: Flow<TimelineEventAddedParameter> = client
          .events
          .filter {
              it.method == "timelineEventAdded"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Previously buffered events would be reported before method returns.
   * See also: timelineEventAdded
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(args: EnableParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("PerformanceTimeline.enable", parameter)
  }

  public suspend fun enable(eventTypes: String): Unit {
    val parameter = EnableParameter(eventTypes = eventTypes)
    enable(parameter)
  }

  /**
   * See https://github.com/WICG/LargestContentfulPaint and largest_contentful_paint.idl
   */
  @Serializable
  public class LargestContentfulPaint(
    public val renderTime: Double,
    public val loadTime: Double,
    /**
     * The number of pixels being painted.
     */
    public val size: Double,
    /**
     * The id attribute of the element, if available.
     */
    public val elementId: String? = null,
    /**
     * The URL of the image (may be trimmed).
     */
    public val url: String? = null,
    public val nodeId: Int? = null
  )

  @Serializable
  public class LayoutShiftAttribution(
    public val previousRect: DOM.Rect,
    public val currentRect: DOM.Rect,
    public val nodeId: Int? = null
  )

  /**
   * See https://wicg.github.io/layout-instability/#sec-layout-shift and layout_shift.idl
   */
  @Serializable
  public class LayoutShift(
    /**
     * Score increment produced by this event.
     */
    public val value: Double,
    public val hadRecentInput: Boolean,
    public val lastInputTime: Double,
    public val sources: List<LayoutShiftAttribution>
  )

  @Serializable
  public class TimelineEvent(
    /**
     * Identifies the frame that this event is related to. Empty for non-frame targets.
     */
    public val frameId: String,
    /**
     * The event type, as specified in
     * https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype
     * This determines which of the optional "details" fiedls is present.
     */
    public val type: String,
    /**
     * Name may be empty depending on the type.
     */
    public val name: String,
    /**
     * Time in seconds since Epoch, monotonically increasing within document lifetime.
     */
    public val time: Double,
    /**
     * Event duration, if applicable.
     */
    public val duration: Double? = null,
    public val lcpDetails: LargestContentfulPaint? = null,
    public val layoutShiftDetails: LayoutShift? = null
  )

  /**
   * Sent when a performance timeline event is added. See reportPerformanceTimeline method.
   */
  public data class TimelineEventAddedParameter(
    public val event: TimelineEvent
  )

  @Serializable
  public data class EnableParameter(
    /**
     * The types of event to report, as specified in
     * https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype
     * The specified filter overrides any previous filters, passing empty
     * filter disables recording.
     * Note that not all types exposed to the web platform are currently supported.
     */
    public val eventTypes: String
  )
}
