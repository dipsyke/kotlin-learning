package example.jsonfilesave

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.File

val SAVE_FILE = File("C:\\tmp\\jsonfilesave\\save.txt")
val objectMapper = ObjectMapper()

class Person(
    val name: String,
    val mailingAddress: MailingAddress,
)

class MailingAddress(
    val country: String,
    val city: String,
)

fun main() {
    val originalPerson = Person(
        name = "CukorDipszy",
        mailingAddress = MailingAddress(
            country = "Dipszikó",
            city = "Dipszity"
        )
    )
    println("originalPerson: name: ${originalPerson.name}, country: ${originalPerson.mailingAddress.country}, city: ${originalPerson.mailingAddress.city}")


    val serializedPerson = serializePerson(originalPerson)

    saveTextIntoFile(serializedPerson)

    val textFromFile: String = readTextFromFile()

    val parsedPerson: Person = deserializePerson(textFromFile)

    println("parsedPerson: name: ${parsedPerson.name}, country: ${parsedPerson.mailingAddress.country}, city: ${parsedPerson.mailingAddress.city}")
}

private fun serializePerson(person: Person): String {
    val personJson = ObjectNode(JsonNodeFactory.instance)

    personJson.put("name", person.name)

    val mailingAddressJson = ObjectNode(JsonNodeFactory.instance)

    mailingAddressJson.put("country", person.mailingAddress.country)
    mailingAddressJson.put("city", person.mailingAddress.city)

    personJson.replace("mailingAddress", mailingAddressJson)

    return personJson.toString()
}

private fun deserializePerson(string: String): Person {
    val json = objectMapper.readTree(string)

    val mailingAddress = MailingAddress(
        country = json.get("mailingAddress").get("country").asText(),
        city = json.get("mailingAddress").get("city").asText(),
    )

    val person = Person(
        name = json.get("name").asText(),
        mailingAddress = mailingAddress
    )

    return person
}

private fun saveTextIntoFile(textToSave: String) {
    println("Saving text into file: $textToSave")
    SAVE_FILE.printWriter().use {
        it.println(textToSave)
    }
}

private fun readTextFromFile(): String {
    return SAVE_FILE.readText()
}
