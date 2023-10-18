
import java.util.*;
import java.util.Map.Entry;

/*
출발지에서 출발해서 종점으로 이동해야한다

함정으로 이동하면 이동한 함정과 연결된 모든 화살표의 방향이 바뀜

n <= 1000
roads <= 3000
trap의 개수 <= 10,
항상 도착가능한 경우만 주어짐

간선의 변경이 흔하게 일어날 수 있음

dijkstra

*/

class Solution {
    int n, start, end;
    Point[] points;
    boolean[] isTrap;
    PriorityQueue<DijkstraNode> heap;
    HashMap<Integer, Integer> flipIndexMap;
    int[][] distance;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        init(n, start, end, roads, traps);
        return solve();
    }

    void init(int n, int start, int end, int[][] roads, int[] traps) {
        this.n = n;
        this.start = start;
        this.end = end;
        points = new Point[n + 1];
        isTrap = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            points[i] = new Point(i);
        }
        heap = new PriorityQueue<>(DijkstraNode::compareWithDistance);
        flipIndexMap = new HashMap<>();
        distance = new int[1 << traps.length][n + 1];

        for (int[] road : roads) {
            int P = road[0];
            int Q = road[1];
            int S = road[2];

            points[P].roadMap.put(Q, Math.min(S, points[P].roadMap.getOrDefault(Q, 3001)));

            points[Q].inDegree.add(P);

        }

        for (int i = 0; i < traps.length; i++) {
            isTrap[traps[i]] = true;
            flipIndexMap.put(traps[i], i);
        }
        for (int[] d : distance) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }


    }

    int solve() {
        heap.add(new DijkstraNode(start, 0, 0));
        while (!heap.isEmpty()) {
            DijkstraNode currentNode = heap.remove();
            if (isTrap[currentNode.index]) {
                int flipIndex = flipIndexMap.get(currentNode.index);
                if (currentNode.flipped == (currentNode.flipped | (1 << flipIndex))) {
                    currentNode.flipped &= ~(1 << flipIndex);
                } else {
                    currentNode.flipped |= (1 << flipIndex);
                }
            }
            if (currentNode.index == end) {
                return currentNode.d;
            }
            if (distance[currentNode.flipped][currentNode.index] <= currentNode.d) {
                continue;
            }
            Point currentPoint = points[currentNode.index];
            for (Entry<Integer, Integer> flipEntry : flipIndexMap.entrySet()) {
                Integer pointIndex = flipEntry.getKey();
                Integer flipIndex = flipEntry.getValue();
                if (currentNode.flipped == (currentNode.flipped | (1 << flipIndex))) {
                    flipRoad(points[pointIndex]);
                }
            }
            distance[currentNode.flipped][currentNode.index] = currentNode.d;
            PriorityQueue<Road> roadHeap = new PriorityQueue<>(Road::compareWithD);

            for (Entry<Integer, Integer> roadEntry : currentPoint.roadMap.entrySet()) {
                Integer to = roadEntry.getKey();
                Integer d = roadEntry.getValue();
                roadHeap.add(new Road(to, d));
            }
            while (!roadHeap.isEmpty()) {
                Road road = roadHeap.remove();
                heap.add(new DijkstraNode(road.to, distance[currentNode.flipped][currentNode.index] + road.d, currentNode.flipped));
            }
            for (Entry<Integer, Integer> flipEntry : flipIndexMap.entrySet()) {
                Integer pointIndex = flipEntry.getKey();
                Integer flipIndex = flipEntry.getValue();
                if (currentNode.flipped == (currentNode.flipped | (1 << flipIndex))) {
                    flipRoad(points[pointIndex]);
                }
            }
        }
        int toReturn = Integer.MAX_VALUE;
        for (int[] d : distance) {
            toReturn = Math.min(toReturn, d[end]);
        }
        return toReturn;
    }

    void flipRoad(Point point) {
        HashMap<Integer, Integer> fromPointMap = point.roadMap;
        HashMap<Integer, Integer> toPointMap = new HashMap<>();
        for (Integer inDegree : point.inDegree) {
            Point inDegreePoint = points[inDegree];
            toPointMap.put(inDegree, inDegreePoint.roadMap.get(point.index));
        }
        point.roadMap = new HashMap<>();
        point.inDegree = new HashSet<>();

         /*
        point로 들어오는 길을 뒤집어야함
         */
        for (Entry<Integer, Integer> roadEntry : toPointMap.entrySet()) {
            Integer to = roadEntry.getKey();
            Integer d = roadEntry.getValue();

            Point toPoint = points[to];
            point.roadMap.put(to, d);
            toPoint.inDegree.add(point.index);
            if (!fromPointMap.containsKey(toPoint.index)) {
                toPoint.roadMap.remove(point.index);
            }

        }
        /*
        point에서 출발한 길을 뒤집어야함,
         */
        for (Entry<Integer, Integer> roadEntry : fromPointMap.entrySet()) {
            Integer from = roadEntry.getKey();
            Integer d = roadEntry.getValue();

            Point fromPoint = points[from];
            point.inDegree.add(fromPoint.index);
            fromPoint.roadMap.put(point.index, d);
            if (!toPointMap.containsKey(fromPoint.index)) {
                fromPoint.inDegree.remove(point.index);
            }
        }

    }


    static class Point {
        int index;
        HashSet<Integer> inDegree;
        HashMap<Integer, Integer> roadMap;

        public Point(int index) {
            this.index = index;
            this.roadMap = new HashMap<>();
            this.inDegree = new HashSet<>();
        }
    }

    static class Road {
        int to;
        int d;

        public Road(int to, int d) {
            this.to = to;
            this.d = d;
        }

        int compareWithD(Road compare) {
            return Integer.compare(this.d, compare.d);
        }
    }

    static class DijkstraNode {
        int index;
        int d;
        int flipped;

        public DijkstraNode(int index, int d, int flipped) {
            this.index = index;
            this.d = d;
            this.flipped = flipped;
        }

        int compareWithDistance(DijkstraNode compare) {
            if (this.d != compare.d) {
                return Integer.compare(this.d, compare.d);

            }
            return Integer.compare(this.flipped, compare.flipped);
        }
    }

}