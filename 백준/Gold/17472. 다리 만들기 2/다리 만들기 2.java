import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int[][] map;
    HashMap<Integer, List<Bridge>> graph;
    HashMap<Integer, List<Coordinate>> edges;
    PriorityQueue<Bridge> bridges;
    int[] parent = {0, 1, 2, 3, 4, 5, 6};
    int answer;
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
        map = new int[N][M];
        bridges = new PriorityQueue<>(Bridge::compareTo);
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        graph = new HashMap<>();
        edges = new HashMap<>();
        recolorMap();
        initGraph();
        answer = 0;
    }

    void recolorMap() {
        int mapIndex = 1;
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    List<Coordinate> edge = new ArrayList<>();
                    visited[i][j] = 1;
                    List<Coordinate> currentNodes = List.of(new Coordinate(i, j));
                    while (!currentNodes.isEmpty()) {
                        List<Coordinate> temp = new ArrayList<>();
                        for (Coordinate currentNode : currentNodes) {
                            int edgeCount = 0;
                            map[currentNode.x][currentNode.y] = mapIndex;
                            for (int k = 0; k < 4; k++) {
                                int newX = currentNode.x + dx[k];
                                int newY = currentNode.y + dy[k];
                                if (isInner(newX, newY)) {
                                    if (visited[newX][newY] == 0) {
                                        if (map[newX][newY] == 1) {
                                            visited[newX][newY] = 1;
                                            temp.add(new Coordinate(newX, newY));
                                        } else {
                                            edgeCount += 1;
                                        }
                                    }
                                }
                            }
                            if (edgeCount >= 1) {
                                edge.add(currentNode);
                            }
                        }
                        currentNodes = temp;

                    }
                    edges.put(mapIndex, edge);
                    mapIndex += 1;
                }
            }
        }
    }

    void initGraph() {
        for (Integer islandNumber : edges.keySet()) {
            List<Coordinate> edge = edges.get(islandNumber);
            HashMap<Integer, PriorityQueue<Integer>> visitedIslands = new HashMap<>();
            for (Coordinate coordinate : edge) {
                for (int i = 0; i < 4; i++) {
                    int newX = coordinate.x + dx[i];
                    int newY = coordinate.y + dy[i];
                    int distance = 0;
                    while (isInner(newX, newY) && map[newX][newY] != islandNumber) {

                        if (map[newX][newY] == 0) {
                            newX += dx[i];
                            newY += dy[i];
                        } else {
                            if (distance >= 2) {

                                PriorityQueue<Integer> orDefault = visitedIslands.getOrDefault(map[newX][newY], new PriorityQueue<>());
                                orDefault.add(distance);
                                visitedIslands.put(map[newX][newY], orDefault);
                            }
                            break;
                        }
                        distance += 1;
                    }
                }
            }
            for (Integer to : visitedIslands.keySet()) {
//                System.out.println(islandNumber+" "+ to+" " +visitedIslands.get(to).peek());
                bridges.add(new Bridge(islandNumber, to, visitedIslands.get(to).peek()));
            }
        }
    }
    
    boolean isInner(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j <M;
    }

    void solution() throws IOException {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        while (!bridges.isEmpty()) {
            Bridge bridge = bridges.remove();
            if (find(bridge.from) != find(bridge.to)) {
//                System.out.println(bridge.from + " " + bridge.to + " " + bridge.value);
                union(bridge.from, bridge.to);
                answer += bridge.value;
            }
        }
        HashSet<Integer> connectedCheck = new HashSet<>();
        for (Integer island : edges.keySet()) {
//            System.out.println(island + " " + find(island));
            connectedCheck.add(find(island));
        }
        if (connectedCheck.size() == 1) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }

    }

    int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[px] = py;
        }
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Bridge {
        int from;
        int to;
        int value;

        public Bridge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        public int compareTo(Bridge compare) {
            return Integer.compare(this.value, compare.value);
        }
    }
}
