import java.util.*;

class Solution {
    int answer;
    int N, K;
    HashMap<Integer, List<Road>> roadMap;
    int[] dijkstra;
    public int solution(int N, int[][] road, int K) {
        init(N, road, K);
        solve();
        return answer;
    }
    
    void init(int N, int[][] roads, int K) {
        this.N = N;
        this.K = K;
        this.dijkstra = new int[N+1];
        Arrays.fill(dijkstra, 1_000_000);
        answer = 0;
        roadMap = new HashMap<>();
        for(int[] road : roads) {
            roadMap.putIfAbsent(road[0], new ArrayList<>());
            roadMap.putIfAbsent(road[1], new ArrayList<>());
            
            roadMap.get(road[0]).add(new Road(road[1], road[2]));
            roadMap.get(road[1]).add(new Road(road[0], road[2]));
        }
    }
    
    void solve() {
        PriorityQueue<Node> heap = new PriorityQueue<>(Node::compareWithValue);
        heap.add(new Node(1, 0));
        while (!heap.isEmpty()) {
            Node removed = heap.remove();
            if (dijkstra[removed.index] <= removed.value) {
                continue;
            }
            dijkstra[removed.index] = removed.value;
            for (Road road : roadMap.getOrDefault(removed.index, new ArrayList<>())) {
                heap.add(new Node(road.to, road.time + removed.value));
            }
            
        }
        for (int i = 1; i <= N; i++) {
            if (dijkstra[i] <= K) {
                answer += 1;
            }
        }
    }
    
    static class Road {
        int to;
        int time;
        
        Road(int to, int time) {
            this.to = to;
            this.time = time;
        }   
    }
    
    static class Node {
        int index;
        int value;
        
        Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
        
        int compareWithValue(Node compare) {
            return Integer.compare(this.value, compare.value);
        }
    }
}