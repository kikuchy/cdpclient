package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.fetch: Fetch
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Fetch(this))

/**
 * A domain for letting clients substitute browser's network layer with client code.
 */
public class Fetch(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val requestPaused: Flow<RequestPausedParameter> = client
          .events
          .filter {
              it.method == "requestPaused"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val authRequired: Flow<AuthRequiredParameter> = client
          .events
          .filter {
              it.method == "authRequired"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Disables the fetch domain.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Fetch.disable", parameter)
  }

  /**
   * Enables issuing of requestPaused events. A request will be paused until client
   * calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(args: EnableParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Fetch.enable", parameter)
  }

  public suspend fun enable(patterns: List<RequestPattern>? = null, handleAuthRequests: Boolean? =
      null): Unit {
    val parameter = EnableParameter(patterns = patterns,handleAuthRequests = handleAuthRequests)
    enable(parameter)
  }

  /**
   * Causes the request to fail with specified reason.
   */
  @ExperimentalCoroutinesApi
  public suspend fun failRequest(args: FailRequestParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Fetch.failRequest", parameter)
  }

  public suspend fun failRequest(requestId: String, errorReason: Network.ErrorReason): Unit {
    val parameter = FailRequestParameter(requestId = requestId,errorReason = errorReason)
    failRequest(parameter)
  }

  /**
   * Provides response to the request.
   */
  @ExperimentalCoroutinesApi
  public suspend fun fulfillRequest(args: FulfillRequestParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Fetch.fulfillRequest", parameter)
  }

  public suspend fun fulfillRequest(
    requestId: String,
    responseCode: Int,
    responseHeaders: List<HeaderEntry>? = null,
    binaryResponseHeaders: String? = null,
    body: String? = null,
    responsePhrase: String? = null
  ): Unit {
    val parameter = FulfillRequestParameter(requestId = requestId,responseCode =
        responseCode,responseHeaders = responseHeaders,binaryResponseHeaders =
        binaryResponseHeaders,body = body,responsePhrase = responsePhrase)
    fulfillRequest(parameter)
  }

  /**
   * Continues the request, optionally modifying some of its parameters.
   */
  @ExperimentalCoroutinesApi
  public suspend fun continueRequest(args: ContinueRequestParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Fetch.continueRequest", parameter)
  }

  public suspend fun continueRequest(
    requestId: String,
    url: String? = null,
    method: String? = null,
    postData: String? = null,
    headers: List<HeaderEntry>? = null
  ): Unit {
    val parameter = ContinueRequestParameter(requestId = requestId,url = url,method =
        method,postData = postData,headers = headers)
    continueRequest(parameter)
  }

  /**
   * Continues a request supplying authChallengeResponse following authRequired event.
   */
  @ExperimentalCoroutinesApi
  public suspend fun continueWithAuth(args: ContinueWithAuthParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Fetch.continueWithAuth", parameter)
  }

  public suspend fun continueWithAuth(requestId: String,
      authChallengeResponse: AuthChallengeResponse): Unit {
    val parameter = ContinueWithAuthParameter(requestId = requestId,authChallengeResponse =
        authChallengeResponse)
    continueWithAuth(parameter)
  }

  /**
   * Causes the body of the response to be received from the server and
   * returned as a single string. May only be issued for a request that
   * is paused in the Response stage and is mutually exclusive with
   * takeResponseBodyForInterceptionAsStream. Calling other methods that
   * affect the request or disabling fetch domain before body is received
   * results in an undefined behavior.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getResponseBody(args: GetResponseBodyParameter): GetResponseBodyReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Fetch.getResponseBody", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getResponseBody(requestId: String): GetResponseBodyReturn {
    val parameter = GetResponseBodyParameter(requestId = requestId)
    return getResponseBody(parameter)
  }

  /**
   * Returns a handle to the stream representing the response body.
   * The request must be paused in the HeadersReceived stage.
   * Note that after this command the request can't be continued
   * as is -- client either needs to cancel it or to provide the
   * response body.
   * The stream only supports sequential read, IO.read will fail if the position
   * is specified.
   * This method is mutually exclusive with getResponseBody.
   * Calling other methods that affect the request or disabling fetch
   * domain before body is received results in an undefined behavior.
   */
  @ExperimentalCoroutinesApi
  public suspend fun takeResponseBodyAsStream(args: TakeResponseBodyAsStreamParameter):
      TakeResponseBodyAsStreamReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Fetch.takeResponseBodyAsStream", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun takeResponseBodyAsStream(requestId: String): TakeResponseBodyAsStreamReturn {
    val parameter = TakeResponseBodyAsStreamParameter(requestId = requestId)
    return takeResponseBodyAsStream(parameter)
  }

  /**
   * Stages of the request to handle. Request will intercept before the request is
   * sent. Response will intercept after the response is received (but before response
   * body is received.
   */
  @Serializable
  public enum class RequestStage {
    @SerialName("Request")
    REQUEST,
    @SerialName("Response")
    RESPONSE,
  }

  @Serializable
  public class RequestPattern(
    /**
     * Wildcards ('*' -> zero or more, '?' -> exactly one) are allowed. Escape character is
     * backslash. Omitting is equivalent to "*".
     */
    public val urlPattern: String? = null,
    /**
     * If set, only requests for matching resource types will be intercepted.
     */
    public val resourceType: Network.ResourceType? = null,
    /**
     * Stage at wich to begin intercepting requests. Default is Request.
     */
    public val requestStage: RequestStage? = null
  )

  /**
   * Response HTTP header entry
   */
  @Serializable
  public class HeaderEntry(
    public val name: String,
    public val value: String
  )

  /**
   * Authorization challenge for HTTP status code 401 or 407.
   */
  @Serializable
  public class AuthChallenge(
    /**
     * Source of the authentication challenge.
     */
    public val source: String? = null,
    /**
     * Origin of the challenger.
     */
    public val origin: String,
    /**
     * The authentication scheme used, such as basic or digest
     */
    public val scheme: String,
    /**
     * The realm of the challenge. May be empty.
     */
    public val realm: String
  )

  /**
   * Response to an AuthChallenge.
   */
  @Serializable
  public class AuthChallengeResponse(
    /**
     * The decision on what to do in response to the authorization challenge.  Default means
     * deferring to the default behavior of the net stack, which will likely either the Cancel
     * authentication or display a popup dialog box.
     */
    public val response: String,
    /**
     * The username to provide, possibly empty. Should only be set if response is
     * ProvideCredentials.
     */
    public val username: String? = null,
    /**
     * The password to provide, possibly empty. Should only be set if response is
     * ProvideCredentials.
     */
    public val password: String? = null
  )

  /**
   * Issued when the domain is enabled and the request URL matches the
   * specified filter. The request is paused until the client responds
   * with one of continueRequest, failRequest or fulfillRequest.
   * The stage of the request can be determined by presence of responseErrorReason
   * and responseStatusCode -- the request is at the response stage if either
   * of these fields is present and in the request stage otherwise.
   */
  public data class RequestPausedParameter(
    /**
     * Each request the page makes will have a unique id.
     */
    public val requestId: String,
    /**
     * The details of the request.
     */
    public val request: Network.Request,
    /**
     * The id of the frame that initiated the request.
     */
    public val frameId: String,
    /**
     * How the requested resource will be used.
     */
    public val resourceType: Network.ResourceType,
    /**
     * Response error if intercepted at response stage.
     */
    public val responseErrorReason: Network.ErrorReason? = null,
    /**
     * Response code if intercepted at response stage.
     */
    public val responseStatusCode: Int? = null,
    /**
     * Response headers if intercepted at the response stage.
     */
    public val responseHeaders: List<HeaderEntry>? = null,
    /**
     * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it,
     * then this networkId will be the same as the requestId present in the requestWillBeSent event.
     */
    public val networkId: String? = null
  )

  /**
   * Issued when the domain is enabled with handleAuthRequests set to true.
   * The request is paused until client responds with continueWithAuth.
   */
  public data class AuthRequiredParameter(
    /**
     * Each request the page makes will have a unique id.
     */
    public val requestId: String,
    /**
     * The details of the request.
     */
    public val request: Network.Request,
    /**
     * The id of the frame that initiated the request.
     */
    public val frameId: String,
    /**
     * How the requested resource will be used.
     */
    public val resourceType: Network.ResourceType,
    /**
     * Details of the Authorization Challenge encountered.
     * If this is set, client should respond with continueRequest that
     * contains AuthChallengeResponse.
     */
    public val authChallenge: AuthChallenge
  )

  @Serializable
  public data class EnableParameter(
    /**
     * If specified, only requests matching any of these patterns will produce
     * fetchRequested event and will be paused until clients response. If not set,
     * all requests will be affected.
     */
    public val patterns: List<RequestPattern>? = null,
    /**
     * If true, authRequired events will be issued and requests will be paused
     * expecting a call to continueWithAuth.
     */
    public val handleAuthRequests: Boolean? = null
  )

  @Serializable
  public data class FailRequestParameter(
    /**
     * An id the client received in requestPaused event.
     */
    public val requestId: String,
    /**
     * Causes the request to fail with the given reason.
     */
    public val errorReason: Network.ErrorReason
  )

  @Serializable
  public data class FulfillRequestParameter(
    /**
     * An id the client received in requestPaused event.
     */
    public val requestId: String,
    /**
     * An HTTP response code.
     */
    public val responseCode: Int,
    /**
     * Response headers.
     */
    public val responseHeaders: List<HeaderEntry>? = null,
    /**
     * Alternative way of specifying response headers as a \0-separated
     * series of name: value pairs. Prefer the above method unless you
     * need to represent some non-UTF8 values that can't be transmitted
     * over the protocol as text. (Encoded as a base64 string when passed over JSON)
     */
    public val binaryResponseHeaders: String? = null,
    /**
     * A response body. (Encoded as a base64 string when passed over JSON)
     */
    public val body: String? = null,
    /**
     * A textual representation of responseCode.
     * If absent, a standard phrase matching responseCode is used.
     */
    public val responsePhrase: String? = null
  )

  @Serializable
  public data class ContinueRequestParameter(
    /**
     * An id the client received in requestPaused event.
     */
    public val requestId: String,
    /**
     * If set, the request url will be modified in a way that's not observable by page.
     */
    public val url: String? = null,
    /**
     * If set, the request method is overridden.
     */
    public val method: String? = null,
    /**
     * If set, overrides the post data in the request. (Encoded as a base64 string when passed over
     * JSON)
     */
    public val postData: String? = null,
    /**
     * If set, overrides the request headers.
     */
    public val headers: List<HeaderEntry>? = null
  )

  @Serializable
  public data class ContinueWithAuthParameter(
    /**
     * An id the client received in authRequired event.
     */
    public val requestId: String,
    /**
     * Response to  with an authChallenge.
     */
    public val authChallengeResponse: AuthChallengeResponse
  )

  @Serializable
  public data class GetResponseBodyParameter(
    /**
     * Identifier for the intercepted request to get body for.
     */
    public val requestId: String
  )

  @Serializable
  public data class GetResponseBodyReturn(
    /**
     * Response body.
     */
    public val body: String,
    /**
     * True, if content was sent as base64.
     */
    public val base64Encoded: Boolean
  )

  @Serializable
  public data class TakeResponseBodyAsStreamParameter(
    public val requestId: String
  )

  @Serializable
  public data class TakeResponseBodyAsStreamReturn(
    public val stream: String
  )
}
