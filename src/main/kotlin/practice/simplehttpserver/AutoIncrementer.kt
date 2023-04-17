package practice.simplehttpserver

class AutoIncrementer(
    private var nextValue: Int = 1
) {
    fun getNextValue(): Int {
        val valueToReturn = nextValue
        nextValue += 1
        return valueToReturn
    }

//    fun getNextValueGood(): Int {
//        return nextValue++
//    }
}