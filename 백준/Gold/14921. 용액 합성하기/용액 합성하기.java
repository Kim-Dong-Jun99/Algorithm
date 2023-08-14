import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int[] liquids;
    int left;
    int right;
    int answer;
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
        liquids = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(liquids);
        answer = Integer.MAX_VALUE;
    }


    void solution() throws IOException {
        if (liquids[0] == 0 || liquids[N - 1] == 0) {
            if (liquids[0] == 0) {
                System.out.println(liquids[0] + liquids[1]);
            } else {
                System.out.println(liquids[N - 1] + liquids[N - 2]);
            }
        } else if ((liquids[0] / Math.abs(liquids[0])) * (liquids[N - 1] / Math.abs(liquids[N-1])) >= 0) {
            if (liquids[0] > 0) {
                System.out.println(liquids[0] + liquids[1]);
            } else {
                System.out.println(liquids[N - 1] + liquids[N - 2]);
            }
        } else {
            left = 0;
            right = N - 1;
            while (left < right) {
                updateAnswer();
            }
            System.out.println(answer);
        }
    }

    void updateAnswer() {
        if (Math.abs(answer) > Math.abs(liquids[left] + liquids[right])) {
            answer = liquids[left] + liquids[right];
        }
        if (liquids[left] + liquids[right] > 0) {
            right -= 1;
        } else {
            left += 1;
        }
    }
}
