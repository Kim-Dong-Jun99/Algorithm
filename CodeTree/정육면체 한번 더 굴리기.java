import java.util.*;
import java.io.*;

/*

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};
    static final int[] OPPOSITE = {2, 3, 0, 1};
    static final int[] CW = {1, 2, 3, 0};
    static final int[] CCW = {3, 0, 1, 2};
    int[] inputArray;
    int n, m;
    int[][] board;
    Dice dice;
    int score;
    int round;

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

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = getInputArray();
        }

        dice = new Dice();
        score = 0;
        round = 0;
    }


    void solution() throws IOException {
        while (round < m) {
            rollDice();
            score += calculateScore();
            round += 1;
        }
        System.out.println(score);
    }

    void rollDice() {
        int newX = dice.position.x + DX[dice.direction];
        int newY = dice.position.y + DY[dice.direction];
        if (!isInner(newX, newY)) {
            dice.direction = OPPOSITE[dice.direction];
            newX = dice.position.x + DX[dice.direction];
            newY = dice.position.y + DY[dice.direction];
        }

        dice.roll();
        dice.position.x = newX;
        dice.position.y = newY;

        if (board[dice.position.x][dice.position.y] < dice.sides[5]) {
            dice.direction = CW[dice.direction];
        } else if (board[dice.position.x][dice.position.y] > dice.sides[5]) {
            dice.direction = CCW[dice.direction];
        }

    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    int calculateScore() {
        int calculatedSum = 0;
        boolean[][] visited = new boolean[n][n];
        List<Position> currentNodes = Collections.singletonList(dice.position);
        visited[dice.position.x][dice.position.y] = true;
        while (!currentNodes.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position currentNode : currentNodes) {
                calculatedSum += board[currentNode.x][currentNode.y];
                for (int i = 0; i < 4; i++) {
                    int newX = currentNode.x + DX[i];
                    int newY = currentNode.y + DY[i];
                    if (isInner(newX, newY) && !visited[newX][newY] && board[newX][newY] == board[dice.position.x][dice.position.y]) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            currentNodes = temp;
        }
        return calculatedSum;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Dice {
        Position position = new Position(0, 0);

        int[] sides = {1, 2, 3, 4, 5, 6};

        int direction = 1;

        void roll() {
            int[] newSides = new int[6];
            if (direction == 0) {
                newSides[3] = sides[3];
                newSides[2] = sides[2];
                newSides[0] = sides[1];
                newSides[1] = sides[5];
                newSides[5] = sides[4];
                newSides[4] = sides[0];

            } else if (direction == 1) {
                newSides[1] = sides[1];
                newSides[4] = sides[4];
                newSides[0] = sides[3];
                newSides[2] = sides[0];
                newSides[3] = sides[5];
                newSides[5] = sides[2];
            } else if (direction == 2) {
                newSides[3] = sides[3];
                newSides[2] = sides[2];
                newSides[0] = sides[4];
                newSides[1] = sides[0];
                newSides[5] = sides[1];
                newSides[4] = sides[5];
            } else {
                newSides[1] = sides[1];
                newSides[4] = sides[4];
                newSides[0] = sides[2];
                newSides[2] = sides[5];
                newSides[5] = sides[3];
                newSides[3] = sides[0];
            }
            sides = newSides;
        }

    }

}
