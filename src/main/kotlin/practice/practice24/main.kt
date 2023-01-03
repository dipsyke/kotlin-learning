package practice.practice24

val POSSIBLE_FRONT_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun main() {
    val boardWidth = 6
    val boardHeight = 8
    val numberOfCards = boardHeight*boardWidth
    val cards = ArrayList<Card>()
    for (n in 0 until numberOfCards/2) {
        cards.add(Card("+", POSSIBLE_FRONT_CHARACTERS[n].toString(), true))
        cards.add(Card("+", POSSIBLE_FRONT_CHARACTERS[n].toString(), true))
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


    printBoard(board)

}

fun printBoard(board: ArrayList<ArrayList<Card>>) {
    val boardWidth = board[0].size
    print(" ")
//    ugyanaz, mint a repeat, csak hosszabb
//    for (i in 0 until boardWidth) {
//        print(i)
//    }
    repeat(boardWidth) {
        print(it)
    }
    println()

    var rowNumber = 0
    for (row in board) {
        print(rowNumber)
        rowNumber += 1
        for (card in row) {
            if (card.isFrontVisible) {
                print(card.front)
            } else {
                print(card.back)
            }
        }
        println()
    }
}