package example

fun main() {
    println("Hali")
    var answer = readLine()!!

    while (answer == "k" || answer == "n") {
        println("iteráció")
        answer = readLine()!!

    }
    println("Tschüssi")
}