package practice.simplehttpserver

import practice.tubbydatasource.Entity

data class Task(
    override var id: Int? = null,
    val name: String = "",
    var isDone: Boolean = false
) : Entity