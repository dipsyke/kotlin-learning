package practice.library

enum class ContentCategory(val displayName: String) {
    ROMANCE("Romance"),
    FANTASY("Fantasy"),
    SCIFI("Sci-Fi"),
    DRAMA("Drama"),
    HENTAI("Hentai"),
    ;

    companion object {
        fun fromDisplayName(name: String): ContentCategory? {
            return values().firstOrNull {
                it.displayName == name
            }
        }
    }
}

//val readCategory: ContentCategory? = ContentCategory.fromDisplayName(readLine()!!)
//if(readCategory == null){
//    println("Nincs ilyen kattegória")
//}