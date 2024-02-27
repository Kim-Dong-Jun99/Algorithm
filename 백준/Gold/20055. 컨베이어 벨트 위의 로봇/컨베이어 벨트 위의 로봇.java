import java.io.*;
import java.util.*;

/*
	길이가 N인 컨베이어 벨트,
    i번 칸의 내구도는 Ai,
    0번 칸에서 올리고, N-1번 칸에서 내림,
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N, K;
    int[] belt;
    int answer;
    List<Integer> robots;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        K = inputArray[1];
        belt = getInputArray();
        robots = new ArrayList<>();
        answer = 0;
    }

    void solve() throws IOException {
        while (!isEnd()) {
            spinBelt();
            moveRobots();
            putRobot();
            updateAnswer();
        }

        System.out.println(answer);
    }

    boolean isEnd() {
        int count = 0;
        for (int health : belt) {
            if (health == 0) {
                count += 1;
            }
        }
        return count >= K;
    }

    void spinBelt() {
        int[] newBelt = new int[2 * N];
        List<Integer> newRobots = new ArrayList<>();
        for (int i = 0; i < 2 * N; i++) {
            newBelt[i] = belt[getLeftIndex(i)];;
        }
        for (Integer robot : robots) {
            int newRobot = robot + 1;
            if (newRobot != N - 1) {
                newRobots.add(newRobot);
            }
        }
        belt = newBelt;
        robots = newRobots;
    }

    int getLeftIndex(int index) {
        return (index + 2*N-1) % (2*N);
    }

    void moveRobots() {
        Set<Integer> robotPosition = new HashSet<>();
        List<Integer> newRobots = new ArrayList<>();
        for (Integer robot : robots) {
            if (!robotPosition.contains(robot + 1) && belt[robot + 1] > 0) {
                belt[robot+1] -= 1;
                if (robot + 1 != N - 1) {
                    newRobots.add(robot + 1);
                    robotPosition.add(robot + 1);
                }

            } else {
                newRobots.add(robot);
                robotPosition.add(robot);
            }
        }
        robots = newRobots;
    }

    void putRobot() {
        if (belt[0] > 0) {
            robots.add(0);
            belt[0] -= 1;
        }
    }

    void updateAnswer() {
        answer += 1;
    }
}