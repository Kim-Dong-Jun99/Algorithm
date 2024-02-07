import java.io.*;
import java.util.*;
class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int T, N, M;
    int[] coins;
    int[] dp;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        T = Integer.parseInt(BR.readLine());

    }
    void solve() throws IOException {
        while (T-- > 0) {
            N = Integer.parseInt(BR.readLine());
            coins = getInputArray();
            M = Integer.parseInt(BR.readLine());
            dp = new int[M+1];
            for(int coin : coins) {
                if (coin <= M) {
                    dp[coin] += 1;
                }
                for (int i = 1; i < M; i++) {
                    if (dp[i] != 0) {
                        int newValue = i + coin;
                        if (newValue <= M) {
                            dp[newValue] += dp[i];
                        }
                    }
                }
            }



            BW.write(Integer.toString(dp[M])+"\n");


        }
        BW.flush();
        BW.close();
        BR.close();

    }
}