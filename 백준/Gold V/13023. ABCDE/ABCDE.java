import java.io.*;
import java.util.*;

/*
	친구 관계 depth가 5 이상인지 구하기,
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, M;
    boolean[] visited;
    Map<Integer, List<Integer>> connected;

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
        visited = new boolean[N];
        connected = new HashMap<>();
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            int a = inputArray[0];
            int b = inputArray[1];

            connected.putIfAbsent(a, new ArrayList<>());
            connected.putIfAbsent(b, new ArrayList<>());

            connected.get(a).add(b);
            connected.get(b).add(a);
        }

    }

    void solve() throws IOException {
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            int depth = dfs(i, 1);
            if (depth >= 5) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
        return;
    }

    int dfs(int index, int depth) {
        if (depth == 5) {
            return depth;
        }
        visited[index] = true;
        int group = 0;
        for (Integer next : connected.getOrDefault(index, new ArrayList<>())) {
            if (!visited[next]) {
                group = Math.max(group, dfs(next, depth+1));
            }
        }
        visited[index] = false;
        return group;

    }
}