import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final char WALL = '1';

    int[] inputArray;
    int N, M, K;
    char[][] board;
    boolean[][][] visited;
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
        K = inputArray[2];
        board = new char[N][M];
        visited = new boolean[N][M][K+1];
        for (int i = 0; i < N; i++) {
            String inputStr = BR.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = inputStr.charAt(j);
            }
        }
    }

    void solve() throws IOException {
        int answer = 0;
        List<Position> current = Collections.singletonList(new Position(0, 0, 0));
        while (!current.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position p : current) {
                if (p.x == N-1 && p.y == M-1) {
                    BW.write(Integer.toString(answer+1));
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int newX = p.x + DX[d];
                    int newY = p.y + DY[d];
                    if (isInner(newX, newY)) {
                        if (board[newX][newY] == WALL) {
                            if (p.breaked + 1 <= K && !visited[newX][newY][p.breaked+1]) {
                                visited[newX][newY][p.breaked+1] = true;
                                temp.add(new Position(newX, newY, p.breaked+1));
                            }
                        } else {
                            if (!visited[newX][newY][p.breaked]) {
                                visited[newX][newY][p.breaked] = true;
                                temp.add(new Position(newX, newY, p.breaked));
                            }
                        }
                    }
                }
            }
            answer += 1;
            current = temp;
        }
        BW.write("-1");
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Position {
        int x;
        int y;
        int breaked;

        Position(int x, int y, int breaked) {
            this.x = x;
            this.y = y;
            this.breaked = breaked;
        }
    }
}