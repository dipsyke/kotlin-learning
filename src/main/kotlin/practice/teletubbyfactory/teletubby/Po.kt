package practice.teletubbyfactory.teletubby

import practice.teletubbyfactory.AntennaType

class Po(override val vigorousness: Int) : Teletubby {
    override val name: String = "Po"
    override val color: String = "red"
    override val antenna: AntennaType = AntennaType.CIRCLE
}