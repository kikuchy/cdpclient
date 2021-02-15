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

public val CDPClient.css: CSS
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(CSS(this))

/**
 * This domain exposes CSS read/write operations. All CSS objects (stylesheets, rules, and styles)
 * have an associated `id` used in subsequent operations on the related object. Each object type has
 * a specific `id` structure, and those are not interchangeable between objects of different kinds.
 * CSS objects can be loaded using the `get*ForNode()` calls (which accept a DOM node id). A client
 * can also keep track of stylesheets via the `styleSheetAdded`/`styleSheetRemoved` events and
 * subsequently load the required stylesheet contents using the `getStyleSheet[Text]()` methods.
 */
public class CSS(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  public val fontsUpdated: Flow<FontsUpdatedParameter> = client
          .events
          .filter {
              it.method == "fontsUpdated"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val mediaQueryResultChanged: Flow<Unit> = client
          .events
          .filter {
              it.method == "mediaQueryResultChanged"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val styleSheetAdded: Flow<StyleSheetAddedParameter> = client
          .events
          .filter {
              it.method == "styleSheetAdded"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val styleSheetChanged: Flow<StyleSheetChangedParameter> = client
          .events
          .filter {
              it.method == "styleSheetChanged"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  @ExperimentalCoroutinesApi
  public val styleSheetRemoved: Flow<StyleSheetRemovedParameter> = client
          .events
          .filter {
              it.method == "styleSheetRemoved"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Inserts a new rule with the given `ruleText` in a stylesheet with given `styleSheetId`, at the
   * position specified by `location`.
   */
  @ExperimentalCoroutinesApi
  public suspend fun addRule(args: AddRuleParameter): AddRuleReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.addRule", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun addRule(
    styleSheetId: String,
    ruleText: String,
    location: SourceRange
  ): AddRuleReturn {
    val parameter = AddRuleParameter(styleSheetId = styleSheetId,ruleText = ruleText,location =
        location)
    return addRule(parameter)
  }

  /**
   * Returns all class names from specified stylesheet.
   */
  @ExperimentalCoroutinesApi
  public suspend fun collectClassNames(args: CollectClassNamesParameter): CollectClassNamesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.collectClassNames", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun collectClassNames(styleSheetId: String): CollectClassNamesReturn {
    val parameter = CollectClassNamesParameter(styleSheetId = styleSheetId)
    return collectClassNames(parameter)
  }

  /**
   * Creates a new special "via-inspector" stylesheet in the frame with given `frameId`.
   */
  @ExperimentalCoroutinesApi
  public suspend fun createStyleSheet(args: CreateStyleSheetParameter): CreateStyleSheetReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.createStyleSheet", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun createStyleSheet(frameId: String): CreateStyleSheetReturn {
    val parameter = CreateStyleSheetParameter(frameId = frameId)
    return createStyleSheet(parameter)
  }

  /**
   * Disables the CSS agent for the given page.
   */
  @ExperimentalCoroutinesApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("CSS.disable", parameter)
  }

  /**
   * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been
   * enabled until the result of this command is received.
   */
  @ExperimentalCoroutinesApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("CSS.enable", parameter)
  }

  /**
   * Ensures that the given node will have specified pseudo-classes whenever its style is computed
   * by
   * the browser.
   */
  @ExperimentalCoroutinesApi
  public suspend fun forcePseudoState(args: ForcePseudoStateParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("CSS.forcePseudoState", parameter)
  }

  public suspend fun forcePseudoState(nodeId: Int, forcedPseudoClasses: String): Unit {
    val parameter = ForcePseudoStateParameter(nodeId = nodeId,forcedPseudoClasses =
        forcedPseudoClasses)
    forcePseudoState(parameter)
  }

  @ExperimentalCoroutinesApi
  public suspend fun getBackgroundColors(args: GetBackgroundColorsParameter):
      GetBackgroundColorsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.getBackgroundColors", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getBackgroundColors(nodeId: Int): GetBackgroundColorsReturn {
    val parameter = GetBackgroundColorsParameter(nodeId = nodeId)
    return getBackgroundColors(parameter)
  }

  /**
   * Returns the computed style for a DOM node identified by `nodeId`.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getComputedStyleForNode(args: GetComputedStyleForNodeParameter):
      GetComputedStyleForNodeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.getComputedStyleForNode", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getComputedStyleForNode(nodeId: Int): GetComputedStyleForNodeReturn {
    val parameter = GetComputedStyleForNodeParameter(nodeId = nodeId)
    return getComputedStyleForNode(parameter)
  }

  /**
   * Returns the styles defined inline (explicitly in the "style" attribute and implicitly, using
   * DOM
   * attributes) for a DOM node identified by `nodeId`.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getInlineStylesForNode(args: GetInlineStylesForNodeParameter):
      GetInlineStylesForNodeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.getInlineStylesForNode", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getInlineStylesForNode(nodeId: Int): GetInlineStylesForNodeReturn {
    val parameter = GetInlineStylesForNodeParameter(nodeId = nodeId)
    return getInlineStylesForNode(parameter)
  }

  /**
   * Returns requested styles for a DOM node identified by `nodeId`.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getMatchedStylesForNode(args: GetMatchedStylesForNodeParameter):
      GetMatchedStylesForNodeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.getMatchedStylesForNode", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getMatchedStylesForNode(nodeId: Int): GetMatchedStylesForNodeReturn {
    val parameter = GetMatchedStylesForNodeParameter(nodeId = nodeId)
    return getMatchedStylesForNode(parameter)
  }

  /**
   * Returns all media queries parsed by the rendering engine.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getMediaQueries(): GetMediaQueriesReturn {
    val parameter = null
    val result = client.callCommand("CSS.getMediaQueries", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Requests information about platform fonts which we used to render child TextNodes in the given
   * node.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getPlatformFontsForNode(args: GetPlatformFontsForNodeParameter):
      GetPlatformFontsForNodeReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.getPlatformFontsForNode", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getPlatformFontsForNode(nodeId: Int): GetPlatformFontsForNodeReturn {
    val parameter = GetPlatformFontsForNodeParameter(nodeId = nodeId)
    return getPlatformFontsForNode(parameter)
  }

  /**
   * Returns the current textual content for a stylesheet.
   */
  @ExperimentalCoroutinesApi
  public suspend fun getStyleSheetText(args: GetStyleSheetTextParameter): GetStyleSheetTextReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.getStyleSheetText", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getStyleSheetText(styleSheetId: String): GetStyleSheetTextReturn {
    val parameter = GetStyleSheetTextParameter(styleSheetId = styleSheetId)
    return getStyleSheetText(parameter)
  }

  /**
   * Starts tracking the given computed styles for updates. The specified array of properties
   * replaces the one previously specified. Pass empty array to disable tracking.
   * Use takeComputedStyleUpdates to retrieve the list of nodes that had properties modified.
   * The changes to computed style properties are only tracked for nodes pushed to the front-end
   * by the DOM agent. If no changes to the tracked properties occur after the node has been pushed
   * to the front-end, no updates will be issued for the node.
   */
  @ExperimentalCoroutinesApi
  public suspend fun trackComputedStyleUpdates(args: TrackComputedStyleUpdatesParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("CSS.trackComputedStyleUpdates", parameter)
  }

  public suspend fun trackComputedStyleUpdates(propertiesToTrack: List<CSSComputedStyleProperty>):
      Unit {
    val parameter = TrackComputedStyleUpdatesParameter(propertiesToTrack = propertiesToTrack)
    trackComputedStyleUpdates(parameter)
  }

  /**
   * Polls the next batch of computed style updates.
   */
  @ExperimentalCoroutinesApi
  public suspend fun takeComputedStyleUpdates(): TakeComputedStyleUpdatesReturn {
    val parameter = null
    val result = client.callCommand("CSS.takeComputedStyleUpdates", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Find a rule with the given active property for the given node and set the new value for this
   * property
   */
  @ExperimentalCoroutinesApi
  public suspend
      fun setEffectivePropertyValueForNode(args: SetEffectivePropertyValueForNodeParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("CSS.setEffectivePropertyValueForNode", parameter)
  }

  public suspend fun setEffectivePropertyValueForNode(
    nodeId: Int,
    propertyName: String,
    value: String
  ): Unit {
    val parameter = SetEffectivePropertyValueForNodeParameter(nodeId = nodeId,propertyName =
        propertyName,value = value)
    setEffectivePropertyValueForNode(parameter)
  }

  /**
   * Modifies the keyframe rule key text.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setKeyframeKey(args: SetKeyframeKeyParameter): SetKeyframeKeyReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.setKeyframeKey", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setKeyframeKey(
    styleSheetId: String,
    range: SourceRange,
    keyText: String
  ): SetKeyframeKeyReturn {
    val parameter = SetKeyframeKeyParameter(styleSheetId = styleSheetId,range = range,keyText =
        keyText)
    return setKeyframeKey(parameter)
  }

  /**
   * Modifies the rule selector.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setMediaText(args: SetMediaTextParameter): SetMediaTextReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.setMediaText", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setMediaText(
    styleSheetId: String,
    range: SourceRange,
    text: String
  ): SetMediaTextReturn {
    val parameter = SetMediaTextParameter(styleSheetId = styleSheetId,range = range,text = text)
    return setMediaText(parameter)
  }

  /**
   * Modifies the rule selector.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setRuleSelector(args: SetRuleSelectorParameter): SetRuleSelectorReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.setRuleSelector", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setRuleSelector(
    styleSheetId: String,
    range: SourceRange,
    selector: String
  ): SetRuleSelectorReturn {
    val parameter = SetRuleSelectorParameter(styleSheetId = styleSheetId,range = range,selector =
        selector)
    return setRuleSelector(parameter)
  }

  /**
   * Sets the new stylesheet text.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setStyleSheetText(args: SetStyleSheetTextParameter): SetStyleSheetTextReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.setStyleSheetText", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setStyleSheetText(styleSheetId: String, text: String):
      SetStyleSheetTextReturn {
    val parameter = SetStyleSheetTextParameter(styleSheetId = styleSheetId,text = text)
    return setStyleSheetText(parameter)
  }

  /**
   * Applies specified style edits one after another in the given order.
   */
  @ExperimentalCoroutinesApi
  public suspend fun setStyleTexts(args: SetStyleTextsParameter): SetStyleTextsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("CSS.setStyleTexts", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setStyleTexts(edits: List<StyleDeclarationEdit>): SetStyleTextsReturn {
    val parameter = SetStyleTextsParameter(edits = edits)
    return setStyleTexts(parameter)
  }

  /**
   * Enables the selector recording.
   */
  @ExperimentalCoroutinesApi
  public suspend fun startRuleUsageTracking(): Unit {
    val parameter = null
    client.callCommand("CSS.startRuleUsageTracking", parameter)
  }

  /**
   * Stop tracking rule usage and return the list of rules that were used since last call to
   * `takeCoverageDelta` (or since start of coverage instrumentation)
   */
  @ExperimentalCoroutinesApi
  public suspend fun stopRuleUsageTracking(): StopRuleUsageTrackingReturn {
    val parameter = null
    val result = client.callCommand("CSS.stopRuleUsageTracking", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Obtain list of rules that became used since last call to this method (or since start of
   * coverage
   * instrumentation)
   */
  @ExperimentalCoroutinesApi
  public suspend fun takeCoverageDelta(): TakeCoverageDeltaReturn {
    val parameter = null
    val result = client.callCommand("CSS.takeCoverageDelta", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Enables/disables rendering of local CSS fonts (enabled by default).
   */
  @ExperimentalCoroutinesApi
  public suspend fun setLocalFontsEnabled(args: SetLocalFontsEnabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("CSS.setLocalFontsEnabled", parameter)
  }

  public suspend fun setLocalFontsEnabled(enabled: Boolean): Unit {
    val parameter = SetLocalFontsEnabledParameter(enabled = enabled)
    setLocalFontsEnabled(parameter)
  }

  /**
   * Stylesheet type: "injected" for stylesheets injected via extension, "user-agent" for user-agent
   * stylesheets, "inspector" for stylesheets created by the inspector (i.e. those holding the "via
   * inspector" rules), "regular" for regular stylesheets.
   */
  @Serializable
  public enum class StyleSheetOrigin {
    @SerialName("injected")
    INJECTED,
    @SerialName("user-agent")
    USER_AGENT,
    @SerialName("inspector")
    INSPECTOR,
    @SerialName("regular")
    REGULAR,
  }

  /**
   * CSS rule collection for a single pseudo style.
   */
  @Serializable
  public class PseudoElementMatches(
    /**
     * Pseudo element type.
     */
    public val pseudoType: DOM.PseudoType,
    /**
     * Matches of CSS rules applicable to the pseudo style.
     */
    public val matches: List<RuleMatch>
  )

  /**
   * Inherited CSS rule collection from ancestor node.
   */
  @Serializable
  public class InheritedStyleEntry(
    /**
     * The ancestor node's inline style, if any, in the style inheritance chain.
     */
    public val inlineStyle: CSSStyle? = null,
    /**
     * Matches of CSS rules matching the ancestor node in the style inheritance chain.
     */
    public val matchedCSSRules: List<RuleMatch>
  )

  /**
   * Match data for a CSS rule.
   */
  @Serializable
  public class RuleMatch(
    /**
     * CSS rule in the match.
     */
    public val rule: CSSRule,
    /**
     * Matching selector indices in the rule's selectorList selectors (0-based).
     */
    public val matchingSelectors: Int
  )

  /**
   * Data for a simple selector (these are delimited by commas in a selector list).
   */
  @Serializable
  public class Value(
    /**
     * Value text.
     */
    public val text: String,
    /**
     * Value range in the underlying resource (if available).
     */
    public val range: SourceRange? = null
  )

  /**
   * Selector list data.
   */
  @Serializable
  public class SelectorList(
    /**
     * Selectors in the list.
     */
    public val selectors: List<Value>,
    /**
     * Rule selector text.
     */
    public val text: String
  )

  /**
   * CSS stylesheet metainformation.
   */
  @Serializable
  public class CSSStyleSheetHeader(
    /**
     * The stylesheet identifier.
     */
    public val styleSheetId: String,
    /**
     * Owner frame identifier.
     */
    public val frameId: String,
    /**
     * Stylesheet resource URL.
     */
    public val sourceURL: String,
    /**
     * URL of source map associated with the stylesheet (if any).
     */
    public val sourceMapURL: String? = null,
    /**
     * Stylesheet origin.
     */
    public val origin: StyleSheetOrigin,
    /**
     * Stylesheet title.
     */
    public val title: String,
    /**
     * The backend id for the owner node of the stylesheet.
     */
    public val ownerNode: Int? = null,
    /**
     * Denotes whether the stylesheet is disabled.
     */
    public val disabled: Boolean,
    /**
     * Whether the sourceURL field value comes from the sourceURL comment.
     */
    public val hasSourceURL: Boolean? = null,
    /**
     * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for
     * document.written STYLE tags.
     */
    public val isInline: Boolean,
    /**
     * Whether this stylesheet is mutable. Inline stylesheets become mutable
     * after they have been modified via CSSOM API.
     * <link> element's stylesheets become mutable only if DevTools modifies them.
     * Constructed stylesheets (new CSSStyleSheet()) are mutable immediately after creation.
     */
    public val isMutable: Boolean,
    /**
     * Whether this stylesheet is a constructed stylesheet (created using new CSSStyleSheet()).
     */
    public val isConstructed: Boolean,
    /**
     * Line offset of the stylesheet within the resource (zero based).
     */
    public val startLine: Double,
    /**
     * Column offset of the stylesheet within the resource (zero based).
     */
    public val startColumn: Double,
    /**
     * Size of the content (in characters).
     */
    public val length: Double,
    /**
     * Line offset of the end of the stylesheet within the resource (zero based).
     */
    public val endLine: Double,
    /**
     * Column offset of the end of the stylesheet within the resource (zero based).
     */
    public val endColumn: Double
  )

  /**
   * CSS rule representation.
   */
  @Serializable
  public class CSSRule(
    /**
     * The css style sheet identifier (absent for user agent stylesheet and user-specified
     * stylesheet rules) this rule came from.
     */
    public val styleSheetId: String? = null,
    /**
     * Rule selector data.
     */
    public val selectorList: SelectorList,
    /**
     * Parent stylesheet's origin.
     */
    public val origin: StyleSheetOrigin,
    /**
     * Associated style declaration.
     */
    public val style: CSSStyle,
    /**
     * Media list array (for rules involving media queries). The array enumerates media queries
     * starting with the innermost one, going outwards.
     */
    public val media: List<CSSMedia>? = null
  )

  /**
   * CSS coverage information.
   */
  @Serializable
  public class RuleUsage(
    /**
     * The css style sheet identifier (absent for user agent stylesheet and user-specified
     * stylesheet rules) this rule came from.
     */
    public val styleSheetId: String,
    /**
     * Offset of the start of the rule (including selector) from the beginning of the stylesheet.
     */
    public val startOffset: Double,
    /**
     * Offset of the end of the rule body from the beginning of the stylesheet.
     */
    public val endOffset: Double,
    /**
     * Indicates whether the rule was actually used by some element in the page.
     */
    public val used: Boolean
  )

  /**
   * Text range within a resource. All numbers are zero-based.
   */
  @Serializable
  public class SourceRange(
    /**
     * Start line of range.
     */
    public val startLine: Int,
    /**
     * Start column of range (inclusive).
     */
    public val startColumn: Int,
    /**
     * End line of range
     */
    public val endLine: Int,
    /**
     * End column of range (exclusive).
     */
    public val endColumn: Int
  )

  @Serializable
  public class ShorthandEntry(
    /**
     * Shorthand name.
     */
    public val name: String,
    /**
     * Shorthand value.
     */
    public val value: String,
    /**
     * Whether the property has "!important" annotation (implies `false` if absent).
     */
    public val important: Boolean? = null
  )

  @Serializable
  public class CSSComputedStyleProperty(
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
   * CSS style representation.
   */
  @Serializable
  public class CSSStyle(
    /**
     * The css style sheet identifier (absent for user agent stylesheet and user-specified
     * stylesheet rules) this rule came from.
     */
    public val styleSheetId: String? = null,
    /**
     * CSS properties in the style.
     */
    public val cssProperties: List<CSSProperty>,
    /**
     * Computed values for all shorthands found in the style.
     */
    public val shorthandEntries: List<ShorthandEntry>,
    /**
     * Style declaration text (if available).
     */
    public val cssText: String? = null,
    /**
     * Style declaration range in the enclosing stylesheet (if available).
     */
    public val range: SourceRange? = null
  )

  /**
   * CSS property declaration data.
   */
  @Serializable
  public class CSSProperty(
    /**
     * The property name.
     */
    public val name: String,
    /**
     * The property value.
     */
    public val value: String,
    /**
     * Whether the property has "!important" annotation (implies `false` if absent).
     */
    public val important: Boolean? = null,
    /**
     * Whether the property is implicit (implies `false` if absent).
     */
    public val implicit: Boolean? = null,
    /**
     * The full property text as specified in the style.
     */
    public val text: String? = null,
    /**
     * Whether the property is understood by the browser (implies `true` if absent).
     */
    public val parsedOk: Boolean? = null,
    /**
     * Whether the property is disabled by the user (present for source-based properties only).
     */
    public val disabled: Boolean? = null,
    /**
     * The entire property range in the enclosing style declaration (if available).
     */
    public val range: SourceRange? = null
  )

  /**
   * CSS media rule descriptor.
   */
  @Serializable
  public class CSSMedia(
    /**
     * Media query text.
     */
    public val text: String,
    /**
     * Source of the media query: "mediaRule" if specified by a @media rule, "importRule" if
     * specified by an @import rule, "linkedSheet" if specified by a "media" attribute in a linked
     * stylesheet's LINK tag, "inlineSheet" if specified by a "media" attribute in an inline
     * stylesheet's STYLE tag.
     */
    public val source: String,
    /**
     * URL of the document containing the media query description.
     */
    public val sourceURL: String? = null,
    /**
     * The associated rule (@media or @import) header range in the enclosing stylesheet (if
     * available).
     */
    public val range: SourceRange? = null,
    /**
     * Identifier of the stylesheet containing this object (if exists).
     */
    public val styleSheetId: String? = null,
    /**
     * Array of media queries.
     */
    public val mediaList: List<MediaQuery>? = null
  )

  /**
   * Media query descriptor.
   */
  @Serializable
  public class MediaQuery(
    /**
     * Array of media query expressions.
     */
    public val expressions: List<MediaQueryExpression>,
    /**
     * Whether the media query condition is satisfied.
     */
    public val active: Boolean
  )

  /**
   * Media query expression descriptor.
   */
  @Serializable
  public class MediaQueryExpression(
    /**
     * Media query expression value.
     */
    public val value: Double,
    /**
     * Media query expression units.
     */
    public val unit: String,
    /**
     * Media query expression feature.
     */
    public val feature: String,
    /**
     * The associated range of the value text in the enclosing stylesheet (if available).
     */
    public val valueRange: SourceRange? = null,
    /**
     * Computed length of media query expression (if applicable).
     */
    public val computedLength: Double? = null
  )

  /**
   * Information about amount of glyphs that were rendered with given font.
   */
  @Serializable
  public class PlatformFontUsage(
    /**
     * Font's family name reported by platform.
     */
    public val familyName: String,
    /**
     * Indicates if the font was downloaded or resolved locally.
     */
    public val isCustomFont: Boolean,
    /**
     * Amount of glyphs that were rendered with this font.
     */
    public val glyphCount: Double
  )

  /**
   * Information about font variation axes for variable fonts
   */
  @Serializable
  public class FontVariationAxis(
    /**
     * The font-variation-setting tag (a.k.a. "axis tag").
     */
    public val tag: String,
    /**
     * Human-readable variation name in the default language (normally, "en").
     */
    public val name: String,
    /**
     * The minimum value (inclusive) the font supports for this tag.
     */
    public val minValue: Double,
    /**
     * The maximum value (inclusive) the font supports for this tag.
     */
    public val maxValue: Double,
    /**
     * The default value.
     */
    public val defaultValue: Double
  )

  /**
   * Properties of a web font:
   * https://www.w3.org/TR/2008/REC-CSS2-20080411/fonts.html#font-descriptions
   * and additional information such as platformFontFamily and fontVariationAxes.
   */
  @Serializable
  public class FontFace(
    /**
     * The font-family.
     */
    public val fontFamily: String,
    /**
     * The font-style.
     */
    public val fontStyle: String,
    /**
     * The font-variant.
     */
    public val fontVariant: String,
    /**
     * The font-weight.
     */
    public val fontWeight: String,
    /**
     * The font-stretch.
     */
    public val fontStretch: String,
    /**
     * The unicode-range.
     */
    public val unicodeRange: String,
    /**
     * The src.
     */
    public val src: String,
    /**
     * The resolved platform font family
     */
    public val platformFontFamily: String,
    /**
     * Available variation settings (a.k.a. "axes").
     */
    public val fontVariationAxes: List<FontVariationAxis>? = null
  )

  /**
   * CSS keyframes rule representation.
   */
  @Serializable
  public class CSSKeyframesRule(
    /**
     * Animation name.
     */
    public val animationName: Value,
    /**
     * List of keyframes.
     */
    public val keyframes: List<CSSKeyframeRule>
  )

  /**
   * CSS keyframe rule representation.
   */
  @Serializable
  public class CSSKeyframeRule(
    /**
     * The css style sheet identifier (absent for user agent stylesheet and user-specified
     * stylesheet rules) this rule came from.
     */
    public val styleSheetId: String? = null,
    /**
     * Parent stylesheet's origin.
     */
    public val origin: StyleSheetOrigin,
    /**
     * Associated key text.
     */
    public val keyText: Value,
    /**
     * Associated style declaration.
     */
    public val style: CSSStyle
  )

  /**
   * A descriptor of operation to mutate style declaration text.
   */
  @Serializable
  public class StyleDeclarationEdit(
    /**
     * The css style sheet identifier.
     */
    public val styleSheetId: String,
    /**
     * The range of the style text in the enclosing stylesheet.
     */
    public val range: SourceRange,
    /**
     * New style text.
     */
    public val text: String
  )

  /**
   * Fires whenever a web font is updated.  A non-empty font parameter indicates a successfully
   * loaded
   * web font
   */
  public data class FontsUpdatedParameter(
    /**
     * The web font that has loaded.
     */
    public val font: FontFace? = null
  )

  /**
   * Fired whenever an active document stylesheet is added.
   */
  public data class StyleSheetAddedParameter(
    /**
     * Added stylesheet metainfo.
     */
    public val header: CSSStyleSheetHeader
  )

  /**
   * Fired whenever a stylesheet is changed as a result of the client operation.
   */
  public data class StyleSheetChangedParameter(
    public val styleSheetId: String
  )

  /**
   * Fired whenever an active document stylesheet is removed.
   */
  public data class StyleSheetRemovedParameter(
    /**
     * Identifier of the removed stylesheet.
     */
    public val styleSheetId: String
  )

  @Serializable
  public data class AddRuleParameter(
    /**
     * The css style sheet identifier where a new rule should be inserted.
     */
    public val styleSheetId: String,
    /**
     * The text of a new rule.
     */
    public val ruleText: String,
    /**
     * Text position of a new rule in the target style sheet.
     */
    public val location: SourceRange
  )

  @Serializable
  public data class AddRuleReturn(
    /**
     * The newly created rule.
     */
    public val rule: CSSRule
  )

  @Serializable
  public data class CollectClassNamesParameter(
    public val styleSheetId: String
  )

  @Serializable
  public data class CollectClassNamesReturn(
    /**
     * Class name list.
     */
    public val classNames: String
  )

  @Serializable
  public data class CreateStyleSheetParameter(
    /**
     * Identifier of the frame where "via-inspector" stylesheet should be created.
     */
    public val frameId: String
  )

  @Serializable
  public data class CreateStyleSheetReturn(
    /**
     * Identifier of the created "via-inspector" stylesheet.
     */
    public val styleSheetId: String
  )

  @Serializable
  public data class ForcePseudoStateParameter(
    /**
     * The element id for which to force the pseudo state.
     */
    public val nodeId: Int,
    /**
     * Element pseudo classes to force when computing the element's style.
     */
    public val forcedPseudoClasses: String
  )

  @Serializable
  public data class GetBackgroundColorsParameter(
    /**
     * Id of the node to get background colors for.
     */
    public val nodeId: Int
  )

  @Serializable
  public data class GetBackgroundColorsReturn(
    /**
     * The range of background colors behind this element, if it contains any visible text. If no
     * visible text is present, this will be undefined. In the case of a flat background color,
     * this will consist of simply that color. In the case of a gradient, this will consist of each
     * of the color stops. For anything more complicated, this will be an empty array. Images will
     * be ignored (as if the image had failed to load).
     */
    public val backgroundColors: String?,
    /**
     * The computed font size for this node, as a CSS computed value string (e.g. '12px').
     */
    public val computedFontSize: String?,
    /**
     * The computed font weight for this node, as a CSS computed value string (e.g. 'normal' or
     * '100').
     */
    public val computedFontWeight: String?
  )

  @Serializable
  public data class GetComputedStyleForNodeParameter(
    public val nodeId: Int
  )

  @Serializable
  public data class GetComputedStyleForNodeReturn(
    /**
     * Computed style for the specified DOM node.
     */
    public val computedStyle: List<CSSComputedStyleProperty>
  )

  @Serializable
  public data class GetInlineStylesForNodeParameter(
    public val nodeId: Int
  )

  @Serializable
  public data class GetInlineStylesForNodeReturn(
    /**
     * Inline style for the specified DOM node.
     */
    public val inlineStyle: CSSStyle?,
    /**
     * Attribute-defined element style (e.g. resulting from "width=20 height=100%").
     */
    public val attributesStyle: CSSStyle?
  )

  @Serializable
  public data class GetMatchedStylesForNodeParameter(
    public val nodeId: Int
  )

  @Serializable
  public data class GetMatchedStylesForNodeReturn(
    /**
     * Inline style for the specified DOM node.
     */
    public val inlineStyle: CSSStyle?,
    /**
     * Attribute-defined element style (e.g. resulting from "width=20 height=100%").
     */
    public val attributesStyle: CSSStyle?,
    /**
     * CSS rules matching this node, from all applicable stylesheets.
     */
    public val matchedCSSRules: List<RuleMatch>?,
    /**
     * Pseudo style matches for this node.
     */
    public val pseudoElements: List<PseudoElementMatches>?,
    /**
     * A chain of inherited styles (from the immediate node parent up to the DOM tree root).
     */
    public val inherited: List<InheritedStyleEntry>?,
    /**
     * A list of CSS keyframed animations matching this node.
     */
    public val cssKeyframesRules: List<CSSKeyframesRule>?
  )

  @Serializable
  public data class GetMediaQueriesReturn(
    public val medias: List<CSSMedia>
  )

  @Serializable
  public data class GetPlatformFontsForNodeParameter(
    public val nodeId: Int
  )

  @Serializable
  public data class GetPlatformFontsForNodeReturn(
    /**
     * Usage statistics for every employed platform font.
     */
    public val fonts: List<PlatformFontUsage>
  )

  @Serializable
  public data class GetStyleSheetTextParameter(
    public val styleSheetId: String
  )

  @Serializable
  public data class GetStyleSheetTextReturn(
    /**
     * The stylesheet text.
     */
    public val text: String
  )

  @Serializable
  public data class TrackComputedStyleUpdatesParameter(
    public val propertiesToTrack: List<CSSComputedStyleProperty>
  )

  @Serializable
  public data class TakeComputedStyleUpdatesReturn(
    /**
     * The list of node Ids that have their tracked computed styles updated
     */
    public val nodeIds: List<Int>
  )

  @Serializable
  public data class SetEffectivePropertyValueForNodeParameter(
    /**
     * The element id for which to set property.
     */
    public val nodeId: Int,
    public val propertyName: String,
    public val value: String
  )

  @Serializable
  public data class SetKeyframeKeyParameter(
    public val styleSheetId: String,
    public val range: SourceRange,
    public val keyText: String
  )

  @Serializable
  public data class SetKeyframeKeyReturn(
    /**
     * The resulting key text after modification.
     */
    public val keyText: Value
  )

  @Serializable
  public data class SetMediaTextParameter(
    public val styleSheetId: String,
    public val range: SourceRange,
    public val text: String
  )

  @Serializable
  public data class SetMediaTextReturn(
    /**
     * The resulting CSS media rule after modification.
     */
    public val media: CSSMedia
  )

  @Serializable
  public data class SetRuleSelectorParameter(
    public val styleSheetId: String,
    public val range: SourceRange,
    public val selector: String
  )

  @Serializable
  public data class SetRuleSelectorReturn(
    /**
     * The resulting selector list after modification.
     */
    public val selectorList: SelectorList
  )

  @Serializable
  public data class SetStyleSheetTextParameter(
    public val styleSheetId: String,
    public val text: String
  )

  @Serializable
  public data class SetStyleSheetTextReturn(
    /**
     * URL of source map associated with script (if any).
     */
    public val sourceMapURL: String?
  )

  @Serializable
  public data class SetStyleTextsParameter(
    public val edits: List<StyleDeclarationEdit>
  )

  @Serializable
  public data class SetStyleTextsReturn(
    /**
     * The resulting styles after modification.
     */
    public val styles: List<CSSStyle>
  )

  @Serializable
  public data class StopRuleUsageTrackingReturn(
    public val ruleUsage: List<RuleUsage>
  )

  @Serializable
  public data class TakeCoverageDeltaReturn(
    public val coverage: List<RuleUsage>,
    /**
     * Monotonically increasing time, in seconds.
     */
    public val timestamp: Double
  )

  @Serializable
  public data class SetLocalFontsEnabledParameter(
    /**
     * Whether rendering of local fonts is enabled.
     */
    public val enabled: Boolean
  )
}
