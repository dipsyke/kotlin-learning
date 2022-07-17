fun main() {

    for (i in 1..5) {
        println("A")
    }

    for (i in 1..5) {
        println("B i=$i")
    }

    for (i in 1..5) {
        println("C-I i=$i")
        println("C-II i=$i")
    }

    for (character in "asdf") {
        println("D character=$character")
        println(character)
    }

    for (i in 4..6) {
        println("E i=$i")
    }

    var bottom = 5
    var top = 7
    for (i in bottom..top) {
        println("F i=$i")
    }
}