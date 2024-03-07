import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N;
    int[] S;
    int[][] dp;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        S = getInputArray();
        dp = new int[N][2];
    }

    void solve() throws IOException {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                answer = Math.max(answer, dfs(i, 1));
            } else if (i == N-1) {
                answer = Math.max(answer, dfs(i, 0));
            } else {
                answer = Math.max(answer, dfs(i, 0) + dfs(i, 1) - 1);
            }
        }
        System.out.println(answer);
    }

    int dfs(int index, int direction) {
        if (dp[index][direction] != 0) {
            return dp[index][direction];
        }
        int result = 1;
        int maxSubResult = 0;
        if (direction == 1) {
            for (int i = index + 1; i < N; i++) {
                if (S[i] < S[index]) {
                    maxSubResult = Math.max(maxSubResult, dfs(i, direction));
                }
            }
        } else {
            for (int i = index - 1; i > -1; i--) {
                if (S[index] > S[i]) {
                    maxSubResult = Math.max(maxSubResult, dfs(i, direction));
                }
            }
        }
        dp[index][direction] = result + maxSubResult;
        return dp[index][direction];
    }
}