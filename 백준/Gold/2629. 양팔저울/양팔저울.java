import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int[] weights;
    int M;
    int[] marbles;
    boolean[][] dp;
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
        weights = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new boolean[N+1][30 * 500 + 1];
        M = Integer.parseInt(BR.readLine());
        marbles = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void solution() throws IOException {
        dp(0, 0);

        for (Integer marble : marbles) {
            if (marble > 30 * 500) {
                BW.write("N ");
            } else if (dp[N][marble]) {
                BW.write("Y ");
            } else {
                BW.write("N ");
            }
        }
        BW.flush();
        BW.close();
    }

    void dp(int index, int weight) {
        if (dp[index][weight]) {
            return;
        }
        dp[index][weight] = true;
        if (index < N) {
            dp(index + 1, weight);
            dp(index + 1, weight + weights[index]);
            dp(index + 1, Math.abs(weight - weights[index]));
        }
    }
}