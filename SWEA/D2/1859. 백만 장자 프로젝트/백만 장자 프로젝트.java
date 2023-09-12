import java.io.*;
import java.math.BigInteger;
import java.util.*;


class Solution {

    private final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T;
    long answer;
    int N;
    int[] sellPrices;
    int[] maxPrices;


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
        N = Integer.parseInt(BR.readLine());
        sellPrices = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        maxPrices = new int[N];

    }


    void solve() {
        int tempMax = 0;
        for (int i = N - 1; i > -1; i--) {
            tempMax = Math.max(tempMax, sellPrices[i]);
            maxPrices[i] = tempMax;
        }
        for (int i = 0; i < N; i++) {
            answer += maxPrices[i] - sellPrices[i];
        }
    }

}