import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    
    long[] inputArray;
    long H, W, N, M;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
	}
    
    long[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    }
    
	void init() throws IOException {
        inputArray = getInputArray();
        H = inputArray[0];
        W = inputArray[1];
        N = inputArray[2];
        M = inputArray[3];
	}
	
	void solve() {
        long a = H / (N+1);
        if (H % (N+1) != 0 ) {
            a += 1;
        }
        long b = W / (M+1);
        if (W % (M+1) != 0) {
            b += 1;
        }
		System.out.println(a*b);
	}
}