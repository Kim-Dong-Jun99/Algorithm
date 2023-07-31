import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    int K;
    HashMap<Integer, List<Road>> roads;
    long[][] dijkstra;
    PriorityQueue<Node> toSearch;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    void init() throws IOException {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        K = inputArray[2];
        roads = new HashMap<>();
        dijkstra = new long[N + 1][K + 1];
        toSearch = new PriorityQueue<>(Node::compareTo);
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dijkstra[i], Long.MAX_VALUE);
        }
        for (int i = 0; i < M; i++) {
            int[] tempInputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = tempInputArray[0];
            int to = tempInputArray[1];
            int value = tempInputArray[2];

            List<Road> neighborsOfFrom = roads.getOrDefault(from, new ArrayList<>());
            neighborsOfFrom.add(new Road(to, value));
            roads.put(from, neighborsOfFrom);

            List<Road> neighborsOfTo = roads.getOrDefault(to, new ArrayList<>());
            neighborsOfTo.add(new Road(from, value));
            roads.put(to, neighborsOfTo);
        }
    }

    void solution() throws IOException {
        toSearch.add(new Node(1, 0, 0));
        dijkstra[1][0] = 0;
        while (!toSearch.isEmpty()) {
            Node currentNode = toSearch.remove();
            if (dijkstra[currentNode.nodeNumber][currentNode.kCount] < currentNode.value) {
                continue;
            }
            
            List<Road> neighborRoads = roads.get(currentNode.nodeNumber);
            for (Road neighborRoad : neighborRoads) {
                if (dijkstra[currentNode.nodeNumber][currentNode.kCount] + neighborRoad.value < dijkstra[neighborRoad.to][currentNode.kCount]) {
                    dijkstra[neighborRoad.to][currentNode.kCount] = dijkstra[currentNode.nodeNumber][currentNode.kCount] + neighborRoad.value;
                    toSearch.add(new Node(neighborRoad.to, currentNode.kCount, dijkstra[neighborRoad.to][currentNode.kCount]));
                }
                if (currentNode.kCount < K  && dijkstra[currentNode.nodeNumber][currentNode.kCount] < dijkstra[neighborRoad.to][currentNode.kCount + 1]) {
                    dijkstra[neighborRoad.to][currentNode.kCount + 1] = dijkstra[currentNode.nodeNumber][currentNode.kCount];

                    toSearch.add(new Node(neighborRoad.to, currentNode.kCount + 1, dijkstra[neighborRoad.to][currentNode.kCount + 1]));

                }
            }
        }
        System.out.println(Arrays.stream(dijkstra[N]).min().getAsLong());
    }

    static class Road {
        int to;
        int value;

        public Road(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

    static class Node {
        int nodeNumber;
        int kCount;
        long value;

        public Node(int nodeNumber, int kCount, long value) {
            this.nodeNumber = nodeNumber;
            this.kCount = kCount;
            this.value = value;
        }

        public int compareTo(Node compare) {
            return Long.compare(this.value, compare.value);
        }
    }
}
