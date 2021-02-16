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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.animation: Animation
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Animation(this))

public class Animation(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val animationCanceled: Flow<AnimationCanceledParameter> = client
          .events
          .filter {
              it.method == "animationCanceled"
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
  public val animationCreated: Flow<AnimationCreatedParameter> = client
          .events
          .filter {
              it.method == "animationCreated"
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
  public val animationStarted: Flow<AnimationStartedParameter> = client
          .events
          .filter {
              it.method == "animationStarted"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Disables animation domain notifications.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Animation.disable", parameter)
  }

  /**
   * Enables animation domain notifications.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Animation.enable", parameter)
  }

  /**
   * Returns the current time of the an animation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getCurrentTime(args: GetCurrentTimeParameter): GetCurrentTimeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Animation.getCurrentTime", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns the current time of the an animation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getCurrentTime(id: String): GetCurrentTimeReturn {
    val parameter = GetCurrentTimeParameter(id = id)
    return getCurrentTime(parameter)
  }

  /**
   * Gets the playback rate of the document timeline.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getPlaybackRate(): GetPlaybackRateReturn {
    val parameter = null
    val result = client.callCommand("Animation.getPlaybackRate", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Releases a set of animations to no longer be manipulated.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun releaseAnimations(args: ReleaseAnimationsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Animation.releaseAnimations", parameter)
  }

  /**
   * Releases a set of animations to no longer be manipulated.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun releaseAnimations(animations: String): Unit {
    val parameter = ReleaseAnimationsParameter(animations = animations)
    releaseAnimations(parameter)
  }

  /**
   * Gets the remote object of the Animation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun resolveAnimation(args: ResolveAnimationParameter): ResolveAnimationReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Animation.resolveAnimation", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Gets the remote object of the Animation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun resolveAnimation(animationId: String): ResolveAnimationReturn {
    val parameter = ResolveAnimationParameter(animationId = animationId)
    return resolveAnimation(parameter)
  }

  /**
   * Seek a set of animations to a particular time within each animation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun seekAnimations(args: SeekAnimationsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Animation.seekAnimations", parameter)
  }

  /**
   * Seek a set of animations to a particular time within each animation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun seekAnimations(animations: String, currentTime: Double): Unit {
    val parameter = SeekAnimationsParameter(animations = animations,currentTime = currentTime)
    seekAnimations(parameter)
  }

  /**
   * Sets the paused state of a set of animations.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setPaused(args: SetPausedParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Animation.setPaused", parameter)
  }

  /**
   * Sets the paused state of a set of animations.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setPaused(animations: String, paused: Boolean): Unit {
    val parameter = SetPausedParameter(animations = animations,paused = paused)
    setPaused(parameter)
  }

  /**
   * Sets the playback rate of the document timeline.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setPlaybackRate(args: SetPlaybackRateParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Animation.setPlaybackRate", parameter)
  }

  /**
   * Sets the playback rate of the document timeline.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setPlaybackRate(playbackRate: Double): Unit {
    val parameter = SetPlaybackRateParameter(playbackRate = playbackRate)
    setPlaybackRate(parameter)
  }

  /**
   * Sets the timing of an animation node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setTiming(args: SetTimingParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Animation.setTiming", parameter)
  }

  /**
   * Sets the timing of an animation node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setTiming(
    animationId: String,
    duration: Double,
    delay: Double
  ): Unit {
    val parameter = SetTimingParameter(animationId = animationId,duration = duration,delay = delay)
    setTiming(parameter)
  }

  /**
   * Animation instance.
   */
  @Serializable
  public data class Animation(
    /**
     * `Animation`'s id.
     */
    public val id: String,
    /**
     * `Animation`'s name.
     */
    public val name: String,
    /**
     * `Animation`'s internal paused state.
     */
    public val pausedState: Boolean,
    /**
     * `Animation`'s play state.
     */
    public val playState: String,
    /**
     * `Animation`'s playback rate.
     */
    public val playbackRate: Double,
    /**
     * `Animation`'s start time.
     */
    public val startTime: Double,
    /**
     * `Animation`'s current time.
     */
    public val currentTime: Double,
    /**
     * Animation type of `Animation`.
     */
    public val type: String,
    /**
     * `Animation`'s source animation node.
     */
    public val source: AnimationEffect? = null,
    /**
     * A unique ID for `Animation` representing the sources that triggered this CSS
     * animation/transition.
     */
    public val cssId: String? = null
  )

  /**
   * AnimationEffect instance
   */
  @Serializable
  public data class AnimationEffect(
    /**
     * `AnimationEffect`'s delay.
     */
    public val delay: Double,
    /**
     * `AnimationEffect`'s end delay.
     */
    public val endDelay: Double,
    /**
     * `AnimationEffect`'s iteration start.
     */
    public val iterationStart: Double,
    /**
     * `AnimationEffect`'s iterations.
     */
    public val iterations: Double,
    /**
     * `AnimationEffect`'s iteration duration.
     */
    public val duration: Double,
    /**
     * `AnimationEffect`'s playback direction.
     */
    public val direction: String,
    /**
     * `AnimationEffect`'s fill mode.
     */
    public val fill: String,
    /**
     * `AnimationEffect`'s target node.
     */
    public val backendNodeId: Int? = null,
    /**
     * `AnimationEffect`'s keyframes.
     */
    public val keyframesRule: KeyframesRule? = null,
    /**
     * `AnimationEffect`'s timing function.
     */
    public val easing: String
  )

  /**
   * Keyframes Rule
   */
  @Serializable
  public data class KeyframesRule(
    /**
     * CSS keyframed animation's name.
     */
    public val name: String? = null,
    /**
     * List of animation keyframes.
     */
    public val keyframes: List<KeyframeStyle>
  )

  /**
   * Keyframe Style
   */
  @Serializable
  public data class KeyframeStyle(
    /**
     * Keyframe's time offset.
     */
    public val offset: String,
    /**
     * `AnimationEffect`'s timing function.
     */
    public val easing: String
  )

  /**
   * Event for when an animation has been cancelled.
   */
  public data class AnimationCanceledParameter(
    /**
     * Id of the animation that was cancelled.
     */
    public val id: String
  )

  /**
   * Event for each animation that has been created.
   */
  public data class AnimationCreatedParameter(
    /**
     * Id of the animation that was created.
     */
    public val id: String
  )

  /**
   * Event for animation that has been started.
   */
  public data class AnimationStartedParameter(
    /**
     * Animation that was started.
     */
    public val animation: Animation
  )

  @Serializable
  public data class GetCurrentTimeParameter(
    /**
     * Id of animation.
     */
    public val id: String
  )

  @Serializable
  public data class GetCurrentTimeReturn(
    /**
     * Current time of the page.
     */
    public val currentTime: Double
  )

  @Serializable
  public data class GetPlaybackRateReturn(
    /**
     * Playback rate for animations on page.
     */
    public val playbackRate: Double
  )

  @Serializable
  public data class ReleaseAnimationsParameter(
    /**
     * List of animation ids to seek.
     */
    public val animations: String
  )

  @Serializable
  public data class ResolveAnimationParameter(
    /**
     * Animation id.
     */
    public val animationId: String
  )

  @Serializable
  public data class ResolveAnimationReturn(
    /**
     * Corresponding remote object.
     */
    public val remoteObject: Runtime.RemoteObject
  )

  @Serializable
  public data class SeekAnimationsParameter(
    /**
     * List of animation ids to seek.
     */
    public val animations: String,
    /**
     * Set the current time of each animation.
     */
    public val currentTime: Double
  )

  @Serializable
  public data class SetPausedParameter(
    /**
     * Animations to set the pause state of.
     */
    public val animations: String,
    /**
     * Paused state to set to.
     */
    public val paused: Boolean
  )

  @Serializable
  public data class SetPlaybackRateParameter(
    /**
     * Playback rate for animations on page
     */
    public val playbackRate: Double
  )

  @Serializable
  public data class SetTimingParameter(
    /**
     * Animation id.
     */
    public val animationId: String,
    /**
     * Duration of the animation.
     */
    public val duration: Double,
    /**
     * Delay of the animation.
     */
    public val delay: Double
  )
}
