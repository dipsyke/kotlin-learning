package example

fun main() {
    val logikaiValami: Boolean = 1 == 1

    if (logikaiValami /*condition */) { //fejléc
        // then törzs
        println("Ez a feltétel igaz!")
    } else {
        // else törzs
        println("Ez a feltétel hamis :'(")
    }


    if (1 == 2) {
        println("Ez a feltétel igaz!")
    } else if (1 == 3) {

    } else {
        // else törzs
        println("Ez a feltétel hamis :'(")
    }

    if (1 == 2) {
        println("Ez a feltétel igaz!")
    } else {
        if (1 == 3) {

        } else {
            // else törzs
            println("Ez a feltétel hamis :'(")
        }
    }

    if (1 == 1 && 2 == 2) { // ÉS
        println("teljesült")
    }

    if (1 == 1 && 1 == 2) { // ÉS
        println("teljesült ez is")
    } else {
        println("ez már nem telesült")
    }

    if (1 == 1 || 1 == 2) { // VAGY
        println("teljesült yeah")
    } else {
        println("ez már nem telesült noo")
    }
}