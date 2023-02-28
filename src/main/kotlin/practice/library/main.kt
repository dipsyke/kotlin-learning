package practice.library

import practice.library.content.Audio
import practice.library.content.Text
import practice.library.content.Video
import practice.library.content.YoutubeVideo
import practice.library.content.base.*

fun main() {
    val libraryContents = ArrayList<Content>()
    libraryContents.add(Audio(title = "Fuckoff", ContentCategory.ROMANCE))
    libraryContents.add(Text(title = "A polip kilencedik karja", ContentCategory.HENTAI))
    libraryContents.add(Video(title = "Szerelmünk lapjai", ContentCategory.ROMANCE))
    libraryContents.add(Audio(title = "Flowers", ContentCategory.SCIFI))
    libraryContents.add(Text(title = "A könyvkötő", ContentCategory.FANTASY))
    libraryContents.add(Video(title = "Star Wars", ContentCategory.SCIFI))
    libraryContents.add(YoutubeVideo(title = "TomPeti - Megcsikiztél", ContentCategory.SCIFI, link = "https://www.youtube.com/watch?v=g8H0PdFzRFY"))

    print(
        "Hello and welcome to the Dipszykó State Library! Please choose which category are you looking for today: ${
            ContentCategory.values().map { it.displayName }
        }: "
    )
    var chosenCategory: ContentCategory? = null
    while (true) {
        val inputChosenCategory = readLine()!!
        chosenCategory = ContentCategory.fromDisplayName(inputChosenCategory)
        if (chosenCategory == null) {
            println(
                "It's not a valid category, please choose another one: ${
                    ContentCategory.values().map { it.displayName }
                }: "
            )

        } else {
            break
        }
    }

    val contentsInChosenCategory = ArrayList<Content>()
    for (i in 0 until libraryContents.size) {
        if (chosenCategory == libraryContents[i].getCategory()) {
            contentsInChosenCategory.add(libraryContents[i])
        }
    }
    for (i in 0 until contentsInChosenCategory.size) {
        println("$i: ${contentsInChosenCategory[i].title} (${contentsInChosenCategory[i].contentType})")
    }

    print("Please choose which content you want: ")
    val chosenContentIndex = readLine()!!.toInt()
    val selectedContent = contentsInChosenCategory[chosenContentIndex]

    println("What do you want to do with ${selectedContent.title}?")


    if (selectedContent is Listenable) {
        println("l: listen")
    }
    if (selectedContent is Watchable) {
        println("w: watch")
    }
    if (selectedContent is Readable) {
        println("r: read")
    }

    if (selectedContent is Playable) {
        println("p: play")
    }
    if (selectedContent is Shareable) {
        println("s: share")
    }


    val chosenAction = readLine()!!

    when (chosenAction) {
        "l" -> {
            (selectedContent as Listenable).listenToContent()
        }
        "w" -> {
            (selectedContent as Watchable).watchContent()
        }

        "r" -> {
            (selectedContent as Readable).readContent()
        }

        "p" -> {
            (selectedContent as Playable).playContent()
        }
        "s" -> {
            (selectedContent as Shareable).shareContent()
        }
    }
}