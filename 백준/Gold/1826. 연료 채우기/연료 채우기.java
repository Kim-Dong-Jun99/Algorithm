import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    // 0번 인덱스에 주유소까지 거리, 1번 인덱스에 채울 수 있는 기름 양 저장
    int[][] villageAndGas;
    // 현재 기름 양
    int currentGas;
    // 마을로 향하며 주유할 수 잇는 기름의 최대 양을 저장하기 위한 heap
    PriorityQueue<Integer> maxHeap;


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        N = Integer.parseInt(BR.readLine());
        villageAndGas = new int[N+1][2];
        for (int i = 0; i < N; i++) {
            int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            villageAndGas[i][0] = inputArray[0];
            villageAndGas[i][1] = inputArray[1];
        }
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        villageAndGas[N][0] = inputArray[0];
        villageAndGas[N][1] = 0;
        currentGas = inputArray[1];
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    void solution() throws Exception{
        Arrays.sort(villageAndGas, Comparator.comparingInt((int[] village) -> village[0]));
        int index = 0;
        int answer = 0;
        while (index <= N) {
            if (villageAndGas[index][0] <= currentGas) {
                maxHeap.add(villageAndGas[index][1]);
                index += 1;
            } else {
                while (!maxHeap.isEmpty() && currentGas < villageAndGas[index][0]) {

                    currentGas += maxHeap.remove();
                    answer += 1;
                }
                if (maxHeap.isEmpty() && currentGas < villageAndGas[index][0]) {
                    answer = -1;
                    break;
                } else {
                    maxHeap.add(villageAndGas[index][1]);
                    index += 1;
                }

            }
        }
        System.out.println(answer);

    }


}