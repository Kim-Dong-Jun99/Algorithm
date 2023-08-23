import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] inputArray;
    int w, n, weight;
    int[] A;
    int[] smallIndex;
    int[] bigIndex;
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
        w = inputArray[0];
        n = inputArray[1];
        A = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        smallIndex = new int[400_000];
        bigIndex = new int[400_000];
        Arrays.fill(smallIndex, -1);
        Arrays.fill(bigIndex, -1);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                weight = A[i] + A[j];
                if (smallIndex[weight] == -1) {
                    smallIndex[weight] = i;
                    bigIndex[weight] = j;
                }
            }
        }
    }


    void solution() throws IOException {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                weight = w - (A[i] + A[j]);
                if (weight >= 400_000 || weight < 0 || smallIndex[weight] == -1) {
                    continue;
                }
                if (smallIndex[weight] != i && bigIndex[weight] != j && smallIndex[weight] != j && bigIndex[weight] != i) {
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
    }

}
