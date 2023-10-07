
import java.util.*;
import java.io.*;

/*
n * n

1개의 셀에는 코어 혹은 전선이 놓일 수 있다.

가장자리에는 전원이 흐르고 있음

core와 전원을 연결하는 전선은 직선으로만 설치 가능
전선 교차 x
가장 자리에 위치한 코어는 전원에 연결된 것으로 간주,

최대한 많은 코어에 전원을 연결했을 경우, 전선 길이의 합을 구해야함
여러 방법이 있을 경우 전선 길이의 합이 최소가 되는 값을 구해라


*/

class Solution {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int EMPTY = 0;
    static final int CORE = 1;
    static final int WIRE = 2;
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    List<Position> positions;

    int n;
    int[][] circuit;
    BacktrackResult answer;
    int T;

    public static void main(String[] args) {
        Solution solution = new Solution();
        try {
            solution.testcase();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    void testcase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        for (int i = 1; i <= T; i++) {
            init();
            solution();
            printResult(i);
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        circuit = new int[n][n];
        positions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            circuit[i] = getInputArray();
        }

        initPositions();
    }

    void initPositions() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (circuit[i][j] == CORE) {
                    positions.add(new Position(i, j));
                }
            }
        }
    }


    void solution() {
        answer = backtrack(0, 0, 0);
    }

    BacktrackResult backtrack(int index, int connected, int wireCount) {
        if (index == positions.size()) {
            return new BacktrackResult(connected, wireCount);
        }

        Position position = positions.get(index);
        PriorityQueue<BacktrackResult> backtrackResults = new PriorityQueue<>(BacktrackResult::getBestResult);
        if (isOnEdge(position)) {
            backtrackResults.add(backtrack(index + 1, connected + 1, wireCount));
        } else {
            backtrackResults.add(backtrack(index + 1, connected, wireCount));
            for (int d = 0; d < 4; d++) {
                if (canConnect(position, d)) {
                    wireCount += connect(position, d);

                    backtrackResults.add(backtrack(index + 1, connected + 1, wireCount));

                    wireCount -= unConnect(position, d);
                }
            }
        }

        return backtrackResults.peek();
    }

    boolean canConnect(Position position, int direction) {
        Position current = new Position(position.x + DX[direction], position.y + DY[direction]);

        while (isInner(current)) {
            if (circuit[current.x][current.y] != EMPTY) {
                return false;
            }
            current.x += DX[direction];
            current.y += DY[direction];
        }

        return true;
    }

    int connect(Position position, int direction) {
        int connect = 0;
        Position current = new Position(position.x + DX[direction], position.y + DY[direction]);
        while (isInner(current)) {
            connect += 1;
            circuit[current.x][current.y] = WIRE;
            current.x += DX[direction];
            current.y += DY[direction];
        }

        return connect;
    }

    int unConnect(Position position, int direction) {
        int unConnect = 0;
        Position current = new Position(position.x + DX[direction], position.y + DY[direction]);
        while (isInner(current)) {
            unConnect += 1;
            circuit[current.x][current.y] = EMPTY;
            current.x += DX[direction];
            current.y += DY[direction];
        }
        return unConnect;
    }

    boolean isInner(Position position) {
        return 0 <= position.x && position.x < n && 0 <= position.y && position.y < n;
    }

    boolean isOnEdge(Position position) {
        return position.x == 0 || position.y == 0 || position.x == n - 1 || position.y == n - 1;
    }

    void printResult(int testCase) {
        System.out.println("#" + testCase + " " + answer.wireCount);
    }

    static class BacktrackResult {
        int connected;
        int wireCount;

        public BacktrackResult(int connected, int wireCount) {
            this.connected = connected;
            this.wireCount = wireCount;
        }

        int getBestResult(BacktrackResult compare) {
            if (this.connected != compare.connected) {
                return Integer.compare(compare.connected, this.connected);
            }
            return Integer.compare(this.wireCount, compare.wireCount);
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}