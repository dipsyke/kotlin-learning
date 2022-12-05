package practice.practice23

import java.awt.Color
import java.awt.Desktop
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


fun main() {
    print("Add meg a fájl nevét: ")
    val inputFileName = readLine()!!
    print("Add meg a fájl kiterjesztését: ")
    val inputFileExtension = readLine()!!
    println("Milyen színű képet szeretnél? Add meg a komponensek szorzóit!")
    print("Add meg a piros szorzóját: ")
    val redComponent = readLine()!!.toDouble()
    print("Add meg a zöld szorzóját: ")
    val greenComponent = readLine()!!.toDouble()
    print("Add meg a kék szorzóját: ")
    val blueComponent = readLine()!!.toDouble()
    val bufferedImage: BufferedImage = ImageIO.read(File("C:\\tmp\\$inputFileName.$inputFileExtension"))

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
            val minRed = minOf(255, (redComponent * average).toInt())
            val minGreen = minOf(255, (greenComponent * average).toInt())
            val minBlue = minOf(255, (blueComponent * average).toInt())
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


    val outputFile = File("C:\\tmp\\$inputFileName-custom.png")
    ImageIO.write(img, "png", outputFile)
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