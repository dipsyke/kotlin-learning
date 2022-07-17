package practice

fun main() {
    println("bármi")

    var numberOfChickeys = 2
    println("eddig $numberOfChickeys chickeyt kaptam")
    numberOfChickeys = numberOfChickeys + 1
    println("eddig $numberOfChickeys chickeyt kaptam")

    println("hány chickeyt kell még kapnom?")
    val remainingChickeys = readLine()!!.toInt()

    for(i in 1..remainingChickeys){
        println("$i. Chickeyzés")
    }


}