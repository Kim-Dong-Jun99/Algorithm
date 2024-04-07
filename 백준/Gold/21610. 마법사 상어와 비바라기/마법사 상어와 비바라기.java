import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] inputArray;
    int N, M;
    int[][] water;
    Move[] moves;
    List<Position> cloud;
    List<Position> watered;
    boolean[][] wasCloud;

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
        water = new int[N][N];
        for (int i = 0; i < N; i++) {
            water[i] = getInputArray();
        }
        moves = new Move[M];
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            moves[i] = new Move(inputArray[0] - 1, inputArray[1]);
        }
        initCloud();
    }

    void initCloud() {
        cloud = new ArrayList<>();
        cloud.add(new Position(N - 1, 0));
        cloud.add(new Position(N - 1, 1));
        cloud.add(new Position(N - 2, 0));
        cloud.add(new Position(N - 2, 1));
    }

    void solve() throws IOException {
        for (Move move : moves) {
            rain(move);
            magic();
            createCloud();
        }
        calculateWater();
    }

    void rain(Move move) {
        wasCloud = new boolean[N][N];
        watered = new ArrayList<>();
        for (Position p : cloud) {
            Position moved = getNextPosition(p, move);
            wasCloud[moved.x][moved.y] = true;
            water[moved.x][moved.y] += 1;
            wasCloud[moved.x][moved.y] = true;
            watered.add(moved);
        }
    }

    void magic() {
        for (Position p : watered) {
            for (int d = 1; d < 8; d += 2) {
                int newX = p.x + DX[d];
                int newY = p.y + DY[d];
                if (isInner(newX, newY) && water[newX][newY] > 0) {
                    water[p.x][p.y] += 1;
                }
            }
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    void createCloud() {
        List<Position> newCloud = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (water[i][j] >= 2 && !wasCloud[i][j]) {
                    water[i][j] -= 2;
                    newCloud.add(new Position(i, j));
                }
            }
        }
        cloud = newCloud;
    }

    void calculateWater() throws IOException {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += water[i][j];
            }
        }
        BW.write(Integer.toString(answer));
    }

    Position getNextPosition(Position p, Move move) {
        int newX = p.x;
        int newY = p.y;
        int dx = DX[move.direction];
        int dy = DY[move.direction];
        if (dx == 1) {
            newX = getIncPosition(p.x, move.distance);
        }
        if (dx == -1) {
            newX = getDescPosition(p.x, move.distance);
        }
        if (dy == 1) {
            newY = getIncPosition(p.y, move.distance);
        }
        if (dy == -1) {
            newY = getDescPosition(p.y, move.distance);
        }

        return new Position(newX, newY);
    }

    int getIncPosition(int index, int distance) {
        distance %= N;
        return (index + distance) % N;
    }

    int getDescPosition(int index, int distance) {
        distance %= N;
        return (index + (N - distance)) % N;
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

    static class Move {
        int direction;
        int distance;

        Move(int direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }
    }
}