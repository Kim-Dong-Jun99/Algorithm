class Solution {
    int[][] dp;
    int mod = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[n][m];
        for (int[] puddle : puddles) {
            dp[puddle[1]-1][puddle[0]-1] = -1;
        } 
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] != -1) {
                    if (i + 1 < n && dp[i+1][j] != -1) {
                        dp[i+1][j] += dp[i][j] % mod;
                    }
                    if (j + 1 < m && dp[i][j+1] != -1) {
                        dp[i][j+1] += dp[i][j] % mod;
                    }
                }
            }
        }
        return dp[n-1][m-1] % mod;
    }
}