import java.util.*;
import java.io.*;


class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] inputArray;
    long answer;
    int N, B, C;
    int[] A;

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
        A = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        B = inputArray[0];
        C = inputArray[1];
        answer = 0;
    }

    void solution() throws IOException {
        for (int i : A) {

            answer += 1;
            i -= B;
            if (i > 0) {

                answer += (int) Math.ceil((double) i / C);
            }
        }
        System.out.println(answer);
    }


}