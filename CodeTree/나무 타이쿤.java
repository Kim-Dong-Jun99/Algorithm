import java.util.*;
import java.io.*;

/*
n * n 격자

서로 다른 높이를 가진 리브로수

특수 영양제 -> 1 * 1 땅에 리브로수 높이를 1증가, 씨앗만 있으면 높이 1의 리브로수를 만들어낸다

특수영양제를 규칙에따라 이동시킨다

이동시킨 후 해당 땅에 특수 영양제 투입

툭수 영양제를 투입한 리브로수의 대각선으로 인접한 방향에 높이가 1이상인 리브로수가 있는 만큼 높이가 더 성장, 격자가 벗어난 경우 세지 않음

특수 영양제를 투입한 리브로수를 제외하고 높이가 2 이상인 리브로수는 높이 2를 베어서 잘라낸 리브로수로 영양제를 사고, 해당 위치에 특수 영양제를 올려준다

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] DY = {1, 1, 0, -1, -1, -1, 0, 1};
    int[] inputArray;
    int n, m;
    int[][] treeBoard;
    boolean[][] nutritionBoard;
    int year;
    Move[] moves;
    List<Position> nutritionList;
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

        treeBoard = new int[n][n];
        nutritionBoard = new boolean[n][n];
        moves = new Move[m];
        nutritionList = Arrays.asList(
                new Position(n - 1, 0),
                new Position(n - 1, 1),
                new Position(n - 2, 0),
                new Position(n - 2, 1)
        );
        for (int i = 0; i < n; i++) {
            treeBoard[i] = getInputArray();
        }

        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            moves[i] = new Move(inputArray[0] - 1, inputArray[1]);
        }
    }


    void solution() throws IOException {
        while (year < m) {
            Move move = moves[year];
            moveNutrition(move);
            dropNutrition();
            growTree();
            buyNutrition();
            year += 1;
        }
        printHeightSum();
    }

    void moveNutrition(Move move) {

        boolean[][] newNutritionBoard = new boolean[n][n];
        List<Position> newNutritionList = new ArrayList<>();

        for (Position nutrition : nutritionList) {
            Position nextPosition = getNextPosition(nutrition, move.d, move.p);
            newNutritionList.add(nextPosition);
            newNutritionBoard[nextPosition.x][nextPosition.y] = true;
        }

        nutritionList = newNutritionList;
        nutritionBoard = newNutritionBoard;
    }

    void dropNutrition() {
        for (Position position : nutritionList) {
            treeBoard[position.x][position.y] += 1;
        }
    }

    void growTree() {
        for (Position position : nutritionList) {
            for (int d = 1; d < 8; d += 2) {
                int newX = position.x + DX[d];
                int newY = position.y + DY[d];
                if (isInner(newX, newY) && treeBoard[newX][newY] > 0) {
                    treeBoard[position.x][position.y] += 1;
                }
            }
        }

    }

    void buyNutrition() {
        nutritionList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (treeBoard[i][j] >= 2 && !nutritionBoard[i][j]) {
                    treeBoard[i][j] -= 2;
                    nutritionList.add(new Position(i, j));
                }
            }
        }
    }

    void printHeightSum() {
        int heightSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                heightSum += treeBoard[i][j];
            }
        }
        System.out.println(heightSum);
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    Position getNextPosition(Position position, int direction, int distance) {
        return new Position((position.x + DX[direction]*distance + n) % n, (position.y + DY[direction]*distance + n) % n);
    }


    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Move {
        int d;
        int p;

        public Move(int d, int p) {
            this.d = d;
            this.p = p;
        }
    }

}
