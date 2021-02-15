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

public val CDPClient.page: Page
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Page(this))

/**
 * Actions and events related to the inspected page belong to the page domain.
 */
public class Page(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val domContentEventFired: Flow<DomContentEventFiredParameter> = client
          .events
          .filter {
              it.method == "domContentEventFired"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val fileChooserOpened: Flow<FileChooserOpenedParameter> = client
          .events
          .filter {
              it.method == "fileChooserOpened"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameAttached: Flow<FrameAttachedParameter> = client
          .events
          .filter {
              it.method == "frameAttached"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameClearedScheduledNavigation: Flow<FrameClearedScheduledNavigationParameter> =
      client
          .events
          .filter {
              it.method == "frameClearedScheduledNavigation"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameDetached: Flow<FrameDetachedParameter> = client
          .events
          .filter {
              it.method == "frameDetached"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameNavigated: Flow<FrameNavigatedParameter> = client
          .events
          .filter {
              it.method == "frameNavigated"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val documentOpened: Flow<DocumentOpenedParameter> = client
          .events
          .filter {
              it.method == "documentOpened"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameResized: Flow<Unit> = client
          .events
          .filter {
              it.method == "frameResized"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameRequestedNavigation: Flow<FrameRequestedNavigationParameter> = client
          .events
          .filter {
              it.method == "frameRequestedNavigation"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameScheduledNavigation: Flow<FrameScheduledNavigationParameter> = client
          .events
          .filter {
              it.method == "frameScheduledNavigation"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameStartedLoading: Flow<FrameStartedLoadingParameter> = client
          .events
          .filter {
              it.method == "frameStartedLoading"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val frameStoppedLoading: Flow<FrameStoppedLoadingParameter> = client
          .events
          .filter {
              it.method == "frameStoppedLoading"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val downloadWillBegin: Flow<DownloadWillBeginParameter> = client
          .events
          .filter {
              it.method == "downloadWillBegin"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val downloadProgress: Flow<DownloadProgressParameter> = client
          .events
          .filter {
              it.method == "downloadProgress"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val interstitialHidden: Flow<Unit> = client
          .events
          .filter {
              it.method == "interstitialHidden"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val interstitialShown: Flow<Unit> = client
          .events
          .filter {
              it.method == "interstitialShown"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val javascriptDialogClosed: Flow<JavascriptDialogClosedParameter> = client
          .events
          .filter {
              it.method == "javascriptDialogClosed"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val javascriptDialogOpening: Flow<JavascriptDialogOpeningParameter> = client
          .events
          .filter {
              it.method == "javascriptDialogOpening"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val lifecycleEvent: Flow<LifecycleEventParameter> = client
          .events
          .filter {
              it.method == "lifecycleEvent"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val loadEventFired: Flow<LoadEventFiredParameter> = client
          .events
          .filter {
              it.method == "loadEventFired"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val navigatedWithinDocument: Flow<NavigatedWithinDocumentParameter> = client
          .events
          .filter {
              it.method == "navigatedWithinDocument"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val screencastFrame: Flow<ScreencastFrameParameter> = client
          .events
          .filter {
              it.method == "screencastFrame"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val screencastVisibilityChanged: Flow<ScreencastVisibilityChangedParameter> = client
          .events
          .filter {
              it.method == "screencastVisibilityChanged"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val windowOpen: Flow<WindowOpenParameter> = client
          .events
          .filter {
              it.method == "windowOpen"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val compilationCacheProduced: Flow<CompilationCacheProducedParameter> = client
          .events
          .filter {
              it.method == "compilationCacheProduced"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun addScriptToEvaluateOnLoad(args: AddScriptToEvaluateOnLoadParameter):
      AddScriptToEvaluateOnLoadReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.addScriptToEvaluateOnLoad", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun addScriptToEvaluateOnLoad(scriptSource: String):
      AddScriptToEvaluateOnLoadReturn {
    val parameter = AddScriptToEvaluateOnLoadParameter(scriptSource = scriptSource)
    return addScriptToEvaluateOnLoad(parameter)
  }

  /**
   * Evaluates given script in every frame upon creation (before loading frame's scripts).
   */
  @ExperimentalCoroutinesApi
  public suspend
      fun addScriptToEvaluateOnNewDocument(args: AddScriptToEvaluateOnNewDocumentParameter):
      AddScriptToEvaluateOnNewDocumentReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.addScriptToEvaluateOnNewDocument", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun addScriptToEvaluateOnNewDocument(source: String, worldName: String? = null):
      AddScriptToEvaluateOnNewDocumentReturn {
    val parameter = AddScriptToEvaluateOnNewDocumentParameter(source = source,worldName = worldName)
    return addScriptToEvaluateOnNewDocument(parameter)
  }

  /**
   * Brings page to front (activates tab).
   */
  @ExperimentalCoroutinesApi
  public suspend fun bringToFront(): Unit {
    val parameter = null
    client.callCommand("Page.bringToFront", parameter)
  }

  /**
   * Capture page screenshot.
   */
  @ExperimentalCoroutinesApi
  public suspend fun captureScreenshot(args: CaptureScreenshotParameter): CaptureScreenshotReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.captureScreenshot", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun captureScreenshot(
    format: String? = null,
    quality: Int? = null,
    clip: Viewport? = null,
    fromSurface: Boolean? = null,
    captureBeyondViewport: Boolean? = null
  ): CaptureScreenshotReturn {
    val parameter = CaptureScreenshotParameter(format = format,quality = quality,clip =
        clip,fromSurface = fromSurface,captureBeyondViewport = captureBeyondViewport)
    return captureScreenshot(parameter)
  }

  /**
   * Returns a snapshot of the page as a string. For MHTML format, the serialization includes
   * iframes, shadow DOM, external resources, and element-inline styles.
   */
  @ExperimentalCoroutinesApi
  public suspend fun captureSnapshot(args: CaptureSnapshotParameter): CaptureSnapshotReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.captureSnapshot", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun captureSnapshot(format: String? = null): CaptureSnapshotReturn {
    val parameter = CaptureSnapshotParameter(format = format)
    return captureSnapshot(parameter)
  }

  /**
   * Clears the overriden device metrics.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun clearDeviceMetricsOverride(): Unit {
    val parameter = null
    client.callCommand("Page.clearDeviceMetricsOverride", parameter)
  }

  /**
   * Clears the overridden Device Orientation.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun clearDeviceOrientationOverride(): Unit {
    val parameter = null
    client.callCommand("Page.clearDeviceOrientationOverride", parameter)
  }

  /**
   * Clears the overriden Geolocation Position and Error.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun clearGeolocationOverride(): Unit {
    val parameter = null
    client.callCommand("Page.clearGeolocationOverride", parameter)
  }

  /**
   * Creates an isolated world for the given frame.
   */
  @ExperimentalCoroutinesApi
  public suspend fun createIsolatedWorld(args: CreateIsolatedWorldParameter):
      CreateIsolatedWorldReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.createIsolatedWorld", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun createIsolatedWorld(
    frameId: String,
    worldName: String? = null,
    grantUniveralAccess: Boolean? = null
  ): CreateIsolatedWorldReturn {
    val parameter = CreateIsolatedWorldParameter(frameId = frameId,worldName =
        worldName,grantUniveralAccess = grantUniveralAccess)
    return createIsolatedWorld(parameter)
  }

  /**
   * Deletes browser cookie with given name, domain and path.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun deleteCookie(args: DeleteCookieParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.deleteCookie", parameter)
  }

  public suspend fun deleteCookie(cookieName: String, url: String): Unit {
    val parameter = DeleteCookieParameter(cookieName = cookieName,url = url)
    deleteCookie(parameter)
  }

  /**
   * Disables page domain notifications.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Page.disable", parameter)
  }

  /**
   * Enables page domain notifications.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Page.enable", parameter)
  }

  @ExperimentalCoroutinesApi
  public suspend fun getAppManifest(): GetAppManifestReturn {
    val parameter = null
    val result = client.callCommand("Page.getAppManifest", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  public suspend fun getInstallabilityErrors(): GetInstallabilityErrorsReturn {
    val parameter = null
    val result = client.callCommand("Page.getInstallabilityErrors", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  public suspend fun getManifestIcons(): GetManifestIconsReturn {
    val parameter = null
    val result = client.callCommand("Page.getManifestIcons", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns all browser cookies. Depending on the backend support, will return detailed cookie
   * information in the `cookies` field.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun getCookies(): GetCookiesReturn {
    val parameter = null
    val result = client.callCommand("Page.getCookies", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns present frame tree structure.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getFrameTree(): GetFrameTreeReturn {
    val parameter = null
    val result = client.callCommand("Page.getFrameTree", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getLayoutMetrics(): GetLayoutMetricsReturn {
    val parameter = null
    val result = client.callCommand("Page.getLayoutMetrics", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns navigation history for the current page.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getNavigationHistory(): GetNavigationHistoryReturn {
    val parameter = null
    val result = client.callCommand("Page.getNavigationHistory", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Resets navigation history for the current page.
   */
  @ExperimentalCoroutinesApi
  public suspend fun resetNavigationHistory(): Unit {
    val parameter = null
    client.callCommand("Page.resetNavigationHistory", parameter)
  }

  /**
   * Returns content of the given resource.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getResourceContent(args: GetResourceContentParameter):
      GetResourceContentReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.getResourceContent", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getResourceContent(frameId: String, url: String): GetResourceContentReturn {
    val parameter = GetResourceContentParameter(frameId = frameId,url = url)
    return getResourceContent(parameter)
  }

  /**
   * Returns present frame / resource tree structure.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getResourceTree(): GetResourceTreeReturn {
    val parameter = null
    val result = client.callCommand("Page.getResourceTree", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
   */
  @ExperimentalCoroutinesApi
  public suspend fun handleJavaScriptDialog(args: HandleJavaScriptDialogParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.handleJavaScriptDialog", parameter)
  }

  public suspend fun handleJavaScriptDialog(accept: Boolean, promptText: String? = null): Unit {
    val parameter = HandleJavaScriptDialogParameter(accept = accept,promptText = promptText)
    handleJavaScriptDialog(parameter)
  }

  /**
   * Navigates current page to the given URL.
   */
  @ExperimentalCoroutinesApi
  public suspend fun navigate(args: NavigateParameter): NavigateReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.navigate", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun navigate(
    url: String,
    referrer: String? = null,
    transitionType: TransitionType? = null,
    frameId: String? = null,
    referrerPolicy: ReferrerPolicy? = null
  ): NavigateReturn {
    val parameter = NavigateParameter(url = url,referrer = referrer,transitionType =
        transitionType,frameId = frameId,referrerPolicy = referrerPolicy)
    return navigate(parameter)
  }

  /**
   * Navigates current page to the given history entry.
   */
  @ExperimentalCoroutinesApi
  public suspend fun navigateToHistoryEntry(args: NavigateToHistoryEntryParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.navigateToHistoryEntry", parameter)
  }

  public suspend fun navigateToHistoryEntry(entryId: Int): Unit {
    val parameter = NavigateToHistoryEntryParameter(entryId = entryId)
    navigateToHistoryEntry(parameter)
  }

  /**
   * Print page as PDF.
   */
  @ExperimentalCoroutinesApi
  public suspend fun printToPDF(args: PrintToPDFParameter): PrintToPDFReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.printToPDF", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun printToPDF(
    landscape: Boolean? = null,
    displayHeaderFooter: Boolean? = null,
    printBackground: Boolean? = null,
    scale: Double? = null,
    paperWidth: Double? = null,
    paperHeight: Double? = null,
    marginTop: Double? = null,
    marginBottom: Double? = null,
    marginLeft: Double? = null,
    marginRight: Double? = null,
    pageRanges: String? = null,
    ignoreInvalidPageRanges: Boolean? = null,
    headerTemplate: String? = null,
    footerTemplate: String? = null,
    preferCSSPageSize: Boolean? = null,
    transferMode: String? = null
  ): PrintToPDFReturn {
    val parameter = PrintToPDFParameter(landscape = landscape,displayHeaderFooter =
        displayHeaderFooter,printBackground = printBackground,scale = scale,paperWidth =
        paperWidth,paperHeight = paperHeight,marginTop = marginTop,marginBottom =
        marginBottom,marginLeft = marginLeft,marginRight = marginRight,pageRanges =
        pageRanges,ignoreInvalidPageRanges = ignoreInvalidPageRanges,headerTemplate =
        headerTemplate,footerTemplate = footerTemplate,preferCSSPageSize =
        preferCSSPageSize,transferMode = transferMode)
    return printToPDF(parameter)
  }

  /**
   * Reloads given page optionally ignoring the cache.
   */
  @ExperimentalCoroutinesApi
  public suspend fun reload(args: ReloadParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.reload", parameter)
  }

  public suspend fun reload(ignoreCache: Boolean? = null, scriptToEvaluateOnLoad: String? = null):
      Unit {
    val parameter = ReloadParameter(ignoreCache = ignoreCache,scriptToEvaluateOnLoad =
        scriptToEvaluateOnLoad)
    reload(parameter)
  }

  /**
   * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun removeScriptToEvaluateOnLoad(args: RemoveScriptToEvaluateOnLoadParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.removeScriptToEvaluateOnLoad", parameter)
  }

  public suspend fun removeScriptToEvaluateOnLoad(identifier: String): Unit {
    val parameter = RemoveScriptToEvaluateOnLoadParameter(identifier = identifier)
    removeScriptToEvaluateOnLoad(parameter)
  }

  /**
   * Removes given script from the list.
   */
  @ExperimentalCoroutinesApi
  public suspend
      fun removeScriptToEvaluateOnNewDocument(args: RemoveScriptToEvaluateOnNewDocumentParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.removeScriptToEvaluateOnNewDocument", parameter)
  }

  public suspend fun removeScriptToEvaluateOnNewDocument(identifier: String): Unit {
    val parameter = RemoveScriptToEvaluateOnNewDocumentParameter(identifier = identifier)
    removeScriptToEvaluateOnNewDocument(parameter)
  }

  /**
   * Acknowledges that a screencast frame has been received by the frontend.
   */
  @ExperimentalCoroutinesApi
  public suspend fun screencastFrameAck(args: ScreencastFrameAckParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.screencastFrameAck", parameter)
  }

  public suspend fun screencastFrameAck(sessionId: Int): Unit {
    val parameter = ScreencastFrameAckParameter(sessionId = sessionId)
    screencastFrameAck(parameter)
  }

  /**
   * Searches for given string in resource content.
   */
  @ExperimentalCoroutinesApi
  public suspend fun searchInResource(args: SearchInResourceParameter): SearchInResourceReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Page.searchInResource", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun searchInResource(
    frameId: String,
    url: String,
    query: String,
    caseSensitive: Boolean? = null,
    isRegex: Boolean? = null
  ): SearchInResourceReturn {
    val parameter = SearchInResourceParameter(frameId = frameId,url = url,query =
        query,caseSensitive = caseSensitive,isRegex = isRegex)
    return searchInResource(parameter)
  }

  /**
   * Enable Chrome's experimental ad filter on all sites.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setAdBlockingEnabled(args: SetAdBlockingEnabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setAdBlockingEnabled", parameter)
  }

  public suspend fun setAdBlockingEnabled(enabled: Boolean): Unit {
    val parameter = SetAdBlockingEnabledParameter(enabled = enabled)
    setAdBlockingEnabled(parameter)
  }

  /**
   * Enable page Content Security Policy by-passing.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setBypassCSP(args: SetBypassCSPParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setBypassCSP", parameter)
  }

  public suspend fun setBypassCSP(enabled: Boolean): Unit {
    val parameter = SetBypassCSPParameter(enabled = enabled)
    setBypassCSP(parameter)
  }

  /**
   * Overrides the values of device screen dimensions (window.screen.width, window.screen.height,
   * window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media
   * query results).
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setDeviceMetricsOverride(args: SetDeviceMetricsOverrideParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setDeviceMetricsOverride", parameter)
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
    screenOrientation: Emulation.ScreenOrientation? = null,
    viewport: Viewport? = null
  ): Unit {
    val parameter = SetDeviceMetricsOverrideParameter(width = width,height =
        height,deviceScaleFactor = deviceScaleFactor,mobile = mobile,scale = scale,screenWidth =
        screenWidth,screenHeight = screenHeight,positionX = positionX,positionY =
        positionY,dontSetVisibleSize = dontSetVisibleSize,screenOrientation =
        screenOrientation,viewport = viewport)
    setDeviceMetricsOverride(parameter)
  }

  /**
   * Overrides the Device Orientation.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setDeviceOrientationOverride(args: SetDeviceOrientationOverrideParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setDeviceOrientationOverride", parameter)
  }

  public suspend fun setDeviceOrientationOverride(
    alpha: Double,
    beta: Double,
    gamma: Double
  ): Unit {
    val parameter = SetDeviceOrientationOverrideParameter(alpha = alpha,beta = beta,gamma = gamma)
    setDeviceOrientationOverride(parameter)
  }

  /**
   * Set generic font families.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setFontFamilies(args: SetFontFamiliesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setFontFamilies", parameter)
  }

  public suspend fun setFontFamilies(fontFamilies: FontFamilies): Unit {
    val parameter = SetFontFamiliesParameter(fontFamilies = fontFamilies)
    setFontFamilies(parameter)
  }

  /**
   * Set default font sizes.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setFontSizes(args: SetFontSizesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setFontSizes", parameter)
  }

  public suspend fun setFontSizes(fontSizes: FontSizes): Unit {
    val parameter = SetFontSizesParameter(fontSizes = fontSizes)
    setFontSizes(parameter)
  }

  /**
   * Sets given markup as the document's HTML.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setDocumentContent(args: SetDocumentContentParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setDocumentContent", parameter)
  }

  public suspend fun setDocumentContent(frameId: String, html: String): Unit {
    val parameter = SetDocumentContentParameter(frameId = frameId,html = html)
    setDocumentContent(parameter)
  }

  /**
   * Set the behavior when downloading a file.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setDownloadBehavior(args: SetDownloadBehaviorParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setDownloadBehavior", parameter)
  }

  public suspend fun setDownloadBehavior(behavior: String, downloadPath: String? = null): Unit {
    val parameter = SetDownloadBehaviorParameter(behavior = behavior,downloadPath = downloadPath)
    setDownloadBehavior(parameter)
  }

  /**
   * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position
   * unavailable.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setGeolocationOverride(args: SetGeolocationOverrideParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setGeolocationOverride", parameter)
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
   * Controls whether page will emit lifecycle events.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setLifecycleEventsEnabled(args: SetLifecycleEventsEnabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setLifecycleEventsEnabled", parameter)
  }

  public suspend fun setLifecycleEventsEnabled(enabled: Boolean): Unit {
    val parameter = SetLifecycleEventsEnabledParameter(enabled = enabled)
    setLifecycleEventsEnabled(parameter)
  }

  /**
   * Toggles mouse event-based touch event emulation.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setTouchEmulationEnabled(args: SetTouchEmulationEnabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setTouchEmulationEnabled", parameter)
  }

  public suspend fun setTouchEmulationEnabled(enabled: Boolean, configuration: String? = null):
      Unit {
    val parameter = SetTouchEmulationEnabledParameter(enabled = enabled,configuration =
        configuration)
    setTouchEmulationEnabled(parameter)
  }

  /**
   * Starts sending each frame using the `screencastFrame` event.
   */
  @ExperimentalCoroutinesApi
  public suspend fun startScreencast(args: StartScreencastParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.startScreencast", parameter)
  }

  public suspend fun startScreencast(
    format: String? = null,
    quality: Int? = null,
    maxWidth: Int? = null,
    maxHeight: Int? = null,
    everyNthFrame: Int? = null
  ): Unit {
    val parameter = StartScreencastParameter(format = format,quality = quality,maxWidth =
        maxWidth,maxHeight = maxHeight,everyNthFrame = everyNthFrame)
    startScreencast(parameter)
  }

  /**
   * Force the page stop all navigations and pending resource fetches.
   */
  @ExperimentalCoroutinesApi
  public suspend fun stopLoading(): Unit {
    val parameter = null
    client.callCommand("Page.stopLoading", parameter)
  }

  /**
   * Crashes renderer on the IO thread, generates minidumps.
   */
  @ExperimentalCoroutinesApi
  public suspend fun crash(): Unit {
    val parameter = null
    client.callCommand("Page.crash", parameter)
  }

  /**
   * Tries to close page, running its beforeunload hooks, if any.
   */
  @ExperimentalCoroutinesApi
  public suspend fun close(): Unit {
    val parameter = null
    client.callCommand("Page.close", parameter)
  }

  /**
   * Tries to update the web lifecycle state of the page.
   * It will transition the page to the given state according to:
   * https://github.com/WICG/web-lifecycle/
   */
  @ExperimentalCoroutinesApi
  public suspend fun setWebLifecycleState(args: SetWebLifecycleStateParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setWebLifecycleState", parameter)
  }

  public suspend fun setWebLifecycleState(state: String): Unit {
    val parameter = SetWebLifecycleStateParameter(state = state)
    setWebLifecycleState(parameter)
  }

  /**
   * Stops sending each frame in the `screencastFrame`.
   */
  @ExperimentalCoroutinesApi
  public suspend fun stopScreencast(): Unit {
    val parameter = null
    client.callCommand("Page.stopScreencast", parameter)
  }

  /**
   * Forces compilation cache to be generated for every subresource script.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setProduceCompilationCache(args: SetProduceCompilationCacheParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setProduceCompilationCache", parameter)
  }

  public suspend fun setProduceCompilationCache(enabled: Boolean): Unit {
    val parameter = SetProduceCompilationCacheParameter(enabled = enabled)
    setProduceCompilationCache(parameter)
  }

  /**
   * Seeds compilation cache for given url. Compilation cache does not survive
   * cross-process navigation.
   */
  @ExperimentalCoroutinesApi
  public suspend fun addCompilationCache(args: AddCompilationCacheParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.addCompilationCache", parameter)
  }

  public suspend fun addCompilationCache(url: String, `data`: String): Unit {
    val parameter = AddCompilationCacheParameter(url = url,data = data)
    addCompilationCache(parameter)
  }

  /**
   * Clears seeded compilation cache.
   */
  @ExperimentalCoroutinesApi
  public suspend fun clearCompilationCache(): Unit {
    val parameter = null
    client.callCommand("Page.clearCompilationCache", parameter)
  }

  /**
   * Generates a report for testing.
   */
  @ExperimentalCoroutinesApi
  public suspend fun generateTestReport(args: GenerateTestReportParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.generateTestReport", parameter)
  }

  public suspend fun generateTestReport(message: String, group: String? = null): Unit {
    val parameter = GenerateTestReportParameter(message = message,group = group)
    generateTestReport(parameter)
  }

  /**
   * Pauses page execution. Can be resumed using generic Runtime.runIfWaitingForDebugger.
   */
  @ExperimentalCoroutinesApi
  public suspend fun waitForDebugger(): Unit {
    val parameter = null
    client.callCommand("Page.waitForDebugger", parameter)
  }

  /**
   * Intercept file chooser requests and transfer control to protocol clients.
   * When file chooser interception is enabled, native file chooser dialog is not shown.
   * Instead, a protocol event `Page.fileChooserOpened` is emitted.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setInterceptFileChooserDialog(args: SetInterceptFileChooserDialogParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Page.setInterceptFileChooserDialog", parameter)
  }

  public suspend fun setInterceptFileChooserDialog(enabled: Boolean): Unit {
    val parameter = SetInterceptFileChooserDialogParameter(enabled = enabled)
    setInterceptFileChooserDialog(parameter)
  }

  /**
   * Indicates whether a frame has been identified as an ad.
   */
  @Serializable
  public enum class AdFrameType {
    @SerialName("none")
    NONE,
    @SerialName("child")
    CHILD,
    @SerialName("root")
    ROOT,
  }

  /**
   * Indicates whether the frame is a secure context and why it is the case.
   */
  @Serializable
  public enum class SecureContextType {
    @SerialName("Secure")
    SECURE,
    @SerialName("SecureLocalhost")
    SECURELOCALHOST,
    @SerialName("InsecureScheme")
    INSECURESCHEME,
    @SerialName("InsecureAncestor")
    INSECUREANCESTOR,
  }

  /**
   * Indicates whether the frame is cross-origin isolated and why it is the case.
   */
  @Serializable
  public enum class CrossOriginIsolatedContextType {
    @SerialName("Isolated")
    ISOLATED,
    @SerialName("NotIsolated")
    NOTISOLATED,
    @SerialName("NotIsolatedFeatureDisabled")
    NOTISOLATEDFEATUREDISABLED,
  }

  @Serializable
  public enum class GatedAPIFeatures {
    @SerialName("SharedArrayBuffers")
    SHAREDARRAYBUFFERS,
    @SerialName("SharedArrayBuffersTransferAllowed")
    SHAREDARRAYBUFFERSTRANSFERALLOWED,
    @SerialName("PerformanceMeasureMemory")
    PERFORMANCEMEASUREMEMORY,
    @SerialName("PerformanceProfile")
    PERFORMANCEPROFILE,
  }

  /**
   * Information about the Frame on the page.
   */
  @Serializable
  public data class Frame(
    /**
     * Frame unique identifier.
     */
    public val id: String,
    /**
     * Parent frame identifier.
     */
    public val parentId: String? = null,
    /**
     * Identifier of the loader associated with this frame.
     */
    public val loaderId: String,
    /**
     * Frame's name as specified in the tag.
     */
    public val name: String? = null,
    /**
     * Frame document's URL without fragment.
     */
    public val url: String,
    /**
     * Frame document's URL fragment including the '#'.
     */
    public val urlFragment: String? = null,
    /**
     * Frame document's registered domain, taking the public suffixes list into account.
     * Extracted from the Frame's url.
     * Example URLs: http://www.google.com/file.html -> "google.com"
     *               http://a.b.co.uk/file.html      -> "b.co.uk"
     */
    public val domainAndRegistry: String,
    /**
     * Frame document's security origin.
     */
    public val securityOrigin: String,
    /**
     * Frame document's mimeType as determined by the browser.
     */
    public val mimeType: String,
    /**
     * If the frame failed to load, this contains the URL that could not be loaded. Note that unlike
     * url above, this URL may contain a fragment.
     */
    public val unreachableUrl: String? = null,
    /**
     * Indicates whether this frame was tagged as an ad.
     */
    public val adFrameType: AdFrameType? = null,
    /**
     * Indicates whether the main document is a secure context and explains why that is the case.
     */
    public val secureContextType: SecureContextType,
    /**
     * Indicates whether this is a cross origin isolated context.
     */
    public val crossOriginIsolatedContextType: CrossOriginIsolatedContextType,
    /**
     * Indicated which gated APIs / features are available.
     */
    public val gatedAPIFeatures: List<GatedAPIFeatures>
  )

  /**
   * Information about the Resource on the page.
   */
  @Serializable
  public data class FrameResource(
    /**
     * Resource URL.
     */
    public val url: String,
    /**
     * Type of this resource.
     */
    public val type: Network.ResourceType,
    /**
     * Resource mimeType as determined by the browser.
     */
    public val mimeType: String,
    /**
     * last-modified timestamp as reported by server.
     */
    public val lastModified: Double? = null,
    /**
     * Resource content size.
     */
    public val contentSize: Double? = null,
    /**
     * True if the resource failed to load.
     */
    public val failed: Boolean? = null,
    /**
     * True if the resource was canceled during loading.
     */
    public val canceled: Boolean? = null
  )

  /**
   * Information about the Frame hierarchy along with their cached resources.
   */
  @Serializable
  public data class FrameResourceTree(
    /**
     * Frame information for this tree item.
     */
    public val frame: Frame,
    /**
     * Child frames.
     */
    public val childFrames: List<FrameResourceTree>? = null,
    /**
     * Information about frame resources.
     */
    public val resources: List<FrameResource>
  )

  /**
   * Information about the Frame hierarchy.
   */
  @Serializable
  public data class FrameTree(
    /**
     * Frame information for this tree item.
     */
    public val frame: Frame,
    /**
     * Child frames.
     */
    public val childFrames: List<FrameTree>? = null
  )

  /**
   * Transition type.
   */
  @Serializable
  public enum class TransitionType {
    @SerialName("link")
    LINK,
    @SerialName("typed")
    TYPED,
    @SerialName("address_bar")
    ADDRESS_BAR,
    @SerialName("auto_bookmark")
    AUTO_BOOKMARK,
    @SerialName("auto_subframe")
    AUTO_SUBFRAME,
    @SerialName("manual_subframe")
    MANUAL_SUBFRAME,
    @SerialName("generated")
    GENERATED,
    @SerialName("auto_toplevel")
    AUTO_TOPLEVEL,
    @SerialName("form_submit")
    FORM_SUBMIT,
    @SerialName("reload")
    RELOAD,
    @SerialName("keyword")
    KEYWORD,
    @SerialName("keyword_generated")
    KEYWORD_GENERATED,
    @SerialName("other")
    OTHER,
  }

  /**
   * Navigation history entry.
   */
  @Serializable
  public data class NavigationEntry(
    /**
     * Unique id of the navigation history entry.
     */
    public val id: Int,
    /**
     * URL of the navigation history entry.
     */
    public val url: String,
    /**
     * URL that the user typed in the url bar.
     */
    public val userTypedURL: String,
    /**
     * Title of the navigation history entry.
     */
    public val title: String,
    /**
     * Transition type.
     */
    public val transitionType: TransitionType
  )

  /**
   * Screencast frame metadata.
   */
  @Serializable
  public data class ScreencastFrameMetadata(
    /**
     * Top offset in DIP.
     */
    public val offsetTop: Double,
    /**
     * Page scale factor.
     */
    public val pageScaleFactor: Double,
    /**
     * Device screen width in DIP.
     */
    public val deviceWidth: Double,
    /**
     * Device screen height in DIP.
     */
    public val deviceHeight: Double,
    /**
     * Position of horizontal scroll in CSS pixels.
     */
    public val scrollOffsetX: Double,
    /**
     * Position of vertical scroll in CSS pixels.
     */
    public val scrollOffsetY: Double,
    /**
     * Frame swap timestamp.
     */
    public val timestamp: Double? = null
  )

  /**
   * Javascript dialog type.
   */
  @Serializable
  public enum class DialogType {
    @SerialName("alert")
    ALERT,
    @SerialName("confirm")
    CONFIRM,
    @SerialName("prompt")
    PROMPT,
    @SerialName("beforeunload")
    BEFOREUNLOAD,
  }

  /**
   * Error while paring app manifest.
   */
  @Serializable
  public data class AppManifestError(
    /**
     * Error message.
     */
    public val message: String,
    /**
     * If criticial, this is a non-recoverable parse error.
     */
    public val critical: Int,
    /**
     * Error line.
     */
    public val line: Int,
    /**
     * Error column.
     */
    public val column: Int
  )

  /**
   * Parsed app manifest properties.
   */
  @Serializable
  public data class AppManifestParsedProperties(
    /**
     * Computed scope value
     */
    public val scope: String
  )

  /**
   * Layout viewport position and dimensions.
   */
  @Serializable
  public data class LayoutViewport(
    /**
     * Horizontal offset relative to the document (CSS pixels).
     */
    public val pageX: Int,
    /**
     * Vertical offset relative to the document (CSS pixels).
     */
    public val pageY: Int,
    /**
     * Width (CSS pixels), excludes scrollbar if present.
     */
    public val clientWidth: Int,
    /**
     * Height (CSS pixels), excludes scrollbar if present.
     */
    public val clientHeight: Int
  )

  /**
   * Visual viewport position, dimensions, and scale.
   */
  @Serializable
  public data class VisualViewport(
    /**
     * Horizontal offset relative to the layout viewport (CSS pixels).
     */
    public val offsetX: Double,
    /**
     * Vertical offset relative to the layout viewport (CSS pixels).
     */
    public val offsetY: Double,
    /**
     * Horizontal offset relative to the document (CSS pixels).
     */
    public val pageX: Double,
    /**
     * Vertical offset relative to the document (CSS pixels).
     */
    public val pageY: Double,
    /**
     * Width (CSS pixels), excludes scrollbar if present.
     */
    public val clientWidth: Double,
    /**
     * Height (CSS pixels), excludes scrollbar if present.
     */
    public val clientHeight: Double,
    /**
     * Scale relative to the ideal viewport (size at width=device-width).
     */
    public val scale: Double,
    /**
     * Page zoom factor (CSS to device independent pixels ratio).
     */
    public val zoom: Double? = null
  )

  /**
   * Viewport for capturing screenshot.
   */
  @Serializable
  public data class Viewport(
    /**
     * X offset in device independent pixels (dip).
     */
    public val x: Double,
    /**
     * Y offset in device independent pixels (dip).
     */
    public val y: Double,
    /**
     * Rectangle width in device independent pixels (dip).
     */
    public val width: Double,
    /**
     * Rectangle height in device independent pixels (dip).
     */
    public val height: Double,
    /**
     * Page scale factor.
     */
    public val scale: Double
  )

  /**
   * Generic font families collection.
   */
  @Serializable
  public data class FontFamilies(
    /**
     * The standard font-family.
     */
    public val standard: String? = null,
    /**
     * The fixed font-family.
     */
    public val fixed: String? = null,
    /**
     * The serif font-family.
     */
    public val serif: String? = null,
    /**
     * The sansSerif font-family.
     */
    public val sansSerif: String? = null,
    /**
     * The cursive font-family.
     */
    public val cursive: String? = null,
    /**
     * The fantasy font-family.
     */
    public val fantasy: String? = null,
    /**
     * The pictograph font-family.
     */
    public val pictograph: String? = null
  )

  /**
   * Default font sizes.
   */
  @Serializable
  public data class FontSizes(
    /**
     * Default standard font size.
     */
    public val standard: Int? = null,
    /**
     * Default fixed font size.
     */
    public val fixed: Int? = null
  )

  @Serializable
  public enum class ClientNavigationReason {
    @SerialName("formSubmissionGet")
    FORMSUBMISSIONGET,
    @SerialName("formSubmissionPost")
    FORMSUBMISSIONPOST,
    @SerialName("httpHeaderRefresh")
    HTTPHEADERREFRESH,
    @SerialName("scriptInitiated")
    SCRIPTINITIATED,
    @SerialName("metaTagRefresh")
    METATAGREFRESH,
    @SerialName("pageBlockInterstitial")
    PAGEBLOCKINTERSTITIAL,
    @SerialName("reload")
    RELOAD,
    @SerialName("anchorClick")
    ANCHORCLICK,
  }

  @Serializable
  public enum class ClientNavigationDisposition {
    @SerialName("currentTab")
    CURRENTTAB,
    @SerialName("newTab")
    NEWTAB,
    @SerialName("newWindow")
    NEWWINDOW,
    @SerialName("download")
    DOWNLOAD,
  }

  @Serializable
  public data class InstallabilityErrorArgument(
    /**
     * Argument name (e.g. name:'minimum-icon-size-in-pixels').
     */
    public val name: String,
    /**
     * Argument value (e.g. value:'64').
     */
    public val value: String
  )

  /**
   * The installability error
   */
  @Serializable
  public data class InstallabilityError(
    /**
     * The error id (e.g. 'manifest-missing-suitable-icon').
     */
    public val errorId: String,
    /**
     * The list of error arguments (e.g. {name:'minimum-icon-size-in-pixels', value:'64'}).
     */
    public val errorArguments: List<InstallabilityErrorArgument>
  )

  /**
   * The referring-policy used for the navigation.
   */
  @Serializable
  public enum class ReferrerPolicy {
    @SerialName("noReferrer")
    NOREFERRER,
    @SerialName("noReferrerWhenDowngrade")
    NOREFERRERWHENDOWNGRADE,
    @SerialName("origin")
    ORIGIN,
    @SerialName("originWhenCrossOrigin")
    ORIGINWHENCROSSORIGIN,
    @SerialName("sameOrigin")
    SAMEORIGIN,
    @SerialName("strictOrigin")
    STRICTORIGIN,
    @SerialName("strictOriginWhenCrossOrigin")
    STRICTORIGINWHENCROSSORIGIN,
    @SerialName("unsafeUrl")
    UNSAFEURL,
  }

  public data class DomContentEventFiredParameter(
    public val timestamp: Double
  )

  /**
   * Emitted only when `page.interceptFileChooser` is enabled.
   */
  public data class FileChooserOpenedParameter(
    /**
     * Id of the frame containing input node.
     */
    public val frameId: String,
    /**
     * Input node id.
     */
    public val backendNodeId: Int,
    /**
     * Input mode.
     */
    public val mode: String
  )

  /**
   * Fired when frame has been attached to its parent.
   */
  public data class FrameAttachedParameter(
    /**
     * Id of the frame that has been attached.
     */
    public val frameId: String,
    /**
     * Parent frame identifier.
     */
    public val parentFrameId: String,
    /**
     * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
     */
    public val stack: Runtime.StackTrace? = null
  )

  /**
   * Fired when frame no longer has a scheduled navigation.
   */
  public data class FrameClearedScheduledNavigationParameter(
    /**
     * Id of the frame that has cleared its scheduled navigation.
     */
    public val frameId: String
  )

  /**
   * Fired when frame has been detached from its parent.
   */
  public data class FrameDetachedParameter(
    /**
     * Id of the frame that has been detached.
     */
    public val frameId: String,
    public val reason: String
  )

  /**
   * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
   */
  public data class FrameNavigatedParameter(
    /**
     * Frame object.
     */
    public val frame: Frame
  )

  /**
   * Fired when opening document to write to.
   */
  public data class DocumentOpenedParameter(
    /**
     * Frame object.
     */
    public val frame: Frame
  )

  /**
   * Fired when a renderer-initiated navigation is requested.
   * Navigation may still be cancelled after the event is issued.
   */
  public data class FrameRequestedNavigationParameter(
    /**
     * Id of the frame that is being navigated.
     */
    public val frameId: String,
    /**
     * The reason for the navigation.
     */
    public val reason: ClientNavigationReason,
    /**
     * The destination URL for the requested navigation.
     */
    public val url: String,
    /**
     * The disposition for the navigation.
     */
    public val disposition: ClientNavigationDisposition
  )

  /**
   * Fired when frame schedules a potential navigation.
   */
  public data class FrameScheduledNavigationParameter(
    /**
     * Id of the frame that has scheduled a navigation.
     */
    public val frameId: String,
    /**
     * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not
     * guaranteed to start.
     */
    public val delay: Double,
    /**
     * The reason for the navigation.
     */
    public val reason: ClientNavigationReason,
    /**
     * The destination URL for the scheduled navigation.
     */
    public val url: String
  )

  /**
   * Fired when frame has started loading.
   */
  public data class FrameStartedLoadingParameter(
    /**
     * Id of the frame that has started loading.
     */
    public val frameId: String
  )

  /**
   * Fired when frame has stopped loading.
   */
  public data class FrameStoppedLoadingParameter(
    /**
     * Id of the frame that has stopped loading.
     */
    public val frameId: String
  )

  /**
   * Fired when page is about to start a download.
   */
  public data class DownloadWillBeginParameter(
    /**
     * Id of the frame that caused download to begin.
     */
    public val frameId: String,
    /**
     * Global unique identifier of the download.
     */
    public val guid: String,
    /**
     * URL of the resource being downloaded.
     */
    public val url: String,
    /**
     * Suggested file name of the resource (the actual name of the file saved on disk may differ).
     */
    public val suggestedFilename: String
  )

  /**
   * Fired when download makes progress. Last call has |done| == true.
   */
  public data class DownloadProgressParameter(
    /**
     * Global unique identifier of the download.
     */
    public val guid: String,
    /**
     * Total expected bytes to download.
     */
    public val totalBytes: Double,
    /**
     * Total bytes received.
     */
    public val receivedBytes: Double,
    /**
     * Download status.
     */
    public val state: String
  )

  /**
   * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been
   * closed.
   */
  public data class JavascriptDialogClosedParameter(
    /**
     * Whether dialog was confirmed.
     */
    public val result: Boolean,
    /**
     * User input in case of prompt.
     */
    public val userInput: String
  )

  /**
   * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about
   * to
   * open.
   */
  public data class JavascriptDialogOpeningParameter(
    /**
     * Frame url.
     */
    public val url: String,
    /**
     * Message that will be displayed by the dialog.
     */
    public val message: String,
    /**
     * Dialog type.
     */
    public val type: DialogType,
    /**
     * True iff browser is capable showing or acting on the given dialog. When browser has no
     * dialog handler for given target, calling alert while Page domain is engaged will stall
     * the page execution. Execution can be resumed via calling Page.handleJavaScriptDialog.
     */
    public val hasBrowserHandler: Boolean,
    /**
     * Default dialog prompt.
     */
    public val defaultPrompt: String? = null
  )

  /**
   * Fired for top level page lifecycle events such as navigation, load, paint, etc.
   */
  public data class LifecycleEventParameter(
    /**
     * Id of the frame.
     */
    public val frameId: String,
    /**
     * Loader identifier. Empty string if the request is fetched from worker.
     */
    public val loaderId: String,
    public val name: String,
    public val timestamp: Double
  )

  public data class LoadEventFiredParameter(
    public val timestamp: Double
  )

  /**
   * Fired when same-document navigation happens, e.g. due to history API usage or anchor
   * navigation.
   */
  public data class NavigatedWithinDocumentParameter(
    /**
     * Id of the frame.
     */
    public val frameId: String,
    /**
     * Frame's new url.
     */
    public val url: String
  )

  /**
   * Compressed image data requested by the `startScreencast`.
   */
  public data class ScreencastFrameParameter(
    /**
     * Base64-encoded compressed image. (Encoded as a base64 string when passed over JSON)
     */
    public val `data`: String,
    /**
     * Screencast frame metadata.
     */
    public val metadata: ScreencastFrameMetadata,
    /**
     * Frame number.
     */
    public val sessionId: Int
  )

  /**
   * Fired when the page with currently enabled screencast was shown or hidden `.
   */
  public data class ScreencastVisibilityChangedParameter(
    /**
     * True if the page is visible.
     */
    public val visible: Boolean
  )

  /**
   * Fired when a new window is going to be opened, via window.open(), link click, form submission,
   * etc.
   */
  public data class WindowOpenParameter(
    /**
     * The URL for the new window.
     */
    public val url: String,
    /**
     * Window name.
     */
    public val windowName: String,
    /**
     * An array of enabled window features.
     */
    public val windowFeatures: String,
    /**
     * Whether or not it was triggered by user gesture.
     */
    public val userGesture: Boolean
  )

  /**
   * Issued for every compilation cache generated. Is only available
   * if Page.setGenerateCompilationCache is enabled.
   */
  public data class CompilationCacheProducedParameter(
    public val url: String,
    /**
     * Base64-encoded data (Encoded as a base64 string when passed over JSON)
     */
    public val `data`: String
  )

  @Serializable
  public data class AddScriptToEvaluateOnLoadParameter(
    public val scriptSource: String
  )

  @Serializable
  public data class AddScriptToEvaluateOnLoadReturn(
    /**
     * Identifier of the added script.
     */
    public val identifier: String
  )

  @Serializable
  public data class AddScriptToEvaluateOnNewDocumentParameter(
    public val source: String,
    /**
     * If specified, creates an isolated world with the given name and evaluates given script in it.
     * This world name will be used as the ExecutionContextDescription::name when the corresponding
     * event is emitted.
     */
    public val worldName: String? = null
  )

  @Serializable
  public data class AddScriptToEvaluateOnNewDocumentReturn(
    /**
     * Identifier of the added script.
     */
    public val identifier: String
  )

  @Serializable
  public data class CaptureScreenshotParameter(
    /**
     * Image compression format (defaults to png).
     */
    public val format: String? = null,
    /**
     * Compression quality from range [0..100] (jpeg only).
     */
    public val quality: Int? = null,
    /**
     * Capture the screenshot of a given region only.
     */
    public val clip: Viewport? = null,
    /**
     * Capture the screenshot from the surface, rather than the view. Defaults to true.
     */
    public val fromSurface: Boolean? = null,
    /**
     * Capture the screenshot beyond the viewport. Defaults to false.
     */
    public val captureBeyondViewport: Boolean? = null
  )

  @Serializable
  public data class CaptureScreenshotReturn(
    /**
     * Base64-encoded image data. (Encoded as a base64 string when passed over JSON)
     */
    public val `data`: String
  )

  @Serializable
  public data class CaptureSnapshotParameter(
    /**
     * Format (defaults to mhtml).
     */
    public val format: String? = null
  )

  @Serializable
  public data class CaptureSnapshotReturn(
    /**
     * Serialized page data.
     */
    public val `data`: String
  )

  @Serializable
  public data class CreateIsolatedWorldParameter(
    /**
     * Id of the frame in which the isolated world should be created.
     */
    public val frameId: String,
    /**
     * An optional name which is reported in the Execution Context.
     */
    public val worldName: String? = null,
    /**
     * Whether or not universal access should be granted to the isolated world. This is a powerful
     * option, use with caution.
     */
    public val grantUniveralAccess: Boolean? = null
  )

  @Serializable
  public data class CreateIsolatedWorldReturn(
    /**
     * Execution context of the isolated world.
     */
    public val executionContextId: Int
  )

  @Serializable
  public data class DeleteCookieParameter(
    /**
     * Name of the cookie to remove.
     */
    public val cookieName: String,
    /**
     * URL to match cooke domain and path.
     */
    public val url: String
  )

  @Serializable
  public data class GetAppManifestReturn(
    /**
     * Manifest location.
     */
    public val url: String,
    public val errors: List<AppManifestError>,
    /**
     * Manifest content.
     */
    public val `data`: String?,
    /**
     * Parsed manifest properties
     */
    public val parsed: AppManifestParsedProperties?
  )

  @Serializable
  public data class GetInstallabilityErrorsReturn(
    public val installabilityErrors: List<InstallabilityError>
  )

  @Serializable
  public data class GetManifestIconsReturn(
    public val primaryIcon: String?
  )

  @Serializable
  public data class GetCookiesReturn(
    /**
     * Array of cookie objects.
     */
    public val cookies: List<Network.Cookie>
  )

  @Serializable
  public data class GetFrameTreeReturn(
    /**
     * Present frame tree structure.
     */
    public val frameTree: FrameTree
  )

  @Serializable
  public data class GetLayoutMetricsReturn(
    /**
     * Metrics relating to the layout viewport.
     */
    public val layoutViewport: LayoutViewport,
    /**
     * Metrics relating to the visual viewport.
     */
    public val visualViewport: VisualViewport,
    /**
     * Size of scrollable area.
     */
    public val contentSize: DOM.Rect
  )

  @Serializable
  public data class GetNavigationHistoryReturn(
    /**
     * Index of the current navigation history entry.
     */
    public val currentIndex: Int,
    /**
     * Array of navigation history entries.
     */
    public val entries: List<NavigationEntry>
  )

  @Serializable
  public data class GetResourceContentParameter(
    /**
     * Frame id to get resource for.
     */
    public val frameId: String,
    /**
     * URL of the resource to get content for.
     */
    public val url: String
  )

  @Serializable
  public data class GetResourceContentReturn(
    /**
     * Resource content.
     */
    public val content: String,
    /**
     * True, if content was served as base64.
     */
    public val base64Encoded: Boolean
  )

  @Serializable
  public data class GetResourceTreeReturn(
    /**
     * Present frame / resource tree structure.
     */
    public val frameTree: FrameResourceTree
  )

  @Serializable
  public data class HandleJavaScriptDialogParameter(
    /**
     * Whether to accept or dismiss the dialog.
     */
    public val accept: Boolean,
    /**
     * The text to enter into the dialog prompt before accepting. Used only if this is a prompt
     * dialog.
     */
    public val promptText: String? = null
  )

  @Serializable
  public data class NavigateParameter(
    /**
     * URL to navigate the page to.
     */
    public val url: String,
    /**
     * Referrer URL.
     */
    public val referrer: String? = null,
    /**
     * Intended transition type.
     */
    public val transitionType: TransitionType? = null,
    /**
     * Frame id to navigate, if not specified navigates the top frame.
     */
    public val frameId: String? = null,
    /**
     * Referrer-policy used for the navigation.
     */
    public val referrerPolicy: ReferrerPolicy? = null
  )

  @Serializable
  public data class NavigateReturn(
    /**
     * Frame id that has navigated (or failed to navigate)
     */
    public val frameId: String,
    /**
     * Loader identifier.
     */
    public val loaderId: String?,
    /**
     * User friendly error message, present if and only if navigation has failed.
     */
    public val errorText: String?
  )

  @Serializable
  public data class NavigateToHistoryEntryParameter(
    /**
     * Unique id of the entry to navigate to.
     */
    public val entryId: Int
  )

  @Serializable
  public data class PrintToPDFParameter(
    /**
     * Paper orientation. Defaults to false.
     */
    public val landscape: Boolean? = null,
    /**
     * Display header and footer. Defaults to false.
     */
    public val displayHeaderFooter: Boolean? = null,
    /**
     * Print background graphics. Defaults to false.
     */
    public val printBackground: Boolean? = null,
    /**
     * Scale of the webpage rendering. Defaults to 1.
     */
    public val scale: Double? = null,
    /**
     * Paper width in inches. Defaults to 8.5 inches.
     */
    public val paperWidth: Double? = null,
    /**
     * Paper height in inches. Defaults to 11 inches.
     */
    public val paperHeight: Double? = null,
    /**
     * Top margin in inches. Defaults to 1cm (~0.4 inches).
     */
    public val marginTop: Double? = null,
    /**
     * Bottom margin in inches. Defaults to 1cm (~0.4 inches).
     */
    public val marginBottom: Double? = null,
    /**
     * Left margin in inches. Defaults to 1cm (~0.4 inches).
     */
    public val marginLeft: Double? = null,
    /**
     * Right margin in inches. Defaults to 1cm (~0.4 inches).
     */
    public val marginRight: Double? = null,
    /**
     * Paper ranges to print, e.g., '1-5, 8, 11-13'. Defaults to the empty string, which means
     * print all pages.
     */
    public val pageRanges: String? = null,
    /**
     * Whether to silently ignore invalid but successfully parsed page ranges, such as '3-2'.
     * Defaults to false.
     */
    public val ignoreInvalidPageRanges: Boolean? = null,
    /**
     * HTML template for the print header. Should be valid HTML markup with following
     * classes used to inject printing values into them:
     * - `date`: formatted print date
     * - `title`: document title
     * - `url`: document location
     * - `pageNumber`: current page number
     * - `totalPages`: total pages in the document
     *
     * For example, `<span class=title></span>` would generate span containing the title.
     */
    public val headerTemplate: String? = null,
    /**
     * HTML template for the print footer. Should use the same format as the `headerTemplate`.
     */
    public val footerTemplate: String? = null,
    /**
     * Whether or not to prefer page size as defined by css. Defaults to false,
     * in which case the content will be scaled to fit the paper size.
     */
    public val preferCSSPageSize: Boolean? = null,
    /**
     * return as stream
     */
    public val transferMode: String? = null
  )

  @Serializable
  public data class PrintToPDFReturn(
    /**
     * Base64-encoded pdf data. Empty if |returnAsStream| is specified. (Encoded as a base64 string
     * when passed over JSON)
     */
    public val `data`: String,
    /**
     * A handle of the stream that holds resulting PDF data.
     */
    public val stream: String?
  )

  @Serializable
  public data class ReloadParameter(
    /**
     * If true, browser cache is ignored (as if the user pressed Shift+refresh).
     */
    public val ignoreCache: Boolean? = null,
    /**
     * If set, the script will be injected into all frames of the inspected page after reload.
     * Argument will be ignored if reloading dataURL origin.
     */
    public val scriptToEvaluateOnLoad: String? = null
  )

  @Serializable
  public data class RemoveScriptToEvaluateOnLoadParameter(
    public val identifier: String
  )

  @Serializable
  public data class RemoveScriptToEvaluateOnNewDocumentParameter(
    public val identifier: String
  )

  @Serializable
  public data class ScreencastFrameAckParameter(
    /**
     * Frame number.
     */
    public val sessionId: Int
  )

  @Serializable
  public data class SearchInResourceParameter(
    /**
     * Frame id for resource to search in.
     */
    public val frameId: String,
    /**
     * URL of the resource to search in.
     */
    public val url: String,
    /**
     * String to search for.
     */
    public val query: String,
    /**
     * If true, search is case sensitive.
     */
    public val caseSensitive: Boolean? = null,
    /**
     * If true, treats string parameter as regex.
     */
    public val isRegex: Boolean? = null
  )

  @Serializable
  public data class SearchInResourceReturn(
    /**
     * List of search matches.
     */
    public val result: List<Debugger.SearchMatch>
  )

  @Serializable
  public data class SetAdBlockingEnabledParameter(
    /**
     * Whether to block ads.
     */
    public val enabled: Boolean
  )

  @Serializable
  public data class SetBypassCSPParameter(
    /**
     * Whether to bypass page CSP.
     */
    public val enabled: Boolean
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
    public val screenOrientation: Emulation.ScreenOrientation? = null,
    /**
     * The viewport dimensions and scale. If not set, the override is cleared.
     */
    public val viewport: Viewport? = null
  )

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

  @Serializable
  public data class SetFontFamiliesParameter(
    /**
     * Specifies font families to set. If a font family is not specified, it won't be changed.
     */
    public val fontFamilies: FontFamilies
  )

  @Serializable
  public data class SetFontSizesParameter(
    /**
     * Specifies font sizes to set. If a font size is not specified, it won't be changed.
     */
    public val fontSizes: FontSizes
  )

  @Serializable
  public data class SetDocumentContentParameter(
    /**
     * Frame id to set HTML for.
     */
    public val frameId: String,
    /**
     * HTML content to set.
     */
    public val html: String
  )

  @Serializable
  public data class SetDownloadBehaviorParameter(
    /**
     * Whether to allow all or deny all download requests, or use default Chrome behavior if
     * available (otherwise deny).
     */
    public val behavior: String,
    /**
     * The default path to save downloaded files to. This is requred if behavior is set to 'allow'
     */
    public val downloadPath: String? = null
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
  public data class SetLifecycleEventsEnabledParameter(
    /**
     * If true, starts emitting lifecycle events.
     */
    public val enabled: Boolean
  )

  @Serializable
  public data class SetTouchEmulationEnabledParameter(
    /**
     * Whether the touch event emulation should be enabled.
     */
    public val enabled: Boolean,
    /**
     * Touch/gesture events configuration. Default: current platform.
     */
    public val configuration: String? = null
  )

  @Serializable
  public data class StartScreencastParameter(
    /**
     * Image compression format.
     */
    public val format: String? = null,
    /**
     * Compression quality from range [0..100].
     */
    public val quality: Int? = null,
    /**
     * Maximum screenshot width.
     */
    public val maxWidth: Int? = null,
    /**
     * Maximum screenshot height.
     */
    public val maxHeight: Int? = null,
    /**
     * Send every n-th frame.
     */
    public val everyNthFrame: Int? = null
  )

  @Serializable
  public data class SetWebLifecycleStateParameter(
    /**
     * Target lifecycle state
     */
    public val state: String
  )

  @Serializable
  public data class SetProduceCompilationCacheParameter(
    public val enabled: Boolean
  )

  @Serializable
  public data class AddCompilationCacheParameter(
    public val url: String,
    /**
     * Base64-encoded data (Encoded as a base64 string when passed over JSON)
     */
    public val `data`: String
  )

  @Serializable
  public data class GenerateTestReportParameter(
    /**
     * Message to be displayed in the report.
     */
    public val message: String,
    /**
     * Specifies the endpoint group to deliver the report to.
     */
    public val group: String? = null
  )

  @Serializable
  public data class SetInterceptFileChooserDialogParameter(
    public val enabled: Boolean
  )
}
