package practice.teletubbyfactory.teletubby

import practice.teletubbyfactory.AntennaType

class Dipsy(override val vigorousness: Int) : Teletubby {
    override val name: String = "Dipsy"
    override val color: String = "green"
    override val antenna: AntennaType = AntennaType.DRUMSTICK
}