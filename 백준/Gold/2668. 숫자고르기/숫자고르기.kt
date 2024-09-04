var br = System.`in`.bufferedReader()

fun main() {
    initialize()
}

fun initialize() {
    val n: Int = br.readLine().toInt()
    val chart = IntArray(n) { br.readLine().toInt()-1 }

    solve(n, chart)
}

fun solve(n: Int, chart: IntArray) {
    val visited = BooleanArray(n) { false }
    val result: MutableList<MutableList<Int>> = mutableListOf()
    for (i in 0 until n) {
        val list = mutableListOf<Int>()
        if (!visited[i] && dfs(i, list, chart, BooleanArray(n))) {
            result.add(list)
            for (l in list) {
                visited[l] = true
            }
        }
    }
    val answer = mutableListOf<Int>()
    for (l in result) {
        for (i in l) {
            answer.add(i)
        }
    }
    answer.sort()
    println(answer.size)
    for (a in answer) {
        println(a+1)
    }
}

fun dfs(cur:Int, list: MutableList<Int>, chart:IntArray, visited: BooleanArray):Boolean {
    list.add(cur)
    visited[cur] = true
    if (list[0] == chart[cur]) {
        return true
    } else if(visited[chart[cur]]) {
        return false
    }

    return dfs(chart[cur], list, chart, visited)
}