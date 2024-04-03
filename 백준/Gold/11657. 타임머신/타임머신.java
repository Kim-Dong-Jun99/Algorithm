import java.io.*;
import java.util.*;

import static java.lang.Long.MAX_VALUE;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, M;
    long[] distance;
    Edge[] edges;

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
        distance = new long[N+1];
        Arrays.fill(distance, MAX_VALUE);
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            edges[i] = new Edge(inputArray[0], inputArray[1], inputArray[2]);
        }
    }

    void solve() throws IOException {
        distance[1] = 0;
        for (int i = 1; i <= N; i++) {
            for (Edge e : edges) {
                if (distance[e.from] != MAX_VALUE && distance[e.to] > distance[e.from] + e.value) {
                    distance[e.to] = distance[e.from] + e.value;
                }
            }
        }

        if (containsNegativeCycle()) {
            BW.write("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (distance[i] == MAX_VALUE) {
                    BW.write("-1\n");
                } else {
                    BW.write(Long.toString(distance[i])+"\n");
                }
            }
        }

    }

    boolean containsNegativeCycle() {
        for (Edge e : edges) {
            if (distance[e.from] != MAX_VALUE && distance[e.to] > distance[e.from] + e.value) {
                return true;
            }
        }
        return false;
    }

    void printResult() throws IOException {

        BW.flush();
        BW.close();
        BR.close();
    }

    static class Edge {
        int from;
        int to;
        int value;

        Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}