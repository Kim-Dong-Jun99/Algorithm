import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final char EMPTY = '.';
    static final char MINERAL = 'x';
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    int[] inputArray;
    int N, R, C;
    char[][] board;
    int[] sticks;
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
        R = inputArray[0];
        C = inputArray[1];

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String temp = BR.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = temp.charAt(j);
            }
        }

        N = Integer.parseInt(BR.readLine());
        sticks = getInputArray();
    }

    void solve() throws IOException {
        for (int i = 0; i < N; i++) {
            int stick = convertStick(sticks[i]);
            crashMineral(stick, i);
            dropCluster();
        }
        printBoard();
    }

    void printBoard() throws IOException {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                BW.write(board[i][j]);
            }
            BW.newLine();
        }
    }

    void dropCluster() {
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == MINERAL && !visited[i][j]) {
                    List<Position> ps = new LinkedList<>();
                    boolean canDrop = true;
                    Queue<Position> q = new LinkedList<>();
                    q.add(new Position(i, j));
                    visited[i][j] = true;
                    while (!q.isEmpty()) {
                        Position p = q.poll();
                        if (isFloor(p.x)) {
                            canDrop = false;
                        }
                        ps.add(p);
                        for (int d = 0; d < 4; d++) {
                            int newX = p.x + DX[d];
                            int newY = p.y + DY[d];
                            if (canGo(newX, newY, visited)) {
                                q.add(new Position(newX, newY));
                                visited[newX][newY] = true;
                            }
                        }
                    }
                    if (canDrop) {
                        drop(ps);
                        return;
                    }
                }
            }
        }
    }

    void drop(List<Position> ps) {
        List<Position> edge = getEdge(ps);
        unMark(ps);
        int distance = 1;
        while (canDrop(edge, distance + 1)) {
            distance += 1;
        }
        mark(ps, distance);
    }



    List<Position> getEdge(List<Position> ps) {
        List<Position> edge = new ArrayList<>();
        for (Position p : ps) {
            if (board[p.x + 1][p.y] == EMPTY) {
                edge.add(p);
            }
        }
        return edge;
    }

    void unMark(List<Position> ps) {
        for (Position p : ps) {
            board[p.x][p.y] = EMPTY;
        }
    }

    void mark(List<Position> ps, int distance) {
        for (Position p : ps) {
            board[p.x+distance][p.y] = MINERAL;
        }
    }

    boolean canDrop(List<Position> edge, int distance) {
        for (Position p : edge) {
            if (isInner(p.x + distance, p.y) && board[p.x + distance][p.y] == EMPTY) {
                continue;
            }
            return false;
        }
        return true;
    }

    boolean isFloor(int x) {
        return x == R - 1;
    }

    boolean canGo(int newX, int newY, boolean[][] visited) {
        return isInner(newX, newY) && board[newX][newY] == MINERAL && !visited[newX][newY];
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    void crashMineral(int stick, int i) {
        if (throwFromLeft(i)) {
            for (int j = 0; j < C; j++) {
                if (board[stick][j] == MINERAL) {
                    board[stick][j] = EMPTY;
                    return;
                }
            }
        } else {
            for (int j = C - 1; j >= 0; j--) {
                if (board[stick][j] == MINERAL) {
                    board[stick][j] = EMPTY;
                    return;
                }
            }
        }
    }

    int convertStick(int s) {
        return R - s;
    }

    boolean throwFromLeft(int i) {
        return i % 2 == 0;
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