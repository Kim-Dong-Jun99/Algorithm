import kotlin.math.*
import java.util.*

val DX = intArrayOf(0, 1, 0, -1)
val DY = intArrayOf(1, 0, -1, 0)
val BR = System.`in`.bufferedReader()

var inputArr = intArrayOf()
var N = 0
var Q = 0
var M = 0
var ices = arrayOf<IntArray>()
var steps = intArrayOf()

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
    Q = inputArr[1]
    M = 2.toDouble().pow(N.toDouble()).toInt()
    
    ices = Array(M) { getInputArray() }
    steps = getInputArray()
}

fun solve() {
    for (step in steps) {
        spinIces(step)
        meltIce()
    }
    printIceSum()
    printBiggestIce()
}

fun spinIces(step: Int) {
    var newIces = Array(M) { IntArray(M) {0} }
    val L = 2.toDouble().pow(step.toDouble()).toInt()
    
    for (i in 0 until M step(L)) {
        for (j in 0 until M step(L)) {
            spinIce(i, j, L, newIces)
        }
    }

    ices = newIces
}

fun spinIce(x: Int, y: Int, L: Int, newIces: Array<IntArray>) {
    /*
     * 0, 0 -> 0, L-1
     * 0, L-1 -> L-1, L-1
     * L-1, L-1 -> L-1, 0
     * L-1, 0 -> 0, 0
     * formula = x,y -> y, abs(L-1-x)
     */
    for (i in 0 until L) {
        for (j in 0 until L) {
            newIces[j+x][abs(L-1-i)+y] = ices[i+x][j+y] 
        }
    }
}

fun meltIce() {
    val q: Queue<Position> = LinkedList()
    for (i in 0 until M) {
        for (j in 0 until M) {
            if (ices[i][j] == 0) {
                continue
            }
            var iceCount = 0
            for (d in 0 until 4) {
                val nx = i + DX[d]
                val ny = j + DY[d]

                if (isInner(nx, ny) && ices[nx][ny] > 0) {
                    iceCount += 1
                }
            }
            if (iceCount < 3) {
               q.add(Position(i, j)) 
            }
        }
    }
    
    while (!q.isEmpty()) {
        val p = q.remove()
        ices[p.x][p.y] -= 1
    }
}

fun isInner(x: Int, y: Int): Boolean {
    return x in 0 until M && y in 0 until M
}

fun printIceSum() {
    var sum = 0
    for (iceArr in ices) {
        for (ice in iceArr) {
            sum += ice
        }
    }
    println(sum)
}

fun printBiggestIce() {
    val visited = Array(M) { BooleanArray(M) {false} }
    var maxSize = 0

    for (i in 0 until M) {
        for (j in 0 until M) {
            if (visited[i][j]) {
                continue
            }
            if (ices[i][j] == 0) {
                continue
            }
            maxSize = max(maxSize, findIces(i, j, visited))
        }
    }

    println(maxSize)
}

fun findIces(x: Int, y: Int, visited: Array<BooleanArray>): Int {
    var size = 0
    val q: Queue<Position> = LinkedList()
    visited[x][y] = true
    q.add(Position(x, y)) 

    while (!q.isEmpty()) {
        size += 1
        val p = q.remove()
        for (d in 0 until 4) {
            val nx = p.x + DX[d]
            val ny = p.y + DY[d]

            if (isInner(nx, ny) && ices[nx][ny] > 0 && !visited[nx][ny]) {
                visited[nx][ny] = true
                q.add(Position(nx, ny))
            }
        }
    }
    return size
}

class Position(val x: Int, val y: Int) {}
