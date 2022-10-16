package practice.practice19

fun main() {
//    println(readLine())

    val name = askForName()
    val ability = askForAbility()
    println("$name is $ability")

}

fun askForName(): String {
    println("Hey, what's your name?")
    return readLine()!!
}

fun askForAbility(): String {
    println("What are you like?")
    return readLine()!!
}