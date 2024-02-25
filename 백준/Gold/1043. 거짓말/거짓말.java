import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, M;
    int[] parent;
    List<Integer> truth;
    List<List<Integer>> parties;

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
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        inputArray = getInputArray();
        truth = new ArrayList<>();
        for (int i = 1; i < inputArray.length; i++) {
            truth.add(inputArray[i]);
        }
        parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            List<Integer> temp = new ArrayList<>();
            inputArray = getInputArray();
            for (int j = 1; j < inputArray.length; j++) {
                temp.add(inputArray[j]);
            }
            for (int j = 0; j < temp.size() - 1; j++) {
                union(temp.get(j), temp.get(j + 1));
            }
            parties.add(temp);
        }

    }

    int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[px] = py;
        }
    }

    void solve() throws IOException {
        if (truth.isEmpty()) {
            System.out.println(M);
            return;
        }
        int answer = 0;
        HashSet<Integer> parentTruth = new HashSet<>();
        for (Integer t : truth) {
            parentTruth.add(find(t));
        }
        for (List<Integer> partyMember : parties) {
            boolean canGo = true;
            for (Integer member : partyMember) {
                if (parentTruth.contains(find(member))) {
                    canGo = false;
                    break;
                }
            }
            if (canGo) {
                answer += 1;
            }
        }
        System.out.println(answer);
    }
}