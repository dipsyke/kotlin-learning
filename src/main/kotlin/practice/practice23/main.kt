package practice.practice23

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


class Pixel(val red: Int, val green: Int, val blue: Int)

fun main() {
    val bufferedImage: BufferedImage = ImageIO.read(File("C:\\tmp\\google.png"))

    println("a kép szélessége: ${bufferedImage.width}")
    println("a kép magassága: ${bufferedImage.height}")

    val originalImagePixel = ArrayList<ArrayList<Pixel>>()
    for (i in 1..bufferedImage.height) {
        originalImagePixel.add(ArrayList())
    }
    for (row in originalImagePixel) {
        for (i in 1..bufferedImage.width) {
            row.add(Pixel(0, 0, 0))
        }
    }

    for (y in 0 until bufferedImage.height) {
        for (x in 0 until bufferedImage.width) {
            val gotPixel = bufferedImage.getRGB(x, y)
            val color = Color(gotPixel, true)
            val red = color.red
            val green = color.green
            val blue = color.blue
            println("x: $x; y: $y; pixel: ${red.toString(16)}, ${green.toString(16)}, ${blue.toString(16)}")
            val pixel = Pixel(red, green, blue)
            originalImagePixel[y][x] = pixel
        }

    }


}