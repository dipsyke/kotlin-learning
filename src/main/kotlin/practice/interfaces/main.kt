package practice.interfaces

fun main() {
//    displayShape(Triangle(a = 1.0, b = 2.0, c =3.0))
//    displayShape(Rectangle(a = 2.0, b = 3.0))
//    displayShape(Rectangle(a = 3.0, b = 4.0))
//    displayShape(Circle(r = 69.0))

    val listOfShapes = ArrayList<Shape>()
    listOfShapes.add(Triangle(a =  1.0, b=2.0, c=3.0))
    listOfShapes.add(Triangle(a =  2.0, b=3.0, c=4.0))
    listOfShapes.add(Triangle(a =  3.0, b=4.0, c=5.0))
    listOfShapes.add(Rectangle(a =  1.0, b=2.0))
    listOfShapes.add(Rectangle(a =  2.0, b=3.0))
    listOfShapes.add(Rectangle(a =  3.0, b=4.0))
    listOfShapes.add(Circle(r = 1.0))
    listOfShapes.add(Circle(r = 2.0))
    listOfShapes.add(Circle(r = 3.0))

    for (i in listOfShapes) {
        displayShape(i)
    }
}

fun displayShape(shape: Shape) {
    println("This is a ${shape.getName()}, and its circumference is ${shape.calculateCircumference()} ")
}