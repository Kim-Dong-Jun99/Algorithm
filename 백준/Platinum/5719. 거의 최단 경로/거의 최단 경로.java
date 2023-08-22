import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T, N, M, S, D;
    int[] inputArray;
    HashMap<Integer, List<Road>> roadMap;
    HashMap<Integer, List<Road>> reverseRoadMap;
    PriorityQueue<Node> nodes;
    int[] dijkstra;
    int[] almostShort;
    int index;
    int[][] shortestPath;
    List<Integer> shortestNodes;
    HashSet<Integer> visited;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.testCase();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    void testCase() throws IOException {
        while (true) {
            inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (inputArray[0] == 0 && inputArray[1] == 0) {
                break;
            }
            init();
            solution();
        }

    }

    void init() throws IOException {
        N = inputArray[0];
        M = inputArray[1];

        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        S = inputArray[0];
        D = inputArray[1];
        roadMap = new HashMap<>();
        reverseRoadMap = new HashMap<>();
        nodes = new PriorityQueue<>(Node::compareWithValue);
        dijkstra = new int[N + 1];
        almostShort = new int[N + 1];
        Arrays.fill(almostShort, Integer.MAX_VALUE);
        Arrays.fill(dijkstra, Integer.MAX_VALUE);
        shortestPath = new int[N + 1][N + 1];
        index = 0;
        visited = new HashSet<>();
        for (int i = 0; i < M; i++) {
            inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            List<Road> roads = roadMap.getOrDefault(inputArray[0], new ArrayList<>());
            roads.add(new Road(inputArray[1], inputArray[2]));
            roadMap.put(inputArray[0], roads);


            List<Road> reverseRoads = reverseRoadMap.getOrDefault(inputArray[1], new ArrayList<>());
            reverseRoads.add(new Road(inputArray[0], inputArray[2]));
            reverseRoadMap.put(inputArray[1], reverseRoads);
        }
    }


    void solution() throws IOException {
        dijkstra();
        findPath();
        findAlmostShortValue();
    }

    void dijkstra() {
        nodes.add(new Node(S, 0));
        while (!nodes.isEmpty()) {
            Node current = nodes.remove();
            if (dijkstra[current.position] > current.value) {
                dijkstra[current.position] = current.value;
                for (Road road : roadMap.getOrDefault(current.position, new ArrayList<>())) {
                    nodes.add(new Node(road.to, road.value + current.value));
                }

            }
        }
    }

    void findPath() {
        shortestNodes = List.of(D);
        visited.add(D);
        while (!shortestNodes.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (Integer shortestNode : shortestNodes) {
                for (Road road : reverseRoadMap.getOrDefault(shortestNode, new ArrayList<>())) {
                    if (dijkstra[shortestNode] - dijkstra[road.to] == road.value) {
                        if (!visited.contains(road.to)) {
                            visited.add(road.to);
                            temp.add(road.to);
                        }
                        shortestPath[Math.min(shortestNode, road.to)][Math.max(shortestNode, road.to)] = 1;
                    }
                }
            }
            shortestNodes = temp;
        }
    }

    void findAlmostShortValue() {
        nodes.add(new Node(S, 0));
        while (!nodes.isEmpty()) {
            Node current = nodes.remove();
            if (almostShort[current.position] > current.value) {
                almostShort[current.position] = current.value;
                for (Road road : roadMap.getOrDefault(current.position, new ArrayList<>())) {
                    if (shortestPath[Math.min(road.to, current.position)][Math.max(road.to, current.position)] == 1) {
                        continue;
                    }
                    nodes.add(new Node(road.to, road.value + current.value));
                }
            }
        }
        if (almostShort[D] != Integer.MAX_VALUE) {
            System.out.println(almostShort[D]);
        } else {
            System.out.println(-1);
        }
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
        int position;
        int value;
        int pastPosition;

        public Node(int position, int value) {
            this.position = position;
            this.value = value;
        }

        public int compareWithValue(Node compare) {
            return Integer.compare(this.value, compare.value);
        }
    }
}
