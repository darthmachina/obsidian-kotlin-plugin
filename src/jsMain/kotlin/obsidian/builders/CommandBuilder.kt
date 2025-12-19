package obsidian.builders

import Command
import Editor
import Hotkey
import MarkdownView

@Suppress("unused")
@OptIn(ExperimentalJsExport::class)
@JsExport
class CommandBuilder(
    var id: String,
    var name: String
) {
    private val command = InternalCommand(id, name)

    fun icon(icon: String) = apply { command.icon = icon }
    fun mobileOnly(flag: Boolean) = apply { command.mobileOnly = flag }
    fun callback(function: () -> Any) = apply { command.callback = function }
    fun checkCallback(function: ((checking: Boolean) -> dynamic)) = apply { command.checkCallback = function }
    fun editorCallback(function: ((editor: Editor, view: MarkdownView) -> Any)) = apply { command.editorCallback = function }
    fun editorCheckCallback(function: ((checking: Boolean, editor: Editor, view: MarkdownView) -> dynamic)) = apply { command.editorCheckCallback = function }
    fun hotkeys(hotkeys: Array<Hotkey>) = apply { command.hotkeys = hotkeys }
    fun build() : Command {
        return command
    }

    class InternalCommand(override var id: String, override var name: String) : Command
}
