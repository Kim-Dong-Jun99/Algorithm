val br = System.`in`.bufferedReader()

var inputArr = intArrayOf()
var n = 0
var k = 0
var exercise = intArrayOf()
fun main() {
    initialize()
    solve()
}

fun initialize() {
    inputArr = getInputArray()
    n = inputArr[0]
    k = inputArr[1]
    exercise = getInputArray()
}

fun getInputArray(): IntArray {
    return br.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun solve() {
    println(dfs(0, 0, BooleanArray(n) {false}))
}

fun dfs(index: Int, weight: Int, visited:BooleanArray): Int {
    if (weight < 0) {
        return 0
    }
    if (index == n) {
        return 1
    }
    var result = 0
    for ((i, w) in exercise.withIndex()) {
        if (!visited[i]) {
            visited[i] = true
            result += dfs(index+1, weight + w - k, visited)
            visited[i] = false
        }
    }
    return result
}