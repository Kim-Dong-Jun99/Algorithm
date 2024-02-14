import java.io.*;
import java.util.*;
	
/*
	뮤탈이 3번 공격 가능, 1번 9, 2번 3, 3번 1만큼 체력 잃음, 
    모든 scv를 파괴하기 위한 최소 공격 횟수
    N <= 3, 체력 <= 60
    한번의 공격에서 같은 scv 연속 공격 불가
*/
	
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static final int[][] COMBI = {
        {9, 3, 1},
        {9, 1, 3},
        {1, 3, 9},
        {1, 9, 3},
        {3, 1, 9},
        {3, 9, 1}
    };
    
    int[] inputArray;
    int N;
    int[] health;
    int[][][] dp;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        health = new int[3];
        inputArray = getInputArray();
        for (int i = 0; i < N; i++) {
            health[i] = inputArray[i];
        }
        dp = new int[61][61][61];
	}
	
	void solve() throws IOException {
        System.out.println(dfs(health[0], health[1], health[2]));
	}
    
    int dfs(int a, int b, int c) {
        
        a = minusToZero(a);
        b = minusToZero(b);
        c = minusToZero(c);
        
        if (a == 0 && b == 0 && c == 0) {
            return 0;
        }
        
        if (dp[a][b][c] != 0) {
            return dp[a][b][c];
        }
        
        int min = 61;
        for (int[] comb : COMBI) {
            min = Math.min(min, dfs(a-comb[0], b-comb[1], c-comb[2]));
        }
        
        dp[a][b][c] = 1 + min;
        return dp[a][b][c];
        
        

    }
    
    int minusToZero(int i) {
        if (i < 0) {
            return 0;
        }
        return i;
    }
}