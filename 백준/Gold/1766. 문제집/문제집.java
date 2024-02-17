import java.io.*;
import java.util.*;

/*
	N개의 문제로 되어 있는 문제집,
    1번이 가장 쉽고, N 번이 가장 어려움

    N개의 문제는 모두 풀어야함, 가능한 쉬운 문제부터 풀어야하고, 먼저 푸는 것이 좋은 문제는 반드시 먼저 풀어야한다
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, M;
    int[] indegree;
    boolean[] visited;
    Map<Integer, List<Integer>> connected;
    PriorityQueue<Integer> heap;

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

        indegree = new int[N+1];
        heap = new PriorityQueue<>();

        connected = new HashMap<>();
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            int A = inputArray[0];
            int B = inputArray[1];

            connected.putIfAbsent(A, new ArrayList<>());
            connected.get(A).add(B);

            indegree[B] += 1;
        }

    }

    void solve() throws IOException {
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                heap.add(i);
            }
        }
        
        while (!heap.isEmpty()) {
            Integer removed = heap.remove();
            BW.write(Integer.toString(removed)+" ");
            for (Integer next : connected.getOrDefault(removed, new ArrayList<>())) {
                indegree[next] -= 1;
                if (indegree[next] == 0) {
                    heap.add(next);
                }
            }
        }
        
        BW.flush();
        BW.close();
        BR.close();
    }
}