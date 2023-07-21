import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int C;
    int M;
    int[][] delivery;
    int[] truck;
    int[] delivered;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        C = inputArray[1];
        M = Integer.parseInt(BR.readLine());
        delivery = new int[M][3];
        truck = new int[N];
        delivered = new int[N];
        Arrays.fill(truck, 0);
        Arrays.fill(delivered, 0);

        for (int i = 0; i < M; i++) {
            int[] villageAndBoxes = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            delivery[i][0] = villageAndBoxes[0] - 1;
            delivery[i][1] = villageAndBoxes[1] - 1;
            delivery[i][2] = villageAndBoxes[2];

        }
    }

    void solution() throws Exception {
        long answer = 0;
        Arrays.sort(delivery, Comparator.comparing((int[] orders) -> orders[1]));
        for (int[] deliver : delivery) {
            int start = deliver[0];
            int destination = deliver[1];
            int amount = deliver[2];
            int currentWeight = truck[start];
            if (currentWeight != C) {
                int currentMaximum = currentWeight;
                for (int i = start + 1; i < destination; i++) {
                    if (truck[i] > currentMaximum) {
                        currentMaximum = truck[i];
                    }
                    if (currentMaximum == C) {
                        break;
                    }
                }
                if (currentMaximum != C) {
                    int toAdd = Math.min(C - currentMaximum, amount);
                    answer += toAdd;
                    for (int i = start; i < destination; i++) {
                        truck[i] += toAdd;
                    }

                }

            }


        }

        System.out.println(answer);
    }

}