import java.util.*

class Solution {
    var answer = 1
    var coin = 0
    var cards = intArrayOf()
    var n = 0
    var initCardSet = mutableSetOf<Int>()
    var cardSet = mutableSetOf<Int>()
    var index = 0
    var coinHeap = PriorityQueue<Int>()
    
    fun solution(c: Int, cds: IntArray): Int {
        initialize(c, cds)
        solve()
        return answer
    }
    
    fun initialize(c : Int, cds : IntArray) {
        coin = c
        cards = cds
        n = cds.size
        initializeCardSet()
    }
    
    fun initializeCardSet() {
        while (index < n/3) {
            initCardSet.add(cards[index])
            cardSet.add(cards[index])
            if (cardSet.contains(n+1 - cards[index])) {
                coinHeap.add(0)
            }
            index ++
        }
    }
    
    fun solve() {
        while (index + 2 <= n) {
            addCardToCardSet()
            addCardToCardSet()
            if (!canPay()) {
                break
            }
            answer ++
        }
    }
    
    fun addCardToCardSet() {
        cardSet.add(cards[index])
        if (cardSet.contains(n+1-cards[index])) {
            if (initCardSet.contains(n+1-cards[index])) {
                coinHeap.add(1)
            } else {
                coinHeap.add(2)
            }
        }
        index ++
    }
    
    fun canPay(): Boolean {
        if (coinHeap.isEmpty()) {
            return false
        }
        val mustGive = coinHeap.remove()
        if (mustGive > coin) {
            return false
        }
        coin -= mustGive
        return true
    }
    
}