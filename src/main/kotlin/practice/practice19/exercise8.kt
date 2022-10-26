package practice.practice19

fun main() {
    var n = 4

    for (y in 1..n){
       // println("start of external for")
        for (x in 1..y){
            print("#")
        }
        println()
    }
}