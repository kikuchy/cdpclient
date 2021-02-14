package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
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

public val CDPClient.serviceWorker: ServiceWorker
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(ServiceWorker(this))

public class ServiceWorker(
  private val client: CDPClient
) : Domain {
  public val workerErrorReported: Flow<WorkerErrorReportedParameter> = client.events.filter {
          it.method == "workerErrorReported"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val workerRegistrationUpdated: Flow<WorkerRegistrationUpdatedParameter> =
      client.events.filter {
          it.method == "workerRegistrationUpdated"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val workerVersionUpdated: Flow<WorkerVersionUpdatedParameter> = client.events.filter {
          it.method == "workerVersionUpdated"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public suspend fun deliverPushMessage(args: DeliverPushMessageParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.deliverPushMessage", parameter)
  }

  public suspend fun deliverPushMessage(
    origin: String,
    registrationId: String,
    `data`: String
  ): Unit {
    val parameter = DeliverPushMessageParameter(origin = origin,registrationId = registrationId,data
        = data)
    deliverPushMessage(parameter)
  }

  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("ServiceWorker.disable", parameter)
  }

  public suspend fun dispatchSyncEvent(args: DispatchSyncEventParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.dispatchSyncEvent", parameter)
  }

  public suspend fun dispatchSyncEvent(
    origin: String,
    registrationId: String,
    tag: String,
    lastChance: Boolean
  ): Unit {
    val parameter = DispatchSyncEventParameter(origin = origin,registrationId = registrationId,tag =
        tag,lastChance = lastChance)
    dispatchSyncEvent(parameter)
  }

  public suspend fun dispatchPeriodicSyncEvent(args: DispatchPeriodicSyncEventParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.dispatchPeriodicSyncEvent", parameter)
  }

  public suspend fun dispatchPeriodicSyncEvent(
    origin: String,
    registrationId: String,
    tag: String
  ): Unit {
    val parameter = DispatchPeriodicSyncEventParameter(origin = origin,registrationId =
        registrationId,tag = tag)
    dispatchPeriodicSyncEvent(parameter)
  }

  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("ServiceWorker.enable", parameter)
  }

  public suspend fun inspectWorker(args: InspectWorkerParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.inspectWorker", parameter)
  }

  public suspend fun inspectWorker(versionId: String): Unit {
    val parameter = InspectWorkerParameter(versionId = versionId)
    inspectWorker(parameter)
  }

  public suspend fun setForceUpdateOnPageLoad(args: SetForceUpdateOnPageLoadParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.setForceUpdateOnPageLoad", parameter)
  }

  public suspend fun setForceUpdateOnPageLoad(forceUpdateOnPageLoad: Boolean): Unit {
    val parameter = SetForceUpdateOnPageLoadParameter(forceUpdateOnPageLoad = forceUpdateOnPageLoad)
    setForceUpdateOnPageLoad(parameter)
  }

  public suspend fun skipWaiting(args: SkipWaitingParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.skipWaiting", parameter)
  }

  public suspend fun skipWaiting(scopeURL: String): Unit {
    val parameter = SkipWaitingParameter(scopeURL = scopeURL)
    skipWaiting(parameter)
  }

  public suspend fun startWorker(args: StartWorkerParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.startWorker", parameter)
  }

  public suspend fun startWorker(scopeURL: String): Unit {
    val parameter = StartWorkerParameter(scopeURL = scopeURL)
    startWorker(parameter)
  }

  public suspend fun stopAllWorkers(): Unit {
    val parameter = null
    client.callCommand("ServiceWorker.stopAllWorkers", parameter)
  }

  public suspend fun stopWorker(args: StopWorkerParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.stopWorker", parameter)
  }

  public suspend fun stopWorker(versionId: String): Unit {
    val parameter = StopWorkerParameter(versionId = versionId)
    stopWorker(parameter)
  }

  public suspend fun unregister(args: UnregisterParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.unregister", parameter)
  }

  public suspend fun unregister(scopeURL: String): Unit {
    val parameter = UnregisterParameter(scopeURL = scopeURL)
    unregister(parameter)
  }

  public suspend fun updateRegistration(args: UpdateRegistrationParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("ServiceWorker.updateRegistration", parameter)
  }

  public suspend fun updateRegistration(scopeURL: String): Unit {
    val parameter = UpdateRegistrationParameter(scopeURL = scopeURL)
    updateRegistration(parameter)
  }

  /**
   * ServiceWorker registration.
   */
  @Serializable
  public class ServiceWorkerRegistration(
    public val registrationId: String,
    public val scopeURL: String,
    public val isDeleted: Boolean
  )

  @Serializable
  public enum class ServiceWorkerVersionRunningStatus {
    @SerialName("stopped")
    STOPPED,
    @SerialName("starting")
    STARTING,
    @SerialName("running")
    RUNNING,
    @SerialName("stopping")
    STOPPING,
  }

  @Serializable
  public enum class ServiceWorkerVersionStatus {
    @SerialName("new")
    NEW,
    @SerialName("installing")
    INSTALLING,
    @SerialName("installed")
    INSTALLED,
    @SerialName("activating")
    ACTIVATING,
    @SerialName("activated")
    ACTIVATED,
    @SerialName("redundant")
    REDUNDANT,
  }

  /**
   * ServiceWorker version.
   */
  @Serializable
  public class ServiceWorkerVersion(
    public val versionId: String,
    public val registrationId: String,
    public val scriptURL: String,
    public val runningStatus: ServiceWorkerVersionRunningStatus,
    public val status: ServiceWorkerVersionStatus,
    /**
     * The Last-Modified header value of the main script.
     */
    public val scriptLastModified: Double? = null,
    /**
     * The time at which the response headers of the main script were received from the server.
     * For cached script it is the last time the cache entry was validated.
     */
    public val scriptResponseTime: Double? = null,
    public val controlledClients: List<String>? = null,
    public val targetId: String? = null
  )

  /**
   * ServiceWorker error message.
   */
  @Serializable
  public class ServiceWorkerErrorMessage(
    public val errorMessage: String,
    public val registrationId: String,
    public val versionId: String,
    public val sourceURL: String,
    public val lineNumber: Int,
    public val columnNumber: Int
  )

  public class WorkerErrorReportedParameter(
    public val errorMessage: ServiceWorkerErrorMessage
  )

  public class WorkerRegistrationUpdatedParameter(
    public val registrations: List<ServiceWorkerRegistration>
  )

  public class WorkerVersionUpdatedParameter(
    public val versions: List<ServiceWorkerVersion>
  )

  @Serializable
  public data class DeliverPushMessageParameter(
    public val origin: String,
    public val registrationId: String,
    public val `data`: String
  )

  @Serializable
  public data class DispatchSyncEventParameter(
    public val origin: String,
    public val registrationId: String,
    public val tag: String,
    public val lastChance: Boolean
  )

  @Serializable
  public data class DispatchPeriodicSyncEventParameter(
    public val origin: String,
    public val registrationId: String,
    public val tag: String
  )

  @Serializable
  public data class InspectWorkerParameter(
    public val versionId: String
  )

  @Serializable
  public data class SetForceUpdateOnPageLoadParameter(
    public val forceUpdateOnPageLoad: Boolean
  )

  @Serializable
  public data class SkipWaitingParameter(
    public val scopeURL: String
  )

  @Serializable
  public data class StartWorkerParameter(
    public val scopeURL: String
  )

  @Serializable
  public data class StopWorkerParameter(
    public val versionId: String
  )

  @Serializable
  public data class UnregisterParameter(
    public val scopeURL: String
  )

  @Serializable
  public data class UpdateRegistrationParameter(
    public val scopeURL: String
  )
}
