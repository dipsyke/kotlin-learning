package practice.labyrinth

import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode

class SaveDto(
    val currentMapIndex: Int,
) {

    /**
     * serialize
     */
    fun toJson(): ObjectNode {
        val json = ObjectNode(JsonNodeFactory.instance)

        json.put("currentMapIndex", currentMapIndex)

        return json
    }

    companion object {
        /**
         * deserialize
         */
        fun fromJson(json: ObjectNode): SaveDto {
            return SaveDto(
                currentMapIndex = json.get("currentMapIndex").asInt()
            )
        }
    }
}