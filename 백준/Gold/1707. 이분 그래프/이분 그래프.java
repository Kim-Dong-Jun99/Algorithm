import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int K;
    int V;
    int E;
    HashMap<Integer, List<Integer>> graph;
    int[] color;

    public static void main(String[] args) {
        Main main = new Main();
        main.testCase();
    }

    void testCase() {
        try {
            K = Integer.parseInt(BR.readLine());
            while (K-- > 0) {
                init();
                solution();
            }
        } catch (IOException e) {
            System.out.println("exception during I/O");
        }
    }


    void init() throws IOException {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        V = inputArray[0];
        E = inputArray[1];
        graph = new HashMap<>();
        color = new int[V];
        for (int i = 0; i < E; i++) {
            int[] edges = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int left = edges[0] - 1;
            int right = edges[1] - 1;
            List<Integer> edge1Neighbor = graph.getOrDefault(left, new ArrayList<>());
            edge1Neighbor.add(right);
            graph.put(left, edge1Neighbor);

            List<Integer> edge2Neighbor = graph.getOrDefault(right, new ArrayList<>());
            edge2Neighbor.add(left);
            graph.put(right, edge2Neighbor);
        }
    }
    void solution()  {
        try {
            bfs();
            System.out.println("YES");
        } catch (IllegalStateException e) {
            System.out.println("NO");
        }

    }

    void bfs() throws IllegalStateException {
        for (int i = 0; i < V; i++) {
            if (color[i] == 0) {
                List<Integer> currentNodes = List.of(i);
                color[i] = 1;
                while (!currentNodes.isEmpty()) {

                    List<Integer> temp = new ArrayList<>();

                    for (Integer currentNode : currentNodes) {
                        if (graph.containsKey(currentNode)) {
                            List<Integer> neighbors = graph.get(currentNode);

                            for (Integer neighbor : neighbors) {
                                if (color[neighbor] == 0) {
                                    temp.add(neighbor);
                                    color[neighbor] = color[currentNode] * -1;
                                } else if (color[neighbor] * color[currentNode] == 1){
                                    throw new IllegalStateException();
                                }
                            }
                        }
                        
                    }
                    currentNodes = temp;
                }
            }
        }


    }

}
