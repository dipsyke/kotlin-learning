package practice.labyrinth

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.File

object SavingUtils {
    val saveFile = File("C:\\tmp\\labirintus\\save.txt")
    val objectMapper = ObjectMapper()

    fun save(saveDto: SaveDto) {
        val fileContentToWrite: String = saveDto.toJson().toString()

        saveFile.printWriter().use {
            it.println(fileContentToWrite)
        }
    }

    fun load(): SaveDto {
        val fileContent: String = saveFile.readText()
        val parsedJsonFileContent: JsonNode = objectMapper.readTree(fileContent)
        return SaveDto.fromJson(parsedJsonFileContent as ObjectNode)
    }

}