package practice.practice19

fun main() {
    val list = askForString().split(" ", ",", ":", ".")
    //println("${list.elementAt(3)}")
    //println("${list.elementAt(2).length}")
    var maxLength = 0
    var longestWord = ""
    for (y in list) {
        //println("${y.length}")
        if (y.length > maxLength) {
            maxLength = y.length
            longestWord = y
        }
    }
    println("$longestWord")
}

fun askForString(): String {
    println("Give me some input")
    return readLine()!!
}