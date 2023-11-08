import java.util.*;

/*
*/

class Solution {
    int n;
    HashMap<Integer, List<Integer>> graph;
    int[] subTreeSize;
    int[] depth;
    int[][] wires;
    public int solution(int n, int[][] wires) {
        init(n, wires);
        return solve();
    }

    void init(int n, int[][] wires) {
        this.n = n;
        this.wires = wires;
        subTreeSize = new int[n + 1];
        depth = new int[n + 1];
        graph = new HashMap<>();
        initGraph(wires);

        boolean[] visited = new boolean[n + 1];
        initTree(0, 1, visited);
    }

    void initGraph(int[][] connected) {
        for (int[] connect : connected) {
            int from = connect[0];
            int to = connect[1];

            List<Integer> fromConnected = graph.getOrDefault(from, new ArrayList<>());
            fromConnected.add(to);
            graph.put(from, fromConnected);

            List<Integer> toConnected = graph.getOrDefault(to, new ArrayList<>());
            toConnected.add(from);
            graph.put(to, toConnected);
        }
    }

    void initTree(int depth, int node, boolean[] visited) {
        visited[node] = true;
        this.depth[node] = depth;
        List<Integer> connected = graph.get(node);
        int subTree = 1;
        for (int to : connected) {
            if (!visited[to]) {
                initTree(depth + 1, to, visited);
                subTree += subTreeSize[to];
            }
        }
        subTreeSize[node] = subTree;

    }

    int solve() {
        int result = n;
        for (int[] wire : wires) {
            int from = wire[0];
            int to = wire[1];

            if (depth[from] < depth[to]) {
                result = Math.min(result, Math.abs( subTreeSize[1] - subTreeSize[to]*2));
            } else {
                result = Math.min(result, Math.abs(subTreeSize[1] - subTreeSize[from]*2));
            }
        }
        return result;
    }
}