import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int T;
    int[] dijkstra;
    int n, d, c;
    HashMap<Integer, HashMap<Integer, Integer>> computerMap;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.test();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void test() throws IOException {
        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            init();
            solve();
        }
    }

    void init() throws IOException {
        inputArray = getInputArray();
        n = inputArray[0];
        d = inputArray[1];
        c = inputArray[2];
        computerMap = new HashMap<>();
        dijkstra = new int[n+1];
        Arrays.fill(dijkstra, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            computerMap.put(i, new HashMap<>());
        }
        for (int i = 0; i < d; i++) {
            inputArray = getInputArray();
            int a = inputArray[0];
            int b = inputArray[1];
            int s = inputArray[2];
            computerMap.get(b).put(a, s);
        }
    }

    void solve() throws IOException {
        PriorityQueue<Node> heap = new PriorityQueue<>(Node::sort);
        HashSet<Integer> visited = new HashSet<>();
        int maxTime = 0;
        heap.add(new Node(0, c));
        while (!heap.isEmpty()) {
            Node removed = heap.remove();
            if (dijkstra[removed.index] <= removed.time) {
                continue;
            }
            visited.add(removed.index);
            maxTime = Math.max(removed.time, maxTime);
            dijkstra[removed.index] = removed.time;
            for (Integer key : computerMap.get(removed.index).keySet()) {
                int value = computerMap.get(removed.index).get(key);
                if (dijkstra[key] > removed.time + value) {
                    heap.add(new Node(removed.time + value, key));
                }
            }
        }
        BW.write(Integer.toString(visited.size())+" "+Integer.toString(maxTime)+"\n");
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Node {
        int time;
        int index;

        Node(int time, int index) {
            this.time = time;
            this.index = index;
        }

        int sort(Node compare) {
            return Integer.compare(this.time, compare.time);
        }
    }
}