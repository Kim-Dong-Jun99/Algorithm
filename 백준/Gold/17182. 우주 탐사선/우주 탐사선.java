import java.util.*;
import java.io.*;

/*
모든 행성을 탐사하는데 걸리는 최소 시간을 계싼하기
 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int N, K;
    int[][] graph;
    int[][] minValue;
    int[] inputArray;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        K = inputArray[1];
        minValue = new int[N][1 << N];
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            graph[i] = getInputArray();
            Arrays.fill(minValue[i], 1_000_000_000);
        }

    }


    void solution() {
        backtrack(K, (1 << K), 0);
        int result = 1_000_000_000;
        for (int i = 0; i < N; i++) {
            result = Math.min(result, minValue[i][(1 << N) - 1]);
        }
        System.out.println(result);
    }

    void backtrack(int current, int visited, int time) {
        minValue[current][visited] = time;
        for (int i = 0; i < N; i++) {
            if (minValue[i][(visited | (1 << i))] > minValue[current][visited] + graph[current][i]) {
                backtrack(i, visited | (1 << i), minValue[current][visited]+ graph[current][i]);
            }
        }

    }

}