package practice.teletubbyfactory.teletubby

import practice.teletubbyfactory.AntennaType

class Lala(override val vigorousness: Int) : Teletubby {
    override val name: String = "Lala"
    override val color: String = "yellow"
    override val antenna: AntennaType = AntennaType.CURL
}