package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Deprecated
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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.emulation: Emulation
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Emulation(this))

/**
 * This domain emulates different environments for the page.
 */
public class Emulation(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val virtualTimeBudgetExpired: Flow<Unit> = client
          .events
          .filter {
              it.method == "virtualTimeBudgetExpired"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Tells whether emulation is supported.
   */
  @ExperimentalCoroutinesApi
  public suspend fun canEmulate(): CanEmulateReturn {
    val parameter = null
    val result = client.callCommand("Emulation.canEmulate", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Clears the overriden device metrics.
   */
  @ExperimentalCoroutinesApi
  public suspend fun clearDeviceMetricsOverride(): Unit {
    val parameter = null
    client.callCommand("Emulation.clearDeviceMetricsOverride", parameter)
  }

  /**
   * Clears the overriden Geolocation Position and Error.
   */
  @ExperimentalCoroutinesApi
  public suspend fun clearGeolocationOverride(): Unit {
    val parameter = null
    client.callCommand("Emulation.clearGeolocationOverride", parameter)
  }

  /**
   * Requests that page scale factor is reset to initial values.
   */
  @ExperimentalCoroutinesApi
  public suspend fun resetPageScaleFactor(): Unit {
    val parameter = null
    client.callCommand("Emulation.resetPageScaleFactor", parameter)
  }

  /**
   * Enables or disables simulating a focused and active page.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setFocusEmulationEnabled(args: SetFocusEmulationEnabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setFocusEmulationEnabled", parameter)
  }

  public suspend fun setFocusEmulationEnabled(enabled: Boolean): Unit {
    val parameter = SetFocusEmulationEnabledParameter(enabled = enabled)
    setFocusEmulationEnabled(parameter)
  }

  /**
   * Enables CPU throttling to emulate slow CPUs.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setCPUThrottlingRate(args: SetCPUThrottlingRateParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setCPUThrottlingRate", parameter)
  }

  public suspend fun setCPUThrottlingRate(rate: Double): Unit {
    val parameter = SetCPUThrottlingRateParameter(rate = rate)
    setCPUThrottlingRate(parameter)
  }

  /**
   * Sets or clears an override of the default background color of the frame. This override is used
   * if the content does not specify one.
   */
  @ExperimentalCoroutinesApi
  public suspend
      fun setDefaultBackgroundColorOverride(args: SetDefaultBackgroundColorOverrideParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setDefaultBackgroundColorOverride", parameter)
  }

  public suspend fun setDefaultBackgroundColorOverride(color: DOM.RGBA? = null): Unit {
    val parameter = SetDefaultBackgroundColorOverrideParameter(color = color)
    setDefaultBackgroundColorOverride(parameter)
  }

  /**
   * Overrides the values of device screen dimensions (window.screen.width, window.screen.height,
   * window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media
   * query results).
   */
  @ExperimentalCoroutinesApi
  public suspend fun setDeviceMetricsOverride(args: SetDeviceMetricsOverrideParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setDeviceMetricsOverride", parameter)
  }

  public suspend fun setDeviceMetricsOverride(
    width: Int,
    height: Int,
    deviceScaleFactor: Double,
    mobile: Boolean,
    scale: Double? = null,
    screenWidth: Int? = null,
    screenHeight: Int? = null,
    positionX: Int? = null,
    positionY: Int? = null,
    dontSetVisibleSize: Boolean? = null,
    screenOrientation: ScreenOrientation? = null,
    viewport: Page.Viewport? = null,
    displayFeature: DisplayFeature? = null
  ): Unit {
    val parameter = SetDeviceMetricsOverrideParameter(width = width,height =
        height,deviceScaleFactor = deviceScaleFactor,mobile = mobile,scale = scale,screenWidth =
        screenWidth,screenHeight = screenHeight,positionX = positionX,positionY =
        positionY,dontSetVisibleSize = dontSetVisibleSize,screenOrientation =
        screenOrientation,viewport = viewport,displayFeature = displayFeature)
    setDeviceMetricsOverride(parameter)
  }

  @ExperimentalCoroutinesApi
  public suspend fun setScrollbarsHidden(args: SetScrollbarsHiddenParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setScrollbarsHidden", parameter)
  }

  public suspend fun setScrollbarsHidden(hidden: Boolean): Unit {
    val parameter = SetScrollbarsHiddenParameter(hidden = hidden)
    setScrollbarsHidden(parameter)
  }

  @ExperimentalCoroutinesApi
  public suspend fun setDocumentCookieDisabled(args: SetDocumentCookieDisabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setDocumentCookieDisabled", parameter)
  }

  public suspend fun setDocumentCookieDisabled(disabled: Boolean): Unit {
    val parameter = SetDocumentCookieDisabledParameter(disabled = disabled)
    setDocumentCookieDisabled(parameter)
  }

  @ExperimentalCoroutinesApi
  public suspend fun setEmitTouchEventsForMouse(args: SetEmitTouchEventsForMouseParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setEmitTouchEventsForMouse", parameter)
  }

  public suspend fun setEmitTouchEventsForMouse(enabled: Boolean, configuration: String? = null):
      Unit {
    val parameter = SetEmitTouchEventsForMouseParameter(enabled = enabled,configuration =
        configuration)
    setEmitTouchEventsForMouse(parameter)
  }

  /**
   * Emulates the given media type or media feature for CSS media queries.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setEmulatedMedia(args: SetEmulatedMediaParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setEmulatedMedia", parameter)
  }

  public suspend fun setEmulatedMedia(media: String? = null, features: List<MediaFeature>? = null):
      Unit {
    val parameter = SetEmulatedMediaParameter(media = media,features = features)
    setEmulatedMedia(parameter)
  }

  /**
   * Emulates the given vision deficiency.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setEmulatedVisionDeficiency(args: SetEmulatedVisionDeficiencyParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setEmulatedVisionDeficiency", parameter)
  }

  public suspend fun setEmulatedVisionDeficiency(type: String): Unit {
    val parameter = SetEmulatedVisionDeficiencyParameter(type = type)
    setEmulatedVisionDeficiency(parameter)
  }

  /**
   * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position
   * unavailable.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setGeolocationOverride(args: SetGeolocationOverrideParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setGeolocationOverride", parameter)
  }

  public suspend fun setGeolocationOverride(
    latitude: Double? = null,
    longitude: Double? = null,
    accuracy: Double? = null
  ): Unit {
    val parameter = SetGeolocationOverrideParameter(latitude = latitude,longitude =
        longitude,accuracy = accuracy)
    setGeolocationOverride(parameter)
  }

  /**
   * Overrides the Idle state.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setIdleOverride(args: SetIdleOverrideParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setIdleOverride", parameter)
  }

  public suspend fun setIdleOverride(isUserActive: Boolean, isScreenUnlocked: Boolean): Unit {
    val parameter = SetIdleOverrideParameter(isUserActive = isUserActive,isScreenUnlocked =
        isScreenUnlocked)
    setIdleOverride(parameter)
  }

  /**
   * Clears Idle state overrides.
   */
  @ExperimentalCoroutinesApi
  public suspend fun clearIdleOverride(): Unit {
    val parameter = null
    client.callCommand("Emulation.clearIdleOverride", parameter)
  }

  /**
   * Overrides value returned by the javascript navigator object.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setNavigatorOverrides(args: SetNavigatorOverridesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setNavigatorOverrides", parameter)
  }

  public suspend fun setNavigatorOverrides(platform: String): Unit {
    val parameter = SetNavigatorOverridesParameter(platform = platform)
    setNavigatorOverrides(parameter)
  }

  /**
   * Sets a specified page scale factor.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setPageScaleFactor(args: SetPageScaleFactorParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setPageScaleFactor", parameter)
  }

  public suspend fun setPageScaleFactor(pageScaleFactor: Double): Unit {
    val parameter = SetPageScaleFactorParameter(pageScaleFactor = pageScaleFactor)
    setPageScaleFactor(parameter)
  }

  /**
   * Switches script execution in the page.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setScriptExecutionDisabled(args: SetScriptExecutionDisabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setScriptExecutionDisabled", parameter)
  }

  public suspend fun setScriptExecutionDisabled(value: Boolean): Unit {
    val parameter = SetScriptExecutionDisabledParameter(value = value)
    setScriptExecutionDisabled(parameter)
  }

  /**
   * Enables touch on platforms which do not support them.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setTouchEmulationEnabled(args: SetTouchEmulationEnabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setTouchEmulationEnabled", parameter)
  }

  public suspend fun setTouchEmulationEnabled(enabled: Boolean, maxTouchPoints: Int? = null): Unit {
    val parameter = SetTouchEmulationEnabledParameter(enabled = enabled,maxTouchPoints =
        maxTouchPoints)
    setTouchEmulationEnabled(parameter)
  }

  /**
   * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and
   * sets
   * the current virtual time policy.  Note this supersedes any previous time budget.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setVirtualTimePolicy(args: SetVirtualTimePolicyParameter):
      SetVirtualTimePolicyReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Emulation.setVirtualTimePolicy", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setVirtualTimePolicy(
    policy: VirtualTimePolicy,
    budget: Double? = null,
    maxVirtualTimeTaskStarvationCount: Int? = null,
    waitForNavigation: Boolean? = null,
    initialVirtualTime: Double? = null
  ): SetVirtualTimePolicyReturn {
    val parameter = SetVirtualTimePolicyParameter(policy = policy,budget =
        budget,maxVirtualTimeTaskStarvationCount =
        maxVirtualTimeTaskStarvationCount,waitForNavigation = waitForNavigation,initialVirtualTime =
        initialVirtualTime)
    return setVirtualTimePolicy(parameter)
  }

  /**
   * Overrides default host system locale with the specified one.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setLocaleOverride(args: SetLocaleOverrideParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setLocaleOverride", parameter)
  }

  public suspend fun setLocaleOverride(locale: String? = null): Unit {
    val parameter = SetLocaleOverrideParameter(locale = locale)
    setLocaleOverride(parameter)
  }

  /**
   * Overrides default host system timezone with the specified one.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setTimezoneOverride(args: SetTimezoneOverrideParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setTimezoneOverride", parameter)
  }

  public suspend fun setTimezoneOverride(timezoneId: String): Unit {
    val parameter = SetTimezoneOverrideParameter(timezoneId = timezoneId)
    setTimezoneOverride(parameter)
  }

  /**
   * Resizes the frame/viewport of the page. Note that this does not affect the frame's container
   * (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported
   * on Android.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setVisibleSize(args: SetVisibleSizeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setVisibleSize", parameter)
  }

  public suspend fun setVisibleSize(width: Int, height: Int): Unit {
    val parameter = SetVisibleSizeParameter(width = width,height = height)
    setVisibleSize(parameter)
  }

  @ExperimentalCoroutinesApi
  public suspend fun setDisabledImageTypes(args: SetDisabledImageTypesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setDisabledImageTypes", parameter)
  }

  public suspend fun setDisabledImageTypes(imageTypes: List<DisabledImageType>): Unit {
    val parameter = SetDisabledImageTypesParameter(imageTypes = imageTypes)
    setDisabledImageTypes(parameter)
  }

  /**
   * Allows overriding user agent with the given string.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setUserAgentOverride(args: SetUserAgentOverrideParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Emulation.setUserAgentOverride", parameter)
  }

  public suspend fun setUserAgentOverride(
    userAgent: String,
    acceptLanguage: String? = null,
    platform: String? = null,
    userAgentMetadata: UserAgentMetadata? = null
  ): Unit {
    val parameter = SetUserAgentOverrideParameter(userAgent = userAgent,acceptLanguage =
        acceptLanguage,platform = platform,userAgentMetadata = userAgentMetadata)
    setUserAgentOverride(parameter)
  }

  /**
   * Screen orientation.
   */
  @Serializable
  public class ScreenOrientation(
    /**
     * Orientation type.
     */
    public val type: String,
    /**
     * Orientation angle.
     */
    public val angle: Int
  )

  @Serializable
  public class DisplayFeature(
    /**
     * Orientation of a display feature in relation to screen
     */
    public val orientation: String,
    /**
     * The offset from the screen origin in either the x (for vertical
     * orientation) or y (for horizontal orientation) direction.
     */
    public val offset: Int,
    /**
     * A display feature may mask content such that it is not physically
     * displayed - this length along with the offset describes this area.
     * A display feature that only splits content will have a 0 mask_length.
     */
    public val maskLength: Int
  )

  @Serializable
  public class MediaFeature(
    public val name: String,
    public val value: String
  )

  /**
   * advance: If the scheduler runs out of immediate work, the virtual time base may fast forward to
   * allow the next delayed task (if any) to run; pause: The virtual time base may not advance;
   * pauseIfNetworkFetchesPending: The virtual time base may not advance if there are any pending
   * resource fetches.
   */
  @Serializable
  public enum class VirtualTimePolicy {
    @SerialName("advance")
    ADVANCE,
    @SerialName("pause")
    PAUSE,
    @SerialName("pauseIfNetworkFetchesPending")
    PAUSEIFNETWORKFETCHESPENDING,
  }

  /**
   * Used to specify User Agent Cient Hints to emulate. See https://wicg.github.io/ua-client-hints
   */
  @Serializable
  public class UserAgentBrandVersion(
    public val brand: String,
    public val version: String
  )

  /**
   * Used to specify User Agent Cient Hints to emulate. See https://wicg.github.io/ua-client-hints
   * Missing optional values will be filled in by the target with what it would normally use.
   */
  @Serializable
  public class UserAgentMetadata(
    public val brands: List<UserAgentBrandVersion>? = null,
    public val fullVersion: String? = null,
    public val platform: String,
    public val platformVersion: String,
    public val architecture: String,
    public val model: String,
    public val mobile: Boolean
  )

  /**
   * Enum of image types that can be disabled.
   */
  @Serializable
  public enum class DisabledImageType {
    @SerialName("avif")
    AVIF,
    @SerialName("webp")
    WEBP,
  }

  @Serializable
  public data class CanEmulateReturn(
    /**
     * True if emulation is supported.
     */
    public val result: Boolean
  )

  @Serializable
  public data class SetFocusEmulationEnabledParameter(
    /**
     * Whether to enable to disable focus emulation.
     */
    public val enabled: Boolean
  )

  @Serializable
  public data class SetCPUThrottlingRateParameter(
    /**
     * Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).
     */
    public val rate: Double
  )

  @Serializable
  public data class SetDefaultBackgroundColorOverrideParameter(
    /**
     * RGBA of the default background color. If not specified, any existing override will be
     * cleared.
     */
    public val color: DOM.RGBA? = null
  )

  @Serializable
  public data class SetDeviceMetricsOverrideParameter(
    /**
     * Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     */
    public val width: Int,
    /**
     * Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     */
    public val height: Int,
    /**
     * Overriding device scale factor value. 0 disables the override.
     */
    public val deviceScaleFactor: Double,
    /**
     * Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text
     * autosizing and more.
     */
    public val mobile: Boolean,
    /**
     * Scale to apply to resulting view image.
     */
    public val scale: Double? = null,
    /**
     * Overriding screen width value in pixels (minimum 0, maximum 10000000).
     */
    public val screenWidth: Int? = null,
    /**
     * Overriding screen height value in pixels (minimum 0, maximum 10000000).
     */
    public val screenHeight: Int? = null,
    /**
     * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
     */
    public val positionX: Int? = null,
    /**
     * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
     */
    public val positionY: Int? = null,
    /**
     * Do not set visible view size, rely upon explicit setVisibleSize call.
     */
    public val dontSetVisibleSize: Boolean? = null,
    /**
     * Screen orientation override.
     */
    public val screenOrientation: ScreenOrientation? = null,
    /**
     * If set, the visible area of the page will be overridden to this viewport. This viewport
     * change is not observed by the page, e.g. viewport-relative elements do not change positions.
     */
    public val viewport: Page.Viewport? = null,
    /**
     * If set, the display feature of a multi-segment screen. If not set, multi-segment support
     * is turned-off.
     */
    public val displayFeature: DisplayFeature? = null
  )

  @Serializable
  public data class SetScrollbarsHiddenParameter(
    /**
     * Whether scrollbars should be always hidden.
     */
    public val hidden: Boolean
  )

  @Serializable
  public data class SetDocumentCookieDisabledParameter(
    /**
     * Whether document.coookie API should be disabled.
     */
    public val disabled: Boolean
  )

  @Serializable
  public data class SetEmitTouchEventsForMouseParameter(
    /**
     * Whether touch emulation based on mouse input should be enabled.
     */
    public val enabled: Boolean,
    /**
     * Touch/gesture events configuration. Default: current platform.
     */
    public val configuration: String? = null
  )

  @Serializable
  public data class SetEmulatedMediaParameter(
    /**
     * Media type to emulate. Empty string disables the override.
     */
    public val media: String? = null,
    /**
     * Media features to emulate.
     */
    public val features: List<MediaFeature>? = null
  )

  @Serializable
  public data class SetEmulatedVisionDeficiencyParameter(
    /**
     * Vision deficiency to emulate.
     */
    public val type: String
  )

  @Serializable
  public data class SetGeolocationOverrideParameter(
    /**
     * Mock latitude
     */
    public val latitude: Double? = null,
    /**
     * Mock longitude
     */
    public val longitude: Double? = null,
    /**
     * Mock accuracy
     */
    public val accuracy: Double? = null
  )

  @Serializable
  public data class SetIdleOverrideParameter(
    /**
     * Mock isUserActive
     */
    public val isUserActive: Boolean,
    /**
     * Mock isScreenUnlocked
     */
    public val isScreenUnlocked: Boolean
  )

  @Serializable
  public data class SetNavigatorOverridesParameter(
    /**
     * The platform navigator.platform should return.
     */
    public val platform: String
  )

  @Serializable
  public data class SetPageScaleFactorParameter(
    /**
     * Page scale factor.
     */
    public val pageScaleFactor: Double
  )

  @Serializable
  public data class SetScriptExecutionDisabledParameter(
    /**
     * Whether script execution should be disabled in the page.
     */
    public val value: Boolean
  )

  @Serializable
  public data class SetTouchEmulationEnabledParameter(
    /**
     * Whether the touch event emulation should be enabled.
     */
    public val enabled: Boolean,
    /**
     * Maximum touch points supported. Defaults to one.
     */
    public val maxTouchPoints: Int? = null
  )

  @Serializable
  public data class SetVirtualTimePolicyParameter(
    public val policy: VirtualTimePolicy,
    /**
     * If set, after this many virtual milliseconds have elapsed virtual time will be paused and a
     * virtualTimeBudgetExpired event is sent.
     */
    public val budget: Double? = null,
    /**
     * If set this specifies the maximum number of tasks that can be run before virtual is forced
     * forwards to prevent deadlock.
     */
    public val maxVirtualTimeTaskStarvationCount: Int? = null,
    /**
     * If set the virtual time policy change should be deferred until any frame starts navigating.
     * Note any previous deferred policy change is superseded.
     */
    public val waitForNavigation: Boolean? = null,
    /**
     * If set, base::Time::Now will be overriden to initially return this value.
     */
    public val initialVirtualTime: Double? = null
  )

  @Serializable
  public data class SetVirtualTimePolicyReturn(
    /**
     * Absolute timestamp at which virtual time was first enabled (up time in milliseconds).
     */
    public val virtualTimeTicksBase: Double
  )

  @Serializable
  public data class SetLocaleOverrideParameter(
    /**
     * ICU style C locale (e.g. "en_US"). If not specified or empty, disables the override and
     * restores default host system locale.
     */
    public val locale: String? = null
  )

  @Serializable
  public data class SetTimezoneOverrideParameter(
    /**
     * The timezone identifier. If empty, disables the override and
     * restores default host system timezone.
     */
    public val timezoneId: String
  )

  @Serializable
  public data class SetVisibleSizeParameter(
    /**
     * Frame width (DIP).
     */
    public val width: Int,
    /**
     * Frame height (DIP).
     */
    public val height: Int
  )

  @Serializable
  public data class SetDisabledImageTypesParameter(
    /**
     * Image types to disable.
     */
    public val imageTypes: List<DisabledImageType>
  )

  @Serializable
  public data class SetUserAgentOverrideParameter(
    /**
     * User agent to use.
     */
    public val userAgent: String,
    /**
     * Browser langugage to emulate.
     */
    public val acceptLanguage: String? = null,
    /**
     * The platform navigator.platform should return.
     */
    public val platform: String? = null,
    /**
     * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
     */
    public val userAgentMetadata: UserAgentMetadata? = null
  )
}
