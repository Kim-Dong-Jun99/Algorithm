import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int pizzaSize;
    HashMap<Integer, Integer> A;
    HashMap<Integer, Integer> B;
    int[] aSlice;
    int[] bSlice;
    int[] aCumulativeSum;
    int[] bCumulativeSum;
    int m;
    int n;
    int endIndex;
    int subSum;
    int count;
    int answer;
    int aSums;
    int bSums;

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
        pizzaSize = Integer.parseInt(BR.readLine());
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        m = inputArray[0];
        n = inputArray[1];
        A = new HashMap<>();
        B = new HashMap<>();
        aSlice = new int[m];
        bSlice = new int[n];
        aCumulativeSum = new int[m];
        bCumulativeSum = new int[n];
        aSums = 0;
        bSums = 0;

        answer = 0;

        initPizzaSlice();

        initCumulativeSum();
    }

    void initPizzaSlice() throws IOException {
        for (int i = 0; i < m; i++) {
            int pizzaSlice = Integer.parseInt(BR.readLine());
            count = A.getOrDefault(pizzaSlice, 0);
            A.put(pizzaSlice, count + 1);
            aSlice[i] = pizzaSlice;

        }
        for (int i = 0; i < n; i++) {
            int pizzaSlice = Integer.parseInt(BR.readLine());
            count = B.getOrDefault(pizzaSlice, 0);
            B.put(pizzaSlice, count + 1);
            bSlice[i] = pizzaSlice;
        }
    }

    void initCumulativeSum() {
        for (int i = 0; i < m; i++) {
            aSums += aSlice[i];
            aCumulativeSum[i] = aSums;
        }
        for (int i = 0; i < n; i++) {
            bSums += bSlice[i];
            bCumulativeSum[i] = bSums;
        }
        Integer aSumsCount = A.getOrDefault(aSums, 0);
        A.put(aSums, aSumsCount + 1);
        Integer bSumsCount = B.getOrDefault(bSums, 0);
        B.put(bSums, bSumsCount + 1);

        initConnectedPizza(A, m, aCumulativeSum);
        initConnectedPizza(B, n, bCumulativeSum);

    }

    void initConnectedPizza(HashMap<Integer, Integer> target, int total, int[] cumulativeSum) {
        for (int sliceCount = 2; sliceCount <= total - 1; sliceCount++) {
            for (int i = 0; i < total; i++) {
                endIndex = i + sliceCount - 1;
                if (endIndex >= total) {
                    subSum = cumulativeSum[total - 1];
                    subSum -= cumulativeSum[i - 1];
                    subSum += cumulativeSum[endIndex - total];
                } else {
                    subSum = cumulativeSum[endIndex];
                    if (i > 0) {
                        subSum -= cumulativeSum[i - 1];
                    }

                }
                count = target.getOrDefault(subSum, 0);
                target.put(subSum, count + 1);
            }
        }
    }

    void solution() throws IOException {
        answer += A.getOrDefault(pizzaSize, 0);
        answer += B.getOrDefault(pizzaSize, 0);
        for (Integer sizeA : A.keySet()) {
            if (sizeA >= pizzaSize) {
                continue;
            }
            answer += A.get(sizeA) * B.getOrDefault(pizzaSize - sizeA, 0);
        }
        System.out.println(answer);
    }


}
