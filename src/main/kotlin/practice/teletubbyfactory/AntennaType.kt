package practice.teletubbyfactory

enum class AntennaType(val displayName: String) {
    TRIANGLE("triangle"),
    CIRCLE("circle"),
    DRUMSTICK("drumstick"),
    CURL("curl")
    ;

    companion object {
        fun pickCustomAntennaFromDisplayName(customTeletubbyAntennaType: String): AntennaType? {
            return values().firstOrNull { it.displayName == customTeletubbyAntennaType }
        }
    }
}
