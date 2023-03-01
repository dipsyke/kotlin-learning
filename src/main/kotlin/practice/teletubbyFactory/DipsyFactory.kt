package practice.teletubbyFactory

import kotlin.random.Random

class DipsyFactory() : TeletubbyFactory {
    override fun createNewTeletubby(): Teletubby {
        val randomNumber = Random.nextInt(0, 10)
        return Dipsy(randomNumber)
    }
}