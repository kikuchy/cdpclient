package net.kikuchy.cdpclient.domain

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

public val CDPClient.cast: Cast
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Cast(this))

/**
 * A domain for interacting with Cast, Presentation API, and Remote Playback API
 * functionalities.
 */
public class Cast(
  private val client: CDPClient
) : Domain {
  public val sinksUpdated: Flow<SinksUpdatedParameter> = client.events.filter {
          it.method == "sinksUpdated"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val issueUpdated: Flow<IssueUpdatedParameter> = client.events.filter {
          it.method == "issueUpdated"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  /**
   * Starts observing for sinks that can be used for tab mirroring, and if set,
   * sinks compatible with |presentationUrl| as well. When sinks are found, a
   * |sinksUpdated| event is fired.
   * Also starts observing for issue messages. When an issue is added or removed,
   * an |issueUpdated| event is fired.
   */
  public suspend fun enable(args: EnableParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Cast.enable", parameter)
  }

  public suspend fun enable(presentationUrl: String? = null): Unit {
    val parameter = EnableParameter(presentationUrl = presentationUrl)
    enable(parameter)
  }

  /**
   * Stops observing for sinks and issues.
   */
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Cast.disable", parameter)
  }

  /**
   * Sets a sink to be used when the web page requests the browser to choose a
   * sink via Presentation API, Remote Playback API, or Cast SDK.
   */
  public suspend fun setSinkToUse(args: SetSinkToUseParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Cast.setSinkToUse", parameter)
  }

  public suspend fun setSinkToUse(sinkName: String): Unit {
    val parameter = SetSinkToUseParameter(sinkName = sinkName)
    setSinkToUse(parameter)
  }

  /**
   * Starts mirroring the tab to the sink.
   */
  public suspend fun startTabMirroring(args: StartTabMirroringParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Cast.startTabMirroring", parameter)
  }

  public suspend fun startTabMirroring(sinkName: String): Unit {
    val parameter = StartTabMirroringParameter(sinkName = sinkName)
    startTabMirroring(parameter)
  }

  /**
   * Stops the active Cast session on the sink.
   */
  public suspend fun stopCasting(args: StopCastingParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Cast.stopCasting", parameter)
  }

  public suspend fun stopCasting(sinkName: String): Unit {
    val parameter = StopCastingParameter(sinkName = sinkName)
    stopCasting(parameter)
  }

  @Serializable
  public class Sink(
    public val name: String,
    public val id: String,
    /**
     * Text describing the current session. Present only if there is an active
     * session on the sink.
     */
    public val session: String? = null
  )

  /**
   * This is fired whenever the list of available sinks changes. A sink is a
   * device or a software surface that you can cast to.
   */
  public class SinksUpdatedParameter(
    public val sinks: List<Sink>
  )

  /**
   * This is fired whenever the outstanding issue/error message changes.
   * |issueMessage| is empty if there is no issue.
   */
  public class IssueUpdatedParameter(
    public val issueMessage: String
  )

  @Serializable
  public data class EnableParameter(
    public val presentationUrl: String?
  )

  @Serializable
  public data class SetSinkToUseParameter(
    public val sinkName: String
  )

  @Serializable
  public data class StartTabMirroringParameter(
    public val sinkName: String
  )

  @Serializable
  public data class StopCastingParameter(
    public val sinkName: String
  )
}
