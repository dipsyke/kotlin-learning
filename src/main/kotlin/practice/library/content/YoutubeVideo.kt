package practice.library.content

import practice.library.ContentCategory
import practice.library.content.base.Shareable

class YoutubeVideo(title: String, contentCategory: ContentCategory, val link: String) : Video(title, contentCategory),
    Shareable {
    override val contentType: String = "youtube video"

    init {
        println("A YoutubeVideo osztály példánya épp készül, a title értéke: $title")
    }

    override fun shareContent() {
        println("You shared $title, this is the link: $link")
    }
}


//class Csiki(param: String, val prop: String) {
//    var prop2: String = param
//
//    init {
//        println("A Csiki osztály példánya épp készül")
//        println("A Csiki osztály példánya épp készül, a param értéke: $param")
//    }
//}
//
//fun asd() {
//    YoutubeVideo(title = "", contentCategory = ContentCategory.SCIFI)
//
//    val csiki = Csiki(param = "xcy", prop = "qwe")
//    csiki.prop
//    csiki.prop2
//
//}