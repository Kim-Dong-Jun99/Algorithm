import java.util.*;
import java.io.*;

public class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    int[] numbers;
    long[][] subSums;

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
        numbers = getInputArray();
        subSums = new long[N][21];
        subSums[0][numbers[0]] = 1l;
    }

    void solve() throws IOException {
        for(int i = 1; i < N-1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (subSums[i-1][j] != 0) {
                    if (isValid(j + numbers[i])) {
                        subSums[i][j+numbers[i]] += subSums[i-1][j];
                    }
                    if (isValid(j - numbers[i])) {
                        subSums[i][j-numbers[i]] += subSums[i-1][j];
                    }
                }
            }

        }

        BW.write(Long.toString(subSums[N-2][numbers[N-1]]));
        BW.flush();
        BW.close();
        BR.close();

    }

    boolean isValid(int number) {
        return 0 <= number && number <= 20;
    }
}