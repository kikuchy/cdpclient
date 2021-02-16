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

public val CDPClient.storage: Storage
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Storage(this))

public class Storage(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val cacheStorageContentUpdated: Flow<CacheStorageContentUpdatedParameter> = client
          .events
          .filter {
              it.method == "cacheStorageContentUpdated"
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
  public val cacheStorageListUpdated: Flow<CacheStorageListUpdatedParameter> = client
          .events
          .filter {
              it.method == "cacheStorageListUpdated"
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
  public val indexedDBContentUpdated: Flow<IndexedDBContentUpdatedParameter> = client
          .events
          .filter {
              it.method == "indexedDBContentUpdated"
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
  public val indexedDBListUpdated: Flow<IndexedDBListUpdatedParameter> = client
          .events
          .filter {
              it.method == "indexedDBListUpdated"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Clears storage for origin.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clearDataForOrigin(args: ClearDataForOriginParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Storage.clearDataForOrigin", parameter)
  }

  /**
   * Clears storage for origin.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clearDataForOrigin(origin: String, storageTypes: String): Unit {
    val parameter = ClearDataForOriginParameter(origin = origin, storageTypes = storageTypes)
    clearDataForOrigin(parameter)
  }

  /**
   * Returns all browser cookies.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getCookies(args: GetCookiesParameter): GetCookiesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Storage.getCookies", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns all browser cookies.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getCookies(browserContextId: String? = null): GetCookiesReturn {
    val parameter = GetCookiesParameter(browserContextId = browserContextId)
    return getCookies(parameter)
  }

  /**
   * Sets given cookies.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setCookies(args: SetCookiesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Storage.setCookies", parameter)
  }

  /**
   * Sets given cookies.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setCookies(cookies: List<Network.CookieParam>, browserContextId: String? =
      null): Unit {
    val parameter = SetCookiesParameter(cookies = cookies, browserContextId = browserContextId)
    setCookies(parameter)
  }

  /**
   * Clears cookies.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clearCookies(args: ClearCookiesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Storage.clearCookies", parameter)
  }

  /**
   * Clears cookies.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clearCookies(browserContextId: String? = null): Unit {
    val parameter = ClearCookiesParameter(browserContextId = browserContextId)
    clearCookies(parameter)
  }

  /**
   * Returns usage and quota in bytes.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getUsageAndQuota(args: GetUsageAndQuotaParameter): GetUsageAndQuotaReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Storage.getUsageAndQuota", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns usage and quota in bytes.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getUsageAndQuota(origin: String): GetUsageAndQuotaReturn {
    val parameter = GetUsageAndQuotaParameter(origin = origin)
    return getUsageAndQuota(parameter)
  }

  /**
   * Override quota for the specified origin
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun overrideQuotaForOrigin(args: OverrideQuotaForOriginParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Storage.overrideQuotaForOrigin", parameter)
  }

  /**
   * Override quota for the specified origin
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun overrideQuotaForOrigin(origin: String, quotaSize: Double? = null): Unit {
    val parameter = OverrideQuotaForOriginParameter(origin = origin, quotaSize = quotaSize)
    overrideQuotaForOrigin(parameter)
  }

  /**
   * Registers origin to be notified when an update occurs to its cache storage list.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun trackCacheStorageForOrigin(args: TrackCacheStorageForOriginParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Storage.trackCacheStorageForOrigin", parameter)
  }

  /**
   * Registers origin to be notified when an update occurs to its cache storage list.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun trackCacheStorageForOrigin(origin: String): Unit {
    val parameter = TrackCacheStorageForOriginParameter(origin = origin)
    trackCacheStorageForOrigin(parameter)
  }

  /**
   * Registers origin to be notified when an update occurs to its IndexedDB.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun trackIndexedDBForOrigin(args: TrackIndexedDBForOriginParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Storage.trackIndexedDBForOrigin", parameter)
  }

  /**
   * Registers origin to be notified when an update occurs to its IndexedDB.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun trackIndexedDBForOrigin(origin: String): Unit {
    val parameter = TrackIndexedDBForOriginParameter(origin = origin)
    trackIndexedDBForOrigin(parameter)
  }

  /**
   * Unregisters origin from receiving notifications for cache storage.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun untrackCacheStorageForOrigin(args: UntrackCacheStorageForOriginParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Storage.untrackCacheStorageForOrigin", parameter)
  }

  /**
   * Unregisters origin from receiving notifications for cache storage.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun untrackCacheStorageForOrigin(origin: String): Unit {
    val parameter = UntrackCacheStorageForOriginParameter(origin = origin)
    untrackCacheStorageForOrigin(parameter)
  }

  /**
   * Unregisters origin from receiving notifications for IndexedDB.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun untrackIndexedDBForOrigin(args: UntrackIndexedDBForOriginParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Storage.untrackIndexedDBForOrigin", parameter)
  }

  /**
   * Unregisters origin from receiving notifications for IndexedDB.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun untrackIndexedDBForOrigin(origin: String): Unit {
    val parameter = UntrackIndexedDBForOriginParameter(origin = origin)
    untrackIndexedDBForOrigin(parameter)
  }

  /**
   * Returns the number of stored Trust Tokens per issuer for the
   * current browsing context.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getTrustTokens(): GetTrustTokensReturn {
    val parameter = null
    val result = client.callCommand("Storage.getTrustTokens", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Enum of possible storage types.
   */
  @Serializable
  public enum class StorageType {
    @SerialName("appcache")
    APPCACHE,
    @SerialName("cookies")
    COOKIES,
    @SerialName("file_systems")
    FILE_SYSTEMS,
    @SerialName("indexeddb")
    INDEXEDDB,
    @SerialName("local_storage")
    LOCAL_STORAGE,
    @SerialName("shader_cache")
    SHADER_CACHE,
    @SerialName("websql")
    WEBSQL,
    @SerialName("service_workers")
    SERVICE_WORKERS,
    @SerialName("cache_storage")
    CACHE_STORAGE,
    @SerialName("all")
    ALL,
    @SerialName("other")
    OTHER,
  }

  /**
   * Usage for a storage type.
   */
  @Serializable
  public data class UsageForType(
    /**
     * Name of storage type.
     */
    public val storageType: StorageType,
    /**
     * Storage usage (bytes).
     */
    public val usage: Double
  )

  /**
   * Pair of issuer origin and number of available (signed, but not used) Trust
   * Tokens from that issuer.
   */
  @Serializable
  public data class TrustTokens(
    public val issuerOrigin: String,
    public val count: Double
  )

  /**
   * A cache's contents have been modified.
   */
  public data class CacheStorageContentUpdatedParameter(
    /**
     * Origin to update.
     */
    public val origin: String,
    /**
     * Name of cache in origin.
     */
    public val cacheName: String
  )

  /**
   * A cache has been added/deleted.
   */
  public data class CacheStorageListUpdatedParameter(
    /**
     * Origin to update.
     */
    public val origin: String
  )

  /**
   * The origin's IndexedDB object store has been modified.
   */
  public data class IndexedDBContentUpdatedParameter(
    /**
     * Origin to update.
     */
    public val origin: String,
    /**
     * Database to update.
     */
    public val databaseName: String,
    /**
     * ObjectStore to update.
     */
    public val objectStoreName: String
  )

  /**
   * The origin's IndexedDB database list has been modified.
   */
  public data class IndexedDBListUpdatedParameter(
    /**
     * Origin to update.
     */
    public val origin: String
  )

  @Serializable
  public data class ClearDataForOriginParameter(
    /**
     * Security origin.
     */
    public val origin: String,
    /**
     * Comma separated list of StorageType to clear.
     */
    public val storageTypes: String
  )

  @Serializable
  public data class GetCookiesParameter(
    /**
     * Browser context to use when called on the browser endpoint.
     */
    public val browserContextId: String? = null
  )

  @Serializable
  public data class GetCookiesReturn(
    /**
     * Array of cookie objects.
     */
    public val cookies: List<Network.Cookie>
  )

  @Serializable
  public data class SetCookiesParameter(
    /**
     * Cookies to be set.
     */
    public val cookies: List<Network.CookieParam>,
    /**
     * Browser context to use when called on the browser endpoint.
     */
    public val browserContextId: String? = null
  )

  @Serializable
  public data class ClearCookiesParameter(
    /**
     * Browser context to use when called on the browser endpoint.
     */
    public val browserContextId: String? = null
  )

  @Serializable
  public data class GetUsageAndQuotaParameter(
    /**
     * Security origin.
     */
    public val origin: String
  )

  @Serializable
  public data class GetUsageAndQuotaReturn(
    /**
     * Storage usage (bytes).
     */
    public val usage: Double,
    /**
     * Storage quota (bytes).
     */
    public val quota: Double,
    /**
     * Whether or not the origin has an active storage quota override
     */
    public val overrideActive: Boolean,
    /**
     * Storage usage per type (bytes).
     */
    public val usageBreakdown: List<UsageForType>
  )

  @Serializable
  public data class OverrideQuotaForOriginParameter(
    /**
     * Security origin.
     */
    public val origin: String,
    /**
     * The quota size (in bytes) to override the original quota with.
     * If this is called multiple times, the overriden quota will be equal to
     * the quotaSize provided in the final call. If this is called without
     * specifying a quotaSize, the quota will be reset to the default value for
     * the specified origin. If this is called multiple times with different
     * origins, the override will be maintained for each origin until it is
     * disabled (called without a quotaSize).
     */
    public val quotaSize: Double? = null
  )

  @Serializable
  public data class TrackCacheStorageForOriginParameter(
    /**
     * Security origin.
     */
    public val origin: String
  )

  @Serializable
  public data class TrackIndexedDBForOriginParameter(
    /**
     * Security origin.
     */
    public val origin: String
  )

  @Serializable
  public data class UntrackCacheStorageForOriginParameter(
    /**
     * Security origin.
     */
    public val origin: String
  )

  @Serializable
  public data class UntrackIndexedDBForOriginParameter(
    /**
     * Security origin.
     */
    public val origin: String
  )

  @Serializable
  public data class GetTrustTokensReturn(
    public val tokens: List<TrustTokens>
  )
}
