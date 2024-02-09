import java.io.*;
import java.util.*;
class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int T;
    int[] numbers;
    int maxNumber;
    int[] dp;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        T = Integer.parseInt(BR.readLine());
        maxNumber = 0;
        numbers = new int[T];
        for (int i = 0; i < T; i++) {
            numbers[i] = Integer.parseInt(BR.readLine());
            maxNumber = Math.max(numbers[i], maxNumber);
        }
        dp = new int[maxNumber+1];
        Arrays.fill(dp, 1);
        initDp();
    }

    void initDp() {
        for (int i = 2; i <= 3; i++) {
            dp[i] += 1;
            for (int j = 1; j <= maxNumber; j++) {
                if (j + i <= maxNumber) {
                    dp[j+i] += dp[j];
                }
            }
        }
    }
    void solve() throws IOException {
        for (Integer number : numbers) {
            BW.write(Integer.toString(dp[number])+"\n");
        }
        BW.flush();
        BW.close();
        BR.close();
    }
}