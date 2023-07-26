import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    int[][] map;
    int[][] dp;
    int[][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (Exception e) {
            System.out.println("exception during I/O");
        }
    }


    void init() throws Exception {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];

        map = new int[N][M];
        dp = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

    }

    void solution() throws Exception {
        int answer = dfs(0, 0);


        System.out.println(answer);
    }

    int dfs(int r, int c) {
        if (r == N - 1 && c == M - 1) {
            return 1;
        } else if (visited[r][c] != 0) {
            return dp[r][c];
        } else {
            visited[r][c] = 1;
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                    if (map[nr][nc] < map[r][c]) {
                        dp[r][c] += dfs(nr, nc);
                    }
                }
            }
            return dp[r][c];
        }
    }


}