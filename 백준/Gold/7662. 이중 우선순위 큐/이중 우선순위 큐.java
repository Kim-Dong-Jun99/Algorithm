import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T;
    int k;
    PriorityQueue<Data> minHeap;
    PriorityQueue<Data> maxHeap;
    HashSet<Integer> popped;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.testCase();
        } catch (Exception e) {
            System.out.println("exception during I/O");
        }
    }

    void testCase() throws Exception{
        T = Integer.parseInt(BR.readLine());
        for (int i = 0; i < T; i++) {
            init();
            solution();
        }
        BW.flush();
        BW.close();
    }

    void init() throws Exception {
        k = Integer.parseInt(BR.readLine());
        minHeap = new PriorityQueue<>(Data::minOrder);
        maxHeap = new PriorityQueue<>(Data::maxOrder);
        popped = new HashSet<>();
    }

    void solution() throws Exception {
        for (int i = 0; i < k; i++) {
            String[] inputArray = BR.readLine().split(" ");
            String command = inputArray[0];

            if (command.equals("I")) {
                int toAdd = Integer.parseInt(inputArray[1]);
                minHeap.add(new Data(toAdd, i));
                maxHeap.add(new Data(toAdd, i));
            } else {
                int minOrMax = Integer.parseInt(inputArray[1]);
                if (minOrMax == -1) {
                    while (!minHeap.isEmpty() && popped.contains(minHeap.peek().index)) {
                        minHeap.remove();
                    }
                    if (!minHeap.isEmpty()) {
                        popped.add(minHeap.peek().index);
                        minHeap.remove();
                    }
                } else {
                    while (!maxHeap.isEmpty() && popped.contains(maxHeap.peek().index)) {
                        maxHeap.remove();
                    }
                    if (!maxHeap.isEmpty()) {
                        popped.add(maxHeap.peek().index);
                        maxHeap.remove();
                    }
                }
            }
        }

        while (!minHeap.isEmpty() && popped.contains(minHeap.peek().index)) {
            minHeap.remove();
        }

        while (!maxHeap.isEmpty() && popped.contains(maxHeap.peek().index)) {
            maxHeap.remove();
        }

        if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            BW.write(maxHeap.peek().value + " " + minHeap.peek().value + "\n");
        } else {
            BW.write("EMPTY\n");

        }

    }


    static class Data {
        int value;
        int index;

        public Data(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int minOrder(Data compare) {
            if (this.value < compare.value) {
                return -1;
            } else if (this.value == compare.value) {
                if (this.index > compare.index) {
                    return -1;
                } else {
                    return 1;
                }
            } else {

                return 1;
            }
        }

        public int maxOrder(Data compare) {
            if (this.value > compare.value) {
                return -1;
            } else if (this.value == compare.value) {
                if (this.index > compare.index) {
                    return -1;
                } else {
                    return 1;
                }
            } else {

                return 1;
            }
        }
    }
}