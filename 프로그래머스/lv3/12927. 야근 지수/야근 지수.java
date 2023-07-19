

import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq= new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }
        boolean done = false;
        for (int i = 0; i < n; i++) {
            Integer removed = pq.remove();
            if (removed == 0) {
                done = true;
                break;
            }
            removed -= 1;
            pq.add(removed);
        }
        if (done) {
            return 0;
        }
        for (Integer integer : pq) {
            answer += Math.pow(integer, 2);
        }
        return answer;
    }
}
