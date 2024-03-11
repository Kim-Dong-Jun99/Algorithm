import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final char PLUS = '+';
    static final char MULTIPLE = '*';
    static final char MINUS = '-';
    static final int[] DX = {1, 0};
    static final int[] DY = {0, 1};
    int N;
    char[][] board;
    int max, min;
    char[] formula;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String[] split = BR.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = split[j].charAt(0);
            }
        }
        formula = new char[2 * N - 1];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    void solve() throws IOException {
        dfs(0,0,0);
        System.out.println(max +" "+min);
    }

    void dfs(int x, int y, int index) {
        if (x == N - 1 && y == N - 1) {
            formula[index] = board[x][y];
            int left = Character.getNumericValue(formula[0]);
            for (int i = 1; i < 2 * N - 1; i += 2) {
                if (formula[i] == MINUS) {
                    left -= Character.getNumericValue(formula[i + 1]);
                }
                if (formula[i] == PLUS) {
                    left += Character.getNumericValue(formula[i + 1]);
                }
                if (formula[i] == MULTIPLE) {
                    left *= Character.getNumericValue(formula[i + 1]);
                }
            }
            max = Math.max(max, left);
            min = Math.min(min, left);
            return;
        }
        formula[index] = board[x][y];
        for (int d = 0; d < 2; d++ ) {
            int newX = x + DX[d];
            int newY = y + DY[d];
            if (isInner(newX, newY)) {
                dfs(newX, newY, index + 1);
            }
        }

    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

}