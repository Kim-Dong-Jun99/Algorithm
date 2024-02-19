import java.util.*;
import java.io.*;

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};
    int[] inputArray;
    int N, M, K;
    int[][] originalBoard;
    int[][] board;
    Rotate[] rotations;
    int answer;
    int[] order;
    boolean[] visited;
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
        K = inputArray[2];
        originalBoard = new int[N][M];
        rotations = new Rotate[K];
        order = new int[K];
        visited= new boolean[K];
        answer = 100 * 50;
        for (int i = 0; i < N; i++) {
            originalBoard[i] = getInputArray();
        }

        for (int i = 0; i < K; i++) {
            inputArray = getInputArray();
            rotations[i] = new Rotate(inputArray[0]-1, inputArray[1]-1, inputArray[2]);
        }


    }

    void solve() throws IOException {
        dfs(0);
        System.out.println(answer);
    }

    void dfs(int index) {
        if (index == K) {
            board = copyBoard();
            for (int i : order) {
                Rotate rotate = rotations[i];
                rotateBoard(rotate);
            }
            updateAnswer();
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[index] = i;

                dfs(index + 1);

                visited[i] = false;
            }
        }
    }

    void rotateBoard(Rotate rotate) {
        for (int i = 1; i <= rotate.s; i++) {
            int len = (i*2+1) * 4 - 4;
            Position[] positions = new Position[len];
            int[] values = new int[len];

            int x = rotate.r - i;
            int y = rotate.c - i;
            int index = 0;
            for (int d = 0; d < 4; d++) {
                while (canMove(x, y, d, i, rotate.r, rotate.c)) {
                    positions[index] = new Position(x, y);
                    values[index] = board[x][y];
                    x += DX[d];
                    y += DY[d];
                    index += 1;
                    
                }
            }

            for (int j = 0; j < len; j++) {
                int leftIndex = getLeftIndex(j, len);
                Position p = positions[j];
                board[p.x][p.y] = values[leftIndex];
            }
        }
    }

    int getLeftIndex(int index, int len) {
        return (index + len - 1) % len;
    }

    boolean canMove(int x, int y, int d, int i, int r, int c) {
        int newX = x + DX[d];
        int newY = y + DY[d];
        return Math.max(Math.abs(newX-r), Math.abs(newY-c)) <= i;
    }

    void updateAnswer() {
        for (int i = 0; i < N; i++) {
            int tempSum = 0;
            for (int j = 0; j < M; j++) {
                tempSum += board[i][j];
            }
            answer = Math.min(answer, tempSum);
        }
    }

    int[][] copyBoard() {
        int[][] tempBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempBoard[i][j] = originalBoard[i][j];
            }
        }
        return tempBoard;
    }

    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Rotate {
        int r;
        int c;
        int s;

        public Rotate(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}