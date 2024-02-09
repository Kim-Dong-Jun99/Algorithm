import java.io.*;
import java.util.*;
class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, R, Q;
    HashMap<Integer, List<Integer>> graph;

    int[] dp;
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
        R = inputArray[1];
        Q = inputArray[2];

        graph = new HashMap<>();

        for (int i = 0; i < N-1; i++) {
            inputArray = getInputArray();
            int from = inputArray[0];
            int to = inputArray[1];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        dp = new int[N+1];
        visited = new boolean[N+1];

        initTree(R);
    }

    void initTree(int index) {
        visited[index] = true;
        int subtree = 1;
        List<Integer> childs = graph.get(index);
        for (Integer child : childs) {
            if (!visited[child]) {
                initTree(child);
                subtree += dp[child];
            }
        }

        dp[index] = subtree;

    }

    void solve() throws IOException {
        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(BR.readLine());
            BW.write(Integer.toString(dp[query])+"\n");
        }
        BW.flush();
        BW.close();
        BR.close();

    }
}