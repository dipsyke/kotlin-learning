package practice.teletubbyfactory

import practice.teletubbyfactory.teletubby.CustomTeletubby
import practice.teletubbyfactory.teletubby.Teletubby
import practice.teletubbyfactory.teletubbyfactory.DipsyFactory
import practice.teletubbyfactory.teletubbyfactory.LalaFactory
import practice.teletubbyfactory.teletubbyfactory.PoFactory
import practice.teletubbyfactory.teletubbyfactory.TinkyWinkyFactory
import kotlin.random.Random

fun main() {

    val tinkyWinkyFactory = TinkyWinkyFactory()
    val dipsyFactory = DipsyFactory()
    val lalaFactory = LalaFactory()
    val poFactory = PoFactory()

    print("Hey and welcome to the Teletubby Factory! Which teletubby would you like to build today? (tw, d, l, p, c=custom): ")
    val teletubbyToBuild = readLine()!!

    when (teletubbyToBuild) {
        "tw" -> displayTeletubby(tinkyWinkyFactory.createNewTeletubby())
        "d" -> displayTeletubby(dipsyFactory.createNewTeletubby())
        "l" -> displayTeletubby(lalaFactory.createNewTeletubby())
        "p" -> displayTeletubby(poFactory.createNewTeletubby())
        "c" -> {
            print("Please name your custom teletubby: ")
            val customTeletubbyName = readLine()!!
            print("What color teletubby do you want?: ")
            val customTeletubbyColor = readLine()!!
            val randomNumber = Random.nextInt(0, 101)
            print("What antenna type do you want for your teletubby?(drumstick, triangle, circle, curl): ")
            var customTeletubbyAntennaType: AntennaType? = null
            while (true) {
               customTeletubbyAntennaType = AntennaType.pickCustomAntennaFromDisplayName(readLine()!!)
                if (customTeletubbyAntennaType == null) {
                    println("Uh oh your teletubby has no antenna yet! Please choose a valid antenna type (drumstick, triangle, circle, curl): ")
                } else {
                    break
                }
            }

            displayTeletubby(
                CustomTeletubby(
                    customTeletubbyName,
                    customTeletubbyColor,
                    vigorousness = randomNumber,
                    antenna = customTeletubbyAntennaType!!
                )
            )
        }
    }


//    displayTeletubby(dipsyFactory.createNewTeletubby())
//    displayTeletubby(tinkyWinkyFactory.createNewTeletubby())
//    displayTeletubby(lalaFactory.createNewTeletubby())
//    displayTeletubby(poFactory.createNewTeletubby())

}

fun displayTeletubby(teletubbyToDisplay: Teletubby) {
    println("----------------------------------------")
    println("Your new Teletubby is ${teletubbyToDisplay.name}!!!!!\ncolor: ${teletubbyToDisplay.color}\nantenna type: ${teletubbyToDisplay.antenna}\nvigorousness: ${teletubbyToDisplay.vigorousness}%")
    if (teletubbyToDisplay.vigorousness >= 80) {
        println("Good luck...")
    }
    println("------------------------------------------")

}