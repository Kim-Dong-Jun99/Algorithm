import java.util.*

class Solution {
    var answer = intArrayOf()
    var n = 0
    var winCount = 0
    var dices = arrayOf<IntArray>()
    
    fun solution(dice: Array<IntArray>): IntArray {
        initialize(dice)
        solve()
        return answer
    }
    
    fun initialize(dice: Array<IntArray>) {
        n = dice.size
        answer = IntArray(n/2) {0}
        dices = dice
    }
    
    fun solve() {
        dfs(BooleanArray(n){false}, IntArray(n/2){0}, 0)
    }
    
    fun dfs(visited: BooleanArray, A: IntArray, index:Int) {
        if (index == n/2) {
            val B = createB(visited)
            simulate(A, B)
            return
        }
        if (index == 0) {
            for (i in 0 until n) {
                if (!visited[i]) {
                    A[index] = i
                    visited[i] = true
                    dfs(visited, A, index +1)
                    visited[i] = false
                }
            }
        } else {
            for (i in A[index-1]+1 until n) {
                if (!visited[i]) {
                    A[index] = i
                    visited[i] = true
                    dfs(visited, A, index +1)
                    visited[i] = false
                }
            }
        }
        
    }
    
    fun createB(visited: BooleanArray): IntArray {
        var index = 0;
        val B = IntArray(n/2) {0}
        for (i in 0 until n) {
            if (!visited[i]) {
                B[index] = i
                index ++
            }
        }
        return B
    }
    
    fun simulate(A: IntArray, B: IntArray) {
        val sumA = getSums(A)
        val sumB = getSums(B)
        sumB.sort()
        var tempWinCount = 0
        for (a in sumA) {
            tempWinCount += bst(a, sumB)
        }
        if (tempWinCount > winCount) {
            winCount = tempWinCount
            for (i in 0 until n/2) {
                answer[i] = A[i]+1
            }
        }
    }
    
    fun bst(a: Int, sumB: MutableList<Int>): Int {
        var left = 0
        var right = sumB.size - 1
        while (left <= right) {
            val mid = (left + right) / 2
            if (sumB.get(mid) >= a) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
        
    }
    
    fun getSums(arr: IntArray): MutableList<Int> {
        val sums = mutableListOf<Int>()
        dfsSum(sums, arr, 0, 0)
        return sums
    }
    
    fun dfsSum(sums: MutableList<Int>, arr: IntArray, index: Int, sum: Int) {
        if (index == n/2) {
            sums.add(sum)
            return
        }
        for (i in 0 until 6) {
            dfsSum(sums, arr, index + 1, sum + dices[arr[index]][i])
        }
    }
}