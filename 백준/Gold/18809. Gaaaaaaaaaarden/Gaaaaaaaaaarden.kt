import java.util.*
import kotlin.math.*

val br = System.`in`.bufferedReader()

val DX = intArrayOf(0, 1, 0, -1)
val DY = intArrayOf(1, 0, -1, 0)
val LAKE = 0
val WALL = 1 
val EMPTY = 2

var inputArr = intArrayOf()
var n = 0
var m = 0
var g = 0
var r = 0
var garden: Array<IntArray> = arrayOf()
val emptyPositions: MutableList<Position> = mutableListOf()
var board: Array<CharArray> = arrayOf()
var isFlower: Array<BooleanArray> = arrayOf()

fun main() {
    initialize()
    solve()
}

fun initialize() {
    inputArr = getInputArray()
    n = inputArr[0]
    m = inputArr[1]
    g = inputArr[2]
    r = inputArr[3]
    
    garden = Array(n) { getInputArray() }
    initEmptyPosition()
}

fun initEmptyPosition() {
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (garden[i][j] == EMPTY) {
                emptyPositions.add(Position(i, j))
            }
        }
    }
}

fun getInputArray(): IntArray {
    return br.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun solve() {
    println(dfs(0, Stack<Position>(), Stack<Position>()))
}

fun dfs(index: Int, green: Stack<Position>, red: Stack<Position>): Int {
    if (red.size + green.size + emptyPositions.size - index < r + g) {
        return 0
    }
    if (index == emptyPositions.size) {
        return getFlowers(green, red)
    }
    var result = 0
    result = max(result, dfs(index + 1, green, red))
    if (green.size < g) {
        green.push(emptyPositions[index])
        result = max(result, dfs(index+1, green, red))
        green.pop()
    }
    if (red.size < r) {
        red.push(emptyPositions[index])
        result = max(result, dfs(index+1, green, red))
        red.pop()
    }
    return result
}

fun getFlowers(green: Stack<Position>, red: Stack<Position>): Int {
    var curGreen = mutableListOf<Position>()
    var curRed = mutableListOf<Position>()
    
    board = Array(n) { CharArray(m) {'.'}}
    isFlower = Array(n) { BooleanArray(m) {false}}
    for (p in green) {
        curGreen.add(p)
        board[p.x][p.y] = 'g'
    }
    for (p in red) {
        curRed.add(p)
        board[p.x][p.y] = 'r'
    }
    var nx = 0
    var ny = 0
    var flowers = 0
    while (!curGreen.isEmpty() || !curRed.isEmpty()) {
        var tempGreen = mutableListOf<Position>()
        var tempRed = mutableListOf<Position>()
        
        for (p in curRed) {
            for (d in 0 until 4) {
                nx = p.x + DX[d]
                ny = p.y + DY[d]
                if (isInner(nx, ny) && garden[nx][ny] != LAKE && board[nx][ny] == '.' && !isFlower[nx][ny]) {
                    if (canBeFlower(nx, ny)) {
                        isFlower[nx][ny] = true
                        flowers += 1
                    } else {
                        board[nx][ny] = 'r'
                        tempRed.add(Position(nx, ny))
                    }
                }
            }
        }
        
        for (p in curGreen) {
            for (d in 0 until 4) {
                nx = p.x + DX[d]
                ny = p.y + DY[d]
                if (isInner(nx, ny) && garden[nx][ny] != LAKE && board[nx][ny] == '.' && !isFlower[nx][ny]) {
                    board[nx][ny] = 'g'
                    tempGreen.add(Position(nx, ny))
                }
            }
        }
        
        curGreen = tempGreen
        curRed = tempRed
    }
    return flowers
    
}

fun canBeFlower(x: Int, y:Int):Boolean {
    for (d in 0 until 4) {
        val nx = x+DX[d]
        val ny = y+DY[d]
        if (isInner(nx, ny) && board[nx][ny] == 'g') {
            return true
        }
    }
    return false
}

fun isInner(x: Int, y: Int): Boolean {
    return x in 0 until n && y in 0 until m
}

class Position(val x: Int, val y: Int) {}