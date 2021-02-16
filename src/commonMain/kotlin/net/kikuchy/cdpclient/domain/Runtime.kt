package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.Domain

public val CDPClient.runtime: Runtime
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Runtime(this))

/**
 * Runtime domain exposes JavaScript runtime by means of remote evaluation and mirror objects.
 * Evaluation results are returned as mirror object that expose object type, string representation
 * and unique identifier that can be used for further object reference. Original objects are
 * maintained in memory unless they are either explicitly released or are released along with the
 * other objects in their object group.
 */
public class Runtime(
  private val client: CDPClient
) : Domain {
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public val bindingCalled: Flow<BindingCalledParameter> = client
          .events
          .filter {
              it.method == "bindingCalled"
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
  public val consoleAPICalled: Flow<ConsoleAPICalledParameter> = client
          .events
          .filter {
              it.method == "consoleAPICalled"
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
  public val exceptionRevoked: Flow<ExceptionRevokedParameter> = client
          .events
          .filter {
              it.method == "exceptionRevoked"
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
  public val exceptionThrown: Flow<ExceptionThrownParameter> = client
          .events
          .filter {
              it.method == "exceptionThrown"
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
  public val executionContextCreated: Flow<ExecutionContextCreatedParameter> = client
          .events
          .filter {
              it.method == "executionContextCreated"
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
  public val executionContextDestroyed: Flow<ExecutionContextDestroyedParameter> = client
          .events
          .filter {
              it.method == "executionContextDestroyed"
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
  public val executionContextsCleared: Flow<Unit> = client
          .events
          .filter {
              it.method == "executionContextsCleared"
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
  public val inspectRequested: Flow<InspectRequestedParameter> = client
          .events
          .filter {
              it.method == "inspectRequested"
          }
          .map {
              it.params
          }
          .filterNotNull()
          .map {
              Json.decodeFromJsonElement(it)
          }

  /**
   * Add handler to promise with given promise object id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun awaitPromise(args: AwaitPromiseParameter): AwaitPromiseReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Runtime.awaitPromise", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Add handler to promise with given promise object id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun awaitPromise(
    promiseObjectId: String,
    returnByValue: Boolean? = null,
    generatePreview: Boolean? = null
  ): AwaitPromiseReturn {
    val parameter = AwaitPromiseParameter(promiseObjectId = promiseObjectId, returnByValue =
        returnByValue, generatePreview = generatePreview)
    return awaitPromise(parameter)
  }

  /**
   * Calls function with given declaration on the given object. Object group of the result is
   * inherited from the target object.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun callFunctionOn(args: CallFunctionOnParameter): CallFunctionOnReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Runtime.callFunctionOn", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Calls function with given declaration on the given object. Object group of the result is
   * inherited from the target object.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun callFunctionOn(
    functionDeclaration: String,
    objectId: String? = null,
    arguments: List<CallArgument>? = null,
    silent: Boolean? = null,
    returnByValue: Boolean? = null,
    generatePreview: Boolean? = null,
    userGesture: Boolean? = null,
    awaitPromise: Boolean? = null,
    executionContextId: Int? = null,
    objectGroup: String? = null
  ): CallFunctionOnReturn {
    val parameter = CallFunctionOnParameter(functionDeclaration = functionDeclaration, objectId =
        objectId, arguments = arguments, silent = silent, returnByValue = returnByValue,
        generatePreview = generatePreview, userGesture = userGesture, awaitPromise = awaitPromise,
        executionContextId = executionContextId, objectGroup = objectGroup)
    return callFunctionOn(parameter)
  }

  /**
   * Compiles expression.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun compileScript(args: CompileScriptParameter): CompileScriptReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Runtime.compileScript", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Compiles expression.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun compileScript(
    expression: String,
    sourceURL: String,
    persistScript: Boolean,
    executionContextId: Int? = null
  ): CompileScriptReturn {
    val parameter = CompileScriptParameter(expression = expression, sourceURL = sourceURL,
        persistScript = persistScript, executionContextId = executionContextId)
    return compileScript(parameter)
  }

  /**
   * Disables reporting of execution contexts creation.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun disable(): Unit {
    val parameter = null
    client.callCommand("Runtime.disable", parameter)
  }

  /**
   * Discards collected exceptions and console API calls.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun discardConsoleEntries(): Unit {
    val parameter = null
    client.callCommand("Runtime.discardConsoleEntries", parameter)
  }

  /**
   * Enables reporting of execution contexts creation by means of `executionContextCreated` event.
   * When the reporting gets enabled the event will be sent immediately for each existing execution
   * context.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun enable(): Unit {
    val parameter = null
    client.callCommand("Runtime.enable", parameter)
  }

  /**
   * Evaluates expression on global object.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun evaluate(args: EvaluateParameter): EvaluateReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Runtime.evaluate", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Evaluates expression on global object.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun evaluate(
    expression: String,
    objectGroup: String? = null,
    includeCommandLineAPI: Boolean? = null,
    silent: Boolean? = null,
    contextId: Int? = null,
    returnByValue: Boolean? = null,
    generatePreview: Boolean? = null,
    userGesture: Boolean? = null,
    awaitPromise: Boolean? = null,
    throwOnSideEffect: Boolean? = null,
    timeout: Double? = null,
    disableBreaks: Boolean? = null,
    replMode: Boolean? = null,
    allowUnsafeEvalBlockedByCSP: Boolean? = null
  ): EvaluateReturn {
    val parameter = EvaluateParameter(expression = expression, objectGroup = objectGroup,
        includeCommandLineAPI = includeCommandLineAPI, silent = silent, contextId = contextId,
        returnByValue = returnByValue, generatePreview = generatePreview, userGesture = userGesture,
        awaitPromise = awaitPromise, throwOnSideEffect = throwOnSideEffect, timeout = timeout,
        disableBreaks = disableBreaks, replMode = replMode, allowUnsafeEvalBlockedByCSP =
        allowUnsafeEvalBlockedByCSP)
    return evaluate(parameter)
  }

  /**
   * Returns the isolate id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getIsolateId(): GetIsolateIdReturn {
    val parameter = null
    val result = client.callCommand("Runtime.getIsolateId", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns the JavaScript heap usage.
   * It is the total usage of the corresponding isolate not scoped to a particular Runtime.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getHeapUsage(): GetHeapUsageReturn {
    val parameter = null
    val result = client.callCommand("Runtime.getHeapUsage", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns properties of a given object. Object group of the result is inherited from the target
   * object.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getProperties(args: GetPropertiesParameter): GetPropertiesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Runtime.getProperties", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns properties of a given object. Object group of the result is inherited from the target
   * object.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun getProperties(
    objectId: String,
    ownProperties: Boolean? = null,
    accessorPropertiesOnly: Boolean? = null,
    generatePreview: Boolean? = null
  ): GetPropertiesReturn {
    val parameter = GetPropertiesParameter(objectId = objectId, ownProperties = ownProperties,
        accessorPropertiesOnly = accessorPropertiesOnly, generatePreview = generatePreview)
    return getProperties(parameter)
  }

  /**
   * Returns all let, const and class variables from global scope.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun globalLexicalScopeNames(args: GlobalLexicalScopeNamesParameter):
      GlobalLexicalScopeNamesReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Runtime.globalLexicalScopeNames", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Returns all let, const and class variables from global scope.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun globalLexicalScopeNames(executionContextId: Int? = null):
      GlobalLexicalScopeNamesReturn {
    val parameter = GlobalLexicalScopeNamesParameter(executionContextId = executionContextId)
    return globalLexicalScopeNames(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun queryObjects(args: QueryObjectsParameter): QueryObjectsReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Runtime.queryObjects", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun queryObjects(prototypeObjectId: String, objectGroup: String? = null):
      QueryObjectsReturn {
    val parameter = QueryObjectsParameter(prototypeObjectId = prototypeObjectId, objectGroup =
        objectGroup)
    return queryObjects(parameter)
  }

  /**
   * Releases remote object with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun releaseObject(args: ReleaseObjectParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Runtime.releaseObject", parameter)
  }

  /**
   * Releases remote object with given id.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun releaseObject(objectId: String): Unit {
    val parameter = ReleaseObjectParameter(objectId = objectId)
    releaseObject(parameter)
  }

  /**
   * Releases all remote objects that belong to a given group.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun releaseObjectGroup(args: ReleaseObjectGroupParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Runtime.releaseObjectGroup", parameter)
  }

  /**
   * Releases all remote objects that belong to a given group.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun releaseObjectGroup(objectGroup: String): Unit {
    val parameter = ReleaseObjectGroupParameter(objectGroup = objectGroup)
    releaseObjectGroup(parameter)
  }

  /**
   * Tells inspected instance to run if it was waiting for debugger to attach.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun runIfWaitingForDebugger(): Unit {
    val parameter = null
    client.callCommand("Runtime.runIfWaitingForDebugger", parameter)
  }

  /**
   * Runs script with given id in a given context.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun runScript(args: RunScriptParameter): RunScriptReturn {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    val result = client.callCommand("Runtime.runScript", parameter)
    return result!!.let { Json.decodeFromJsonElement(it) }
  }

  /**
   * Runs script with given id in a given context.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun runScript(
    scriptId: String,
    executionContextId: Int? = null,
    objectGroup: String? = null,
    silent: Boolean? = null,
    includeCommandLineAPI: Boolean? = null,
    returnByValue: Boolean? = null,
    generatePreview: Boolean? = null,
    awaitPromise: Boolean? = null
  ): RunScriptReturn {
    val parameter = RunScriptParameter(scriptId = scriptId, executionContextId = executionContextId,
        objectGroup = objectGroup, silent = silent, includeCommandLineAPI = includeCommandLineAPI,
        returnByValue = returnByValue, generatePreview = generatePreview, awaitPromise =
        awaitPromise)
    return runScript(parameter)
  }

  /**
   * Enables or disables async call stacks tracking.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setAsyncCallStackDepth(args: SetAsyncCallStackDepthParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Runtime.setAsyncCallStackDepth", parameter)
  }

  /**
   * Enables or disables async call stacks tracking.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setAsyncCallStackDepth(maxDepth: Int): Unit {
    val parameter = SetAsyncCallStackDepthParameter(maxDepth = maxDepth)
    setAsyncCallStackDepth(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend
      fun setCustomObjectFormatterEnabled(args: SetCustomObjectFormatterEnabledParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Runtime.setCustomObjectFormatterEnabled", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setCustomObjectFormatterEnabled(enabled: Boolean): Unit {
    val parameter = SetCustomObjectFormatterEnabledParameter(enabled = enabled)
    setCustomObjectFormatterEnabled(parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setMaxCallStackSizeToCapture(args: SetMaxCallStackSizeToCaptureParameter):
      Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Runtime.setMaxCallStackSizeToCapture", parameter)
  }

  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun setMaxCallStackSizeToCapture(size: Int): Unit {
    val parameter = SetMaxCallStackSizeToCaptureParameter(size = size)
    setMaxCallStackSizeToCapture(parameter)
  }

  /**
   * Terminate current or next JavaScript execution.
   * Will cancel the termination when the outer-most script execution ends.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun terminateExecution(): Unit {
    val parameter = null
    client.callCommand("Runtime.terminateExecution", parameter)
  }

  /**
   * If executionContextId is empty, adds binding with the given name on the
   * global objects of all inspected contexts, including those created later,
   * bindings survive reloads.
   * If executionContextId is specified, adds binding only on global object of
   * given execution context.
   * Binding function takes exactly one argument, this argument should be string,
   * in case of any other input, function throws an exception.
   * Each binding function call produces Runtime.bindingCalled notification.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun addBinding(args: AddBindingParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Runtime.addBinding", parameter)
  }

  /**
   * If executionContextId is empty, adds binding with the given name on the
   * global objects of all inspected contexts, including those created later,
   * bindings survive reloads.
   * If executionContextId is specified, adds binding only on global object of
   * given execution context.
   * Binding function takes exactly one argument, this argument should be string,
   * in case of any other input, function throws an exception.
   * Each binding function call produces Runtime.bindingCalled notification.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun addBinding(name: String, executionContextId: Int? = null): Unit {
    val parameter = AddBindingParameter(name = name, executionContextId = executionContextId)
    addBinding(parameter)
  }

  /**
   * This method does not remove binding function from global object but
   * unsubscribes current runtime agent from Runtime.bindingCalled notifications.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun removeBinding(args: RemoveBindingParameter): Unit {
    val parameter = Json { encodeDefaults = false }.encodeToJsonElement(args)
    client.callCommand("Runtime.removeBinding", parameter)
  }

  /**
   * This method does not remove binding function from global object but
   * unsubscribes current runtime agent from Runtime.bindingCalled notifications.
   */
  @ExperimentalCoroutinesApi
  @ExperimentalSerializationApi
  public suspend fun removeBinding(name: String): Unit {
    val parameter = RemoveBindingParameter(name = name)
    removeBinding(parameter)
  }

  /**
   * Mirror object referencing original JavaScript object.
   */
  @Serializable
  public data class RemoteObject(
    /**
     * Object type.
     */
    public val type: String,
    /**
     * Object subtype hint. Specified for `object` or `wasm` type values only.
     */
    public val subtype: String? = null,
    /**
     * Object class (constructor) name. Specified for `object` type values only.
     */
    public val className: String? = null,
    /**
     * Remote object value in case of primitive values or JSON values (if it was requested).
     */
    public val value: JsonElement? = null,
    /**
     * Primitive value which can not be JSON-stringified does not have `value`, but gets this
     * property.
     */
    public val unserializableValue: String? = null,
    /**
     * String representation of the object.
     */
    public val description: String? = null,
    /**
     * Unique object identifier (for non-primitive values).
     */
    public val objectId: String? = null,
    /**
     * Preview containing abbreviated property values. Specified for `object` type values only.
     */
    public val preview: ObjectPreview? = null,
    public val customPreview: CustomPreview? = null
  )

  @Serializable
  public data class CustomPreview(
    /**
     * The JSON-stringified result of formatter.header(object, config) call.
     * It contains json ML array that represents RemoteObject.
     */
    public val header: String,
    /**
     * If formatter returns true as a result of formatter.hasBody call then bodyGetterId will
     * contain RemoteObjectId for the function that returns result of formatter.body(object, config)
     * call.
     * The result value is json ML array.
     */
    public val bodyGetterId: String? = null
  )

  /**
   * Object containing abbreviated remote object value.
   */
  @Serializable
  public data class ObjectPreview(
    /**
     * Object type.
     */
    public val type: String,
    /**
     * Object subtype hint. Specified for `object` type values only.
     */
    public val subtype: String? = null,
    /**
     * String representation of the object.
     */
    public val description: String? = null,
    /**
     * True iff some of the properties or entries of the original object did not fit.
     */
    public val overflow: Boolean,
    /**
     * List of the properties.
     */
    public val properties: List<PropertyPreview>,
    /**
     * List of the entries. Specified for `map` and `set` subtype values only.
     */
    public val entries: List<EntryPreview>? = null
  )

  @Serializable
  public data class PropertyPreview(
    /**
     * Property name.
     */
    public val name: String,
    /**
     * Object type. Accessor means that the property itself is an accessor property.
     */
    public val type: String,
    /**
     * User-friendly property value string.
     */
    public val value: String? = null,
    /**
     * Nested value preview.
     */
    public val valuePreview: ObjectPreview? = null,
    /**
     * Object subtype hint. Specified for `object` type values only.
     */
    public val subtype: String? = null
  )

  @Serializable
  public data class EntryPreview(
    /**
     * Preview of the key. Specified for map-like collection entries.
     */
    public val key: ObjectPreview? = null,
    /**
     * Preview of the value.
     */
    public val value: ObjectPreview
  )

  /**
   * Object property descriptor.
   */
  @Serializable
  public data class PropertyDescriptor(
    /**
     * Property name or symbol description.
     */
    public val name: String,
    /**
     * The value associated with the property.
     */
    public val value: RemoteObject? = null,
    /**
     * True if the value associated with the property may be changed (data descriptors only).
     */
    public val writable: Boolean? = null,
    /**
     * A function which serves as a getter for the property, or `undefined` if there is no getter
     * (accessor descriptors only).
     */
    public val `get`: RemoteObject? = null,
    /**
     * A function which serves as a setter for the property, or `undefined` if there is no setter
     * (accessor descriptors only).
     */
    public val `set`: RemoteObject? = null,
    /**
     * True if the type of this property descriptor may be changed and if the property may be
     * deleted from the corresponding object.
     */
    public val configurable: Boolean,
    /**
     * True if this property shows up during enumeration of the properties on the corresponding
     * object.
     */
    public val enumerable: Boolean,
    /**
     * True if the result was thrown during the evaluation.
     */
    public val wasThrown: Boolean? = null,
    /**
     * True if the property is owned for the object.
     */
    public val isOwn: Boolean? = null,
    /**
     * Property symbol object, if the property is of the `symbol` type.
     */
    public val symbol: RemoteObject? = null
  )

  /**
   * Object internal property descriptor. This property isn't normally visible in JavaScript code.
   */
  @Serializable
  public data class InternalPropertyDescriptor(
    /**
     * Conventional property name.
     */
    public val name: String,
    /**
     * The value associated with the property.
     */
    public val value: RemoteObject? = null
  )

  /**
   * Object private field descriptor.
   */
  @Serializable
  public data class PrivatePropertyDescriptor(
    /**
     * Private property name.
     */
    public val name: String,
    /**
     * The value associated with the private property.
     */
    public val value: RemoteObject? = null,
    /**
     * A function which serves as a getter for the private property,
     * or `undefined` if there is no getter (accessor descriptors only).
     */
    public val `get`: RemoteObject? = null,
    /**
     * A function which serves as a setter for the private property,
     * or `undefined` if there is no setter (accessor descriptors only).
     */
    public val `set`: RemoteObject? = null
  )

  /**
   * Represents function call argument. Either remote object id `objectId`, primitive `value`,
   * unserializable primitive value or neither of (for undefined) them should be specified.
   */
  @Serializable
  public data class CallArgument(
    /**
     * Primitive value or serializable javascript object.
     */
    public val value: JsonElement? = null,
    /**
     * Primitive value which can not be JSON-stringified.
     */
    public val unserializableValue: String? = null,
    /**
     * Remote object handle.
     */
    public val objectId: String? = null
  )

  /**
   * Description of an isolated world.
   */
  @Serializable
  public data class ExecutionContextDescription(
    /**
     * Unique id of the execution context. It can be used to specify in which execution context
     * script evaluation should be performed.
     */
    public val id: Int,
    /**
     * Execution context origin.
     */
    public val origin: String,
    /**
     * Human readable name describing given context.
     */
    public val name: String,
    /**
     * Embedder-specific auxiliary data.
     */
    public val auxData: Map<String, JsonElement>? = null
  )

  /**
   * Detailed information about exception (or error) that was thrown during script compilation or
   * execution.
   */
  @Serializable
  public data class ExceptionDetails(
    /**
     * Exception id.
     */
    public val exceptionId: Int,
    /**
     * Exception text, which should be used together with exception object when available.
     */
    public val text: String,
    /**
     * Line number of the exception location (0-based).
     */
    public val lineNumber: Int,
    /**
     * Column number of the exception location (0-based).
     */
    public val columnNumber: Int,
    /**
     * Script ID of the exception location.
     */
    public val scriptId: String? = null,
    /**
     * URL of the exception location, to be used when the script was not reported.
     */
    public val url: String? = null,
    /**
     * JavaScript stack trace if available.
     */
    public val stackTrace: StackTrace? = null,
    /**
     * Exception object if available.
     */
    public val exception: RemoteObject? = null,
    /**
     * Identifier of the context where exception happened.
     */
    public val executionContextId: Int? = null
  )

  /**
   * Stack entry for runtime errors and assertions.
   */
  @Serializable
  public data class CallFrame(
    /**
     * JavaScript function name.
     */
    public val functionName: String,
    /**
     * JavaScript script id.
     */
    public val scriptId: String,
    /**
     * JavaScript script name or url.
     */
    public val url: String,
    /**
     * JavaScript script line number (0-based).
     */
    public val lineNumber: Int,
    /**
     * JavaScript script column number (0-based).
     */
    public val columnNumber: Int
  )

  /**
   * Call frames for assertions or error messages.
   */
  @Serializable
  public data class StackTrace(
    /**
     * String label of this stack trace. For async traces this may be a name of the function that
     * initiated the async call.
     */
    public val description: String? = null,
    /**
     * JavaScript function name.
     */
    public val callFrames: List<CallFrame>,
    /**
     * Asynchronous JavaScript stack trace that preceded this stack, if available.
     */
    public val parent: StackTrace? = null,
    /**
     * Asynchronous JavaScript stack trace that preceded this stack, if available.
     */
    public val parentId: StackTraceId? = null
  )

  /**
   * If `debuggerId` is set stack trace comes from another debugger and can be resolved there. This
   * allows to track cross-debugger calls. See `Runtime.StackTrace` and `Debugger.paused` for
   * usages.
   */
  @Serializable
  public data class StackTraceId(
    public val id: String,
    public val debuggerId: String? = null
  )

  /**
   * Notification is issued every time when binding is called.
   */
  public data class BindingCalledParameter(
    public val name: String,
    public val payload: String,
    /**
     * Identifier of the context where the call was made.
     */
    public val executionContextId: Int
  )

  /**
   * Issued when console API was called.
   */
  public data class ConsoleAPICalledParameter(
    /**
     * Type of the call.
     */
    public val type: String,
    /**
     * Call arguments.
     */
    public val args: List<RemoteObject>,
    /**
     * Identifier of the context where the call was made.
     */
    public val executionContextId: Int,
    /**
     * Call timestamp.
     */
    public val timestamp: Double,
    /**
     * Stack trace captured when the call was made. The async stack chain is automatically reported
     * for
     * the following call types: `assert`, `error`, `trace`, `warning`. For other types the async
     * call
     * chain can be retrieved using `Debugger.getStackTrace` and `stackTrace.parentId` field.
     */
    public val stackTrace: StackTrace? = null,
    /**
     * Console context descriptor for calls on non-default console context (not console.*):
     * 'anonymous#unique-logger-id' for call on unnamed context, 'name#unique-logger-id' for call
     * on named context.
     */
    public val context: String? = null
  )

  /**
   * Issued when unhandled exception was revoked.
   */
  public data class ExceptionRevokedParameter(
    /**
     * Reason describing why exception was revoked.
     */
    public val reason: String,
    /**
     * The id of revoked exception, as reported in `exceptionThrown`.
     */
    public val exceptionId: Int
  )

  /**
   * Issued when exception was thrown and unhandled.
   */
  public data class ExceptionThrownParameter(
    /**
     * Timestamp of the exception.
     */
    public val timestamp: Double,
    public val exceptionDetails: ExceptionDetails
  )

  /**
   * Issued when new execution context is created.
   */
  public data class ExecutionContextCreatedParameter(
    /**
     * A newly created execution context.
     */
    public val context: ExecutionContextDescription
  )

  /**
   * Issued when execution context is destroyed.
   */
  public data class ExecutionContextDestroyedParameter(
    /**
     * Id of the destroyed context
     */
    public val executionContextId: Int
  )

  /**
   * Issued when object should be inspected (for example, as a result of inspect() command line API
   * call).
   */
  public data class InspectRequestedParameter(
    public val `object`: RemoteObject,
    public val hints: Map<String, JsonElement>
  )

  @Serializable
  public data class AwaitPromiseParameter(
    /**
     * Identifier of the promise.
     */
    public val promiseObjectId: String,
    /**
     * Whether the result is expected to be a JSON object that should be sent by value.
     */
    public val returnByValue: Boolean? = null,
    /**
     * Whether preview should be generated for the result.
     */
    public val generatePreview: Boolean? = null
  )

  @Serializable
  public data class AwaitPromiseReturn(
    /**
     * Promise result. Will contain rejected value if promise was rejected.
     */
    public val result: RemoteObject,
    /**
     * Exception details if stack strace is available.
     */
    public val exceptionDetails: ExceptionDetails?
  )

  @Serializable
  public data class CallFunctionOnParameter(
    /**
     * Declaration of the function to call.
     */
    public val functionDeclaration: String,
    /**
     * Identifier of the object to call function on. Either objectId or executionContextId should
     * be specified.
     */
    public val objectId: String? = null,
    /**
     * Call arguments. All call arguments must belong to the same JavaScript world as the target
     * object.
     */
    public val arguments: List<CallArgument>? = null,
    /**
     * In silent mode exceptions thrown during evaluation are not reported and do not pause
     * execution. Overrides `setPauseOnException` state.
     */
    public val silent: Boolean? = null,
    /**
     * Whether the result is expected to be a JSON object which should be sent by value.
     */
    public val returnByValue: Boolean? = null,
    /**
     * Whether preview should be generated for the result.
     */
    public val generatePreview: Boolean? = null,
    /**
     * Whether execution should be treated as initiated by user in the UI.
     */
    public val userGesture: Boolean? = null,
    /**
     * Whether execution should `await` for resulting value and return once awaited promise is
     * resolved.
     */
    public val awaitPromise: Boolean? = null,
    /**
     * Specifies execution context which global object will be used to call function on. Either
     * executionContextId or objectId should be specified.
     */
    public val executionContextId: Int? = null,
    /**
     * Symbolic group name that can be used to release multiple objects. If objectGroup is not
     * specified and objectId is, objectGroup will be inherited from object.
     */
    public val objectGroup: String? = null
  )

  @Serializable
  public data class CallFunctionOnReturn(
    /**
     * Call result.
     */
    public val result: RemoteObject,
    /**
     * Exception details.
     */
    public val exceptionDetails: ExceptionDetails?
  )

  @Serializable
  public data class CompileScriptParameter(
    /**
     * Expression to compile.
     */
    public val expression: String,
    /**
     * Source url to be set for the script.
     */
    public val sourceURL: String,
    /**
     * Specifies whether the compiled script should be persisted.
     */
    public val persistScript: Boolean,
    /**
     * Specifies in which execution context to perform script run. If the parameter is omitted the
     * evaluation will be performed in the context of the inspected page.
     */
    public val executionContextId: Int? = null
  )

  @Serializable
  public data class CompileScriptReturn(
    /**
     * Id of the script.
     */
    public val scriptId: String?,
    /**
     * Exception details.
     */
    public val exceptionDetails: ExceptionDetails?
  )

  @Serializable
  public data class EvaluateParameter(
    /**
     * Expression to evaluate.
     */
    public val expression: String,
    /**
     * Symbolic group name that can be used to release multiple objects.
     */
    public val objectGroup: String? = null,
    /**
     * Determines whether Command Line API should be available during the evaluation.
     */
    public val includeCommandLineAPI: Boolean? = null,
    /**
     * In silent mode exceptions thrown during evaluation are not reported and do not pause
     * execution. Overrides `setPauseOnException` state.
     */
    public val silent: Boolean? = null,
    /**
     * Specifies in which execution context to perform evaluation. If the parameter is omitted the
     * evaluation will be performed in the context of the inspected page.
     */
    public val contextId: Int? = null,
    /**
     * Whether the result is expected to be a JSON object that should be sent by value.
     */
    public val returnByValue: Boolean? = null,
    /**
     * Whether preview should be generated for the result.
     */
    public val generatePreview: Boolean? = null,
    /**
     * Whether execution should be treated as initiated by user in the UI.
     */
    public val userGesture: Boolean? = null,
    /**
     * Whether execution should `await` for resulting value and return once awaited promise is
     * resolved.
     */
    public val awaitPromise: Boolean? = null,
    /**
     * Whether to throw an exception if side effect cannot be ruled out during evaluation.
     * This implies `disableBreaks` below.
     */
    public val throwOnSideEffect: Boolean? = null,
    /**
     * Terminate execution after timing out (number of milliseconds).
     */
    public val timeout: Double? = null,
    /**
     * Disable breakpoints during execution.
     */
    public val disableBreaks: Boolean? = null,
    /**
     * Setting this flag to true enables `let` re-declaration and top-level `await`.
     * Note that `let` variables can only be re-declared if they originate from
     * `replMode` themselves.
     */
    public val replMode: Boolean? = null,
    /**
     * The Content Security Policy (CSP) for the target might block 'unsafe-eval'
     * which includes eval(), Function(), setTimeout() and setInterval()
     * when called with non-callable arguments. This flag bypasses CSP for this
     * evaluation and allows unsafe-eval. Defaults to true.
     */
    public val allowUnsafeEvalBlockedByCSP: Boolean? = null
  )

  @Serializable
  public data class EvaluateReturn(
    /**
     * Evaluation result.
     */
    public val result: RemoteObject,
    /**
     * Exception details.
     */
    public val exceptionDetails: ExceptionDetails?
  )

  @Serializable
  public data class GetIsolateIdReturn(
    /**
     * The isolate id.
     */
    public val id: String
  )

  @Serializable
  public data class GetHeapUsageReturn(
    /**
     * Used heap size in bytes.
     */
    public val usedSize: Double,
    /**
     * Allocated heap size in bytes.
     */
    public val totalSize: Double
  )

  @Serializable
  public data class GetPropertiesParameter(
    /**
     * Identifier of the object to return properties for.
     */
    public val objectId: String,
    /**
     * If true, returns properties belonging only to the element itself, not to its prototype
     * chain.
     */
    public val ownProperties: Boolean? = null,
    /**
     * If true, returns accessor properties (with getter/setter) only; internal properties are not
     * returned either.
     */
    public val accessorPropertiesOnly: Boolean? = null,
    /**
     * Whether preview should be generated for the results.
     */
    public val generatePreview: Boolean? = null
  )

  @Serializable
  public data class GetPropertiesReturn(
    /**
     * Object properties.
     */
    public val result: List<PropertyDescriptor>,
    /**
     * Internal object properties (only of the element itself).
     */
    public val internalProperties: List<InternalPropertyDescriptor>?,
    /**
     * Object private properties.
     */
    public val privateProperties: List<PrivatePropertyDescriptor>?,
    /**
     * Exception details.
     */
    public val exceptionDetails: ExceptionDetails?
  )

  @Serializable
  public data class GlobalLexicalScopeNamesParameter(
    /**
     * Specifies in which execution context to lookup global scope variables.
     */
    public val executionContextId: Int? = null
  )

  @Serializable
  public data class GlobalLexicalScopeNamesReturn(
    public val names: String
  )

  @Serializable
  public data class QueryObjectsParameter(
    /**
     * Identifier of the prototype to return objects for.
     */
    public val prototypeObjectId: String,
    /**
     * Symbolic group name that can be used to release the results.
     */
    public val objectGroup: String? = null
  )

  @Serializable
  public data class QueryObjectsReturn(
    /**
     * Array with objects.
     */
    public val objects: RemoteObject
  )

  @Serializable
  public data class ReleaseObjectParameter(
    /**
     * Identifier of the object to release.
     */
    public val objectId: String
  )

  @Serializable
  public data class ReleaseObjectGroupParameter(
    /**
     * Symbolic object group name.
     */
    public val objectGroup: String
  )

  @Serializable
  public data class RunScriptParameter(
    /**
     * Id of the script to run.
     */
    public val scriptId: String,
    /**
     * Specifies in which execution context to perform script run. If the parameter is omitted the
     * evaluation will be performed in the context of the inspected page.
     */
    public val executionContextId: Int? = null,
    /**
     * Symbolic group name that can be used to release multiple objects.
     */
    public val objectGroup: String? = null,
    /**
     * In silent mode exceptions thrown during evaluation are not reported and do not pause
     * execution. Overrides `setPauseOnException` state.
     */
    public val silent: Boolean? = null,
    /**
     * Determines whether Command Line API should be available during the evaluation.
     */
    public val includeCommandLineAPI: Boolean? = null,
    /**
     * Whether the result is expected to be a JSON object which should be sent by value.
     */
    public val returnByValue: Boolean? = null,
    /**
     * Whether preview should be generated for the result.
     */
    public val generatePreview: Boolean? = null,
    /**
     * Whether execution should `await` for resulting value and return once awaited promise is
     * resolved.
     */
    public val awaitPromise: Boolean? = null
  )

  @Serializable
  public data class RunScriptReturn(
    /**
     * Run result.
     */
    public val result: RemoteObject,
    /**
     * Exception details.
     */
    public val exceptionDetails: ExceptionDetails?
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
  public data class SetCustomObjectFormatterEnabledParameter(
    public val enabled: Boolean
  )

  @Serializable
  public data class SetMaxCallStackSizeToCaptureParameter(
    public val size: Int
  )

  @Serializable
  public data class AddBindingParameter(
    public val name: String,
    public val executionContextId: Int? = null
  )

  @Serializable
  public data class RemoveBindingParameter(
    public val name: String
  )
}
