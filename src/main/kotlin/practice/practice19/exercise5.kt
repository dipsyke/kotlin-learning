package practice.practice19

fun main() {
    val originalString = "asd asd asd"
    val oldValue = "a"
    val newValue = "b"

    var newString = ""

    for (char in originalString) {
        if (char == oldValue[0]) {
            newString += newValue
        } else {
            newString += char
        }
        println(newString)
    }

    println("the final newString: $newString")

}