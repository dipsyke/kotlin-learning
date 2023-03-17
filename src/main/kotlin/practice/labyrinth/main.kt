package practice.labyrinth

import java.io.File
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.pathString

fun main() {
    val loadedSave: SaveDto = SavingUtils.load()

    val levelsDirectory: Path = Path.of("C:\\tmp\\labirintus")
    val allMapFiles = levelsDirectory.listDirectoryEntries()


    for (i in loadedSave.currentMapIndex until allMapFiles.size) {
        println("Starting map: ${allMapFiles[i].pathString}")

        playLevel(allMapFiles[i].pathString)

        SavingUtils.save(SaveDto(currentMapIndex = i + 1))
    }

}

fun playLevel(level: String) {
    var numberOfKeys = 0

    val gameMap = readMap(level)
    println(gameMap.board)

    var playerPosition = Coordinate(0, 0)

    val startFieldPosition = getFieldPosition(gameMap.board, fieldToFind = FieldType.START)
    if (startFieldPosition != null) {
        playerPosition = startFieldPosition
    }

    printBoard(gameMap.board, playerPosition)


    while (true) {
        val wantedPosition = when (readLine()!!) {
            "d" -> Coordinate(playerPosition.x + 1, playerPosition.y)
            "a" -> Coordinate(playerPosition.x - 1, playerPosition.y)
            "w" -> Coordinate(playerPosition.x, playerPosition.y - 1)
            "s" -> Coordinate(playerPosition.x, playerPosition.y + 1)
            else -> playerPosition
        }

        if (
            wantedPosition.x < gameMap.width && wantedPosition.x >= 0 &&
            wantedPosition.y < gameMap.height && wantedPosition.y >= 0
        ) {
            when (gameMap.board[wantedPosition.y][wantedPosition.x]) {
                FieldType.WALL -> println("You cannot step here! This is a wall.")
                FieldType.EMPTY, FieldType.FINISH, FieldType.START -> playerPosition = wantedPosition
                FieldType.KEY -> {
                    playerPosition = wantedPosition
                    ++numberOfKeys
                    gameMap.board[wantedPosition.y][wantedPosition.x] = FieldType.EMPTY
                    println("You've collected a key.")
                }
                FieldType.DOOR -> {
                    if (numberOfKeys > 0) {
                        playerPosition = wantedPosition
                        --numberOfKeys
                        gameMap.board[wantedPosition.y][wantedPosition.x] = FieldType.EMPTY
                        println("You used a key to unlock a door.")
                    } else {
                        println("You need a key to open this door.")
                    }
                }
                FieldType.PORTALIN -> {
                    val portalOutPosition = getFieldPosition(gameMap.board, FieldType.PORTALOUT)
                    if (portalOutPosition != null) {
                        playerPosition = portalOutPosition
                    } else {
                        playerPosition = wantedPosition
                    }
                }
                FieldType.PORTALOUT -> {
                    val portalInPosition = getFieldPosition(gameMap.board, FieldType.PORTALIN)
                    if (portalInPosition != null) {
                        playerPosition = portalInPosition
                    } else {
                        playerPosition = wantedPosition
                    }
                }
            }
        } else {
            println("You cannot step here! This would be outside the map.")
        }


        printBoard(gameMap.board, playerPosition)
        val finishFieldPosition = getFieldPosition(gameMap.board, FieldType.FINISH)
        if (playerPosition.x == finishFieldPosition!!.x && playerPosition.y == finishFieldPosition.y) {
            println("Yay you've won!")
            break
        }

    }
}

fun readMap(fileName: String): GameMap {
    val gameMapLines = readFileAsLines(fileName)
    val gameMapWidth = gameMapLines[0].split(";")[0].toInt()
    val gameMapHeight = gameMapLines[0].split(";")[1].toInt()

    val board = ArrayList<ArrayList<FieldType>>()
    for (y in 1 until gameMapHeight + 1) {
        val row = ArrayList<FieldType>()

        board.add(row)
        for (x in 0 until gameMapWidth) {
            when (gameMapLines[y][x]) {
                'X' -> row.add(FieldType.WALL)
                'x' -> row.add(FieldType.WALL)
                ' ' -> row.add(FieldType.EMPTY)
                '!' -> row.add(FieldType.FINISH)
                '?' -> row.add(FieldType.START)
                'D' -> row.add(FieldType.DOOR)
                'K' -> row.add(FieldType.KEY)
                'P' -> row.add(FieldType.PORTALIN)
                'O' -> row.add(FieldType.PORTALOUT)
                else -> throw Exception("Unrecognized field in map at $x;$y")
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

fun getFieldPosition(board: List<List<FieldType>>, fieldToFind: FieldType): Coordinate? {
    var y = 0
    for (row in board) {
        var x = 0
        for (field in row) {

            if (field == fieldToFind) {

                return Coordinate(x, y)
            }
            ++x
        }
        ++y
    }
    return null
}