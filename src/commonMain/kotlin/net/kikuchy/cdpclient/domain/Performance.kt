package net.kikuchy.cdpclient.domain

import kotlin.Deprecated
import kotlin.Double
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

public val CDPClient.performance: Performance
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Performance(this))

public class Performance(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val metrics: Flow<MetricsParameter> = client
          .events
          .filter {
              it.method == "metrics"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Disable collecting and reporting metrics.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Performance.disable", parameter)
  }

  /**
   * Enable collecting and reporting metrics.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(args: EnableParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Performance.enable", parameter)
  }

  public suspend fun enable(timeDomain: String? = null): Unit {
    val parameter = EnableParameter(timeDomain = timeDomain)
    enable(parameter)
  }

  /**
   * Sets time domain to use for collecting and reporting duration metrics.
   * Note that this must be called before enabling metrics collection. Calling
   * this method while metrics collection is enabled returns an error.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setTimeDomain(args: SetTimeDomainParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Performance.setTimeDomain", parameter)
  }

  public suspend fun setTimeDomain(timeDomain: String): Unit {
    val parameter = SetTimeDomainParameter(timeDomain = timeDomain)
    setTimeDomain(parameter)
  }

  /**
   * Retrieve current values of run-time metrics.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getMetrics(): GetMetricsReturn {
    val parameter = null
    val result = client.callCommand("Performance.getMetrics", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Run-time execution metric.
   */
  @Serializable
  public data class Metric(
    /**
     * Metric name.
     */
    public val name: String,
    /**
     * Metric value.
     */
    public val value: Double
  )

  /**
   * Current values of the metrics.
   */
  public data class MetricsParameter(
    /**
     * Current values of the metrics.
     */
    public val metrics: List<Metric>,
    /**
     * Timestamp title.
     */
    public val title: String
  )

  @Serializable
  public data class EnableParameter(
    /**
     * Time domain to use for collecting and reporting duration metrics.
     */
    public val timeDomain: String? = null
  )

  @Serializable
  public data class SetTimeDomainParameter(
    /**
     * Time domain
     */
    public val timeDomain: String
  )

  @Serializable
  public data class GetMetricsReturn(
    /**
     * Current values for run-time metrics.
     */
    public val metrics: List<Metric>
  )
}
