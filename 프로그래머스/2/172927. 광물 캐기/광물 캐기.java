import java.util.*;

/*
곡괭이로 광산에서 광석을 캐야함, 

다이아, 철, 돌 곡괭이를 각각 0 ~ 5개 가지고 있음, 

곡괭이로 광물을 캘 때는 피로도가 소모된다, 

광물 5개를 캔 후에는 더 이상 사용 불가

사용 가능한 곡괭이 중 아무거나 하나 선택,
한번 사용 시작한 곡괭이는 사용할 수 없을때까지 사용,
주어진 순서대로만 캘 수 있음,
*/

class Solution {
    public static final int[][] ENERGY = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    public static final String DIAMOND = "diamond";
    public static final String IRON = "iron";
    public static final String STONE = "stone";
    
    int answer, n, m;
    int[] picks;
    int[] minerals;
    int[] tools;
    
    public int solution(int[] picks, String[] minerals) {
        init(picks, minerals);
        solve();
        return answer;
    }
    
    void init(int[] picks, String[] minerals) {
        this.picks = picks;        
        this.n  = minerals.length;
        this.minerals = new int[n];
        for (int i = 0; i < n; i++) {
            String mineral = minerals[i];
            if (mineral.equals(DIAMOND)) {
                this.minerals[i] = 0;
            }
            if (mineral.equals(IRON)) {
                this.minerals[i] = 1;
            }
            if (mineral.equals(STONE)) {
                this.minerals[i] = 2;
            }
        }
        this.m = 0;
        for (int pick : picks) {
            m += pick;
        }
        this.m = Math.min(m, (int) Math.ceil(n / 5.0));
        this.tools = new int[m];
    }
    
    void solve() {
        answer = dfs(0);
    }
    
    int dfs(int index) {
        if (index == m) {
            int result = 0;
            for (int i = 0; i < m; i++) {
                for (int j = i * 5; j < i * 5 + 5; j++) {
                    if (j >= n) {
                        break;
                    }
                    result += ENERGY[tools[i]][minerals[j]];
                }
            }
            return result;
        }
        int result = 25 * 50;
        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                picks[i] -= 1;
                tools[index] = i;
                result = Math.min(result, dfs(index+1));
                picks[i] += 1;
            }
        }
        
        return result;
    }
}