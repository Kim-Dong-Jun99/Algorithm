import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
         int answer = 0;
        Arrays.sort(scoville);
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.add((long) i);
        }
        if (pq.peek() >= K) {
            return answer;
        }
        while (pq.size() >= 2) {
            Long polled = pq.remove();
            Long polled1 = pq.remove();
            pq.add(polled + polled1 * 2);
            answer += 1;
            if (pq.peek() >= K) {
                return answer;
            }
        }
        return -1;
    }
}