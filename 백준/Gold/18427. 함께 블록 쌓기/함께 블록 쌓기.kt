val br = System.`in`.bufferedReader()

var inputArr = intArrayOf()
var n = 0
var m = 0
var h = 0
val mod = 10_007
var blocks: Array<IntArray> = arrayOf()
var dp: Array<IntArray> = arrayOf()

fun main() {
    initialize()
    solve()
}

fun initialize() {
    inputArr = getInputArray()
    n = inputArr[0]
    m = inputArr[1]
    h = inputArr[2]
    
    blocks = Array(n) { getInputArray()}
    dp = Array(n+1) { IntArray(h+1) {0}}
}

fun getInputArray(): IntArray {
    return br.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun solve() {
    dp[0][0] = 1
    for (i in 1..n) {
        val index = i-1
        for (height in 0..h) {
            for (block in blocks[index]) {
                if (height - block >= 0) {
                    dp[i][height] += dp[i-1][height-block] 
                    dp[i][height] %= mod
                }
            }
            dp[i][height] += dp[i-1][height]
            dp[i][height] %= mod

        }
    }
    println(dp[n][h] % mod)
}
