import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    int K;
    int[] built;
    HashMap<Integer, List<Integer>> connection;
    int[] mustBuildCount;
    int[] uniqueNeedBuildCount;
    String[] commands;
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
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        K = inputArray[2];
        connection = new HashMap<>();
        uniqueNeedBuildCount = new int[N + 1];
        mustBuildCount = new int[N + 1];
        for (int i = 0; i < M; i++) {
            int[] tempInputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            List<Integer> build = connection.getOrDefault(tempInputArray[0], new ArrayList<>());
            build.add(tempInputArray[1]);
            connection.put(tempInputArray[0], build);
            mustBuildCount[tempInputArray[1]] += 1;


        }
        built = new int[N + 1];
        commands = new String[K];
        for (int i = 0; i < K; i++) {
            commands[i] = BR.readLine();
        }
    }


    void solution() throws IOException {
        try {
            for (String commandString : commands) {
                int[] command = Arrays.stream(commandString.split(" ")).mapToInt(Integer::parseInt).toArray();
                if (command[0] == 1) {
                    build(command[1]);
                } else {
                    remove(command[1]);
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("Lier!");
            return;
        }
        System.out.println("King-God-Emperor");
    }

    void build(int building) throws IllegalStateException {
        if (uniqueNeedBuildCount[building] >= mustBuildCount[building]) {
            if (built[building] == 0) {
                List<Integer> connected = connection.getOrDefault(building, new ArrayList<>());
                for (Integer connectedBuilding : connected) {
                    uniqueNeedBuildCount[connectedBuilding] += 1;
                }
            }
            built[building] += 1;
        } else {
            throw new IllegalStateException();
        }
    }

    void remove(int building) throws IllegalStateException {
        if (built[building] == 0) {
            throw new IllegalStateException();
        }
        built[building] -= 1;
        if (built[building] == 0) {
            List<Integer> connected = connection.getOrDefault(building, new ArrayList<>());
            for (Integer connectedBuilding : connected) {
                uniqueNeedBuildCount[connectedBuilding] -= 1;
            }
        }
    }

}

