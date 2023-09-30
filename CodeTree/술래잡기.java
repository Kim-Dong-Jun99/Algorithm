import java.util.*;
import java.io.*;

/*
n*n 보드, 중앙에 술래가 있음

m 명의 도망자, 좌우로만 움직이는 유형, 상하로만 움직이는 유형이 있음
좌우로 움직이는 사람은 항상 오른쪽을, 상하로 움직이는 사람은 아래쪽을 보고있음

h 개의 나무가 있음

m 명의 도망자가 먼저 동시에 움직이고, 술래가 움직이는 것을 k 번 반복
도망자가 움직일때, 술래와의 거리가 3이하인 도망자만 움직인다.

도망자는 격자를 벗어나는 경우 방향을 턴하고, 이동하려는 칸에 술래가 없으면 움직인다.

술래는 달팽이처럼 움직이고, 이동을 마치고 바로 방향을 틀고, 바라보는 방향 3칸 내에 있는 도망자를 잡는다.
이때 도망자가 있는 칸에 나무가 있으면 해당 도망자는 안 잡힘

n <= 99
m, h <= n ^ 2
k <= 100

catcher의 방향을 저장할 위치를 이중 배열로 만들어서 미리 표기해두고, 그 점에 도달하면 방향 턴하는 식으로 구현하면될듯?

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 0, 1, 0, 1, 1, -1, -1};
    static final int[] DY = {0, 1, 0, -1, 1, -1, 1, -1};
    static final int[] OPPOSITE = {2, 3, 0, 1};
    int[] inputArray;
    boolean[][] treeBoard;
    boolean[][] turnBoard;
    List<Runner> runners;
    Catcher catcher;
    int n, m, h, k;
    int round;
    int score;

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
        inputArray = getInputArray();
        n = inputArray[0];
        m = inputArray[1];
        h = inputArray[2];
        k = inputArray[3];

        runners = new ArrayList<>();
        turnBoard = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            runners.add(new Runner(new Position(inputArray[0] - 1, inputArray[1] - 1), inputArray[2]));
        }

        treeBoard = new boolean[n][n];
        for (int i = 0; i < h; i++) {
            inputArray = getInputArray();
            treeBoard[inputArray[0] - 1][inputArray[1] - 1] = true;
        }

        catcher = new Catcher(new Position(n / 2, n / 2));

        round = 1;
        score = 0;

        initTurnBoard();

    }

    void initTurnBoard() {
        turnBoard[0][0] = true;
        turnBoard[n / 2][n / 2] = true;
        turnBoard[n / 2 - 1][n / 2] = true;

        int distance = 1;
        while (n / 2 - distance >= 0) {
            for (int i = 4; i < 8; i++) {
                int newX = n / 2 + DX[i] * distance;
                int newY = n / 2 + DY[i] * distance;
                if (i == 7) {
                    newX -= 1;
                }
                if (isInner(newX, newY)) {
                    turnBoard[newX][newY] = true;
                }
            }
            distance += 1;
        }
//        System.out.println("turnboard");
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(turnBoard[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }


    void solution() throws IOException {
        while (round <= k) {
//            System.out.println("round "+round);
            moveRunners();
            moveCatcher();
            catchRunners();
            round += 1;
        }
        System.out.println(score);
    }

    void moveRunners() {
        for (Runner runner : runners) {
//            System.out.println("runner from " + runner.position.x + " " + runner.position.y);
            if (mustMove(runner)) {

                int newX = runner.position.x + DX[runner.direction];
                int newY = runner.position.y + DY[runner.direction];

                if (!isInner(newX, newY)) {
                    runner.direction = OPPOSITE[runner.direction];
                    newX = runner.position.x + DX[runner.direction];
                    newY = runner.position.y + DY[runner.direction];
                }

                if (newX != catcher.position.x || newY != catcher.position.y) {
                    runner.position.x = newX;
                    runner.position.y = newY;
                }


            }
//            System.out.println("runner to " + runner.position.x + " " + runner.position.y);
        }

    }

    boolean mustMove(Runner runner) {
        return (Math.abs(catcher.position.x - runner.position.x) + Math.abs(catcher.position.y - runner.position.y) <= 3);
    }

    void moveCatcher() {
//        System.out.println("from catcher " + catcher.position.x + " " + catcher.position.y);
        int newX = catcher.position.x + DX[catcher.direction];
        int newY = catcher.position.y + DY[catcher.direction];
        if (turnBoard[newX][newY]) {
            if (newX == n / 2 && newY == n / 2) {
                catcher.goOuter = true;
                catcher.direction = OPPOSITE[catcher.direction];
            } else if (newX == 0 && newY == 0) {
                catcher.goOuter = false;
                catcher.direction = OPPOSITE[catcher.direction];
            } else {
                if (catcher.goOuter) {
                    catcher.direction = getCWDirection(catcher.direction);
                } else {
                    catcher.direction = getCCWDirection(catcher.direction);
                }
            }

        }
        catcher.position.x = newX;
        catcher.position.y = newY;

//        System.out.println("to catcher " + catcher.position.x + " " + catcher.position.y);

    }

    void catchRunners() {
        boolean[][] canCatch = new boolean[n][n];
        canCatch[catcher.position.x][catcher.position.y] = true;
        for (int i = 1; i <= 2; i++) {
            int newX = catcher.position.x + DX[catcher.direction] * i;
            int newY = catcher.position.y + DY[catcher.direction] * i;
            if (!isInner(newX, newY)) {
                break;
            }
//            System.out.println("can catch "+newX+" "+newY);
            canCatch[newX][newY] = true;
        }
        List<Runner> newRunners = new ArrayList<>();
        int catched = 0;
        for (Runner runner : runners) {
            if (canCatch[runner.position.x][runner.position.y] && !treeBoard[runner.position.x][runner.position.y]) {
                catched += 1;
            } else {
                newRunners.add(runner);
            }
        }
        score += round * catched;
        runners = newRunners;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    int getCWDirection(int direction) {
        return (direction + 1) % 4;
    }

    int getCCWDirection(int direction) {
        return (direction - 1 + 4) % 4;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Runner {
        Position position;
        int direction;

        public Runner(Position position, int direction) {
            this.position = position;
            this.direction = direction;
        }
    }

    static class Catcher {
        Position position;
        int direction;
        boolean goOuter;

        public Catcher(Position position) {
            this.position = position;
            this.direction = 0;
            this.goOuter = true;
        }
    }

}
