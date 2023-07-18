import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    int K;

    HashMap<Integer, Queue<Integer>> wordLengthTable;


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        K = inputArray[1];
        wordLengthTable = new HashMap<>();
        for (int i = 0; i < 21; i++) {
            wordLengthTable.put(i, new ArrayDeque<>());
        }
        for (int i = 0; i < N; i++) {
            String inputName = BR.readLine();
            Queue<Integer> indexes= wordLengthTable.get(inputName.length());
            indexes.add(i);

        }
    }

    void solution() throws Exception {

        long answer = 0;
        for (int i = 2; i < 21; i++) {
            Queue<Integer> indexes = wordLengthTable.get(i);
            Queue<Integer> temp = new ArrayDeque<>();
            while (indexes.size() > 0) {
                Integer removed = indexes.remove();
                while (temp.size() > 0 && removed - temp.peek() > K) {

                    temp.remove();

                }
                answer += temp.size();
                temp.add(removed);
            }
        }
        System.out.println(answer);
    }


}