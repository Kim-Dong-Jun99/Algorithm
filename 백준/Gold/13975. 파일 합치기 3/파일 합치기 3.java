import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int T, K;
    PriorityQueue<Long> heap;
    long[] files;
    long answer;
    
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
    
   long[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    }
    
	void init() throws IOException {
        K = Integer.parseInt(BR.readLine());
        files = getInputArray();
        heap = new PriorityQueue<>();
        for (Long file : files) {
            heap.add(file);
        }
        answer = 0L;
	}
	
	void solve() throws IOException {
		while (heap.size() > 1) {
            long s1 = heap.remove();
            long s2 = heap.remove();
            answer += (s1 + s2);
            heap.add(s1+s2);
        }
        BW.write(Long.toString(answer)+"\n");
	}
	
	void printResult() throws IOException {	
		BW.flush();
		BW.close();
		BR.close();
	}
}