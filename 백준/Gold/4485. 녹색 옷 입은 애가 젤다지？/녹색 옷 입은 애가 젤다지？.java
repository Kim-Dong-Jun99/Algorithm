import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};

    int N;
    int[][] rupee;
    int[][] loss;
    int testCase;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.testCase();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void testCase() throws IOException {
        testCase = 0;
        while (true) {
            testCase += 1;
            N = Integer.parseInt(BR.readLine());
            if (N == 0) {
                BW.flush();
                return;
            }
            init();
            solve();
        }
    }

    void init() throws IOException {
        rupee = new int[N][N];
        loss = new int[N][N];
        for (int i = 0; i < N; i++) {
            rupee[i] = getInputArray();
            Arrays.fill(loss[i], 1_000_000);
        }
    }

    void solve() throws IOException {
        dijkstra();
        BW.write("Problem "+testCase+": "+loss[N-1][N-1]+"\n");
    }

    void dijkstra() {
        PriorityQueue<Node> heap = new PriorityQueue<>(Node::compareWithValue);
        heap.add(new Node(0, 0, rupee[0][0]));
        while (!heap.isEmpty()) {
            Node removed = heap.remove();
            if (loss[removed.x][removed.y] <= removed.value) {
                continue;
            }
            loss[removed.x][removed.y] = removed.value;
            for (int d = 0; d < 4; d++) {
                int newX = removed.x + DX[d];
                int newY = removed.y + DY[d];
                if (isInner(newX, newY)) {
                    heap.add(new Node(newX, newY, removed.value + rupee[newX][newY]));
                }
            }
        }

    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Node {
        int x;
        int y;
        int value;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        int compareWithValue(Node compare) {
            return Integer.compare(this.value, compare.value);
        }
    }
}