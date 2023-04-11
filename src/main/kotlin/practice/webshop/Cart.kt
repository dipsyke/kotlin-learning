package practice.webshop

class Cart {
    val productIds = ArrayList<Int>()

    fun addProduct(productId: Int) {
        productIds.add(productId)
    }

    fun removeProduct(productId: Int) {
        productIds.remove(productId)
    }
}