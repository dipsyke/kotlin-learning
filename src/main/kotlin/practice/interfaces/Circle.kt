package practice.interfaces

class Circle(val r: Double): Shape {
    override fun calculateCircumference(): Double {
       return 2*r*Math.PI
    }

    override fun getName(): String {
        return "Po"
    }

}