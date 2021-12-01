@file:Suppress("unused", "INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsModule("obsidian")
@file:JsNonModule

import obsidian.ClipboardEvent
import kotlin.js.Promise
import obsidian.Record
import org.khronos.webgl.ArrayBuffer
import org.w3c.dom.*
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent

open external class Component {
    open fun load()
    open fun onload()
    open fun unload()
    open fun onunload()
    open fun <T : Component> addChild(component: T): T
    open fun <T : Component> removeChild(component: T): T
    open fun register(cb: () -> Any)
    open fun registerEvent(eventRef: EventRef)
    open fun registerDomEvent(el: Window, type: String, callback: (self: HTMLElement, ev: Any) -> Any)
    open fun registerDomEvent(el: Document, type: String, callback: (self: HTMLElement, ev: Any) -> Any)
    open fun <K : Nothing?> registerDomEvent(el: HTMLElement, type: K, callback: (self: HTMLElement, ev: Any) -> Any)
    open fun registerScopeEvent(keyHandler: KeymapEventHandler)
    open fun registerInterval(id: Number)
}

open external class Plugin(app: App, manifest: PluginManifest) : Component {
    open var app: App
    open var manifest: PluginManifest
    open fun addRibbonIcon(icon: String, title: String, callback: (evt: MouseEvent) -> Any): HTMLElement
    open fun addStatusBarItem(): HTMLElement
    open fun addCommand(command: Command): Command
    open fun addSettingTab(settingTab: PluginSettingTab)
//    open fun registerView(type: String, viewCreator: ViewCreator)
    open fun registerExtensions(extensions: Array<String>, viewType: String)
    open fun registerMarkdownPostProcessor(postProcessor: MarkdownPostProcessor): MarkdownPostProcessor
    open fun registerMarkdownCodeBlockProcessor(language: String, handler: (source: String, el: HTMLElement, ctx: MarkdownPostProcessorContext) -> Any): MarkdownPostProcessor
//    open fun registerCodeMirror(callback: (cm: CodeMirror.Editor) -> Any)
//    open fun registerObsidianProtocolHandler(action: String, handler: ObsidianProtocolHandler)
    open fun registerEditorSuggest(editorSuggest: EditorSuggest<Any>)
    open fun loadData(): Promise<Any>
    open fun saveData(data: Any): Promise<Unit>
}

open external class PluginSettingTab(app: App, plugin: Plugin) : SettingTab

open external class SettingTab {
    open var app: App
    open var containerEl: HTMLElement
    open fun display(): Any
    open fun hide(): Any
}

open external class App {
    open var workspace: Workspace
    open var vault: Vault
    open var metadataCache: MetadataCache
    open var fileManager: FileManager
    open var lastEvent: dynamic /* MouseEvent? | KeyboardEvent? | TouchEvent? | PointerEvent? */
}

open external class Workspace : Events {
    open var leftSplit: dynamic /* WorkspaceSidedock | WorkspaceMobileDrawer */
    open var rightSplit: dynamic /* WorkspaceSidedock | WorkspaceMobileDrawer */
    open var leftRibbon: WorkspaceRibbon
    open var rightRibbon: WorkspaceRibbon
    open var rootSplit: WorkspaceSplit
    open var activeLeaf: WorkspaceLeaf?
    open var containerEl: HTMLElement
    open var layoutReady: Boolean
    open var requestSaveLayout: () -> Unit
    open var requestSaveHistory: () -> Unit
    open fun onLayoutReady(callback: () -> Any)
    open fun changeLayout(workspace: Any): Promise<Unit>
    open fun getLayout(): Any
    open fun createLeafInParent(parent: WorkspaceSplit, index: Number): WorkspaceLeaf
    open fun splitLeaf(source: WorkspaceItem, newLeaf: WorkspaceItem, direction: String /* "vertical" | "horizontal" */ = definedExternally, before: Boolean = definedExternally)
    open fun createLeafBySplit(leaf: WorkspaceLeaf, direction: String /* "vertical" | "horizontal" */ = definedExternally, before: Boolean = definedExternally): WorkspaceLeaf
    open fun splitActiveLeaf(direction: String /* "vertical" | "horizontal" */ = definedExternally): WorkspaceLeaf
    open fun splitLeafOrActive(leaf: WorkspaceLeaf = definedExternally, direction: String /* "vertical" | "horizontal" */ = definedExternally): WorkspaceLeaf
    open fun duplicateLeaf(leaf: WorkspaceLeaf, direction: String /* "vertical" | "horizontal" */ = definedExternally): Promise<Unit>
    open fun getUnpinnedLeaf(type: String = definedExternally): WorkspaceLeaf
    open fun getLeaf(newLeaf: Boolean = definedExternally): WorkspaceLeaf
    open fun openLinkText(linktext: String, sourcePath: String, newLeaf: Boolean = definedExternally, openViewState: OpenViewState = definedExternally): Promise<Unit>
    open fun setActiveLeaf(leaf: WorkspaceLeaf, pushHistory: Boolean = definedExternally, focus: Boolean = definedExternally)
    open fun getLeafById(id: String): WorkspaceLeaf
    open fun getGroupLeaves(group: String): Array<WorkspaceLeaf>
    open fun getMostRecentLeaf(): WorkspaceLeaf
    open fun getLeftLeaf(split: Boolean): WorkspaceLeaf
    open fun getRightLeaf(split: Boolean): WorkspaceLeaf
    open fun <T : View> getActiveViewOfType(type: Constructor<T>): T?
    open fun getActiveFile(): TFile?
    open fun iterateRootLeaves(callback: (leaf: WorkspaceLeaf) -> Any)
    open fun iterateAllLeaves(callback: (leaf: WorkspaceLeaf) -> Any)
    open fun getLeavesOfType(viewType: String): Array<WorkspaceLeaf>
    open fun detachLeavesOfType(viewType: String)
    open fun revealLeaf(leaf: WorkspaceLeaf)
    open fun getLastOpenFiles(): Array<String>
    //    open fun iterateCodeMirrors(callback: (cm: CodeMirror.Editor) -> Any)
    open fun on(name: String /* "quick-preview" */, callback: (file: TFile, data: String) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "quick-preview" */, callback: (file: TFile, data: String) -> Any): EventRef
    open fun on(name: String /* "resize" | "click" | "layout-change" | "css-change" */, callback: () -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "resize" | "click" | "layout-change" | "css-change" */, callback: () -> Any): EventRef
    open fun on(name: String /* "active-leaf-change" */, callback: (leaf: WorkspaceLeaf?) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "active-leaf-change" */, callback: (leaf: WorkspaceLeaf?) -> Any): EventRef
    open fun on(name: String /* "file-open" */, callback: (file: TFile?) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "file-open" */, callback: (file: TFile?) -> Any): EventRef
    open fun on(name: String /* "file-menu" */, callback: (menu: Menu, file: TAbstractFile, source: String, leaf: WorkspaceLeaf) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "file-menu" */, callback: (menu: Menu, file: TAbstractFile, source: String, leaf: WorkspaceLeaf) -> Any): EventRef
    open fun on(name: String /* "editor-menu" */, callback: (menu: Menu, editor: Editor, view: MarkdownView) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "editor-menu" */, callback: (menu: Menu, editor: Editor, view: MarkdownView) -> Any): EventRef
    open fun on(name: String /* "editor-change" */, callback: (editor: Editor, markdownView: MarkdownView) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "editor-change" */, callback: (editor: Editor, markdownView: MarkdownView) -> Any): EventRef
    open fun on(name: String /* "editor-paste" */, callback: (evt: ClipboardEvent, editor: Editor, markdownView: MarkdownView) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "editor-paste" */, callback: (evt: ClipboardEvent, editor: Editor, markdownView: MarkdownView) -> Any): EventRef
    open fun on(name: String /* "editor-drop" */, callback: (evt: DragEvent, editor: Editor, markdownView: MarkdownView) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "editor-drop" */, callback: (evt: DragEvent, editor: Editor, markdownView: MarkdownView) -> Any): EventRef
//    open fun on(name: String /* "codemirror" */, callback: (cm: CodeMirror.Editor) -> Any, ctx: Any = definedExternally): EventRef
//    open fun on(name: String /* "codemirror" */, callback: (cm: CodeMirror.Editor) -> Any): EventRef
    open fun on(name: String /* "quit" */, callback: (tasks: Tasks) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "quit" */, callback: (tasks: Tasks) -> Any): EventRef
}

open external class Vault : Events {
    open var adapter: DataAdapter
    open var configDir: String
    open fun getName(): String
    open fun getAbstractFileByPath(path: String): TAbstractFile?
    open fun getRoot(): TFolder
    open fun create(path: String, data: String, options: DataWriteOptions = definedExternally): Promise<TFile>
    open fun createBinary(path: String, data: ArrayBuffer, options: DataWriteOptions = definedExternally): Promise<TFile>
    open fun createFolder(path: String): Promise<Unit>
    open fun read(file: TFile): Promise<String>
    open fun cachedRead(file: TFile): Promise<String>
    open fun readBinary(file: TFile): Promise<ArrayBuffer>
    open fun getResourcePath(file: TFile): String
    open fun delete(file: TAbstractFile, force: Boolean = definedExternally): Promise<Unit>
    open fun trash(file: TAbstractFile, system: Boolean): Promise<Unit>
    open fun rename(file: TAbstractFile, newPath: String): Promise<Unit>
    open fun modify(file: TFile, data: String, options: DataWriteOptions = definedExternally): Promise<Unit>
    open fun modifyBinary(file: TFile, data: ArrayBuffer, options: DataWriteOptions = definedExternally): Promise<Unit>
    open fun copy(file: TFile, newPath: String): Promise<TFile>
    open fun getAllLoadedFiles(): Array<TAbstractFile>
    open fun getMarkdownFiles(): Array<TFile>
    open fun getFiles(): Array<TFile>
    open fun on(name: String /* "create" | "modify" | "delete" */, callback: (file: TAbstractFile) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "create" | "modify" | "delete" */, callback: (file: TAbstractFile) -> Any): EventRef
    open fun on(name: String /* "rename" */, callback: (file: TAbstractFile, oldPath: String) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "rename" */, callback: (file: TAbstractFile, oldPath: String) -> Any): EventRef
    open fun on(name: String /* "closed" */, callback: () -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "closed" */, callback: () -> Any): EventRef

    companion object {
        fun recurseChildren(root: TFolder, cb: (file: TAbstractFile) -> Any)
    }
}

external interface Command {
    var id: String
    var name: String
    var icon: String?
        get() = definedExternally
        set(value) = definedExternally
    var mobileOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var callback: (() -> Any)?
        get() = definedExternally
        set(value) = definedExternally
    var checkCallback: ((checking: Boolean) -> dynamic)?
        get() = definedExternally
        set(value) = definedExternally
    var editorCallback: ((editor: Editor, view: MarkdownView) -> Any)?
        get() = definedExternally
        set(value) = definedExternally
    var editorCheckCallback: ((checking: Boolean, editor: Editor, view: MarkdownView) -> dynamic)?
        get() = definedExternally
        set(value) = definedExternally
    var hotkeys: Array<Hotkey>?
        get() = definedExternally
        set(value) = definedExternally
}

open external class Setting(containerEl: HTMLElement) {
    open var settingEl: HTMLElement
    open var infoEl: HTMLElement
    open var nameEl: HTMLElement
    open var descEl: HTMLElement
    open var controlEl: HTMLElement
    open var components: Array<BaseComponent>
    open fun setName(name: String): Setting /* this */
    open fun setName(name: DocumentFragment): Setting /* this */
    open fun setDesc(desc: String): Setting /* this */
    open fun setDesc(desc: DocumentFragment): Setting /* this */
    open fun setClass(cls: String): Setting /* this */
    open fun setTooltip(tooltip: String): Setting /* this */
    open fun setHeading(): Setting /* this */
    open fun setDisabled(disabled: Boolean): Setting /* this */
    open fun addButton(cb: (component: ButtonComponent) -> Any): Setting /* this */
    open fun addExtraButton(cb: (component: ExtraButtonComponent) -> Any): Setting /* this */
    open fun addToggle(cb: (component: ToggleComponent) -> Any): Setting /* this */
    open fun addText(cb: (component: TextComponent) -> Any): Setting /* this */
    open fun addSearch(cb: (component: SearchComponent) -> Any): Setting /* this */
    open fun addTextArea(cb: (component: TextAreaComponent) -> Any): Setting /* this */
    open fun addMomentFormat(cb: (component: MomentFormatComponent) -> Any): Setting /* this */
    open fun addDropdown(cb: (component: DropdownComponent) -> Any): Setting /* this */
    open fun addSlider(cb: (component: SliderComponent) -> Any): Setting /* this */
    open fun then(cb: (setting: Setting /* this */) -> Any): Setting /* this */
}

open external class MetadataCache : Events {
    open fun getFirstLinkpathDest(linkpath: String, sourcePath: String): TFile?
    open fun getFileCache(file: TFile): CachedMetadata?
    open fun getCache(path: String): CachedMetadata
    open fun fileToLinktext(file: TFile, sourcePath: String, omitMdExtension: Boolean = definedExternally): String
    open var resolvedLinks: Record<String, Record<String, Number>>
    open var unresolvedLinks: Record<String, Record<String, Number>>
    open fun on(name: String /* "changed" | "resolve" */, callback: (file: TFile) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "changed" | "resolve" */, callback: (file: TFile) -> Any): EventRef
    open fun on(name: String /* "resolved" */, callback: () -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "resolved" */, callback: () -> Any): EventRef
}

open external class FileManager {
    open fun getNewFileParent(sourcePath: String): TFolder
    open fun renameFile(file: TAbstractFile, newPath: String): Promise<Unit>
    open fun generateMarkdownLink(file: TFile, sourcePath: String, subpath: String = definedExternally, alias: String = definedExternally): String
}

external interface DataAdapter {
    fun getName(): String
    fun exists(normalizedPath: String, sensitive: Boolean = definedExternally): Promise<Boolean>
    fun stat(normalizedPath: String): Promise<Stat?>
    fun list(normalizedPath: String): Promise<ListedFiles>
    fun read(normalizedPath: String): Promise<String>
    fun readBinary(normalizedPath: String): Promise<ArrayBuffer>
    fun write(normalizedPath: String, data: String, options: DataWriteOptions = definedExternally): Promise<Unit>
    fun writeBinary(normalizedPath: String, data: ArrayBuffer, options: DataWriteOptions = definedExternally): Promise<Unit>
    fun getResourcePath(normalizedPath: String): String
    fun mkdir(normalizedPath: String): Promise<Unit>
    fun trashSystem(normalizedPath: String): Promise<Boolean>
    fun trashLocal(normalizedPath: String): Promise<Unit>
    fun rmdir(normalizedPath: String, recursive: Boolean): Promise<Unit>
    fun remove(normalizedPath: String): Promise<Unit>
    fun rename(normalizedPath: String, normalizedNewPath: String): Promise<Unit>
    fun copy(normalizedPath: String, normalizedNewPath: String): Promise<Unit>
}

external interface Stat {
    var type: String /* "file" | "folder" */
    var ctime: Number
    var mtime: Number
    var size: Number
}

external interface ListedFiles {
    var files: Array<String>
    var folders: Array<String>
}

open external class TAbstractFile {
    open var vault: Vault
    open var path: String
    open var name: String
    open var parent: TFolder
}

open external class TFolder : TAbstractFile {
    open var children: Array<TAbstractFile>
    open fun isRoot(): Boolean
}

open external class TFile : TAbstractFile {
    open var stat: FileStats
    open var basename: String
    open var extension: String
}

external interface FileStats {
    var ctime: Number
    var mtime: Number
    var size: Number
}

external interface DataWriteOptions {
    var ctime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var mtime: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CachedMetadata {
    var links: Array<LinkCache>?
        get() = definedExternally
        set(value) = definedExternally
    var embeds: Array<EmbedCache>?
        get() = definedExternally
        set(value) = definedExternally
    var tags: Array<TagCache>?
        get() = definedExternally
        set(value) = definedExternally
    var headings: Array<HeadingCache>?
        get() = definedExternally
        set(value) = definedExternally
    var sections: Array<SectionCache>?
        get() = definedExternally
        set(value) = definedExternally
    var listItems: Array<ListItemCache>?
        get() = definedExternally
        set(value) = definedExternally
    var frontmatter: FrontMatterCache?
        get() = definedExternally
        set(value) = definedExternally
    var blocks: Record<String, BlockCache>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface LinkCache : ReferenceCache

external interface EmbedCache : ReferenceCache

external interface TagCache : CacheItem {
    var tag: String
}

external interface HeadingCache : CacheItem {
    var heading: String
    var level: Number
}

external interface SectionCache : CacheItem {
    var id: String?
        get() = definedExternally
        set(value) = definedExternally
    var type: String
}

external interface ListItemCache : CacheItem {
    var id: String?
        get() = definedExternally
        set(value) = definedExternally
    var task: String?
        get() = definedExternally
        set(value) = definedExternally
    var parent: Number
}

external interface FrontMatterCache : CacheItem {
    @Suppress("DEPRECATION")
    @nativeGetter
    operator fun get(key: String): Any?
    @Suppress("DEPRECATION")
    @nativeSetter
    operator fun set(key: String, value: Any)
}

external interface BlockCache : CacheItem {
    var id: String
}

open external class WorkspaceRibbon

open external class WorkspaceItem : Events {
    open fun getRoot(): WorkspaceItem
}

open external class WorkspaceParent : WorkspaceItem

open external class WorkspaceSplit : WorkspaceParent

open external class WorkspaceTabs : WorkspaceParent

open external class WorkspaceLeaf : WorkspaceItem {
    open var view: View
    open fun openFile(file: TFile, openState: OpenViewState = definedExternally): Promise<Unit>
    open fun open(view: View): Promise<View>
    open fun getViewState(): ViewState
    open fun setViewState(viewState: ViewState, eState: Any = definedExternally): Promise<Unit>
    open fun getEphemeralState(): Any
    open fun setEphemeralState(state: Any)
    open fun togglePinned()
    open fun setPinned(pinned: Boolean)
    open fun setGroupMember(other: WorkspaceLeaf)
    open fun setGroup(group: String)
    open fun detach()
    open fun getIcon(): String
    open fun getDisplayText(): String
    open fun onResize()
    open fun on(name: String /* "pinned-change" */, callback: (pinned: Boolean) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "pinned-change" */, callback: (pinned: Boolean) -> Any): EventRef
    open fun on(name: String /* "group-change" */, callback: (group: String) -> Any, ctx: Any = definedExternally): EventRef
    open fun on(name: String /* "group-change" */, callback: (group: String) -> Any): EventRef
}

//typealias ViewCreator = (leaf: WorkspaceLeaf) -> View

open external class View(leaf: WorkspaceLeaf) : Component {
    open var app: App
    open var icon: String
    open var navigation: Boolean
    open var leaf: WorkspaceLeaf
    open var containerEl: HTMLElement
    open fun onOpen(): Promise<Unit>
    open fun onClose(): Promise<Unit>
    open fun getViewType(): String
    open fun getState(): Any
    open fun setState(state: Any, result: ViewStateResult): Promise<Unit>
    open fun getEphemeralState(): Any
    open fun setEphemeralState(state: Any)
    open fun getIcon(): String
    open fun onResize()
    open fun getDisplayText(): String
    open fun onHeaderMenu(menu: Menu)
}

external interface MarkdownPostProcessor {
    @Suppress("DEPRECATION")
    @nativeInvoke
    operator fun invoke(el: HTMLElement, ctx: MarkdownPostProcessorContext): dynamic /* Promise<Any> | Unit */
    var sortOrder: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MarkdownPostProcessorContext {
    var docId: String
    var sourcePath: String
    var frontmatter: Any?
    fun addChild(child: MarkdownRenderChild)
    fun getSectionInfo(el: HTMLElement): MarkdownSectionInformation?
}

external interface MarkdownSectionInformation {
    var text: String
    var lineStart: Number
    var lineEnd: Number
}

open external class MarkdownView(leaf: WorkspaceLeaf) : TextFileView {
    open var editor: Editor
    open var previewMode: MarkdownPreviewView
    open var currentMode: MarkdownSubView
    override fun getViewType(): String
    open fun getMode(): String /* "source" | "preview" | "live" */
    override fun getViewData(): String
    override fun clear()
    override fun setViewData(data: String, clear: Boolean)
    open fun showSearch(replace: Boolean = definedExternally)
}

open external class MarkdownPreviewView(containerEl: HTMLElement) : MarkdownRenderer, MarkdownSubView, MarkdownPreviewEvents {
    override var containerEl: HTMLElement
    override fun get(): String
    override fun set(data: String, clear: Boolean)
    open fun clear()
    open fun rerender(full: Boolean = definedExternally)
    override fun getScroll(): Number
    override fun applyScroll(scroll: Number)
}

external interface MarkdownSubView {
    fun getScroll(): Number
    fun applyScroll(scroll: Number)
    fun get(): String
    fun set(data: String, clear: Boolean)
}

open external class MarkdownRenderer(containerEl: HTMLElement) : MarkdownRenderChild, MarkdownPreviewEvents, HoverParent {
    companion object {
        fun renderMarkdown(markdown: String, el: HTMLElement, sourcePath: String, component: Component): Promise<Unit>
    }
}

open external class MarkdownRenderChild(containerEl: HTMLElement) : Component {
    open var containerEl: HTMLElement
}

external interface MarkdownPreviewEvents : Component

open external class TextFileView(leaf: WorkspaceLeaf) : EditableFileView {
    open var data: String
    open var requestSave: () -> Unit
    override fun onUnloadFile(file: TFile): Promise<Unit>
    override fun onLoadFile(file: TFile): Promise<Unit>
    open fun save(clear: Boolean = definedExternally): Promise<Unit>
    open fun getViewData(): String
    open fun setViewData(data: String, clear: Boolean)
    open fun clear()
}

open external class EditableFileView(leaf: WorkspaceLeaf) : FileView

open external class FileView(leaf: WorkspaceLeaf) : ItemView {
    open var allowNoFile: Boolean
    open var file: TFile
    override fun getDisplayText(): String
    override fun onload()
    override fun getState(): Any
    override fun setState(state: Any, result: ViewStateResult): Promise<Unit>
    open fun onLoadFile(file: TFile): Promise<Unit>
    open fun onUnloadFile(file: TFile): Promise<Unit>
    override fun onMoreOptionsMenu(menu: Menu)
    override fun onHeaderMenu(menu: Menu)
    open fun canAcceptExtension(extension: String): Boolean
}

open external class ItemView(leaf: WorkspaceLeaf) : View {
    open var contentEl: HTMLElement
    open fun onMoreOptionsMenu(menu: Menu)
    open fun addAction(icon: String, title: String, callback: (evt: MouseEvent) -> Any, size: Number = definedExternally): HTMLElement
    override fun onHeaderMenu(menu: Menu)
}

open external class Menu(app: App) : Component {
    open fun setNoIcon(): Menu /* this */
    open fun addItem(cb: (item: MenuItem) -> Any): Menu /* this */
    open fun addSeparator(): Menu /* this */
    open fun showAtMouseEvent(evt: MouseEvent): Menu /* this */
    open fun showAtPosition(position: Point): Menu /* this */
    open fun hide(): Menu /* this */
    open fun onHide(callback: () -> Any)
}

open external class MenuItem(menu: Menu) {
    open fun setTitle(title: String): MenuItem /* this */
    open fun setTitle(title: DocumentFragment): MenuItem /* this */
    open fun setIcon(icon: String?, size: Number = definedExternally): MenuItem /* this */
    open fun setActive(active: Boolean): MenuItem /* this */
    open fun setDisabled(disabled: Boolean): MenuItem /* this */
    open fun setIsLabel(isLabel: Boolean): MenuItem /* this */
    open fun onClick(callback: (evt: Any /* MouseEvent | KeyboardEvent */) -> Any): MenuItem /* this */
}

open external class Editor {
    open fun getDoc(): Editor /* this */
    open fun refresh()
    open fun getValue(): String
    open fun setValue(content: String)
    open fun getLine(line: Number): String
    open fun setLine(n: Number, text: String)
    open fun lineCount(): Number
    open fun lastLine(): Number
    open fun getSelection(): String
    open fun somethingSelected(): Boolean
    open fun getRange(from: EditorPosition, to: EditorPosition): String
    open fun replaceSelection(replacement: String, origin: String = definedExternally)
    open fun replaceRange(replacement: String, from: EditorPosition, to: EditorPosition = definedExternally, origin: String = definedExternally)
    open fun getCursor(string: String /* "from" | "to" | "head" | "anchor" */ = definedExternally): EditorPosition
    open fun listSelections(): Array<EditorSelection>
    open fun setCursor(pos: EditorPosition, ch: Number = definedExternally)
    open fun setCursor(pos: EditorPosition)
    open fun setCursor(pos: Number, ch: Number = definedExternally)
    open fun setCursor(pos: Number)
    open fun setSelection(anchor: EditorPosition, head: EditorPosition = definedExternally)
    open fun setSelections(ranges: Array<EditorSelectionOrCaret>, main: Number = definedExternally)
    open fun focus()
    open fun blur()
    open fun hasFocus(): Boolean
    open fun getScrollInfo(): `T$1`
    open fun scrollTo(x: Number? = definedExternally, y: Number? = definedExternally)
    open fun scrollIntoView(range: EditorRange, margin: Number = definedExternally)
    open fun undo()
    open fun redo()
    open fun exec(command: String /* "goUp" | "goDown" | "goLeft" | "goRight" | "goStart" | "goEnd" | "indentMore" | "indentLess" | "newlineAndIndent" | "swapLineUp" | "swapLineDown" | "deleteLine" | "toggleFold" | "foldAll" | "unfoldAll" */)
    open fun transaction(tx: EditorTransaction)
    open fun posToOffset(pos: EditorPosition): Number
    open fun offsetToPos(offset: Number): EditorPosition
}

open external class Modal(app: App) : CloseableComponent {
    open var app: App
    open var scope: Scope
    open var containerEl: HTMLElement
    open var modalEl: HTMLElement
    open var titleEl: HTMLElement
    open var contentEl: HTMLElement
    open var shouldRestoreSelection: Boolean
    open fun open()
    override fun close()
    open fun onOpen()
    open fun onClose()
}

external interface `T$1` {
    var top: Number
    var left: Number
}

external interface EditorChange : EditorRangeOrCaret {
    var text: String
}

external interface EditorPosition {
    var line: Number
    var ch: Number
}

external interface EditorRange {
    var from: EditorPosition
    var to: EditorPosition
}

external interface EditorRangeOrCaret {
    var from: EditorPosition
    var to: EditorPosition?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EditorSelection {
    var anchor: EditorPosition
    var head: EditorPosition
}

external interface EditorSelectionOrCaret {
    var anchor: EditorPosition
    var head: EditorPosition?
        get() = definedExternally
        set(value) = definedExternally
}

open external class EditorSuggest<T>(app: App) : PopoverSuggest<T> {
    open var context: EditorSuggestContext?
    open var limit: Number
    open fun onTrigger(cursor: EditorPosition, editor: Editor, file: TFile): EditorSuggestTriggerInfo?
    open fun getSuggestions(context: EditorSuggestContext): dynamic /* Array<T> | Promise<Array<T>> */
}

external interface EditorSuggestContext : EditorSuggestTriggerInfo {
    var editor: Editor
    var file: TFile
}

external interface EditorSuggestTriggerInfo {
    var start: EditorPosition
    var end: EditorPosition
    var query: String
}

external interface EditorTransaction {
    var replaceSelection: String?
        get() = definedExternally
        set(value) = definedExternally
    var changes: Array<EditorChange>?
        get() = definedExternally
        set(value) = definedExternally
    var selections: Array<EditorRangeOrCaret>?
        get() = definedExternally
        set(value) = definedExternally
    var selection: EditorRangeOrCaret?
        get() = definedExternally
        set(value) = definedExternally
}

external interface HoverParent {
    var hoverPopover: HoverPopover?
        get() = definedExternally
        set(value) = definedExternally
}

open external class HoverPopover(parent: HoverParent, targetEl: HTMLElement?, waitTime: Number = definedExternally) : Component {
    open var state: PopoverState
}

external enum class PopoverState

open external class PopoverSuggest<T>(app: App, scope: Scope = definedExternally) : ISuggestOwner<T>, CloseableComponent {
    open fun open()
    override fun close()
    override fun renderSuggestion(value: T, el: HTMLElement)
    override fun selectSuggestion(value: T, evt: MouseEvent)
    override fun selectSuggestion(value: T, evt: KeyboardEvent)
}

external interface ISuggestOwner<T> {
    fun renderSuggestion(value: T, el: HTMLElement)
    fun selectSuggestion(value: T, evt: MouseEvent)
    fun selectSuggestion(value: T, evt: KeyboardEvent)
}

open external class Scope {
    //    open fun register(modifiers: Array<String /* "Mod" | "Ctrl" | "Meta" | "Shift" | "Alt" */>, key: String?, func: KeymapEventListener): KeymapEventHandler
    open fun unregister(handler: KeymapEventHandler)
}

external interface Hotkey {
    var modifiers: Array<String /* "Mod" | "Ctrl" | "Meta" | "Shift" | "Alt" */>
    var key: String
}

external interface KeymapEventHandler : KeymapInfo {
    var scope: Scope
}

//typealias KeymapEventListener = (evt: KeyboardEvent, ctx: KeymapContext) -> dynamic

external interface KeymapInfo {
    var modifiers: String?
    var key: String?
}

external interface ViewStateResult

external interface OpenViewState

external interface ViewState {
    var type: String
    var state: Any?
        get() = definedExternally
        set(value) = definedExternally
    var active: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var pinned: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var group: WorkspaceLeaf?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CacheItem {
    var position: Pos
}

external interface ReferenceCache : CacheItem {
    var link: String
    var original: String
    var displayText: String?
        get() = definedExternally
        set(value) = definedExternally
}

open external class AbstractTextComponent<T>(inputEl: T) : ValueComponent<String> {
    open var inputEl: T
    override fun setDisabled(disabled: Boolean): AbstractTextComponent<T> /* this */
    override fun getValue(): String
    override fun setValue(value: String): AbstractTextComponent<T> /* this */
    open fun setPlaceholder(placeholder: String): AbstractTextComponent<T> /* this */
    open fun onChanged()
    open fun onChange(callback: (value: String) -> Any): AbstractTextComponent<T> /* this */
}

open external class BaseComponent {
    open var disabled: Boolean
    open fun then(cb: (component: BaseComponent /* this */) -> Any): BaseComponent /* this */
    open fun setDisabled(disabled: Boolean): BaseComponent /* this */
}

open external class ButtonComponent(containerEl: HTMLElement) : BaseComponent {
    open var buttonEl: HTMLButtonElement
    override fun setDisabled(disabled: Boolean): ButtonComponent /* this */
    open fun setCta(): ButtonComponent /* this */
    open fun removeCta(): ButtonComponent /* this */
    open fun setWarning(): ButtonComponent /* this */
    open fun setTooltip(tooltip: String): ButtonComponent /* this */
    open fun setButtonText(name: String): ButtonComponent /* this */
    open fun setIcon(icon: String): ButtonComponent /* this */
    open fun setClass(cls: String): ButtonComponent /* this */
    open fun onClick(callback: (evt: MouseEvent) -> Any): ButtonComponent /* this */
}

open external class DropdownComponent(containerEl: HTMLElement) : ValueComponent<String> {
    open var selectEl: HTMLSelectElement
    override fun setDisabled(disabled: Boolean): DropdownComponent /* this */
    open fun addOption(value: String, display: String): DropdownComponent /* this */
    open fun addOptions(options: Record<String, String>): DropdownComponent /* this */
    override fun getValue(): String
    override fun setValue(value: String): DropdownComponent /* this */
    open fun onChange(callback: (value: String) -> Any): DropdownComponent /* this */
}

open external class ExtraButtonComponent(containerEl: HTMLElement) : BaseComponent {
    open var extraSettingsEl: HTMLElement
    override fun setDisabled(disabled: Boolean): ExtraButtonComponent /* this */
    open fun setTooltip(tooltip: String): ExtraButtonComponent /* this */
    open fun setIcon(icon: String): ExtraButtonComponent /* this */
    open fun onClick(callback: () -> Any): ExtraButtonComponent /* this */
}

open external class MomentFormatComponent(containerEl: HTMLElement) : TextComponent {
    open var sampleEl: HTMLElement
    open fun setDefaultFormat(defaultFormat: String): MomentFormatComponent /* this */
    open fun setSampleEl(sampleEl: HTMLElement): MomentFormatComponent /* this */
    override fun setValue(value: String): MomentFormatComponent /* this */
    override fun onChanged()
    open fun updateSample()
}

open external class SearchComponent(containerEl: HTMLElement) : AbstractTextComponent<HTMLInputElement> {
    open var clearButtonEl: HTMLElement
    override fun onChanged()
}

open external class SliderComponent(containerEl: HTMLElement) : ValueComponent<Number> {
    open var sliderEl: HTMLInputElement
    override fun setDisabled(disabled: Boolean): SliderComponent /* this */
    open fun setLimits(min: Number, max: Number, step: Number): SliderComponent /* this */
    open fun setLimits(min: Number, max: Number, step: String /* "any" */): SliderComponent /* this */
    override fun getValue(): Number
    override fun setValue(value: Number): SliderComponent /* this */
    open fun getValuePretty(): String
    open fun setDynamicTooltip(): SliderComponent /* this */
    open fun showTooltip()
    open fun onChange(callback: (value: Number) -> Any): SliderComponent /* this */
}

open external class TextAreaComponent(containerEl: HTMLElement) : AbstractTextComponent<HTMLTextAreaElement>

open external class TextComponent(containerEl: HTMLElement) : AbstractTextComponent<HTMLInputElement>

open external class ToggleComponent(containerEl: HTMLElement) : ValueComponent<Boolean> {
    open var toggleEl: HTMLElement
    override fun setDisabled(disabled: Boolean): ToggleComponent /* this */
    override fun getValue(): Boolean
    override fun setValue(value: Boolean): ToggleComponent /* this */
    open fun setTooltip(tooltip: String): ToggleComponent /* this */
    open fun onClick()
    open fun onChange(callback: (value: Boolean) -> Any): ToggleComponent /* this */
}

open external class ValueComponent<T> : BaseComponent {
    open fun registerOptionListener(listeners: Record<String, (value: T) -> T>, key: String): ValueComponent<T> /* this */
    open fun getValue(): T
    open fun setValue(value: T): ValueComponent<T> /* this */
}

external interface CloseableComponent {
    fun close(): Any
}

external interface Pos {
    var start: Loc
    var end: Loc
}

external interface Loc {
    var line: Number
    var col: Number
    var offset: Number
}

external interface Point {
    var x: Number
    var y: Number
}

external interface PluginManifest {
    var dir: String?
        get() = definedExternally
        set(value) = definedExternally
    var id: String
    var name: String
    var author: String
    var version: String
    var minAppVersion: String
    var description: String
    var authorUrl: String?
        get() = definedExternally
        set(value) = definedExternally
    var isDesktopOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

open external class Events {
    open fun on(name: String, callback: (data: Any) -> Any, ctx: Any = definedExternally): EventRef
    open fun off(name: String, callback: (data: Any) -> Any)
    open fun offref(ref: EventRef)
    open fun trigger(name: String, vararg data: Any)
    open fun tryTrigger(evt: EventRef, args: Array<Any>)
}

//typealias ObsidianProtocolHandler = (params: ObsidianProtocolData) -> Any

external interface EventRef

open external class Tasks {
    open fun add(callback: () -> Promise<Any>)
    open fun addPromise(promise: Promise<Any>)
    open fun isEmpty(): Boolean
    open fun promise(): Promise<Any>
}

external interface Constructor<T>
