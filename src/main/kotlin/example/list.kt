package example

fun main() {
    val list: ArrayList<Int> = ArrayList()
    printAllElementsOfList(list)
    list.add(5)
    list.add(7)
    list.add(13)
    list.add(6)
    list.add(80)
    list.add(4)
    printAllElementsOfList(list)
    list.removeAt(3)
    printAllElementsOfList(list)
    list[3] = 69
    printAllElementsOfList(list)

    println("============== iterálások =================")
    println("Sima iterálás:")
    for (item in list) {
        print("$item,")
    }
    println()

    println("Indexelt iterálás:")
    for (i in 0 until list.size) {
        print("${list[i]},")
    }
    println()

    println("A lista harmadik eleme: ${list[2]}")
    println("=========================================")

    for (i in 1..10) {
        list.add(i)
    }
    printAllElementsOfList(list)

    for (i in 1..20) {
        if (i % 2 == 0) {
            list.add(i)
        }
    }
    printAllElementsOfList(list)

    for (i in 1..10) {
        list.add(i * 2)
    }
    printAllElementsOfList(list)

}

private fun printAllElementsOfList(list: List<Int>) {
    print("size: ${list.size}; items: ")
    for (item in list) {
        print("$item,")
    }
    println()
}
