import kotlin.test.Test

class TestClient {
    @Test
    fun testLoad() {
        val manifest = object : PluginManifest {
            override var id: String
                get() = TODO("Not yet implemented")
                set(value) {}
            override var name: String
                get() = TODO("Not yet implemented")
                set(value) {}
            override var author: String
                get() = TODO("Not yet implemented")
                set(value) {}
            override var version: String
                get() = TODO("Not yet implemented")
                set(value) {}
            override var minAppVersion: String
                get() = TODO("Not yet implemented")
                set(value) {}
            override var description: String
                get() = TODO("Not yet implemented")
                set(value) {}
        }
        val plugin = KotlinPlugin(App(), manifest)
        plugin.onload()

    }
}
