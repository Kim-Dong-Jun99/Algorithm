import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int H, W;
    int[] blocks;
    int answer;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        H = inputArray[0];
        W = inputArray[1];
        blocks = getInputArray();
        answer = 0;
    }

    void solve() throws IOException {
        for (int i = 1; i < W - 1; i++) {
            int filled = Math.min(getLeftMax(i), getRightMax(i)) - blocks[i];
            if (filled > 0) {

                answer += filled;
            }
        }
        System.out.println(answer);
    }

    int getLeftMax(int index) {
        int max = 0;
        for (int i = 0; i < index; i++) {
            max = Math.max(max, blocks[i]);
        }
        return max;
    }

    int getRightMax(int index) {
        int max = 0;
        for (int i = index + 1; i < W; i++) {
            max = Math.max(max, blocks[i]);
        }
        return max;
    }
}