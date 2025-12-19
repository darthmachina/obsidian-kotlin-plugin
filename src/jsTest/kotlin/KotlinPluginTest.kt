import kotlin.test.Test

class TestClient {
    @Test
    fun testLoad() {
        val manifest = object : PluginManifest {
            override var id: String
                get() = ""
                set(value) {}
            override var name: String
                get() = ""
                set(value) {}
            override var author: String
                get() = ""
                set(value) {}
            override var version: String
                get() = ""
                set(value) {}
            override var minAppVersion: String
                get() = ""
                set(value) {}
            override var description: String
                get() = ""
                set(value) {}
        }
        val plugin = KotlinPlugin(App(), manifest)
        plugin.onload()

    }
}
