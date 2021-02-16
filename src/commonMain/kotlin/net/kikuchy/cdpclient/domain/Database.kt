package net.kikuchy.cdpclient.domain

import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.database: Database
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Database(this))

public class Database(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val addDatabase: Flow<AddDatabaseParameter> = client
          .events
          .filter {
              it.method == "addDatabase"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Disables database tracking, prevents database events from being sent to the client.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Database.disable", parameter)
  }

  /**
   * Enables database tracking, database events will now be delivered to the client.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Database.enable", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun executeSQL(args: ExecuteSQLParameter): ExecuteSQLReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Database.executeSQL", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun executeSQL(databaseId: String, query: String): ExecuteSQLReturn {
    val parameter = ExecuteSQLParameter(databaseId = databaseId,query = query)
    return executeSQL(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getDatabaseTableNames(args: GetDatabaseTableNamesParameter):
      GetDatabaseTableNamesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Database.getDatabaseTableNames", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getDatabaseTableNames(databaseId: String): GetDatabaseTableNamesReturn {
    val parameter = GetDatabaseTableNamesParameter(databaseId = databaseId)
    return getDatabaseTableNames(parameter)
  }

  /**
   * Database object.
   */
  @Serializable
  public data class Database(
    /**
     * Database ID.
     */
    public val id: String,
    /**
     * Database domain.
     */
    public val domain: String,
    /**
     * Database name.
     */
    public val name: String,
    /**
     * Database version.
     */
    public val version: String
  )

  /**
   * Database error.
   */
  @Serializable
  public data class Error(
    /**
     * Error message.
     */
    public val message: String,
    /**
     * Error code.
     */
    public val code: Int
  )

  public data class AddDatabaseParameter(
    public val database: Database
  )

  @Serializable
  public data class ExecuteSQLParameter(
    public val databaseId: String,
    public val query: String
  )

  @Serializable
  public data class ExecuteSQLReturn(
    public val columnNames: String?,
    public val values: JsonElement?,
    public val sqlError: Error?
  )

  @Serializable
  public data class GetDatabaseTableNamesParameter(
    public val databaseId: String
  )

  @Serializable
  public data class GetDatabaseTableNamesReturn(
    public val tableNames: String
  )
}
