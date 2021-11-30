# obsidian-kotlin-plugin
Base Obsidian plugin in Kotlin

## Usage
Right now the plugin only outputs "KotlinPlugin onload()" and "KotlinPlugin onunload()" to the console if it is loaded and unloaded successfully, respectively. 

**NOTE:** I plan on making this repo a Template, but leaving that off for now as the bindings will be improved in the short-term. I will try to make breaking changes minimal so people can pull changes into their forked repository without too much trouble.

To use this for developing a plugin:
1. Fork the repo
2. Rename `KotlinPlugin` to something more descriptive
3. Rename `KotlinPluginTest` to match the plugin name
4. Rename `KotlinPluginSettings` to match the plugin name
5. Rename `KotlinPluginSettingsTab` to match the plugin name
6. Edit `src/main/resources/manifest.json` to match your plugin information
7. Edit `build.gradle.kts` to change the group name
8. Edit `settings.gradle.kts` to match the rootProject name

In this example plugin, everything that needs to change is in the root package, while everything under the `obsidian` package is for the API and should not need to change when developing a plugin (unless implementing something that is currently missing). 

### API
`ObsidianApi` is the main API file, containing many of the classes from the Obsidian TypeScript definition file. `ES5` contains a few of the DOM-related API needed for the current implementation of the Obsidian API. 

The `obsidian.builders` package contains helper classes for building up the Obsidian API data. This is mainly to help with building data that the API tags as `interface`, since those cannot be instantiated directly and Kotlin isn't as free as Javascript for parameters. Right now the only builder is `CommandBuilder` for constructing a `Command` object, but any further builders needed will follow the same pattern.

## Obsidian API Bindings
The Kotlin bindings for Obsidian were created by using [Dukat](https://github.com/Kotlin/dukat) on the `obsidian.d.ts` file and copying over enough to get the main `Plugin` and `App` classes to compile with much of the functionality in place. There were errors in the CodeMirror and DOM files so none of the CodeMirror items are available in the API for now, and only enough of the DOM items were copied over to fix any compilation errors in the API itself.

CodeMirror had two errors, only one of which was something not immediately fixable. Once the plugin itself is expanded a bit more I plan on revisiting the CodeMirror bindings and pulling those in as well.

I was hoping that Dukat could be fully integrated into the build but there were a LOT of errors in the DOM bindings, so at least for now the bindings are a manual process.

Thanks to the answer on [Stack Overflow](https://stackoverflow.com/questions/68293035/how-to-convert-javascript-exported-class-to-kotlin-js) for getting me through the last issues I was having.

## Contributing
Please see the [contributing doc](CONTRIBUTING.md) for more information on helping out if you would like to.

## Onegoing Development
There is a Project setup in GitHub to track issues and what is being worked on. That is available at the [Development Board](https://github.com/darthmachina/obsidian-kotlin-plugin/projects/1)
