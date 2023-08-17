import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[] COUNTER = new int[10];
    int N;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
    }


    void solution() throws IOException {
        solve(N);
        for (int i = 0; i < 10; i++) {
            System.out.print(COUNTER[i] + " ");
        }
    }

    void solve(int num) {

        int start = 1;


        int digit = 1;

        while (start <= num) {
            while (num % 10 != 9 && start <= num) {
                count(num, digit);

                num--;
            }
            if (num < start) {
                break;
            }

            while (start % 10 != 0 && start <= num) {
                count(start, digit);

                start++;
            }

            start /= 10;
            num /= 10;

            for (int i = 0; i < 10; i++) {
                COUNTER[i] += (num - start + 1) * digit;
            }

            digit *= 10;
        }
    }

    void count(int num, int digit) {
        while (num > 0) {
            COUNTER[num % 10] += digit;
            num /= 10;
        }
    }

}
