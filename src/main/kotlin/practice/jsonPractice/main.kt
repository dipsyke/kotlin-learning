package practice.jsonPractice

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.File

private val SAVE_FILE = File("C:\\tmp\\jsonpractice\\save.txt")
private val objectMapper = ObjectMapper()

fun main() {
    val cat1 = Cat(
        name = "Garfield",
        color = "red",
        age = 8,
        isCute = false
    )
    println("The first cat is ${cat1.name}, color: ${cat1.color}, age: ${cat1.age}, cute: ${cat1.isCute}")



 val serializedCat = serializeCat(cat1)
    saveTextAsFile(serializedCat)


    val fileText = readFileAsText()
    val deserializedCat = deserializeCat(fileText)
    println("The cat from the file is: ${deserializedCat.name}, age: ${deserializedCat.age}, color: ${deserializedCat.color}, cuteness: ${deserializedCat.isCute}")
}


private fun saveTextAsFile(textToSave: String) {
    println("saving text into file $textToSave")
    SAVE_FILE.printWriter().use {
        it.println(textToSave)
    }
}

private fun readFileAsText(): String {
    return SAVE_FILE.readText()
}

private fun serializeCat(cat: Cat): String {
    val catJson = ObjectNode(JsonNodeFactory.instance)
    catJson.put("name", cat.name)
    catJson.put("age", cat.age)
    catJson.put("color", cat.color)
    catJson.put("cuteness", cat.isCute)
    return catJson.toString()
}

private fun deserializeCat(string: String): Cat {
    val json = objectMapper.readTree(string)
    return Cat(
        name = json.get("name").asText(),
        age = json.get("age").asInt(),
        color = json.get("color").asText(),
        isCute = json.get("cuteness").asBoolean()
    )
}

