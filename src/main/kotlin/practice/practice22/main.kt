package practice.practice22

fun main() {
    val board: ArrayList<ArrayList<String>> = ArrayList()

    for (i in 1..10) {
        board.add(ArrayList())
    }


    for (row in board) {
        for (i in 0..10) {
            row.add(" ")
        }
    }



    while (true) {
        print("Player X, enter the coordinates of the next X (row;column):")
        val xCoordinates: String = readLine()!!
        val splitCoordinates = xCoordinates.split(";")

        board[splitCoordinates[0].toInt()][splitCoordinates[1].toInt()] = "X"

        printBoard(board)

    }
}

fun printBoard(board: ArrayList<ArrayList<String>>) {
    val boardDisplayWidth = board[0].size * 4 + 1
    println("==============================================================================")

    fun printHorizontalSeparator() {
        for (i in 0 until boardDisplayWidth) {
            print(if (i == 0) "├" else if (i == boardDisplayWidth - 1) "┤" else if (i % 4 == 0) "+" else "-")
        }
        println()
    }

    printHorizontalSeparator()
    for (row in board) {
        print('|')
        for (field in row) {
            print(" $field ")
            print('|')
        }
        println()
        printHorizontalSeparator()
    }

    println("==============================================================================")
}


/*

<board>

Player X, enter the coordinates of the next X:
<beolvas 5;7>
* put an X to the entered coordinates

<board>

Player X, enter the coordinates of the next X:
<beolvas 5;7>
* put an X to the entered coordinates

<board>

Player X, enter the coordinates of the next X:
<beolvas 5;7>
* put an X to the entered coordinates

<board>

Player X, enter the coordinates of the next X:
<beolvas 5;7>
* put an X to the entered coordinates


 */