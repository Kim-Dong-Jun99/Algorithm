import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int S;
    TreeMap<Integer, Integer> maxPriceSum;
    Painting[] paintings;
    int[] inputArray;
    int tempMaxPrice;
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
        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        S = inputArray[1];
        maxPriceSum = new TreeMap<>();
        paintings = new Painting[N];
        for (int i = 0; i < N; i++) {
            inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            paintings[i] = new Painting(inputArray[0], inputArray[1]);
        }
        Arrays.sort(paintings, Painting::compareWithHeight);
        maxPriceSum.put(0, 0);
        answer = 0;

    }


    void solution() throws IOException {
        for (Painting painting : paintings) {
            Map.Entry<Integer, Integer> notBuy = maxPriceSum.floorEntry(painting.height);
            Map.Entry<Integer, Integer> buy = maxPriceSum.floorEntry(painting.height - S);
            tempMaxPrice = Math.max(notBuy.getValue(), buy.getValue() + painting.price);
            maxPriceSum.put(painting.height, tempMaxPrice);
            answer = Math.max(answer, tempMaxPrice);
        }
        System.out.println(answer);
    }

    static class Painting {
        int height;
        int price;

        public Painting(int height, int price) {
            this.height = height;
            this.price = price;
        }

        public int compareWithHeight(Painting compare) {
            return Integer.compare(this.height, compare.height);
        }


    }
}
