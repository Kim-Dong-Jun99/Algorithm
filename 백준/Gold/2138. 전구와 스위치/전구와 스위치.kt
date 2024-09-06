import java.util.*

val br = System.`in`.bufferedReader()

var N = 0
var before: IntArray = intArrayOf()
var after: IntArray = intArrayOf()
var originalBefore: IntArray = intArrayOf()

fun main() {
    initialize()
    solve()
}

fun initialize() {
    N = br.readLine().toInt()
    originalBefore = strToArr()
    after = strToArr()
}

fun strToArr(): IntArray {
    val str = br.readLine()
    var arr = IntArray(N)
    for (i in 0 until N) {
        arr[i] = Character.getNumericValue(str[i])
    }
    return arr
}

fun copyBefore() {
    before = IntArray(N)
    for (i in 0 until N) {
        before[i] = originalBefore[i]
    }
}

fun solve() {
    var cnt = 0
    copyBefore()
    for (i in 1 until N) {
        if (before[i-1] != after[i-1]) {
            cnt += 1
            before[i] = before[i].convert()
            before[i-1] = before[i-1].convert()
            if (i != N - 1) {
                before[i+1] = before[i+1].convert()
            } 

        } 
        if (equalsArr()) {
            println(cnt)
            return
        }
    }

    cnt = 1
    copyBefore()
    before[0] = before[0].convert()
    before[1] = before[1].convert()

    for (i in 1 until N) {
        if (before[i-1] != after[i-1]) {
            cnt += 1
            before[i] = before[i].convert()
            before[i-1] = before[i-1].convert()
            if (i != N-1) {
                before[i+1] = before[i+1].convert()
            }
        } 
        if (equalsArr()) {
            println(cnt)
            return 
        }
        
    }
    println(-1)
}

fun Int.convert(): Int {
    if (this == 0) {
        return 1
    }
    return 0
}

fun equalsArr():Boolean {
    for (i in before.indices) {
        if (before[i] != after[i]) {
            return false
        }
    }
    return true
}
