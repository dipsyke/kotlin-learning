package practice.library.content

import practice.library.ContentCategory
import practice.library.content.base.Content
import practice.library.content.base.Listenable
import practice.library.content.base.Watchable

class Video(override val title: String, private val contentCategory: ContentCategory) : Watchable, Listenable, Content {
    override val contentType: String = "video"

    override fun getCategory(): ContentCategory {
        return contentCategory
    }

    override fun watchContent() {
        println("You are watching a $contentType called $title")
    }

    override fun listenToContent() {
        println("You are listening to a $contentType called $title")
    }

    override fun playContent() {
        watchContent()
    }
}