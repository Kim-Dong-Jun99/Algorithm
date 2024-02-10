import java.io.*;
import java.util.*;

/*

N <= 30
H N개, W N개로 이루어진 문자열이 나올 것임
근데 W a개가 나오려면, H개가 a개 이상 나와야함,

0 - H, 1 - W

*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    long[][] dp;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void solve() throws IOException {
        while (true) {
            N = Integer.parseInt(BR.readLine());
            if (N == 0) {
                break;
            }

            dp = new long[N+1][N+1];
            dp[1][0] = 1;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j <= i; j++) {
                    if (dp[i][j] != 0) {
                        if (i + 1 <= N) {
                            dp[i+1][j] += dp[i][j];
                        }
                        if (i >= j+1) {
                            dp[i][j+1] += dp[i][j];
                        }
                    }
                }
            }


            BW.write(Long.toString(dp[N][N])+"\n");
        }
        BW.flush();
        BW.close();
        BR.close();
    }
}