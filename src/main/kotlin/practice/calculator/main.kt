package practice.calculator

fun main() {
    var startNumber = askForNumber("Add meg a kezdő számot")
    while (true) {
        print("Milyen műveletet szeretnél végezni (+-): ")
        val calculation = readLine()!!
        val secondNumber = askForNumber("Add meg a második számot")
        startNumber = calculate(startNumber, secondNumber, calculation)
    }

}

fun askForNumber(title: String): Int {
    print("$title: ")
    return readLine()!!.toInt()
}

fun calculate(startNumber: Int, secondNumber: Int, calculation: String): Int {
    var result: Int = startNumber
    if (calculation == "+") {
        result = startNumber + secondNumber

    } else if (calculation == "-") {
        result = startNumber - secondNumber
    }
    println("Az eredmény: $result")
    return result
}
