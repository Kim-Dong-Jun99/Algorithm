import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int n, k;
    List<Integer> cand;
    int[] dp;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
		main.printResult();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        inputArray = getInputArray();
        n = inputArray[0];
        k = inputArray[1];
        
        cand = new ArrayList<>();
        dp = new int[k+1];
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(BR.readLine());
            if (temp <= k) {
                cand.add(temp);
            }
        }
	}
	
	void solve() throws IOException {
		dp[0] = 1;
        for (Integer i : cand) {
            for (int j = 1; j <= k; j++) {
                if (j >= i) {
                    dp[j] += dp[j-i];
                }
            }
        }
        BW.write(Integer.toString(dp[k]));
	}
	
	void printResult() throws IOException {	
		BW.flush();
		BW.close();
		BR.close();
	}
}