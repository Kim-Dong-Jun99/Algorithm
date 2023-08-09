import java.util.*;
import java.io.*;
class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    int K;
    int maxLength;
    int[] toPrint;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch(IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    void init() throws IOException {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        K = inputArray[2];
        maxLength = N + M;
        toPrint = new int[maxLength];
    }
    void solution() throws IOException {
        int searched = dp(0, -1, 0);
//        System.out.println(searched);
        if (searched == K) {
            for (Integer i : toPrint) {
                if (i == 1) {
                    BW.write("a");
                } else {
                    BW.write("z");
                }
            }
        } else {
            BW.write("-1");
        }
        BW.flush();
        BW.close();
    }

    int dp(int aCount, int startIndex, int order) {
//        System.out.println(order);
        if (aCount >= N) {
            order += 1;
            return order;
        }
        for (int i = startIndex + 1; i <= maxLength - N + aCount; i++) {

            order = dp(aCount + 1, i, order);
            if (order == K) {
                toPrint[i] = 1;
                return order;
            }
        }
        return order;
    }

}
