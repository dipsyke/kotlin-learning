package practice

fun main() {
    val userName = askForName()

    // print a customized greeting for the user
    println("Szia $userName!")
    val yearOfBirth: Int = askForYearOfBirth()

    val age = 2022 - yearOfBirth
    println("$age éves vagy")

    doTheCalculator()

    println("Hol akarod kiszámolni a függvények értékeit?")
    val z = readLine()!!.toInt()
    printElement(z)
}

fun askForName (): String {
    println("Hogy hívnak?")
    val inputt = readLine()!!
    return inputt
}

fun askForYearOfBirth (): Int {
    println("Mikor születtél?")
    return readLine()!!.toInt()

}

fun doTheCalculator() {
    println("add meg az a-t")
    val a = readLine()!!.toInt()
    println("Add meg a b-t")
    val b = readLine()!!.toInt()
    val sum = a + b
    println("a + b = $sum")
}