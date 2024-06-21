import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final char EMPTY = '.';
    static final char RAIN = '*';
    static final char RIVER = 'X';
    static final char CAR_WASH = 'W';
    static final char HOME = 'H';
    
    int[] inputArray;
    int R, C;
    char[][] board;
    List<Position> loc;
    List<Position> rain;
    Position goal;
    boolean[][] visited;
    
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
        R = inputArray[0];
        C = inputArray[1];
        loc = new ArrayList<>();
        rain = new ArrayList<>();
        visited = new boolean[R][C];
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = BR.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == RAIN) {
                    rain.add(new Position(i, j));
                }
                if (board[i][j] == CAR_WASH) {
                    loc.add(new Position(i, j));
                    visited[i][j] = true;
                }
                if (board[i][j] == HOME) {
                    goal = new Position(i, j);
                }
            }
        }
    }

    void solve() {
        int time = 0;
        while (!loc.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position r : rain) {
                for (int d = 0; d < 4; d++) {
                    int newX = r.x + DX[d];
                    int newY = r.y + DY[d];
                    if (isInner(newX, newY) && board[newX][newY] == EMPTY) {
                        temp.add(new Position(newX, newY));
                        board[newX][newY] = RAIN;
                    }
                }
            }
            rain = temp;
            temp = new ArrayList<>();
            for (Position p : loc) {
                for (int d = 0; d < 4; d++) {
                    int newX = p.x + DX[d];
                    int newY = p.y + DY[d];
                    if (isInner(newX, newY) && !visited[newX][newY] && (board[newX][newY] == EMPTY || board[newX][newY] == HOME)) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            loc = temp;
            time += 1;
            if (visited[goal.x][goal.y]) {
                System.out.println(time);
                return;
            }
        }
        System.out.println("FAIL");
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
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
