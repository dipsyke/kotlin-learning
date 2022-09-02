package practice.practice14

fun main() {
    val appletree = Tree("apple")
    val raspberrytree = Tree("raspberry")
    val grapetree = Tree("grape")
    val carrottree = Tree("carrot")

    val game = Game()
    game.addTree(appletree)
    game.addTree(raspberrytree)
    game.addTree(grapetree)
    game.addTree(carrottree)


//    while (game.harvestedFruits < 300) {
    while (true) {
        try {
            println(game.getReadableGameState())
            println("Which action do you want to take?\nwater\nharvest\nexit")
            val action = readLine()!!
            if (action == "exit") {
                println("EXITING...")
                break
            }
            println("Choose a tree from 0 to ${game.getNumberOfTrees() - 1}")
            val chosenTree = readLine()!!.toInt()

            if (action == "water") {
                game.water(chosenTree)
            }

            if (action == "harvest") {
                game.harvest(chosenTree)
            }
            game.tick()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    println(game.getReadableGameState())

}