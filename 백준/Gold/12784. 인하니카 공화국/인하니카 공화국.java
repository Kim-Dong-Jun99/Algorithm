import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int T, N, M;
    HashMap<Integer, List<Bridge>> bridgeMap;
    boolean[] visited;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.test();
    }

    void test() throws IOException {
        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            init();
            solve();
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        M = inputArray[1];
        bridgeMap = new HashMap<>();
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            bridgeMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            int from = inputArray[0];
            int to = inputArray[1];
            int bomb = inputArray[2];

            bridgeMap.get(from).add(new Bridge(to, bomb));
            bridgeMap.get(to).add(new Bridge(from, bomb));
        }
    }

    void solve() throws IOException {
        int answer = 0;
        visited[1] = true;
        for (Bridge bridge : bridgeMap.get(1)) {
            answer += Math.min(bridge.bomb, dfs(bridge.to));
        }
        System.out.println(answer);
    }

    int dfs(int node) {
        if (bridgeMap.get(node).size() == 1) {
            return bridgeMap.get(node).get(0).bomb;

        }

        visited[node] = true;
        int sum = 0;
        for (Bridge bridge : bridgeMap.get(node)) {
            if (!visited[bridge.to]) {
                sum += Math.min(bridge.bomb, dfs(bridge.to));
            }
        }
        return sum;
    }

    static class Bridge {
        int to;
        int bomb;

        Bridge(int to, int bomb) {
            this.to = to;
            this.bomb = bomb;
        }
    }
}