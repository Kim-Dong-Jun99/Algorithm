import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, C;
    int[] houses;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        C = inputArray[1];
        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(BR.readLine());
        }
        Arrays.sort(houses);
    }

    void solve() throws IOException {
        int left = 1;
        int right = (houses[N - 1] - houses[0] ) / (C-1);
        int answer = 0;
        while (left <= right) {
            int current = houses[0];
            int visited = 1;
            int mid = (left + right) / 2;
            for (int i = 1; i < N; i++) {
                if (current + mid <= houses[i]) {
                    visited += 1;
                    current = houses[i];
                }
            }
            if (visited >= C) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}