package practice.simplehttpserver

import practice.tubbydatasource.Entity


data class Product (
    override var id: Int?= null,
    var name: String = "",
    var price: Int? = null,
): Entity