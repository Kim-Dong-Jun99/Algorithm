val br = System.`in`.bufferedReader()

var inputArr = intArrayOf()
var h = 0
var w = 0
var x = 0
var y = 0
var b: Array<IntArray> = arrayOf()
var a: Array<IntArray> = arrayOf()

fun main() {
    initialize()
    solve()
}

fun getInputArray(): IntArray {
    return br.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun initialize() {
    inputArr = getInputArray()
    h = inputArr[0]
    w = inputArr[1]
    x = inputArr[2]
    y = inputArr[3]
    
    b = Array(h+x) { getInputArray()}
    a = Array(h) { IntArray(w) {0}}
}

fun isInnerA(i: Int, j: Int): Boolean {
    return i in 0 until h && j in 0 until w
}

fun isInnerB(i: Int, j: Int): Boolean {
    return i-x in 0 until h && j-y in 0 until w
}

fun solve() {
    for (i in 0 until h+x) {
        for (j in 0 until w+y) {
            if (isInnerA(i, j) && isInnerB(i, j)) {
                a[i][j] = b[i][j] - a[i-x][j-y]
            } else if (isInnerA(i, j) && !isInnerB(i, j)) {
                a[i][j] = b[i][j]
            } else if (!isInnerA(i, j) && isInnerB(i, j)) {
                a[i-x][j-y] = b[i][j]
            }
        }
    }
    
    for (arr in a) {
        for (i in arr) {
            print("$i ")
        }
        println()
    }
}
