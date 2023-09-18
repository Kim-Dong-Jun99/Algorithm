import java.util.*;
import java.io.*;
import java.util.stream.Stream;


class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] inputArray;
    int N, M, H;
    boolean[][] edges;
    int answer;

    public static void main(String[] args) {

        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    Stream<String> getInputStream() throws IOException {
        return Arrays.stream(BR.readLine().split(" "));
    }

    void init() throws IOException {
        inputArray = getInputStream().mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        H = inputArray[2];

        edges = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            inputArray = getInputStream().mapToInt(Integer::parseInt).toArray();
            edges[inputArray[0]][inputArray[1]] = true;
        }
    }

    void solution() throws IOException {
        answer = backtrack(0, 1);
        System.out.println(answer);
    }

    int backtrack(int index, int row) {
        if (index > 3) {
            return -1;
        }

        if (isDone()) {
            return index;
        }
        int toReturn = -1;
        for (int i = row; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (canPut(i, j)) {
                    edges[i][j] = true;

                    int backtracked = backtrack(index + 1, i);
                    if (backtracked != -1) {
                        if (toReturn == -1) {
                            toReturn = backtracked;
                        } else {
                            toReturn = Math.min(toReturn, backtracked);
                        }
                    }
                    edges[i][j] = false;
                }


            }
        }

        return toReturn;
    }

    boolean canPut(int i, int j) {
        if (j == 1) {
            return !edges[i][j] && !edges[i][j + 1];
        } else if (j == N) {
            return !edges[i][j] && !edges[i][j - 1];
        } else {
            return !edges[i][j] && !edges[i][j - 1] && !edges[i][j + 1];
        }
    }

    boolean isDone() {
        for (int i = 1; i <= N; i++) {
            int startNode = i;
            for (int j = 1; j <= H; j++) {
                if (edges[j][startNode]) {
                    startNode += 1;
                } else if (startNode - 1 >= 1 && edges[j][startNode - 1]) {
                    startNode -= 1;
                }
            }
            if (startNode != i) {
                return false;
            }
        }


        return true;
    }

}