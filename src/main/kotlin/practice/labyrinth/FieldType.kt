package practice.labyrinth

enum class FieldType (val displayName: String){
    EMPTY (displayName = " "),
    WALL (displayName = "X"),
    FINISH ("!"),
    START("?")
}