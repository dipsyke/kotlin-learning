package practice.library.content

import practice.library.ContentCategory
import practice.library.content.base.Downloadable
import practice.library.content.base.Shareable

class OnlineArticle (title: String, contentCategory: ContentCategory, val link: String ): Text(title, contentCategory), Downloadable {
    override val contentType: String = "online article"
    override fun printContent() {
        println("You are printing $title, this is the link: $link")
    }

    override fun downloadContent() {
        println("You are downloading $title, this is the link: $link")
    }

    override fun shareContent() {
        println("You shared $title, this is the link: $link")
    }
}