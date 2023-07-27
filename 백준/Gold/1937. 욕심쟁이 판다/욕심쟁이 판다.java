
import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int n;
    int[][] forest;
    int[][] dp;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int answer = 0;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("exception during I/O");
        }
    }


    void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        forest = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            forest[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    void solution() throws IOException {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    dfs(i, j);
                }
                if (answer < dp[i][j]) {
                    answer = dp[i][j];
                }
//                System.out.print(dp[i][j]+" ");
            }
//            System.out.println();
        }
        System.out.println(answer);
    }


    int dfs(int i, int j) {
        dp[i][j] += 1;
        PriorityQueue<Integer> tempMax = new PriorityQueue<>(Collections.reverseOrder());
        for (int k = 0; k < 4; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];
            if (canGo(i, j, ni, nj)) {
                if (dp[ni][nj] == 0) {
                    dfs(ni, nj);
                }
                tempMax.add(dp[ni][nj]);

            }
        }
        if (!tempMax.isEmpty()) {

            dp[i][j] += tempMax.peek();
        }
        return dp[i][j];
    }

    boolean canGo(int i, int j, int ni, int nj) {

        if (0 <= ni && ni < n && 0 <= nj && nj < n) {
            return forest[i][j] < forest[ni][nj];
        }
        return false;
    }
}