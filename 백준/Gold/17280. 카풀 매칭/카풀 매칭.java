import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T;
    int N;
    int M;
    int[] Xs;
    int answer;
    PriorityQueue<Driver> drivers;
    int index;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.testCase();
        } catch (Exception e) {
            e.printStackTrace();
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
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = inputArray[0];
        M = inputArray[1];
        Xs = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(Xs);

        drivers = new PriorityQueue<>(Driver::sortWithY);
        for (int i = 0; i < M; i++) {
            int[] driver = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            drivers.add(new Driver(driver[0], driver[1]));
        }

        answer = 0;
        index = 0;
    }

    void solution() throws Exception {
        while (!drivers.isEmpty() && index < N) {
            if (Xs[index] < drivers.peek().y) {
                index += 1;
            } else if (drivers.peek().z < Xs[index]) {
                drivers.remove();
            } else {
                PriorityQueue<Driver> getMinimumZ = new PriorityQueue<>(Driver::sortWithZ);
                while (!drivers.isEmpty() && drivers.peek().y <= Xs[index]) {
                    getMinimumZ.add(drivers.remove());
                }
                while (!getMinimumZ.isEmpty() && getMinimumZ.peek().z < Xs[index]) {
                    getMinimumZ.remove();
                }
                if (!getMinimumZ.isEmpty()) {
                    answer += 1;
                    index += 1;
                    getMinimumZ.remove();
                    while (!getMinimumZ.isEmpty()) {

                        drivers.add(getMinimumZ.remove());
                    }
                }

            }
        }
        BW.write(Integer.toString(answer));
        BW.newLine();
    }

    static class Driver {
        int y;
        int z;

        public Driver(int y, int z) {
            this.y = y;
            this.z = z;
        }


        public int sortWithY(Driver compare) {
            if (this.y < compare.y) {
                return -1;
            } else if (this.y == compare.y) {
                return Integer.compare(this.z, compare.z);
            } else {
                return 1;
            }
        }

        public int sortWithZ(Driver compare) {
            if (this.z < compare.z) {
                return -1;
            } else if (this.z == compare.z) {
                return Integer.compare(this.y, compare.y);
            } else {
                return 1;
            }
        }
    }

}