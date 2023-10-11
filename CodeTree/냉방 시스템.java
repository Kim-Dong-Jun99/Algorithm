import java.util.*;
import java.io.*;

/*

n * n 크기 격자,

0 빈공간
1 사무실
2 왼쪽 방향으로 향하는 에어컨
3 위쪽 방향으로 향하는 에어컨
4 오른쪽 방향으로 향하는 에어컨
5 아래쪽 방향으로 향하는 에어컨

바람은 에어컨이 향하는 방향으로 5짜리를 하나 놓고, 거기서 부터 재귀적으로 대각선 두개, 다음칸으로 n-1만큼 전파시키면될듯?
벽이 있으면 막힘

시원한 공기들이 섞임, 벽으로 인접하면 막히고, 높 -> 낮으로 시원함의 차이 / 4만큼 퍼져나감, 모든 칸에서 동시에 일어남

외벽에 있는 칸은 시원함이 1감소

칸에 모든 시원함이 k이상이 될 때까지 반복

*/

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, -1, 0, 1};
    static final int[] DY = {-1, 0, 1, 0};
    int[] inputArray;
    int[][] cool;
    int[][] coolWind;
    HashSet<Position>[][] cantGo;
    List<Position> offices;
    List<AC> acs;

    int n, m, k, time;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
            main.printResult();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        n = inputArray[0];
        m = inputArray[1];
        k = inputArray[2];
        time = 0;

        cool = new int[n][n];
        coolWind = new int[n][n];
        cantGo = new HashSet[n][n];
        offices = new ArrayList<>();
        acs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inputArray = getInputArray();
            for (int j = 0; j < n; j++) {
                cantGo[i][j] = new HashSet<>();
                if (inputArray[j] == 1) {
                    offices.add(new Position(i, j));
                }
                if (inputArray[j] >= 2) {
                    acs.add(new AC(new Position(i, j), inputArray[j] - 2));
                }
            }
        }

        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            int x = inputArray[0];
            int y = inputArray[1];
            int s = inputArray[2];

            x--;
            y--;

            if (s == 1) {
                cantGo[x][y - 1].add(new Position(x, y));
                cantGo[x][y].add(new Position(x, y - 1));
            } else {
                cantGo[x - 1][y].add(new Position(x, y));
                cantGo[x][y].add(new Position(x - 1, y));
            }
        }

        initACSpreadMap();

    }


    int getCWDirection(int direction) {
        return (direction + 1) % 4;
    }

    int getCCWDirection(int direction) {
        return (direction - 1 + 4) % 4;
    }

    void initACSpreadMap() {
        for (AC ac : acs) {
            int direction = ac.direction;
            int cwDirection = getCWDirection(ac.direction);
            int ccwDirection = getCCWDirection(ac.direction);
            HashSet<Position> visited = new HashSet<>();
            List<Position> currentPositions = Collections.singletonList(ac.p);

            for (int i = 0; i < 5; i++) {
                List<Position> temp = new ArrayList<>();
                if (i == 0) {
                    for (Position current : currentPositions) {
                        Position nextPosition = getNextPosition(current, direction);
                        if (isInner(nextPosition) && !cantGo[current.x][current.y].contains(nextPosition)) {
                            if (!visited.contains(nextPosition)) {
                                temp.add(nextPosition);
                                visited.add(nextPosition);
                                coolWind[nextPosition.x][nextPosition.y] += 5 - i;
                            }
                        }
                    }
                } else {
                    for (Position current : currentPositions) {
                        Position nextPosition = getNextPosition(current, direction);
                        Position cwPosition = getNextPosition(current, cwDirection);
                        Position ccwPosition = getNextPosition(current, ccwDirection);

                        if (isInner(nextPosition) && !cantGo[current.x][current.y].contains(nextPosition)) {
                            if (!visited.contains(nextPosition)) {
                                temp.add(nextPosition);
                                visited.add(nextPosition);
                                coolWind[nextPosition.x][nextPosition.y] += 5 - i;
                            }
                        }
                        if (isInner(cwPosition) && !cantGo[current.x][current.y].contains(cwPosition)) {
                            Position cwDiagonalPosition = getNextPosition(cwPosition, direction);
                            if (isInner(cwDiagonalPosition) && !cantGo[cwPosition.x][cwPosition.y].contains(cwDiagonalPosition)) {
                                if (!visited.contains(cwDiagonalPosition)) {
                                    temp.add(cwDiagonalPosition);
                                    visited.add(cwDiagonalPosition);
                                    coolWind[cwDiagonalPosition.x][cwDiagonalPosition.y] += 5 - i;
                                }
                            }
                        }
                        if (isInner(ccwPosition) && !cantGo[current.x][current.y].contains(ccwPosition)) {
                            Position ccwDiagonalPosition = getNextPosition(ccwPosition, direction);
                            if (isInner(ccwDiagonalPosition) && !cantGo[ccwPosition.x][ccwPosition.y].contains(ccwDiagonalPosition)) {
                                if (!visited.contains(ccwDiagonalPosition)) {
                                    temp.add(ccwDiagonalPosition);
                                    visited.add(ccwDiagonalPosition);
                                    coolWind[ccwDiagonalPosition.x][ccwDiagonalPosition.y] += 5 - i;
                                }
                            }
                        }


                    }

                }
//                ac.spreadMap.put(5 - i, temp);
                currentPositions = temp;
            }
        }
    }

    Position getNextPosition(Position position, int direction) {
        return new Position(position.x + DX[direction], position.y + DY[direction]);
    }

    void solution() {
//        printSpreadMap();
        while (time < 100) {
            spreadWind();
            mixWind();
            decreaseCool();
            time += 1;
            if (finished()) {
                break;
            }
        }

    }

//    void printSpreadMap() {
//        for (AC ac : acs) {
//            System.out.println("ac : " + ac.p.x + " " + ac.p.y);
//            for (int cool : ac.spreadMap.keySet()) {
//                System.out.println("cool " + cool);
//                for (Position p : ac.spreadMap.get(cool)) {
//                    System.out.print("("+p.x+", "+p.y+") ");
//                }
//                System.out.println();
//            }
//        }
//    }

    void spreadWind() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cool[i][j] += coolWind[i][j];
            }
        }
    }

    void mixWind() {
        int[][] newCool = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(cool[i], 0, newCool[i], 0, n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int decreased = 0;
                for (int d = 0; d < 4; d++) {
                    Position nextPosition = new Position(i + DX[d], j + DY[d]);
                    if (isInner(nextPosition) && cool[i][j] > cool[nextPosition.x][nextPosition.y] && !cantGo[i][j].contains(nextPosition)) {
                        int diff = cool[i][j] - cool[nextPosition.x][nextPosition.y];
                        newCool[nextPosition.x][nextPosition.y] += diff / 4;
                        decreased += diff / 4;
                    }
                }
                newCool[i][j] -= decreased;
            }
        }
        cool = newCool;
    }

    void decreaseCool() {
        for (int i = 1; i < n-1; i++) {
            if (cool[0][i] > 0) {
                cool[0][i] -= 1;
            }
            if (cool[n - 1][i] > 0) {
                cool[n - 1][i] -= 1;
            }
            if (cool[i][0] > 0) {
                cool[i][0] -= 1;
            }
            if (cool[i][n - 1] > 0) {
                cool[i][n - 1] -= 1;
            }
        }
        if (cool[0][0] > 0) {
            cool[0][0] -= 1;
        }
        if (cool[0][n - 1] > 0) {
            cool[0][n - 1] -= 1;
        }
        if (cool[n - 1][0] > 0) {
            cool[n - 1][0] -= 1;
        }
        if (cool[n - 1][n - 1] > 0) {
            cool[n - 1][n - 1] -= 1;
        }
    }

    void printResult() throws IOException {
        if (finished()) {
            System.out.println(time);
        } else {
            System.out.println(-1);
        }
    }

    boolean finished() {
        for (Position office : offices) {
            if (cool[office.x][office.y] < k) {
                return false;
            }
        }
        return true;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    boolean isInner(Position position) {
        return 0 <= position.x && position.x < n && 0 <= position.y && position.y < n;
    }
    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;

        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Position) {
                Position compare = (Position) obj;
                return this.x == compare.x && this.y == compare.y;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return x * 20 + y;
        }
    }

    static class AC {
        Position p;
        int direction;
//        HashMap<Integer, List<Position>> spreadMap;


        public AC(Position p, int direction) {
            this.p = p;
            this.direction = direction;
//            this.spreadMap = new HashMap<>();
        }
    }
}
