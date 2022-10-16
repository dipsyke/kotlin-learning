package practice.practice19

fun main() {
val number = askForNumber()
    val result = number * number
    println("Result: $result")

}

fun askForNumber(): Int {
    println("Enter a number, and I'll square it!")
  val number = readLine()!!.toInt()
    return number


}