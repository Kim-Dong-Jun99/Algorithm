import java.util.*;
import java.io.*;

/*
n*n 격자, m 명의 승객, 주어진 배터리 용량으로 승객을 모두 태울 수 있는지,

승객을 태우러 이동할 때 혹은 태우고 목적지로 이동할 때 항상 최단거리로 이동,
한칸 이동할때 1만큼 배터리 소모, 승객을 무사히 목적지로 태워주면 소모한 배터리 2배 만큼 충전한 뒤 이동,

승객이 여러명일 경우 현재 위치에서 최단거리가 가장 짧은 승객을 태운다. 그런 승객이 여러명일 경우, 가장 위, 가장 왼쪽 승객을 고른다.

도로의 상태가 주어질 때 모든 승객을 데려다주고 남은 배터리 양을 출력, 이동할 수 없다면 -1

n <= 20

*/

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};
    static final int EMPTY = 0;
    int[] inputArray;
    int[][] road;
    int[][] passengerBoard;
    HashSet<Integer>[][] destinationBoard;
    HashSet<Integer> visitedPassenger;
    boolean[][] visited;
    Position carPosition;
    Passenger passenger;
    int n, m, c;
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
        c = inputArray[2];

        road = new int[n][n];
        passengerBoard = new int[n][n];
        destinationBoard = new HashSet[n][n];
        visitedPassenger = new HashSet<>();

        for (int i = 0; i < n; i++) {
            road[i] = getInputArray();
            Arrays.fill(passengerBoard[i], -1);
            for (int j = 0; j < n; j++) {
                destinationBoard[i][j] = new HashSet<>();
            }
        }

        inputArray = getInputArray();
        carPosition = new Position(inputArray[0] - 1, inputArray[1] - 1);
        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            passengerBoard[inputArray[0] - 1][inputArray[1] - 1] = i;
            destinationBoard[inputArray[2] - 1][inputArray[3] - 1].add(i);
        }
    }

    void solution() {
        for (int i = 0; i < m; i++) {
            passenger = findNextPassenger();
            if (passenger.p == null) {
                c = -1;
                return;
            }
            c -= passenger.distance;
            if (c <= 0) {
                c = -1;
                return;
            }
            carPosition = passenger.p;
            visitedPassenger.add(passengerBoard[passenger.p.x][passenger.p.y]);
            int destination = passengerBoard[passenger.p.x][passenger.p.y];
            Destination toDestination = goToDestination(destination);
            if (toDestination.p == null) {
                c = -1;
                return;
            }
            c -= toDestination.distance;
            if (c < 0) {
                c = -1;
                return;
            }
            carPosition = toDestination.p;
            c += toDestination.distance * 2;

        }
    }

    Passenger findNextPassenger() {
        if (passengerBoard[carPosition.x][carPosition.y] != -1 && !visitedPassenger.contains(passengerBoard[carPosition.x][carPosition.y])) {
            return new Passenger(carPosition, 0);
        }
        visited = new boolean[n][n];
        int distance = 0;
        List<Position> currentPositions = Collections.singletonList(carPosition);
        PriorityQueue<Position> possiblePassengers = new PriorityQueue<>(Position::compareWithXAndY);
        visited[carPosition.x][carPosition.y] = true;
        while (!currentPositions.isEmpty()) {
            distance += 1;
            List<Position> temp = new ArrayList<>();
            for (Position current : currentPositions) {
                for (int d = 0; d < 4; d++) {
                    int newX = current.x + DX[d];
                    int newY = current.y + DY[d];
                    if (canGo(newX, newY)) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                        if (passengerBoard[newX][newY] != -1 && !visitedPassenger.contains(passengerBoard[newX][newY])) {
                            possiblePassengers.add(new Position(newX, newY));
                        }
                    }
                }
            }
            if (!possiblePassengers.isEmpty()) {
                break;
            }
            currentPositions = temp;
        }
        if (possiblePassengers.isEmpty()) {
            return new Passenger(null, -1);
        } else {
            return new Passenger(possiblePassengers.peek(), distance);
        }
    }

    boolean canGo(int x, int y) {
        return isInner(x, y) && road[x][y] == EMPTY && !visited[x][y];
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    Destination goToDestination(int destination) {
        visited = new boolean[n][n];
        int distance = 0;
        List<Position> currentPositions = Collections.singletonList(carPosition);
        visited[carPosition.x][carPosition.y] = true;
        while (!currentPositions.isEmpty()) {
            distance += 1;
            List<Position> temp = new ArrayList<>();
            for (Position current : currentPositions) {
                for (int d = 0; d < 4; d++) {
                    int newX = current.x + DX[d];
                    int newY = current.y + DY[d];
                    if (canGo(newX, newY)) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                        if (destinationBoard[newX][newY].contains(destination)) {
                            return new Destination(new Position(newX, newY), distance);
                        }
                    }
                }
            }
            currentPositions = temp;
        }
        return new Destination(null, -1);
    }

    void printResult() {
        System.out.println(c);
    }

    static class Passenger {
        Position p;
        int distance;

        public Passenger(Position p, int distance) {
            this.p = p;
            this.distance = distance;
        }
    }

    static class Destination {
        Position p;
        int distance;

        public Destination(Position p, int distance) {
            this.p = p;
            this.distance = distance;
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int compareWithXAndY(Position compare) {
            if (this.x != compare.x) {
                return Integer.compare(this.x, compare.x);
            }
            return Integer.compare(this.y, compare.y);
        }
    }
}
