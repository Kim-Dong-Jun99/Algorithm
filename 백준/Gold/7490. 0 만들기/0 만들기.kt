import java.util.*
val br = System.`in`.bufferedReader()

var TC = 0
var N = 0
val OPERATORS: CharArray = charArrayOf('+', '-', ' ')
var numbers = intArrayOf()

fun main() {
    testCase()
}

fun testCase() {
    TC = br.readLine().toInt()
    for (t in 1..TC) {
        initialize()
        solve()
        if (t != TC) {
            println()
        }
    }
}

fun initialize() {
    N = br.readLine().toInt()
}

fun solve() {
    numbers = IntArray(N) {it+1}
    val resultList: MutableList<String> = mutableListOf()
    dfs(0, CharArray(N-1) { ' ' }, resultList)
    resultList.sort()
    for (result in resultList) {
        println(result)
    }
}

fun dfs(index: Int, formula: CharArray, resultList: MutableList<String>) {
    if (index == N-1) {
        if (isValid(formula)) {
            resultList.add(formulaToString(formula))
        }
        return
    }
    for (operator in OPERATORS) {
        formula[index] = operator
        dfs(index + 1, formula, resultList)
    } 
}

fun isValid(formula: CharArray): Boolean {
    val stack:Stack<Int> = Stack()
    var numberList: MutableList<Int> = mutableListOf()
    var formulaList: MutableList<Char> = mutableListOf()
    for (i in formula.indices) {
        if (formula[i] == ' ') {
            if (i == 0 || formula[i-1] != ' ') {
                numberList.add((numbers[i].toString() + numbers[i+1].toString()).toInt())
            } else {
                val last = numberList[numberList.lastIndex]
                numberList.removeAt(numberList.lastIndex)
                numberList.add((last.toString() + numbers[i+1].toString()).toInt())

            }
        } else {
            formulaList.add(formula[i])
            if (i == 0 || (i - 1 >= 0 && formula[i-1] != ' ')) {
                numberList.add(numbers[i])
            }
        }
    }
    if (formula[formula.lastIndex] != ' ') {
        numberList.add(numbers[numbers.lastIndex])
    }

    if (formulaList.size == 0) {
        return false
    }
    for (i in formulaList.indices) {
        var left = 0
        var right = numberList[i+1]
        if (stack.size == 0) {
            left = numberList[i]
        } else {
            left = stack.pop()
        }
        when (formulaList[i]) {
            '+' -> stack.push(left + right)
            '-' -> stack.push(left - right)
        }
    }
    return stack.pop() == 0
}

fun formulaToString(formula: CharArray): String {
    var result = ""
    for (i in 0..<N) {
        result += numbers[i]
        if (i != N-1) {
            result += formula[i]
        }
    }
    return result
}
