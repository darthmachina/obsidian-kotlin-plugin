@OptIn(ExperimentalJsExport::class)
@JsExport
data class KotlinPluginSettings(
    var mySetting: String
) {
    companion object {
        fun default() : KotlinPluginSettings{
            return KotlinPluginSettings("default")
        }

        fun toJson(settings: KotlinPluginSettings) : String {
            return JSON.stringify(settings)
        }

        fun fromJson(json: String) : KotlinPluginSettings {
            return JSON.parse(json)
        }
    }
}