import java.io.*;
import java.math.BigInteger;
import java.util.*;


class Solution {

    private final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int T;
    int answer;
    String[] inputArray;
    HashSet<String> visited;
    String target;
    int changeCount;
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.testCase();


    }

    public void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            solve();
            BW.write("#" + tc + " "+answer);
            BW.newLine();
        }
        BW.flush();
        BW.close();
    }


    void init() throws IOException {
        answer = 0;
        inputArray = BR.readLine().split(" ");
        target = inputArray[0];
        changeCount = Integer.parseInt(inputArray[1]);
        visited = new HashSet<>();

    }


    void solve() {
        dfs(target, 0);
    }

    void dfs(String target, int count) {
        if (count == changeCount) {
            answer = Math.max(answer, Integer.parseInt(target));
            return;
        }

        if (visited.contains(target)) {
            return;
        }

        visited.add(target);

        for (int i = 0; i < target.length(); i++) {
            for (int j = i + 1; j < target.length(); j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < target.length(); k++) {

                    if (k == j) {
                        sb.append(target.charAt(i));
                    } else if (k == i) {
                        sb.append(target.charAt(j));
                    } else {
                        sb.append(target.charAt(k));
                    }
                }

                dfs(sb.toString(), count + 1);

            }
        }
    }



}