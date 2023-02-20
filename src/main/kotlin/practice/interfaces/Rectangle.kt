package practice.interfaces

class Rectangle(val a: Double, val b: Double):Shape {
    override fun calculateCircumference(): Double {
        return 2*a+2*b
    }

    override fun getName(): String {
        return "Rektangle"
    }
}