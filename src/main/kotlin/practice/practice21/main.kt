package practice.practice21

fun main() {
    val picture: ArrayList<ArrayList<String>> = ArrayList()
    for (i in 1..10) {
        picture.add(ArrayList())
    }

    for (sor in picture) {
        for (i in 0..20) {
           // sor.add((i % 10).toString())
            sor.add(" ")
        }
    }

    picture[0][5] = "x"
    picture[0][6] = "x"
    picture[0][7] = "x"
    picture[0][13] = "x"
    picture[0][14] = "x"
    picture[0][15] = "x"
    picture[1][4] = "x"
    picture[1][8] = "x"
    picture[1][12] = "x"
    picture[1][16] = "x"
    picture[2][3] = "x"
    picture[2][9] = "x"
    picture[2][11] = "x"
    picture[2][17] = "x"
    picture[3][2] = "x"
    picture[3][3] = "x"
    picture[3][10] = "x"
    picture[3][17] = "x"
    picture[3][18] = "x"
    picture[4][2] = "x"
    picture[4][3] = "x"
    picture[4][17] = "x"
    picture[4][18] = "x"
    picture[5][3] = "x"
    picture[5][17] = "x"
    picture[6][4] = "x"
    picture[6][5] = "x"
    picture[6][15] = "x"
    picture[6][16] = "x"
    picture[7][6] = "x"
    picture[7][7] = "x"
    picture[7][13] = "x"
    picture[7][14] = "x"
    picture[8][8] = "x"
    picture[8][9] = "x"
    picture[8][11] = "x"
    picture[8][12] = "x"
    picture[9][10] = "x"

    for (sor in picture) {
        for (pixel in sor) {
            print(pixel)
        }
        println()
    }
}