package practice.practice19

import kotlin.math.PI

fun main() {
val radius = askForRadius()
    val circumference = calculateCircumference(radius)
    println("The circle's circumference based on the given radius is: $circumference cm")
    val area = calculateArea(radius)
    println("Area of the circle is: $area cm^2")

}

fun askForRadius(): Int {
    println("Enter the circle's radius (cm)!")
    return readLine()!!.toInt()
}

fun calculateCircumference(radius: Int): Double {
return 2 * radius * PI
}

fun calculateArea(radius: Int):Double {
    return radius * radius * PI
}