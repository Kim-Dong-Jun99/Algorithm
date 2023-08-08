import java.util.*;
import java.io.*;
class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int K;
    Jewelry[] jewelries;
    int[] bags;
    PriorityQueue<Jewelry> greedy;
    int index;
    long answer;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch(IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    void init() throws IOException {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        K = inputArray[1];
        jewelries = new Jewelry[N];
        for (int i = 0; i < N; i++) {
            int[] tempInputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            jewelries[i] = new Jewelry(tempInputArray[0], tempInputArray[1]);
        }
        bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(BR.readLine());
        }
        Arrays.sort(bags);
        Arrays.sort(jewelries, Jewelry::compareWithWeight);
        greedy = new PriorityQueue<>(Jewelry::compareWithValue);
        index = 0;
        answer = 0;
    }
    void solution() throws IOException {
        for (int i = 0; i < K; i++) {
            while (index < N && jewelries[index].weight <= bags[i]) {
                greedy.add(jewelries[index]);
                index += 1;
            }
            if (!greedy.isEmpty()) {
                answer += greedy.remove().value;
            }
        }
        System.out.println(answer);
    }

    static class Jewelry {
        int weight;
        int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int compareWithWeight(Jewelry compare) {
            return Integer.compare(this.weight, compare.weight);
        }

        public int compareWithValue(Jewelry compare) {
            return Integer.compare(compare.value, this.value);
        }
    }


}
