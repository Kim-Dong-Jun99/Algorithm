import kotlin.math.*

fun main() {
    val testCase: Int = readln().toInt()
    for (tc in 1..testCase) {
        solve()
    }
}

fun solve() {
    val W: String = readln()
    val K: Int = readln().toInt()
    val charCount = mutableMapOf<Char,  MutableList<Int>>()
    var maxCount: Int = 0
    
    for (i in W.indices) {
        val c: Char = W[i]
        charCount.addIndex(c, i)
        maxCount = max(maxCount, charCount[c]?.size ?: 0)
    }
    
    if (maxCount < K) {
        println(-1)
        return
    }
    getMinAndMaxLength(charCount, W.length, K)
}

fun getMinAndMaxLength(charCount : MutableMap<Char, MutableList<Int>>, strLength: Int, K: Int) {
    var minLength: Int = strLength
    var maxLength: Int = 0
    for ((k, v) in charCount) {
        if (v.size < K) {
            continue
        }
        val deque = ArrayDeque<Int>()
        for (index in v) {
            if (deque.size < K) {
                deque.add(index)
            } else {
                minLength = min(minLength, deque.last() - deque.first() + 1)
                maxLength = max(maxLength, deque.last() - deque.first() + 1)
                deque.removeFirst()
                deque.add(index)
            }
        }
        minLength = min(minLength, deque.last() - deque.first() + 1)
        maxLength = max(maxLength, deque.last() - deque.first() + 1)
    }
    println("$minLength $maxLength")
}

fun MutableMap<Char, MutableList<Int>>.addIndex(c: Char, i: Int) {
    if (c in this) {
        this[c]?.add(i)
    } else {
        this[c] = mutableListOf<Int>(i)
    }
}
