package practice.library.content

import practice.library.ContentCategory
import practice.library.content.base.Content
import practice.library.content.base.Listenable

open class Audio(override val title: String, private val contentCategory: ContentCategory) : Listenable, Content {
    override val contentType: String = "audio"

    override fun listenToContent() {
        println("You are listening to an $contentType called $title")
    }

    override fun playContent() {
        listenToContent()
    }

    override fun getCategory(): ContentCategory {
        return contentCategory
    }
}