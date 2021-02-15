package net.kikuchy.cdpclient.domain

import kotlin.Boolean
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

public val CDPClient.browser: Browser
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Browser(this))

/**
 * The Browser domain defines methods and events for browser managing.
 */
public class Browser(
  private val client: CDPClient
) : Domain {
  /**
   * Set permission settings for given origin.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setPermission(args: SetPermissionParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Browser.setPermission", parameter)
  }

  public suspend fun setPermission(
    permission: PermissionDescriptor,
    setting: PermissionSetting,
    origin: String? = null,
    browserContextId: String? = null
  ): Unit {
    val parameter = SetPermissionParameter(permission = permission,setting = setting,origin =
        origin,browserContextId = browserContextId)
    setPermission(parameter)
  }

  /**
   * Grant specific permissions to the given origin and reject all others.
   */
  @ExperimentalCoroutinesApi
  public suspend fun grantPermissions(args: GrantPermissionsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Browser.grantPermissions", parameter)
  }

  public suspend fun grantPermissions(
    permissions: List<PermissionType>,
    origin: String? = null,
    browserContextId: String? = null
  ): Unit {
    val parameter = GrantPermissionsParameter(permissions = permissions,origin =
        origin,browserContextId = browserContextId)
    grantPermissions(parameter)
  }

  /**
   * Reset all permission management for all origins.
   */
  @ExperimentalCoroutinesApi
  public suspend fun resetPermissions(args: ResetPermissionsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Browser.resetPermissions", parameter)
  }

  public suspend fun resetPermissions(browserContextId: String? = null): Unit {
    val parameter = ResetPermissionsParameter(browserContextId = browserContextId)
    resetPermissions(parameter)
  }

  /**
   * Set the behavior when downloading a file.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setDownloadBehavior(args: SetDownloadBehaviorParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Browser.setDownloadBehavior", parameter)
  }

  public suspend fun setDownloadBehavior(
    behavior: String,
    browserContextId: String? = null,
    downloadPath: String? = null
  ): Unit {
    val parameter = SetDownloadBehaviorParameter(behavior = behavior,browserContextId =
        browserContextId,downloadPath = downloadPath)
    setDownloadBehavior(parameter)
  }

  /**
   * Close browser gracefully.
   */
  @ExperimentalCoroutinesApi
  public suspend fun close(): Unit {
    val parameter = null
    client.callCommand("Browser.close", parameter)
  }

  /**
   * Crashes browser on the main thread.
   */
  @ExperimentalCoroutinesApi
  public suspend fun crash(): Unit {
    val parameter = null
    client.callCommand("Browser.crash", parameter)
  }

  /**
   * Crashes GPU process.
   */
  @ExperimentalCoroutinesApi
  public suspend fun crashGpuProcess(): Unit {
    val parameter = null
    client.callCommand("Browser.crashGpuProcess", parameter)
  }

  /**
   * Returns version information.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getVersion(): GetVersionReturn {
    val parameter = null
    val result = client.callCommand("Browser.getVersion", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns the command line switches for the browser process if, and only if
   * --enable-automation is on the commandline.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getBrowserCommandLine(): GetBrowserCommandLineReturn {
    val parameter = null
    val result = client.callCommand("Browser.getBrowserCommandLine", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Get Chrome histograms.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getHistograms(args: GetHistogramsParameter): GetHistogramsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Browser.getHistograms", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getHistograms(query: String? = null, delta: Boolean? = null):
      GetHistogramsReturn {
    val parameter = GetHistogramsParameter(query = query,delta = delta)
    return getHistograms(parameter)
  }

  /**
   * Get a Chrome histogram by name.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getHistogram(args: GetHistogramParameter): GetHistogramReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Browser.getHistogram", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getHistogram(name: String, delta: Boolean? = null): GetHistogramReturn {
    val parameter = GetHistogramParameter(name = name,delta = delta)
    return getHistogram(parameter)
  }

  /**
   * Get position and size of the browser window.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getWindowBounds(args: GetWindowBoundsParameter): GetWindowBoundsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Browser.getWindowBounds", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getWindowBounds(windowId: Int): GetWindowBoundsReturn {
    val parameter = GetWindowBoundsParameter(windowId = windowId)
    return getWindowBounds(parameter)
  }

  /**
   * Get the browser window that contains the devtools target.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getWindowForTarget(args: GetWindowForTargetParameter):
      GetWindowForTargetReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Browser.getWindowForTarget", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getWindowForTarget(targetId: String? = null): GetWindowForTargetReturn {
    val parameter = GetWindowForTargetParameter(targetId = targetId)
    return getWindowForTarget(parameter)
  }

  /**
   * Set position and/or size of the browser window.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setWindowBounds(args: SetWindowBoundsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Browser.setWindowBounds", parameter)
  }

  public suspend fun setWindowBounds(windowId: Int, bounds: Bounds): Unit {
    val parameter = SetWindowBoundsParameter(windowId = windowId,bounds = bounds)
    setWindowBounds(parameter)
  }

  /**
   * Set dock tile details, platform-specific.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setDockTile(args: SetDockTileParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Browser.setDockTile", parameter)
  }

  public suspend fun setDockTile(badgeLabel: String? = null, image: String? = null): Unit {
    val parameter = SetDockTileParameter(badgeLabel = badgeLabel,image = image)
    setDockTile(parameter)
  }

  /**
   * Invoke custom browser commands used by telemetry.
   */
  @ExperimentalCoroutinesApi
  public suspend fun executeBrowserCommand(args: ExecuteBrowserCommandParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Browser.executeBrowserCommand", parameter)
  }

  public suspend fun executeBrowserCommand(commandId: BrowserCommandId): Unit {
    val parameter = ExecuteBrowserCommandParameter(commandId = commandId)
    executeBrowserCommand(parameter)
  }

  /**
   * The state of the browser window.
   */
  @Serializable
  public enum class WindowState {
    @SerialName("normal")
    NORMAL,
    @SerialName("minimized")
    MINIMIZED,
    @SerialName("maximized")
    MAXIMIZED,
    @SerialName("fullscreen")
    FULLSCREEN,
  }

  /**
   * Browser window bounds information
   */
  @Serializable
  public class Bounds(
    /**
     * The offset from the left edge of the screen to the window in pixels.
     */
    public val left: Int? = null,
    /**
     * The offset from the top edge of the screen to the window in pixels.
     */
    public val top: Int? = null,
    /**
     * The window width in pixels.
     */
    public val width: Int? = null,
    /**
     * The window height in pixels.
     */
    public val height: Int? = null,
    /**
     * The window state. Default to normal.
     */
    public val windowState: WindowState? = null
  )

  @Serializable
  public enum class PermissionType {
    @SerialName("accessibilityEvents")
    ACCESSIBILITYEVENTS,
    @SerialName("audioCapture")
    AUDIOCAPTURE,
    @SerialName("backgroundSync")
    BACKGROUNDSYNC,
    @SerialName("backgroundFetch")
    BACKGROUNDFETCH,
    @SerialName("clipboardReadWrite")
    CLIPBOARDREADWRITE,
    @SerialName("clipboardSanitizedWrite")
    CLIPBOARDSANITIZEDWRITE,
    @SerialName("displayCapture")
    DISPLAYCAPTURE,
    @SerialName("durableStorage")
    DURABLESTORAGE,
    @SerialName("flash")
    FLASH,
    @SerialName("geolocation")
    GEOLOCATION,
    @SerialName("midi")
    MIDI,
    @SerialName("midiSysex")
    MIDISYSEX,
    @SerialName("nfc")
    NFC,
    @SerialName("notifications")
    NOTIFICATIONS,
    @SerialName("paymentHandler")
    PAYMENTHANDLER,
    @SerialName("periodicBackgroundSync")
    PERIODICBACKGROUNDSYNC,
    @SerialName("protectedMediaIdentifier")
    PROTECTEDMEDIAIDENTIFIER,
    @SerialName("sensors")
    SENSORS,
    @SerialName("videoCapture")
    VIDEOCAPTURE,
    @SerialName("videoCapturePanTiltZoom")
    VIDEOCAPTUREPANTILTZOOM,
    @SerialName("idleDetection")
    IDLEDETECTION,
    @SerialName("wakeLockScreen")
    WAKELOCKSCREEN,
    @SerialName("wakeLockSystem")
    WAKELOCKSYSTEM,
  }

  @Serializable
  public enum class PermissionSetting {
    @SerialName("granted")
    GRANTED,
    @SerialName("denied")
    DENIED,
    @SerialName("prompt")
    PROMPT,
  }

  /**
   * Definition of PermissionDescriptor defined in the Permissions API:
   * https://w3c.github.io/permissions/#dictdef-permissiondescriptor.
   */
  @Serializable
  public class PermissionDescriptor(
    /**
     * Name of permission.
     * See
     * https://cs.chromium.org/chromium/src/third_party/blink/renderer/modules/permissions/permission_descriptor.idl
     * for valid permission names.
     */
    public val name: String,
    /**
     * For "midi" permission, may also specify sysex control.
     */
    public val sysex: Boolean? = null,
    /**
     * For "push" permission, may specify userVisibleOnly.
     * Note that userVisibleOnly = true is the only currently supported type.
     */
    public val userVisibleOnly: Boolean? = null,
    /**
     * For "clipboard" permission, may specify allowWithoutSanitization.
     */
    public val allowWithoutSanitization: Boolean? = null,
    /**
     * For "camera" permission, may specify panTiltZoom.
     */
    public val panTiltZoom: Boolean? = null
  )

  /**
   * Browser command ids used by executeBrowserCommand.
   */
  @Serializable
  public enum class BrowserCommandId {
    @SerialName("openTabSearch")
    OPENTABSEARCH,
    @SerialName("closeTabSearch")
    CLOSETABSEARCH,
  }

  /**
   * Chrome histogram bucket.
   */
  @Serializable
  public class Bucket(
    /**
     * Minimum value (inclusive).
     */
    public val low: Int,
    /**
     * Maximum value (exclusive).
     */
    public val high: Int,
    /**
     * Number of samples.
     */
    public val count: Int
  )

  /**
   * Chrome histogram.
   */
  @Serializable
  public class Histogram(
    /**
     * Name.
     */
    public val name: String,
    /**
     * Sum of sample values.
     */
    public val sum: Int,
    /**
     * Total number of samples.
     */
    public val count: Int,
    /**
     * Buckets.
     */
    public val buckets: List<Bucket>
  )

  @Serializable
  public data class SetPermissionParameter(
    /**
     * Descriptor of permission to override.
     */
    public val permission: PermissionDescriptor,
    /**
     * Setting of the permission.
     */
    public val setting: PermissionSetting,
    /**
     * Origin the permission applies to, all origins if not specified.
     */
    public val origin: String? = null,
    /**
     * Context to override. When omitted, default browser context is used.
     */
    public val browserContextId: String? = null
  )

  @Serializable
  public data class GrantPermissionsParameter(
    public val permissions: List<PermissionType>,
    /**
     * Origin the permission applies to, all origins if not specified.
     */
    public val origin: String? = null,
    /**
     * BrowserContext to override permissions. When omitted, default browser context is used.
     */
    public val browserContextId: String? = null
  )

  @Serializable
  public data class ResetPermissionsParameter(
    /**
     * BrowserContext to reset permissions. When omitted, default browser context is used.
     */
    public val browserContextId: String? = null
  )

  @Serializable
  public data class SetDownloadBehaviorParameter(
    /**
     * Whether to allow all or deny all download requests, or use default Chrome behavior if
     * available (otherwise deny). |allowAndName| allows download and names files according to
     * their dowmload guids.
     */
    public val behavior: String,
    /**
     * BrowserContext to set download behavior. When omitted, default browser context is used.
     */
    public val browserContextId: String? = null,
    /**
     * The default path to save downloaded files to. This is requred if behavior is set to 'allow'
     * or 'allowAndName'.
     */
    public val downloadPath: String? = null
  )

  @Serializable
  public data class GetVersionReturn(
    /**
     * Protocol version.
     */
    public val protocolVersion: String,
    /**
     * Product name.
     */
    public val product: String,
    /**
     * Product revision.
     */
    public val revision: String,
    /**
     * User-Agent.
     */
    public val userAgent: String,
    /**
     * V8 version.
     */
    public val jsVersion: String
  )

  @Serializable
  public data class GetBrowserCommandLineReturn(
    /**
     * Commandline parameters
     */
    public val arguments: String
  )

  @Serializable
  public data class GetHistogramsParameter(
    /**
     * Requested substring in name. Only histograms which have query as a
     * substring in their name are extracted. An empty or absent query returns
     * all histograms.
     */
    public val query: String? = null,
    /**
     * If true, retrieve delta since last call.
     */
    public val delta: Boolean? = null
  )

  @Serializable
  public data class GetHistogramsReturn(
    /**
     * Histograms.
     */
    public val histograms: List<Histogram>
  )

  @Serializable
  public data class GetHistogramParameter(
    /**
     * Requested histogram name.
     */
    public val name: String,
    /**
     * If true, retrieve delta since last call.
     */
    public val delta: Boolean? = null
  )

  @Serializable
  public data class GetHistogramReturn(
    /**
     * Histogram.
     */
    public val histogram: Histogram
  )

  @Serializable
  public data class GetWindowBoundsParameter(
    /**
     * Browser window id.
     */
    public val windowId: Int
  )

  @Serializable
  public data class GetWindowBoundsReturn(
    /**
     * Bounds information of the window. When window state is 'minimized', the restored window
     * position and size are returned.
     */
    public val bounds: Bounds
  )

  @Serializable
  public data class GetWindowForTargetParameter(
    /**
     * Devtools agent host id. If called as a part of the session, associated targetId is used.
     */
    public val targetId: String? = null
  )

  @Serializable
  public data class GetWindowForTargetReturn(
    /**
     * Browser window id.
     */
    public val windowId: Int,
    /**
     * Bounds information of the window. When window state is 'minimized', the restored window
     * position and size are returned.
     */
    public val bounds: Bounds
  )

  @Serializable
  public data class SetWindowBoundsParameter(
    /**
     * Browser window id.
     */
    public val windowId: Int,
    /**
     * New window bounds. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined
     * with 'left', 'top', 'width' or 'height'. Leaves unspecified fields unchanged.
     */
    public val bounds: Bounds
  )

  @Serializable
  public data class SetDockTileParameter(
    public val badgeLabel: String? = null,
    /**
     * Png encoded image. (Encoded as a base64 string when passed over JSON)
     */
    public val image: String? = null
  )

  @Serializable
  public data class ExecuteBrowserCommandParameter(
    public val commandId: BrowserCommandId
  )
}
