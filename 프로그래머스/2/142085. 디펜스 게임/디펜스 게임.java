import java.util.*;

class Solution {
    int answer, n, k;
    int[] enemy;
    public int solution(int n, int k, int[] enemy) {
        init(n, k, enemy);
        solve();
        return answer;
    }
    
    void init(int n, int k, int[] enemy) {
        this.n = n;
        this.k = k;
        this.enemy = enemy;
        this.answer = 0;
    }
    
    void solve() {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        long subSum = 0l;
        while (answer < enemy.length) {
            subSum += enemy[answer];
            heap.add(enemy[answer]);
            if (subSum > n) {
                while (k > 0 && subSum > n) {
                    int removed = heap.remove();
                    subSum -= removed;
                    k -= 1;
                }
                if (subSum > n) {
                    break;
                }
                
            }
            answer += 1;
            
        }
    }
}