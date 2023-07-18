import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int K;
    String NS;
    Deque<Integer> toPrint;
    int popCount;
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        int[] intArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = intArray[0];
        K = intArray[1];

        NS = BR.readLine();

        toPrint = new ArrayDeque<>();
        popCount = 0;
    }

    void solution() throws Exception {

        for (int i = 0; i < N; i++) {
            int temp = Character.getNumericValue(NS.charAt(i));
            if (toPrint.size() == 0) {
                toPrint.add(temp);
            } else {
                if (popCount < K) {
                    while (toPrint.size() > 0 && popCount < K && toPrint.getLast() < temp) {
                        toPrint.removeLast();
                        popCount += 1;

                    }
                    toPrint.add(temp);

                } else {
                    toPrint.add(temp);
                }
            }

        }
        while (popCount < K) {
            toPrint.removeLast();
            popCount += 1;
        }
        for (Integer i : toPrint) {
            BW.write(Integer.toString(i));
        }
        BW.flush();
        BW.close();
    }
}
