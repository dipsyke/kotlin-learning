package practice

fun main() {

    val age = askForAge()
    if (age >= 18) {
        println("Jó, akkor kaphatsz alkoholt.")
        offerAlcohol()
    } else {
        println("Nem kapsz alkoholt :P")
    }
}

fun offerAlcohol() {
    println("Milyen fajta alkoholt szeretnél?")
    /*
    válaszlehetőségek: sör, bor, pálinka
    Ha bármi mást ír be, akkor azt mondjuk neki, hogy "Nem tudom, mit akarsz"
     */

    val chosenAlcohol = readLine()!!

    if (chosenAlcohol == "sört") {
        println("Tessék it a sörcid!")
    } else if (chosenAlcohol == "bort") {
        println("Itt a borcid!")
    } else if (chosenAlcohol == "pálinkát") {
        println("Tessék itt a pálinkacsád!")
    } else {
        println("Nem tudom, mit akarsz")
    }
}

/**
 * @return age of the user
 */
fun askForAge(): Int {
    println("Hány éves vagy?")

    val originallyRead = readLine()
    val nonNullRead = originallyRead!!
    val convertedToNumberRead = nonNullRead.toInt()

    return convertedToNumberRead
}