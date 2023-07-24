import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int V;
    long answer;
    HashMap<Integer, Node> nodeHashMap;
    HashSet<Integer> visited;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (Exception e) {
            System.out.println("exception during I/O");
        }

    }

    void init() throws Exception {
        V = Integer.parseInt(BR.readLine());
        answer = 0L;
        nodeHashMap = new HashMap<>();
        visited = new HashSet<>();

        for (int i = 0; i < V; i++) {
            int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = inputArray[0];

            if (!nodeHashMap.containsKey(from)) {
                Node newNode = new Node(from);
                nodeHashMap.put(from, newNode);
            }

            Node node = nodeHashMap.get(from);

            for (int j = 1; j + 1 < inputArray.length; j += 2) {
                node.neighbor.put(inputArray[j], inputArray[j + 1]);
            }

        }

    }

    void solution() throws Exception {
        Node root = nodeHashMap.get(1);
        visited.add(1);
        long fromRoot = travel(root);
        if (fromRoot > answer) {
            answer = fromRoot;
        }
        System.out.println(answer);
    }

    long travel(Node parent) {
        HashMap<Integer, Integer> neighbors = parent.getNeighbor();
        PriorityQueue<Long> maxSubTreeDistance = new PriorityQueue<>(Collections.reverseOrder());

        for (Integer child : neighbors.keySet()) {
            if (!visited.contains(child)) {
                visited.add(child);
                Node nextNode = nodeHashMap.get(child);
                long travelDistance = travel(nextNode);
                travelDistance += neighbors.get(child);
                maxSubTreeDistance.add(travelDistance);
            }
        }

        if (maxSubTreeDistance.isEmpty()) {
            return 0L;
        } else {
            long toReturn = maxSubTreeDistance.peek();
            if (maxSubTreeDistance.size() >= 2) {
                long subTreeDistance1 = maxSubTreeDistance.remove();
                long subTreeDistance2 = maxSubTreeDistance.remove();
                if (subTreeDistance1 + subTreeDistance2 > answer) {
                    answer = subTreeDistance1 + subTreeDistance2;
                }
            }
            return toReturn;
        }
    }

    static class Node {
        int number;
        HashMap<Integer, Integer> neighbor;

        public Node(int number) {
            this.number = number;
            this.neighbor = new HashMap<>();
        }

        public int getNumber() {
            return number;
        }

        public HashMap<Integer, Integer> getNeighbor() {
            return neighbor;
        }
    }


}