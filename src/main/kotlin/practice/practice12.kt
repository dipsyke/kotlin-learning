package practice

class Product(var size: Int? = null, var color: String? = null, var shape: String? = null) {
    override fun toString(): String {
        return "{size: $size, color: $color, shape: $shape}"
    }
}

class SizeMachine(val targetSize: Int) {

    fun customizeProduct(productToWorkOn: Product) {
        println("Working on product: resizing to $targetSize")
        productToWorkOn.size = targetSize
    }
}

class ColorMachine(val targetColor: String) {
    fun customizeProduct(productToWorkOn: Product) {
        println("Working on product: recoloring to $targetColor")
        productToWorkOn.color = targetColor
    }
}

class ShapeMachine(val targetShape: String) {
    fun customizeProduct(productToWorkOn: Product) {
        println("Working on product: reshaping to $targetShape")
        productToWorkOn.shape = targetShape
    }
}

class ProductionLine(
    val sizeMachineToUse: SizeMachine,
    val colorMachineToUse: ColorMachine,
    val shapeMachineToUse: ShapeMachine
) {
    fun factorProduct(): Product {
        val newProduct = Product()
        sizeMachineToUse.customizeProduct(newProduct)
        colorMachineToUse.customizeProduct(newProduct)
        shapeMachineToUse.customizeProduct(newProduct)
        return newProduct
    }
}

fun main() {

    val p = Product()
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

    val bigRedTriangle = productionLineBigRedTriangle.factorProduct()
    println("This is a $bigRedTriangle")

}