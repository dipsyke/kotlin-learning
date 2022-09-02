package practice.practice14

class Tree(val type: String) {

    var numberOfFruits: Int = 0
        private set

    var waterLevel: Int = 10
        private set

    var isAlive: Boolean = true
        private set


    fun tick() {
        if (isAlive == false) {
            return
        }

        waterLevel -= 1
        if (waterLevel > 0) {
            numberOfFruits += 1
        }

        if (waterLevel < -4) {
            isAlive = false
        }
    }

    fun water() {
        waterLevel += 5
    }

    fun harvest(): Int {
        val savedNumberOfFruits = numberOfFruits
        numberOfFruits = 0
        return savedNumberOfFruits
    }


    override fun toString(): String {
        return "{number of fruits: $numberOfFruits, water level: $waterLevel, is alive: $isAlive, type: $type}"
    }


}