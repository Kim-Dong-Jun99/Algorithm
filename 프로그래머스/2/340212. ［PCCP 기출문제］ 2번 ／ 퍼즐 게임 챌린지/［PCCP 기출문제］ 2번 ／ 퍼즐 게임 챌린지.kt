import java.util.*

class Solution {
    var answer = 0
    var n = 0
    var diffs = intArrayOf()
    var times = intArrayOf()
    var limit = 0L
    
    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        initialize(diffs, times, limit)
        solve()
        return answer
    }
    
    fun initialize(diffs: IntArray, times: IntArray, limit: Long) {
        this.diffs = diffs
        this.times = times
        this.limit = limit
        this.n = diffs.size
    }
    
    fun solve() {
        var left = 1
        var right = 100_000
        while (left <= right) {
            val mid = (left + right) / 2
            if (possible(mid)) {
                answer = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
    }
    
    fun possible(level: Int): Boolean {
        var time = 0L
        for (i in 0 until n) {
            if (level >= diffs[i]) {
                 time += times[i]
            } else {
                val diff = diffs[i] - level
                time += times[i] 
                time += times[i] * diff
                if (i > 0) {
                    time += times[i-1]*diff
                }
            }
            if (time > limit) {
                return false
            }
        }
        return true
    }
}