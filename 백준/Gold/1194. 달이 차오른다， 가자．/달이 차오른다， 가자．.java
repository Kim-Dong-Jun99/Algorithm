import java.util.*;
import java.io.*;
class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, -1, 0, 1};
    int N;
    int M;
    char[][] maze;
    int startX;
    int startY;
//    Position start;
    HashSet<Character> keys;
    int[][][] dp;
    int answer;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch(IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    void init() throws IOException {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        maze = new char[N][M];
        dp = new int[N][M][64];
        keys = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String tempString = BR.readLine();
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
                maze[i][j] = tempString.charAt(j);
                if (maze[i][j] == '0') {
                    startX = i;
                    startY = j;
                }
            }
        }
        answer = Integer.MAX_VALUE;
    }
    void solution() throws IOException {
        dp(startX, startY, 0,0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    void dp(int x, int y, int value,int keyStatus) {
        if (maze[x][y] == '1') {
            answer = Math.min(answer, value);
            return;
        }
        if (dp[x][y][keyStatus] <= value) {
            return;
        }
        dp[x][y][keyStatus] = value;
        for (int i = 0; i < 4; i++) {
            int newX = x + DX[i];
            int newY = y + DY[i];
            if (isInner(newX, newY) && isNotWall(newX, newY)) {
                if (maze[newX][newY] == '.' || maze[newX][newY] == '1' || maze[newX][newY] == '0') {
                    dp(newX, newY, value + 1, keyStatus);
                } else if (97 <= maze[newX][newY] && maze[newX][newY] <= 102) {
                    if (!keys.contains(maze[newX][newY])) {
                        keyStatus |= (1 << ((int) maze[newX][newY] - 97));
                        keys.add(maze[newX][newY]);
                        dp(newX, newY, value + 1, keyStatus);
                        keys.remove(maze[newX][newY]);
                        keyStatus &= ~(1 << ((int) maze[newX][newY] - 97));
                    } else {
                        dp(newX, newY, value + 1, keyStatus);
                    }

                } else {
                    if (keyStatus == (keyStatus | (1 << ((int) maze[newX][newY] - 65)))) {

                        dp(newX, newY, value + 1, keyStatus);
                    }
                }
            }

        }
    }

    boolean isInner(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < M;
    }

    boolean isNotWall(int i, int j) {
        return !(maze[i][j] == '#');
    }
}
