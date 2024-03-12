import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int N, M;
    Map<Integer, List<Integer>> ascMap;
    Map<Integer, List<Integer>> descMap;
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
        N = inputArray[0];
        M = inputArray[1];
        ascMap = new HashMap<>();
        descMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            ascMap.put(i, new ArrayList<>());
            descMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            int from = inputArray[0];
            int to = inputArray[1];
            ascMap.get(from).add(to);
            descMap.get(to).add(from);
            
        }
	}
	
	void solve() throws IOException {
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int smallerThan = getSmaller(i);
            int biggerThan = getBigger(i);
            if (smallerThan + biggerThan == N+1) {
                answer += 1;
            }
        }
        System.out.println(answer);
	}
    
    int getSmaller(int index) {
        int sum = 0;
        boolean[] visited = new boolean[N+1];
        List<Integer> currentPositions = Collections.singletonList(index);
        while (!currentPositions.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (Integer current : currentPositions) {
                sum += 1;
                for (Integer next : descMap.get(current)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        temp.add(next);
                        
                    }
                }
            }
            currentPositions = temp;
        }
        return sum;
    }
    
    int getBigger(int index) {
        int sum = 0;
        boolean[] visited = new boolean[N+1];
        List<Integer> currentPositions = Collections.singletonList(index);
        while (!currentPositions.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (Integer current : currentPositions) {
                sum += 1;
                for (Integer next : ascMap.get(current)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        temp.add(next);
                        
                    }
                }
            }
            currentPositions = temp;
        }
        return sum;
    }
}