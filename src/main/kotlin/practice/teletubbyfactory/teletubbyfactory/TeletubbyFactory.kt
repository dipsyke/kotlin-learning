package practice.teletubbyfactory.teletubbyfactory

import practice.teletubbyfactory.teletubby.Teletubby

interface TeletubbyFactory {
    fun createNewTeletubby(): Teletubby
}