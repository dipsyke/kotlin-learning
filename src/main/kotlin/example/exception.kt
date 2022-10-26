package example

fun main() {
    println("Exception dobás előtt")
    throw Exception("Ez itt a hiba message-e")
    println("Exception dobás után")
}