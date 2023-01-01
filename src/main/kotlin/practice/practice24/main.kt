package practice.practice24


fun main() {
    val board = ArrayList<ArrayList<Card>>()

    repeat(4) {
        board.add(ArrayList())
    }

    for (row in board) {
        repeat(3) {
            row.add(Card("+", "A", false))
        }
    }

    board[0][1].isFrontVisible=true

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
        rowNumber+=1
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