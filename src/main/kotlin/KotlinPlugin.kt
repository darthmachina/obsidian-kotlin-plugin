import builders.CommandBuilder
import dialogs.KotlinDialog

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("default")
class KotlinPlugin(app: App, manifest: PluginManifest) : Plugin(app, manifest) {
    var settings : KotlinPluginSettings = KotlinPluginSettings.default()

    override fun onload() {
        loadSettings()

        // This adds a simple command that can be triggered anywhere
        addCommand(CommandBuilder(
            "open-kotlin-modal-simple",
            "Open Kotlin modal (simple)")
            .callback {
                KotlinDialog(app).open()
            }
            .build())

        // This adds an editor command that can perform some operation on the current editor instance
        addCommand(CommandBuilder(
            "kotlin-editor-command",
            "Kotlin editor command")
            .editorCallback { editor, _ ->
                console.log("Editor selection", editor.getSelection())
                editor.replaceSelection("Sample Editor Command")
            }
            .build())

        // This adds a complex command that can check whether the current state of the app allows execution of the command
        // TODO Figure out how to use getActiveViewOfType

        // Add Settings tab
        addSettingTab(KotlinPluginSettingsTab(app, this))
        console.log("KotlinPlugin onload()")
    }

    override fun onunload() {
        console.log("KotlinPlugin onunload()")
    }

    private fun loadSettings() {
        // TODO: implement exmaple of versioned settings
        loadData().then {result ->
            val loadedSettings = KotlinPluginSettings.fromJson(result as String)
            console.log("loadedSettings: ", loadedSettings)
            // TODO Replace with a version check
            if (loadedSettings.mySetting != "") {
                console.log("Saving loaded settings")
                settings = loadedSettings
            }
        }
    }
}
