private fun main() {
    var gondolásokSzáma = 14


    printHeadline("Üres tubbies")

    talkLikeDipsy("hello tinky winky")
    talkLikeTinkyWinky("hello dipsy")


    printHeadline("A nagy kérdés")

    talkLikeDipsy("hányszor gondoltál ma rám?")
    convinceDipsyke(gondolásokSzáma)
    talkLikeDipsy("a faszt")

    gondolásokSzáma = 45
    convinceDipsyke(gondolásokSzáma)
    talkLikeDipsy("na persze persze")

    gondolásokSzáma = gondolásokSzáma + 5
    convinceDipsyke(gondolásokSzáma)
    talkLikeDipsy("jó, elhiszem")

}

private fun convinceDipsyke(numberOfThoughts: Int) {
    talkLikeTinkyWinky("Dipsyke, $numberOfThoughts alkalommal gondoltam rád.")
    talkLikeTinkyWinky("Bizony ám, $numberOfThoughts alkalommal gondoltam rád!")
    talkLikeTinkyWinky("Komolyan $numberOfThoughts alkalommal gondoltam rád!")
}

private fun talkLikeDipsy(sentence: String) {
    println("Dipsy: $sentence")
}

private fun talkLikeTinkyWinky(sentence: String) {
    println("Tinky: $sentence")
}

fun printHeadline(headline: String) {
    println()
    println()
    println("=================================================")
    println("==== $headline")
    println("=================================================")
    println()
    println()
}