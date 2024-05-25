import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int T;
    int n;
    int[] f;
    int[][] d;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
        main.testCase();
		main.printResult();
	}
    
    void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            init();
            solve();
        }
    }
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        f = getInputArray();
        d = new int[n+1][n+1];
        
	}
	
	void solve() throws IOException {
		for (int i = 0; i < n-1; i++) {
            d[i][i+1] = f[i] + f[i+1];
            for (int j = i+2; j < n; j++) {
                d[i][j] = d[i][j-1] + f[j];
            }
        }
        
        for (int v = 2; v < n; v++) {
            for (int i = 0; i < n-v; i++) {
                int j = i+v;
                int temp = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    temp = Math.min(temp, d[i][k] + d[k+1][j]);
                }
                d[i][j] += temp;
            }
        }
        BW.write(Integer.toString(d[0][n-1])+"\n");
	}
	
	void printResult() throws IOException {	
		BW.flush();
		BW.close();
		BR.close();
	}
}