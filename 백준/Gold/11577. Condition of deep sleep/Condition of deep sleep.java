import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int N;
    int K;
    int[] lightBulbs;

    Queue<Integer> queue;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        K = inputArray[1];
        queue = new ArrayDeque<>();
        lightBulbs = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void solution() {
        long answer = 0;
        boolean done = true;
        for (int i = 0; i < N; i++) {
            while (queue.size() > 0 && queue.peek() < i) {
                queue.remove();
            }

            if (queue.size() % 2 == 0) {
                if (lightBulbs[i] == 1) {
                    if (i + K - 1 < N) {
                        answer += 1;
                        queue.add(i + K - 1);
                    } else {
                        done = false;
                        break;
                    }
                }
            } else {
                if (lightBulbs[i] == 0) {
                    if (i + K - 1 < N) {
                        answer += 1;
                        queue.add(i + K - 1);
                    } else {
                        done = false;
                        break;
                    }
                }
            }

        }
        if (done) {
            System.out.println(answer);
        } else {
            System.out.println("Insomnia");
        }
    }


}