import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int EMPTY = 0;
    public static final int HOUSE = 1;
    public static final int CHICKEN = 2;
    int[] inputArray;
    int N, M;
    int[][] board;
    List<Position> houses;
    List<Position> chickens;
    int[] remain;
    int answer;
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
        board = new int[N][N];
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            board[i] = getInputArray();
            for (int j = 0; j < N; j++) {
                if (board[i][j] == HOUSE) {
                    houses.add(new Position(i, j));
                }
                if (board[i][j] == CHICKEN) {
                    chickens.add(new Position(i, j));
                }
            }
        }
        remain = new int[M];
        answer = Integer.MAX_VALUE;

    }

    void solve() throws IOException {
        dfs(0);
        System.out.println(answer);
    }

    void dfs(int index) {
        if (index == M) {
            int tempAnswer = 0;
            for (Position house : houses) {
                int shortestDistance = Integer.MAX_VALUE;
                for (int i = 0; i < M; i++) {
                    Position chicken = chickens.get(remain[i]);
                    shortestDistance = Math.min(shortestDistance, getDistance(house, chicken));
                }
                tempAnswer += shortestDistance;
            }
            answer = Math.min(answer, tempAnswer);
            return;
        }
        if (index == 0) {
            for (int i = 0; i < chickens.size(); i++) {
                remain[index] = i;
                dfs(index + 1);
            }
        } else {
            for (int i = remain[index-1]+1; i < chickens.size(); i++) {
                remain[index] = i;
                dfs(index + 1);
            }
        }
    }

    int getDistance(Position p1, Position p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
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