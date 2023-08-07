import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    int[] bytes;
    int[] costs;
    int answer;
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
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        bytes = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        costs = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        answer = Integer.MAX_VALUE;
        dp = new int[N][100 * N + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    void solution() throws IOException {
        dp(0, 0, 0);
        System.out.println(answer);
    }

    void dp(int index, int weight, int cost) {
        if (weight >= M) {
            answer = Math.min(answer, cost);
            return;
        }
        if (index >= N || dp[index][cost] >= weight) {
//            System.out.println(index +" "+cost);
            return;
        }
        dp[index][cost] = weight;
        dp(index + 1, weight, cost);
        dp(index + 1, weight + bytes[index], cost + costs[index]);

    }
}