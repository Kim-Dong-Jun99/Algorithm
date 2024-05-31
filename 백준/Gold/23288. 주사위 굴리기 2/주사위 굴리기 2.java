import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final int[] OPPOSITE = {2, 3, 0, 1};

    int[] inputArray;
    int N, M, K;
    int[][] board;
    Dice dice;

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

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = getInputArray();
        }

        dice = new Dice();
    }

    void solve() throws IOException {
        int answer = 0;
        for (int i = 0; i < K; i++) {
            rollDice();
            answer += calculateScore();
            compareAndRotate();
        }
        BW.write(Integer.toString(answer));
    }

    void rollDice() {
        int newX = dice.x + DX[dice.d];
        int newY = dice.y + DY[dice.d];

        if (!isInner(newX, newY)) {
            dice.d = OPPOSITE[dice.d];
            newX = dice.x + DX[dice.d];
            newY = dice.y + DY[dice.d];
        }
        dice.updateXY(newX, newY);
        dice.updateSide();
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    int calculateScore() {
        int B = board[dice.x][dice.y];
        int C = 0;
        Queue<Position> current = new LinkedList<>();
        current.add(new Position(dice.x, dice.y));
        boolean[][] visited = new boolean[N][M];
        visited[dice.x][dice.y] = true;
        while (!current.isEmpty()) {
            C += 1;
            Position p = current.remove();

            for (int d = 0; d < 4; d++) {
                int newX = p.x + DX[d];
                int newY = p.y + DY[d];

                if (isInner(newX, newY) && board[newX][newY] == B && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    current.add(new Position(newX, newY));
                }
            }
        }
        return B * C;
    }

    void compareAndRotate() {
        if (dice.side[5] > board[dice.x][dice.y]) {
            dice.rotateCW();
        }
        if (dice.side[5] < board[dice.x][dice.y]) {
            dice.rotateCCW();
        }
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

    static class Dice {
        int x;
        int y;
        int[] side;
        int d;

        Dice() {
            this.x = 0;
            this.y = 0;
            this.side = new int[6];
            this.d = 0;
            for (int i = 0; i < 6; i++) {
                this.side[i] = i+1;
            }
        }

        void updateXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void updateSide() {
            int[] newSide = new int[6];
            if (this.d == 0) {
                newSide[0] = side[3];
                newSide[3] = side[5];
                newSide[5] = side[2];
                newSide[2] = side[0];
                newSide[1] = side[1];
                newSide[4] = side[4];
            }
            if (this.d == 1) {
                newSide[0] = side[1];
                newSide[4] = side[0];
                newSide[5] = side[4];
                newSide[1] = side[5];
                newSide[3] = side[3];
                newSide[2] = side[2];
            }
            if (this.d == 2) {
                newSide[0] = side[2];
                newSide[3] = side[0];
                newSide[5] = side[3];
                newSide[2] = side[5];
                newSide[1] = side[1];
                newSide[4] = side[4];
            }
            if (this.d == 3) {
                newSide[0] = side[4];
                newSide[1] = side[0];
                newSide[5] = side[1];
                newSide[4] = side[5];
                newSide[3] = side[3];
                newSide[2] = side[2];
            }
            this.side = newSide;
        }

        void rotateCW() {
            this.d = (this.d + 1) % 4;
        }

        void rotateCCW() {
            this.d = (this.d + 3) % 4;
        }
    }
}