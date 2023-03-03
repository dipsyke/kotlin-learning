package practice.teletubbyfactory.teletubby

import practice.teletubbyfactory.AntennaType

class TinkyWinky(override val vigorousness: Int) : Teletubby {
    override val name: String = "Tinky Winky"
    override val color: String = "purple"
    override val antenna: AntennaType = AntennaType.TRIANGLE
}