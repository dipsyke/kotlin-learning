package example

fun main() {
    val list: ArrayList<Int> = ArrayList()
    list.add(1)
    list.add(2)
    list.add(3)

    println("list: $list")

    val newList = list.map {
        val newValue = it*2
        println("IN map, mapping $it to $newValue")
        return@map newValue
    }

    println("newList: $newList")
}