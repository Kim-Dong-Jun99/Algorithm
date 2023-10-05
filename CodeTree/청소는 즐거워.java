
import java.util.*;
import java.io.*;

/*
n * n 격자

빗자루가 이동한 위치에 있는 먼지는 모두 없어짐, 이동한 위치에 있는 먼지는 비율에 맞춰서 퍼지게된다.
a = 이동한 위치에 있는 먼지 - 퍼진 먼지양

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] inputArray;
    int n;
    int[][] dusts;
    boolean[][] turnBoard;
    int removedDustSum;
    Position current;
    int direction;
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
        dusts = new int[n][n];
        turnBoard = new boolean[n][n];
        removedDustSum = 0;
        current = new Position(n / 2, n / 2);
        direction = 0;

        for (int i = 0; i < n; i++) {
            dusts[i] = getInputArray();
        }

        initTurnBoard();
    }

    void initTurnBoard() {
        Position centerPosition = new Position(n / 2, n / 2);
        for (int i = 1; i <= n / 2; i++) {
            for (int d = 1; d < 8; d += 2) {

                int newX = centerPosition.x + DX[d] * i;
                int newY = centerPosition.y + DY[d] * i;
                if (d == 7) {
                    newX += 1;
                }
                turnBoard[newX][newY] = true;
            }
        }
    }

    int getCCWDirection(int direction) {
        return (direction + 1) % 8;
    }

    int getCWDirection(int direction) {
        return (direction - 1 + 8) % 8;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    void solution() throws IOException {
        while (true) {
            current.x += DX[direction];
            current.y += DY[direction];
            if (!isInner(current.x, current.y)) {
                break;
            }

            updateDust();

            if (turnBoard[current.x][current.y]) {
                direction = getCCWDirection(getCCWDirection(direction));
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dusts[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println(removedDustSum);
    }

    void updateDust() {
        int totalDust = dusts[current.x][current.y];
        dusts[current.x][current.y] = 0;
        int tempRemoved = 0;
        // 5%
        if (isInner(current.x + DX[direction] * 2, current.y + DY[direction] * 2)) {
            dusts[current.x + DX[direction] * 2][current.y + DY[direction] * 2] += (int) (totalDust * 0.05);
        }else {
            removedDustSum += (int) (totalDust * 0.05);
        }
        tempRemoved += (int) (totalDust * 0.05);
        // 10%
        if (isInner(current.x + DX[getCWDirection(direction)], current.y + DY[getCWDirection(direction)])) {
            dusts[current.x + DX[getCWDirection(direction)]][current.y + DY[getCWDirection(direction)]] += (int) (totalDust * 0.1);
        } else {
            removedDustSum += (int) (totalDust * 0.1);
        }
        if (isInner(current.x + DX[getCCWDirection(direction)], current.y + DY[getCCWDirection(direction)])) {
            dusts[current.x + DX[getCCWDirection(direction)]][current.y + DY[getCCWDirection(direction)]] += (int) (totalDust * 0.1);
        } else {
            removedDustSum += (int) (totalDust * 0.1);
        }
        tempRemoved += (int) (totalDust * 0.1);
        tempRemoved += (int) (totalDust * 0.1);
        // 7%
        if (isInner(current.x + DX[getCWDirection(getCWDirection(direction))], current.y + DY[getCWDirection(getCWDirection(direction))])) {
            dusts[current.x + DX[getCWDirection(getCWDirection(direction))]][current.y + DY[getCWDirection(getCWDirection(direction))]] += (int) (totalDust * 0.07);
        } else {
            removedDustSum += (int) (totalDust * 0.07);
        }
        if (isInner(current.x + DX[getCCWDirection(getCCWDirection(direction))], current.y + DY[getCCWDirection(getCCWDirection(direction))])) {
            dusts[current.x + DX[getCCWDirection(getCCWDirection(direction))]][current.y + DY[getCCWDirection(getCCWDirection(direction))]] += (int) (totalDust * 0.07);
        } else {
            removedDustSum += (int) (totalDust * 0.07);
        }
        tempRemoved += (int) (totalDust * 0.07);
        tempRemoved += (int) (totalDust * 0.07);
        // 2%
        if (isInner(current.x + DX[getCWDirection(getCWDirection(direction))] * 2, current.y + DY[getCWDirection(getCWDirection(direction))] * 2)) {
            dusts[current.x + DX[getCWDirection(getCWDirection(direction))] * 2][current.y + DY[getCWDirection(getCWDirection(direction))] * 2] += (int) (totalDust * 0.02);
        } else {
            removedDustSum += (int) (totalDust * 0.02);
        }
        if (isInner(current.x + DX[getCCWDirection(getCCWDirection(direction))] * 2, current.y + DY[getCCWDirection(getCCWDirection(direction))] * 2)) {
            dusts[current.x + DX[getCCWDirection(getCCWDirection(direction))] * 2][current.y + DY[getCCWDirection(getCCWDirection(direction))] * 2] += (int) (totalDust * 0.02);
        } else {
            removedDustSum += (int) (totalDust * 0.02);
        }
        tempRemoved += (int) (totalDust * 0.02);
        tempRemoved += (int) (totalDust * 0.02);
        // 1%
        if (isInner(current.x + DX[getCWDirection(getCWDirection(getCWDirection(direction)))], current.y + DY[getCWDirection(getCWDirection(getCWDirection(direction)))])) {
            dusts[current.x + DX[getCWDirection(getCWDirection(getCWDirection(direction)))]][current.y + DY[getCWDirection(getCWDirection(getCWDirection(direction)))]] += (int) (totalDust * 0.01);
        } else {
            removedDustSum += (int) (totalDust * 0.01);
        }
        if (isInner(current.x + DX[getCCWDirection(getCCWDirection(getCCWDirection(direction)))], current.y + DY[getCCWDirection(getCCWDirection(getCCWDirection(direction)))])) {
            dusts[current.x + DX[getCCWDirection(getCCWDirection(getCCWDirection(direction)))]][current.y + DY[getCCWDirection(getCCWDirection(getCCWDirection(direction)))]] += (int) (totalDust * 0.01);
        } else {
            removedDustSum += (int) (totalDust * 0.01);
        }
        tempRemoved += (int) (totalDust * 0.01);
        tempRemoved += (int) (totalDust * 0.01);
        // a%
        int remain = totalDust - tempRemoved;
        if (isInner(current.x + DX[direction], current.y + DY[direction])) {
            dusts[current.x + DX[direction]][current.y + DY[direction]] += remain;
        } else {
            removedDustSum += remain;
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
