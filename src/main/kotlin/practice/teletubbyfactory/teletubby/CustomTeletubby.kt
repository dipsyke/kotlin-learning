package practice.teletubbyfactory.teletubby

import practice.teletubbyfactory.AntennaType

class CustomTeletubby(
    override val name: String,
    override val color: String,
    override val antenna: AntennaType,
    override val vigorousness: Int
) : Teletubby {
}