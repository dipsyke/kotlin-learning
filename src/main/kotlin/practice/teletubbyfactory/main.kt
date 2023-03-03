package practice.teletubbyfactory

import practice.teletubbyfactory.teletubby.Dipsy
import practice.teletubbyfactory.teletubby.Teletubby
import practice.teletubbyfactory.teletubbyfactory.DipsyFactory
import practice.teletubbyfactory.teletubbyfactory.TinkyWinkyFactory

fun main() {
    val dipsyFactory = DipsyFactory()

    displayTeletubby(dipsyFactory.createNewTeletubby())
    displayTeletubby(dipsyFactory.createNewTeletubby())
    displayTeletubby(dipsyFactory.createNewTeletubby())

    val tinkyWinkyFactory = TinkyWinkyFactory()

    displayTeletubby(tinkyWinkyFactory.createNewTeletubby())
    displayTeletubby(tinkyWinkyFactory.createNewTeletubby())
    displayTeletubby(tinkyWinkyFactory.createNewTeletubby())


}

fun displayTeletubby(teletubbyToDisplay: Teletubby) {
    println("----------------------------------------")
    println("Your new Teletubby is ${teletubbyToDisplay.name}!!!!!\ncolor: ${teletubbyToDisplay.color}\nantenna type: ${teletubbyToDisplay.antenna}\nvigorousness: ${teletubbyToDisplay.vigorousness}%")
    if (teletubbyToDisplay.vigorousness>=80) {
        println("Good luck...")
    }
    println("------------------------------------------")
}