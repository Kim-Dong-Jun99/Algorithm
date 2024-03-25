import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int A, B, C;
    boolean[][][] dp;
    boolean[] visited;
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
        A = inputArray[0];
        B = inputArray[1];
        C = inputArray[2];
        dp = new boolean[A + 1][B + 1][C + 1];
        visited = new boolean[C + 1];

    }

    void solve() throws IOException {
        dfs(0, 0, C);
        for (int i = 0; i <= C; i++) {
            if (visited[i]) {

                BW.write(Integer.toString(i) + " ");
            }
        }
        BW.flush();
        BW.close();
        BR.close();
    }

    void dfs(int a, int b, int c) {
        if (dp[a][b][c]) {
            return;
        }
        dp[a][b][c] = true;
        if (a == 0) {
            visited[c] = true;
        }
        if (c + a <= A) {
            dfs(c + a, b, 0);
        } else {
            dfs(A, b, c + a - A);
        }

        if (c + b <= B) {
            dfs(a, c + b, 0);
        } else {
            dfs(a, B, c + b - B);
        }

        if (c + a <= C) {
            dfs(0, b, c + a);
        } else {
            dfs(c + a - C, b, C);
        }

        if (b + a <= B) {
            dfs(0, b + a, c);
        } else {
            dfs(b + a - B, B, c);
        }

        if (b + c <= C) {
            dfs(a, 0, b + c);
        } else {
            dfs(a, b + c - C, C);
        }

        if (a + b <= A) {
            dfs(a + b, 0, c);
        } else {
            dfs(A, a + b - A, c);
        }
    }
}