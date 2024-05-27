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
            String str = BR.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }
    }

    void solve() throws IOException {
        List<Position> current = new ArrayList<>();
        current.add(new Position(0, 0, 0));
        visited[0][0][0] = true;
        int time = 1;
        while (!current.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position p : current) {
                if (p.x == N-1 && p.y == M-1) {
                    BW.write(Integer.toString(time));
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int newX = p.x + DX[d];
                    int newY = p.y + DY[d];
                    if (isInner(newX, newY)) {
                        if (board[newX][newY] == WALL) {
                            if (p.crashed == K) {
                                continue;
                            }
                            if (time % 2 == 1) {
                                if (visited[newX][newY][p.crashed + 1]) {
                                    continue;
                                }
                                visited[newX][newY][p.crashed + 1] = true;
                                temp.add(new Position(newX, newY, p.crashed+1));
                            } else {
                                temp.add(new Position(p.x, p.y, p.crashed));
                            }

                        } else {
                            if (visited[newX][newY][p.crashed]) {
                                continue;
                            }
                            visited[newX][newY][p.crashed] = true;
                            temp.add(new Position(newX, newY, p.crashed));
                        }
                    }
                }

            }
            current = temp;
            time += 1;
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
        int crashed;

        Position(int x, int y, int crashed) {
            this.x = x;
            this.y = y;
            this.crashed = crashed;
        }
    }

}