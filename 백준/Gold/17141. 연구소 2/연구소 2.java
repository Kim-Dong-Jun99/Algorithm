import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final int VIRUS = 2;
    static final int WALL = 1;

    int N, M;
    int[][] board;
    int[] inputArray;
    int walls, virus;
    Position[] virusPositions;
    int answer;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        M = inputArray[1];
        board = new int[N][N];
        walls = 0;
        virus = 0;
        answer = Integer.MAX_VALUE;
        virusPositions = new Position[10];
        for (int i = 0; i < N; i++) {
            board[i] = getInputArray();
            for (int j = 0; j < N; j++) {
                if (board[i][j] == WALL) {
                    walls += 1;
                }
                if (board[i][j] == VIRUS) {
                    virusPositions[virus] = new Position(i, j);
                    virus += 1;
                }
            }
        }
    }

    void solve() throws IOException {
        dfs(0, new int[M], new boolean[10]);
        if (answer != Integer.MAX_VALUE) {
            BW.write(Integer.toString(answer));
        } else {
            BW.write(Integer.toString(-1));
        }
    }

    void dfs(int n, int[] current, boolean[] visited) {
        if (n == M) {
            answer = Math.min(answer, bfs(current));
            return;
        }
        if (n == 0) {
            for (int i = 0; i < virus; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    current[n] = i;
                    dfs(n+1, current, visited);
                    visited[i] = false;
                }
            }
        } else {
            for (int i = current[n-1]+1; i < virus; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    current[n] = i;
                    dfs(n+1, current, visited);
                    visited[i] = false;
                }
            }
        }
    }

    int bfs(int[] current) {
        int virused = 0;
        boolean[][] visited = new boolean[N][N];
        List<Position> positions = new ArrayList<>();
        for (int c : current) {
            positions.add(virusPositions[c]);
            visited[virusPositions[c].x][virusPositions[c].y] = true;
        }
        int time = 0;
        while (!positions.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position p : positions) {
                virused += 1;
                for (int d = 0; d < 4; d++) {
                    int newX = p.x + DX[d];
                    int newY = p.y + DY[d];
                    if (isInner(newX, newY) && board[newX][newY] != WALL && !visited[newX][newY]) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            positions = temp;
            if (!temp.isEmpty()) {
                time += 1;
            }
        }
        if (virused == N * N - walls) {
            return time;
        }
        return Integer.MAX_VALUE;

    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}