package dialogs

import App
import Modal

class KotlinDialog(override var app: App) : Modal(app) {
    override fun onOpen() {
        contentEl.innerText = "Woah!"
    }

    override fun onClose() {
        while(contentEl.firstChild != null) {
            contentEl.lastChild?.let { contentEl.removeChild(it) }
        }
    }
}