import java.io.*;
import java.util.*;

/*
-   A,B,C는 모두 ‘0’ 또는 ‘1’로만 구성된 길이가 1 이상 1,000 이하인 문자열이다.
-    LCS(A, B) = X
-    LCS(B, C) = Y
-    LCS(C, A) = Z
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYmRMhpakYADFARi&categoryId=AYmRMhpakYADFARi&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

class Solution {

    private final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T;
    int[] inputArray, minLengths;
    int X, Y, Z, aMinLength, bMinLength, cMinLength;
    String A, B, C;
    StringBuilder sb;


    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.testCase();


    }

    public void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            solve();
            BW.write("#" + tc + " " + A + " " + B + " " + C);
            BW.newLine();
        }
        BW.flush();
        BW.close();
    }


    void init() throws IOException {
        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        X = inputArray[0];
        Y = inputArray[1];
        Z = inputArray[2];

        aMinLength = Math.max(X, Z);
        bMinLength = Math.max(X, Y);
        cMinLength = Math.max(Y, Z);
        minLengths = new int[]{aMinLength, bMinLength, cMinLength};
        Arrays.sort(minLengths);

        A = "";
        B = "";
        C = "";
    }


    void solve() {
        if (X == Y && Y == Z) {
            A += createRepeatedString("1", X);
            B += createRepeatedString("1", X);
            C += createRepeatedString("1", X);
        } else if (isPair()) {
            if (X == Y) { // B가 겹침
                if (Z > X) {
                    B += createRepeatedString("1", X);
                    A += createRepeatedString("1", X);
                    A += createRepeatedString("0", Z - X);
                    C += createRepeatedString("1", X);
                    C += createRepeatedString("0", Z - X);
                } else {
                    B += createRepeatedString("10", X);
//                    B = "10".repeat(X);
                    A += createRepeatedString("1", X);
//                    A = "1".repeat(X);
                    A += createRepeatedString("0", Z);
//                    A += "0".repeat(Z);
                    C += createRepeatedString("0", X);
//                    C = "0".repeat(X);
                    C += createRepeatedString("1", Z);
//                    C += "1".repeat(Z);
                }
            } else if (X == Z) { // A가 겹침
                if (Y > X) {
                    A += createRepeatedString("1", X);
//                    A = "1".repeat(X);
                    B += createRepeatedString("1", X);
//                    B = "1".repeat(X);
                    B += createRepeatedString("0", Y - X);
//                    B += "0".repeat(Y - X);
                    C += createRepeatedString("1", X);
//                    C = "1".repeat(X);
                    C += createRepeatedString("0", Y - X);
//                    C += "0".repeat(Y - X);
                } else {
                    A += createRepeatedString("10", X);
//                    A = "10".repeat(X);
                    B += createRepeatedString("1", X);
//                    B = "1".repeat(X);
                    B += createRepeatedString("0", Y);
//                    B += "0".repeat(Y);
                    C += createRepeatedString("0", X);
//                    C = "0".repeat(X);
                    C += createRepeatedString("1", Y);
//                    C += "1".repeat(Y);
                }
            } else if (Y == Z){ // C가 겹침
                if (X > Y) {
                    C += createRepeatedString("1", Y);
//                    C = "1".repeat(Y);
                    A += createRepeatedString("1", Y);
//                    A = "1".repeat(X);
                    A += createRepeatedString("0", X - Y);
//                    A += "0".repeat(X - Y);
                    B += createRepeatedString("1", Y);
//                    B = "1".repeat(X);
                    B += createRepeatedString("0", X - Y);
//                    B += "0".repeat(X - Y);
                } else {
                    C += createRepeatedString("10", Y);
//                    C = "10".repeat(Y);
                    A += createRepeatedString("1", Y);
//                    A = "1".repeat(Y);
                    A += createRepeatedString("0", X);
//                    A += "0".repeat(X);
                    B += createRepeatedString("0", Y);
//                    B = "0".repeat(Y);
                    B += createRepeatedString("1", X);
//                    B += "1".repeat(X);
                }
            }
        } else {
            if (aMinLength == minLengths[0]) {
                A += createRepeatedString("0", minLengths[0]);
                B += createRepeatedString("0", X);
                C += createRepeatedString("0", Z);
                C += createRepeatedString("1", Y - Math.min(X, Z));
                B += createRepeatedString("1", Y - Math.min(X, Z));

            } else if (bMinLength == minLengths[0]) {
                B += createRepeatedString("0", minLengths[0]);
                A += createRepeatedString("0", X);
                C += createRepeatedString("0", Y);
                C += createRepeatedString("1", Z - Math.min(X, Y));
                A += createRepeatedString("1", Z - Math.min(X, Y));
            } else {
                C += createRepeatedString("0", minLengths[0]);
                B += createRepeatedString("0", Y);
                A += createRepeatedString("0", Z);
                A += createRepeatedString("1", X - Math.min(Y, Z));
                B += createRepeatedString("1", X - Math.min(Y, Z));
            }

        }
    }

    boolean isPair() {
        return X == Y || Y == Z || X == Z;
    }

    String createRepeatedString(String toAppend, int repeat) {
        sb = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            sb.append(toAppend);
        }
        return sb.toString();
    }
}