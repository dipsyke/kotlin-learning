package practice.dogShelter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.File

private val SAVE_FILE = File("C:\\tmp\\shelter\\save.txt")
private val objectmapper = ObjectMapper()

fun main() {
    val listOfDogs = ArrayList<Dog>()
    val shelter = Shelter("Bitch Sanctuary", listOfDogs)

    val golden = Dog("Bubu", 3, true)
    val husky = Dog("Kyle", 5, false)
    val bobtail = Dog("Bob", 10, true)
    val shiba = Dog("Travis", 7, false)
    val pitbull = Dog("Peter", 1, true)

    listOfDogs.add(golden)
    listOfDogs.add(husky)
    listOfDogs.add(bobtail)
    listOfDogs.add(shiba)
    listOfDogs.add(pitbull)

    println("This is the ${shelter.name} and our dogs are: ")
    for (it in listOfDogs) {
        println(it.name)
    }

    writeTextIntoFile(serializeShelter(shelter))

    val fileText = readFileAsText()
    val deserializedShelter = deserializeShelter(fileText)

    println("The cats from the file are: ${deserializedShelter.listOfDogs[0].name}, ${deserializedShelter.listOfDogs[1].name}, ${deserializedShelter.listOfDogs[2].name}, ${deserializedShelter.listOfDogs[3].name}, ${deserializedShelter.listOfDogs[4].name} }")

}

private fun serializeShelter(shelter: Shelter): String {
    val shelterJson = ObjectNode(JsonNodeFactory.instance)
    shelterJson.put("name", shelter.name)
    val dogListJson = ArrayNode(JsonNodeFactory.instance)
    shelterJson.replace("dogs", dogListJson)

    for (dog in shelter.listOfDogs) {
        dogListJson.add(serializeDog(dog))
    }
    return shelterJson.toString()
}

private fun serializeDog(dog: Dog): ObjectNode {
    val dogJson = ObjectNode(JsonNodeFactory.instance)
    dogJson.put("dogName", dog.name)
    dogJson.put("age", dog.age)
    dogJson.put("goodness", dog.isGoodBoy)
    return dogJson
}

private fun writeTextIntoFile(textToSave: String) {
    println("Saving text into file: $textToSave")
    SAVE_FILE.printWriter().use {
        it.println(textToSave)
    }
}

private fun readFileAsText(): String {
    return SAVE_FILE.readText()
}

private fun deserializeShelter(string: String): Shelter {
    val shelterJson = objectmapper.readTree(string) as ObjectNode
    val dogListJson = shelterJson.get("dogs") as ArrayNode
    val dogList = ArrayList<Dog>()

    for (dogJson in dogListJson) {
        dogList.add(deserializeDog(dogJson as ObjectNode))
    }
    return Shelter(
        name = shelterJson.get("name").asText(),
        listOfDogs = dogList,
    )
}

private fun deserializeDog(dogJson: ObjectNode): Dog {
    return Dog(
        name = dogJson.get("dogName").asText(),
        age = dogJson.get("age").asInt(),
        isGoodBoy = dogJson.get("goodness").asBoolean()
    )
}