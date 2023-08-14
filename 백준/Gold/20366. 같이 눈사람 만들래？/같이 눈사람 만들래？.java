import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int[] snows;
    int left;
    int right;
    int answer;
    int elsa;
    int anna;
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
        snows = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(snows);
        answer = Integer.MAX_VALUE;
    }


    void solution() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = i + 3; j < N; j++) {
                elsa = snows[i] + snows[j];
                left = i + 1;
                right = j - 1;
                while (left < right) {
                    anna = snows[left] + snows[right];
                    updateAnswer();
                }
            }


        }
        System.out.println(answer);
    }

    void updateAnswer() {
        answer = Math.min(answer, Math.abs(anna - elsa));
        if (elsa > anna) {
            left += 1;
        } else {
            right -= 1;
        }
    }
}
