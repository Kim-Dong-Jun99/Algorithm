import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, M;
    int[] answer, indegree;
    Map<Integer, List<Integer>> connected;
    List<Integer> lectures;
    int semester;

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

        answer = new int[N+1];
        indegree = new int[N+1];
        connected = new HashMap<>();

        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            int A = inputArray[0];
            int B = inputArray[1];

            indegree[B] += 1;
            connected.putIfAbsent(A, new ArrayList<>());
            connected.get(A).add(B);
        }
        semester = 1;
        initLectures();
    }

    void initLectures() {
        lectures = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                lectures.add(i);
            }
        }
    }

    void solve() throws IOException {
        while(!lectures.isEmpty()) {
            List<Integer> newLectures = new ArrayList<>();
            for (int lecture : lectures) {
                answer[lecture] = semester;
                for (int next : connected.getOrDefault(lecture, new ArrayList<>())) {
                    indegree[next] -= 1;
                    if (indegree[next] == 0) {
                        newLectures.add(next);
                    }
                }
            }
            lectures = newLectures;
            semester += 1;
        }

        for (int i = 1; i <= N; i++) {
            BW.write(Integer.toString(answer[i])+" ");
        }
        BW.flush();
        BW.close();
        BR.close();
    }
}