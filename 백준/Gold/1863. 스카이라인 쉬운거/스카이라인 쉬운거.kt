import java.util.*

val br = System.`in`.bufferedReader()

var n = 0
var skyLines:Array<IntArray> = arrayOf()

fun main() {
    initialize()
    solve()
}

fun initialize() {
    n = br.readLine().toInt()
    skyLines = Array<IntArray>(n) { br.readLine().split(" ").map { it.toInt() }.toIntArray() }
}

fun solve() {
    var count = 0
    val stack = Stack<Int>()
    for ((x, y) in skyLines) {
        while (stack.size > 0 && stack.peek() >= y) {
            if (stack.peek() > y) {
                count += 1
            }
            stack.pop()
        }
        if (y == 0) {
            continue
        }
        stack.push(y)
    }
    count += stack.size
    println(count)
}
