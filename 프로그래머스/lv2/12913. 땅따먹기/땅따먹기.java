import java.util.*;
class Solution {
    int solution(int[][] land) {
        int N = land.length;
        int[][] dp = new int[N][4];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 0);

        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                dp[i][j] += land[i][j];
                if (i + 1 < N) {
                    for (int k = 0; k < 4; k++) {
                        if (j != k) {
                            dp[i + 1][k] = Math.max(dp[i + 1][k], dp[i][j]);
                        }
                    }
                }

            }
        }
        return Arrays.stream(dp[N - 1]).max().getAsInt();
    }
}