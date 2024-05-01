import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, K;
    long[][] dp;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        K = inputArray[1];
        dp = new long[K+1][N+1];
    }

    void solve() throws IOException {
        for (int i = 0; i <= N; i++) {
            dp[1][i] += 1;
        }
        for (int i = 2; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                if (j % 2 == 0) {
                    for (int k = 0; k < j / 2; k++) {
                        dp[i][j] += dp[i-1][j-k] + dp[i-1][k];
                    }
                    dp[i][j] += dp[i-1][j/2];
                } else {
                    for (int k = 0; k <= j / 2; k++) {
                        dp[i][j] += dp[i-1][j-k] + dp[i-1][k];
                    }
                }
                dp[i][j] %= 1_000_000_000;
            }
        }
        BW.write(Long.toString(dp[K][N] % 1_000_000_000));
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }
}