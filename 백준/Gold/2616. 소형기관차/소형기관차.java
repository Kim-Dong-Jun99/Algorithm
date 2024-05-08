import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int N, M;
    int[] trains;
    int[] sums;
    int[][] dp;

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
        N = Integer.parseInt(BR.readLine());
        trains = getInputArray();
        M = Integer.parseInt(BR.readLine());
        sums = new int[N];
        dp = new int[3][N];
        initSums();
    }

    void initSums() {
        for (int i = M - 1; i < N; i++) {
            int temp = 0;
            for (int j = i - M + 1; j <= i; j++) {
                temp += trains[j];
            }
            sums[i] = temp;
        }
    }

    void solve() throws IOException {
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(sums[i], max);
            dp[0][i] = max;
        }
        max = 0;
        for (int i = M * 2 - 1; i < N; i++) {
            max = Math.max(dp[0][i - M] + sums[i], max);
            dp[1][i] = max;
        }
        max = 0;
        for (int i = M * 3 - 1; i < N; i++) {
            max = Math.max(dp[1][i - M] + sums[i], max);
            dp[2][i] = max;
        }
        BW.write(Integer.toString(max));
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }
}