package practice

fun main() {
    val favouriteColour = askForFavouriteColour().lowercase()
    val favouriteTeletubby = askForFavouriteTeletubby().lowercase()

    /*
        lila && tinky-winky -> Áh igen, tinky-winky az lila
        zöld && dipsy -> Áh igen, Dipsy az zöld
        sárga && lala -> Áh igen, Lala az sárga
        piros && po -> Áh igen, Po az piros
        bármi más -> Hát ez egy furcsa kombináció
     */

    if (favouriteColour == "lila" && favouriteTeletubby == "tinky-winky") {
        println("Áh igen, Tinky-winky az lila")
    } else if (favouriteColour == "zöld" && favouriteTeletubby == "dipsy") {
        println("Áh igen, Dipsy az zöld")
    } else if (favouriteColour == "sárga" && favouriteTeletubby == "lala") {
        println("Áh igen, Lala az sárga")
    } else if (favouriteColour == "piros" && favouriteTeletubby == "po") {
        println("Áh igen, Po az piros")
    } else {
        println("Hát ez egy furcsa kombináció")
    }
}

fun askForFavouriteColour(): String {
    println("Mi a kedvenc színed?")
    return readLine()!!
}

fun askForFavouriteTeletubby(): String {
    println("Mi a kedvenc Teletubbyd?")
    val teletubby = readLine()!!
    val lowercasedTeletubby = teletubby.lowercase()
    if (lowercasedTeletubby == "tinky-winky" || lowercasedTeletubby == "dipsy"
        || lowercasedTeletubby == "lala" || lowercasedTeletubby == "po"
    ) {
        return teletubby
    }

    throw Exception("Ez nem is egy igazi teletubby")
}