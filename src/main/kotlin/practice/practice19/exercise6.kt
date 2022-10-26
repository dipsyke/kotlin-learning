package practice.practice19

fun main() {
    //input = "Why are You up SO late?"

    val changedString = changeCase("Why are You up SO late?", "lower")
    println(changedString)
    println(changeCase("Why are You up SO late?", "upper"))

}

fun changeCase(input: String, case: String): String {
    when (case) {
        "upper" -> return input.uppercase()
        "lower" -> return input.lowercase()
        else -> return ""
    }
}

fun changeCase2(input: String, case: String): String {
    return when (case) {
        "upper" -> input.uppercase()
        "lower" -> input.lowercase()
        else -> ""
    }
}