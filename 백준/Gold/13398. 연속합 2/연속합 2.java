import java.io.*;
import java.util.*;
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int n;
    int[] numbers;
    int[][] dp;
    int result;
    
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
        numbers = getInputArray();
        dp = new int[n][2];
        result = numbers[0];
	}
    
	void solve() throws IOException {
        for (int i = 0; i < n; i++) {
            dp[i][0] = numbers[i];
            dp[i][1] = numbers[i];
            if (i == 0) {
                continue;
            } else {
                dp[i][0] = Math.max(dp[i - 1][0] + numbers[i], numbers[i]);
                dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + numbers[i]);
            }
            result = Math.max(result, dp[i][0]);
            result = Math.max(result, dp[i][1]);
        }
        System.out.println(result);
	}
}