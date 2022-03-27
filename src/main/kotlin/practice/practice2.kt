package practice

fun main() {
    println("hello")
    printCapital("hello")
    var numberOfApples = 13
    println("$numberOfApples almánk van")
    numberOfApples = numberOfApples + 5
    printCapital("$numberOfApples almánk van")
    println(talkLikeDipsy("a kurva anyád"))


    val amitDipsyMondott = talkLikeDipsy("a kurva anyádból van $numberOfApples")
    println(amitDipsyMondott)
    printCapital(amitDipsyMondott)
}

private fun talkLikeDipsy(text: String): String {
    return "$text bazd meg"
}


private fun printCapital(toPrint: String) {
    println(toPrint.uppercase())
}