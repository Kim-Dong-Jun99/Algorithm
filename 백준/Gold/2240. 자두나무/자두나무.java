import java.io.*;
import java.util.*;
class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int T, W;
    int[][] dp;
    int[] fruits;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        T = inputArray[0];
        W = inputArray[1];
        fruits = new int[T];
        for (int i = 0; i < T; i++) {
            fruits[i] = Integer.parseInt(BR.readLine());
        }

        dp = new int[T][W+1];

    }

    void solve() throws IOException {
        int result = 0;
        for (int i = 0; i < T; i++) {
            if (i == 0) {
                if (fruits[i] == 1) {
                    dp[i][0] = 1;
                } else {
                    dp[i][1] = 1;
                }
            } else {
                for (int j = 0; j <= W; j++) {
                    if (fruits[i] == 1 && j % 2 == 0) {
                        dp[i][j] += 1;
                    }
                    if (fruits[i] == 2 && j % 2 == 1) {
                        dp[i][j] += 1;
                    }
                }
            }

            if (i + 1 < T) {
                for (int j = 0; j <= W; j++) {
                    dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
                    if (j + 1 <= W) {
                        dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
                    }

                }
            }
        }
        for (int i = 0; i <= W; i++) {
            result = Math.max(result, dp[T-1][i]);
        }
        System.out.println(result);

    }
}