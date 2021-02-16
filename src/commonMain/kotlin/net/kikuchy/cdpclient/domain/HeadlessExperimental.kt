package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
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

public val CDPClient.headlessExperimental: HeadlessExperimental
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(HeadlessExperimental(this))

/**
 * This domain provides experimental commands only supported in headless mode.
 */
public class HeadlessExperimental(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val needsBeginFramesChanged: Flow<NeedsBeginFramesChangedParameter> = client
          .events
          .filter {
              it.method == "needsBeginFramesChanged"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures
   * a
   * screenshot from the resulting frame. Requires that the target was created with enabled
   * BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also
   * https://goo.gl/3zHXhB for more background.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun beginFrame(args: BeginFrameParameter): BeginFrameReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("HeadlessExperimental.beginFrame", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures
   * a
   * screenshot from the resulting frame. Requires that the target was created with enabled
   * BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also
   * https://goo.gl/3zHXhB for more background.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun beginFrame(
    frameTimeTicks: Double? = null,
    interval: Double? = null,
    noDisplayUpdates: Boolean? = null,
    screenshot: ScreenshotParams? = null
  ): BeginFrameReturn {
    val parameter = BeginFrameParameter(frameTimeTicks = frameTimeTicks,interval =
        interval,noDisplayUpdates = noDisplayUpdates,screenshot = screenshot)
    return beginFrame(parameter)
  }

  /**
   * Disables headless events for the target.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("HeadlessExperimental.disable", parameter)
  }

  /**
   * Enables headless events for the target.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("HeadlessExperimental.enable", parameter)
  }

  /**
   * Encoding options for a screenshot.
   */
  @Serializable
  public data class ScreenshotParams(
    /**
     * Image compression format (defaults to png).
     */
    public val format: String? = null,
    /**
     * Compression quality from range [0..100] (jpeg only).
     */
    public val quality: Int? = null
  )

  /**
   * Issued when the target starts or stops needing BeginFrames.
   * Deprecated. Issue beginFrame unconditionally instead and use result from
   * beginFrame to detect whether the frames were suppressed.
   */
  public data class NeedsBeginFramesChangedParameter(
    /**
     * True if BeginFrames are needed, false otherwise.
     */
    public val needsBeginFrames: Boolean
  )

  @Serializable
  public data class BeginFrameParameter(
    /**
     * Timestamp of this BeginFrame in Renderer TimeTicks (milliseconds of uptime). If not set,
     * the current time will be used.
     */
    public val frameTimeTicks: Double? = null,
    /**
     * The interval between BeginFrames that is reported to the compositor, in milliseconds.
     * Defaults to a 60 frames/second interval, i.e. about 16.666 milliseconds.
     */
    public val interval: Double? = null,
    /**
     * Whether updates should not be committed and drawn onto the display. False by default. If
     * true, only side effects of the BeginFrame will be run, such as layout and animations, but
     * any visual updates may not be visible on the display or in screenshots.
     */
    public val noDisplayUpdates: Boolean? = null,
    /**
     * If set, a screenshot of the frame will be captured and returned in the response. Otherwise,
     * no screenshot will be captured. Note that capturing a screenshot can fail, for example,
     * during renderer initialization. In such a case, no screenshot data will be returned.
     */
    public val screenshot: ScreenshotParams? = null
  )

  @Serializable
  public data class BeginFrameReturn(
    /**
     * Whether the BeginFrame resulted in damage and, thus, a new frame was committed to the
     * display. Reported for diagnostic uses, may be removed in the future.
     */
    public val hasDamage: Boolean,
    /**
     * Base64-encoded image data of the screenshot, if one was requested and successfully taken.
     * (Encoded as a base64 string when passed over JSON)
     */
    public val screenshotData: String?
  )
}
