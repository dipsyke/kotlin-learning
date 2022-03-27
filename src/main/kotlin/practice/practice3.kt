package practice

fun main() {
    printElement(0)
    printElement(1)
    printElement(2)
    printElement(3)
    printElement(4)
    printElement(5)
}

fun printElement(x: Int) {
    val calculatedY1 = calculateYFor2X(x)
    val calculatedY2 = calculateYForXSquare(x)
    val calculatedY3 = calculateYForMinus3X(x)
    val calculatedY4 = calculateYForConstant2()
    println("where x = $x, y1 = $calculatedY1, y2 = $calculatedY2, y3 = $calculatedY3, y4 = $calculatedY4")
}

fun calculateYFor2X(x: Int): Int {
    val y = 2 * x
    val z = 3 * x
    return y
}

fun calculateYForXSquare(x: Int): Int {
    val y = x * x
    return y
}


fun calculateYForMinus3X(x: Int): Int {
    val y = -3 * x
    return y
}

fun calculateYForConstant2 ():Int{
    val y = 2
    return y
}