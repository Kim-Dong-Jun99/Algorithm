import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.*;

/*
	손님을 도착지로 데려다줄때마다 연료가 충전, 바닥나면 업무 종료
    m 명을 태우는 것이 목표, n*n 격자,

    택시가 빈칸에 있을 때, 상하좌우로 인접한 빈칸 중 하나로 이동 가능,

    m명의 승객, 빈칸 중 하나에 서있음,
    최단거리가 가장 짧은 승객을 선정, 그런 승객이 여러명이면, 행번호, 작은순 그 다음 열번호 작은순

    한칸 이동마다 1 연료 소모,
    목적지에 도착하면 소모한 연료 2배 충전, 목적지 도착과 동시에 연료가 바닥이면 실패 아님
    모든 승객을 데려다줄 수 있는지 알아내고, 데려다줄 수 있는 경우 최종적으로 남는 연료양 구하기
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};

    public static final int WALL = 1;
    public static final int EMPTY = 0;
    public static final int PASSENGER = 2;

    int[] inputArray;
    int N, M, fuel;
    int[][] board;
    int delivered;
    Position taxi;
    Position[][] goals;
    int answer;

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
        fuel = inputArray[2];
        board = new int[N][N];
        goals = new Position[N][N];
        delivered = 0;
        for (int i = 0; i < N; i++) {
            board[i] = getInputArray();
        }

        inputArray = getInputArray();
        taxi = new Position(inputArray[0] - 1, inputArray[1] - 1);
        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            board[inputArray[0]-1][inputArray[1]-1] = PASSENGER;
            goals[inputArray[0]-1][inputArray[1]-1] = new Position(inputArray[2]-1, inputArray[3]-1);
        }
    }

    void solve() throws IOException {
        while (delivered < M) {
            Passenger nextPassenger = getNextPassenger();
            if (canPickUp(nextPassenger)) {
                pickUp(nextPassenger);
            } else {
                answer = -1;
                return;
            }
            int goalDistance = toGoal(nextPassenger);
            if (goalDistance != -1 && fuel >= goalDistance) {
                fuel += goalDistance;
                taxi = goals[taxi.x][taxi.y];
            } else {
                answer = -1;
                return;
            }
            delivered += 1;
        }
        answer = fuel;
    }

    private int toGoal(Passenger p) {
        int result = -1;
        int moved = 0;
        boolean[][] visited = new boolean[N][N];
        Position goal = goals[p.x][p.y];
        List<Position> positions = Collections.singletonList(taxi);
        while (!positions.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position position : positions) {
                for (int d = 0; d < 4; d++) {
                    int newX = position.x + DX[d];
                    int newY = position.y + DY[d];
                    if (isInner(newX, newY) && !visited[newX][newY] && board[newX][newY] != WALL) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            moved += 1;
            if (visited[goal.x][goal.y]) {
                result = moved;
                break;
            }
            positions = temp;
        }
        return result;
    }

    boolean canPickUp(Passenger p) {
        return p != null && fuel >= p.d;
    }
    void pickUp(Passenger p) {
        fuel -= p.d;
        taxi.x = p.x;
        taxi.y = p.y;
        board[p.x][p.y] = EMPTY;
    }
    void printResult() throws IOException {
        System.out.println(answer);
    }

    Passenger getNextPassenger() {
        if (board[taxi.x][taxi.y] == PASSENGER) {
            return new Passenger(taxi.x, taxi.y, 0);
        }
        List<Position> positions = Collections.singletonList(taxi);
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Passenger> heap = new PriorityQueue<>(Passenger::sortWithDAndXAndY);
        int moved = 1;
        while (!positions.isEmpty() && heap.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position position : positions) {
                for (int d = 0; d < 4; d++) {
                    int newX = position.x + DX[d];
                    int newY = position.y + DY[d];
                    if (isInner(newX, newY) && !visited[newX][newY] && board[newX][newY] != WALL) {
                        visited[newX][newY] = true;
                        temp.add(new Position(newX, newY));
                        if (board[newX][newY] == PASSENGER) {
                            heap.add(new Passenger(newX, newY, moved));
                        }
                    }
                }
            }
            moved += 1;
            positions = temp;
        }
        return heap.peek();

    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Passenger {
        int x;
        int y;
        int d;

        public Passenger(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        int sortWithDAndXAndY(Passenger compare) {
            if (this.d != compare.d) {
                return Integer.compare(this.d, compare.d);
            }
            if (this.x != compare.x) {
                return Integer.compare(this.x, compare.x);
            }
            return Integer.compare(this.y, compare.y);
        }
    }
}