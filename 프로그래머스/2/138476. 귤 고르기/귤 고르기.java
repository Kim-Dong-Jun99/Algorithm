import java.util.*;

/*
귤 k 개를 고를 때, 크기가 서로 다른 종류의 수의 최솟값을 구하기
*/

class Solution {
    int answer, k;
    HashMap<Integer, Integer> tangerineMap;
    PriorityQueue<Integer> heap;
    public int solution(int k, int[] tangerines) {
        init(k, tangerines);
        solve();
        return answer;
    }
    
    void init(int k, int[] tangerines) {
        this.k = k;
        tangerineMap = new HashMap<>();
        for (int tangerine : tangerines) {
            tangerineMap.putIfAbsent(tangerine, 0);
            tangerineMap.put(tangerine, tangerineMap.get(tangerine) + 1);
        }
        heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int key : tangerineMap.keySet()) {
            heap.add(tangerineMap.get(key));
        }
    }
    
    void solve() {
        int picked = 0;
        while (picked < k && !heap.isEmpty()) {
            answer += 1;
            picked += heap.remove();
        }
    }
    
}