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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.accessibility: Accessibility
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Accessibility(this))

public class Accessibility(
  private val client: CDPClient
) : Domain {
  /**
   * Disables the accessibility domain.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Accessibility.disable", parameter)
  }

  /**
   * Enables the accessibility domain which causes `AXNodeId`s to remain consistent between method
   * calls.
   * This turns on accessibility for the page, which can impact performance until accessibility is
   * disabled.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Accessibility.enable", parameter)
  }

  /**
   * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getPartialAXTree(args: GetPartialAXTreeParameter): GetPartialAXTreeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Accessibility.getPartialAXTree", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getPartialAXTree(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null,
    fetchRelatives: Boolean? = null
  ): GetPartialAXTreeReturn {
    val parameter = GetPartialAXTreeParameter(nodeId = nodeId,backendNodeId = backendNodeId,objectId
        = objectId,fetchRelatives = fetchRelatives)
    return getPartialAXTree(parameter)
  }

  /**
   * Fetches the entire accessibility tree for the root Document
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getFullAXTree(args: GetFullAXTreeParameter): GetFullAXTreeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Accessibility.getFullAXTree", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Fetches the entire accessibility tree for the root Document
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getFullAXTree(max_depth: Int? = null): GetFullAXTreeReturn {
    val parameter = GetFullAXTreeParameter(max_depth = max_depth)
    return getFullAXTree(parameter)
  }

  /**
   * Fetches a particular accessibility node by AXNodeId.
   * Requires `enable()` to have been called previously.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getChildAXNodes(args: GetChildAXNodesParameter): GetChildAXNodesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Accessibility.getChildAXNodes", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Fetches a particular accessibility node by AXNodeId.
   * Requires `enable()` to have been called previously.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getChildAXNodes(id: String): GetChildAXNodesReturn {
    val parameter = GetChildAXNodesParameter(id = id)
    return getChildAXNodes(parameter)
  }

  /**
   * Query a DOM node's accessibility subtree for accessible name and role.
   * This command computes the name and role for all nodes in the subtree, including those that are
   * ignored for accessibility, and returns those that mactch the specified name and role. If no DOM
   * node is specified, or the DOM node does not exist, the command returns an error. If neither
   * `accessibleName` or `role` is specified, it returns all the accessibility nodes in the subtree.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun queryAXTree(args: QueryAXTreeParameter): QueryAXTreeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Accessibility.queryAXTree", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Query a DOM node's accessibility subtree for accessible name and role.
   * This command computes the name and role for all nodes in the subtree, including those that are
   * ignored for accessibility, and returns those that mactch the specified name and role. If no DOM
   * node is specified, or the DOM node does not exist, the command returns an error. If neither
   * `accessibleName` or `role` is specified, it returns all the accessibility nodes in the subtree.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun queryAXTree(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null,
    accessibleName: String? = null,
    role: String? = null
  ): QueryAXTreeReturn {
    val parameter = QueryAXTreeParameter(nodeId = nodeId,backendNodeId = backendNodeId,objectId =
        objectId,accessibleName = accessibleName,role = role)
    return queryAXTree(parameter)
  }

  /**
   * Enum of possible property types.
   */
  @Serializable
  public enum class AXValueType {
    @SerialName("boolean")
    BOOLEAN,
    @SerialName("tristate")
    TRISTATE,
    @SerialName("booleanOrUndefined")
    BOOLEANORUNDEFINED,
    @SerialName("idref")
    IDREF,
    @SerialName("idrefList")
    IDREFLIST,
    @SerialName("integer")
    INTEGER,
    @SerialName("node")
    NODE,
    @SerialName("nodeList")
    NODELIST,
    @SerialName("number")
    NUMBER,
    @SerialName("string")
    STRING,
    @SerialName("computedString")
    COMPUTEDSTRING,
    @SerialName("token")
    TOKEN,
    @SerialName("tokenList")
    TOKENLIST,
    @SerialName("domRelation")
    DOMRELATION,
    @SerialName("role")
    ROLE,
    @SerialName("internalRole")
    INTERNALROLE,
    @SerialName("valueUndefined")
    VALUEUNDEFINED,
  }

  /**
   * Enum of possible property sources.
   */
  @Serializable
  public enum class AXValueSourceType {
    @SerialName("attribute")
    ATTRIBUTE,
    @SerialName("implicit")
    IMPLICIT,
    @SerialName("style")
    STYLE,
    @SerialName("contents")
    CONTENTS,
    @SerialName("placeholder")
    PLACEHOLDER,
    @SerialName("relatedElement")
    RELATEDELEMENT,
  }

  /**
   * Enum of possible native property sources (as a subtype of a particular AXValueSourceType).
   */
  @Serializable
  public enum class AXValueNativeSourceType {
    @SerialName("figcaption")
    FIGCAPTION,
    @SerialName("label")
    LABEL,
    @SerialName("labelfor")
    LABELFOR,
    @SerialName("labelwrapped")
    LABELWRAPPED,
    @SerialName("legend")
    LEGEND,
    @SerialName("rubyannotation")
    RUBYANNOTATION,
    @SerialName("tablecaption")
    TABLECAPTION,
    @SerialName("title")
    TITLE,
    @SerialName("other")
    OTHER,
  }

  /**
   * A single source for a computed AX property.
   */
  @Serializable
  public data class AXValueSource(
    /**
     * What type of source this is.
     */
    public val type: AXValueSourceType,
    /**
     * The value of this property source.
     */
    public val value: AXValue? = null,
    /**
     * The name of the relevant attribute, if any.
     */
    public val attribute: String? = null,
    /**
     * The value of the relevant attribute, if any.
     */
    public val attributeValue: AXValue? = null,
    /**
     * Whether this source is superseded by a higher priority source.
     */
    public val superseded: Boolean? = null,
    /**
     * The native markup source for this value, e.g. a <label> element.
     */
    public val nativeSource: AXValueNativeSourceType? = null,
    /**
     * The value, such as a node or node list, of the native source.
     */
    public val nativeSourceValue: AXValue? = null,
    /**
     * Whether the value for this property is invalid.
     */
    public val invalid: Boolean? = null,
    /**
     * Reason for the value being invalid, if it is.
     */
    public val invalidReason: String? = null
  )

  @Serializable
  public data class AXRelatedNode(
    /**
     * The BackendNodeId of the related DOM node.
     */
    public val backendDOMNodeId: Int,
    /**
     * The IDRef value provided, if any.
     */
    public val idref: String? = null,
    /**
     * The text alternative of this node in the current context.
     */
    public val text: String? = null
  )

  @Serializable
  public data class AXProperty(
    /**
     * The name of this property.
     */
    public val name: AXPropertyName,
    /**
     * The value of this property.
     */
    public val value: AXValue
  )

  /**
   * A single computed AX property.
   */
  @Serializable
  public data class AXValue(
    /**
     * The type of this value.
     */
    public val type: AXValueType,
    /**
     * The computed value of this property.
     */
    public val value: JsonElement? = null,
    /**
     * One or more related nodes, if applicable.
     */
    public val relatedNodes: List<AXRelatedNode>? = null,
    /**
     * The sources which contributed to the computation of this property.
     */
    public val sources: List<AXValueSource>? = null
  )

  /**
   * Values of AXProperty name:
   * - from 'busy' to 'roledescription': states which apply to every AX node
   * - from 'live' to 'root': attributes which apply to nodes in live regions
   * - from 'autocomplete' to 'valuetext': attributes which apply to widgets
   * - from 'checked' to 'selected': states which apply to widgets
   * - from 'activedescendant' to 'owns' - relationships between elements other than
   * parent/child/sibling.
   */
  @Serializable
  public enum class AXPropertyName {
    @SerialName("busy")
    BUSY,
    @SerialName("disabled")
    DISABLED,
    @SerialName("editable")
    EDITABLE,
    @SerialName("focusable")
    FOCUSABLE,
    @SerialName("focused")
    FOCUSED,
    @SerialName("hidden")
    HIDDEN,
    @SerialName("hiddenRoot")
    HIDDENROOT,
    @SerialName("invalid")
    INVALID,
    @SerialName("keyshortcuts")
    KEYSHORTCUTS,
    @SerialName("settable")
    SETTABLE,
    @SerialName("roledescription")
    ROLEDESCRIPTION,
    @SerialName("live")
    LIVE,
    @SerialName("atomic")
    ATOMIC,
    @SerialName("relevant")
    RELEVANT,
    @SerialName("root")
    ROOT,
    @SerialName("autocomplete")
    AUTOCOMPLETE,
    @SerialName("hasPopup")
    HASPOPUP,
    @SerialName("level")
    LEVEL,
    @SerialName("multiselectable")
    MULTISELECTABLE,
    @SerialName("orientation")
    ORIENTATION,
    @SerialName("multiline")
    MULTILINE,
    @SerialName("readonly")
    READONLY,
    @SerialName("required")
    REQUIRED,
    @SerialName("valuemin")
    VALUEMIN,
    @SerialName("valuemax")
    VALUEMAX,
    @SerialName("valuetext")
    VALUETEXT,
    @SerialName("checked")
    CHECKED,
    @SerialName("expanded")
    EXPANDED,
    @SerialName("modal")
    MODAL,
    @SerialName("pressed")
    PRESSED,
    @SerialName("selected")
    SELECTED,
    @SerialName("activedescendant")
    ACTIVEDESCENDANT,
    @SerialName("controls")
    CONTROLS,
    @SerialName("describedby")
    DESCRIBEDBY,
    @SerialName("details")
    DETAILS,
    @SerialName("errormessage")
    ERRORMESSAGE,
    @SerialName("flowto")
    FLOWTO,
    @SerialName("labelledby")
    LABELLEDBY,
    @SerialName("owns")
    OWNS,
  }

  /**
   * A node in the accessibility tree.
   */
  @Serializable
  public data class AXNode(
    /**
     * Unique identifier for this node.
     */
    public val nodeId: String,
    /**
     * Whether this node is ignored for accessibility
     */
    public val ignored: Boolean,
    /**
     * Collection of reasons why this node is hidden.
     */
    public val ignoredReasons: List<AXProperty>? = null,
    /**
     * This `Node`'s role, whether explicit or implicit.
     */
    public val role: AXValue? = null,
    /**
     * The accessible name for this `Node`.
     */
    public val name: AXValue? = null,
    /**
     * The accessible description for this `Node`.
     */
    public val description: AXValue? = null,
    /**
     * The value for this `Node`.
     */
    public val value: AXValue? = null,
    /**
     * All other properties
     */
    public val properties: List<AXProperty>? = null,
    /**
     * IDs for each of this node's child nodes.
     */
    public val childIds: List<String>? = null,
    /**
     * The backend ID for the associated DOM node, if any.
     */
    public val backendDOMNodeId: Int? = null
  )

  @Serializable
  public data class GetPartialAXTreeParameter(
    /**
     * Identifier of the node to get the partial accessibility tree for.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node to get the partial accessibility tree for.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper to get the partial accessibility tree for.
     */
    public val objectId: String? = null,
    /**
     * Whether to fetch this nodes ancestors, siblings and children. Defaults to true.
     */
    public val fetchRelatives: Boolean? = null
  )

  @Serializable
  public data class GetPartialAXTreeReturn(
    /**
     * The `Accessibility.AXNode` for this DOM node, if it exists, plus its ancestors, siblings and
     * children, if requested.
     */
    public val nodes: List<AXNode>
  )

  @Serializable
  public data class GetFullAXTreeParameter(
    /**
     * The maximum depth at which descendants of the root node should be retrieved.
     * If omitted, the full tree is returned.
     */
    public val max_depth: Int? = null
  )

  @Serializable
  public data class GetFullAXTreeReturn(
    public val nodes: List<AXNode>
  )

  @Serializable
  public data class GetChildAXNodesParameter(
    public val id: String
  )

  @Serializable
  public data class GetChildAXNodesReturn(
    public val nodes: List<AXNode>
  )

  @Serializable
  public data class QueryAXTreeParameter(
    /**
     * Identifier of the node for the root to query.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node for the root to query.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper for the root to query.
     */
    public val objectId: String? = null,
    /**
     * Find nodes with this computed name.
     */
    public val accessibleName: String? = null,
    /**
     * Find nodes with this computed role.
     */
    public val role: String? = null
  )

  @Serializable
  public data class QueryAXTreeReturn(
    /**
     * A list of `Accessibility.AXNode` matching the specified attributes,
     * including nodes that are ignored for accessibility.
     */
    public val nodes: List<AXNode>
  )
}
