import java.util.*

class Solution {
    val answer = intArrayOf(0, 0)
    var nums = intArrayOf()
    val set = mutableSetOf<Int>()
    val map = mutableMapOf<Int, MutableList<Int>>()
    var target = 0

    fun twoSum(nums: IntArray, target: Int): IntArray {
        initialize(nums, target)
        solve()
        return answer   
    }

    fun initialize(nums: IntArray, target: Int) {
        this.nums = nums
        this.target = target
    }

    fun solve() {
        nums.forEachIndexed {i, num -> 
            set.add(num)
            val list = map.getOrDefault(num, mutableListOf<Int>())
            list.add(i)
            map[num] = list
        }
        nums.forEachIndexed {i, num -> 
            if (set.contains(target - num)) {
                if (target % 2 == 0 && num  == target/2) {
                    if (map[num]!!.size > 1) {
                        val list = map[num]
                        answer[0] = Math.min(list!![0], list!![1])
                        answer[1] = Math.max(list!![0], list!![1])
                        return
                    }
                    
                } else {
                    val list = map.get(target - num)
                    answer[0] = Math.min(list!![0], i)
                    answer[1] = Math.max(list!![0], i)
                    return
                }
                
            }
        }
    }

}