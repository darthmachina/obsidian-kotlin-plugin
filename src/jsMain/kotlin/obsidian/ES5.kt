@file:Suppress("unused", "UNUSED_TYPEALIAS_PARAMETER", "INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS",)

package obsidian

import org.w3c.dom.DataTransfer
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent

typealias Record<K, T> = Any

external interface ClipboardEvent : Event {
    var clipboardData: DataTransfer?
}

external interface PointerEvent : MouseEvent {
    var height: Number
    var isPrimary: Boolean
    var pointerId: Number
    var pointerType: String
    var pressure: Number
    var tangentialPressure: Number
    var tiltX: Number
    var tiltY: Number
    var twist: Number
    var width: Number
}
