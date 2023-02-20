package practice.fajlkezeles2

import java.io.File

fun main() {
    val listToWrite = ArrayList<String>()
    listToWrite.add("first")
    listToWrite.add("second")
    listToWrite.add("third")


//     File("C:\\tmp\\fajlkezeles\\asd2.txt").writeText(content)
    File("C:\\tmp\\fajlkezeles\\asd2.txt").printWriter().use {
        for (i in listToWrite) {
            it.println(i)
        }
    }
    //     File("C:\\tmp\\fajlkezeles\\asd2.txt").appendText("\nasdsad")
    File("C:\\tmp\\fajlkezeles\\asd2.txt").forEachLine { println(it) }
}


fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()