package net.kikuchy.cdpclient.domain

import kotlin.Double
import kotlin.String
import kotlin.Unit
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

public val CDPClient.webAudio: WebAudio
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(WebAudio(this))

/**
 * This domain allows inspection of Web Audio API.
 * https://webaudio.github.io/web-audio-api/
 */
public class WebAudio(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val contextCreated: Flow<ContextCreatedParameter> = client
          .events
          .filter {
              it.method == "contextCreated"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val contextWillBeDestroyed: Flow<ContextWillBeDestroyedParameter> = client
          .events
          .filter {
              it.method == "contextWillBeDestroyed"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val contextChanged: Flow<ContextChangedParameter> = client
          .events
          .filter {
              it.method == "contextChanged"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val audioListenerCreated: Flow<AudioListenerCreatedParameter> = client
          .events
          .filter {
              it.method == "audioListenerCreated"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val audioListenerWillBeDestroyed: Flow<AudioListenerWillBeDestroyedParameter> = client
          .events
          .filter {
              it.method == "audioListenerWillBeDestroyed"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val audioNodeCreated: Flow<AudioNodeCreatedParameter> = client
          .events
          .filter {
              it.method == "audioNodeCreated"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val audioNodeWillBeDestroyed: Flow<AudioNodeWillBeDestroyedParameter> = client
          .events
          .filter {
              it.method == "audioNodeWillBeDestroyed"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val audioParamCreated: Flow<AudioParamCreatedParameter> = client
          .events
          .filter {
              it.method == "audioParamCreated"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val audioParamWillBeDestroyed: Flow<AudioParamWillBeDestroyedParameter> = client
          .events
          .filter {
              it.method == "audioParamWillBeDestroyed"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val nodesConnected: Flow<NodesConnectedParameter> = client
          .events
          .filter {
              it.method == "nodesConnected"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val nodesDisconnected: Flow<NodesDisconnectedParameter> = client
          .events
          .filter {
              it.method == "nodesDisconnected"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val nodeParamConnected: Flow<NodeParamConnectedParameter> = client
          .events
          .filter {
              it.method == "nodeParamConnected"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val nodeParamDisconnected: Flow<NodeParamDisconnectedParameter> = client
          .events
          .filter {
              it.method == "nodeParamDisconnected"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Enables the WebAudio domain and starts sending context lifetime events.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("WebAudio.enable", parameter)
  }

  /**
   * Disables the WebAudio domain.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("WebAudio.disable", parameter)
  }

  /**
   * Fetch the realtime data from the registered contexts.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getRealtimeData(args: GetRealtimeDataParameter): GetRealtimeDataReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("WebAudio.getRealtimeData", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getRealtimeData(contextId: String): GetRealtimeDataReturn {
    val parameter = GetRealtimeDataParameter(contextId = contextId)
    return getRealtimeData(parameter)
  }

  /**
   * Enum of BaseAudioContext types
   */
  @Serializable
  public enum class ContextType {
    @SerialName("realtime")
    REALTIME,
    @SerialName("offline")
    OFFLINE,
  }

  /**
   * Enum of AudioContextState from the spec
   */
  @Serializable
  public enum class ContextState {
    @SerialName("suspended")
    SUSPENDED,
    @SerialName("running")
    RUNNING,
    @SerialName("closed")
    CLOSED,
  }

  /**
   * Enum of AudioNode::ChannelCountMode from the spec
   */
  @Serializable
  public enum class ChannelCountMode {
    @SerialName("clamped-max")
    CLAMPED_MAX,
    @SerialName("explicit")
    EXPLICIT,
    @SerialName("max")
    MAX,
  }

  /**
   * Enum of AudioNode::ChannelInterpretation from the spec
   */
  @Serializable
  public enum class ChannelInterpretation {
    @SerialName("discrete")
    DISCRETE,
    @SerialName("speakers")
    SPEAKERS,
  }

  /**
   * Enum of AudioParam::AutomationRate from the spec
   */
  @Serializable
  public enum class AutomationRate {
    @SerialName("a-rate")
    A_RATE,
    @SerialName("k-rate")
    K_RATE,
  }

  /**
   * Fields in AudioContext that change in real-time.
   */
  @Serializable
  public data class ContextRealtimeData(
    /**
     * The current context time in second in BaseAudioContext.
     */
    public val currentTime: Double,
    /**
     * The time spent on rendering graph divided by render qunatum duration,
     * and multiplied by 100. 100 means the audio renderer reached the full
     * capacity and glitch may occur.
     */
    public val renderCapacity: Double,
    /**
     * A running mean of callback interval.
     */
    public val callbackIntervalMean: Double,
    /**
     * A running variance of callback interval.
     */
    public val callbackIntervalVariance: Double
  )

  /**
   * Protocol object for BaseAudioContext
   */
  @Serializable
  public data class BaseAudioContext(
    public val contextId: String,
    public val contextType: ContextType,
    public val contextState: ContextState,
    public val realtimeData: ContextRealtimeData? = null,
    /**
     * Platform-dependent callback buffer size.
     */
    public val callbackBufferSize: Double,
    /**
     * Number of output channels supported by audio hardware in use.
     */
    public val maxOutputChannelCount: Double,
    /**
     * Context sample rate.
     */
    public val sampleRate: Double
  )

  /**
   * Protocol object for AudioListener
   */
  @Serializable
  public data class AudioListener(
    public val listenerId: String,
    public val contextId: String
  )

  /**
   * Protocol object for AudioNode
   */
  @Serializable
  public data class AudioNode(
    public val nodeId: String,
    public val contextId: String,
    public val nodeType: String,
    public val numberOfInputs: Double,
    public val numberOfOutputs: Double,
    public val channelCount: Double,
    public val channelCountMode: ChannelCountMode,
    public val channelInterpretation: ChannelInterpretation
  )

  /**
   * Protocol object for AudioParam
   */
  @Serializable
  public data class AudioParam(
    public val paramId: String,
    public val nodeId: String,
    public val contextId: String,
    public val paramType: String,
    public val rate: AutomationRate,
    public val defaultValue: Double,
    public val minValue: Double,
    public val maxValue: Double
  )

  /**
   * Notifies that a new BaseAudioContext has been created.
   */
  public data class ContextCreatedParameter(
    public val context: BaseAudioContext
  )

  /**
   * Notifies that an existing BaseAudioContext will be destroyed.
   */
  public data class ContextWillBeDestroyedParameter(
    public val contextId: String
  )

  /**
   * Notifies that existing BaseAudioContext has changed some properties (id stays the same)..
   */
  public data class ContextChangedParameter(
    public val context: BaseAudioContext
  )

  /**
   * Notifies that the construction of an AudioListener has finished.
   */
  public data class AudioListenerCreatedParameter(
    public val listener: AudioListener
  )

  /**
   * Notifies that a new AudioListener has been created.
   */
  public data class AudioListenerWillBeDestroyedParameter(
    public val contextId: String,
    public val listenerId: String
  )

  /**
   * Notifies that a new AudioNode has been created.
   */
  public data class AudioNodeCreatedParameter(
    public val node: AudioNode
  )

  /**
   * Notifies that an existing AudioNode has been destroyed.
   */
  public data class AudioNodeWillBeDestroyedParameter(
    public val contextId: String,
    public val nodeId: String
  )

  /**
   * Notifies that a new AudioParam has been created.
   */
  public data class AudioParamCreatedParameter(
    public val `param`: AudioParam
  )

  /**
   * Notifies that an existing AudioParam has been destroyed.
   */
  public data class AudioParamWillBeDestroyedParameter(
    public val contextId: String,
    public val nodeId: String,
    public val paramId: String
  )

  /**
   * Notifies that two AudioNodes are connected.
   */
  public data class NodesConnectedParameter(
    public val contextId: String,
    public val sourceId: String,
    public val destinationId: String,
    public val sourceOutputIndex: Double? = null,
    public val destinationInputIndex: Double? = null
  )

  /**
   * Notifies that AudioNodes are disconnected. The destination can be null, and it means all the
   * outgoing connections from the source are disconnected.
   */
  public data class NodesDisconnectedParameter(
    public val contextId: String,
    public val sourceId: String,
    public val destinationId: String,
    public val sourceOutputIndex: Double? = null,
    public val destinationInputIndex: Double? = null
  )

  /**
   * Notifies that an AudioNode is connected to an AudioParam.
   */
  public data class NodeParamConnectedParameter(
    public val contextId: String,
    public val sourceId: String,
    public val destinationId: String,
    public val sourceOutputIndex: Double? = null
  )

  /**
   * Notifies that an AudioNode is disconnected to an AudioParam.
   */
  public data class NodeParamDisconnectedParameter(
    public val contextId: String,
    public val sourceId: String,
    public val destinationId: String,
    public val sourceOutputIndex: Double? = null
  )

  @Serializable
  public data class GetRealtimeDataParameter(
    public val contextId: String
  )

  @Serializable
  public data class GetRealtimeDataReturn(
    public val realtimeData: ContextRealtimeData
  )
}
