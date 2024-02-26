import java.util.*;

class Solution {
    int[] answer, numbers;
    int n;
    public int[] solution(int[] numbers) {
        init(numbers);
        solve();
        return answer;
    }
    
    void init(int[] numbers) {
        this.n = numbers.length;
        this.numbers = numbers;
        this.answer = new int[n];
        Arrays.fill(this.answer, -1);
    }
    
    void solve() {
        PriorityQueue<Number> heap = new PriorityQueue<>(Number::compareWithValue);
        for (int i = 0; i < n; i++) {
            while (!heap.isEmpty()) {
                if (heap.peek().value < numbers[i]) {
                    answer[heap.peek().index] = numbers[i];
                    heap.remove();
                } else {
                    break;
                }
            }
            heap.add(new Number(i, numbers[i]));
        }
    }
    
    static class Number {
        int index;
        int value;
        
        Number(int index, int value) {
            this.index = index;
            this.value = value;
        }
        
        int compareWithValue(Number compare) {
            return Integer.compare(this.value, compare.value);
        }
    }
}