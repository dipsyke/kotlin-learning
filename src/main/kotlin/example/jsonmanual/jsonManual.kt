package example.jsonmanual

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

}

private fun serializeListToJson(list: List<Csiki>): String {
    var serializedJson = "["

    var preFix = ""
    list.forEach {
        serializedJson += preFix
        serializedJson += it.toJson()
        preFix = ","
    }

    serializedJson += "]"

    return serializedJson
}


private data class Csiki(
    val strength: Int,
    val target: String,
    val isGood: Boolean,
) {
    fun toJson(): String {
        return """
            {
                "strength: $strength,
                "target": "$target",
                "isGood": $isGood
            }
        """.trimIndent()
    }
}