package practice.practice20

fun main() {
    val list: ArrayList<String> = ArrayList()
    list.add("fuck off")
    printAllElements(list)
    list.add("a")
    list.add("b")
    printAllElements(list)
    println("A harmadik elem ${list[2]}")
    list.removeAt(1)
    printAllElements(list)
    for (i in 1..10){
        list.add((i * 3).toString())
    }
    printAllElements(list)
    list[4] = "gépház"
    printAllElements(list)
}

fun printAllElements(csiki: ArrayList<String>) {
    for (item in csiki) {
        println(item)
    }
}