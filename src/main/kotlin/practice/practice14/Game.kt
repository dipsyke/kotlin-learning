package practice.practice14

class Game() {
    private val trees: ArrayList<Tree> = ArrayList()

    var elapsedTicks: Int = 0
        private set

    var harvestedFruits: Int = 0
        private set

    fun addTree(tree: Tree) {
        trees.add(tree)
    }

    fun tick() {
        for (tree in trees) {
            tree.tick()
        }
        elapsedTicks += 1
    }

    fun harvest(treeIndex: Int) {

        val treeToHarvest = trees.get(index = treeIndex)
        harvestedFruits += treeToHarvest.harvest()
    }


    fun water(treeIndex: Int) {
        val treeToWater = trees.get(treeIndex)
        treeToWater.water()
    }

    fun getNumberOfTrees(): Int {
        var numberOfTrees = trees.size
        return numberOfTrees
    }

    fun getReadableGameState(): String {
        var concatenatedTrees = ""
        var index = 0
        for (tree in trees) {
            concatenatedTrees += "$index: " + tree.toString() + "\n"
            ++index
        }
        return """
            |=========== GAME STATE ===========
            |Elapsed ticks: $elapsedTicks
            |Number of harvested fruits: $harvestedFruits
            |Trees
            |$concatenatedTrees
            |==================================
            """.trimMargin()
    }

}