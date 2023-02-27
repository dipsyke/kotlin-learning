package example

fun main() {
    executeClause {
        println("IN clause")
    }

    executeClause {
        println("IN 2nd clause")
    }


    val list: ArrayList<Int> = ArrayList()
    list.add(1)
    list.add(2)
    list.add(3)

    println("list: $list")

    val newList = list.mockMap {
        val newValue = it*2
        println("IN map, mapping $it to $newValue")
        return@mockMap newValue
    }

    println("newList: $newList")
}

private fun executeClause(clause: () -> Unit) {
    println("BEGIN executeClause")
    clause.invoke()
    println("END executeClause")
}

private fun <T, R> List<T>.mockMap(clause: (item: T) -> R): List<R> {
    val newList = ArrayList<R>()
    for (oldItem in this) {
        newList.add(clause.invoke(oldItem))
    }
    return newList
}