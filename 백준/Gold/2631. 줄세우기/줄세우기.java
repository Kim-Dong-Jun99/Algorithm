import java.util.*;
import java.io.*;

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    int[] position;
    int[] dp;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        position = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            position[i] = Integer.parseInt(BR.readLine());
        }
    }

    void solve() throws IOException {
        dp[0] = 1;

        int answer = 0;
        for(int i=1;i<N;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(position[i] > position[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(N-answer);
    }
}