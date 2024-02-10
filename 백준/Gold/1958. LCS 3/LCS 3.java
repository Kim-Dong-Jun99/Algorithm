import java.io.*;
import java.util.*;

/*
s1, s2, s3

*/
class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final int[] d1 = {-1, 0, 0, 0, -1, -1};
    public static final int[] d2 = {0, -1, 0, -1, 0, -1};
    public static final int[] d3 = {0, 0, -1, -1, -1, 0};

    int[] inputArray;
    String s1, s2, s3;
    int n1, n2, n3;

    int[][][] dp;



    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        s1 = BR.readLine();
        s2 = BR.readLine();
        s3 = BR.readLine();

        n1 = s1.length()+1;
        n2 = s2.length()+1;
        n3 = s3.length()+1;

        dp = new int[n1][n2][n3];

    }
    void solve() throws IOException {
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                for (int k = 1; k < n3; k++) {
                    if (s1.charAt(i-1) == s2.charAt(j-1) && s2.charAt(j-1) == s3.charAt(k-1)) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        int tempMax = 0;
                        for (int d = 0; d < 6; d++) {
                            tempMax = Math.max(tempMax, dp[i+d1[d]][j+d2[d]][k+d3[d]]);
                        }
                        dp[i][j][k] = tempMax;
                    }
                }
            }
        }
        System.out.println(dp[s1.length()][s2.length()][s3.length()]);

    }
}