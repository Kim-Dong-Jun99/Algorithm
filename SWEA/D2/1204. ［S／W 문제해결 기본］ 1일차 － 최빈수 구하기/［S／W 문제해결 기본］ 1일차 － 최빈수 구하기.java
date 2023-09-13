import java.io.*;
import java.math.BigInteger;
import java.util.*;


class Solution {

    private final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T;
    int answer;
    int maxCount;
    int[] inputArray;
    HashMap<Integer, Integer> studentMap;
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
        BR.readLine();
        answer = 0;
        maxCount = 0;
        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        studentMap = new HashMap<>();


    }


    void solve() {
        for (Integer num : inputArray) {
            Integer orDefault = studentMap.getOrDefault(num, 0);
            orDefault += 1;
            studentMap.put(num, orDefault);
            if (orDefault > maxCount) {
                maxCount = orDefault;
                answer = num;
            } else if (maxCount == orDefault && answer < num) {
                answer = num;
            }

        }
    }

}