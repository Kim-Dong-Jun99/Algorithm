import java.io.*;
import java.util.*;

/*
보드 바깥으로 나가거나, 구멍에 빠지면 끝, 무한히 게임 진행 가능하면 -1 출력

무한히 가능 -> 사이클 존재

사이클 판단???, dfs 돌릴때마다 visited 초기화,,
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};

    int[] inputArray;
    int N, M;
    int[][] board;
    int[][] dp;
    boolean[][] visited;
    int result;

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

        board = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        result = 0;

        for (int i = 0; i < N; i++) {
            String tempString = BR.readLine();
            for (int j = 0; j < M; j++) {
                char c = tempString.charAt(j);
                if (Character.isDigit(c)) {
                    board[i][j] = Character.getNumericValue(c);
                }
            }
        }
    }

    void solve() throws IOException {

        System.out.println(dfs(0, 0));
    }

    int dfs(int x, int y) {
        if (!isInner(x, y) || board[x][y] == 0) {
            return 0;
        }

        if (visited[x][y]) {
            return -1;
        }

        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        visited[x][y] = true;
        int maxResult = 0;
        for (int d = 0; d < 4; d++) {
            int newX = x + DX[d] * board[x][y];
            int newY = y + DY[d] * board[x][y];

            int tempResult = dfs(newX, newY);
            if (tempResult == -1) {
                return -1;
            }
            maxResult = Math.max(maxResult, tempResult);

        }
        visited[x][y] = false;

        dp[x][y] = 1 + maxResult;
        return dp[x][y];
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}