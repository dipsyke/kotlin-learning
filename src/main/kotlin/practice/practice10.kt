package practice

fun main() {
   printRectangle(4, 8)
    println()
    println()
    println()
    printRectangle(8, 4)
    println()
    println()
    println()
    printTriangle(1)
    printTriangle(3)
    printTriangle(5)
    printTriangle(7)
    printRectangle(3, 3)


}

fun printRectangle(width: Int, height: Int) {

    for (y in 1..height) {
        for (x in 1..width) {
            print("*")
        }
        println()
    }

}

/**

 *
 **
 ***
 ****
 *****

 */
fun printTriangle(side: Int) {

    for (y in 1..side) {
        for (x in 1..side) {
            if (x<=y) {
                print("*")
            }else{
                print(" ")
            }
        }
        println()
    }

}