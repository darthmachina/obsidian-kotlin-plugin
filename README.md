# obsidian-kotlin-plugin
Base Obsidian plugin in Kotlin

## Usage
Right now the plugin only outputs "KotlinPlugin onload()" and "KotlinPlugin onunload()" to the console if it is loaded and unloaded successfully, respectively. 

## Obsidian API Bindings
The Kotlin bindings for Obsidian were created by using [Dukat](https://github.com/Kotlin/dukat) on the `obsidian.d.ts` file and copying over enough to get the main `Plugin` and `App` classes to compile with much of the functionality in place. There were errors in the CodeMirror and DOM files so none of the CodeMirror items are available in the API for now, and only enough of the DOM items were copied over to fix any compilation errors in the API itself.

CodeMirror had two errors, only one of which was something not immediately fixable. Once the plugin itself is expanded a bit more I plan on revisiting the CodeMirror bindings and pulling those in as well.

I was hoping that Dukat could be fully integrated into the build but there were a LOT of errors in the DOM bindings, so at least for now the bindings are a manual process.

Thanks to the answer on [Stack Overflow](https://stackoverflow.com/questions/68293035/how-to-convert-javascript-exported-class-to-kotlin-js) for getting me through the last issues I was having.
