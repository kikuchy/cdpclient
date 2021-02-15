package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Deprecated
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

public val CDPClient.security: Security
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Security(this))

/**
 * Security
 */
public class Security(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val certificateError: Flow<CertificateErrorParameter> = client
          .events
          .filter {
              it.method == "certificateError"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val visibleSecurityStateChanged: Flow<VisibleSecurityStateChangedParameter> = client
          .events
          .filter {
              it.method == "visibleSecurityStateChanged"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val securityStateChanged: Flow<SecurityStateChangedParameter> = client
          .events
          .filter {
              it.method == "securityStateChanged"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Disables tracking security state changes.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Security.disable", parameter)
  }

  /**
   * Enables tracking security state changes.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Security.enable", parameter)
  }

  /**
   * Enable/disable whether all certificate errors should be ignored.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setIgnoreCertificateErrors(args: SetIgnoreCertificateErrorsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Security.setIgnoreCertificateErrors", parameter)
  }

  public suspend fun setIgnoreCertificateErrors(ignore: Boolean): Unit {
    val parameter = SetIgnoreCertificateErrorsParameter(ignore = ignore)
    setIgnoreCertificateErrors(parameter)
  }

  /**
   * Handles a certificate error that fired a certificateError event.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun handleCertificateError(args: HandleCertificateErrorParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Security.handleCertificateError", parameter)
  }

  public suspend fun handleCertificateError(eventId: Int, action: CertificateErrorAction): Unit {
    val parameter = HandleCertificateErrorParameter(eventId = eventId,action = action)
    handleCertificateError(parameter)
  }

  /**
   * Enable/disable overriding certificate errors. If enabled, all certificate error events need to
   * be handled by the DevTools client and should be answered with `handleCertificateError`
   * commands.
   */
  @ExperimentalCoroutinesApi
  @Deprecated(message = "")
  public suspend fun setOverrideCertificateErrors(args: SetOverrideCertificateErrorsParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Security.setOverrideCertificateErrors", parameter)
  }

  public suspend fun setOverrideCertificateErrors(`override`: Boolean): Unit {
    val parameter = SetOverrideCertificateErrorsParameter(override = override)
    setOverrideCertificateErrors(parameter)
  }

  /**
   * A description of mixed content (HTTP resources on HTTPS pages), as defined by
   * https://www.w3.org/TR/mixed-content/#categories
   */
  @Serializable
  public enum class MixedContentType {
    @SerialName("blockable")
    BLOCKABLE,
    @SerialName("optionally-blockable")
    OPTIONALLY_BLOCKABLE,
    @SerialName("none")
    NONE,
  }

  /**
   * The security level of a page or resource.
   */
  @Serializable
  public enum class SecurityState {
    @SerialName("unknown")
    UNKNOWN,
    @SerialName("neutral")
    NEUTRAL,
    @SerialName("insecure")
    INSECURE,
    @SerialName("secure")
    SECURE,
    @SerialName("info")
    INFO,
    @SerialName("insecure-broken")
    INSECURE_BROKEN,
  }

  /**
   * Details about the security state of the page certificate.
   */
  @Serializable
  public data class CertificateSecurityState(
    /**
     * Protocol name (e.g. "TLS 1.2" or "QUIC").
     */
    public val protocol: String,
    /**
     * Key Exchange used by the connection, or the empty string if not applicable.
     */
    public val keyExchange: String,
    /**
     * (EC)DH group used by the connection, if applicable.
     */
    public val keyExchangeGroup: String? = null,
    /**
     * Cipher name.
     */
    public val cipher: String,
    /**
     * TLS MAC. Note that AEAD ciphers do not have separate MACs.
     */
    public val mac: String? = null,
    /**
     * Page certificate.
     */
    public val certificate: String,
    /**
     * Certificate subject name.
     */
    public val subjectName: String,
    /**
     * Name of the issuing CA.
     */
    public val issuer: String,
    /**
     * Certificate valid from date.
     */
    public val validFrom: Double,
    /**
     * Certificate valid to (expiration) date
     */
    public val validTo: Double,
    /**
     * The highest priority network error code, if the certificate has an error.
     */
    public val certificateNetworkError: String? = null,
    /**
     * True if the certificate uses a weak signature aglorithm.
     */
    public val certificateHasWeakSignature: Boolean,
    /**
     * True if the certificate has a SHA1 signature in the chain.
     */
    public val certificateHasSha1Signature: Boolean,
    /**
     * True if modern SSL
     */
    public val modernSSL: Boolean,
    /**
     * True if the connection is using an obsolete SSL protocol.
     */
    public val obsoleteSslProtocol: Boolean,
    /**
     * True if the connection is using an obsolete SSL key exchange.
     */
    public val obsoleteSslKeyExchange: Boolean,
    /**
     * True if the connection is using an obsolete SSL cipher.
     */
    public val obsoleteSslCipher: Boolean,
    /**
     * True if the connection is using an obsolete SSL signature.
     */
    public val obsoleteSslSignature: Boolean
  )

  @Serializable
  public enum class SafetyTipStatus {
    @SerialName("badReputation")
    BADREPUTATION,
    @SerialName("lookalike")
    LOOKALIKE,
  }

  @Serializable
  public data class SafetyTipInfo(
    /**
     * Describes whether the page triggers any safety tips or reputation warnings. Default is
     * unknown.
     */
    public val safetyTipStatus: SafetyTipStatus,
    /**
     * The URL the safety tip suggested ("Did you mean?"). Only filled in for lookalike matches.
     */
    public val safeUrl: String? = null
  )

  /**
   * Security state information about the page.
   */
  @Serializable
  public data class VisibleSecurityState(
    /**
     * The security level of the page.
     */
    public val securityState: SecurityState,
    /**
     * Security state details about the page certificate.
     */
    public val certificateSecurityState: CertificateSecurityState? = null,
    /**
     * The type of Safety Tip triggered on the page. Note that this field will be set even if the
     * Safety Tip UI was not actually shown.
     */
    public val safetyTipInfo: SafetyTipInfo? = null,
    /**
     * Array of security state issues ids.
     */
    public val securityStateIssueIds: String
  )

  /**
   * An explanation of an factor contributing to the security state.
   */
  @Serializable
  public data class SecurityStateExplanation(
    /**
     * Security state representing the severity of the factor being explained.
     */
    public val securityState: SecurityState,
    /**
     * Title describing the type of factor.
     */
    public val title: String,
    /**
     * Short phrase describing the type of factor.
     */
    public val summary: String,
    /**
     * Full text explanation of the factor.
     */
    public val description: String,
    /**
     * The type of mixed content described by the explanation.
     */
    public val mixedContentType: MixedContentType,
    /**
     * Page certificate.
     */
    public val certificate: String,
    /**
     * Recommendations to fix any issues.
     */
    public val recommendations: String? = null
  )

  /**
   * Information about insecure content on the page.
   */
  @Serializable
  public data class InsecureContentStatus(
    /**
     * Always false.
     */
    public val ranMixedContent: Boolean,
    /**
     * Always false.
     */
    public val displayedMixedContent: Boolean,
    /**
     * Always false.
     */
    public val containedMixedForm: Boolean,
    /**
     * Always false.
     */
    public val ranContentWithCertErrors: Boolean,
    /**
     * Always false.
     */
    public val displayedContentWithCertErrors: Boolean,
    /**
     * Always set to unknown.
     */
    public val ranInsecureContentStyle: SecurityState,
    /**
     * Always set to unknown.
     */
    public val displayedInsecureContentStyle: SecurityState
  )

  /**
   * The action to take when a certificate error occurs. continue will continue processing the
   * request and cancel will cancel the request.
   */
  @Serializable
  public enum class CertificateErrorAction {
    @SerialName("continue")
    CONTINUE,
    @SerialName("cancel")
    CANCEL,
  }

  /**
   * There is a certificate error. If overriding certificate errors is enabled, then it should be
   * handled with the `handleCertificateError` command. Note: this event does not fire if the
   * certificate error has been allowed internally. Only one client per target should override
   * certificate errors at the same time.
   */
  public data class CertificateErrorParameter(
    /**
     * The ID of the event.
     */
    public val eventId: Int,
    /**
     * The type of the error.
     */
    public val errorType: String,
    /**
     * The url that was requested.
     */
    public val requestURL: String
  )

  /**
   * The security state of the page changed.
   */
  public data class VisibleSecurityStateChangedParameter(
    /**
     * Security state information about the page.
     */
    public val visibleSecurityState: VisibleSecurityState
  )

  /**
   * The security state of the page changed.
   */
  public data class SecurityStateChangedParameter(
    /**
     * Security state.
     */
    public val securityState: SecurityState,
    /**
     * True if the page was loaded over cryptographic transport such as HTTPS.
     */
    public val schemeIsCryptographic: Boolean,
    /**
     * List of explanations for the security state. If the overall security state is `insecure` or
     * `warning`, at least one corresponding explanation should be included.
     */
    public val explanations: List<SecurityStateExplanation>,
    /**
     * Information about insecure content on the page.
     */
    public val insecureContentStatus: InsecureContentStatus,
    /**
     * Overrides user-visible description of the state.
     */
    public val summary: String? = null
  )

  @Serializable
  public data class SetIgnoreCertificateErrorsParameter(
    /**
     * If true, all certificate errors will be ignored.
     */
    public val ignore: Boolean
  )

  @Serializable
  public data class HandleCertificateErrorParameter(
    /**
     * The ID of the event.
     */
    public val eventId: Int,
    /**
     * The action to take on the certificate error.
     */
    public val action: CertificateErrorAction
  )

  @Serializable
  public data class SetOverrideCertificateErrorsParameter(
    /**
     * If true, certificate errors will be overridden.
     */
    public val `override`: Boolean
  )
}
