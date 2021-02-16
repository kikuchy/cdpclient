package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.backgroundService: BackgroundService
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(BackgroundService(this))

/**
 * Defines events for background web platform features.
 */
public class BackgroundService(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val recordingStateChanged: Flow<RecordingStateChangedParameter> = client
          .events
          .filter {
              it.method == "recordingStateChanged"
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
  public val backgroundServiceEventReceived: Flow<BackgroundServiceEventReceivedParameter> = client
          .events
          .filter {
              it.method == "backgroundServiceEventReceived"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Enables event updates for the service.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startObserving(args: StartObservingParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("BackgroundService.startObserving", parameter)
  }

  /**
   * Enables event updates for the service.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startObserving(service: ServiceName): Unit {
    val parameter = StartObservingParameter(service = service)
    startObserving(parameter)
  }

  /**
   * Disables event updates for the service.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stopObserving(args: StopObservingParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("BackgroundService.stopObserving", parameter)
  }

  /**
   * Disables event updates for the service.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stopObserving(service: ServiceName): Unit {
    val parameter = StopObservingParameter(service = service)
    stopObserving(parameter)
  }

  /**
   * Set the recording state for the service.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setRecording(args: SetRecordingParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("BackgroundService.setRecording", parameter)
  }

  /**
   * Set the recording state for the service.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setRecording(shouldRecord: Boolean, service: ServiceName): Unit {
    val parameter = SetRecordingParameter(shouldRecord = shouldRecord, service = service)
    setRecording(parameter)
  }

  /**
   * Clears all stored data for the service.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clearEvents(args: ClearEventsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("BackgroundService.clearEvents", parameter)
  }

  /**
   * Clears all stored data for the service.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clearEvents(service: ServiceName): Unit {
    val parameter = ClearEventsParameter(service = service)
    clearEvents(parameter)
  }

  /**
   * The Background Service that will be associated with the commands/events.
   * Every Background Service operates independently, but they share the same
   * API.
   */
  @Serializable
  public enum class ServiceName {
    @SerialName("backgroundFetch")
    BACKGROUNDFETCH,
    @SerialName("backgroundSync")
    BACKGROUNDSYNC,
    @SerialName("pushMessaging")
    PUSHMESSAGING,
    @SerialName("notifications")
    NOTIFICATIONS,
    @SerialName("paymentHandler")
    PAYMENTHANDLER,
    @SerialName("periodicBackgroundSync")
    PERIODICBACKGROUNDSYNC,
  }

  /**
   * A key-value pair for additional event information to pass along.
   */
  @Serializable
  public data class EventMetadata(
    public val key: String,
    public val value: String
  )

  @Serializable
  public data class BackgroundServiceEvent(
    /**
     * Timestamp of the event (in seconds).
     */
    public val timestamp: Double,
    /**
     * The origin this event belongs to.
     */
    public val origin: String,
    /**
     * The Service Worker ID that initiated the event.
     */
    public val serviceWorkerRegistrationId: String,
    /**
     * The Background Service this event belongs to.
     */
    public val service: ServiceName,
    /**
     * A description of the event.
     */
    public val eventName: String,
    /**
     * An identifier that groups related events together.
     */
    public val instanceId: String,
    /**
     * A list of event-specific information.
     */
    public val eventMetadata: List<EventMetadata>
  )

  /**
   * Called when the recording state for the service has been updated.
   */
  public data class RecordingStateChangedParameter(
    public val isRecording: Boolean,
    public val service: ServiceName
  )

  /**
   * Called with all existing backgroundServiceEvents when enabled, and all new
   * events afterwards if enabled and recording.
   */
  public data class BackgroundServiceEventReceivedParameter(
    public val backgroundServiceEvent: BackgroundServiceEvent
  )

  @Serializable
  public data class StartObservingParameter(
    public val service: ServiceName
  )

  @Serializable
  public data class StopObservingParameter(
    public val service: ServiceName
  )

  @Serializable
  public data class SetRecordingParameter(
    public val shouldRecord: Boolean,
    public val service: ServiceName
  )

  @Serializable
  public data class ClearEventsParameter(
    public val service: ServiceName
  )
}
