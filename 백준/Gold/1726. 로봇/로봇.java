import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    int[] inputArray;
    int M, N;
    Position robot;
    Position goal;
    int[][] board;
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
        board = new int[N][M];
        visited = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            board[i] = getInputArray();
        }
        inputArray = getInputArray();
        robot = new Position(inputArray[0]-1, inputArray[1]-1, convertDirection(inputArray[2]));
        inputArray = getInputArray();
        goal = new Position(inputArray[0]-1, inputArray[1]-1, convertDirection(inputArray[2]));
    }

    int convertDirection(int d) {
        if (d == 1) {
            return 0;
        } else if (d == 2) {
            return 2;
        } else if (d == 3) {
            return 1;
        } else {
            return 3;
        }
    }

    void solve() throws IOException {
        List<Position> current = new ArrayList<>();
        current.add(robot);
        visited[robot.x][robot.y][robot.d] = true;
        int distance = 0;
        while (!current.isEmpty()) {
            if (visited[goal.x][goal.y][goal.d]) {
                BW.write(Integer.toString(distance));
                return;
            }
            List<Position> temp = new ArrayList<>();
            for (Position p : current) {
                int newX = p.x + DX[p.d];
                int newY = p.y + DY[p.d];
                int cw = getCW(p.d);
                int ccw = getCCW(p.d);
                int moved = 1;
                while (isInner(newX, newY) && board[newX][newY] == 0 && moved <= 3) {
                    if (!visited[newX][newY][p.d]) {
                        visited[newX][newY][p.d] = true;
                        temp.add(new Position(newX, newY, p.d));
                    }
                    newX += DX[p.d];
                    newY += DY[p.d];
                    moved += 1;
                }
                if (!visited[p.x][p.y][cw]) {
                    visited[p.x][p.y][cw] = true;
                    temp.add(new Position(p.x, p.y, cw));
                }
                if (!visited[p.x][p.y][ccw]) {
                    visited[p.x][p.y][ccw] = true;
                    temp.add(new Position(p.x, p.y, ccw));
                }
            }
            current = temp;
            distance += 1;
        }

    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    int getCW(int d) {
        return (d + 1) % 4;
    }

    int getCCW(int d) {
        return (d + 3) % 4;
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Position {
        int x;
        int y;
        int d;

        Position(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;

        }
    }
}