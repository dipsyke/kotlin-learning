package practice.jsonPractice2

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode

fun main() {
    val objectMapper = ObjectMapper()
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT)

//    val objectNode = ObjectNode(JsonNodeFactory.instance)
//    objectNode.put("krumpli", "pli")
//    objectNode.put("hagyma", "ma")
//    println(objectNode.toString())
//    println(objectMapper.writeValueAsString(objectNode))


    val tagsJson = ArrayNode(JsonNodeFactory.instance)
    tagsJson.add("teletubby")
    tagsJson.add("cuki")
    tagsJson.add("csikis")

    val addressJson = ObjectNode(JsonNodeFactory.instance)
    addressJson.put("country", "Dipszykó")
    addressJson.put("city", "Dipszity")
    addressJson.put("street", "Dipsztreet")

    val favouriteFoodsJson = ArrayNode(JsonNodeFactory.instance)
    val cakeJson = ObjectNode(JsonNodeFactory.instance)

    cakeJson.put("type", "cake")
    cakeJson.put("color", "purple")

    val ingredientsCakeJson = ArrayNode(JsonNodeFactory.instance)
    ingredientsCakeJson.add("csikipüré")
    ingredientsCakeJson.add("málnalekvár")
    ingredientsCakeJson.add("dipszósz")

    cakeJson.replace("ingredients", ingredientsCakeJson)

    favouriteFoodsJson.add(cakeJson)

    val pancakeJson = ObjectNode(JsonNodeFactory.instance)
    pancakeJson.put("type", "pancake")
    pancakeJson.put("color", "yellow")

    val ingredientsPancakeJson = ArrayNode(JsonNodeFactory.instance)
    ingredientsPancakeJson.add("liszt")
    ingredientsPancakeJson.add("tojás")
    ingredientsPancakeJson.add("tejszínhab")

    pancakeJson.replace("pancake", ingredientsPancakeJson)

    favouriteFoodsJson.add(pancakeJson)


    val rootJson = ObjectNode(JsonNodeFactory.instance)
    rootJson.put("name", "Dipsz")
    rootJson.replace("tags", tagsJson)
    rootJson.replace("address", addressJson)
    rootJson.replace("favourite foods", favouriteFoodsJson)


    println(objectMapper.writeValueAsString(rootJson))

}