package practice.practice15

fun main() {
    println("Hali")
    var answer = ""
    var lowest = 0
    var highest = 1000

    println("Gondolj egy számrra $lowest-től ${highest-1}-ig!")
    while (answer != "q"  && answer != "e") {
        val guess = (lowest + highest)/2
        println("erre gondoltál $guess? (k = kisebbre gondoltam,n = nagyobbra gondoltam,e = egyenlő,q = qilépés)")
        answer = readLine()!!
        if (answer == "n"){
            lowest = guess
        }
        if (answer == "k"){
            highest = guess
        }

    }
    println("Tschüssi")
}