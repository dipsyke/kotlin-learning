package practice.teletubbyfactory.teletubbyfactory

import practice.teletubbyfactory.teletubby.Dipsy
import kotlin.random.Random

class DipsyFactory() : TeletubbyFactory {

    init {
        println("DipsyFactory is being constructed")
    }

    override fun createNewTeletubby(): Dipsy {
        println("A Dipsy is being made...")
        val randomNumber = Random.nextInt(0,101)
        val newTeletubby = Dipsy(randomNumber)
        println("Your Dipsy is ready.")
        return newTeletubby
    }
}

//fun main() {
//    val v1 = 1
//    val v2 = 2
//    fa(1, 2)
//    fa(v1, v2)
//    fa(a = v1, b = v2)
//    fa(b = v2, a = v1)
//}
//
//fun fa(a: Int, b: Int) {
//
//}