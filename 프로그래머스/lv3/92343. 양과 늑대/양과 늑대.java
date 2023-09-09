import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    int answer;
    int[] info;
    int[][] edges;
    HashMap<Integer, List<Integer>> childMap;
    int[][] dp;

    public int solution(int[] info, int[][] edges) {
        init(info, edges);
        solve();
        return answer;
    }

    void init(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;
        childMap = new HashMap<>();
        initTree();
        dp = new int[info.length][2];
        dp[0][0] = 1;
        answer = 1;
    }

    void initTree() {
        for (int[] edge : edges) {
            List<Integer> child = childMap.getOrDefault(edge[0], new ArrayList<>());
            child.add(edge[1]);
            childMap.put(edge[0], child);
        }

    }

    void solve() {
        answer = dfs(0, 0, 0, childMap.get(0));


    }

    int dfs(int sheep, int wolf, int current, List<Integer> nextNode) {
        if (info[current] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (sheep <= wolf) {
            return sheep;
        }
        int toReturn = sheep;
        for (Integer child : nextNode) {
            List<Integer> newNodes = new ArrayList<>(nextNode);
            newNodes.remove(child);
            newNodes.addAll(childMap.getOrDefault(child, new ArrayList<>()));
            toReturn = Math.max(toReturn, dfs(sheep, wolf, child, newNodes));
        }
        return toReturn;
    }

}