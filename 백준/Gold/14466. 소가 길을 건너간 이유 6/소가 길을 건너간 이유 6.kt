import java.util.*
val br = System.`in`.bufferedReader()

val dx = arrayOf<Int>(0, 1, 0, -1)
val dy = arrayOf<Int>(1, 0, -1, 0 )

var n = 0
var k = 0
var r = 0
var inputArr = intArrayOf()
var canGo: Array<Array<BooleanArray>> = arrayOf()
var cows: Array<IntArray> = arrayOf()
var isCow: Array<BooleanArray> = arrayOf()

fun main() {
    initialize()
    solve()
}

fun getInputArray(): IntArray {
    return br.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun initialize() {
    inputArr = getInputArray()
    n = inputArr[0]
    k = inputArr[1]
    r = inputArr[2]

    canGo = Array(n+1) { Array(n+1) { BooleanArray(4) {true} } }
    for (i in 1..r) {
        inputArr = getInputArray()
        val ax = inputArr[0]
        val ay = inputArr[1]
        val bx = inputArr[2]
        val by = inputArr[3]

        canGo[ax][ay][getDirection(ax, ay, bx, by)] = false
        canGo[bx][by][getDirection(bx, by, ax, ay)] = false
    }
    isCow = Array(n+1) { BooleanArray(n+1) {false} }
    cows = Array(k) { 
        inputArr = getInputArray()
        isCow[inputArr[0]][inputArr[1]] = true
        inputArr
    }
}

fun solve() {
    var answer = 0
    for (cow in cows) {
        val q = LinkedList<Position>()
        val visited = Array(n+1) { BooleanArray(n+1) {false} }
        q.add(Position(cow[0], cow[1]))
        visited[cow[0]][cow[1]] = true
        var cantGo = k-1
        while (!q.isEmpty()) {
            val p: Position = q.remove()
            for (d in 0 until 4) {
                val nx = p.x + dx[d]
                val ny = p.y + dy[d]
                if (isInner(nx, ny) && canGo[p.x][p.y][d] && !visited[nx][ny]) {
                    visited[nx][ny] = true
                    q.add(Position(nx, ny))
                    if (isCow[nx][ny]) {
                        cantGo -= 1
                    }
                }
            }
        }
        answer += cantGo
    }
    println(answer/2)
}

fun isInner(x: Int, y: Int): Boolean {
    return x in 1..n && y in 1..n
}

fun getDirection(fromX:Int, fromY:Int, toX:Int, toY:Int): Int {
    for (d in 0 until 4) {
        if (fromX + dx[d] == toX && fromY + dy[d] == toY) {
            return d
        }
    }
    return -1
}

class Position(val x: Int, val y: Int) {}
