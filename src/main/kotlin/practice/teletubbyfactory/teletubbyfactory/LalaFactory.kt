package practice.teletubbyfactory.teletubbyfactory

import practice.teletubbyfactory.teletubby.Lala
import practice.teletubbyfactory.teletubby.Teletubby
import kotlin.random.Random

class LalaFactory: TeletubbyFactory {

    init {
        println("Lala factory is being constructed...")
    }
    override fun createNewTeletubby(): Teletubby {
        println("A Lala is being made...")
        val randomNumber = Random.nextInt(0,51)
        val newTeletubby = Lala(randomNumber)
        println("Your Lala is ready.")
        return newTeletubby
    }
}