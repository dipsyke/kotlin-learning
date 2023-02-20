package practice.fajlkezeles1

import java.io.File

fun main() {
    val originalContent = readFileAsLines("C:\\tmp\\fajlkezeles\\asd.txt")
    val newContent = originalContent[0].toInt() + 1


    File("C:\\tmp\\fajlkezeles\\asd.txt").printWriter().use {
        it.println(newContent)
    }
    File("C:\\tmp\\fajlkezeles\\asd.txt").forEachLine { println(it) }
}


fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()