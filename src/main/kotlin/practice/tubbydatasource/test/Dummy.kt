package practice.tubbydatasource.test

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.NullNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.util.StdConverter
import practice.tubbydatasource.Entity
import practice.tubbydatasource.TubbyDataSource
import java.io.File

@JsonSerialize(converter = DummyJacksonSerializer::class)
@JsonDeserialize(converter = DummyJacksonDeserializer::class)
data class Dummy(
    override var id: Int? = null,
    val asdf: Int
) : Entity {
}

class DummyJacksonSerializer : StdConverter<Dummy, JsonNode>() {
    override fun convert(value: Dummy?): JsonNode {
        if (value == null) {
            return NullNode.instance
        }

        return ObjectNode(JsonNodeFactory.instance).apply {
            put("id", value.id)
            put("asdf", value.asdf)
        }
    }
}

class DummyJacksonDeserializer : StdConverter<JsonNode, Dummy>() {
    override fun convert(json: JsonNode): Dummy? {
        if (json.isNull) {
            return null
        }

        return Dummy(
            id = json.get("id").asInt(),
            asdf = json.get("asdf").asInt(),
        )
    }
}

fun main() {
    val objectMapper = ObjectMapper()

    val dummy = Dummy(null, 123)
    dummy.id = 17

    println(objectMapper.writeValueAsString(dummy))

    println(objectMapper.readValue("""{"id":10, "asdf":20}""", Dummy::class.java))

    val TEST_FILE_PATH = "C:\\tmp\\tubbydatasource\\test.json"
    File(TEST_FILE_PATH).delete()

    val dataSource1 = TubbyDataSource(File(TEST_FILE_PATH))

    assertNull(dataSource1.getById(Dummy::class.java, 1))
    val originalDummy = Dummy(id = null, asdf = 69)
    assertNull(originalDummy.id)
    dataSource1.save(originalDummy)
    assertEquals(1, originalDummy.id)

    val readDummy1 = dataSource1.getById(Dummy::class.java, 1)
    assertNotNull(readDummy1)
    readDummy1!!
    assertEquals(originalDummy.id, readDummy1.id)
    assertEquals(originalDummy.asdf, readDummy1.asdf)

    val dataSource2 = TubbyDataSource(File(TEST_FILE_PATH))

    val readDummy2 = dataSource2.getById(Dummy::class.java, 1)
    assertNotNull(readDummy2)
    readDummy2!!
    assertEquals(originalDummy.id, readDummy2.id)
    assertEquals(originalDummy.asdf, readDummy2.asdf)
}

fun assertEquals(expected: Any?, actual: Any?, message: String = "expected not equals actual") {
    assertTrue(expected == actual, message)
}

fun assertNotNull(value: Any?, message: String = "value is null") {
    assertTrue(value != null, message)
}

fun assertNull(value: Any?, message: String = "value is not null") {
    assertTrue(value == null, message)
}

fun assertTrue(value: Boolean, message: String = "value is not true") {
    if (!value) {
        throw RuntimeException(message)
    }
}