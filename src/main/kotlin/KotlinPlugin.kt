@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("default")
class KotlinPlugin(app: App, manifest: PluginManifest) : Plugin(app, manifest) {
    var settings : KotlinPluginSettings = KotlinPluginSettings.default()

    override fun onload() {
        loadSettings()
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
