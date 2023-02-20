package practice.interfaces

fun main() {
    displayShape(Triangle(a = 1.0, b = 2.0, c =3.0))
    displayShape(Rectangle(a = 2.0, b = 3.0))
    displayShape(Rectangle(a = 3.0, b = 4.0))
    displayShape(Circle(r = 69.0))
}

fun displayShape(shape: Shape) {
    println("This is a ${shape.getName()}, and its circumference is ${shape.calculateCircumference()} ")
}