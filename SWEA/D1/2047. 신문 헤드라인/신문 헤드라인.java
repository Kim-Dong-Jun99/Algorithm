import java.io.*;
import java.util.*;

/*
-   A,B,C는 모두 ‘0’ 또는 ‘1’로만 구성된 길이가 1 이상 1,000 이하인 문자열이다.
-    LCS(A, B) = X
-    LCS(B, C) = Y
-    LCS(C, A) = Z
 */

class Solution {

    private final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T;
    String givenString;
    int[] inputArray;


    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
//        solution.testCase();

        solution.init();
        solution.solve();

    }

    public void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        for (int tc = 0; tc <= T; tc++) {
            init();
            solve();
            BW.newLine();
        }
        BW.flush();
        BW.close();
    }


    void init() throws IOException {
        givenString = BR.readLine();

    }


    void solve() {
        System.out.println(givenString.toUpperCase());
    }


}