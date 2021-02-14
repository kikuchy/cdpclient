package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
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

public val CDPClient.applicationCache: ApplicationCache
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(ApplicationCache(this))

public class ApplicationCache(
  private val client: CDPClient
) : Domain {
  public val applicationCacheStatusUpdated: Flow<ApplicationCacheStatusUpdatedParameter> =
      client.events.filter {
          it.method == "applicationCacheStatusUpdated"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val networkStateUpdated: Flow<NetworkStateUpdatedParameter> = client.events.filter {
          it.method == "networkStateUpdated"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  /**
   * Enables application cache domain notifications.
   */
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("ApplicationCache.enable", parameter)
  }

  /**
   * Returns relevant application cache data for the document in given frame.
   */
  public suspend fun getApplicationCacheForFrame(args: GetApplicationCacheForFrameParameter):
      GetApplicationCacheForFrameReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("ApplicationCache.getApplicationCacheForFrame", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getApplicationCacheForFrame(frameId: String):
      GetApplicationCacheForFrameReturn {
    val parameter = GetApplicationCacheForFrameParameter(frameId = frameId)
    return getApplicationCacheForFrame(parameter)
  }

  /**
   * Returns array of frame identifiers with manifest urls for each frame containing a document
   * associated with some application cache.
   */
  public suspend fun getFramesWithManifests(): GetFramesWithManifestsReturn {
    val parameter = null
    val result = client.callCommand("ApplicationCache.getFramesWithManifests", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns manifest URL for document in the given frame.
   */
  public suspend fun getManifestForFrame(args: GetManifestForFrameParameter):
      GetManifestForFrameReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("ApplicationCache.getManifestForFrame", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getManifestForFrame(frameId: String): GetManifestForFrameReturn {
    val parameter = GetManifestForFrameParameter(frameId = frameId)
    return getManifestForFrame(parameter)
  }

  /**
   * Detailed application cache resource information.
   */
  @Serializable
  public class ApplicationCacheResource(
    /**
     * Resource url.
     */
    public val url: String,
    /**
     * Resource size.
     */
    public val size: Int,
    /**
     * Resource type.
     */
    public val type: String
  )

  /**
   * Detailed application cache information.
   */
  @Serializable
  public class ApplicationCache(
    /**
     * Manifest URL.
     */
    public val manifestURL: String,
    /**
     * Application cache size.
     */
    public val size: Double,
    /**
     * Application cache creation time.
     */
    public val creationTime: Double,
    /**
     * Application cache update time.
     */
    public val updateTime: Double,
    /**
     * Application cache resources.
     */
    public val resources: List<ApplicationCacheResource>
  )

  /**
   * Frame identifier - manifest URL pair.
   */
  @Serializable
  public class FrameWithManifest(
    /**
     * Frame identifier.
     */
    public val frameId: String,
    /**
     * Manifest URL.
     */
    public val manifestURL: String,
    /**
     * Application cache status.
     */
    public val status: Int
  )

  public class ApplicationCacheStatusUpdatedParameter(
    /**
     * Identifier of the frame containing document whose application cache updated status.
     */
    public val frameId: String,
    /**
     * Manifest URL.
     */
    public val manifestURL: String,
    /**
     * Updated application cache status.
     */
    public val status: Int
  )

  public class NetworkStateUpdatedParameter(
    public val isNowOnline: Boolean
  )

  @Serializable
  public data class GetApplicationCacheForFrameParameter(
    /**
     * Identifier of the frame containing document whose application cache is retrieved.
     */
    public val frameId: String
  )

  @Serializable
  public data class GetApplicationCacheForFrameReturn(
    /**
     * Relevant application cache data for the document in given frame.
     */
    public val applicationCache: ApplicationCache
  )

  @Serializable
  public data class GetFramesWithManifestsReturn(
    /**
     * Array of frame identifiers with manifest urls for each frame containing a document
     * associated with some application cache.
     */
    public val frameIds: List<FrameWithManifest>
  )

  @Serializable
  public data class GetManifestForFrameParameter(
    /**
     * Identifier of the frame containing document whose manifest is retrieved.
     */
    public val frameId: String
  )

  @Serializable
  public data class GetManifestForFrameReturn(
    /**
     * Manifest URL for document in the given frame.
     */
    public val manifestURL: String
  )
}