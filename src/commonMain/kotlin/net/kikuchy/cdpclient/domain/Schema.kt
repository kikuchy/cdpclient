package net.kikuchy.cdpclient.domain

import kotlin.String
import kotlin.collections.List
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

public val CDPClient.schema: Schema
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Schema(this))

/**
 * This domain is deprecated.
 */
public class Schema(
  private val client: CDPClient
) : Domain {
  /**
   * Returns supported domains.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getDomains(): GetDomainsReturn {
    val parameter = null
    val result = client.callCommand("Schema.getDomains", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Description of the protocol domain.
   */
  @Serializable
  public data class Domain(
    /**
     * Domain name.
     */
    public val name: String,
    /**
     * Domain version.
     */
    public val version: String
  )

  @Serializable
  public data class GetDomainsReturn(
    /**
     * List of supported domains.
     */
    public val domains: List<Domain>
  )
}
