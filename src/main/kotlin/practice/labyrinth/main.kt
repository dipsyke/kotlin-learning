package practice.labyrinth

import practice.library.ContentCategory
import java.io.File

fun main() {

    val gameMap = readMap("C:\\tmp\\labirintus\\map1.txt")
    println(gameMap.board)

    var playerPosition = getStartPlayerPosition(gameMap.board)


    printBoard(gameMap.board, playerPosition)
    while (true) {
        when (readLine()!!) {
            "d" -> playerPosition = Coordinate(playerPosition.x + 1, playerPosition.y)
            "a" -> playerPosition = Coordinate(playerPosition.x - 1, playerPosition.y)
            "w" -> playerPosition = Coordinate(playerPosition.x , playerPosition.y-1)
            "s" -> playerPosition = Coordinate(playerPosition.x , playerPosition.y+1)
        }
        printBoard(gameMap.board, playerPosition)
    }

}

fun readMap(fileName: String): GameMap {
    val gameMapLines = readFileAsLines(fileName)
    val gameMapHeight = gameMapLines[0].split(";")[0].toInt()
    val gameMapWidth = gameMapLines[0].split(";")[1].toInt()

    val board = ArrayList<ArrayList<FieldType>>()
    for (y in 1 until gameMapHeight + 1) {
        val row = ArrayList<FieldType>()

        board.add(row)
        for (x in 0 until gameMapWidth) {
            when (gameMapLines[y][x]) {
                'X' -> row.add(FieldType.WALL)
                ' ' -> row.add(FieldType.EMPTY)
                '!' -> row.add(FieldType.FINISH)
                '?' -> row.add(FieldType.START)
            }
        }

    }
    return GameMap(gameMapHeight, gameMapWidth, board)

}

fun readFileAsLines(fileName: String): List<String> {
    return File(fileName).readLines()
}

fun printBoard(board: List<List<FieldType>>, playerPosition: Coordinate) {
    var y = 0
    for (row in board) {
        var x = 0
        for (field in row) {

            if (x == playerPosition.x && y == playerPosition.y) {
                print("*")
            } else {
                print(field.displayName)
            }
            ++x
        }
        println()
        ++y
    }


}

fun getStartPlayerPosition(board: List<List<FieldType>>):Coordinate {
    var y = 0
    for (row in board) {
        var x = 0
        for (field in row) {

            if (field == FieldType.START) {

                return Coordinate(x, y)
            }
            ++x
        }
        println()
        ++y
    }
    return Coordinate(0,0)
}