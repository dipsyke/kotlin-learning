
class Tree(val type: String) {

    var numberOfFruits: Int = 0
                get() {
            if (!isAlive) {
                throw Exception("Ez a fa mér meghalt")
            }
            return field
        }
//        set(value) {
//            if (value < 0) {
//                throw Exception("Nem lehet negatív számű gyümi")
//            }
//            field = value
//        }
//          private set(value) {
//              field = value
//          }
        private set

    var isAlive: Boolean = true

    fun tick() {
        numberOfFruits += 1
    }
}


fun asd() {
    val tree1 = Tree("alma")

    println(tree1.numberOfFruits)

    //tree1.numberOfFruits = -10 // private setter miatt nem működik
}