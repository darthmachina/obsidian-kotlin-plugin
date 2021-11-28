@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("default")
class KotlinPlugin(app: App, manifest: PluginManifest) : Plugin(app, manifest) {
    override fun onload() {
        console.log("KotlinPlugin onload()")
    }

    override fun onunload() {
        console.log("KotlinPlugin onunload()")
    }
}
