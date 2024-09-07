val br = System.`in`.bufferedReader()

var n = 0
var numbers:IntArray = intArrayOf()
fun main() {
    initialize()
    solve()
}

fun initialize() {
    n = br.readLine().toInt()
    numbers = br.readLine().split(" ").map {it.toInt()}.toIntArray()
}

fun solve() {
    val numberMap = mutableMapOf<Int,Int>()
    var answer:Long = 0
    var left = 0
    for (right in 0..n) {
        if (right == n) {
            while (left < n) {
                answer += right - left
                left += 1
            }
            break
        }
        val number = numbers[right]
        val count = numberMap.getOrDefault(number, 0)
        numberMap.put(number, count+1)
        if (numberMap.getOrDefault(number, 0) > 1) {
            while (true) {
                answer += right - left
                numberMap.put(numbers[left], numberMap.getOrDefault(numbers[left], 1) - 1)
                left += 1
                if (numberMap.getOrDefault(number, 1) <= 1) {
                    break
                }
            }
        }
    }
    println(answer)
}   
