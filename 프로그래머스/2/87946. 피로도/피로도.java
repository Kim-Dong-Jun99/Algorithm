import java.util.*;

class Solution {
    int answer;
    int n, k;
    int[][] dungeons;
    boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        init(k, dungeons);
        solve();
        return answer;
    }
    
    void init(int k, int[][] dungeons) {
        this.answer = 0;
        this.n = dungeons.length;
        this.k = k;
        this.dungeons = dungeons;
        this.visited = new boolean[n];
    }
    
    void solve() {
        dfs(k, 0);
    }
    
    void dfs(int tired, int caught) {
        answer = Math.max(answer, caught);
        
        for (int i = 0; i < n; i++) {
            if (!visited[i] && tired >= dungeons[i][0]) {
                visited[i] = true;
                dfs(tired - dungeons[i][1], caught + 1);
                visited[i] = false;
            }
        }
    }
}