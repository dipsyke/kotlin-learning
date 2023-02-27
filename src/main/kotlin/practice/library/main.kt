package practice.library

import practice.library.content.Audio
import practice.library.content.base.Content
import practice.library.content.Text
import practice.library.content.Video
import practice.library.content.base.Listenable
import practice.library.content.base.Playable
import practice.library.content.base.Watchable
import practice.library.content.base.Readable

fun main() {
    val libraryContents = ArrayList<Content>()
    libraryContents.add(Audio(title = "Fuckoff", ContentCategory.ROMANCE))
    libraryContents.add(Text(title = "A polip kilencedik karja", ContentCategory.HENTAI))
    libraryContents.add(Video(title = "Szerelmünk lapjai", ContentCategory.ROMANCE))
    libraryContents.add(Audio(title = "Flowers", ContentCategory.SCIFI))
    libraryContents.add(Text(title = "A könyvkötő", ContentCategory.FANTASY))
    libraryContents.add(Video(title = "Star Wars", ContentCategory.SCIFI))

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
    for (i in 0 until libraryContents.size) {
        if (chosenCategory == libraryContents[i].getCategory()) {
            println("$i: ${libraryContents[i].title} (${libraryContents[i].contentType})")
        }
    }
    print("Please choose which content you want: ")
    val chosenContentIndex = readLine()!!.toInt()
    val selectedContent = libraryContents[chosenContentIndex]
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
    }
}