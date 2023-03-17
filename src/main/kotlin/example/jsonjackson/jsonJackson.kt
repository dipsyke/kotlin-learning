package example.jsonjackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode

fun main() {

    val csiki1 = Csiki(
        strength = 10,
        target = "hónalj",
        isGood = true,
    )

    val csiki2 = Csiki(
        strength = 20,
        target = "másik hónalj",
        isGood = false,
    )

    println("csiki1:")
    println(csiki1.toJson())
    println("csiki2:")
    println(csiki2.toJson())

    val csikiList = listOf(
        csiki1,
        csiki2
    )

    println("csiki list:")
    println(serializeListToJson(csikiList))


    val testJson = """
        {
            "key1": "val1",
            "key2": [
                       1,
                       2,
                       3,
                       4
            ]  
        }
    """.trimIndent()

    val objectMapper = ObjectMapper()

    val processedJsonNode = objectMapper.readTree(testJson)
    processedJsonNode as ObjectNode
    println("\$.key1: " + processedJsonNode.get("key1").asText())
    println("\$.key2[1]: " + processedJsonNode.get("key2").get(1).asInt())

}

private fun serializeListToJson(list: List<Csiki>): ArrayNode {
    val json = ArrayNode(JsonNodeFactory.instance)

    list.forEach {
        json.add(it.toJson())
    }

    return json
}


private data class Csiki(
    val strength: Int,
    val target: String,
    val isGood: Boolean,
) {
    fun toJson(): ObjectNode {
        val json = ObjectNode(JsonNodeFactory.instance)

        json.put("strength", strength)
        json.put("target", target)
        json.put("isGood", isGood)

        return json
    }
}