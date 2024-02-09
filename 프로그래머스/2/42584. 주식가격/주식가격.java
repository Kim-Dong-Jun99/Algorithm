import java.util.*;

class Solution {
    int[] answer, prices;
    int n;
    PriorityQueue<Stock> stocks;
    
    public int[] solution(int[] prices) {
        init(prices);
        solve();
        return answer;
    }
    
    void init(int[] prices) {
        this.prices = prices;
        n = prices.length;
        answer = new int[n];
        stocks = new PriorityQueue<>(Stock::sortWithValue);
        initAnswer();
    }
    
    void initAnswer() {
        for (int i = 0; i < n; i++) {
            answer[i] = n - 1 - i;
        }
    }
    
    void solve() {
        for (int i = 0; i < n; i++) {
            while (!stocks.isEmpty() && stocks.peek().value > prices[i]) {
                Stock removed = stocks.remove();
                answer[removed.index] = i - removed.index;
            }
            stocks.add(new Stock(i, prices[i]));
        }
        
    }
    
    public static class Stock {
        int value;
        int index;
        
        public Stock(int index, int value) {
            this.index = index;
            this.value = value;
        }
        
        public int sortWithValue(Stock compare) {
            return Integer.compare(compare.value, this.value);
        }
    }
}