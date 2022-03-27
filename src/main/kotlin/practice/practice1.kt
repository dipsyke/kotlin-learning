package practice

fun main() {
    var numberOfPigs =1
    println("szia")
    printTitle("béka")
    printNumberOfPigs(numberOfPigs)
    printTitle("rőőf")
    println("tinky")
    printStuff("tinky")
}

/**
 * ================
 * === béka
 * ================
 */
fun printTitle(title: String) {
    println("===============================")
    printStuff("===== $title")
    println("===============================")
}

fun printStuff(param1: String) {
    println(param1)
}

fun printNumberOfPigs(something: Int){
    println("number of pigs: $something ")
}