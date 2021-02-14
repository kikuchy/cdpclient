package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Deprecated
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
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

public val CDPClient.target: Target
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Target(this))

/**
 * Supports additional targets discovery and allows to attach to them.
 */
public class Target(
  private val client: CDPClient
) : Domain {
  public val attachedToTarget: Flow<AttachedToTargetParameter> = client.events.filter {
          it.method == "attachedToTarget"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val detachedFromTarget: Flow<DetachedFromTargetParameter> = client.events.filter {
          it.method == "detachedFromTarget"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val receivedMessageFromTarget: Flow<ReceivedMessageFromTargetParameter> =
      client.events.filter {
          it.method == "receivedMessageFromTarget"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val targetCreated: Flow<TargetCreatedParameter> = client.events.filter {
          it.method == "targetCreated"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val targetDestroyed: Flow<TargetDestroyedParameter> = client.events.filter {
          it.method == "targetDestroyed"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val targetCrashed: Flow<TargetCrashedParameter> = client.events.filter {
          it.method == "targetCrashed"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val targetInfoChanged: Flow<TargetInfoChangedParameter> = client.events.filter {
          it.method == "targetInfoChanged"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  /**
   * Activates (focuses) the target.
   */
  public suspend fun activateTarget(args: ActivateTargetParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Target.activateTarget", parameter)
  }

  public suspend fun activateTarget(targetId: String): Unit {
    val parameter = ActivateTargetParameter(targetId = targetId)
    activateTarget(parameter)
  }

  /**
   * Attaches to the target with given id.
   */
  public suspend fun attachToTarget(args: AttachToTargetParameter): AttachToTargetReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Target.attachToTarget", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun attachToTarget(targetId: String, flatten: Boolean? = null):
      AttachToTargetReturn {
    val parameter = AttachToTargetParameter(targetId = targetId,flatten = flatten)
    return attachToTarget(parameter)
  }

  /**
   * Attaches to the browser target, only uses flat sessionId mode.
   */
  public suspend fun attachToBrowserTarget(): AttachToBrowserTargetReturn {
    val parameter = null
    val result = client.callCommand("Target.attachToBrowserTarget", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Closes the target. If the target is a page that gets closed too.
   */
  public suspend fun closeTarget(args: CloseTargetParameter): CloseTargetReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Target.closeTarget", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun closeTarget(targetId: String): CloseTargetReturn {
    val parameter = CloseTargetParameter(targetId = targetId)
    return closeTarget(parameter)
  }

  /**
   * Inject object to the target's main frame that provides a communication
   * channel with browser target.
   *
   * Injected object will be available as `window[bindingName]`.
   *
   * The object has the follwing API:
   * - `binding.send(json)` - a method to send messages over the remote debugging protocol
   * - `binding.onmessage = json => handleMessage(json)` - a callback that will be called for the
   * protocol notifications and command responses.
   */
  public suspend fun exposeDevToolsProtocol(args: ExposeDevToolsProtocolParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Target.exposeDevToolsProtocol", parameter)
  }

  public suspend fun exposeDevToolsProtocol(targetId: String, bindingName: String? = null): Unit {
    val parameter = ExposeDevToolsProtocolParameter(targetId = targetId,bindingName = bindingName)
    exposeDevToolsProtocol(parameter)
  }

  /**
   * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than
   * one.
   */
  public suspend fun createBrowserContext(args: CreateBrowserContextParameter):
      CreateBrowserContextReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Target.createBrowserContext", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun createBrowserContext(
    disposeOnDetach: Boolean? = null,
    proxyServer: String? = null,
    proxyBypassList: String? = null
  ): CreateBrowserContextReturn {
    val parameter = CreateBrowserContextParameter(disposeOnDetach = disposeOnDetach,proxyServer =
        proxyServer,proxyBypassList = proxyBypassList)
    return createBrowserContext(parameter)
  }

  /**
   * Returns all browser contexts created with `Target.createBrowserContext` method.
   */
  public suspend fun getBrowserContexts(): GetBrowserContextsReturn {
    val parameter = null
    val result = client.callCommand("Target.getBrowserContexts", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Creates a new page.
   */
  public suspend fun createTarget(args: CreateTargetParameter): CreateTargetReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Target.createTarget", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun createTarget(
    url: String,
    width: Int? = null,
    height: Int? = null,
    browserContextId: String? = null,
    enableBeginFrameControl: Boolean? = null,
    newWindow: Boolean? = null,
    background: Boolean? = null
  ): CreateTargetReturn {
    val parameter = CreateTargetParameter(url = url,width = width,height = height,browserContextId =
        browserContextId,enableBeginFrameControl = enableBeginFrameControl,newWindow =
        newWindow,background = background)
    return createTarget(parameter)
  }

  /**
   * Detaches session with given id.
   */
  public suspend fun detachFromTarget(args: DetachFromTargetParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Target.detachFromTarget", parameter)
  }

  public suspend fun detachFromTarget(sessionId: String? = null, targetId: String? = null): Unit {
    val parameter = DetachFromTargetParameter(sessionId = sessionId,targetId = targetId)
    detachFromTarget(parameter)
  }

  /**
   * Deletes a BrowserContext. All the belonging pages will be closed without calling their
   * beforeunload hooks.
   */
  public suspend fun disposeBrowserContext(args: DisposeBrowserContextParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Target.disposeBrowserContext", parameter)
  }

  public suspend fun disposeBrowserContext(browserContextId: String): Unit {
    val parameter = DisposeBrowserContextParameter(browserContextId = browserContextId)
    disposeBrowserContext(parameter)
  }

  /**
   * Returns information about a target.
   */
  public suspend fun getTargetInfo(args: GetTargetInfoParameter): GetTargetInfoReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Target.getTargetInfo", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getTargetInfo(targetId: String? = null): GetTargetInfoReturn {
    val parameter = GetTargetInfoParameter(targetId = targetId)
    return getTargetInfo(parameter)
  }

  /**
   * Retrieves a list of available targets.
   */
  public suspend fun getTargets(): GetTargetsReturn {
    val parameter = null
    val result = client.callCommand("Target.getTargets", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Sends protocol message over session with given id.
   * Consider using flat mode instead; see commands attachToTarget, setAutoAttach,
   * and crbug.com/991325.
   */
  @Deprecated(message = "")
  public suspend fun sendMessageToTarget(args: SendMessageToTargetParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Target.sendMessageToTarget", parameter)
  }

  public suspend fun sendMessageToTarget(
    message: String,
    sessionId: String? = null,
    targetId: String? = null
  ): Unit {
    val parameter = SendMessageToTargetParameter(message = message,sessionId = sessionId,targetId =
        targetId)
    sendMessageToTarget(parameter)
  }

  /**
   * Controls whether to automatically attach to new targets which are considered to be related to
   * this one. When turned on, attaches to all existing related targets as well. When turned off,
   * automatically detaches from all currently attached targets.
   */
  public suspend fun setAutoAttach(args: SetAutoAttachParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Target.setAutoAttach", parameter)
  }

  public suspend fun setAutoAttach(
    autoAttach: Boolean,
    waitForDebuggerOnStart: Boolean,
    flatten: Boolean? = null
  ): Unit {
    val parameter = SetAutoAttachParameter(autoAttach = autoAttach,waitForDebuggerOnStart =
        waitForDebuggerOnStart,flatten = flatten)
    setAutoAttach(parameter)
  }

  /**
   * Controls whether to discover available targets and notify via
   * `targetCreated/targetInfoChanged/targetDestroyed` events.
   */
  public suspend fun setDiscoverTargets(args: SetDiscoverTargetsParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Target.setDiscoverTargets", parameter)
  }

  public suspend fun setDiscoverTargets(discover: Boolean): Unit {
    val parameter = SetDiscoverTargetsParameter(discover = discover)
    setDiscoverTargets(parameter)
  }

  /**
   * Enables target discovery for the specified locations, when `setDiscoverTargets` was set to
   * `true`.
   */
  public suspend fun setRemoteLocations(args: SetRemoteLocationsParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Target.setRemoteLocations", parameter)
  }

  public suspend fun setRemoteLocations(locations: List<RemoteLocation>): Unit {
    val parameter = SetRemoteLocationsParameter(locations = locations)
    setRemoteLocations(parameter)
  }

  @Serializable
  public class TargetInfo(
    public val targetId: String,
    public val type: String,
    public val title: String,
    public val url: String,
    /**
     * Whether the target has an attached client.
     */
    public val attached: Boolean,
    /**
     * Opener target Id
     */
    public val openerId: String? = null,
    /**
     * Whether the target has access to the originating window.
     */
    public val canAccessOpener: Boolean,
    /**
     * Frame id of originating window (is only set if target has an opener).
     */
    public val openerFrameId: String? = null,
    public val browserContextId: String? = null
  )

  @Serializable
  public class RemoteLocation(
    public val host: String,
    public val port: Int
  )

  /**
   * Issued when attached to target because of auto-attach or `attachToTarget` command.
   */
  public class AttachedToTargetParameter(
    /**
     * Identifier assigned to the session used to send/receive messages.
     */
    public val sessionId: String,
    public val targetInfo: TargetInfo,
    public val waitingForDebugger: Boolean
  )

  /**
   * Issued when detached from target for any reason (including `detachFromTarget` command). Can be
   * issued multiple times per target if multiple sessions have been attached to it.
   */
  public class DetachedFromTargetParameter(
    /**
     * Detached session identifier.
     */
    public val sessionId: String,
    /**
     * Deprecated.
     */
    public val targetId: String? = null
  )

  /**
   * Notifies about a new protocol message received from the session (as reported in
   * `attachedToTarget` event).
   */
  public class ReceivedMessageFromTargetParameter(
    /**
     * Identifier of a session which sends a message.
     */
    public val sessionId: String,
    public val message: String,
    /**
     * Deprecated.
     */
    public val targetId: String? = null
  )

  /**
   * Issued when a possible inspection target is created.
   */
  public class TargetCreatedParameter(
    public val targetInfo: TargetInfo
  )

  /**
   * Issued when a target is destroyed.
   */
  public class TargetDestroyedParameter(
    public val targetId: String
  )

  /**
   * Issued when a target has crashed.
   */
  public class TargetCrashedParameter(
    public val targetId: String,
    /**
     * Termination status type.
     */
    public val status: String,
    /**
     * Termination error code.
     */
    public val errorCode: Int
  )

  /**
   * Issued when some information about a target has changed. This only happens between
   * `targetCreated` and `targetDestroyed`.
   */
  public class TargetInfoChangedParameter(
    public val targetInfo: TargetInfo
  )

  @Serializable
  public data class ActivateTargetParameter(
    public val targetId: String
  )

  @Serializable
  public data class AttachToTargetParameter(
    public val targetId: String,
    /**
     * Enables "flat" access to the session via specifying sessionId attribute in the commands.
     * We plan to make this the default, deprecate non-flattened mode,
     * and eventually retire it. See crbug.com/991325.
     */
    public val flatten: Boolean?
  )

  @Serializable
  public data class AttachToTargetReturn(
    /**
     * Id assigned to the session.
     */
    public val sessionId: String
  )

  @Serializable
  public data class AttachToBrowserTargetReturn(
    /**
     * Id assigned to the session.
     */
    public val sessionId: String
  )

  @Serializable
  public data class CloseTargetParameter(
    public val targetId: String
  )

  @Serializable
  public data class CloseTargetReturn(
    /**
     * Always set to true. If an error occurs, the response indicates protocol error.
     */
    public val success: Boolean
  )

  @Serializable
  public data class ExposeDevToolsProtocolParameter(
    public val targetId: String,
    /**
     * Binding name, 'cdp' if not specified.
     */
    public val bindingName: String?
  )

  @Serializable
  public data class CreateBrowserContextParameter(
    /**
     * If specified, disposes this context when debugging session disconnects.
     */
    public val disposeOnDetach: Boolean?,
    /**
     * Proxy server, similar to the one passed to --proxy-server
     */
    public val proxyServer: String?,
    /**
     * Proxy bypass list, similar to the one passed to --proxy-bypass-list
     */
    public val proxyBypassList: String?
  )

  @Serializable
  public data class CreateBrowserContextReturn(
    /**
     * The id of the context created.
     */
    public val browserContextId: String
  )

  @Serializable
  public data class GetBrowserContextsReturn(
    /**
     * An array of browser context ids.
     */
    public val browserContextIds: List<String>
  )

  @Serializable
  public data class CreateTargetParameter(
    /**
     * The initial URL the page will be navigated to.
     */
    public val url: String,
    /**
     * Frame width in DIP (headless chrome only).
     */
    public val width: Int?,
    /**
     * Frame height in DIP (headless chrome only).
     */
    public val height: Int?,
    /**
     * The browser context to create the page in.
     */
    public val browserContextId: String?,
    /**
     * Whether BeginFrames for this target will be controlled via DevTools (headless chrome only,
     * not supported on MacOS yet, false by default).
     */
    public val enableBeginFrameControl: Boolean?,
    /**
     * Whether to create a new Window or Tab (chrome-only, false by default).
     */
    public val newWindow: Boolean?,
    /**
     * Whether to create the target in background or foreground (chrome-only,
     * false by default).
     */
    public val background: Boolean?
  )

  @Serializable
  public data class CreateTargetReturn(
    /**
     * The id of the page opened.
     */
    public val targetId: String
  )

  @Serializable
  public data class DetachFromTargetParameter(
    /**
     * Session to detach.
     */
    public val sessionId: String?,
    /**
     * Deprecated.
     */
    public val targetId: String?
  )

  @Serializable
  public data class DisposeBrowserContextParameter(
    public val browserContextId: String
  )

  @Serializable
  public data class GetTargetInfoParameter(
    public val targetId: String?
  )

  @Serializable
  public data class GetTargetInfoReturn(
    public val targetInfo: TargetInfo
  )

  @Serializable
  public data class GetTargetsReturn(
    /**
     * The list of targets.
     */
    public val targetInfos: List<TargetInfo>
  )

  @Serializable
  public data class SendMessageToTargetParameter(
    public val message: String,
    /**
     * Identifier of the session.
     */
    public val sessionId: String?,
    /**
     * Deprecated.
     */
    public val targetId: String?
  )

  @Serializable
  public data class SetAutoAttachParameter(
    /**
     * Whether to auto-attach to related targets.
     */
    public val autoAttach: Boolean,
    /**
     * Whether to pause new targets when attaching to them. Use `Runtime.runIfWaitingForDebugger`
     * to run paused targets.
     */
    public val waitForDebuggerOnStart: Boolean,
    /**
     * Enables "flat" access to the session via specifying sessionId attribute in the commands.
     * We plan to make this the default, deprecate non-flattened mode,
     * and eventually retire it. See crbug.com/991325.
     */
    public val flatten: Boolean?
  )

  @Serializable
  public data class SetDiscoverTargetsParameter(
    /**
     * Whether to discover available targets.
     */
    public val discover: Boolean
  )

  @Serializable
  public data class SetRemoteLocationsParameter(
    /**
     * List of remote locations.
     */
    public val locations: List<RemoteLocation>
  )
}