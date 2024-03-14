import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    public static final int[] DY = {1, 1, 0, -1, -1, -1, 0, 1};
    int[] inputArray;
    int N, M, K;
    char[][] board;
    List<String> likes;
    HashMap<String, Integer> resultMap;
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
        board = new char[N][M];
        likes = new ArrayList<>();
        resultMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String inputStr = BR.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = inputStr.charAt(j);
            }
        }
        for (int i = 0; i < K; i++) {
            likes.add(BR.readLine());
        }

    }

    void solve() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(1, Character.toString(board[i][j]), i, j);
            }
        }
        for (String like : likes) {
            System.out.println(resultMap.getOrDefault(like, 0));

        }
    }

    void dfs(int index, String str, int x, int y) {
        resultMap.put(str, resultMap.getOrDefault(str, 0) + 1);
        if (index == 5) {
            return;
        }
        for (int d = 0; d < 8; d++) {
            int newX = x + DX[d];
            int newY = y + DY[d];
            newX = rotateX(newX);
            newY = rotateY(newY);
            dfs(index + 1, str + Character.toString(board[newX][newY]), newX, newY);

        }
    }

    int rotateX(int x) {
        if (isInnerX(x)) {
            return x;
        } else if (x == N) {
            return 0;
        } else {
            return N - 1;
        }
    }

    int rotateY(int y) {
        if (isInnerY(y)) {
            return y;
        } else if (y == M) {
            return 0;
        } else {
            return M - 1;
        }
    }

    boolean isInnerX(int x) {
        return 0 <= x && x < N;
    }

    boolean isInnerY(int y) {
        return 0 <= y && y < M;
    }
}