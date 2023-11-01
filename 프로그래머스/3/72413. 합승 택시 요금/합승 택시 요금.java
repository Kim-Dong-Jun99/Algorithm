import java.util.*;

/*
택시 합승을 해서 돈이 아껴지는지를 판단하고 합승하려고함

S에서 출발해서 A,B로 가야함

S에서 A,B모두 도착하는 최소를 구함

n - 지점 개수, <= 200

다익스트라


*/

class Solution {
    int n, s, a, b;
    Graph graph;
    int[] dijkstraFromS;
    int[] dijkstraFromA;
    int[] dijkstraFromB;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n, s, a, b, fares);
        return solve();
    }
    void init(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        this.s = s;
        this.a = a;
        this.b = b;
        graph = new Graph(n);
        dijkstraFromS = new int[n + 1];
        dijkstraFromA = new int[n + 1];
        dijkstraFromB = new int[n + 1];
        Arrays.fill(dijkstraFromS, Integer.MAX_VALUE);
        Arrays.fill(dijkstraFromA, Integer.MAX_VALUE);
        Arrays.fill(dijkstraFromB, Integer.MAX_VALUE);
        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];

            graph.addRoad(c, d, f);
            graph.addRoad(d, c, f);
        }
        initDijkstra(dijkstraFromS, s);
        initDijkstra(dijkstraFromA, a);
        initDijkstra(dijkstraFromB, b);
    }

    void initDijkstra(int[] dijkstra, int s) {
        PriorityQueue<DijkstraNode> heap = new PriorityQueue<>(DijkstraNode::compareWithValue);
        heap.add(new DijkstraNode(s, 0));
        while (!heap.isEmpty()) {
            DijkstraNode node = heap.remove();
            if (node.value >= dijkstra[node.to]) {
                continue;
            }
            dijkstra[node.to] = node.value;
            for (Road road : graph.getRoads(node.to)) {
                if (dijkstra[road.to] > node.value + road.value) {
                    heap.add(new DijkstraNode(road.to, node.value + road.value));
                }
            }
        }
    }




    int solve() {
        int minValue = dijkstraFromS[a] + dijkstraFromS[b];
        for (int i = 1; i <= n; i++) {
            if (i == s || dijkstraFromS[i] == Integer.MAX_VALUE) {
                continue;
            }
            if (i == a || i == b) {
                minValue = Math.min(minValue, dijkstraFromA[b] + Math.min(dijkstraFromS[a], dijkstraFromS[b]));

            } else {

                minValue = Math.min(minValue, dijkstraFromA[i] + dijkstraFromB[i] + dijkstraFromS[i]);
            }

        }
        return minValue;
    }



    static class Road {
        int to;
        int value;

        public Road(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

    static class DijkstraNode {
        int to;
        int value;

        public DijkstraNode(int to, int value) {
            this.to = to;
            this.value = value;
        }

        int compareWithValue(DijkstraNode compare) {
            return Integer.compare(this.value, compare.value);
        }
    }

    static class Graph {
        private List<Road>[] roads;

        public Graph(int n) {
            roads = new List[n + 1];
        }

        void addRoad(int from, int to, int value) {
            if (roads[from] == null) {
                roads[from] = new ArrayList<>();
            }
            roads[from].add(new Road(to, value));
        }

        List<Road> getRoads(int from) {
            if (roads[from] == null) {
                return new ArrayList<>();
            }
            return roads[from];
        }
    }


}


