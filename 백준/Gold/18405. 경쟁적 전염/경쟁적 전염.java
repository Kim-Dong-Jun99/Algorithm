import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    int[] inputArray;
    int N, K, S, X, Y;
    int[][] virus;

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
        K = inputArray[1];
        virus = new int[N][N];
        for (int i = 0; i < N; i++) {
            virus[i] = getInputArray();
        }
        inputArray = getInputArray();
        S = inputArray[0];
        X = inputArray[1]-1;
        Y = inputArray[2]-1;
    }

    void solve() throws IOException {
        int time = 0;
        List<Position> current = getCurrent();
        while (!current.isEmpty() && time < S) {
            List<Position> temp = new ArrayList<>();
            for (Position p : current) {
                for (int d = 0; d < 4; d++) {
                    int newX = p.x + DX[d];
                    int newY = p.y + DY[d];
                    if (isInner(newX, newY) && virus[newX][newY] == 0) {
                        virus[newX][newY] = p.virus;
                        temp.add(new Position(newX, newY, p.virus));
                    }
                }
            }
            time += 1;
            current = temp;
        }
        BW.write(Integer.toString(virus[X][Y]));
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    List<Position> getCurrent() {
        List<Position> ps = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (virus[i][j] != 0) {
                    ps.add(new Position(i, j, virus[i][j]));
                }
            }
        }
        ps.sort(Position::sortWithVirus);
        return ps;
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Position {
        int x;
        int y;
        int virus;

        Position(int x, int y, int virus) {
            this.x = x;
            this.y = y;
            this.virus = virus;
        }

        int sortWithVirus(Position compare) {
            return Integer.compare(this.virus, compare.virus);
        }
    }
}