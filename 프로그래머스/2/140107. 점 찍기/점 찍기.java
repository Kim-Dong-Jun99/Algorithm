import java.util.*;

class Solution {
    long answer;
    int k, d;
    public long solution(int k, int d) {
        init(k, d);
        solve();
        return answer;
    }
    
    void init(int k, int d) {
        answer = 0;
        this.k = k;
        this.d = d;
    }
    
    void solve() {
        long powered = (long) Math.pow(d, 2);
        for (int i = 0; i <= d; i += k) {
            long diff = powered - (long) Math.pow(i, 2);
            int sqrt = (int) Math.sqrt(diff) / k;
            answer += sqrt + 1;
        }
    }
}