import java.io.*;
import java.util.Arrays;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, M, K;
    int[][] board;
    Sticker[] stickers;


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
        stickers = new Sticker[K];
        for (int i = 0; i < K; i++) {
            inputArray = getInputArray();
            int r = inputArray[0];
            int c = inputArray[1];
            int[][] s = new int[r][c];
            for (int j = 0; j < r; j++) {
                s[j] = getInputArray();
            }
            stickers[i] = new Sticker(r, c, s);
        }
    }

    void solve() throws IOException {
        for (Sticker sticker : stickers) {
            markSticker(sticker);
        }
    }

    void printResult() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += board[i][j];
            }
        }
        System.out.println(answer);
    }

    void markSticker(Sticker sticker) {
        for (int d = 0; d < 4; d++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (canMark(i, j, sticker)) {
                        mark(i, j, sticker);
                        return;
                    }
                }
            }
            sticker.rotate();
        }
    }

    boolean canMark(int x, int y, Sticker sticker) {
        for (int i = 0; i < sticker.r; i++) {
            for (int j = 0; j < sticker.c; j++) {
                if (!isInner(x + i, y + j) || board[x + i][y + j] + sticker.s[i][j] == 2) {

                    return false;
                }
            }
        }
        return true;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    void mark(int x, int y, Sticker sticker) {
        for (int i = 0; i < sticker.r; i++) {
            for (int j = 0; j < sticker.c; j++) {
                board[x + i][y + j] += sticker.s[i][j];
            }
        }
    }



    static class Sticker {
        int r;
        int c;
        int[][] s;

        Sticker(int r, int c, int[][] s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }

        void rotate() {
            int[][] newS = new int[c][r];
            int newR = c;
            int newC = r;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    newS[j][r - 1 - i] = s[i][j];
                }
            }

            r = newR;
            c = newC;
            s = newS;
        }
    }
}