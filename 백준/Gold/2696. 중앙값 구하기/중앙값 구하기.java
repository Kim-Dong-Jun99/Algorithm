import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int T;
    int M;
    int row;
    int[][] numbers;
    PriorityQueue<Integer> asc;
    PriorityQueue<Integer> desc;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.testCase();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            init();
            solve();
        }
    }

    void init() throws IOException {
        M = Integer.parseInt(BR.readLine());
        row = M / 10;
        if (M % 10 != 0) {
            row += 1;
        }
        numbers = new int[row][10];
        for (int i = 0; i < row; i++) {
            numbers[i] = getInputArray();
        }
        desc = new PriorityQueue<>(Collections.reverseOrder());
        asc = new PriorityQueue<>();
    }


    void solve() throws IOException {
        int index = 0;
        int printed = 0;
        BW.write(Integer.toString(M / 2 + M % 2) + "\n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (i == 0 && j == 0) {
                    asc.add(numbers[i][j]);
                } else {
                    if (numbers[i][j] < asc.peek()) {
                        desc.add(numbers[i][j]);
                    } else {
                        asc.add(numbers[i][j]);
                    }
                }
                if (asc.size() > desc.size() + 1) {
                    desc.add(asc.poll());
                }
                if (desc.size() > asc.size() + 1) {
                    asc.add(desc.poll());
                }
                if (index % 2 == 0) {
                    if (asc.size() > desc.size()) {
                        BW.write(Integer.toString(asc.peek())+" ");
                    }  else {
                        BW.write(Integer.toString(desc.peek())+" ");
                    }
                    printed += 1;
                    if (printed % 10 == 0) {
                        BW.write("\n");
                    }
                }
                index += 1;
            }
        }
        BW.write("\n");
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }
}