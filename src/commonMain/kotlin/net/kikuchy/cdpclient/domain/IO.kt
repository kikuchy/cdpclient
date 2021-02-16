package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit
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

public val CDPClient.io: IO
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(IO(this))

/**
 * Input/Output operations for streams produced by DevTools.
 */
public class IO(
  private val client: CDPClient
) : Domain {
  /**
   * Close the stream, discard any temporary backing storage.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun close(args: CloseParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("IO.close", parameter)
  }

  /**
   * Close the stream, discard any temporary backing storage.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun close(handle: String): Unit {
    val parameter = CloseParameter(handle = handle)
    close(parameter)
  }

  /**
   * Read a chunk of the stream
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun read(args: ReadParameter): ReadReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("IO.read", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Read a chunk of the stream
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun read(
    handle: String,
    offset: Int? = null,
    size: Int? = null
  ): ReadReturn {
    val parameter = ReadParameter(handle = handle,offset = offset,size = size)
    return read(parameter)
  }

  /**
   * Return UUID of Blob object specified by a remote object id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun resolveBlob(args: ResolveBlobParameter): ResolveBlobReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("IO.resolveBlob", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Return UUID of Blob object specified by a remote object id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun resolveBlob(objectId: String): ResolveBlobReturn {
    val parameter = ResolveBlobParameter(objectId = objectId)
    return resolveBlob(parameter)
  }

  @Serializable
  public data class CloseParameter(
    /**
     * Handle of the stream to close.
     */
    public val handle: String
  )

  @Serializable
  public data class ReadParameter(
    /**
     * Handle of the stream to read.
     */
    public val handle: String,
    /**
     * Seek to the specified offset before reading (if not specificed, proceed with offset
     * following the last read). Some types of streams may only support sequential reads.
     */
    public val offset: Int? = null,
    /**
     * Maximum number of bytes to read (left upon the agent discretion if not specified).
     */
    public val size: Int? = null
  )

  @Serializable
  public data class ReadReturn(
    /**
     * Set if the data is base64-encoded
     */
    public val base64Encoded: Boolean?,
    /**
     * Data that were read.
     */
    public val `data`: String,
    /**
     * Set if the end-of-file condition occured while reading.
     */
    public val eof: Boolean
  )

  @Serializable
  public data class ResolveBlobParameter(
    /**
     * Object id of a Blob object wrapper.
     */
    public val objectId: String
  )

  @Serializable
  public data class ResolveBlobReturn(
    /**
     * UUID of the specified Blob.
     */
    public val uuid: String
  )
}
