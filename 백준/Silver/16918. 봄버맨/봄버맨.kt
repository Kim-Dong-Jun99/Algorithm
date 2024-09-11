
val br = System.`in`.bufferedReader()

val dx = intArrayOf(0, 1, 0, -1)
val dy = intArrayOf(1, 0, -1, 0)

var inputArr = intArrayOf()
var r = 0
var c = 0
var n = 0
var board: Array<CharArray> = arrayOf()
var boomTime: Array<IntArray> = arrayOf()
fun main() {
    initialize()
    solve()
}

fun getInputArray(): IntArray {
    return br.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun initialize() {
    inputArr = getInputArray()
    r = inputArr[0]
    c = inputArr[1]
    n = inputArr[2]

    board = Array(r) { CharArray(c) {'.'} }
    boomTime = Array(r) { IntArray(c) {0} }
    for (i in 0 until r) {
        val inputStr = br.readLine()
        for (j in 0 until c) {
            board[i][j] = inputStr[j]
            if (board[i][j] == 'O') {
                boomTime[i][j] = 3
            }
        }
    }


}

fun solve() {
    for (time in 0..n) {
        when {
            time <= 1 -> continue
            time % 2 == 0 -> fillBoard(time)
            else -> boom(time)
        }
    }
    for (i in 0 until r) {
        for (j in 0 until c) {
            print("${board[i][j]}")
        }
        println()
    }
}

fun fillBoard(time: Int) {
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (board[i][j] != 'O') {
                board[i][j] = 'O'
                boomTime[i][j] = time+3
            }
        }
    }
}

fun boom(time: Int) {
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (boomTime[i][j] == time) {
                boomTime[i][j] = 0
                board[i][j] = '.'
                for (d in 0 until 4) {
                    val nx = i + dx[d]
                    val ny = j + dy[d]
                    if (isInner(nx, ny)) {
                        board[nx][ny] = '.'
                        boomTime[i][j] = 0
                    }
                }
            }
        }
    }
}

fun isInner(x: Int, y: Int): Boolean {
    return x in 0 until r && y in 0 until c
}
