package practice.interfaces

class Triangle(val a: Double, val b:Double, val c:Double):Shape{
    override fun calculateCircumference(): Double {
        return a+b+c
    }

    override fun getName(): String {
        return "Tinky nose"
    }
}