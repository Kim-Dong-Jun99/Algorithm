import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final char U = 'U';
    static final char R = 'R';
    static final char L = 'L';
    static final char D = 'D';
    int[] inputArray;
    int N, M;
    char[][] board;
    boolean[][] visited;
    int[][] dp;

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
        N = inputArray[0];
        M = inputArray[1];
        board = new char[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String inputString = BR.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = inputString.charAt(j);
                dp[i][j] = -1;
            }
        }
    }

    void solve() throws IOException {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(i, j) == 1) {
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }

    int dfs(int x, int y) {
        visited[x][y] = true;
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        int d = charToInt(board[x][y]);
        int newX = x + DX[d];
        int newY = y + DY[d];
        if (!isInner(newX, newY)) {
            dp[x][y] = 1;
        } else {
            if (visited[newX][newY] && dp[newX][newY] == -1) {
                dp[x][y] = 0;
            } else {
                dp[x][y] = dfs(newX, newY);
            }
        }
        return dp[x][y];
    }


    int charToInt(char c) {
        if (c == U) {
            return 3;
        } else if (c == R) {
            return 0;
        } else if (c == D) {
            return 1;
        } else {
            return 2;
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}