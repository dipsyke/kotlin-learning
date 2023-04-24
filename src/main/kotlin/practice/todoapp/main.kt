package practice.todoapp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.File

private val SAVE_FILE = File("C:\\tmp\\todo\\save.txt")
private val objectMapper = ObjectMapper()

fun main() {
    val fileText = readTextFromFile()
    var toDoList = deserializeToDoList(fileText)
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
        saveToDoListAsFile(serializeToDoList(toDoList))
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

fun serializeTask(task: Task): ObjectNode {
    val taskJson = ObjectNode(JsonNodeFactory.instance)
    taskJson.put("task", task.taskName)
    taskJson.put("isDone", task.isDone)
    taskJson.put("importanceLevel", task.importance.level)
    return taskJson
}

fun serializeToDoList(toDoList: ArrayList<Task>): String {
    val toDoListJson = ArrayNode(JsonNodeFactory.instance)
    for (task in toDoList) {
        toDoListJson.add(serializeTask(task))
    }
    return toDoListJson.toString()
}

fun deserializeTask(taskJson: ObjectNode): Task {
    return Task(
        taskName = taskJson.get("task").asText(),
        isDone = taskJson.get("isDone").asBoolean(),
        importance = TaskImportance.fromLevel(taskJson.get("importanceLevel").asInt())
    )
}

fun deserializeToDoList(string: String): ArrayList<Task> {
    val taskListJson = objectMapper.readTree(string) as ArrayNode
    val taskList = ArrayList<Task>()

    for (taskJson in taskListJson) {
        taskList.add(deserializeTask(taskJson as ObjectNode))
    }
    return taskList
}

fun saveToDoListAsFile(string: String) {
    SAVE_FILE.printWriter().use {
        it.println(string)
    }
}


fun readTextFromFile(): String {
    return SAVE_FILE.readText()
}