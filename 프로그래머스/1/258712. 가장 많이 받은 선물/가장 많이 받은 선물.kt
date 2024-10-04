import java.util.*

class Solution {
    var friends = arrayOf<String>()
    var gifts = arrayOf<String>()
    var answer = 0
    var n = 0
    var friendMap = mutableMapOf<String, Int>()
    var giftMap = arrayOf<IntArray>()
    var taken = intArrayOf()
    var given = intArrayOf()
    
    
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        initialize(friends, gifts)
        solve()
        return answer
    }
    
    fun initialize(friends: Array<String>, gifts: Array<String>) {
        this.friends = friends
        this.gifts = gifts
        this.n = friends.size
        initFriends()
        initGifts()
    }
    
    fun initFriends() {
        friends.forEachIndexed { index, friend ->
            friendMap.put(friend, index)
        }
    }
    
    fun initGifts() {
        giftMap = Array(n) {IntArray(n) {0}}
        taken = IntArray(n) {0}
        given = IntArray(n) {0}
        gifts.forEach { gift ->
            val fromTo = gift.split(" ")
            val from = friendMap.get(fromTo[0]) ?: 0
            val to = friendMap.get(fromTo[1]) ?: 0
            giftMap[from][to] += 1
            given[from] += 1
            taken[to] += 1
            
        }
    }
    
    fun solve() {
       friends.forEachIndexed { i, friend ->
           var present = 0
           val from = friendMap.get(friend) ?: 0
           
           for (j in 0 until n) {
               if (i == j) {
                   continue
               }
               val to = friendMap.get(friends[j]) ?: 0
               if ((giftMap[from][to] != 0 || giftMap[to][from] != 0) && giftMap[from][to] != giftMap[to][from]) {
                   if (giftMap[from][to] > giftMap[to][from]) {
                       present += 1
                   }
               } else {
                   val fromValue = given[from] - taken[from]
                   val toValue = given[to] - taken[to]
                   if (fromValue > toValue) {
                       present += 1
                   }
               }
           }
           answer = Math.max(answer, present)
        }
    }
    
    fun getPresents(friend: String): Int {
        var present = 0
        
        return present
    }
}