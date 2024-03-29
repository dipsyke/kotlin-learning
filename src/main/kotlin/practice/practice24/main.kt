package practice.practice24

val POSSIBLE_FRONT_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun main() {
    var pointsPlayer1 = 0
    var pointsPlayer2 = 0
    val boardWidth = 3
    val boardHeight = 4
    val numberOfCards = boardHeight * boardWidth
    val cards = ArrayList<Card>()


    for (n in 0 until numberOfCards / 2) {
        cards.add(Card("+", POSSIBLE_FRONT_CHARACTERS[n].toString(), false))
        cards.add(Card("+", POSSIBLE_FRONT_CHARACTERS[n].toString(), false))

    }

    cards.shuffle()


    val board = ArrayList<ArrayList<Card>>()

    repeat(boardHeight) {
        board.add(ArrayList())
    }
    var cardIndex = 0
    for (row in board) {
        repeat(boardWidth) {
            row.add(cards[cardIndex])
            cardIndex += 1
        }
    }

    var numberOfRounds = 1
    while (pointsPlayer1 + pointsPlayer2 < numberOfCards / 2) {
        printStateOfGame(pointsPlayer1, pointsPlayer2, numberOfRounds)
        println()

        val matchPlayer1 = play1Round(board, "Player1")
        if (matchPlayer1) {
            pointsPlayer1 += 1
        }
        if (pointsPlayer1 + pointsPlayer2 >= numberOfCards / 2) {
            break
        }
        val matchPlayer2 = play1Round(board, "Player2")
        if (matchPlayer2) {
            pointsPlayer2 += 1
        }

        numberOfRounds += 1

    }

    when {
        pointsPlayer1 > pointsPlayer2 -> println("End of Game, the Winner is *drum roll* Player1!")
        pointsPlayer2 > pointsPlayer1 -> println("End of Game, the Winner is *drum roll* Player2!")
        pointsPlayer2 == pointsPlayer1 -> println("End of Game, the Winner is *drum roll* nobody (everybody sucks)!")
    }
}

fun printBoard(board: ArrayList<ArrayList<Card>>) {
    val boardWidth = board[0].size
    repeat(boardWidth + 3) {
        print("-")
    }
    println()
    print("| ")
    repeat(boardWidth) {
        print(it)
    }
    print("|")

//    ugyanaz, mint a repeat, csak hosszabb
//    for (i in 0 until boardWidth) {
//        print(i)
//    }
    println()

    var rowNumber = 0
    for (row in board) {
        print("|")
        print(rowNumber)
        rowNumber += 1
        for (card in row) {
            if (card.isFrontVisible) {
                print(card.front)
            } else {
                print(card.back)
            }
        }
        print("|")
        println()
    }
    repeat(boardWidth + 3) {
        print("-")
    }
    println()
}

fun printStateOfGame(pointsPlayer1: Int, pointsPlayer2: Int, round: Int) {
    println(
        "Round $round\nPlayer1: $pointsPlayer1                      Player2: $pointsPlayer2"
    )

}

fun pickCard(playerName: String, cardToPick: String, board: ArrayList<ArrayList<Card>>): Coordinate {
    val boardWidth = board[0].size
    val boardHeight = board.size
    while (true) {
        print("$playerName, pick your $cardToPick card (x;y): ")
        val pick1OfPlayer1 = readLine()!!.split(";")
        val coordinate = Coordinate(pick1OfPlayer1[0].toInt(), pick1OfPlayer1[1].toInt())
        if (coordinate.x < 0 || coordinate.x >= boardWidth || coordinate.y < 0 || coordinate.y >= boardHeight) {
            println("Helytelen választás, válassz egy új kártyát (x;y)!")
            continue
        }
        val card = board[coordinate.y][coordinate.x]
        if (card.isFrontVisible) {
            println("Ezt a kártyát nem lehet még egyszer kijátszani, válassz egy új kártyát!")
            continue
        }
        return coordinate

    }
}

fun play1Round(board: ArrayList<ArrayList<Card>>, playerName: String): Boolean {
    printBoard(board)
    val coordinateOfPick1OfPlayer = pickCard(playerName, "first", board)
    val card1OfPlayer = board[coordinateOfPick1OfPlayer.y][coordinateOfPick1OfPlayer.x]
    card1OfPlayer.isFrontVisible = true
    printBoard(board)

    val coordinateOfPick2OfPlayer = pickCard(playerName, "second", board)
    val card2OfPlayer = board[coordinateOfPick2OfPlayer.y][coordinateOfPick2OfPlayer.x]
    card2OfPlayer.isFrontVisible = true
    printBoard(board)
    val match = card1OfPlayer.front == card2OfPlayer.front

    if (match) {
        println("It's a match!")
    } else {
        card1OfPlayer.isFrontVisible = false
        card2OfPlayer.isFrontVisible = false
        println("No match :(")
    }
    Thread.sleep(2_000)
    return match
}