import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    
    int[] inputArray;
    int N, M;
    int[] dijkstra;
    Map<Integer, Integer>[] roadMap;
    
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
        roadMap = new Map[N+1];
        dijkstra = new int[N+1];
        for (int i = 0; i <= N; i++) {
            roadMap[i] = new HashMap<>();
            dijkstra[i] = 50_000_000;
        }
        
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            roadMap[inputArray[0]].put(inputArray[1], Math.min(roadMap[inputArray[0]].getOrDefault(inputArray[1], 50_000_000), inputArray[2]));
            roadMap[inputArray[1]].put(inputArray[0], Math.min(roadMap[inputArray[1]].getOrDefault(inputArray[0], 50_000_000), inputArray[2]));

        }
        
    }
    
    void solve() {
        PriorityQueue<Node> heap = new PriorityQueue<>(Node::compare);
        heap.add(new Node(1, 0));
        while (!heap.isEmpty()) {
            Node node = heap.remove();
            if (dijkstra[node.pos] <= node.value) {
                continue;
            }
            dijkstra[node.pos] = node.value;
            for (Integer next : roadMap[node.pos].keySet()) {
                if (dijkstra[next] > node.value + roadMap[node.pos].get(next)) {
                    heap.add(new Node(next, node.value + roadMap[node.pos].get(next)));
                }
            }
        }
        System.out.println(dijkstra[N]);
    }
    
    class Node {
        int pos;
        int value;
        
        Node(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }
        
        int compare(Node compare) {
            return Integer.compare(this.value, compare.value);
        }
    }
}