package practice

fun main() {
    talkLikeSziporka("Puszedli, hogy kell életben tartani egy virágot?")
    talkLikePuszedli("Sziporka, honnan a faszból tudnám, az enyémek is mindig meghalnak")

    talkLikeSziporka(convertIntoUppercase("Ne kiabálj velem :("))
    talkLikeSziporka(talkNicely("Legyél velem kedves"))
    talkLikeSziporka("hüpp hüpp hüpp")
    talkLikeCsuporka("Mi történik itt?")
    println(echo("TI KURVÁK"))


    staySilentAwkwardly()
    talkLikePuszedli(convertIntoUppercase("Csak azt akartam neki elmondani, hogy..."))
    talkLikeSziporka(convertIntoUppercase("hüpp hüpp hüpp"))
    println(echo("HÜPP"))
    talkLikeSziporka(echo("hüpike"))

    talkLikeCsuporka("death stare")

    talkLikePuszedli(convertIntoLowercase(convertIntoUppercase("tehát csak")))

    talkLikeCsuporka(proposeIdea("go and fuck yourself"))
    talkLikeCsuporka(include("te, Sziproka", "te, Puszedli"))
    talkLikeCsuporka("és ${include("te, Sziproka", "te, Puszedli")}")
    talkLikeCsuporka("és " +  include("te, Sziproka", "te, Puszedli"))
    talkLikeCsuporka(proposeIdea(convertIntoUppercase("go and fuck yourself")))
    talkLikeCsuporka(convertIntoUppercase(proposeIdea("go and fuck yourself")))


}


fun talkLikeSziporka(text: String) {
    println("Sziporka: $text")

}


fun talkLikePuszedli(text: String) {
    println("Puszedli: $text, te balfasz.")
}

fun talkLikeCsuporka(text: String) {
    println("Csuporka: $text")
}

fun convertIntoUppercase(toconvert: String): String {
    return toconvert.uppercase()
}

fun convertIntoLowercase(toconvert: String): String {
    return toconvert.lowercase()

}

fun staySilentAwkwardly() {
    println("......................")
    println("...Awkward silence....")
    println(".......................")
}

/**
 * ALMA -> ALMA alma ALMA
 * alma -> alma alma ALMA
 * ALma -> ALma alma ALMA
 */
fun echo(toEcho: String): String {
    return "$toEcho ${toEcho.lowercase()} ${toEcho.uppercase()}"

}

/**
 * alma -> alma, kedvesem
 */
fun talkNicely(phrase: String): String {
    return "$phrase, kedvesem"
}

/**
 * alma, körte -> nemcsak alma, hanem körte is
 */
fun include(one: String, other:String): String{
    return "nemcsak $one, hanem $other is"
}












/**
 * alma -> Van egy ötletem: alma. Mit gondolsz?
 */
fun proposeIdea(idea: String): String{
    return "Van egy ötletem: $idea. Mit gondolsz?"
}

