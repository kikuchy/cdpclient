package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Deprecated
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.debugger: Debugger
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Debugger(this))

/**
 * Debugger domain exposes JavaScript debugging capabilities. It allows setting and removing
 * breakpoints, stepping through execution, exploring stack traces, etc.
 */
public class Debugger(
  private val client: CDPClient
) : Domain {
  public val breakpointResolved: Flow<BreakpointResolvedParameter> = client.events.filter {
          it.method == "breakpointResolved"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val paused: Flow<PausedParameter> = client.events.filter {
          it.method == "paused"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val resumed: Flow<Unit> = client.events.filter {
          it.method == "resumed"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val scriptFailedToParse: Flow<ScriptFailedToParseParameter> = client.events.filter {
          it.method == "scriptFailedToParse"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  public val scriptParsed: Flow<ScriptParsedParameter> = client.events.filter {
          it.method == "scriptParsed"
      }.map {
          it.params
      }.filterNotNull().map {
          Json.decodeFromJsonElement(it)
      }

  /**
   * Continues execution until specific location is reached.
   */
  public suspend fun continueToLocation(args: ContinueToLocationParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.continueToLocation", parameter)
  }

  public suspend fun continueToLocation(location: Location, targetCallFrames: String? = null):
      Unit {
    val parameter = ContinueToLocationParameter(location = location,targetCallFrames =
        targetCallFrames)
    continueToLocation(parameter)
  }

  /**
   * Disables debugger for given page.
   */
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Debugger.disable", parameter)
  }

  /**
   * Enables debugger for the given page. Clients should not assume that the debugging has been
   * enabled until the result for this command is received.
   */
  public suspend fun enable(args: EnableParameter): EnableReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.enable", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun enable(maxScriptsCacheSize: Double? = null): EnableReturn {
    val parameter = EnableParameter(maxScriptsCacheSize = maxScriptsCacheSize)
    return enable(parameter)
  }

  /**
   * Evaluates expression on a given call frame.
   */
  public suspend fun evaluateOnCallFrame(args: EvaluateOnCallFrameParameter):
      EvaluateOnCallFrameReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.evaluateOnCallFrame", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun evaluateOnCallFrame(
    callFrameId: String,
    expression: String,
    objectGroup: String? = null,
    includeCommandLineAPI: Boolean? = null,
    silent: Boolean? = null,
    returnByValue: Boolean? = null,
    generatePreview: Boolean? = null,
    throwOnSideEffect: Boolean? = null,
    timeout: Double? = null
  ): EvaluateOnCallFrameReturn {
    val parameter = EvaluateOnCallFrameParameter(callFrameId = callFrameId,expression =
        expression,objectGroup = objectGroup,includeCommandLineAPI = includeCommandLineAPI,silent =
        silent,returnByValue = returnByValue,generatePreview = generatePreview,throwOnSideEffect =
        throwOnSideEffect,timeout = timeout)
    return evaluateOnCallFrame(parameter)
  }

  /**
   * Execute a Wasm Evaluator module on a given call frame.
   */
  public suspend fun executeWasmEvaluator(args: ExecuteWasmEvaluatorParameter):
      ExecuteWasmEvaluatorReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.executeWasmEvaluator", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun executeWasmEvaluator(
    callFrameId: String,
    evaluator: String,
    timeout: Double? = null
  ): ExecuteWasmEvaluatorReturn {
    val parameter = ExecuteWasmEvaluatorParameter(callFrameId = callFrameId,evaluator =
        evaluator,timeout = timeout)
    return executeWasmEvaluator(parameter)
  }

  /**
   * Returns possible locations for breakpoint. scriptId in start and end range locations should be
   * the same.
   */
  public suspend fun getPossibleBreakpoints(args: GetPossibleBreakpointsParameter):
      GetPossibleBreakpointsReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.getPossibleBreakpoints", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getPossibleBreakpoints(
    start: Location,
    end: Location? = null,
    restrictToFunction: Boolean? = null
  ): GetPossibleBreakpointsReturn {
    val parameter = GetPossibleBreakpointsParameter(start = start,end = end,restrictToFunction =
        restrictToFunction)
    return getPossibleBreakpoints(parameter)
  }

  /**
   * Returns source for the script with given id.
   */
  public suspend fun getScriptSource(args: GetScriptSourceParameter): GetScriptSourceReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.getScriptSource", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getScriptSource(scriptId: String): GetScriptSourceReturn {
    val parameter = GetScriptSourceParameter(scriptId = scriptId)
    return getScriptSource(parameter)
  }

  /**
   * This command is deprecated. Use getScriptSource instead.
   */
  @Deprecated(message = "")
  public suspend fun getWasmBytecode(args: GetWasmBytecodeParameter): GetWasmBytecodeReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.getWasmBytecode", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getWasmBytecode(scriptId: String): GetWasmBytecodeReturn {
    val parameter = GetWasmBytecodeParameter(scriptId = scriptId)
    return getWasmBytecode(parameter)
  }

  /**
   * Returns stack trace with given `stackTraceId`.
   */
  public suspend fun getStackTrace(args: GetStackTraceParameter): GetStackTraceReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.getStackTrace", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun getStackTrace(stackTraceId: Runtime.StackTraceId): GetStackTraceReturn {
    val parameter = GetStackTraceParameter(stackTraceId = stackTraceId)
    return getStackTrace(parameter)
  }

  /**
   * Stops on the next JavaScript statement.
   */
  public suspend fun pause(): Unit {
    val parameter = null
    client.callCommand("Debugger.pause", parameter)
  }

  @Deprecated(message = "")
  public suspend fun pauseOnAsyncCall(args: PauseOnAsyncCallParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.pauseOnAsyncCall", parameter)
  }

  public suspend fun pauseOnAsyncCall(parentStackTraceId: Runtime.StackTraceId): Unit {
    val parameter = PauseOnAsyncCallParameter(parentStackTraceId = parentStackTraceId)
    pauseOnAsyncCall(parameter)
  }

  /**
   * Removes JavaScript breakpoint.
   */
  public suspend fun removeBreakpoint(args: RemoveBreakpointParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.removeBreakpoint", parameter)
  }

  public suspend fun removeBreakpoint(breakpointId: String): Unit {
    val parameter = RemoveBreakpointParameter(breakpointId = breakpointId)
    removeBreakpoint(parameter)
  }

  /**
   * Restarts particular call frame from the beginning.
   */
  public suspend fun restartFrame(args: RestartFrameParameter): RestartFrameReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.restartFrame", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun restartFrame(callFrameId: String): RestartFrameReturn {
    val parameter = RestartFrameParameter(callFrameId = callFrameId)
    return restartFrame(parameter)
  }

  /**
   * Resumes JavaScript execution.
   */
  public suspend fun resume(args: ResumeParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.resume", parameter)
  }

  public suspend fun resume(terminateOnResume: Boolean? = null): Unit {
    val parameter = ResumeParameter(terminateOnResume = terminateOnResume)
    resume(parameter)
  }

  /**
   * Searches for given string in script content.
   */
  public suspend fun searchInContent(args: SearchInContentParameter): SearchInContentReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.searchInContent", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun searchInContent(
    scriptId: String,
    query: String,
    caseSensitive: Boolean? = null,
    isRegex: Boolean? = null
  ): SearchInContentReturn {
    val parameter = SearchInContentParameter(scriptId = scriptId,query = query,caseSensitive =
        caseSensitive,isRegex = isRegex)
    return searchInContent(parameter)
  }

  /**
   * Enables or disables async call stacks tracking.
   */
  public suspend fun setAsyncCallStackDepth(args: SetAsyncCallStackDepthParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.setAsyncCallStackDepth", parameter)
  }

  public suspend fun setAsyncCallStackDepth(maxDepth: Int): Unit {
    val parameter = SetAsyncCallStackDepthParameter(maxDepth = maxDepth)
    setAsyncCallStackDepth(parameter)
  }

  /**
   * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in
   * scripts with url matching one of the patterns. VM will try to leave blackboxed script by
   * performing 'step in' several times, finally resorting to 'step out' if unsuccessful.
   */
  public suspend fun setBlackboxPatterns(args: SetBlackboxPatternsParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.setBlackboxPatterns", parameter)
  }

  public suspend fun setBlackboxPatterns(patterns: String): Unit {
    val parameter = SetBlackboxPatternsParameter(patterns = patterns)
    setBlackboxPatterns(parameter)
  }

  /**
   * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted
   * scripts by performing 'step in' several times, finally resorting to 'step out' if unsuccessful.
   * Positions array contains positions where blackbox state is changed. First interval isn't
   * blackboxed. Array should be sorted.
   */
  public suspend fun setBlackboxedRanges(args: SetBlackboxedRangesParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.setBlackboxedRanges", parameter)
  }

  public suspend fun setBlackboxedRanges(scriptId: String, positions: List<ScriptPosition>): Unit {
    val parameter = SetBlackboxedRangesParameter(scriptId = scriptId,positions = positions)
    setBlackboxedRanges(parameter)
  }

  /**
   * Sets JavaScript breakpoint at a given location.
   */
  public suspend fun setBreakpoint(args: SetBreakpointParameter): SetBreakpointReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.setBreakpoint", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setBreakpoint(location: Location, condition: String? = null):
      SetBreakpointReturn {
    val parameter = SetBreakpointParameter(location = location,condition = condition)
    return setBreakpoint(parameter)
  }

  /**
   * Sets instrumentation breakpoint.
   */
  public suspend fun setInstrumentationBreakpoint(args: SetInstrumentationBreakpointParameter):
      SetInstrumentationBreakpointReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.setInstrumentationBreakpoint", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setInstrumentationBreakpoint(instrumentation: String):
      SetInstrumentationBreakpointReturn {
    val parameter = SetInstrumentationBreakpointParameter(instrumentation = instrumentation)
    return setInstrumentationBreakpoint(parameter)
  }

  /**
   * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this
   * command is issued, all existing parsed scripts will have breakpoints resolved and returned in
   * `locations` property. Further matching script parsing will result in subsequent
   * `breakpointResolved` events issued. This logical breakpoint will survive page reloads.
   */
  public suspend fun setBreakpointByUrl(args: SetBreakpointByUrlParameter):
      SetBreakpointByUrlReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.setBreakpointByUrl", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setBreakpointByUrl(
    lineNumber: Int,
    url: String? = null,
    urlRegex: String? = null,
    scriptHash: String? = null,
    columnNumber: Int? = null,
    condition: String? = null
  ): SetBreakpointByUrlReturn {
    val parameter = SetBreakpointByUrlParameter(lineNumber = lineNumber,url = url,urlRegex =
        urlRegex,scriptHash = scriptHash,columnNumber = columnNumber,condition = condition)
    return setBreakpointByUrl(parameter)
  }

  /**
   * Sets JavaScript breakpoint before each call to the given function.
   * If another function was created from the same source as a given one,
   * calling it will also trigger the breakpoint.
   */
  public suspend fun setBreakpointOnFunctionCall(args: SetBreakpointOnFunctionCallParameter):
      SetBreakpointOnFunctionCallReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.setBreakpointOnFunctionCall", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setBreakpointOnFunctionCall(objectId: String, condition: String? = null):
      SetBreakpointOnFunctionCallReturn {
    val parameter = SetBreakpointOnFunctionCallParameter(objectId = objectId,condition = condition)
    return setBreakpointOnFunctionCall(parameter)
  }

  /**
   * Activates / deactivates all breakpoints on the page.
   */
  public suspend fun setBreakpointsActive(args: SetBreakpointsActiveParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.setBreakpointsActive", parameter)
  }

  public suspend fun setBreakpointsActive(active: Boolean): Unit {
    val parameter = SetBreakpointsActiveParameter(active = active)
    setBreakpointsActive(parameter)
  }

  /**
   * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions or
   * no exceptions. Initial pause on exceptions state is `none`.
   */
  public suspend fun setPauseOnExceptions(args: SetPauseOnExceptionsParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.setPauseOnExceptions", parameter)
  }

  public suspend fun setPauseOnExceptions(state: String): Unit {
    val parameter = SetPauseOnExceptionsParameter(state = state)
    setPauseOnExceptions(parameter)
  }

  /**
   * Changes return value in top frame. Available only at return break position.
   */
  public suspend fun setReturnValue(args: SetReturnValueParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.setReturnValue", parameter)
  }

  public suspend fun setReturnValue(newValue: Runtime.CallArgument): Unit {
    val parameter = SetReturnValueParameter(newValue = newValue)
    setReturnValue(parameter)
  }

  /**
   * Edits JavaScript source live.
   */
  public suspend fun setScriptSource(args: SetScriptSourceParameter): SetScriptSourceReturn {
    val parameter = Json.encodeToJsonElement(args)
    val result = client.callCommand("Debugger.setScriptSource", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  public suspend fun setScriptSource(
    scriptId: String,
    scriptSource: String,
    dryRun: Boolean? = null
  ): SetScriptSourceReturn {
    val parameter = SetScriptSourceParameter(scriptId = scriptId,scriptSource = scriptSource,dryRun
        = dryRun)
    return setScriptSource(parameter)
  }

  /**
   * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
   */
  public suspend fun setSkipAllPauses(args: SetSkipAllPausesParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.setSkipAllPauses", parameter)
  }

  public suspend fun setSkipAllPauses(skip: Boolean): Unit {
    val parameter = SetSkipAllPausesParameter(skip = skip)
    setSkipAllPauses(parameter)
  }

  /**
   * Changes value of variable in a callframe. Object-based scopes are not supported and must be
   * mutated manually.
   */
  public suspend fun setVariableValue(args: SetVariableValueParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.setVariableValue", parameter)
  }

  public suspend fun setVariableValue(
    scopeNumber: Int,
    variableName: String,
    newValue: Runtime.CallArgument,
    callFrameId: String
  ): Unit {
    val parameter = SetVariableValueParameter(scopeNumber = scopeNumber,variableName =
        variableName,newValue = newValue,callFrameId = callFrameId)
    setVariableValue(parameter)
  }

  /**
   * Steps into the function call.
   */
  public suspend fun stepInto(args: StepIntoParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.stepInto", parameter)
  }

  public suspend fun stepInto(breakOnAsyncCall: Boolean? = null, skipList: List<LocationRange>? =
      null): Unit {
    val parameter = StepIntoParameter(breakOnAsyncCall = breakOnAsyncCall,skipList = skipList)
    stepInto(parameter)
  }

  /**
   * Steps out of the function call.
   */
  public suspend fun stepOut(): Unit {
    val parameter = null
    client.callCommand("Debugger.stepOut", parameter)
  }

  /**
   * Steps over the statement.
   */
  public suspend fun stepOver(args: StepOverParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Debugger.stepOver", parameter)
  }

  public suspend fun stepOver(skipList: List<LocationRange>? = null): Unit {
    val parameter = StepOverParameter(skipList = skipList)
    stepOver(parameter)
  }

  /**
   * Location in the source code.
   */
  @Serializable
  public class Location(
    /**
     * Script identifier as reported in the `Debugger.scriptParsed`.
     */
    public val scriptId: String,
    /**
     * Line number in the script (0-based).
     */
    public val lineNumber: Int,
    /**
     * Column number in the script (0-based).
     */
    public val columnNumber: Int? = null
  )

  /**
   * Location in the source code.
   */
  @Serializable
  public class ScriptPosition(
    public val lineNumber: Int,
    public val columnNumber: Int
  )

  /**
   * Location range within one script.
   */
  @Serializable
  public class LocationRange(
    public val scriptId: String,
    public val start: ScriptPosition,
    public val end: ScriptPosition
  )

  /**
   * JavaScript call frame. Array of call frames form the call stack.
   */
  @Serializable
  public class CallFrame(
    /**
     * Call frame identifier. This identifier is only valid while the virtual machine is paused.
     */
    public val callFrameId: String,
    /**
     * Name of the JavaScript function called on this call frame.
     */
    public val functionName: String,
    /**
     * Location in the source code.
     */
    public val functionLocation: Location? = null,
    /**
     * Location in the source code.
     */
    public val location: Location,
    /**
     * JavaScript script name or url.
     */
    public val url: String,
    /**
     * Scope chain for this call frame.
     */
    public val scopeChain: List<Scope>,
    /**
     * `this` object for this call frame.
     */
    public val `this`: Runtime.RemoteObject,
    /**
     * The value being returned, if the function is at return point.
     */
    public val returnValue: Runtime.RemoteObject? = null
  )

  /**
   * Scope description.
   */
  @Serializable
  public class Scope(
    /**
     * Scope type.
     */
    public val type: String,
    /**
     * Object representing the scope. For `global` and `with` scopes it represents the actual
     * object; for the rest of the scopes, it is artificial transient object enumerating scope
     * variables as its properties.
     */
    public val `object`: Runtime.RemoteObject,
    public val name: String? = null,
    /**
     * Location in the source code where scope starts
     */
    public val startLocation: Location? = null,
    /**
     * Location in the source code where scope ends
     */
    public val endLocation: Location? = null
  )

  /**
   * Search match for resource.
   */
  @Serializable
  public class SearchMatch(
    /**
     * Line number in resource content.
     */
    public val lineNumber: Double,
    /**
     * Line with match content.
     */
    public val lineContent: String
  )

  @Serializable
  public class BreakLocation(
    /**
     * Script identifier as reported in the `Debugger.scriptParsed`.
     */
    public val scriptId: String,
    /**
     * Line number in the script (0-based).
     */
    public val lineNumber: Int,
    /**
     * Column number in the script (0-based).
     */
    public val columnNumber: Int? = null,
    public val type: String? = null
  )

  /**
   * Enum of possible script languages.
   */
  @Serializable
  public enum class ScriptLanguage {
    @SerialName("JavaScript")
    JAVASCRIPT,
    @SerialName("WebAssembly")
    WEBASSEMBLY,
  }

  /**
   * Debug symbols available for a wasm script.
   */
  @Serializable
  public class DebugSymbols(
    /**
     * Type of the debug symbols.
     */
    public val type: String,
    /**
     * URL of the external symbol source.
     */
    public val externalURL: String? = null
  )

  /**
   * Fired when breakpoint is resolved to an actual script and location.
   */
  public class BreakpointResolvedParameter(
    /**
     * Breakpoint unique identifier.
     */
    public val breakpointId: String,
    /**
     * Actual breakpoint location.
     */
    public val location: Location
  )

  /**
   * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria.
   */
  public class PausedParameter(
    /**
     * Call stack the virtual machine stopped on.
     */
    public val callFrames: List<CallFrame>,
    /**
     * Pause reason.
     */
    public val reason: String,
    /**
     * Object containing break-specific auxiliary properties.
     */
    public val `data`: Map<String, JsonElement>? = null,
    /**
     * Hit breakpoints IDs
     */
    public val hitBreakpoints: String? = null,
    /**
     * Async stack trace, if any.
     */
    public val asyncStackTrace: Runtime.StackTrace? = null,
    /**
     * Async stack trace, if any.
     */
    public val asyncStackTraceId: Runtime.StackTraceId? = null,
    /**
     * Never present, will be removed.
     */
    public val asyncCallStackTraceId: Runtime.StackTraceId? = null
  )

  /**
   * Fired when virtual machine fails to parse the script.
   */
  public class ScriptFailedToParseParameter(
    /**
     * Identifier of the script parsed.
     */
    public val scriptId: String,
    /**
     * URL or name of the script parsed (if any).
     */
    public val url: String,
    /**
     * Line offset of the script within the resource with given URL (for script tags).
     */
    public val startLine: Int,
    /**
     * Column offset of the script within the resource with given URL.
     */
    public val startColumn: Int,
    /**
     * Last line of the script.
     */
    public val endLine: Int,
    /**
     * Length of the last line of the script.
     */
    public val endColumn: Int,
    /**
     * Specifies script creation context.
     */
    public val executionContextId: Int,
    /**
     * Content hash of the script.
     */
    public val hash: String,
    /**
     * Embedder-specific auxiliary data.
     */
    public val executionContextAuxData: Map<String, JsonElement>? = null,
    /**
     * URL of source map associated with script (if any).
     */
    public val sourceMapURL: String? = null,
    /**
     * True, if this script has sourceURL.
     */
    public val hasSourceURL: Boolean? = null,
    /**
     * True, if this script is ES6 module.
     */
    public val isModule: Boolean? = null,
    /**
     * This script length.
     */
    public val length: Int? = null,
    /**
     * JavaScript top stack frame of where the script parsed event was triggered if available.
     */
    public val stackTrace: Runtime.StackTrace? = null,
    /**
     * If the scriptLanguage is WebAssembly, the code section offset in the module.
     */
    public val codeOffset: Int? = null,
    /**
     * The language of the script.
     */
    public val scriptLanguage: ScriptLanguage? = null,
    /**
     * The name the embedder supplied for this script.
     */
    public val embedderName: String? = null
  )

  /**
   * Fired when virtual machine parses script. This event is also fired for all known and
   * uncollected
   * scripts upon enabling debugger.
   */
  public class ScriptParsedParameter(
    /**
     * Identifier of the script parsed.
     */
    public val scriptId: String,
    /**
     * URL or name of the script parsed (if any).
     */
    public val url: String,
    /**
     * Line offset of the script within the resource with given URL (for script tags).
     */
    public val startLine: Int,
    /**
     * Column offset of the script within the resource with given URL.
     */
    public val startColumn: Int,
    /**
     * Last line of the script.
     */
    public val endLine: Int,
    /**
     * Length of the last line of the script.
     */
    public val endColumn: Int,
    /**
     * Specifies script creation context.
     */
    public val executionContextId: Int,
    /**
     * Content hash of the script.
     */
    public val hash: String,
    /**
     * Embedder-specific auxiliary data.
     */
    public val executionContextAuxData: Map<String, JsonElement>? = null,
    /**
     * True, if this script is generated as a result of the live edit operation.
     */
    public val isLiveEdit: Boolean? = null,
    /**
     * URL of source map associated with script (if any).
     */
    public val sourceMapURL: String? = null,
    /**
     * True, if this script has sourceURL.
     */
    public val hasSourceURL: Boolean? = null,
    /**
     * True, if this script is ES6 module.
     */
    public val isModule: Boolean? = null,
    /**
     * This script length.
     */
    public val length: Int? = null,
    /**
     * JavaScript top stack frame of where the script parsed event was triggered if available.
     */
    public val stackTrace: Runtime.StackTrace? = null,
    /**
     * If the scriptLanguage is WebAssembly, the code section offset in the module.
     */
    public val codeOffset: Int? = null,
    /**
     * The language of the script.
     */
    public val scriptLanguage: ScriptLanguage? = null,
    /**
     * If the scriptLanguage is WebASsembly, the source of debug symbols for the module.
     */
    public val debugSymbols: DebugSymbols? = null,
    /**
     * The name the embedder supplied for this script.
     */
    public val embedderName: String? = null
  )

  @Serializable
  public data class ContinueToLocationParameter(
    /**
     * Location to continue to.
     */
    public val location: Location,
    public val targetCallFrames: String?
  )

  @Serializable
  public data class EnableParameter(
    /**
     * The maximum size in bytes of collected scripts (not referenced by other heap objects)
     * the debugger can hold. Puts no limit if paramter is omitted.
     */
    public val maxScriptsCacheSize: Double?
  )

  @Serializable
  public data class EnableReturn(
    /**
     * Unique identifier of the debugger.
     */
    public val debuggerId: String
  )

  @Serializable
  public data class EvaluateOnCallFrameParameter(
    /**
     * Call frame identifier to evaluate on.
     */
    public val callFrameId: String,
    /**
     * Expression to evaluate.
     */
    public val expression: String,
    /**
     * String object group name to put result into (allows rapid releasing resulting object handles
     * using `releaseObjectGroup`).
     */
    public val objectGroup: String?,
    /**
     * Specifies whether command line API should be available to the evaluated expression, defaults
     * to false.
     */
    public val includeCommandLineAPI: Boolean?,
    /**
     * In silent mode exceptions thrown during evaluation are not reported and do not pause
     * execution. Overrides `setPauseOnException` state.
     */
    public val silent: Boolean?,
    /**
     * Whether the result is expected to be a JSON object that should be sent by value.
     */
    public val returnByValue: Boolean?,
    /**
     * Whether preview should be generated for the result.
     */
    public val generatePreview: Boolean?,
    /**
     * Whether to throw an exception if side effect cannot be ruled out during evaluation.
     */
    public val throwOnSideEffect: Boolean?,
    /**
     * Terminate execution after timing out (number of milliseconds).
     */
    public val timeout: Double?
  )

  @Serializable
  public data class EvaluateOnCallFrameReturn(
    /**
     * Object wrapper for the evaluation result.
     */
    public val result: Runtime.RemoteObject,
    /**
     * Exception details.
     */
    public val exceptionDetails: Runtime.ExceptionDetails?
  )

  @Serializable
  public data class ExecuteWasmEvaluatorParameter(
    /**
     * WebAssembly call frame identifier to evaluate on.
     */
    public val callFrameId: String,
    /**
     * Code of the evaluator module. (Encoded as a base64 string when passed over JSON)
     */
    public val evaluator: String,
    /**
     * Terminate execution after timing out (number of milliseconds).
     */
    public val timeout: Double?
  )

  @Serializable
  public data class ExecuteWasmEvaluatorReturn(
    /**
     * Object wrapper for the evaluation result.
     */
    public val result: Runtime.RemoteObject,
    /**
     * Exception details.
     */
    public val exceptionDetails: Runtime.ExceptionDetails?
  )

  @Serializable
  public data class GetPossibleBreakpointsParameter(
    /**
     * Start of range to search possible breakpoint locations in.
     */
    public val start: Location,
    /**
     * End of range to search possible breakpoint locations in (excluding). When not specified, end
     * of scripts is used as end of range.
     */
    public val end: Location?,
    /**
     * Only consider locations which are in the same (non-nested) function as start.
     */
    public val restrictToFunction: Boolean?
  )

  @Serializable
  public data class GetPossibleBreakpointsReturn(
    /**
     * List of the possible breakpoint locations.
     */
    public val locations: List<BreakLocation>
  )

  @Serializable
  public data class GetScriptSourceParameter(
    /**
     * Id of the script to get source for.
     */
    public val scriptId: String
  )

  @Serializable
  public data class GetScriptSourceReturn(
    /**
     * Script source (empty in case of Wasm bytecode).
     */
    public val scriptSource: String,
    /**
     * Wasm bytecode. (Encoded as a base64 string when passed over JSON)
     */
    public val bytecode: String?
  )

  @Serializable
  public data class GetWasmBytecodeParameter(
    /**
     * Id of the Wasm script to get source for.
     */
    public val scriptId: String
  )

  @Serializable
  public data class GetWasmBytecodeReturn(
    /**
     * Script source. (Encoded as a base64 string when passed over JSON)
     */
    public val bytecode: String
  )

  @Serializable
  public data class GetStackTraceParameter(
    public val stackTraceId: Runtime.StackTraceId
  )

  @Serializable
  public data class GetStackTraceReturn(
    public val stackTrace: Runtime.StackTrace
  )

  @Serializable
  public data class PauseOnAsyncCallParameter(
    /**
     * Debugger will pause when async call with given stack trace is started.
     */
    public val parentStackTraceId: Runtime.StackTraceId
  )

  @Serializable
  public data class RemoveBreakpointParameter(
    public val breakpointId: String
  )

  @Serializable
  public data class RestartFrameParameter(
    /**
     * Call frame identifier to evaluate on.
     */
    public val callFrameId: String
  )

  @Serializable
  public data class RestartFrameReturn(
    /**
     * New stack trace.
     */
    public val callFrames: List<CallFrame>,
    /**
     * Async stack trace, if any.
     */
    public val asyncStackTrace: Runtime.StackTrace?,
    /**
     * Async stack trace, if any.
     */
    public val asyncStackTraceId: Runtime.StackTraceId?
  )

  @Serializable
  public data class ResumeParameter(
    /**
     * Set to true to terminate execution upon resuming execution. In contrast
     * to Runtime.terminateExecution, this will allows to execute further
     * JavaScript (i.e. via evaluation) until execution of the paused code
     * is actually resumed, at which point termination is triggered.
     * If execution is currently not paused, this parameter has no effect.
     */
    public val terminateOnResume: Boolean?
  )

  @Serializable
  public data class SearchInContentParameter(
    /**
     * Id of the script to search in.
     */
    public val scriptId: String,
    /**
     * String to search for.
     */
    public val query: String,
    /**
     * If true, search is case sensitive.
     */
    public val caseSensitive: Boolean?,
    /**
     * If true, treats string parameter as regex.
     */
    public val isRegex: Boolean?
  )

  @Serializable
  public data class SearchInContentReturn(
    /**
     * List of search matches.
     */
    public val result: List<SearchMatch>
  )

  @Serializable
  public data class SetAsyncCallStackDepthParameter(
    /**
     * Maximum depth of async call stacks. Setting to `0` will effectively disable collecting async
     * call stacks (default).
     */
    public val maxDepth: Int
  )

  @Serializable
  public data class SetBlackboxPatternsParameter(
    /**
     * Array of regexps that will be used to check script url for blackbox state.
     */
    public val patterns: String
  )

  @Serializable
  public data class SetBlackboxedRangesParameter(
    /**
     * Id of the script.
     */
    public val scriptId: String,
    public val positions: List<ScriptPosition>
  )

  @Serializable
  public data class SetBreakpointParameter(
    /**
     * Location to set breakpoint in.
     */
    public val location: Location,
    /**
     * Expression to use as a breakpoint condition. When specified, debugger will only stop on the
     * breakpoint if this expression evaluates to true.
     */
    public val condition: String?
  )

  @Serializable
  public data class SetBreakpointReturn(
    /**
     * Id of the created breakpoint for further reference.
     */
    public val breakpointId: String,
    /**
     * Location this breakpoint resolved into.
     */
    public val actualLocation: Location
  )

  @Serializable
  public data class SetInstrumentationBreakpointParameter(
    /**
     * Instrumentation name.
     */
    public val instrumentation: String
  )

  @Serializable
  public data class SetInstrumentationBreakpointReturn(
    /**
     * Id of the created breakpoint for further reference.
     */
    public val breakpointId: String
  )

  @Serializable
  public data class SetBreakpointByUrlParameter(
    /**
     * Line number to set breakpoint at.
     */
    public val lineNumber: Int,
    /**
     * URL of the resources to set breakpoint on.
     */
    public val url: String?,
    /**
     * Regex pattern for the URLs of the resources to set breakpoints on. Either `url` or
     * `urlRegex` must be specified.
     */
    public val urlRegex: String?,
    /**
     * Script hash of the resources to set breakpoint on.
     */
    public val scriptHash: String?,
    /**
     * Offset in the line to set breakpoint at.
     */
    public val columnNumber: Int?,
    /**
     * Expression to use as a breakpoint condition. When specified, debugger will only stop on the
     * breakpoint if this expression evaluates to true.
     */
    public val condition: String?
  )

  @Serializable
  public data class SetBreakpointByUrlReturn(
    /**
     * Id of the created breakpoint for further reference.
     */
    public val breakpointId: String,
    /**
     * List of the locations this breakpoint resolved into upon addition.
     */
    public val locations: List<Location>
  )

  @Serializable
  public data class SetBreakpointOnFunctionCallParameter(
    /**
     * Function object id.
     */
    public val objectId: String,
    /**
     * Expression to use as a breakpoint condition. When specified, debugger will
     * stop on the breakpoint if this expression evaluates to true.
     */
    public val condition: String?
  )

  @Serializable
  public data class SetBreakpointOnFunctionCallReturn(
    /**
     * Id of the created breakpoint for further reference.
     */
    public val breakpointId: String
  )

  @Serializable
  public data class SetBreakpointsActiveParameter(
    /**
     * New value for breakpoints active state.
     */
    public val active: Boolean
  )

  @Serializable
  public data class SetPauseOnExceptionsParameter(
    /**
     * Pause on exceptions mode.
     */
    public val state: String
  )

  @Serializable
  public data class SetReturnValueParameter(
    /**
     * New return value.
     */
    public val newValue: Runtime.CallArgument
  )

  @Serializable
  public data class SetScriptSourceParameter(
    /**
     * Id of the script to edit.
     */
    public val scriptId: String,
    /**
     * New content of the script.
     */
    public val scriptSource: String,
    /**
     * If true the change will not actually be applied. Dry run may be used to get result
     * description without actually modifying the code.
     */
    public val dryRun: Boolean?
  )

  @Serializable
  public data class SetScriptSourceReturn(
    /**
     * New stack trace in case editing has happened while VM was stopped.
     */
    public val callFrames: List<CallFrame>?,
    /**
     * Whether current call stack  was modified after applying the changes.
     */
    public val stackChanged: Boolean?,
    /**
     * Async stack trace, if any.
     */
    public val asyncStackTrace: Runtime.StackTrace?,
    /**
     * Async stack trace, if any.
     */
    public val asyncStackTraceId: Runtime.StackTraceId?,
    /**
     * Exception details if any.
     */
    public val exceptionDetails: Runtime.ExceptionDetails?
  )

  @Serializable
  public data class SetSkipAllPausesParameter(
    /**
     * New value for skip pauses state.
     */
    public val skip: Boolean
  )

  @Serializable
  public data class SetVariableValueParameter(
    /**
     * 0-based number of scope as was listed in scope chain. Only 'local', 'closure' and 'catch'
     * scope types are allowed. Other scopes could be manipulated manually.
     */
    public val scopeNumber: Int,
    /**
     * Variable name.
     */
    public val variableName: String,
    /**
     * New variable value.
     */
    public val newValue: Runtime.CallArgument,
    /**
     * Id of callframe that holds variable.
     */
    public val callFrameId: String
  )

  @Serializable
  public data class StepIntoParameter(
    /**
     * Debugger will pause on the execution of the first async task which was scheduled
     * before next pause.
     */
    public val breakOnAsyncCall: Boolean?,
    /**
     * The skipList specifies location ranges that should be skipped on step into.
     */
    public val skipList: List<LocationRange>?
  )

  @Serializable
  public data class StepOverParameter(
    /**
     * The skipList specifies location ranges that should be skipped on step over.
     */
    public val skipList: List<LocationRange>?
  )
}
