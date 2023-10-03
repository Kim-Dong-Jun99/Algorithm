
import java.util.*;
import java.io.*;

/*
n * n 격자

사람들이 탑승할 칸은 아래 우선 순위를 기준으로 정해짐

1. 인접한 칸 중 앉아있는 좋아하는 친구의 수가 가장 많은 위치로
2. 인접한 칸 중 비어있는 칸의 수가 가장 많은 위치로
3. 그중 행 번호가 가장 작은 위치로
4. 그중 열번호가 가장 작은 위치로
 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, 1, -1};
    int[] inputArray;
    int n;
    int[][] seats;
    Preference[] preferences;
    HashMap<Integer, Preference> preferenceMap;
    List<Seat> possibleSeats;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        seats = new int[n][n];
        preferenceMap = new HashMap<>();
        preferences = new Preference[n * n];

        for (int i = 0; i < n * n; i++) {
            inputArray = getInputArray();
            int studentNumber = inputArray[0];
            HashSet<Integer> preferStudents = new HashSet<>();
            preferStudents.add(inputArray[1]);
            preferStudents.add(inputArray[2]);
            preferStudents.add(inputArray[3]);
            preferStudents.add(inputArray[4]);
            preferences[i] = new Preference(studentNumber, preferStudents);
            preferenceMap.put(studentNumber, preferences[i]);

        }

    }


    void solution() throws IOException {
        for (Preference preference : preferences) {
            possibleSeats = getPossibleSeats(preference);
            possibleSeats.sort(Seat::getBestSeat);
            Seat seat = possibleSeats.get(0);
            seats[seat.position.x][seat.position.y] = preference.studentNumber;
        }
        printResult();
    }

    List<Seat> getPossibleSeats(Preference preference) {
        List<Seat> toReturn = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == 0) {
                    int empty = 0;
                    int friend = 0;
                    for (int d = 0; d < 4; d++) {
                        int newX = i + DX[d];
                        int newY = j + DY[d];
                        if (isInner(newX, newY)) {
                            if (seats[newX][newY] == 0) {
                                empty += 1;
                            } else {
                                if (preference.preferStudents.contains(seats[newX][newY])) {
                                    friend += 1;
                                }
                            }
                        }
                    }
                    toReturn.add(new Seat(new Position(i, j), friend, empty));
                }
            }
        }

        return toReturn;
    }


    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    void printResult() {
        int score = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Preference preference = preferenceMap.get(seats[i][j]);
                int friendCount = 0;
                for (int d = 0; d < 4; d++) {
                    int newX = i + DX[d];
                    int newY = j + DY[d];
                    if (isInner(newX, newY) && preference.preferStudents.contains(seats[newX][newY])) {
                        friendCount += 1;
                    }
                }
                if (friendCount != 0) {
                    score += (int) Math.pow(10, friendCount - 1);
                }
            }
        }
        System.out.println(score);
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Seat {
        Position position;
        int friendCount;
        int emptyCount;

        public Seat(Position position, int friendCount, int emptyCount) {
            this.position = position;
            this.friendCount = friendCount;
            this.emptyCount = emptyCount;
        }

        int getBestSeat(Seat compare) {
            if (this.friendCount != compare.friendCount) {
                return Integer.compare(compare.friendCount, this.friendCount);
            } else if (this.emptyCount != compare.emptyCount) {
                return Integer.compare(compare.emptyCount, this.emptyCount);
            } else if (this.position.x != compare.position.x) {
                return Integer.compare(this.position.x, compare.position.x);
            } else {
                return Integer.compare(this.position.y, compare.position.y);
            }
        }
    }


    static class Preference {
        int studentNumber;
        HashSet<Integer> preferStudents;

        public Preference(int studentNumber, HashSet<Integer> preferStudents) {
            this.studentNumber = studentNumber;
            this.preferStudents = preferStudents;

        }

    }


}
