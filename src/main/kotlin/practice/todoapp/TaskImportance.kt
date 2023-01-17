package practice.todoapp

enum class TaskImportance(val level: Int, val displayName: String) {
    IMPORTANT(level = 1, "Important"),
    LESS_IMPORTANT(level = 2, "Less important"),
    NOT_IMPORTANT(level = 3, "Not important"),
}