import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int testCase;
    int N, M;
    int[] coins, dp;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.test();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void test() throws IOException {
        testCase = Integer.parseInt(BR.readLine());
        while (testCase-- > 0) {
            init();
            solve();
        }
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        coins = getInputArray();
        M = Integer.parseInt(BR.readLine());
        dp = new int[M+1];
    }

    void solve() throws IOException {
        for (int coin : coins) {
            if (coin <= M) {
                dp[coin] += 1;
                for (int i = 1; i <= M; i++) {
                    if (dp[i] != 0 && i + coin <= M) {
                        dp[i + coin] += dp[i];
                    }
                }
            }
        }
        System.out.println(dp[M]);
    }

}