package practice.practice19

fun main() {
    var n = 200

    for (y in 1..n) {
        if (y % 5 == 0 && y % 3 == 0) {
            println("fizzbuzz")
        } else if (y % 3 == 0) {
            println("fizz")
        } else if (y % 5 == 0) {
            println("buzz")
        } else {
            println(y)
        }
    }
    return
    otherVersionOfFizzbuzz(n)
    println("csiki")
}

fun otherVersionOfFizzbuzz(n: Int) {
    for (y in 1..n) {
        var didAnyConditionRun: Boolean = false
        if (y % 3 == 0) {
            print("fizz")
            didAnyConditionRun = true
        }
        if (y % 5 == 0) {
            print("buzz")
            didAnyConditionRun = true
        }

        if (!didAnyConditionRun) {
            print(y)
        }
        println()
    }
}
