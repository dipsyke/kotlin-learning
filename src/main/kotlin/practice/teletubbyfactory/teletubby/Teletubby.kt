package practice.teletubbyfactory.teletubby

import practice.teletubbyfactory.AntennaType

interface Teletubby {
    val name: String
    val color: String
    val antenna: AntennaType
    val vigorousness: Int
}