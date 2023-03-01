package practice.teletubbyFactory

import kotlin.random.Random

interface TeletubbyFactory {
    fun createNewTeletubby(): Teletubby
}