import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int M;
    int N;
    int L;
    int[] shootingRanges;
    int[][] animals;


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = inputArray[0];
        N = inputArray[1];
        L = inputArray[2];

        shootingRanges = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        animals = new int[N][2];
        for (int i = 0; i < N; i++) {
            int[] animal = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            animals[i][0] = animal[0];
            animals[i][1] = animal[1];
        }

    }

    void solution() throws Exception{
        Arrays.sort(shootingRanges);
        Arrays.sort(animals, Comparator.comparing((int[] animal) -> animal[0]));
        int answer = 0;
        int index = 0;
        for (int[] animal : animals) {
            int calculatedDistance = calculateDistance(shootingRanges[index], animal);
            if (calculatedDistance <= L) {
                answer += 1;
            } else {
                if (index + 1 < M) {
                    if (calculateDistance(shootingRanges[index + 1], animal) <= L) {
                        answer += 1;
                        index += 1;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    int calculateDistance(int shoot, int[] animal) {
        return Math.abs(shoot - animal[0]) + animal[1];
    }
}