# Contributing
I am definitely willing to accept help in implementing missing features of the API. Also, if anyone has suggestions for improvements I would love to hear them (better patterns, structure, etc).

## Development
Developing this is just like developing an Obsidian Plugin. The Gradle task `browserWebpack` is the main task to create a build suitable for use in Obsidian, and is the that is used most often.

I do have the default KotlinJS task `browserTest` disabled as that starts a web browser for testing, which is not useful for Obsidian Plugins. This does seem to fully disable running any tests, which is something that I plan on looking into in the short term.

### My Workflow
1. `./gradlew browserWebpack` will create the distribution files for use in Obsidian
2. symlink the `build/distributions` to the Obsidian plugins folder.
    - e.g. `ln -s ~/projects/obsidian-kotlin-plugin/build/distrubitions /vault_location/.obsidian/plugins/kotlin-plugin`
3. Safe Mode needs to be off in Obsidian
4. Refresh `Installed plugins` under `Community plugins`
5. Enable `Kotlin Sample Plugin`

When creating a new build, disable and re-enable the plugin under `Installed plugins` to pull in any changes.
