package practice.simplehttpserver

import practice.tubbydatasource.Entity

data class Tree(
    override var id: Int?= null,
    val name: String = "",
    val age: Int?= null,
) : Entity