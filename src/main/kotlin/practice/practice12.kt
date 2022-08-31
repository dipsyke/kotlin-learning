package practice.p12

private class Product(val name: String,  var size: Int? = null, var color: String? = null, var shape: String? = null) {
    override fun toString(): String {
        return "{name: ${name}, size: $size, color: $color, shape: $shape}"
    }
}

private class SizeMachine(val targetSize: Int) {

    fun customizeProduct(productToWorkOn: Product) {
        println("Working on product: resizing to $targetSize")
        productToWorkOn.size = targetSize
    }
}

private class ColorMachine(val targetColor: String) {
    fun customizeProduct(productToWorkOn: Product) {
        println("Working on product: recoloring to $targetColor")
        productToWorkOn.color = targetColor
    }
}

private class ShapeMachine(val targetShape: String) {
    fun customizeProduct(productToWorkOn: Product) {
        println("Working on product: reshaping to $targetShape")
        productToWorkOn.shape = targetShape
    }
}

private class ProductionLine(
    val sizeMachineToUse: SizeMachine,
    val colorMachineToUse: ColorMachine,
    val shapeMachineToUse: ShapeMachine
) {
    fun factorProduct(productName: String): Product {
        val newProduct = Product(name = productName)
        sizeMachineToUse.customizeProduct(newProduct)
        colorMachineToUse.customizeProduct(newProduct)
        shapeMachineToUse.customizeProduct(newProduct)
        return newProduct
    }
}

fun main() {

    val p = Product(name = "Kati")
    val sizeMachineSmall = SizeMachine(targetSize = 5)
    val sizeMachineBig = SizeMachine(targetSize = 30)

    sizeMachineSmall.customizeProduct(p)
    println(p)

    val colorMachinePurple = ColorMachine(targetColor = "purple")
    val colorMachineRed = ColorMachine(targetColor = "red")
    colorMachineRed.customizeProduct(p)
    println(p)

    val shapeMachineTriangle = ShapeMachine(targetShape = "triangle")
    val shapeMachineRound = ShapeMachine(targetShape = "round")
    shapeMachineRound.customizeProduct(p)
    println(p)

    val productionLineBigRedTriangle = ProductionLine(sizeMachineBig, colorMachineRed, shapeMachineTriangle)
    val productionLineSmallPurpleRound = ProductionLine(sizeMachineSmall, colorMachinePurple, shapeMachineRound)

    val bigRedTriangle = productionLineBigRedTriangle.factorProduct("Linda")
    println("This is a $bigRedTriangle")

    val smallPurpleRound = productionLineSmallPurpleRound.factorProduct("Dóri")
    println("This is a $smallPurpleRound")

    val smallPurpleRound2 = productionLineSmallPurpleRound.factorProduct("Gábor")
    println("This is Gábor, a $smallPurpleRound2")

}