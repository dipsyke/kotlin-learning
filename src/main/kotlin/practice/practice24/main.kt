package practice.practice24

val POSSIBLE_FRONT_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

//val POSSIBLE_FRONT_CHARACTERS = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
var pointsPlayer1 = 0
var pointsPlayer2 = 0

fun main() {
    val boardWidth = 3
    val boardHeight = 4
    val numberOfCards = boardHeight * boardWidth
    val cards = ArrayList<Card>()


    for (n in 0 until numberOfCards / 2) {
        cards.add(Card("+", POSSIBLE_FRONT_CHARACTERS[n].toString(), false))
        cards.add(Card("+", POSSIBLE_FRONT_CHARACTERS[n].toString(), false))

    }

    cards.shuffle()

    println(cards)


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
        printBoard(board)
        println("Player1, pick your first card (x;y)")
        val pick1OfPlayer1 = readLine()!!.split(";")
        val card1OfPlayer1 = board[pick1OfPlayer1[1].toInt()][pick1OfPlayer1[0].toInt()]
        card1OfPlayer1.isFrontVisible = true
        printBoard(board)

        println("Player1, pick your second card (x;y)")
        val pick2OfPlayer1 = readLine()!!.split(";")
        val card2OfPlayer1 = board[pick2OfPlayer1[1].toInt()][pick2OfPlayer1[0].toInt()]
        card2OfPlayer1.isFrontVisible = true
        printBoard(board)

        if (card1OfPlayer1.front == card2OfPlayer1.front) {
            println("It's a match!")
            pointsPlayer1 += 1
        } else {
            card1OfPlayer1.isFrontVisible = false
            card2OfPlayer1.isFrontVisible = false
            println("No match :(")
        }
        Thread.sleep(1_000)

        printBoard(board)
        println("Player2, pick your first card (x;y)")
        val pick1OfPlayer2 = readLine()!!.split(";")
        val card1OfPlayer2 = board[pick1OfPlayer2[1].toInt()][pick1OfPlayer2[0].toInt()]
        card1OfPlayer2.isFrontVisible = true
        printBoard(board)

        println("Player2, pick your second card (x;y)")
        val pick2OfPlayer2 = readLine()!!.split(";")
        val card2OfPlayer2 = board[pick2OfPlayer2[1].toInt()][pick2OfPlayer2[0].toInt()]
        card2OfPlayer2.isFrontVisible = true
        printBoard(board)

        if (card1OfPlayer2.front == card2OfPlayer2.front) {
            println("It's a match!")
            pointsPlayer2 += 1
        } else {
            card1OfPlayer2.isFrontVisible = false
            card2OfPlayer2.isFrontVisible = false
            println("No match :(")
        }
        println()
        println()
        println()
        println()

        numberOfRounds += 1
        Thread.sleep(1_000)

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