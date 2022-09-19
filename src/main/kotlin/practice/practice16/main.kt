package practice.practice16

fun main() {
    val highestNumber = askForHighestNumber()
    val startingNumber = askForStartingNumber()
    var sum = startingNumber

    while (sum <= highestNumber) {
        sum = sum + askForNumberToAdd()
        println("Az eredmény $sum")

    }
    if (sum % 2 == 0) {
        println("Ez a szám páros")
    }
    if (sum % 2 == 1) {
        println("Ez a szám páratlan")
    }
    println("Túl magas számot adtál meg.")

}

fun askForHighestNumber():Int {
   println("Meddig szeretnél számolni?")
    return readLine()!!.toInt()}

fun askForStartingNumber(): Int {
    println("Adj meg egy kezdő értéket!")
    return readLine()!!.toInt()
}

fun askForNumberToAdd(): Int {
    println("Adj hozzá egy számot!")
    return readLine()!!.toInt()

}
