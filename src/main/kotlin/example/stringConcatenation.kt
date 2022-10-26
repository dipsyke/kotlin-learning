package example

private fun main() {
    println("basic string")

    var variable = 5

    // concatenated string by plus sign
    println("basic string " + variable)

    // concatenated string by template
    println("basic string $variable")

    println("concatenated variable: " + variable)

    println("concatenated variable by template: $variable")

    println("concatenated variable by template: " + variable + ", and stuff")
    println("concatenated variable by template: $variable, and stuff")
}
