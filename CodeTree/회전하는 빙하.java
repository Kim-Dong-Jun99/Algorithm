
import java.util.*;
import java.io.*;

/*
2^n * 2^n 격자

레벨에 맞게 빙하 돌리고
얼음 녹음
q번 반복


 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, 1, -1};
    int[] inputArray;
    int n, q, boardSize;
    int[][] glacierBoard;
    int[] spinLevels;
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
        q = inputArray[1];
        boardSize = (int) Math.pow(2, n);

        glacierBoard = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            glacierBoard[i] = getInputArray();
        }

        spinLevels = getInputArray();
    }


    void solution() throws IOException {
        for (Integer spinLevel : spinLevels) {
            spinGlaciers(spinLevel);
            meltGlaciers();
        }
        printResult();
    }

    void spinGlaciers(int level) {
        if (level > 0) {
            int[][] newBoard = new int[boardSize][boardSize];

            int interestBoardSize = (int) Math.pow(2, level);
            int spinBoardSize = (int) Math.pow(2, level - 1);

            for (int i = 0; i < boardSize; i += interestBoardSize) {
                for (int j = 0; j < boardSize; j += interestBoardSize) {

                    spinInsideBoard(i, j, spinBoardSize, newBoard);

                }
            }
            glacierBoard = newBoard;
        }
    }

    void spinInsideBoard(int x, int y, int spinBoardSize, int[][] newBoard) {
        Position[] leftTopPositions = {new Position(x, y), new Position(x, y + spinBoardSize), new Position(x + spinBoardSize, y + spinBoardSize), new Position(x + spinBoardSize, y)};

        for (int index = 0; index < 4; index++) {
            Position position = leftTopPositions[index];
            Position cwPosition = leftTopPositions[getCWPosition(index)];
            for (int i = 0; i < spinBoardSize; i++) {
                for (int j = 0; j < spinBoardSize; j++) {
                    newBoard[cwPosition.x + i][cwPosition.y + j] = glacierBoard[position.x + i][position.y + j];
                }
            }
        }


    }


    int getCWPosition(int index) {
        return (index + 1) % 4;
    }

    void meltGlaciers() {
        List<Position> toMelt = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (glacierBoard[i][j] > 0) {
                    int neighborCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int newX = i + DX[d];
                        int newY = j + DY[d];
                        if (isInner(newX, newY) && glacierBoard[newX][newY] > 0) {
                            neighborCount += 1;
                        }
                    }
                    if (neighborCount < 3) {
                        toMelt.add(new Position(i, j));
                    }
                }
            }
        }

        for (Position position : toMelt) {
            glacierBoard[position.x][position.y] -= 1;
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < boardSize && 0 <= y && y < boardSize;
    }

    void printResult() {
        boolean[][] visited = new boolean[boardSize][boardSize];
        int glacierCount = 0;
        int maxSize = 0;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (glacierBoard[i][j] > 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    int tempMaxSize = 0;
                    List<Position> currentPositions = Collections.singletonList(new Position(i, j));
                    while (!currentPositions.isEmpty()) {
                        List<Position> temp = new ArrayList<>();
                        for (Position currentPosition : currentPositions) {
                            tempMaxSize += 1;
                            glacierCount += glacierBoard[currentPosition.x][currentPosition.y];
                            for (int d = 0; d < 4; d++) {
                                int newX = currentPosition.x + DX[d];
                                int newY = currentPosition.y + DY[d];
                                if (isInner(newX, newY) && !visited[newX][newY] && glacierBoard[newX][newY] > 0) {
                                    temp.add(new Position(newX, newY));
                                    visited[newX][newY] = true;
                                }
                            }
                        }
                        currentPositions = temp;
                    }
                    maxSize = Math.max(maxSize, tempMaxSize);
                }
            }
        }
        System.out.println(glacierCount);
        System.out.println(maxSize);
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
