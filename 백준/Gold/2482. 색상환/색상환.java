import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int MOD = 1_000_000_003;
    int N;
    int K;
    int[][] dp;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        K = Integer.parseInt(BR.readLine());
        dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }
    }

    void solution() throws IOException {
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        int toPrint = (dp[N - 3][K - 1] + dp[N - 1][K]) % MOD;
        System.out.println(toPrint);
    }


}
