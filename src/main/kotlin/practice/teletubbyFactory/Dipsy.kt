package practice.teletubbyFactory

class Dipsy(override val vigorousness: Int) : Teletubby {
    override val color: String = "green"
    override val antenna: AntennaType = AntennaType.DRUMSTICK
}