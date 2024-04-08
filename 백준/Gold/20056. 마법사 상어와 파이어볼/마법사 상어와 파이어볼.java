import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] inputArray;
    int N, M, K;
    List<Fire> fires;
    HashMap<Integer, List<Fire>> map;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        M = inputArray[1];
        K = inputArray[2];
        fires = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            fires.add(new Fire(inputArray[0]-1, inputArray[1]-1, inputArray[2], inputArray[3], inputArray[4]));
        }
    }

    void solve() throws IOException {
        while (K-- > 0) {
            moveFire();
            splitFire();
        }
        calculateMass();
    }

    void moveFire() {
        map = new HashMap<>();
        for (Fire fire : fires) {
            int newX = getNextX(fire.x, fire.d, fire.s);
            int newY = getNextY(fire.y, fire.d, fire.s);

            int index = xyToIndex(newX, newY);
            List<Fire> fireList = map.getOrDefault(index, new ArrayList<>());
            fireList.add(new Fire(newX, newY, fire.m, fire.s, fire.d));
            map.put(index, fireList);
        }
    }

    int getNextX(int x, int d, int s) {
        int newX = x;
        if (DX[d] == 1) {
            newX = getRightPosition(x, s);
        }
        if (DX[d] == -1) {
            newX = getLeftPosition(x, s);
        }
        return newX;
    }

    int getNextY(int y, int d, int s) {
        int newY = y;
        if (DY[d] == 1) {
            newY = getRightPosition(y, s);
        }
        if (DY[d] == -1) {
            newY = getLeftPosition(y, s);
        }
        return newY;
    }

    int getRightPosition(int index, int s) {
        s %= N;
        return (index + s) % N;
    }

    int getLeftPosition(int index, int s) {
        s %= N;
        return (index + (N-s)) % N;
    }

    int xyToIndex(int x, int y) {
        return x * N + y;
    }

    void splitFire() {
        List<Fire> newFires = new ArrayList<>();
        for (int index : map.keySet()) {
            List<Fire> fireList = map.get(index);
            if (fireList.size() == 1) {
                newFires.add(fireList.get(0));
            } else {
                int massSum = 0;
                int speedSum = 0;
                int directionSum = 0;
                for (Fire f : fireList) {
                    massSum += f.m;
                    speedSum += f.s;
                    directionSum += f.d % 2;
                }

                int newMass = massSum / 5;
                int newSpeed = speedSum / fireList.size();
                int newX = index / N;
                int newY = index % N;

                if (newMass == 0) {
                    continue;
                }

                if (directionSum == fireList.size() || directionSum == 0) {
                    for (int d = 0; d < 8; d += 2) {
                        newFires.add(new Fire(newX, newY, newMass, newSpeed, d));
                    }
                } else {
                    for (int d = 1; d < 8; d += 2) {
                        newFires.add(new Fire(newX, newY, newMass, newSpeed, d));
                    }
                }
            }

        }
        fires = newFires;
    }

    void calculateMass() throws IOException {
        int sum = 0;
        for (Fire f: fires) {
            sum += f.m;
        }
        BW.write(Integer.toString(sum));
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Fire {
        int x;
        int y;
        int m;
        int s;
        int d;

        Fire(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}