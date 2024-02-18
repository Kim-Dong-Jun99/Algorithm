import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int n, m;
    int[] boss;
    int[] complemented;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        inputArray = getInputArray();
        n = inputArray[0];
        m = inputArray[1];
        boss = getInputArray();
        for (int i = 0; i < n; i++) {
            boss[i] -= 1;
        }
        complemented = new int[n];
        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            complemented[inputArray[0]-1] += inputArray[1];
        }
	}
	
	void solve() throws IOException {
        for (int i = 0; i < n; i++) {
            if (0 <= boss[i]) {
                complemented[i] += complemented[boss[i]];
            }
            BW.write(Integer.toString(complemented[i])+" ");
            
        }
        BW.flush();
        BW.close();
        BR.close();
    }
	
}