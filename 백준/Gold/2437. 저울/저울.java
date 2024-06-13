import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int n;
    int[] ns;
    int result;

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
        n = Integer.parseInt(BR.readLine());
        ns = getInputArray();
        result = 1;
    }

    void solve() throws IOException {
        Arrays.sort(ns);
        for (int i : ns) {
            if (result < i) {
                break;
            }
            result += i;
        }
        BW.write(Integer.toString(result));
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }


}