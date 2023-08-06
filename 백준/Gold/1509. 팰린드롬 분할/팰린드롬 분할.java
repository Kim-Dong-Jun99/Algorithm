import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    String givenString;
    int N;
    boolean[][] isPalindrome;
    int[] dp;
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
        givenString = BR.readLine();
        N = givenString.length();
        isPalindrome = new boolean[N][N];
        dp = new int[N+1];
        Arrays.fill(dp,2500);
        for (int i = 0; i + 1 < N; i++) {
            isPalindrome[i][i] = true;
            if (givenString.charAt(i) == givenString.charAt(i+1)) {
                isPalindrome[i][i + 1] = true;
            }
        }
        isPalindrome[N-1][N-1] = true;
        for (int j = 2; j <= N; j++) {
            for (int i = 0; i + j < N; i++) {
                if (givenString.charAt(i) == givenString.charAt(i + j) && isPalindrome[i + 1][i + j - 1]) {
                    isPalindrome[i][i + j] = true;
                }
            }
        }
    }

    void solution() throws IOException {
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if (isPalindrome[j-1][i-1]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                } else {
                    dp[i] = Math.min(dp[i], dp[i - 1] + 1);
                }
            }
        }
        System.out.println(dp[N]);
    }



}