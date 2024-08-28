import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    
    int[] inputArray;
    int n;
    String[][] strs;
    String a, b, c;
    int[][] dp;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        strs = new String[n][3];
        for (int i = 0; i < n; i++) {
            strs[i] = BR.readLine().split(" ");
        }
	}
	
	void solve() {
        
		for (int i = 0; i < n; i++) {
            String[] str = strs[i];
            a = str[0];
            b = str[1];
            c = str[2];
            dp = new int[a.length()+1][b.length()+1];
            int result = dfs(0, 0);
            if (result == 1) {
                System.out.println("Data set "+(i+1)+": yes");
            } else {
                System.out.println("Data set "+(i+1)+": no");
            }
        }
	}
    
    int dfs(int aIndex, int bIndex) {
        int cIndex = aIndex + bIndex;
        if (aIndex == a.length() && bIndex == b.length()) {
            return 1;
        }
        
        if (dp[aIndex][bIndex] != 0) {
            return dp[aIndex][bIndex];
        }
        int result = -1;
        if (aIndex < a.length() && a.charAt(aIndex) == c.charAt(cIndex)) {
            result = Math.max(result, dfs(aIndex+1, bIndex));
        }
        if (bIndex < b.length() && b.charAt(bIndex) == c.charAt(cIndex)) {
            result = Math.max(result, dfs(aIndex, bIndex+1));
        }
        dp[aIndex][bIndex] = result;
        return result;
        
        
    }
}