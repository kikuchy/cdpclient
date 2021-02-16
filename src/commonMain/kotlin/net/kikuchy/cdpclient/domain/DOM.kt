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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.dom: DOM
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(DOM(this))

/**
 * This domain exposes DOM read/write operations. Each DOM Node is represented with its mirror
 * object
 * that has an `id`. This `id` can be used to get additional information on the Node, resolve it
 * into
 * the JavaScript object wrapper, etc. It is important that client receives DOM events only for the
 * nodes that are known to the client. Backend keeps track of the nodes that were sent to the client
 * and never sends the same node twice. It is client's responsibility to collect information about
 * the nodes that were sent to the client.<p>Note that `iframe` owner elements will return
 * corresponding document elements as their child nodes.</p>
 */
public class DOM(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val attributeModified: Flow<AttributeModifiedParameter> = client
          .events
          .filter {
              it.method == "attributeModified"
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
  public val attributeRemoved: Flow<AttributeRemovedParameter> = client
          .events
          .filter {
              it.method == "attributeRemoved"
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
  public val characterDataModified: Flow<CharacterDataModifiedParameter> = client
          .events
          .filter {
              it.method == "characterDataModified"
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
  public val childNodeCountUpdated: Flow<ChildNodeCountUpdatedParameter> = client
          .events
          .filter {
              it.method == "childNodeCountUpdated"
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
  public val childNodeInserted: Flow<ChildNodeInsertedParameter> = client
          .events
          .filter {
              it.method == "childNodeInserted"
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
  public val childNodeRemoved: Flow<ChildNodeRemovedParameter> = client
          .events
          .filter {
              it.method == "childNodeRemoved"
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
  public val distributedNodesUpdated: Flow<DistributedNodesUpdatedParameter> = client
          .events
          .filter {
              it.method == "distributedNodesUpdated"
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
  public val documentUpdated: Flow<Unit> = client
          .events
          .filter {
              it.method == "documentUpdated"
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
  public val inlineStyleInvalidated: Flow<InlineStyleInvalidatedParameter> = client
          .events
          .filter {
              it.method == "inlineStyleInvalidated"
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
  public val pseudoElementAdded: Flow<PseudoElementAddedParameter> = client
          .events
          .filter {
              it.method == "pseudoElementAdded"
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
  public val pseudoElementRemoved: Flow<PseudoElementRemovedParameter> = client
          .events
          .filter {
              it.method == "pseudoElementRemoved"
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
  public val setChildNodes: Flow<SetChildNodesParameter> = client
          .events
          .filter {
              it.method == "setChildNodes"
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
  public val shadowRootPopped: Flow<ShadowRootPoppedParameter> = client
          .events
          .filter {
              it.method == "shadowRootPopped"
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
  public val shadowRootPushed: Flow<ShadowRootPushedParameter> = client
          .events
          .filter {
              it.method == "shadowRootPushed"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Collects class names for the node with given id and all of it's child nodes.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun collectClassNamesFromSubtree(args: CollectClassNamesFromSubtreeParameter):
      CollectClassNamesFromSubtreeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.collectClassNamesFromSubtree", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Collects class names for the node with given id and all of it's child nodes.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun collectClassNamesFromSubtree(nodeId: Int): CollectClassNamesFromSubtreeReturn {
    val parameter = CollectClassNamesFromSubtreeParameter(nodeId = nodeId)
    return collectClassNamesFromSubtree(parameter)
  }

  /**
   * Creates a deep copy of the specified node and places it into the target container before the
   * given anchor.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun copyTo(args: CopyToParameter): CopyToReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.copyTo", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Creates a deep copy of the specified node and places it into the target container before the
   * given anchor.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun copyTo(
    nodeId: Int,
    targetNodeId: Int,
    insertBeforeNodeId: Int? = null
  ): CopyToReturn {
    val parameter = CopyToParameter(nodeId = nodeId, targetNodeId = targetNodeId, insertBeforeNodeId
        = insertBeforeNodeId)
    return copyTo(parameter)
  }

  /**
   * Describes node given its id, does not require domain to be enabled. Does not start tracking any
   * objects, can be used for automation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun describeNode(args: DescribeNodeParameter): DescribeNodeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.describeNode", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Describes node given its id, does not require domain to be enabled. Does not start tracking any
   * objects, can be used for automation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun describeNode(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null,
    depth: Int? = null,
    pierce: Boolean? = null
  ): DescribeNodeReturn {
    val parameter = DescribeNodeParameter(nodeId = nodeId, backendNodeId = backendNodeId, objectId =
        objectId, depth = depth, pierce = pierce)
    return describeNode(parameter)
  }

  /**
   * Scrolls the specified rect of the given node into view if not already visible.
   * Note: exactly one between nodeId, backendNodeId and objectId should be passed
   * to identify the node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun scrollIntoViewIfNeeded(args: ScrollIntoViewIfNeededParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.scrollIntoViewIfNeeded", parameter)
  }

  /**
   * Scrolls the specified rect of the given node into view if not already visible.
   * Note: exactly one between nodeId, backendNodeId and objectId should be passed
   * to identify the node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun scrollIntoViewIfNeeded(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null,
    rect: Rect? = null
  ): Unit {
    val parameter = ScrollIntoViewIfNeededParameter(nodeId = nodeId, backendNodeId = backendNodeId,
        objectId = objectId, rect = rect)
    scrollIntoViewIfNeeded(parameter)
  }

  /**
   * Disables DOM agent for the given page.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("DOM.disable", parameter)
  }

  /**
   * Discards search results from the session with the given id. `getSearchResults` should no longer
   * be called for that search.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun discardSearchResults(args: DiscardSearchResultsParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.discardSearchResults", parameter)
  }

  /**
   * Discards search results from the session with the given id. `getSearchResults` should no longer
   * be called for that search.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun discardSearchResults(searchId: String): Unit {
    val parameter = DiscardSearchResultsParameter(searchId = searchId)
    discardSearchResults(parameter)
  }

  /**
   * Enables DOM agent for the given page.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("DOM.enable", parameter)
  }

  /**
   * Focuses the given element.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun focus(args: FocusParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.focus", parameter)
  }

  /**
   * Focuses the given element.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun focus(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null
  ): Unit {
    val parameter = FocusParameter(nodeId = nodeId, backendNodeId = backendNodeId, objectId =
        objectId)
    focus(parameter)
  }

  /**
   * Returns attributes for the specified node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getAttributes(args: GetAttributesParameter): GetAttributesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getAttributes", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns attributes for the specified node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getAttributes(nodeId: Int): GetAttributesReturn {
    val parameter = GetAttributesParameter(nodeId = nodeId)
    return getAttributes(parameter)
  }

  /**
   * Returns boxes for the given node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getBoxModel(args: GetBoxModelParameter): GetBoxModelReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getBoxModel", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns boxes for the given node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getBoxModel(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null
  ): GetBoxModelReturn {
    val parameter = GetBoxModelParameter(nodeId = nodeId, backendNodeId = backendNodeId, objectId =
        objectId)
    return getBoxModel(parameter)
  }

  /**
   * Returns quads that describe node position on the page. This method
   * might return multiple quads for inline nodes.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getContentQuads(args: GetContentQuadsParameter): GetContentQuadsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getContentQuads", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns quads that describe node position on the page. This method
   * might return multiple quads for inline nodes.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getContentQuads(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null
  ): GetContentQuadsReturn {
    val parameter = GetContentQuadsParameter(nodeId = nodeId, backendNodeId = backendNodeId,
        objectId = objectId)
    return getContentQuads(parameter)
  }

  /**
   * Returns the root DOM node (and optionally the subtree) to the caller.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getDocument(args: GetDocumentParameter): GetDocumentReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getDocument", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns the root DOM node (and optionally the subtree) to the caller.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getDocument(depth: Int? = null, pierce: Boolean? = null): GetDocumentReturn {
    val parameter = GetDocumentParameter(depth = depth, pierce = pierce)
    return getDocument(parameter)
  }

  /**
   * Returns the root DOM node (and optionally the subtree) to the caller.
   * Deprecated, as it is not designed to work well with the rest of the DOM agent.
   * Use DOMSnapshot.captureSnapshot instead.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  @Deprecated(message = "")
  public suspend fun getFlattenedDocument(args: GetFlattenedDocumentParameter):
      GetFlattenedDocumentReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getFlattenedDocument", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns the root DOM node (and optionally the subtree) to the caller.
   * Deprecated, as it is not designed to work well with the rest of the DOM agent.
   * Use DOMSnapshot.captureSnapshot instead.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  @Deprecated(message = "")
  public suspend fun getFlattenedDocument(depth: Int? = null, pierce: Boolean? = null):
      GetFlattenedDocumentReturn {
    val parameter = GetFlattenedDocumentParameter(depth = depth, pierce = pierce)
    return getFlattenedDocument(parameter)
  }

  /**
   * Finds nodes with a given computed style in a subtree.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getNodesForSubtreeByStyle(args: GetNodesForSubtreeByStyleParameter):
      GetNodesForSubtreeByStyleReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getNodesForSubtreeByStyle", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Finds nodes with a given computed style in a subtree.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getNodesForSubtreeByStyle(
    nodeId: Int,
    computedStyles: List<CSSComputedStyleProperty>,
    pierce: Boolean? = null
  ): GetNodesForSubtreeByStyleReturn {
    val parameter = GetNodesForSubtreeByStyleParameter(nodeId = nodeId, computedStyles =
        computedStyles, pierce = pierce)
    return getNodesForSubtreeByStyle(parameter)
  }

  /**
   * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is
   * either returned or not.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getNodeForLocation(args: GetNodeForLocationParameter):
      GetNodeForLocationReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getNodeForLocation", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is
   * either returned or not.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getNodeForLocation(
    x: Int,
    y: Int,
    includeUserAgentShadowDOM: Boolean? = null,
    ignorePointerEventsNone: Boolean? = null
  ): GetNodeForLocationReturn {
    val parameter = GetNodeForLocationParameter(x = x, y = y, includeUserAgentShadowDOM =
        includeUserAgentShadowDOM, ignorePointerEventsNone = ignorePointerEventsNone)
    return getNodeForLocation(parameter)
  }

  /**
   * Returns node's HTML markup.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getOuterHTML(args: GetOuterHTMLParameter): GetOuterHTMLReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getOuterHTML", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns node's HTML markup.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getOuterHTML(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null
  ): GetOuterHTMLReturn {
    val parameter = GetOuterHTMLParameter(nodeId = nodeId, backendNodeId = backendNodeId, objectId =
        objectId)
    return getOuterHTML(parameter)
  }

  /**
   * Returns the id of the nearest ancestor that is a relayout boundary.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getRelayoutBoundary(args: GetRelayoutBoundaryParameter):
      GetRelayoutBoundaryReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getRelayoutBoundary", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns the id of the nearest ancestor that is a relayout boundary.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getRelayoutBoundary(nodeId: Int): GetRelayoutBoundaryReturn {
    val parameter = GetRelayoutBoundaryParameter(nodeId = nodeId)
    return getRelayoutBoundary(parameter)
  }

  /**
   * Returns search results from given `fromIndex` to given `toIndex` from the search with the given
   * identifier.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getSearchResults(args: GetSearchResultsParameter): GetSearchResultsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getSearchResults", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns search results from given `fromIndex` to given `toIndex` from the search with the given
   * identifier.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getSearchResults(
    searchId: String,
    fromIndex: Int,
    toIndex: Int
  ): GetSearchResultsReturn {
    val parameter = GetSearchResultsParameter(searchId = searchId, fromIndex = fromIndex, toIndex =
        toIndex)
    return getSearchResults(parameter)
  }

  /**
   * Hides any highlight.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun hideHighlight(): Unit {
    val parameter = null
    client.callCommand("DOM.hideHighlight", parameter)
  }

  /**
   * Highlights DOM node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightNode(): Unit {
    val parameter = null
    client.callCommand("DOM.highlightNode", parameter)
  }

  /**
   * Highlights given rectangle.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun highlightRect(): Unit {
    val parameter = null
    client.callCommand("DOM.highlightRect", parameter)
  }

  /**
   * Marks last undoable state.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun markUndoableState(): Unit {
    val parameter = null
    client.callCommand("DOM.markUndoableState", parameter)
  }

  /**
   * Moves node into the new container, places it before the given anchor.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun moveTo(args: MoveToParameter): MoveToReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.moveTo", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Moves node into the new container, places it before the given anchor.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun moveTo(
    nodeId: Int,
    targetNodeId: Int,
    insertBeforeNodeId: Int? = null
  ): MoveToReturn {
    val parameter = MoveToParameter(nodeId = nodeId, targetNodeId = targetNodeId, insertBeforeNodeId
        = insertBeforeNodeId)
    return moveTo(parameter)
  }

  /**
   * Searches for a given string in the DOM tree. Use `getSearchResults` to access search results or
   * `cancelSearch` to end this search session.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun performSearch(args: PerformSearchParameter): PerformSearchReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.performSearch", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Searches for a given string in the DOM tree. Use `getSearchResults` to access search results or
   * `cancelSearch` to end this search session.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun performSearch(query: String, includeUserAgentShadowDOM: Boolean? = null):
      PerformSearchReturn {
    val parameter = PerformSearchParameter(query = query, includeUserAgentShadowDOM =
        includeUserAgentShadowDOM)
    return performSearch(parameter)
  }

  /**
   * Requests that the node is sent to the caller given its path. // FIXME, use XPath
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun pushNodeByPathToFrontend(args: PushNodeByPathToFrontendParameter):
      PushNodeByPathToFrontendReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.pushNodeByPathToFrontend", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Requests that the node is sent to the caller given its path. // FIXME, use XPath
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun pushNodeByPathToFrontend(path: String): PushNodeByPathToFrontendReturn {
    val parameter = PushNodeByPathToFrontendParameter(path = path)
    return pushNodeByPathToFrontend(parameter)
  }

  /**
   * Requests that a batch of nodes is sent to the caller given their backend node ids.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend
      fun pushNodesByBackendIdsToFrontend(args: PushNodesByBackendIdsToFrontendParameter):
      PushNodesByBackendIdsToFrontendReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.pushNodesByBackendIdsToFrontend", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Requests that a batch of nodes is sent to the caller given their backend node ids.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun pushNodesByBackendIdsToFrontend(backendNodeIds: List<Int>):
      PushNodesByBackendIdsToFrontendReturn {
    val parameter = PushNodesByBackendIdsToFrontendParameter(backendNodeIds = backendNodeIds)
    return pushNodesByBackendIdsToFrontend(parameter)
  }

  /**
   * Executes `querySelector` on a given node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun querySelector(args: QuerySelectorParameter): QuerySelectorReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.querySelector", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Executes `querySelector` on a given node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun querySelector(nodeId: Int, selector: String): QuerySelectorReturn {
    val parameter = QuerySelectorParameter(nodeId = nodeId, selector = selector)
    return querySelector(parameter)
  }

  /**
   * Executes `querySelectorAll` on a given node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun querySelectorAll(args: QuerySelectorAllParameter): QuerySelectorAllReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.querySelectorAll", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Executes `querySelectorAll` on a given node.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun querySelectorAll(nodeId: Int, selector: String): QuerySelectorAllReturn {
    val parameter = QuerySelectorAllParameter(nodeId = nodeId, selector = selector)
    return querySelectorAll(parameter)
  }

  /**
   * Re-does the last undone action.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun redo(): Unit {
    val parameter = null
    client.callCommand("DOM.redo", parameter)
  }

  /**
   * Removes attribute with given name from an element with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun removeAttribute(args: RemoveAttributeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.removeAttribute", parameter)
  }

  /**
   * Removes attribute with given name from an element with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun removeAttribute(nodeId: Int, name: String): Unit {
    val parameter = RemoveAttributeParameter(nodeId = nodeId, name = name)
    removeAttribute(parameter)
  }

  /**
   * Removes node with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun removeNode(args: RemoveNodeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.removeNode", parameter)
  }

  /**
   * Removes node with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun removeNode(nodeId: Int): Unit {
    val parameter = RemoveNodeParameter(nodeId = nodeId)
    removeNode(parameter)
  }

  /**
   * Requests that children of the node with given id are returned to the caller in form of
   * `setChildNodes` events where not only immediate children are retrieved, but all children down
   * to
   * the specified depth.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun requestChildNodes(args: RequestChildNodesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.requestChildNodes", parameter)
  }

  /**
   * Requests that children of the node with given id are returned to the caller in form of
   * `setChildNodes` events where not only immediate children are retrieved, but all children down
   * to
   * the specified depth.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun requestChildNodes(
    nodeId: Int,
    depth: Int? = null,
    pierce: Boolean? = null
  ): Unit {
    val parameter = RequestChildNodesParameter(nodeId = nodeId, depth = depth, pierce = pierce)
    requestChildNodes(parameter)
  }

  /**
   * Requests that the node is sent to the caller given the JavaScript node object reference. All
   * nodes that form the path from the node to the root are also sent to the client as a series of
   * `setChildNodes` notifications.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun requestNode(args: RequestNodeParameter): RequestNodeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.requestNode", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Requests that the node is sent to the caller given the JavaScript node object reference. All
   * nodes that form the path from the node to the root are also sent to the client as a series of
   * `setChildNodes` notifications.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun requestNode(objectId: String): RequestNodeReturn {
    val parameter = RequestNodeParameter(objectId = objectId)
    return requestNode(parameter)
  }

  /**
   * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun resolveNode(args: ResolveNodeParameter): ResolveNodeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.resolveNode", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun resolveNode(
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectGroup: String? = null,
    executionContextId: Int? = null
  ): ResolveNodeReturn {
    val parameter = ResolveNodeParameter(nodeId = nodeId, backendNodeId = backendNodeId, objectGroup
        = objectGroup, executionContextId = executionContextId)
    return resolveNode(parameter)
  }

  /**
   * Sets attribute for an element with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setAttributeValue(args: SetAttributeValueParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.setAttributeValue", parameter)
  }

  /**
   * Sets attribute for an element with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setAttributeValue(
    nodeId: Int,
    name: String,
    value: String
  ): Unit {
    val parameter = SetAttributeValueParameter(nodeId = nodeId, name = name, value = value)
    setAttributeValue(parameter)
  }

  /**
   * Sets attributes on element with given id. This method is useful when user edits some existing
   * attribute value and types in several attribute name/value pairs.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setAttributesAsText(args: SetAttributesAsTextParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.setAttributesAsText", parameter)
  }

  /**
   * Sets attributes on element with given id. This method is useful when user edits some existing
   * attribute value and types in several attribute name/value pairs.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setAttributesAsText(
    nodeId: Int,
    text: String,
    name: String? = null
  ): Unit {
    val parameter = SetAttributesAsTextParameter(nodeId = nodeId, text = text, name = name)
    setAttributesAsText(parameter)
  }

  /**
   * Sets files for the given file input element.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setFileInputFiles(args: SetFileInputFilesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.setFileInputFiles", parameter)
  }

  /**
   * Sets files for the given file input element.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setFileInputFiles(
    files: String,
    nodeId: Int? = null,
    backendNodeId: Int? = null,
    objectId: String? = null
  ): Unit {
    val parameter = SetFileInputFilesParameter(files = files, nodeId = nodeId, backendNodeId =
        backendNodeId, objectId = objectId)
    setFileInputFiles(parameter)
  }

  /**
   * Sets if stack traces should be captured for Nodes. See `Node.getNodeStackTraces`. Default is
   * disabled.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setNodeStackTracesEnabled(args: SetNodeStackTracesEnabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.setNodeStackTracesEnabled", parameter)
  }

  /**
   * Sets if stack traces should be captured for Nodes. See `Node.getNodeStackTraces`. Default is
   * disabled.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setNodeStackTracesEnabled(enable: Boolean): Unit {
    val parameter = SetNodeStackTracesEnabledParameter(enable = enable)
    setNodeStackTracesEnabled(parameter)
  }

  /**
   * Gets stack traces associated with a Node. As of now, only provides stack trace for Node
   * creation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getNodeStackTraces(args: GetNodeStackTracesParameter):
      GetNodeStackTracesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getNodeStackTraces", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Gets stack traces associated with a Node. As of now, only provides stack trace for Node
   * creation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getNodeStackTraces(nodeId: Int): GetNodeStackTracesReturn {
    val parameter = GetNodeStackTracesParameter(nodeId = nodeId)
    return getNodeStackTraces(parameter)
  }

  /**
   * Returns file information for the given
   * File wrapper.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getFileInfo(args: GetFileInfoParameter): GetFileInfoReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getFileInfo", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns file information for the given
   * File wrapper.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getFileInfo(objectId: String): GetFileInfoReturn {
    val parameter = GetFileInfoParameter(objectId = objectId)
    return getFileInfo(parameter)
  }

  /**
   * Enables console to refer to the node with given id via $x (see Command Line API for more
   * details
   * $x functions).
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setInspectedNode(args: SetInspectedNodeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.setInspectedNode", parameter)
  }

  /**
   * Enables console to refer to the node with given id via $x (see Command Line API for more
   * details
   * $x functions).
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setInspectedNode(nodeId: Int): Unit {
    val parameter = SetInspectedNodeParameter(nodeId = nodeId)
    setInspectedNode(parameter)
  }

  /**
   * Sets node name for a node with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setNodeName(args: SetNodeNameParameter): SetNodeNameReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.setNodeName", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Sets node name for a node with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setNodeName(nodeId: Int, name: String): SetNodeNameReturn {
    val parameter = SetNodeNameParameter(nodeId = nodeId, name = name)
    return setNodeName(parameter)
  }

  /**
   * Sets node value for a node with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setNodeValue(args: SetNodeValueParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.setNodeValue", parameter)
  }

  /**
   * Sets node value for a node with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setNodeValue(nodeId: Int, value: String): Unit {
    val parameter = SetNodeValueParameter(nodeId = nodeId, value = value)
    setNodeValue(parameter)
  }

  /**
   * Sets node HTML markup, returns new node id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setOuterHTML(args: SetOuterHTMLParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("DOM.setOuterHTML", parameter)
  }

  /**
   * Sets node HTML markup, returns new node id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setOuterHTML(nodeId: Int, outerHTML: String): Unit {
    val parameter = SetOuterHTMLParameter(nodeId = nodeId, outerHTML = outerHTML)
    setOuterHTML(parameter)
  }

  /**
   * Undoes the last performed action.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun undo(): Unit {
    val parameter = null
    client.callCommand("DOM.undo", parameter)
  }

  /**
   * Returns iframe node that owns iframe with the given domain.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getFrameOwner(args: GetFrameOwnerParameter): GetFrameOwnerReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("DOM.getFrameOwner", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns iframe node that owns iframe with the given domain.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getFrameOwner(frameId: String): GetFrameOwnerReturn {
    val parameter = GetFrameOwnerParameter(frameId = frameId)
    return getFrameOwner(parameter)
  }

  /**
   * Backend node with a friendly name.
   */
  @Serializable
  public data class BackendNode(
    /**
     * `Node`'s nodeType.
     */
    public val nodeType: Int,
    /**
     * `Node`'s nodeName.
     */
    public val nodeName: String,
    public val backendNodeId: Int
  )

  /**
   * Pseudo element type.
   */
  @Serializable
  public enum class PseudoType {
    @SerialName("first-line")
    FIRST_LINE,
    @SerialName("first-letter")
    FIRST_LETTER,
    @SerialName("before")
    BEFORE,
    @SerialName("after")
    AFTER,
    @SerialName("marker")
    MARKER,
    @SerialName("backdrop")
    BACKDROP,
    @SerialName("selection")
    SELECTION,
    @SerialName("target-text")
    TARGET_TEXT,
    @SerialName("spelling-error")
    SPELLING_ERROR,
    @SerialName("grammar-error")
    GRAMMAR_ERROR,
    @SerialName("first-line-inherited")
    FIRST_LINE_INHERITED,
    @SerialName("scrollbar")
    SCROLLBAR,
    @SerialName("scrollbar-thumb")
    SCROLLBAR_THUMB,
    @SerialName("scrollbar-button")
    SCROLLBAR_BUTTON,
    @SerialName("scrollbar-track")
    SCROLLBAR_TRACK,
    @SerialName("scrollbar-track-piece")
    SCROLLBAR_TRACK_PIECE,
    @SerialName("scrollbar-corner")
    SCROLLBAR_CORNER,
    @SerialName("resizer")
    RESIZER,
    @SerialName("input-list-button")
    INPUT_LIST_BUTTON,
  }

  /**
   * Shadow root type.
   */
  @Serializable
  public enum class ShadowRootType {
    @SerialName("user-agent")
    USER_AGENT,
    @SerialName("open")
    OPEN,
    @SerialName("closed")
    CLOSED,
  }

  /**
   * DOM interaction is implemented in terms of mirror objects that represent the actual DOM nodes.
   * DOMNode is a base node mirror type.
   */
  @Serializable
  public data class Node(
    /**
     * Node identifier that is passed into the rest of the DOM messages as the `nodeId`. Backend
     * will only push node with given `id` once. It is aware of all requested nodes and will only
     * fire DOM events for nodes known to the client.
     */
    public val nodeId: Int,
    /**
     * The id of the parent node if any.
     */
    public val parentId: Int? = null,
    /**
     * The BackendNodeId for this node.
     */
    public val backendNodeId: Int,
    /**
     * `Node`'s nodeType.
     */
    public val nodeType: Int,
    /**
     * `Node`'s nodeName.
     */
    public val nodeName: String,
    /**
     * `Node`'s localName.
     */
    public val localName: String,
    /**
     * `Node`'s nodeValue.
     */
    public val nodeValue: String,
    /**
     * Child count for `Container` nodes.
     */
    public val childNodeCount: Int? = null,
    /**
     * Child nodes of this node when requested with children.
     */
    public val children: List<Node>? = null,
    /**
     * Attributes of the `Element` node in the form of flat array `[name1, value1, name2, value2]`.
     */
    public val attributes: String? = null,
    /**
     * Document URL that `Document` or `FrameOwner` node points to.
     */
    public val documentURL: String? = null,
    /**
     * Base URL that `Document` or `FrameOwner` node uses for URL completion.
     */
    public val baseURL: String? = null,
    /**
     * `DocumentType`'s publicId.
     */
    public val publicId: String? = null,
    /**
     * `DocumentType`'s systemId.
     */
    public val systemId: String? = null,
    /**
     * `DocumentType`'s internalSubset.
     */
    public val internalSubset: String? = null,
    /**
     * `Document`'s XML version in case of XML documents.
     */
    public val xmlVersion: String? = null,
    /**
     * `Attr`'s name.
     */
    public val name: String? = null,
    /**
     * `Attr`'s value.
     */
    public val value: String? = null,
    /**
     * Pseudo element type for this node.
     */
    public val pseudoType: PseudoType? = null,
    /**
     * Shadow root type.
     */
    public val shadowRootType: ShadowRootType? = null,
    /**
     * Frame ID for frame owner elements.
     */
    public val frameId: String? = null,
    /**
     * Content document for frame owner elements.
     */
    public val contentDocument: Node? = null,
    /**
     * Shadow root list for given element host.
     */
    public val shadowRoots: List<Node>? = null,
    /**
     * Content document fragment for template elements.
     */
    public val templateContent: Node? = null,
    /**
     * Pseudo elements associated with this node.
     */
    public val pseudoElements: List<Node>? = null,
    /**
     * Import document for the HTMLImport links.
     */
    public val importedDocument: Node? = null,
    /**
     * Distributed nodes for given insertion point.
     */
    public val distributedNodes: List<BackendNode>? = null,
    /**
     * Whether the node is SVG.
     */
    public val isSVG: Boolean? = null
  )

  /**
   * A structure holding an RGBA color.
   */
  @Serializable
  public data class RGBA(
    /**
     * The red component, in the [0-255] range.
     */
    public val r: Int,
    /**
     * The green component, in the [0-255] range.
     */
    public val g: Int,
    /**
     * The blue component, in the [0-255] range.
     */
    public val b: Int,
    /**
     * The alpha component, in the [0-1] range (default: 1).
     */
    public val a: Double? = null
  )

  /**
   * Box model.
   */
  @Serializable
  public data class BoxModel(
    /**
     * Content box
     */
    public val content: List<Double>,
    /**
     * Padding box
     */
    public val padding: List<Double>,
    /**
     * Border box
     */
    public val border: List<Double>,
    /**
     * Margin box
     */
    public val margin: List<Double>,
    /**
     * Node width
     */
    public val width: Int,
    /**
     * Node height
     */
    public val height: Int,
    /**
     * Shape outside coordinates
     */
    public val shapeOutside: ShapeOutsideInfo? = null
  )

  /**
   * CSS Shape Outside details.
   */
  @Serializable
  public data class ShapeOutsideInfo(
    /**
     * Shape bounds
     */
    public val bounds: List<Double>,
    /**
     * Shape coordinate details
     */
    public val shape: JsonElement,
    /**
     * Margin shape bounds
     */
    public val marginShape: JsonElement
  )

  /**
   * Rectangle.
   */
  @Serializable
  public data class Rect(
    /**
     * X coordinate
     */
    public val x: Double,
    /**
     * Y coordinate
     */
    public val y: Double,
    /**
     * Rectangle width
     */
    public val width: Double,
    /**
     * Rectangle height
     */
    public val height: Double
  )

  @Serializable
  public data class CSSComputedStyleProperty(
    /**
     * Computed style property name.
     */
    public val name: String,
    /**
     * Computed style property value.
     */
    public val value: String
  )

  /**
   * Fired when `Element`'s attribute is modified.
   */
  public data class AttributeModifiedParameter(
    /**
     * Id of the node that has changed.
     */
    public val nodeId: Int,
    /**
     * Attribute name.
     */
    public val name: String,
    /**
     * Attribute value.
     */
    public val value: String
  )

  /**
   * Fired when `Element`'s attribute is removed.
   */
  public data class AttributeRemovedParameter(
    /**
     * Id of the node that has changed.
     */
    public val nodeId: Int,
    /**
     * A ttribute name.
     */
    public val name: String
  )

  /**
   * Mirrors `DOMCharacterDataModified` event.
   */
  public data class CharacterDataModifiedParameter(
    /**
     * Id of the node that has changed.
     */
    public val nodeId: Int,
    /**
     * New text value.
     */
    public val characterData: String
  )

  /**
   * Fired when `Container`'s child node count has changed.
   */
  public data class ChildNodeCountUpdatedParameter(
    /**
     * Id of the node that has changed.
     */
    public val nodeId: Int,
    /**
     * New node count.
     */
    public val childNodeCount: Int
  )

  /**
   * Mirrors `DOMNodeInserted` event.
   */
  public data class ChildNodeInsertedParameter(
    /**
     * Id of the node that has changed.
     */
    public val parentNodeId: Int,
    /**
     * If of the previous siblint.
     */
    public val previousNodeId: Int,
    /**
     * Inserted node data.
     */
    public val node: Node
  )

  /**
   * Mirrors `DOMNodeRemoved` event.
   */
  public data class ChildNodeRemovedParameter(
    /**
     * Parent id.
     */
    public val parentNodeId: Int,
    /**
     * Id of the node that has been removed.
     */
    public val nodeId: Int
  )

  /**
   * Called when distrubution is changed.
   */
  public data class DistributedNodesUpdatedParameter(
    /**
     * Insertion point where distrubuted nodes were updated.
     */
    public val insertionPointId: Int,
    /**
     * Distributed nodes for given insertion point.
     */
    public val distributedNodes: List<BackendNode>
  )

  /**
   * Fired when `Element`'s inline style is modified via a CSS property modification.
   */
  public data class InlineStyleInvalidatedParameter(
    /**
     * Ids of the nodes for which the inline styles have been invalidated.
     */
    public val nodeIds: List<Int>
  )

  /**
   * Called when a pseudo element is added to an element.
   */
  public data class PseudoElementAddedParameter(
    /**
     * Pseudo element's parent element id.
     */
    public val parentId: Int,
    /**
     * The added pseudo element.
     */
    public val pseudoElement: Node
  )

  /**
   * Called when a pseudo element is removed from an element.
   */
  public data class PseudoElementRemovedParameter(
    /**
     * Pseudo element's parent element id.
     */
    public val parentId: Int,
    /**
     * The removed pseudo element id.
     */
    public val pseudoElementId: Int
  )

  /**
   * Fired when backend wants to provide client with the missing DOM structure. This happens upon
   * most of the calls requesting node ids.
   */
  public data class SetChildNodesParameter(
    /**
     * Parent node id to populate with children.
     */
    public val parentId: Int,
    /**
     * Child nodes array.
     */
    public val nodes: List<Node>
  )

  /**
   * Called when shadow root is popped from the element.
   */
  public data class ShadowRootPoppedParameter(
    /**
     * Host element id.
     */
    public val hostId: Int,
    /**
     * Shadow root id.
     */
    public val rootId: Int
  )

  /**
   * Called when shadow root is pushed into the element.
   */
  public data class ShadowRootPushedParameter(
    /**
     * Host element id.
     */
    public val hostId: Int,
    /**
     * Shadow root.
     */
    public val root: Node
  )

  @Serializable
  public data class CollectClassNamesFromSubtreeParameter(
    /**
     * Id of the node to collect class names.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class CollectClassNamesFromSubtreeReturn(
    /**
     * Class name list.
     */
    public val classNames: String
  )

  @Serializable
  public data class CopyToParameter(
    /**
     * Id of the node to copy.
     */
    public val nodeId: Int,
    /**
     * Id of the element to drop the copy into.
     */
    public val targetNodeId: Int,
    /**
     * Drop the copy before this node (if absent, the copy becomes the last child of
     * `targetNodeId`).
     */
    public val insertBeforeNodeId: Int? = null
  )

  @Serializable
  public data class CopyToReturn(
    /**
     * Id of the node clone.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class DescribeNodeParameter(
    /**
     * Identifier of the node.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper.
     */
    public val objectId: String? = null,
    /**
     * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
     * entire subtree or provide an integer larger than 0.
     */
    public val depth: Int? = null,
    /**
     * Whether or not iframes and shadow roots should be traversed when returning the subtree
     * (default is false).
     */
    public val pierce: Boolean? = null
  )

  @Serializable
  public data class DescribeNodeReturn(
    /**
     * Node description.
     */
    public val node: Node
  )

  @Serializable
  public data class ScrollIntoViewIfNeededParameter(
    /**
     * Identifier of the node.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper.
     */
    public val objectId: String? = null,
    /**
     * The rect to be scrolled into view, relative to the node's border box, in CSS pixels.
     * When omitted, center of the node will be used, similar to Element.scrollIntoView.
     */
    public val rect: Rect? = null
  )

  @Serializable
  public data class DiscardSearchResultsParameter(
    /**
     * Unique search session identifier.
     */
    public val searchId: String
  )

  @Serializable
  public data class FocusParameter(
    /**
     * Identifier of the node.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper.
     */
    public val objectId: String? = null
  )

  @Serializable
  public data class GetAttributesParameter(
    /**
     * Id of the node to retrieve attibutes for.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class GetAttributesReturn(
    /**
     * An interleaved array of node attribute names and values.
     */
    public val attributes: String
  )

  @Serializable
  public data class GetBoxModelParameter(
    /**
     * Identifier of the node.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper.
     */
    public val objectId: String? = null
  )

  @Serializable
  public data class GetBoxModelReturn(
    /**
     * Box model for the node.
     */
    public val model: BoxModel
  )

  @Serializable
  public data class GetContentQuadsParameter(
    /**
     * Identifier of the node.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper.
     */
    public val objectId: String? = null
  )

  @Serializable
  public data class GetContentQuadsReturn(
    /**
     * Quads that describe node layout relative to viewport.
     */
    public val quads: List<List<Double>>
  )

  @Serializable
  public data class GetDocumentParameter(
    /**
     * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
     * entire subtree or provide an integer larger than 0.
     */
    public val depth: Int? = null,
    /**
     * Whether or not iframes and shadow roots should be traversed when returning the subtree
     * (default is false).
     */
    public val pierce: Boolean? = null
  )

  @Serializable
  public data class GetDocumentReturn(
    /**
     * Resulting node.
     */
    public val root: Node
  )

  @Serializable
  public data class GetFlattenedDocumentParameter(
    /**
     * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
     * entire subtree or provide an integer larger than 0.
     */
    public val depth: Int? = null,
    /**
     * Whether or not iframes and shadow roots should be traversed when returning the subtree
     * (default is false).
     */
    public val pierce: Boolean? = null
  )

  @Serializable
  public data class GetFlattenedDocumentReturn(
    /**
     * Resulting node.
     */
    public val nodes: List<Node>
  )

  @Serializable
  public data class GetNodesForSubtreeByStyleParameter(
    /**
     * Node ID pointing to the root of a subtree.
     */
    public val nodeId: Int,
    /**
     * The style to filter nodes by (includes nodes if any of properties matches).
     */
    public val computedStyles: List<CSSComputedStyleProperty>,
    /**
     * Whether or not iframes and shadow roots in the same target should be traversed when returning
     * the
     * results (default is false).
     */
    public val pierce: Boolean? = null
  )

  @Serializable
  public data class GetNodesForSubtreeByStyleReturn(
    /**
     * Resulting nodes.
     */
    public val nodeIds: List<Int>
  )

  @Serializable
  public data class GetNodeForLocationParameter(
    /**
     * X coordinate.
     */
    public val x: Int,
    /**
     * Y coordinate.
     */
    public val y: Int,
    /**
     * False to skip to the nearest non-UA shadow root ancestor (default: false).
     */
    public val includeUserAgentShadowDOM: Boolean? = null,
    /**
     * Whether to ignore pointer-events: none on elements and hit test them.
     */
    public val ignorePointerEventsNone: Boolean? = null
  )

  @Serializable
  public data class GetNodeForLocationReturn(
    /**
     * Resulting node.
     */
    public val backendNodeId: Int,
    /**
     * Frame this node belongs to.
     */
    public val frameId: String,
    /**
     * Id of the node at given coordinates, only when enabled and requested document.
     */
    public val nodeId: Int?
  )

  @Serializable
  public data class GetOuterHTMLParameter(
    /**
     * Identifier of the node.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper.
     */
    public val objectId: String? = null
  )

  @Serializable
  public data class GetOuterHTMLReturn(
    /**
     * Outer HTML markup.
     */
    public val outerHTML: String
  )

  @Serializable
  public data class GetRelayoutBoundaryParameter(
    /**
     * Id of the node.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class GetRelayoutBoundaryReturn(
    /**
     * Relayout boundary node id for the given node.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class GetSearchResultsParameter(
    /**
     * Unique search session identifier.
     */
    public val searchId: String,
    /**
     * Start index of the search result to be returned.
     */
    public val fromIndex: Int,
    /**
     * End index of the search result to be returned.
     */
    public val toIndex: Int
  )

  @Serializable
  public data class GetSearchResultsReturn(
    /**
     * Ids of the search result nodes.
     */
    public val nodeIds: List<Int>
  )

  @Serializable
  public data class MoveToParameter(
    /**
     * Id of the node to move.
     */
    public val nodeId: Int,
    /**
     * Id of the element to drop the moved node into.
     */
    public val targetNodeId: Int,
    /**
     * Drop node before this one (if absent, the moved node becomes the last child of
     * `targetNodeId`).
     */
    public val insertBeforeNodeId: Int? = null
  )

  @Serializable
  public data class MoveToReturn(
    /**
     * New id of the moved node.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class PerformSearchParameter(
    /**
     * Plain text or query selector or XPath search query.
     */
    public val query: String,
    /**
     * True to search in user agent shadow DOM.
     */
    public val includeUserAgentShadowDOM: Boolean? = null
  )

  @Serializable
  public data class PerformSearchReturn(
    /**
     * Unique search session identifier.
     */
    public val searchId: String,
    /**
     * Number of search results.
     */
    public val resultCount: Int
  )

  @Serializable
  public data class PushNodeByPathToFrontendParameter(
    /**
     * Path to node in the proprietary format.
     */
    public val path: String
  )

  @Serializable
  public data class PushNodeByPathToFrontendReturn(
    /**
     * Id of the node for given path.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class PushNodesByBackendIdsToFrontendParameter(
    /**
     * The array of backend node ids.
     */
    public val backendNodeIds: List<Int>
  )

  @Serializable
  public data class PushNodesByBackendIdsToFrontendReturn(
    /**
     * The array of ids of pushed nodes that correspond to the backend ids specified in
     * backendNodeIds.
     */
    public val nodeIds: List<Int>
  )

  @Serializable
  public data class QuerySelectorParameter(
    /**
     * Id of the node to query upon.
     */
    public val nodeId: Int,
    /**
     * Selector string.
     */
    public val selector: String
  )

  @Serializable
  public data class QuerySelectorReturn(
    /**
     * Query selector result.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class QuerySelectorAllParameter(
    /**
     * Id of the node to query upon.
     */
    public val nodeId: Int,
    /**
     * Selector string.
     */
    public val selector: String
  )

  @Serializable
  public data class QuerySelectorAllReturn(
    /**
     * Query selector result.
     */
    public val nodeIds: List<Int>
  )

  @Serializable
  public data class RemoveAttributeParameter(
    /**
     * Id of the element to remove attribute from.
     */
    public val nodeId: Int,
    /**
     * Name of the attribute to remove.
     */
    public val name: String
  )

  @Serializable
  public data class RemoveNodeParameter(
    /**
     * Id of the node to remove.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class RequestChildNodesParameter(
    /**
     * Id of the node to get children for.
     */
    public val nodeId: Int,
    /**
     * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
     * entire subtree or provide an integer larger than 0.
     */
    public val depth: Int? = null,
    /**
     * Whether or not iframes and shadow roots should be traversed when returning the sub-tree
     * (default is false).
     */
    public val pierce: Boolean? = null
  )

  @Serializable
  public data class RequestNodeParameter(
    /**
     * JavaScript object id to convert into node.
     */
    public val objectId: String
  )

  @Serializable
  public data class RequestNodeReturn(
    /**
     * Node id for given object.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class ResolveNodeParameter(
    /**
     * Id of the node to resolve.
     */
    public val nodeId: Int? = null,
    /**
     * Backend identifier of the node to resolve.
     */
    public val backendNodeId: Int? = null,
    /**
     * Symbolic group name that can be used to release multiple objects.
     */
    public val objectGroup: String? = null,
    /**
     * Execution context in which to resolve the node.
     */
    public val executionContextId: Int? = null
  )

  @Serializable
  public data class ResolveNodeReturn(
    /**
     * JavaScript object wrapper for given node.
     */
    public val `object`: Runtime.RemoteObject
  )

  @Serializable
  public data class SetAttributeValueParameter(
    /**
     * Id of the element to set attribute for.
     */
    public val nodeId: Int,
    /**
     * Attribute name.
     */
    public val name: String,
    /**
     * Attribute value.
     */
    public val value: String
  )

  @Serializable
  public data class SetAttributesAsTextParameter(
    /**
     * Id of the element to set attributes for.
     */
    public val nodeId: Int,
    /**
     * Text with a number of attributes. Will parse this text using HTML parser.
     */
    public val text: String,
    /**
     * Attribute name to replace with new attributes derived from text in case text parsed
     * successfully.
     */
    public val name: String? = null
  )

  @Serializable
  public data class SetFileInputFilesParameter(
    /**
     * Array of file paths to set.
     */
    public val files: String,
    /**
     * Identifier of the node.
     */
    public val nodeId: Int? = null,
    /**
     * Identifier of the backend node.
     */
    public val backendNodeId: Int? = null,
    /**
     * JavaScript object id of the node wrapper.
     */
    public val objectId: String? = null
  )

  @Serializable
  public data class SetNodeStackTracesEnabledParameter(
    /**
     * Enable or disable.
     */
    public val enable: Boolean
  )

  @Serializable
  public data class GetNodeStackTracesParameter(
    /**
     * Id of the node to get stack traces for.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class GetNodeStackTracesReturn(
    /**
     * Creation stack trace, if available.
     */
    public val creation: Runtime.StackTrace?
  )

  @Serializable
  public data class GetFileInfoParameter(
    /**
     * JavaScript object id of the node wrapper.
     */
    public val objectId: String
  )

  @Serializable
  public data class GetFileInfoReturn(
    public val path: String
  )

  @Serializable
  public data class SetInspectedNodeParameter(
    /**
     * DOM node id to be accessible by means of $x command line API.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class SetNodeNameParameter(
    /**
     * Id of the node to set name for.
     */
    public val nodeId: Int,
    /**
     * New node's name.
     */
    public val name: String
  )

  @Serializable
  public data class SetNodeNameReturn(
    /**
     * New node's id.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class SetNodeValueParameter(
    /**
     * Id of the node to set value for.
     */
    public val nodeId: Int,
    /**
     * New node's value.
     */
    public val value: String
  )

  @Serializable
  public data class SetOuterHTMLParameter(
    /**
     * Id of the node to set markup for.
     */
    public val nodeId: Int,
    /**
     * Outer HTML markup to set.
     */
    public val outerHTML: String
  )

  @Serializable
  public data class GetFrameOwnerParameter(
    public val frameId: String
  )

  @Serializable
  public data class GetFrameOwnerReturn(
    /**
     * Resulting node.
     */
    public val backendNodeId: Int,
    /**
     * Id of the node at given coordinates, only when enabled and requested document.
     */
    public val nodeId: Int?
  )
}
