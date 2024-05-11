import java.io.*;
import java.util.*;

class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int[] inputArray;
    int N, M;
    HashMap<Integer, List<Edge>> nodeMap;
    int[][] targets;
    
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
        N = inputArray[0];
        M = inputArray[1];
        nodeMap = new HashMap<>();
        
        for (int i = 1; i <= N; i++) {
        	nodeMap.put(i, new ArrayList<>());
        }        
        for (int i = 0; i < N - 1; i++) {
        	inputArray = getInputArray();
        	int from = inputArray[0];
        	int to = inputArray[1];
        	int value = inputArray[2];
        	
        	nodeMap.get(from).add(new Edge(to, value));
        	nodeMap.get(to).add(new Edge(from, value));
        }
        
        targets = new int[M][2];
        for (int i = 0; i < M; i++) {
        	targets[i] = getInputArray();
        }
	}
	
	void solve() throws IOException {
		for (int[] target : targets) {
			int from = target[0];
			int to = target[1];
			
			getDistance(from, to);
		}
	}
	
	void getDistance(int from, int to) throws IOException {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(from);
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		visited[from] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.remove();
			for (Edge e : nodeMap.get(current)) {
				if (!visited[e.to]) {
					distance[e.to] = distance[current] + e.value;
					visited[e.to] = true;
					queue.add(e.to);
				}
			}
			if (visited[to]) {
				BW.write(Integer.toString(distance[to])+"\n");
				return;
			}
		}
	}
	
	void printResult() throws IOException {	
		BW.flush();
		BW.close();
		BR.close();
	}
	
	static class Edge {
		int to;
		int value;
		
		Edge(int to, int value) {
			this.to = to;
			this.value = value;
		}
	}
	
}