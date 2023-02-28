package practice.library.content.base

import practice.library.ContentCategory

interface Content {
    /**
     * returns content category
     */
    fun getCategory(): ContentCategory
    val title: String
    val contentType: String
}