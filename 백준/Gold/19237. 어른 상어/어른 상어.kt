val BR = System.`in`.bufferedReader()
val DX = intArrayOf(-1, 1, 0, 0)
val DY = intArrayOf(0, 0, -1, 1)

var inputArr = intArrayOf()
var N = 0
var M = 0
var k = 0
var board = arrayOf<IntArray>()
var sharks = arrayOf<Shark>()
var smell = arrayOf<Array<IntArray>>()

fun main() {
    initialize()
    solve()
}

fun initialize() {
    inputArr = getInputArray()
    N = inputArr[0]
    M = inputArr[1]
    k = inputArr[2]
    
    smell = Array(N) { Array(N) { IntArray(2) {-1} } }
    sharks = Array(M) { Shark(it,0,0,0,arrayOf(),false) }
    board = Array(N) { getAdjustedArray() }
    for (i in 0 until N) {
        for (j in 0 until N) {
            if (board[i][j] != -1) {
                val id = board[i][j]
                sharks[id].x = i
                sharks[id].y = j
                smell[i][j][0] = id
                smell[i][j][1] = k
            }
        }
    }

    inputArr = getAdjustedArray()
    for (i in 0 until M) {
        sharks[i].d = inputArr[i]
    }
    for (id in 0 until M) {
        sharks[id].directionPriority = Array(4) {getAdjustedArray()}
    }

}

fun getInputArray(): IntArray {
    return BR.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun getAdjustedArray(): IntArray {
    return BR.readLine().split(" ").map {it.toInt()-1}.toIntArray()
}


fun solve() {
    for (time in 1..1000) {
        moveSharks(time)
        val removeCount = removeSharks(time)
        if (removeCount == M-1) {
            println(time)
            return
        }
    }
    println(-1)
}

fun moveSharks(time: Int) {
    for (shark in sharks) {
        if (shark.isOut) {
            continue
        }
        var moved = false
        for (d in shark.directionPriority[shark.d]) {
            val nx = shark.x + DX[d]
            val ny = shark.y + DY[d]

            if (isInner(nx, ny) && smell[nx][ny][1] < time) {
                shark.x = nx
                shark.y = ny
                shark.d = d
                moved = true
                break
            }
        }
        if (moved) {
            continue
        }
        for (d in shark.directionPriority[shark.d]) {
            val nx = shark.x + DX[d]
            val ny = shark.y + DY[d]

            if (isInner(nx, ny) && smell[nx][ny][0] == shark.id) {
                shark.x = nx
                shark.y = ny
                shark.d = d
                break
            }
        }

    }
}

fun isInner(x: Int, y: Int): Boolean {
    return x in 0 until N && y in 0 until N
}

fun removeSharks(time: Int): Int {
    var removed = 0
    for (shark in sharks) {
        if (shark.isOut) {
            removed += 1
            continue
        }
        if (smell[shark.x][shark.y][1] > time && smell[shark.x][shark.y][0] != shark.id) {
            removed += 1
            shark.isOut = true
            continue
        }
        smell[shark.x][shark.y][0] = shark.id
        smell[shark.x][shark.y][1] = time + k
    }
    return removed
}

class Shark(val id: Int, var x: Int, var y: Int, var d: Int, var directionPriority: Array<IntArray>, var isOut:Boolean) {}
