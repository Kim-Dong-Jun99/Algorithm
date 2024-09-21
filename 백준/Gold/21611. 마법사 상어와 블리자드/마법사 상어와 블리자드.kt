import java.util.*

val BR = System.`in`.bufferedReader()

val DX = intArrayOf(0, 1, 0, -1)
val DY = intArrayOf(1, 0, -1, 0)
val directionMap = intArrayOf(0, 3, 1, 2, 0)

var one = 0
var two = 0
var three = 0
var inputArr = intArrayOf()
var N = 0
var M = 0
var board = arrayOf<IntArray>()
var magics = arrayOf<IntArray>()
val positions = mutableListOf<Position>()
var deque = ArrayDeque<Int>()

fun main() {
    initialize()
    solve()
}

fun getInputArray(): IntArray {
    return BR.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun initialize() {
    inputArr = getInputArray()
    N = inputArr[0]
    M = inputArr[1]


    board = Array(N) {getInputArray()}
    magics = Array(M) {getInputArray()} 
    initPositions()
}

fun initPositions() {
    val rotate = Array(N) { BooleanArray(N) {false} }
    val dx = intArrayOf(1, 1, -1, -1)
    val dy = intArrayOf(1, -1, 1, -1)

    for (d in 0 until 4) {
        var x = N/2
        var y = N/2
        if (d == 3) {
            x += 1
        }
        for (i in 1..N/2) {
            x += dx[d]
            y += dy[d]
            rotate[x][y] = true
        }
    }

    var curX = N/2
    var curY = N/2
    var curD = 2
    while (true) {
        if (rotate[curX][curY]) {
            curD = (curD + 3) % 4
        }
        curX += DX[curD]
        curY += DY[curD]
        if (curX in 0 until N && curY in 0 until N) {
            positions.add(Position(curX, curY))
        } else {
            break
        }
    }
}

fun solve() {
    for (magic in magics) {
        val d = directionMap[magic[0]]
        val s = magic[1]
        throwIce(d, s)
        marbleExplode()
        addMarbles()
    }
    println(one + 2*two + 3*three)
}

fun throwIce(d: Int, s: Int) {
    var x = N/2
    var y = N/2
    for (i in 1..s) {
        x += DX[d]
        y += DY[d]
        board[x][y] = 0
    }
}

fun marbleExplode() {
    deque = ArrayDeque<Int>()
    for (p in positions) {
        if (board[p.x][p.y] == 0) {
            continue
        }
        deque.addLast(board[p.x][p.y])
        board[p.x][p.y] = 0
    }
    explode()
}

fun explode() {
    while (!deque.isEmpty()) {
        var exploded = false

        var cur = deque.first
        var count = 0
        val newDeque = ArrayDeque<Int>()
        while (!deque.isEmpty()) {
            val removed = deque.removeFirst()
            if (cur != removed) {
                if (count < 4) {
                    for (i in 0 until count) {
                        newDeque.addLast(cur)
                    }
                } else {
                    exploded = true
                    if (cur == 1) {
                        one += count
                    } else if (cur == 2) {
                        two += count
                    } else {
                        three += count
                    }
                }
                cur = removed
                count = 1
            } else {
                count += 1
            }
        }
        if (count < 4) {
            for (i in 0 until count) {
                newDeque.addLast(cur)
            }
        } else {
            exploded = true
            if (cur == 1) {
                one += count
            } else if (cur == 2) {
                two += count
            } else {
                three += count
            }
        }
        deque = newDeque
        if (!exploded) {
            break
        }
    }

}

fun addMarbles() {
    if (deque.isEmpty()) {
        return
    }
    val newMarbles = mutableListOf<Int>()
    var cur = deque.first
    var count = 0
    while (!deque.isEmpty()) {
        val removed = deque.removeFirst()
        if (cur == removed) {
            count += 1
        } else {
            newMarbles.add(count)
            newMarbles.add(cur)
            cur = removed
            count = 1
        }
    }
    newMarbles.add(count)
    newMarbles.add(cur)

    for ((i, m) in newMarbles.withIndex()) {
        if (i > positions.lastIndex) {
            break
        }
        board[positions[i].x][positions[i].y] = m
    }
}

class Position(val x: Int, val y: Int) {}
