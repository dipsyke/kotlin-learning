package practice.tubbydatasource

class AutoIncrementer(
    private var currentValue: Int = 1
) {
    fun getCurrentValue(): Int {
        return currentValue
    }

    fun getNextValue(): Int {
        val valueToReturn = currentValue
        currentValue += 1
        return valueToReturn
    }

//    fun getNextValueGood(): Int {
//        return nextValue++
//    }
}