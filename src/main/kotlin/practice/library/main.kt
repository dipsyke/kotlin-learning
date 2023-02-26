package practice.library

import practice.library.content.Audio
import practice.library.content.base.Content
import practice.library.content.Text
import practice.library.content.Video

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
    for (item in libraryContents) {
        if (chosenCategory == item.getCategory()) {
            println("${item.title} (${item.contentType})")
        }
    }
}