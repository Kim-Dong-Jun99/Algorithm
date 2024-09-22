import java.util.*
import kotlin.math.*

val BR = System.`in`.bufferedReader()

val DX = intArrayOf(-1, 0, 1, 0)
val DY = intArrayOf(0, 1, 0, -1)

var inputArr = intArrayOf()
var R = 0
var C = 0
var K = 0
var answer = 0
var golems = arrayOf<Golem>()
var board = arrayOf<IntArray>()

fun main() {
    initialize()
    solve()
}

fun getInputArray(): IntArray {
    return BR.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun initialize() {
    inputArr = getInputArray()
    R = inputArr[0]
    C = inputArr[1]
    K = inputArr[2]

    board = Array(R) { IntArray(C) {-1}}
    golems = Array<Golem>(K) {
        inputArr = getInputArray()
        Golem(it, Position(-2, inputArr[0]-1), inputArr[1])
    }
}

fun solve() {
    for (golem in golems) {
        moveGolem(golem)
        if (!isInner(golem.p.x, golem.p.y)) {
            resetBoard()
            continue
        }
        markGolemToBoard(golem)
        travelGolem(golem)
    }
    println(answer)
}

fun moveGolem(g: Golem) {
    while (true) {
        if (moveDown(g)) {
            continue
        }
        if (moveLeft(g)) {
            continue
        }
        if (moveRight(g)) {
            continue
        }
        break
    }
}

fun canGo(x: Int, y: Int): Boolean {
    if (isInner(x, y)) {
        return board[x][y] == -1
    } else {
        return (y in 0 until C && x < R)
    }
}

fun canGoDown(x: Int, y: Int): Boolean {
    val lx = x
    val ly = y - 1
    val rx = x
    val ry = y + 1
    val dx = x + 1
    val dy = y
    
    if (canGo(lx+1, ly) && canGo(rx+1, ry) && canGo(dx+1, dy)) {
        return true
    }
    return false
} 

fun canGoLeft(x: Int, y: Int): Boolean {
    val lx = x
    val ly = y - 1
    val dx = x + 1
    val dy = y
    val ux = x - 1
    val uy = y
    
    if (canGo(lx, ly-1) && canGo(dx, dy-1) && canGo(ux, uy-1)) {
        return true
    }
    return false
}

fun canGoRight(x: Int, y: Int): Boolean {
    val rx = x
    val ry = y + 1
    val dx = x + 1
    val dy = y
    val ux = x - 1
    val uy = y

    if (canGo(rx, ry+1) && canGo(dx, dy+1) && canGo(ux, uy+1)) {
        return true
    }
    return false
}

fun moveDown(g: Golem): Boolean {
    if (canGoDown(g.p.x, g.p.y)) {
        g.p.x += 1
        return true
    }
    return false
}

fun moveLeft(g: Golem): Boolean {
    if (canGoLeft(g.p.x, g.p.y) && canGoDown(g.p.x, g.p.y-1)) {
        g.p.x += 1
        g.p.y -= 1
        g.d = ccw(g.d)
        return true
    }
    return false
}

fun moveRight(g: Golem): Boolean {
    if (canGoRight(g.p.x, g.p.y) && canGoDown(g.p.x, g.p.y+1)) {
        g.p.x += 1
        g.p.y += 1
        g.d = cw(g.d)
        return true
    }
    return false
}

fun cw(d: Int): Int {
    return (d + 1) % 4
}

fun ccw(d: Int): Int {
    return (d + 3) % 4
}

fun isInner(x: Int, y: Int): Boolean {
    return x in 0 until R && y in 0 until C
}

fun resetBoard() {
    board = Array(R) { IntArray(C) {-1}}
}

fun markGolemToBoard(g: Golem) {
    board[g.p.x][g.p.y] = g.id
    for (d in 0 until 4) {
        board[g.p.x + DX[d]][g.p.y + DY[d]] = g.id
    }
}

fun travelGolem(g: Golem) {
    val visited = BooleanArray(K) {false}
    var lowest = 0
    val q = LinkedList<Int>()
    q.add(g.id)
    visited[g.id] = true
    while (!q.isEmpty()) {
        val cur = golems[q.remove()]
        lowest = max(cur.p.x+2, lowest)
        val ex = cur.p.x + DX[cur.d]
        val ey = cur.p.y + DY[cur.d]
        for (d in 0 until 4) {
            val nx = ex + DX[d]
            val ny = ey + DY[d]
            if (isInner(nx, ny) && board[nx][ny] >= 0 && !visited[board[nx][ny]]) {
                visited[board[nx][ny]] = true
                q.add(board[nx][ny])
            }
        }
    }
    answer += lowest
}

class Golem(val id: Int, val p: Position, var d: Int) {}

class Position(var x: Int, var y: Int) {}
