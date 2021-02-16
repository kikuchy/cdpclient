package net.kikuchy.cdpclient.domain

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

public val CDPClient.log: Log
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Log(this))

/**
 * Provides access to log entries.
 */
public class Log(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val entryAdded: Flow<EntryAddedParameter> = client
          .events
          .filter {
              it.method == "entryAdded"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Clears the log.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clear(): Unit {
    val parameter = null
    client.callCommand("Log.clear", parameter)
  }

  /**
   * Disables log domain, prevents further log entries from being reported to the client.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Log.disable", parameter)
  }

  /**
   * Enables log domain, sends the entries collected so far to the client by means of the
   * `entryAdded` notification.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Log.enable", parameter)
  }

  /**
   * start violation reporting.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startViolationsReport(args: StartViolationsReportParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Log.startViolationsReport", parameter)
  }

  /**
   * start violation reporting.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun startViolationsReport(config: List<ViolationSetting>): Unit {
    val parameter = StartViolationsReportParameter(config = config)
    startViolationsReport(parameter)
  }

  /**
   * Stop violation reporting.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun stopViolationsReport(): Unit {
    val parameter = null
    client.callCommand("Log.stopViolationsReport", parameter)
  }

  /**
   * Log entry.
   */
  @Serializable
  public data class LogEntry(
    /**
     * Log entry source.
     */
    public val source: String,
    /**
     * Log entry severity.
     */
    public val level: String,
    /**
     * Logged text.
     */
    public val text: String,
    /**
     * Timestamp when this entry was added.
     */
    public val timestamp: Double,
    /**
     * URL of the resource if known.
     */
    public val url: String? = null,
    /**
     * Line number in the resource.
     */
    public val lineNumber: Int? = null,
    /**
     * JavaScript stack trace.
     */
    public val stackTrace: Runtime.StackTrace? = null,
    /**
     * Identifier of the network request associated with this entry.
     */
    public val networkRequestId: String? = null,
    /**
     * Identifier of the worker associated with this entry.
     */
    public val workerId: String? = null,
    /**
     * Call arguments.
     */
    public val args: List<Runtime.RemoteObject>? = null
  )

  /**
   * Violation configuration setting.
   */
  @Serializable
  public data class ViolationSetting(
    /**
     * Violation type.
     */
    public val name: String,
    /**
     * Time threshold to trigger upon.
     */
    public val threshold: Double
  )

  /**
   * Issued when new message was logged.
   */
  public data class EntryAddedParameter(
    /**
     * The entry.
     */
    public val entry: LogEntry
  )

  @Serializable
  public data class StartViolationsReportParameter(
    /**
     * Configuration for violations.
     */
    public val config: List<ViolationSetting>
  )
}
