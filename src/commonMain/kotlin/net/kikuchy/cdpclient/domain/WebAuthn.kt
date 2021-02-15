package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

public val CDPClient.webAuthn: WebAuthn
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(WebAuthn(this))

/**
 * This domain allows configuring virtual authenticators to test the WebAuthn
 * API.
 */
public class WebAuthn(
  private val client: CDPClient
) : Domain {
  /**
   * Enable the WebAuthn domain and start intercepting credential storage and
   * retrieval with a virtual authenticator.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("WebAuthn.enable", parameter)
  }

  /**
   * Disable the WebAuthn domain.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("WebAuthn.disable", parameter)
  }

  /**
   * Creates and adds a virtual authenticator.
   */
  @ExperimentalCoroutinesApi
  public suspend fun addVirtualAuthenticator(args: AddVirtualAuthenticatorParameter):
      AddVirtualAuthenticatorReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("WebAuthn.addVirtualAuthenticator", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun addVirtualAuthenticator(options: VirtualAuthenticatorOptions):
      AddVirtualAuthenticatorReturn {
    val parameter = AddVirtualAuthenticatorParameter(options = options)
    return addVirtualAuthenticator(parameter)
  }

  /**
   * Removes the given authenticator.
   */
  @ExperimentalCoroutinesApi
  public suspend fun removeVirtualAuthenticator(args: RemoveVirtualAuthenticatorParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("WebAuthn.removeVirtualAuthenticator", parameter)
  }

  public suspend fun removeVirtualAuthenticator(authenticatorId: String): Unit {
    val parameter = RemoveVirtualAuthenticatorParameter(authenticatorId = authenticatorId)
    removeVirtualAuthenticator(parameter)
  }

  /**
   * Adds the credential to the specified authenticator.
   */
  @ExperimentalCoroutinesApi
  public suspend fun addCredential(args: AddCredentialParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("WebAuthn.addCredential", parameter)
  }

  public suspend fun addCredential(authenticatorId: String, credential: Credential): Unit {
    val parameter = AddCredentialParameter(authenticatorId = authenticatorId,credential =
        credential)
    addCredential(parameter)
  }

  /**
   * Returns a single credential stored in the given virtual authenticator that
   * matches the credential ID.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getCredential(args: GetCredentialParameter): GetCredentialReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("WebAuthn.getCredential", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getCredential(authenticatorId: String, credentialId: String):
      GetCredentialReturn {
    val parameter = GetCredentialParameter(authenticatorId = authenticatorId,credentialId =
        credentialId)
    return getCredential(parameter)
  }

  /**
   * Returns all the credentials stored in the given virtual authenticator.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getCredentials(args: GetCredentialsParameter): GetCredentialsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("WebAuthn.getCredentials", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getCredentials(authenticatorId: String): GetCredentialsReturn {
    val parameter = GetCredentialsParameter(authenticatorId = authenticatorId)
    return getCredentials(parameter)
  }

  /**
   * Removes a credential from the authenticator.
   */
  @ExperimentalCoroutinesApi
  public suspend fun removeCredential(args: RemoveCredentialParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("WebAuthn.removeCredential", parameter)
  }

  public suspend fun removeCredential(authenticatorId: String, credentialId: String): Unit {
    val parameter = RemoveCredentialParameter(authenticatorId = authenticatorId,credentialId =
        credentialId)
    removeCredential(parameter)
  }

  /**
   * Clears all the credentials from the specified device.
   */
  @ExperimentalCoroutinesApi
  public suspend fun clearCredentials(args: ClearCredentialsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("WebAuthn.clearCredentials", parameter)
  }

  public suspend fun clearCredentials(authenticatorId: String): Unit {
    val parameter = ClearCredentialsParameter(authenticatorId = authenticatorId)
    clearCredentials(parameter)
  }

  /**
   * Sets whether User Verification succeeds or fails for an authenticator.
   * The default is true.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setUserVerified(args: SetUserVerifiedParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("WebAuthn.setUserVerified", parameter)
  }

  public suspend fun setUserVerified(authenticatorId: String, isUserVerified: Boolean): Unit {
    val parameter = SetUserVerifiedParameter(authenticatorId = authenticatorId,isUserVerified =
        isUserVerified)
    setUserVerified(parameter)
  }

  /**
   * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if
   * false) for an authenticator.
   * The default is true.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setAutomaticPresenceSimulation(args: SetAutomaticPresenceSimulationParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("WebAuthn.setAutomaticPresenceSimulation", parameter)
  }

  public suspend fun setAutomaticPresenceSimulation(authenticatorId: String, enabled: Boolean):
      Unit {
    val parameter = SetAutomaticPresenceSimulationParameter(authenticatorId =
        authenticatorId,enabled = enabled)
    setAutomaticPresenceSimulation(parameter)
  }

  @Serializable
  public enum class AuthenticatorProtocol {
    @SerialName("u2f")
    U2F,
    @SerialName("ctap2")
    CTAP2,
  }

  @Serializable
  public enum class Ctap2Version {
    @SerialName("ctap2_0")
    CTAP2_0,
    @SerialName("ctap2_1")
    CTAP2_1,
  }

  @Serializable
  public enum class AuthenticatorTransport {
    @SerialName("usb")
    USB,
    @SerialName("nfc")
    NFC,
    @SerialName("ble")
    BLE,
    @SerialName("cable")
    CABLE,
    @SerialName("internal")
    INTERNAL,
  }

  @Serializable
  public data class VirtualAuthenticatorOptions(
    public val protocol: AuthenticatorProtocol,
    /**
     * Defaults to ctap2_0. Ignored if |protocol| == u2f.
     */
    public val ctap2Version: Ctap2Version? = null,
    public val transport: AuthenticatorTransport,
    /**
     * Defaults to false.
     */
    public val hasResidentKey: Boolean? = null,
    /**
     * Defaults to false.
     */
    public val hasUserVerification: Boolean? = null,
    /**
     * If set to true, the authenticator will support the largeBlob extension.
     * https://w3c.github.io/webauthn#largeBlob
     * Defaults to false.
     */
    public val hasLargeBlob: Boolean? = null,
    /**
     * If set to true, tests of user presence will succeed immediately.
     * Otherwise, they will not be resolved. Defaults to true.
     */
    public val automaticPresenceSimulation: Boolean? = null,
    /**
     * Sets whether User Verification succeeds or fails for an authenticator.
     * Defaults to false.
     */
    public val isUserVerified: Boolean? = null
  )

  @Serializable
  public data class Credential(
    public val credentialId: String,
    public val isResidentCredential: Boolean,
    /**
     * Relying Party ID the credential is scoped to. Must be set when adding a
     * credential.
     */
    public val rpId: String? = null,
    /**
     * The ECDSA P-256 private key in PKCS#8 format. (Encoded as a base64 string when passed over
     * JSON)
     */
    public val privateKey: String,
    /**
     * An opaque byte sequence with a maximum size of 64 bytes mapping the
     * credential to a specific user. (Encoded as a base64 string when passed over JSON)
     */
    public val userHandle: String? = null,
    /**
     * Signature counter. This is incremented by one for each successful
     * assertion.
     * See https://w3c.github.io/webauthn/#signature-counter
     */
    public val signCount: Int,
    /**
     * The large blob associated with the credential.
     * See https://w3c.github.io/webauthn/#sctn-large-blob-extension (Encoded as a base64 string
     * when passed over JSON)
     */
    public val largeBlob: String? = null
  )

  @Serializable
  public data class AddVirtualAuthenticatorParameter(
    public val options: VirtualAuthenticatorOptions
  )

  @Serializable
  public data class AddVirtualAuthenticatorReturn(
    public val authenticatorId: String
  )

  @Serializable
  public data class RemoveVirtualAuthenticatorParameter(
    public val authenticatorId: String
  )

  @Serializable
  public data class AddCredentialParameter(
    public val authenticatorId: String,
    public val credential: Credential
  )

  @Serializable
  public data class GetCredentialParameter(
    public val authenticatorId: String,
    public val credentialId: String
  )

  @Serializable
  public data class GetCredentialReturn(
    public val credential: Credential
  )

  @Serializable
  public data class GetCredentialsParameter(
    public val authenticatorId: String
  )

  @Serializable
  public data class GetCredentialsReturn(
    public val credentials: List<Credential>
  )

  @Serializable
  public data class RemoveCredentialParameter(
    public val authenticatorId: String,
    public val credentialId: String
  )

  @Serializable
  public data class ClearCredentialsParameter(
    public val authenticatorId: String
  )

  @Serializable
  public data class SetUserVerifiedParameter(
    public val authenticatorId: String,
    public val isUserVerified: Boolean
  )

  @Serializable
  public data class SetAutomaticPresenceSimulationParameter(
    public val authenticatorId: String,
    public val enabled: Boolean
  )
}
