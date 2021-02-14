package net.kikuchy.cdpclient.domain

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
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

public val CDPClient.input: Input
  get() = getGeneratedDomain() ?: cacheGeneratedDomain(Input(this))

public class Input(
  private val client: CDPClient
) : Domain {
  /**
   * Dispatches a key event to the page.
   */
  public suspend fun dispatchKeyEvent(args: DispatchKeyEventParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.dispatchKeyEvent", parameter)
  }

  public suspend fun dispatchKeyEvent(
    type: String,
    modifiers: Int? = null,
    timestamp: Double? = null,
    text: String? = null,
    unmodifiedText: String? = null,
    keyIdentifier: String? = null,
    code: String? = null,
    key: String? = null,
    windowsVirtualKeyCode: Int? = null,
    nativeVirtualKeyCode: Int? = null,
    autoRepeat: Boolean? = null,
    isKeypad: Boolean? = null,
    isSystemKey: Boolean? = null,
    location: Int? = null,
    commands: String? = null
  ): Unit {
    val parameter = DispatchKeyEventParameter(type = type,modifiers = modifiers,timestamp =
        timestamp,text = text,unmodifiedText = unmodifiedText,keyIdentifier = keyIdentifier,code =
        code,key = key,windowsVirtualKeyCode = windowsVirtualKeyCode,nativeVirtualKeyCode =
        nativeVirtualKeyCode,autoRepeat = autoRepeat,isKeypad = isKeypad,isSystemKey =
        isSystemKey,location = location,commands = commands)
    dispatchKeyEvent(parameter)
  }

  /**
   * This method emulates inserting text that doesn't come from a key press,
   * for example an emoji keyboard or an IME.
   */
  public suspend fun insertText(args: InsertTextParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.insertText", parameter)
  }

  public suspend fun insertText(text: String): Unit {
    val parameter = InsertTextParameter(text = text)
    insertText(parameter)
  }

  /**
   * Dispatches a mouse event to the page.
   */
  public suspend fun dispatchMouseEvent(args: DispatchMouseEventParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.dispatchMouseEvent", parameter)
  }

  public suspend fun dispatchMouseEvent(
    type: String,
    x: Double,
    y: Double,
    modifiers: Int? = null,
    timestamp: Double? = null,
    button: MouseButton? = null,
    buttons: Int? = null,
    clickCount: Int? = null,
    force: Double? = null,
    tangentialPressure: Double? = null,
    tiltX: Int? = null,
    tiltY: Int? = null,
    twist: Int? = null,
    deltaX: Double? = null,
    deltaY: Double? = null,
    pointerType: String? = null
  ): Unit {
    val parameter = DispatchMouseEventParameter(type = type,x = x,y = y,modifiers =
        modifiers,timestamp = timestamp,button = button,buttons = buttons,clickCount =
        clickCount,force = force,tangentialPressure = tangentialPressure,tiltX = tiltX,tiltY =
        tiltY,twist = twist,deltaX = deltaX,deltaY = deltaY,pointerType = pointerType)
    dispatchMouseEvent(parameter)
  }

  /**
   * Dispatches a touch event to the page.
   */
  public suspend fun dispatchTouchEvent(args: DispatchTouchEventParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.dispatchTouchEvent", parameter)
  }

  public suspend fun dispatchTouchEvent(
    type: String,
    touchPoints: List<TouchPoint>,
    modifiers: Int? = null,
    timestamp: Double? = null
  ): Unit {
    val parameter = DispatchTouchEventParameter(type = type,touchPoints = touchPoints,modifiers =
        modifiers,timestamp = timestamp)
    dispatchTouchEvent(parameter)
  }

  /**
   * Emulates touch event from the mouse event parameters.
   */
  public suspend fun emulateTouchFromMouseEvent(args: EmulateTouchFromMouseEventParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.emulateTouchFromMouseEvent", parameter)
  }

  public suspend fun emulateTouchFromMouseEvent(
    type: String,
    x: Int,
    y: Int,
    button: MouseButton,
    timestamp: Double? = null,
    deltaX: Double? = null,
    deltaY: Double? = null,
    modifiers: Int? = null,
    clickCount: Int? = null
  ): Unit {
    val parameter = EmulateTouchFromMouseEventParameter(type = type,x = x,y = y,button =
        button,timestamp = timestamp,deltaX = deltaX,deltaY = deltaY,modifiers =
        modifiers,clickCount = clickCount)
    emulateTouchFromMouseEvent(parameter)
  }

  /**
   * Ignores input events (useful while auditing page).
   */
  public suspend fun setIgnoreInputEvents(args: SetIgnoreInputEventsParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.setIgnoreInputEvents", parameter)
  }

  public suspend fun setIgnoreInputEvents(ignore: Boolean): Unit {
    val parameter = SetIgnoreInputEventsParameter(ignore = ignore)
    setIgnoreInputEvents(parameter)
  }

  /**
   * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
   */
  public suspend fun synthesizePinchGesture(args: SynthesizePinchGestureParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.synthesizePinchGesture", parameter)
  }

  public suspend fun synthesizePinchGesture(
    x: Double,
    y: Double,
    scaleFactor: Double,
    relativeSpeed: Int? = null,
    gestureSourceType: GestureSourceType? = null
  ): Unit {
    val parameter = SynthesizePinchGestureParameter(x = x,y = y,scaleFactor =
        scaleFactor,relativeSpeed = relativeSpeed,gestureSourceType = gestureSourceType)
    synthesizePinchGesture(parameter)
  }

  /**
   * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
   */
  public suspend fun synthesizeScrollGesture(args: SynthesizeScrollGestureParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.synthesizeScrollGesture", parameter)
  }

  public suspend fun synthesizeScrollGesture(
    x: Double,
    y: Double,
    xDistance: Double? = null,
    yDistance: Double? = null,
    xOverscroll: Double? = null,
    yOverscroll: Double? = null,
    preventFling: Boolean? = null,
    speed: Int? = null,
    gestureSourceType: GestureSourceType? = null,
    repeatCount: Int? = null,
    repeatDelayMs: Int? = null,
    interactionMarkerName: String? = null
  ): Unit {
    val parameter = SynthesizeScrollGestureParameter(x = x,y = y,xDistance = xDistance,yDistance =
        yDistance,xOverscroll = xOverscroll,yOverscroll = yOverscroll,preventFling =
        preventFling,speed = speed,gestureSourceType = gestureSourceType,repeatCount =
        repeatCount,repeatDelayMs = repeatDelayMs,interactionMarkerName = interactionMarkerName)
    synthesizeScrollGesture(parameter)
  }

  /**
   * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
   */
  public suspend fun synthesizeTapGesture(args: SynthesizeTapGestureParameter): Unit {
    val parameter = Json.encodeToJsonElement(args)
    client.callCommand("Input.synthesizeTapGesture", parameter)
  }

  public suspend fun synthesizeTapGesture(
    x: Double,
    y: Double,
    duration: Int? = null,
    tapCount: Int? = null,
    gestureSourceType: GestureSourceType? = null
  ): Unit {
    val parameter = SynthesizeTapGestureParameter(x = x,y = y,duration = duration,tapCount =
        tapCount,gestureSourceType = gestureSourceType)
    synthesizeTapGesture(parameter)
  }

  @Serializable
  public class TouchPoint(
    /**
     * X coordinate of the event relative to the main frame's viewport in CSS pixels.
     */
    public val x: Double,
    /**
     * Y coordinate of the event relative to the main frame's viewport in CSS pixels. 0 refers to
     * the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
     */
    public val y: Double,
    /**
     * X radius of the touch area (default: 1.0).
     */
    public val radiusX: Double? = null,
    /**
     * Y radius of the touch area (default: 1.0).
     */
    public val radiusY: Double? = null,
    /**
     * Rotation angle (default: 0.0).
     */
    public val rotationAngle: Double? = null,
    /**
     * Force (default: 1.0).
     */
    public val force: Double? = null,
    /**
     * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
     */
    public val tangentialPressure: Double? = null,
    /**
     * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y
     * axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0)
     */
    public val tiltX: Int? = null,
    /**
     * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X
     * axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
     */
    public val tiltY: Int? = null,
    /**
     * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range
     * [0,359] (default: 0).
     */
    public val twist: Int? = null,
    /**
     * Identifier used to track touch sources between events, must be unique within an event.
     */
    public val id: Double? = null
  )

  @Serializable
  public enum class GestureSourceType {
    @SerialName("default")
    DEFAULT,
    @SerialName("touch")
    TOUCH,
    @SerialName("mouse")
    MOUSE,
  }

  @Serializable
  public enum class MouseButton {
    @SerialName("none")
    NONE,
    @SerialName("left")
    LEFT,
    @SerialName("middle")
    MIDDLE,
    @SerialName("right")
    RIGHT,
    @SerialName("back")
    BACK,
    @SerialName("forward")
    FORWARD,
  }

  @Serializable
  public data class DispatchKeyEventParameter(
    /**
     * Type of the key event.
     */
    public val type: String,
    /**
     * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
     * (default: 0).
     */
    public val modifiers: Int?,
    /**
     * Time at which the event occurred.
     */
    public val timestamp: Double?,
    /**
     * Text as generated by processing a virtual key code with a keyboard layout. Not needed for
     * for `keyUp` and `rawKeyDown` events (default: "")
     */
    public val text: String?,
    /**
     * Text that would have been generated by the keyboard if no modifiers were pressed (except for
     * shift). Useful for shortcut (accelerator) key handling (default: "").
     */
    public val unmodifiedText: String?,
    /**
     * Unique key identifier (e.g., 'U+0041') (default: "").
     */
    public val keyIdentifier: String?,
    /**
     * Unique DOM defined string value for each physical key (e.g., 'KeyA') (default: "").
     */
    public val code: String?,
    /**
     * Unique DOM defined string value describing the meaning of the key in the context of active
     * modifiers, keyboard layout, etc (e.g., 'AltGr') (default: "").
     */
    public val key: String?,
    /**
     * Windows virtual key code (default: 0).
     */
    public val windowsVirtualKeyCode: Int?,
    /**
     * Native virtual key code (default: 0).
     */
    public val nativeVirtualKeyCode: Int?,
    /**
     * Whether the event was generated from auto repeat (default: false).
     */
    public val autoRepeat: Boolean?,
    /**
     * Whether the event was generated from the keypad (default: false).
     */
    public val isKeypad: Boolean?,
    /**
     * Whether the event was a system key event (default: false).
     */
    public val isSystemKey: Boolean?,
    /**
     * Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default:
     * 0).
     */
    public val location: Int?,
    /**
     * Editing commands to send with the key event (e.g., 'selectAll') (default: []).
     * These are related to but not equal the command names used in `document.execCommand` and
     * NSStandardKeyBindingResponding.
     * See
     * https://source.chromium.org/chromium/chromium/src/+/master:third_party/blink/renderer/core/editing/commands/editor_command_names.h
     * for valid command names.
     */
    public val commands: String?
  )

  @Serializable
  public data class InsertTextParameter(
    /**
     * The text to insert.
     */
    public val text: String
  )

  @Serializable
  public data class DispatchMouseEventParameter(
    /**
     * Type of the mouse event.
     */
    public val type: String,
    /**
     * X coordinate of the event relative to the main frame's viewport in CSS pixels.
     */
    public val x: Double,
    /**
     * Y coordinate of the event relative to the main frame's viewport in CSS pixels. 0 refers to
     * the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
     */
    public val y: Double,
    /**
     * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
     * (default: 0).
     */
    public val modifiers: Int?,
    /**
     * Time at which the event occurred.
     */
    public val timestamp: Double?,
    /**
     * Mouse button (default: "none").
     */
    public val button: MouseButton?,
    /**
     * A number indicating which buttons are pressed on the mouse when a mouse event is triggered.
     * Left=1, Right=2, Middle=4, Back=8, Forward=16, None=0.
     */
    public val buttons: Int?,
    /**
     * Number of times the mouse button was clicked (default: 0).
     */
    public val clickCount: Int?,
    /**
     * The normalized pressure, which has a range of [0,1] (default: 0).
     */
    public val force: Double?,
    /**
     * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
     */
    public val tangentialPressure: Double?,
    /**
     * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y
     * axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0).
     */
    public val tiltX: Int?,
    /**
     * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X
     * axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
     */
    public val tiltY: Int?,
    /**
     * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range
     * [0,359] (default: 0).
     */
    public val twist: Int?,
    /**
     * X delta in CSS pixels for mouse wheel event (default: 0).
     */
    public val deltaX: Double?,
    /**
     * Y delta in CSS pixels for mouse wheel event (default: 0).
     */
    public val deltaY: Double?,
    /**
     * Pointer type (default: "mouse").
     */
    public val pointerType: String?
  )

  @Serializable
  public data class DispatchTouchEventParameter(
    /**
     * Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while
     * TouchStart and TouchMove must contains at least one.
     */
    public val type: String,
    /**
     * Active touch points on the touch device. One event per any changed point (compared to
     * previous touch event in a sequence) is generated, emulating pressing/moving/releasing points
     * one by one.
     */
    public val touchPoints: List<TouchPoint>,
    /**
     * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
     * (default: 0).
     */
    public val modifiers: Int?,
    /**
     * Time at which the event occurred.
     */
    public val timestamp: Double?
  )

  @Serializable
  public data class EmulateTouchFromMouseEventParameter(
    /**
     * Type of the mouse event.
     */
    public val type: String,
    /**
     * X coordinate of the mouse pointer in DIP.
     */
    public val x: Int,
    /**
     * Y coordinate of the mouse pointer in DIP.
     */
    public val y: Int,
    /**
     * Mouse button. Only "none", "left", "right" are supported.
     */
    public val button: MouseButton,
    /**
     * Time at which the event occurred (default: current time).
     */
    public val timestamp: Double?,
    /**
     * X delta in DIP for mouse wheel event (default: 0).
     */
    public val deltaX: Double?,
    /**
     * Y delta in DIP for mouse wheel event (default: 0).
     */
    public val deltaY: Double?,
    /**
     * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
     * (default: 0).
     */
    public val modifiers: Int?,
    /**
     * Number of times the mouse button was clicked (default: 0).
     */
    public val clickCount: Int?
  )

  @Serializable
  public data class SetIgnoreInputEventsParameter(
    /**
     * Ignores input events processing when set to true.
     */
    public val ignore: Boolean
  )

  @Serializable
  public data class SynthesizePinchGestureParameter(
    /**
     * X coordinate of the start of the gesture in CSS pixels.
     */
    public val x: Double,
    /**
     * Y coordinate of the start of the gesture in CSS pixels.
     */
    public val y: Double,
    /**
     * Relative scale factor after zooming (>1.0 zooms in, <1.0 zooms out).
     */
    public val scaleFactor: Double,
    /**
     * Relative pointer speed in pixels per second (default: 800).
     */
    public val relativeSpeed: Int?,
    /**
     * Which type of input events to be generated (default: 'default', which queries the platform
     * for the preferred input type).
     */
    public val gestureSourceType: GestureSourceType?
  )

  @Serializable
  public data class SynthesizeScrollGestureParameter(
    /**
     * X coordinate of the start of the gesture in CSS pixels.
     */
    public val x: Double,
    /**
     * Y coordinate of the start of the gesture in CSS pixels.
     */
    public val y: Double,
    /**
     * The distance to scroll along the X axis (positive to scroll left).
     */
    public val xDistance: Double?,
    /**
     * The distance to scroll along the Y axis (positive to scroll up).
     */
    public val yDistance: Double?,
    /**
     * The number of additional pixels to scroll back along the X axis, in addition to the given
     * distance.
     */
    public val xOverscroll: Double?,
    /**
     * The number of additional pixels to scroll back along the Y axis, in addition to the given
     * distance.
     */
    public val yOverscroll: Double?,
    /**
     * Prevent fling (default: true).
     */
    public val preventFling: Boolean?,
    /**
     * Swipe speed in pixels per second (default: 800).
     */
    public val speed: Int?,
    /**
     * Which type of input events to be generated (default: 'default', which queries the platform
     * for the preferred input type).
     */
    public val gestureSourceType: GestureSourceType?,
    /**
     * The number of times to repeat the gesture (default: 0).
     */
    public val repeatCount: Int?,
    /**
     * The number of milliseconds delay between each repeat. (default: 250).
     */
    public val repeatDelayMs: Int?,
    /**
     * The name of the interaction markers to generate, if not empty (default: "").
     */
    public val interactionMarkerName: String?
  )

  @Serializable
  public data class SynthesizeTapGestureParameter(
    /**
     * X coordinate of the start of the gesture in CSS pixels.
     */
    public val x: Double,
    /**
     * Y coordinate of the start of the gesture in CSS pixels.
     */
    public val y: Double,
    /**
     * Duration between touchdown and touchup events in ms (default: 50).
     */
    public val duration: Int?,
    /**
     * Number of times to perform the tap (e.g. 2 for double tap, default: 1).
     */
    public val tapCount: Int?,
    /**
     * Which type of input events to be generated (default: 'default', which queries the platform
     * for the preferred input type).
     */
    public val gestureSourceType: GestureSourceType?
  )
}
