package practice.teletubbyfactory.teletubbyfactory

import practice.teletubbyfactory.teletubby.Po
import practice.teletubbyfactory.teletubby.Teletubby
import kotlin.random.Random

class PoFactory: TeletubbyFactory {

    init {
        println("Po factory is being constructed...")
    }
    override fun createNewTeletubby(): Teletubby {
        println("A Po is being made...")
        val randomNumber = Random.nextInt(0,101)
        val newTeletubby = Po(randomNumber)
        println("Your Po is ready.")
        return newTeletubby
    }
}