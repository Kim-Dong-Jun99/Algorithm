import kotlin.math.*

val br = System.`in`.bufferedReader()

val dx = intArrayOf(0, 1, 0, -1)
val dy = intArrayOf(1, 0, -1, 0)

val shapes: Array<IntArray> = arrayOf(
    intArrayOf(0, 1),
    intArrayOf(1, 2),
    intArrayOf(2, 3),
    intArrayOf(3, 0)
)

var inputArr = intArrayOf()
var n = 0
var m = 0
var trees: Array<IntArray> = arrayOf()

fun main() {
    initialize()
    solve()
}

fun initialize() {
    inputArr = getInputArray()
    n = inputArr[0]
    m = inputArr[1]
    
    trees = Array(n) { getInputArray() }
}

fun getInputArray(): IntArray {
    return br.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun solve() {
    println(dfs(0, Array(n) { BooleanArray(m) { false }}))
}

fun dfs(index: Int, visited: Array<BooleanArray>): Int {
    if (index == n*m) {
        return 0
    }
    var result = 0
    for (i in index until n*m) {
        val x = i / m
        val y = i % m
        if (!visited[x][y]) {
            for (s in shapes.indices) {
                if (canMakeShape(x, y, s, visited)) {
                    markTree(x, y, s, visited)
                    result = max(result, calcValue(x, y, s) + dfs(i + 1, visited))
                    unmarkTree(x, y, s, visited)
                }
            }
        }
    }
    return result
}

fun markTree(x: Int, y:Int, s:Int, visited: Array<BooleanArray>) {
    val d1 = shapes[s][0]
    val d2 = shapes[s][1]
    
    visited[x][y] = true
    visited[x+dx[d1]][y+dy[d1]] = true
    visited[x+dx[d2]][y+dy[d2]] = true
}

fun unmarkTree(x: Int, y:Int, s:Int, visited: Array<BooleanArray>) {
    val d1 = shapes[s][0]
    val d2 = shapes[s][1]
    
    visited[x][y] = false
    visited[x+dx[d1]][y+dy[d1]] = false
    visited[x+dx[d2]][y+dy[d2]] = false
}

fun calcValue(x: Int, y:Int, s:Int): Int {
    val d1 = shapes[s][0]
    val d2 = shapes[s][1]
    
    return trees[x][y] * 2 + trees[x+dx[d1]][y+dy[d1]] + trees[x+dx[d2]][y+dy[d2]]
}

fun canMakeShape(x: Int, y: Int, s:Int, visited: Array<BooleanArray>): Boolean {
    val d1 = shapes[s][0]
    val d2 = shapes[s][1]
    
    return isInner(x+dx[d1], y+dy[d1]) && !visited[x+dx[d1]][y+dy[d1]] 
    && isInner(x+dx[d2], y+dy[d2]) && !visited[x+dx[d2]][y+dy[d2]]
}

fun isInner(x: Int, y: Int): Boolean {
    return x in 0 until n && y in 0 until m
}
