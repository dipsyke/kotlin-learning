package practice.teletubbyfactory.teletubbyfactory

import practice.teletubbyfactory.teletubby.Dipsy
import practice.teletubbyfactory.teletubby.Teletubby
import practice.teletubbyfactory.teletubby.TinkyWinky
import kotlin.random.Random

class TinkyWinkyFactory: TeletubbyFactory {
    init {
        println("TinkyWinkyFactory is being constructed")
    }
    override fun createNewTeletubby(): Teletubby {
        println("A Tinky Winky is being made...")
        val randomNumber = Random.nextInt(50,101)
        val newTeletubby = TinkyWinky(randomNumber)
        println("Your Tinky Winky is ready.")
        return newTeletubby
    }
}