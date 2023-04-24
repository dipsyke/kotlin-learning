package practice.tubbydatasource

class AutoIncrementer(
    var currentValue: Int = 1
) {
    fun getNextValue(): Int {
        val valueToReturn = currentValue
        currentValue += 1
        return valueToReturn
    }

//    fun getNextValueGood(): Int {
//        return nextValue++
//    }
}