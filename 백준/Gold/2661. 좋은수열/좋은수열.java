import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N;
    StringBuilder sb;
    boolean finished;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        sb = new StringBuilder();
        finished = false;
    }

    void solve() throws IOException {
        dfs("");
    }

    void dfs(String str) {
        if (str.length() == N) { 
            System.out.println(str);
            System.exit(0);
        }
        if (isBadString(str)) {
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (!isBadString(str + i)) {
                dfs(str+i);
            }
        }
    }

    boolean isBadString(String string) {
        int n = string.length();
        for (int i = 1; i <= n / 2; i++) {
            if (string.substring(n - 2 * i, n - i).equals(string.substring(n - i, n))) {

                return true;
            }
        }
        return false;
    }
}