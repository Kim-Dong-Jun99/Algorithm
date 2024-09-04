var br = System.`in`.bufferedReader()

val digit: Array<String> = arrayOf(
    "1111101",
    "0011000",
    "0110111",
    "0011111",
    "1011010",
    "1001111",
    "1101111",
    "0011100",
    "1111111",
    "1011111"
)

fun main() {
    initialize()
}

fun getInputArray():IntArray {
    return br.readLine().split(" ").map { it.toInt() }.toIntArray()
}

fun initialize() {
    val inputArr = getInputArray()
    val n = inputArr[0]
    val k = inputArr[1]
    val p = inputArr[2]
    val x = inputArr[3]
    solve(n, k, p, x)
}

fun solve(n:Int, k: Int, p: Int, x: Int) {
    val currentLight = intToString(x, k)
    var answer = 0
    for (light in 1..n) {
       if (canChange(currentLight, intToString(light, k), p, k)) {
           answer += 1
       }
    }
    println(answer)
}

fun intToString(x: Int, k:Int):String {
    var str = ""
    for (i in 0 until k - x.toString().length) {
        str += "0"
    }
    str += x.toString()
    return str
}

fun canChange(currentLight:String, toChange:String, p:Int, k:Int):Boolean {
    var sum = 0
    for (i in 0 until k) {
       sum += convert(Character.getNumericValue(currentLight[i]), Character.getNumericValue(toChange[i]))
    }
    return sum in 1..p
}

fun convert(a:Int, b:Int):Int {
    val result = digit[a].toInt(2) xor digit[b].toInt(2)
    return Integer.toBinaryString(result).count() {it == '1'}
}