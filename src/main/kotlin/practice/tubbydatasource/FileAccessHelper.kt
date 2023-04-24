package practice.tubbydatasource

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.File

object FileAccessHelper {
    private val objectMapper = ObjectMapper()

    fun persist(storageFile: File, storageMap: Map<Class<*>, Table<*>>) {
        val rootJson = ObjectNode(JsonNodeFactory.instance)

        val tablesJson = rootJson.putArray("tables")

        for ((clazz, table) in storageMap) {
            val tableJson = ObjectNode(JsonNodeFactory.instance)

            tableJson.put("autoIncrementValue", table.autoIncrementer.currentValue)
            tableJson.put("javaClassCanonicalName", clazz.canonicalName)
            val recordsJson = tableJson.putArray("records")

            for (record in table.records) {
                recordsJson.add(objectMapper.valueToTree(record) as JsonNode)
            }

            tablesJson.add(tableJson)
        }

        storageFile.createNewFile()
        storageFile.printWriter().use {
            it.println(rootJson.toString())
        }
    }

    fun load(storageFile: File): HashMap<Class<*>, Table<*>> {
        if (!storageFile.exists()) {
            println("WARNING: storageFile does not exist. defaulting to an empty dataset")
            return HashMap()
        }

        val rootJson = objectMapper.readTree(storageFile.readText())

        val tablesJson = rootJson.get("tables") as ArrayNode

        val storageMap = HashMap<Class<*>, Table<*>>()
        for (tableJson in tablesJson) {
            val autoIncrementValue = tableJson.get("autoIncrementValue").asInt()
            val javaClassCanonicalName = tableJson.get("javaClassCanonicalName").asText()
            val clazz = Class.forName(javaClassCanonicalName)

            val table = Table(
                autoIncrementer = AutoIncrementer(autoIncrementValue),
                records = ArrayList()
            )
            storageMap[clazz] = table

            val recordsJson = tableJson.get("records")
            for (recordJson in recordsJson) {
                val record = objectMapper.treeToValue(recordJson, clazz)
                table.records.add(record as Entity)
            }
        }

        return storageMap
    }
}