package practice.todoapp

fun main() {
    var toDoList = ArrayList<Task>()
    printToDoList(toDoList)
    while (true) {
        print("Add task \"a\";  Change status \"c\";  Remove task \"r\": ")
        val answer = readLine()!!
        if (answer == "a") {

            toDoList.add(addNewTask())
            toDoList = ArrayList(toDoList.sortedWith(compareBy { it.importance.level }))

        }
        if (answer == "c") {
            print("Which task's status would you like to change?: ")
            val taskStatusToChange = readLine()!!.toInt()
            toDoList[taskStatusToChange].isDone = !toDoList[taskStatusToChange].isDone
        }
        if (answer == "r") {
            print("Which task do you wish to remove from your to do list?: ")
            val taskToRemove = readLine()!!.toInt()
            toDoList.removeAt(taskToRemove)
        }
        printToDoList(toDoList)
    }

}

fun addNewTask(): Task {
    println("Type in your task and press enter to add the task to your to do list")
    val taskName = readLine()!!
    println("How important is this task (important \"i\", less important \"l\", not important \"n\": )")

    val importanceEnum: TaskImportance = when (readLine()!!) {
        "i" -> TaskImportance.IMPORTANT
        "l" -> TaskImportance.LESS_IMPORTANT
        "n" -> TaskImportance.NOT_IMPORTANT
        else -> TaskImportance.NOT_IMPORTANT
    }
    return Task(taskName, false, importanceEnum)
}

fun printToDoList(toDoList: ArrayList<Task>) {
    println("----TO DO LIST----")
    for (i in 0 until toDoList.size) {
        print(i)
        print("  ${toDoList[i].taskName} (${toDoList[i].importance.displayName})")
        if (toDoList[i].isDone) {
            println("  DONE")
        } else {
            println("  TO DO")
        }
    }
    println("------------------")
}