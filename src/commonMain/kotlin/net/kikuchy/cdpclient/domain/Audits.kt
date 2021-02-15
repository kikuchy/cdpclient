package net.kikuchy.cdpclient.domain

import kotlin.Boolean
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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.audits: Audits
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Audits(this))

/**
 * Audits domain allows investigation of page violations and possible improvements.
 */
public class Audits(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val issueAdded: Flow<IssueAddedParameter> = client
          .events
          .filter {
              it.method == "issueAdded"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Returns the response body and size if it were re-encoded with the specified settings. Only
   * applies to images.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getEncodedResponse(args: GetEncodedResponseParameter):
      GetEncodedResponseReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Audits.getEncodedResponse", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getEncodedResponse(
    requestId: String,
    encoding: String,
    quality: Double? = null,
    sizeOnly: Boolean? = null
  ): GetEncodedResponseReturn {
    val parameter = GetEncodedResponseParameter(requestId = requestId,encoding = encoding,quality =
        quality,sizeOnly = sizeOnly)
    return getEncodedResponse(parameter)
  }

  /**
   * Disables issues domain, prevents further issues from being reported to the client.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Audits.disable", parameter)
  }

  /**
   * Enables issues domain, sends the issues collected so far to the client by means of the
   * `issueAdded` event.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Audits.enable", parameter)
  }

  /**
   * Runs the contrast check for the target page. Found issues are reported
   * using Audits.issueAdded event.
   */
  @ExperimentalCoroutinesApi
  public suspend fun checkContrast(): Unit {
    val parameter = null
    client.callCommand("Audits.checkContrast", parameter)
  }

  /**
   * Information about a cookie that is affected by an inspector issue.
   */
  @Serializable
  public class AffectedCookie(
    /**
     * The following three properties uniquely identify a cookie
     */
    public val name: String,
    public val path: String,
    public val domain: String
  )

  /**
   * Information about a request that is affected by an inspector issue.
   */
  @Serializable
  public class AffectedRequest(
    /**
     * The unique request id.
     */
    public val requestId: String,
    public val url: String? = null
  )

  /**
   * Information about the frame affected by an inspector issue.
   */
  @Serializable
  public class AffectedFrame(
    public val frameId: String
  )

  @Serializable
  public enum class SameSiteCookieExclusionReason {
    @SerialName("ExcludeSameSiteUnspecifiedTreatedAsLax")
    EXCLUDESAMESITEUNSPECIFIEDTREATEDASLAX,
    @SerialName("ExcludeSameSiteNoneInsecure")
    EXCLUDESAMESITENONEINSECURE,
    @SerialName("ExcludeSameSiteLax")
    EXCLUDESAMESITELAX,
    @SerialName("ExcludeSameSiteStrict")
    EXCLUDESAMESITESTRICT,
  }

  @Serializable
  public enum class SameSiteCookieWarningReason {
    @SerialName("WarnSameSiteUnspecifiedCrossSiteContext")
    WARNSAMESITEUNSPECIFIEDCROSSSITECONTEXT,
    @SerialName("WarnSameSiteNoneInsecure")
    WARNSAMESITENONEINSECURE,
    @SerialName("WarnSameSiteUnspecifiedLaxAllowUnsafe")
    WARNSAMESITEUNSPECIFIEDLAXALLOWUNSAFE,
    @SerialName("WarnSameSiteStrictLaxDowngradeStrict")
    WARNSAMESITESTRICTLAXDOWNGRADESTRICT,
    @SerialName("WarnSameSiteStrictCrossDowngradeStrict")
    WARNSAMESITESTRICTCROSSDOWNGRADESTRICT,
    @SerialName("WarnSameSiteStrictCrossDowngradeLax")
    WARNSAMESITESTRICTCROSSDOWNGRADELAX,
    @SerialName("WarnSameSiteLaxCrossDowngradeStrict")
    WARNSAMESITELAXCROSSDOWNGRADESTRICT,
    @SerialName("WarnSameSiteLaxCrossDowngradeLax")
    WARNSAMESITELAXCROSSDOWNGRADELAX,
  }

  @Serializable
  public enum class SameSiteCookieOperation {
    @SerialName("SetCookie")
    SETCOOKIE,
    @SerialName("ReadCookie")
    READCOOKIE,
  }

  /**
   * This information is currently necessary, as the front-end has a difficult
   * time finding a specific cookie. With this, we can convey specific error
   * information without the cookie.
   */
  @Serializable
  public class SameSiteCookieIssueDetails(
    public val cookie: AffectedCookie,
    public val cookieWarningReasons: List<SameSiteCookieWarningReason>,
    public val cookieExclusionReasons: List<SameSiteCookieExclusionReason>,
    /**
     * Optionally identifies the site-for-cookies and the cookie url, which
     * may be used by the front-end as additional context.
     */
    public val operation: SameSiteCookieOperation,
    public val siteForCookies: String? = null,
    public val cookieUrl: String? = null,
    public val request: AffectedRequest? = null
  )

  @Serializable
  public enum class MixedContentResolutionStatus {
    @SerialName("MixedContentBlocked")
    MIXEDCONTENTBLOCKED,
    @SerialName("MixedContentAutomaticallyUpgraded")
    MIXEDCONTENTAUTOMATICALLYUPGRADED,
    @SerialName("MixedContentWarning")
    MIXEDCONTENTWARNING,
  }

  @Serializable
  public enum class MixedContentResourceType {
    @SerialName("Audio")
    AUDIO,
    @SerialName("Beacon")
    BEACON,
    @SerialName("CSPReport")
    CSPREPORT,
    @SerialName("Download")
    DOWNLOAD,
    @SerialName("EventSource")
    EVENTSOURCE,
    @SerialName("Favicon")
    FAVICON,
    @SerialName("Font")
    FONT,
    @SerialName("Form")
    FORM,
    @SerialName("Frame")
    FRAME,
    @SerialName("Image")
    IMAGE,
    @SerialName("Import")
    IMPORT,
    @SerialName("Manifest")
    MANIFEST,
    @SerialName("Ping")
    PING,
    @SerialName("PluginData")
    PLUGINDATA,
    @SerialName("PluginResource")
    PLUGINRESOURCE,
    @SerialName("Prefetch")
    PREFETCH,
    @SerialName("Resource")
    RESOURCE,
    @SerialName("Script")
    SCRIPT,
    @SerialName("ServiceWorker")
    SERVICEWORKER,
    @SerialName("SharedWorker")
    SHAREDWORKER,
    @SerialName("Stylesheet")
    STYLESHEET,
    @SerialName("Track")
    TRACK,
    @SerialName("Video")
    VIDEO,
    @SerialName("Worker")
    WORKER,
    @SerialName("XMLHttpRequest")
    XMLHTTPREQUEST,
    @SerialName("XSLT")
    XSLT,
  }

  @Serializable
  public class MixedContentIssueDetails(
    /**
     * The type of resource causing the mixed content issue (css, js, iframe,
     * form,...). Marked as optional because it is mapped to from
     * blink::mojom::RequestContextType, which will be replaced
     * by network::mojom::RequestDestination
     */
    public val resourceType: MixedContentResourceType? = null,
    /**
     * The way the mixed content issue is being resolved.
     */
    public val resolutionStatus: MixedContentResolutionStatus,
    /**
     * The unsafe http url causing the mixed content issue.
     */
    public val insecureURL: String,
    /**
     * The url responsible for the call to an unsafe url.
     */
    public val mainResourceURL: String,
    /**
     * The mixed content request.
     * Does not always exist (e.g. for unsafe form submission urls).
     */
    public val request: AffectedRequest? = null,
    /**
     * Optional because not every mixed content issue is necessarily linked to a frame.
     */
    public val frame: AffectedFrame? = null
  )

  /**
   * Enum indicating the reason a response has been blocked. These reasons are
   * refinements of the net error BLOCKED_BY_RESPONSE.
   */
  @Serializable
  public enum class BlockedByResponseReason {
    @SerialName("CoepFrameResourceNeedsCoepHeader")
    COEPFRAMERESOURCENEEDSCOEPHEADER,
    @SerialName("CoopSandboxedIFrameCannotNavigateToCoopPage")
    COOPSANDBOXEDIFRAMECANNOTNAVIGATETOCOOPPAGE,
    @SerialName("CorpNotSameOrigin")
    CORPNOTSAMEORIGIN,
    @SerialName("CorpNotSameOriginAfterDefaultedToSameOriginByCoep")
    CORPNOTSAMEORIGINAFTERDEFAULTEDTOSAMEORIGINBYCOEP,
    @SerialName("CorpNotSameSite")
    CORPNOTSAMESITE,
  }

  /**
   * Details for a request that has been blocked with the BLOCKED_BY_RESPONSE
   * code. Currently only used for COEP/COOP, but may be extended to include
   * some CSP errors in the future.
   */
  @Serializable
  public class BlockedByResponseIssueDetails(
    public val request: AffectedRequest,
    public val parentFrame: AffectedFrame? = null,
    public val blockedFrame: AffectedFrame? = null,
    public val reason: BlockedByResponseReason
  )

  @Serializable
  public enum class HeavyAdResolutionStatus {
    @SerialName("HeavyAdBlocked")
    HEAVYADBLOCKED,
    @SerialName("HeavyAdWarning")
    HEAVYADWARNING,
  }

  @Serializable
  public enum class HeavyAdReason {
    @SerialName("NetworkTotalLimit")
    NETWORKTOTALLIMIT,
    @SerialName("CpuTotalLimit")
    CPUTOTALLIMIT,
    @SerialName("CpuPeakLimit")
    CPUPEAKLIMIT,
  }

  @Serializable
  public class HeavyAdIssueDetails(
    /**
     * The resolution status, either blocking the content or warning.
     */
    public val resolution: HeavyAdResolutionStatus,
    /**
     * The reason the ad was blocked, total network or cpu or peak cpu.
     */
    public val reason: HeavyAdReason,
    /**
     * The frame that was blocked.
     */
    public val frame: AffectedFrame
  )

  @Serializable
  public enum class ContentSecurityPolicyViolationType {
    @SerialName("kInlineViolation")
    KINLINEVIOLATION,
    @SerialName("kEvalViolation")
    KEVALVIOLATION,
    @SerialName("kURLViolation")
    KURLVIOLATION,
    @SerialName("kTrustedTypesSinkViolation")
    KTRUSTEDTYPESSINKVIOLATION,
    @SerialName("kTrustedTypesPolicyViolation")
    KTRUSTEDTYPESPOLICYVIOLATION,
  }

  @Serializable
  public class SourceCodeLocation(
    public val scriptId: String? = null,
    public val url: String,
    public val lineNumber: Int,
    public val columnNumber: Int
  )

  @Serializable
  public class ContentSecurityPolicyIssueDetails(
    /**
     * The url not included in allowed sources.
     */
    public val blockedURL: String? = null,
    /**
     * Specific directive that is violated, causing the CSP issue.
     */
    public val violatedDirective: String,
    public val isReportOnly: Boolean,
    public val contentSecurityPolicyViolationType: ContentSecurityPolicyViolationType,
    public val frameAncestor: AffectedFrame? = null,
    public val sourceCodeLocation: SourceCodeLocation? = null,
    public val violatingNodeId: Int? = null
  )

  @Serializable
  public enum class SharedArrayBufferIssueType {
    @SerialName("TransferIssue")
    TRANSFERISSUE,
    @SerialName("CreationIssue")
    CREATIONISSUE,
  }

  /**
   * Details for a issue arising from an SAB being instantiated in, or
   * transfered to a context that is not cross-origin isolated.
   */
  @Serializable
  public class SharedArrayBufferIssueDetails(
    public val sourceCodeLocation: SourceCodeLocation,
    public val isWarning: Boolean,
    public val type: SharedArrayBufferIssueType
  )

  @Serializable
  public enum class TwaQualityEnforcementViolationType {
    @SerialName("kHttpError")
    KHTTPERROR,
    @SerialName("kUnavailableOffline")
    KUNAVAILABLEOFFLINE,
    @SerialName("kDigitalAssetLinks")
    KDIGITALASSETLINKS,
  }

  @Serializable
  public class TrustedWebActivityIssueDetails(
    /**
     * The url that triggers the violation.
     */
    public val url: String,
    public val violationType: TwaQualityEnforcementViolationType,
    public val httpStatusCode: Int? = null,
    /**
     * The package name of the Trusted Web Activity client app. This field is
     * only used when violation type is kDigitalAssetLinks.
     */
    public val packageName: String? = null,
    /**
     * The signature of the Trusted Web Activity client app. This field is only
     * used when violation type is kDigitalAssetLinks.
     */
    public val signature: String? = null
  )

  @Serializable
  public class LowTextContrastIssueDetails(
    public val violatingNodeId: Int,
    public val violatingNodeSelector: String,
    public val contrastRatio: Double,
    public val thresholdAA: Double,
    public val thresholdAAA: Double,
    public val fontSize: String,
    public val fontWeight: String
  )

  /**
   * Details for a CORS related issue, e.g. a warning or error related to
   * CORS RFC1918 enforcement.
   */
  @Serializable
  public class CorsIssueDetails(
    public val corsErrorStatus: Network.CorsErrorStatus,
    public val isWarning: Boolean,
    public val request: AffectedRequest,
    public val resourceIPAddressSpace: Network.IPAddressSpace? = null,
    public val clientSecurityState: Network.ClientSecurityState? = null
  )

  /**
   * A unique identifier for the type of issue. Each type may use one of the
   * optional fields in InspectorIssueDetails to convey more specific
   * information about the kind of issue.
   */
  @Serializable
  public enum class InspectorIssueCode {
    @SerialName("SameSiteCookieIssue")
    SAMESITECOOKIEISSUE,
    @SerialName("MixedContentIssue")
    MIXEDCONTENTISSUE,
    @SerialName("BlockedByResponseIssue")
    BLOCKEDBYRESPONSEISSUE,
    @SerialName("HeavyAdIssue")
    HEAVYADISSUE,
    @SerialName("ContentSecurityPolicyIssue")
    CONTENTSECURITYPOLICYISSUE,
    @SerialName("SharedArrayBufferIssue")
    SHAREDARRAYBUFFERISSUE,
    @SerialName("TrustedWebActivityIssue")
    TRUSTEDWEBACTIVITYISSUE,
    @SerialName("LowTextContrastIssue")
    LOWTEXTCONTRASTISSUE,
    @SerialName("CorsIssue")
    CORSISSUE,
  }

  /**
   * This struct holds a list of optional fields with additional information
   * specific to the kind of issue. When adding a new issue code, please also
   * add a new optional field to this type.
   */
  @Serializable
  public class InspectorIssueDetails(
    public val sameSiteCookieIssueDetails: SameSiteCookieIssueDetails? = null,
    public val mixedContentIssueDetails: MixedContentIssueDetails? = null,
    public val blockedByResponseIssueDetails: BlockedByResponseIssueDetails? = null,
    public val heavyAdIssueDetails: HeavyAdIssueDetails? = null,
    public val contentSecurityPolicyIssueDetails: ContentSecurityPolicyIssueDetails? = null,
    public val sharedArrayBufferIssueDetails: SharedArrayBufferIssueDetails? = null,
    public val twaQualityEnforcementDetails: TrustedWebActivityIssueDetails? = null,
    public val lowTextContrastIssueDetails: LowTextContrastIssueDetails? = null,
    public val corsIssueDetails: CorsIssueDetails? = null
  )

  /**
   * An inspector issue reported from the back-end.
   */
  @Serializable
  public class InspectorIssue(
    public val code: InspectorIssueCode,
    public val details: InspectorIssueDetails
  )

  public data class IssueAddedParameter(
    public val issue: InspectorIssue
  )

  @Serializable
  public data class GetEncodedResponseParameter(
    /**
     * Identifier of the network request to get content for.
     */
    public val requestId: String,
    /**
     * The encoding to use.
     */
    public val encoding: String,
    /**
     * The quality of the encoding (0-1). (defaults to 1)
     */
    public val quality: Double? = null,
    /**
     * Whether to only return the size information (defaults to false).
     */
    public val sizeOnly: Boolean? = null
  )

  @Serializable
  public data class GetEncodedResponseReturn(
    /**
     * The encoded body as a base64 string. Omitted if sizeOnly is true. (Encoded as a base64 string
     * when passed over JSON)
     */
    public val body: String?,
    /**
     * Size before re-encoding.
     */
    public val originalSize: Int,
    /**
     * Size after re-encoding.
     */
    public val encodedSize: Int
  )
}
