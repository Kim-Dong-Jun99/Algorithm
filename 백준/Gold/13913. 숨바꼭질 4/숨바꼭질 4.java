import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, K;
    int[] visited;
    int answer;
    List<Integer> answerList;
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
        K = inputArray[1];
        visited = new int[200_001];
        Arrays.fill(visited, -1);
        answerList = new ArrayList<>();
    }

    void solve() throws IOException {
        bfs();
        fillAnswerList();
        printResult();
    }

    void bfs() {
        List<Integer> currentPositions = Collections.singletonList(N);
        visited[N] = 0;
        while (!currentPositions.isEmpty() && visited[K] == -1) {
            List<Integer> temp = new ArrayList<>();
            for (Integer currentPosition : currentPositions) {
                if (isInner(currentPosition-1) && visited[currentPosition-1] == -1) {
                    visited[currentPosition-1] = currentPosition;
                    temp.add(currentPosition-1);
                }
                if (isInner(currentPosition+1) && visited[currentPosition+1] == -1) {
                    visited[currentPosition+1] = currentPosition;
                    temp.add(currentPosition+1);
                }
                if (isInner(currentPosition*2) && visited[currentPosition*2] == -1) {
                    visited[currentPosition*2] = currentPosition;
                    temp.add(currentPosition*2);
                }
            }
            answer += 1;
            currentPositions = temp;
        }
    }

    boolean isInner(int x) {
        return 0 <= x && x <= 200_000;
    }

    void fillAnswerList() {
        int current = K;
        while (true) {
            answerList.add(current);
            if (current == N) {
                break;
            }
            current = visited[current];
        }
    }

    void printResult() throws IOException {
        BW.write(Integer.toString(answer)+"\n");
        for (int i = answerList.size() - 1; i > -1; i--) {
            BW.write(Integer.toString(answerList.get(i))+" ");
        }
        BW.flush();
        BW.close();
        BR.close();
    }
}