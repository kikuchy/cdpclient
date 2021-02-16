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
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.dOMStorage: DOMStorage
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(DOMStorage(this))

/**
 * Query and modify DOM storage.
 */
public class DOMStorage(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val domStorageItemAdded: Flow<DomStorageItemAddedParameter> = client
          .events
          .filter {
              it.method == "domStorageItemAdded"
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
  public val domStorageItemRemoved: Flow<DomStorageItemRemovedParameter> = client
          .events
          .filter {
              it.method == "domStorageItemRemoved"
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
  public val domStorageItemUpdated: Flow<DomStorageItemUpdatedParameter> = client
          .events
          .filter {
              it.method == "domStorageItemUpdated"
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
  public val domStorageItemsCleared: Flow<DomStorageItemsClearedParameter> = client
          .events
          .filter {
              it.method == "domStorageItemsCleared"
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
  public suspend fun clear(args: ClearParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOMStorage.clear", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun clear(storageId: StorageId): Unit {
    val parameter = ClearParameter(storageId = storageId)
    clear(parameter)
  }

  /**
   * Disables storage tracking, prevents storage events from being sent to the client.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("DOMStorage.disable", parameter)
  }

  /**
   * Enables storage tracking, storage events will now be delivered to the client.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("DOMStorage.enable", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getDOMStorageItems(args: GetDOMStorageItemsParameter):
      GetDOMStorageItemsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOMStorage.getDOMStorageItems", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getDOMStorageItems(storageId: StorageId): GetDOMStorageItemsReturn {
    val parameter = GetDOMStorageItemsParameter(storageId = storageId)
    return getDOMStorageItems(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun removeDOMStorageItem(args: RemoveDOMStorageItemParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOMStorage.removeDOMStorageItem", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun removeDOMStorageItem(storageId: StorageId, key: String): Unit {
    val parameter = RemoveDOMStorageItemParameter(storageId = storageId, key = key)
    removeDOMStorageItem(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setDOMStorageItem(args: SetDOMStorageItemParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOMStorage.setDOMStorageItem", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setDOMStorageItem(
    storageId: StorageId,
    key: String,
    value: String
  ): Unit {
    val parameter = SetDOMStorageItemParameter(storageId = storageId, key = key, value = value)
    setDOMStorageItem(parameter)
  }

  /**
   * DOM Storage identifier.
   */
  @Serializable
  public data class StorageId(
    /**
     * Security origin for the storage.
     */
    public val securityOrigin: String,
    /**
     * Whether the storage is local storage (not session storage).
     */
    public val isLocalStorage: Boolean
  )

  public data class DomStorageItemAddedParameter(
    public val storageId: StorageId,
    public val key: String,
    public val newValue: String
  )

  public data class DomStorageItemRemovedParameter(
    public val storageId: StorageId,
    public val key: String
  )

  public data class DomStorageItemUpdatedParameter(
    public val storageId: StorageId,
    public val key: String,
    public val oldValue: String,
    public val newValue: String
  )

  public data class DomStorageItemsClearedParameter(
    public val storageId: StorageId
  )

  @Serializable
  public data class ClearParameter(
    public val storageId: StorageId
  )

  @Serializable
  public data class GetDOMStorageItemsParameter(
    public val storageId: StorageId
  )

  @Serializable
  public data class GetDOMStorageItemsReturn(
    public val entries: List<List<Double>>
  )

  @Serializable
  public data class RemoveDOMStorageItemParameter(
    public val storageId: StorageId,
    public val key: String
  )

  @Serializable
  public data class SetDOMStorageItemParameter(
    public val storageId: StorageId,
    public val key: String,
    public val value: String
  )
}
