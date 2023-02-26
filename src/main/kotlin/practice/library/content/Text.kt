package practice.library.content

import practice.library.ContentCategory
import practice.library.content.base.Content
import practice.library.content.base.Readable

class Text(override val title: String, private val contentCategory: ContentCategory): Readable, Content {
    override val contentType: String = "text"

    override fun readContent() {
        println("You are reading a $contentType called $title")
    }

    override fun getCategory(): ContentCategory {
        return contentCategory
    }
}