import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int N;
    int[] u;
    HashSet<Integer> sum;
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
        u = new int[N];
        sum = new HashSet<>();
        for (int i = 0; i < N; i++) {
            u[i] = Integer.parseInt(BR.readLine());
        }
        Arrays.sort(u);
        initSet();
	}
    
    void initSet() {
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sum.add(u[i]+u[j]);
            }
        }
    }
	
	void solve() throws IOException {
        for (int i = N - 1; i > -1; i--) {
            for (int j = i; j > -1; j --) {
                int toFind = u[i] - u[j];
                if (sum.contains(toFind)) {
                    System.out.println(u[i]);
                    System.exit(0);
                }
            }
        }
	}
}