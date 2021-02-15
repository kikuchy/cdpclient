package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.indexedDB: IndexedDB
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(IndexedDB(this))

public class IndexedDB(
  private val client: CDPClient
) : Domain {
  /**
   * Clears all entries from an object store.
   */
  @ExperimentalCoroutinesApi
  public suspend fun clearObjectStore(args: ClearObjectStoreParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("IndexedDB.clearObjectStore", parameter)
  }

  public suspend fun clearObjectStore(
    securityOrigin: String,
    databaseName: String,
    objectStoreName: String
  ): Unit {
    val parameter = ClearObjectStoreParameter(securityOrigin = securityOrigin,databaseName =
        databaseName,objectStoreName = objectStoreName)
    clearObjectStore(parameter)
  }

  /**
   * Deletes a database.
   */
  @ExperimentalCoroutinesApi
  public suspend fun deleteDatabase(args: DeleteDatabaseParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("IndexedDB.deleteDatabase", parameter)
  }

  public suspend fun deleteDatabase(securityOrigin: String, databaseName: String): Unit {
    val parameter = DeleteDatabaseParameter(securityOrigin = securityOrigin,databaseName =
        databaseName)
    deleteDatabase(parameter)
  }

  /**
   * Delete a range of entries from an object store
   */
  @ExperimentalCoroutinesApi
  public suspend fun deleteObjectStoreEntries(args: DeleteObjectStoreEntriesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("IndexedDB.deleteObjectStoreEntries", parameter)
  }

  public suspend fun deleteObjectStoreEntries(
    securityOrigin: String,
    databaseName: String,
    objectStoreName: String,
    keyRange: KeyRange
  ): Unit {
    val parameter = DeleteObjectStoreEntriesParameter(securityOrigin = securityOrigin,databaseName =
        databaseName,objectStoreName = objectStoreName,keyRange = keyRange)
    deleteObjectStoreEntries(parameter)
  }

  /**
   * Disables events from backend.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("IndexedDB.disable", parameter)
  }

  /**
   * Enables events from backend.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("IndexedDB.enable", parameter)
  }

  /**
   * Requests data from object store or index.
   */
  @ExperimentalCoroutinesApi
  public suspend fun requestData(args: RequestDataParameter): RequestDataReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("IndexedDB.requestData", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun requestData(
    securityOrigin: String,
    databaseName: String,
    objectStoreName: String,
    indexName: String,
    skipCount: Int,
    pageSize: Int,
    keyRange: KeyRange? = null
  ): RequestDataReturn {
    val parameter = RequestDataParameter(securityOrigin = securityOrigin,databaseName =
        databaseName,objectStoreName = objectStoreName,indexName = indexName,skipCount =
        skipCount,pageSize = pageSize,keyRange = keyRange)
    return requestData(parameter)
  }

  /**
   * Gets metadata of an object store
   */
  @ExperimentalCoroutinesApi
  public suspend fun getMetadata(args: GetMetadataParameter): GetMetadataReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("IndexedDB.getMetadata", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getMetadata(
    securityOrigin: String,
    databaseName: String,
    objectStoreName: String
  ): GetMetadataReturn {
    val parameter = GetMetadataParameter(securityOrigin = securityOrigin,databaseName =
        databaseName,objectStoreName = objectStoreName)
    return getMetadata(parameter)
  }

  /**
   * Requests database with given name in given frame.
   */
  @ExperimentalCoroutinesApi
  public suspend fun requestDatabase(args: RequestDatabaseParameter): RequestDatabaseReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("IndexedDB.requestDatabase", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun requestDatabase(securityOrigin: String, databaseName: String):
      RequestDatabaseReturn {
    val parameter = RequestDatabaseParameter(securityOrigin = securityOrigin,databaseName =
        databaseName)
    return requestDatabase(parameter)
  }

  /**
   * Requests database names for given security origin.
   */
  @ExperimentalCoroutinesApi
  public suspend fun requestDatabaseNames(args: RequestDatabaseNamesParameter):
      RequestDatabaseNamesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("IndexedDB.requestDatabaseNames", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun requestDatabaseNames(securityOrigin: String): RequestDatabaseNamesReturn {
    val parameter = RequestDatabaseNamesParameter(securityOrigin = securityOrigin)
    return requestDatabaseNames(parameter)
  }

  /**
   * Database with an array of object stores.
   */
  @Serializable
  public class DatabaseWithObjectStores(
    /**
     * Database name.
     */
    public val name: String,
    /**
     * Database version (type is not 'integer', as the standard
     * requires the version number to be 'unsigned long long')
     */
    public val version: Double,
    /**
     * Object stores in this database.
     */
    public val objectStores: List<ObjectStore>
  )

  /**
   * Object store.
   */
  @Serializable
  public class ObjectStore(
    /**
     * Object store name.
     */
    public val name: String,
    /**
     * Object store key path.
     */
    public val keyPath: KeyPath,
    /**
     * If true, object store has auto increment flag set.
     */
    public val autoIncrement: Boolean,
    /**
     * Indexes in this object store.
     */
    public val indexes: List<ObjectStoreIndex>
  )

  /**
   * Object store index.
   */
  @Serializable
  public class ObjectStoreIndex(
    /**
     * Index name.
     */
    public val name: String,
    /**
     * Index key path.
     */
    public val keyPath: KeyPath,
    /**
     * If true, index is unique.
     */
    public val unique: Boolean,
    /**
     * If true, index allows multiple entries for a key.
     */
    public val multiEntry: Boolean
  )

  /**
   * Key.
   */
  @Serializable
  public class Key(
    /**
     * Key type.
     */
    public val type: String,
    /**
     * Number value.
     */
    public val number: Double? = null,
    /**
     * String value.
     */
    public val string: String? = null,
    /**
     * Date value.
     */
    public val date: Double? = null,
    /**
     * Array value.
     */
    public val array: List<Key>? = null
  )

  /**
   * Key range.
   */
  @Serializable
  public class KeyRange(
    /**
     * Lower bound.
     */
    public val lower: Key? = null,
    /**
     * Upper bound.
     */
    public val upper: Key? = null,
    /**
     * If true lower bound is open.
     */
    public val lowerOpen: Boolean,
    /**
     * If true upper bound is open.
     */
    public val upperOpen: Boolean
  )

  /**
   * Data entry.
   */
  @Serializable
  public class DataEntry(
    /**
     * Key object.
     */
    public val key: Runtime.RemoteObject,
    /**
     * Primary key object.
     */
    public val primaryKey: Runtime.RemoteObject,
    /**
     * Value object.
     */
    public val value: Runtime.RemoteObject
  )

  /**
   * Key path.
   */
  @Serializable
  public class KeyPath(
    /**
     * Key path type.
     */
    public val type: String,
    /**
     * String value.
     */
    public val string: String? = null,
    /**
     * Array value.
     */
    public val array: String? = null
  )

  @Serializable
  public data class ClearObjectStoreParameter(
    /**
     * Security origin.
     */
    public val securityOrigin: String,
    /**
     * Database name.
     */
    public val databaseName: String,
    /**
     * Object store name.
     */
    public val objectStoreName: String
  )

  @Serializable
  public data class DeleteDatabaseParameter(
    /**
     * Security origin.
     */
    public val securityOrigin: String,
    /**
     * Database name.
     */
    public val databaseName: String
  )

  @Serializable
  public data class DeleteObjectStoreEntriesParameter(
    public val securityOrigin: String,
    public val databaseName: String,
    public val objectStoreName: String,
    /**
     * Range of entry keys to delete
     */
    public val keyRange: KeyRange
  )

  @Serializable
  public data class RequestDataParameter(
    /**
     * Security origin.
     */
    public val securityOrigin: String,
    /**
     * Database name.
     */
    public val databaseName: String,
    /**
     * Object store name.
     */
    public val objectStoreName: String,
    /**
     * Index name, empty string for object store data requests.
     */
    public val indexName: String,
    /**
     * Number of records to skip.
     */
    public val skipCount: Int,
    /**
     * Number of records to fetch.
     */
    public val pageSize: Int,
    /**
     * Key range.
     */
    public val keyRange: KeyRange? = null
  )

  @Serializable
  public data class RequestDataReturn(
    /**
     * Array of object store data entries.
     */
    public val objectStoreDataEntries: List<DataEntry>,
    /**
     * If true, there are more entries to fetch in the given range.
     */
    public val hasMore: Boolean
  )

  @Serializable
  public data class GetMetadataParameter(
    /**
     * Security origin.
     */
    public val securityOrigin: String,
    /**
     * Database name.
     */
    public val databaseName: String,
    /**
     * Object store name.
     */
    public val objectStoreName: String
  )

  @Serializable
  public data class GetMetadataReturn(
    /**
     * the entries count
     */
    public val entriesCount: Double,
    /**
     * the current value of key generator, to become the next inserted
     * key into the object store. Valid if objectStore.autoIncrement
     * is true.
     */
    public val keyGeneratorValue: Double
  )

  @Serializable
  public data class RequestDatabaseParameter(
    /**
     * Security origin.
     */
    public val securityOrigin: String,
    /**
     * Database name.
     */
    public val databaseName: String
  )

  @Serializable
  public data class RequestDatabaseReturn(
    /**
     * Database with an array of object stores.
     */
    public val databaseWithObjectStores: DatabaseWithObjectStores
  )

  @Serializable
  public data class RequestDatabaseNamesParameter(
    /**
     * Security origin.
     */
    public val securityOrigin: String
  )

  @Serializable
  public data class RequestDatabaseNamesReturn(
    /**
     * Database names for origin.
     */
    public val databaseNames: String
  )
}
