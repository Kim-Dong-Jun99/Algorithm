import java.io.*;
import java.util.Arrays;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, M;
    int[] ns;
    long[] subSums;
    long[] count;
    long answer;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        M = inputArray[1];
        ns = getInputArray();
        subSums = new long[N + 1];
        count = new long[M];
        answer = 0;
    }


    void solve() throws IOException {
        for (int i = 1; i <= N; i++) {
            subSums[i] = (subSums[i - 1] + ns[i - 1]) % M;
            if (subSums[i] == 0) {
                answer += 1;
            }
            count[(int) subSums[i]] += 1;
        }
        for (int i = 0; i < M; i++) {
            if (count[i] > 1) {
                answer += (count[i] * (count[i] - 1)) / 2;
            }
        }
        BW.write(Long.toString(answer));
    }

    void printResult() throws IOException {

        BW.flush();
        BW.close();
        BR.close();
    }
}