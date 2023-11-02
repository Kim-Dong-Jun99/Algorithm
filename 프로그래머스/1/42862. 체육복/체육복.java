import java.util.*;

/*

*/

class Solution {
    int[] remain;
    int n;
    public int solution(int n, int[] lost, int[] reserve) {
        init(n, lost, reserve);
        return solve();
    }
    void init(int n, int[] lost, int[] reserve) {
        remain = new int[n + 1];
        this.n = n;
        Arrays.fill(remain, 1);
        for (int l : lost) {
            remain[l] -= 1;
        }
        for (int r : reserve) {
            remain[r] += 1;
        }
    }

    int solve() {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (remain[i] >= 1) {
                result += 1;
            } else {
                if (i > 1 && remain[i - 1] == 2) {
                    remain[i - 1] = 1;
                    remain[i] = 1;
                    result += 1;
                } else if (i < n && remain[i + 1] == 2) {
                    remain[i + 1] = 1;
                    remain[i] = 1;
                    result += 1;
                }

            }


        }
        return result;
    }



}


