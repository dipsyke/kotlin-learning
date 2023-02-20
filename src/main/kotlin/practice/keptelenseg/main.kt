package practice.keptelenseg

import net.coobird.thumbnailator.Thumbnails
import java.awt.Color
import java.awt.Desktop
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ColorComponentMultipliers(val redComponent: Double, val greenComponent: Double, val blueComponent: Double) {}

fun main() {

    print("Add meg a fájl nevét: ")
    val inputFileName = readLine()!!
    print("Add meg a fájl kiterjesztését: ")
    val inputFileExtension = readLine()!!
    //println("Milyen színű képet szeretnél? Add meg a komponensek szorzóit!")
    print("Milyen színű képet szeretnél? (neon, greyscale, custom): ")
    val colorOption = readLine()!!
    var colorComponentMultipliers = ColorComponentMultipliers(0.0, 0.0, 0.0)
    var newWidth = 0
    var newHeight = 0

    when (colorOption) {
        "neon" -> {
            print("Milyen neon színt szeretnél? (red, green, blue): ")
            val neonColorOption = readLine()!!
            if (neonColorOption == "red") {
                colorComponentMultipliers = ColorComponentMultipliers(2.55, 0.49, 0.49)
            }
            if (neonColorOption == "green") {
                colorComponentMultipliers = ColorComponentMultipliers(0.244, 1.0, 0.078)

            }
            if (neonColorOption == "blue") {
                colorComponentMultipliers = ColorComponentMultipliers(0.31, 0.81, 2.55)
            }
        }

        "greyscale" -> {
            colorComponentMultipliers = ColorComponentMultipliers(1.0, 1.0, 1.0)
        }

        "custom" -> {
            colorComponentMultipliers = askForCustomColorComponents()

        }
        else -> println("This is not a valid option")
    }
    val bufferedImage: BufferedImage = ImageIO.read(File("C:\\tmp\\$inputFileName.$inputFileExtension"))

    print("Szeretnéd átméretezni a képet? (igen/nem): ")
    val resizeAnswer = readLine()!!
    if (resizeAnswer == "igen") {
        print("Milyen széles legyen az új kép pixelekben?: ")
        newWidth = readLine()!!.toInt()
        print("Milyen magas legyen az új kép pixelekben?: ")
        newHeight = readLine()!!.toInt()
    } else {
        newWidth = bufferedImage.width
        newHeight = bufferedImage.height
    }


//    println("Do you want to resize the picture to Instagram standard size? (yes, no)")
//    val answerInstagramSize = readLine()!!
//    when (answerInstagramSize) {
//        "yes" -> {
//            BufferedImage(100, 80, BufferedImage.TYPE_INT_RGB)
//        }
//    }

    println("a kép szélessége: ${bufferedImage.width}")
    println("a kép magassága: ${bufferedImage.height}")

    val originalPixelMap = createNewBlackPixelMap(bufferedImage.width, bufferedImage.height)

    for (y in 0 until bufferedImage.height) {
        for (x in 0 until bufferedImage.width) {
            val gotPixel = bufferedImage.getRGB(x, y)
            val color = Color(gotPixel, true)
            originalPixelMap[y][x] = color

            val red = color.red
            val green = color.green
            val blue = color.blue
            // println("x: $x; y: $y; pixel: ${red.toString(16)}, ${green.toString(16)}, ${blue.toString(16)}")
        }

    }

    val greyscalePixelMap = createNewBlackPixelMap(bufferedImage.width, bufferedImage.height)
    for (y in 0 until bufferedImage.height) {
        for (x in 0 until bufferedImage.width) {
            var average = 0

            average += originalPixelMap[y][x].red
            average += originalPixelMap[y][x].green
            average += originalPixelMap[y][x].blue
            average = average / 3
            //val grayscale = Color(average, average, average)
            val minRed = minOf(255, (colorComponentMultipliers.redComponent * average).toInt())
            val minGreen = minOf(255, (colorComponentMultipliers.greenComponent * average).toInt())
            val minBlue = minOf(255, (colorComponentMultipliers.blueComponent * average).toInt())
            val grayscale = Color(minRed, minGreen, minBlue)
            greyscalePixelMap[y][x] = grayscale

        }
    }

    val img = BufferedImage(bufferedImage.width, bufferedImage.height, BufferedImage.TYPE_INT_RGB)

    for (y in 0 until bufferedImage.height) {
        for (x in 0 until bufferedImage.width) {
            img.setRGB(x, y, greyscalePixelMap[y][x].rgb)
        }
    }


    val resizedImg = Thumbnails.of(img).size(newWidth, newHeight).asBufferedImage()

    val outputFile = File("C:\\tmp\\$inputFileName-$colorOption.png")
    ImageIO.write(resizedImg, "png", outputFile)
    println("Csináltam neked egy egyéni képet")
    Desktop.getDesktop().open(outputFile)
}


fun createNewBlackPixelMap(width: Int, height: Int): ArrayList<ArrayList<Color>> {

    val newPixelMap = ArrayList<ArrayList<Color>>()
    for (i in 1..height) {
        newPixelMap.add(ArrayList())
    }
    for (row in newPixelMap) {
        for (i in 1..width) {
            row.add(Color(0, 0, 0))
        }
    }
    return newPixelMap
}


fun askForCustomColorComponents(): ColorComponentMultipliers {
    print("Add meg a prios szorzóját: ")
    val redCustomComponent = readLine()!!.toDouble()
    print("Add meg a zöld szorzóját: ")
    val greenCustomComponent = readLine()!!.toDouble()
    print("Add meg a kék szorzóját: ")
    val blueCustomComponent = readLine()!!.toDouble()

    return ColorComponentMultipliers(redCustomComponent, greenCustomComponent, blueCustomComponent)
}

