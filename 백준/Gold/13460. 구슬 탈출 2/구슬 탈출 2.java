import java.util.*;
import java.io.*;


class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final Character RED = 'R';
    static final Character BLUE = 'B';
    static final Character HOLE = 'O';
    static final Character WALL = '#';
    static final Character EMPTY = '.';
    int[] inputArray;
    int N, M;
    Character[][] board;

    Position red, blue;

    int answer;

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
        N = inputArray[0];
        M = inputArray[1];

        board = new Character[N][M];

        for (int i = 0; i < N; i++) {
            String inputString = BR.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = inputString.charAt(j);
                if (board[i][j] == RED) {
                    red = new Position(i, j);
                    board[i][j] = WALL;
                }
                if (board[i][j] == BLUE) {
                    blue = new Position(i, j);
                    board[i][j] = WALL;
                }
            }
        }


    }

    void solution() throws IOException {
        answer = backtrack(0);
        if (answer > 10) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    int backtrack(int index) {
        if (index > 10) {
            return index;
        }


        if (isOut(blue)) {
            return 11;
        }

        if (isOut(red)) {
            return index;
        }

        int toReturn = 11;
        for (int direction : getMoveDirection()) {
            int pastRedX = red.x;
            int pastRedY = red.y;
            int pastBlueX = blue.x;
            int pastBlueY = blue.y;
            board[pastRedX][pastRedY] = EMPTY;
            board[pastBlueX][pastBlueY] = EMPTY;

            tiltBoard(direction);

            toReturn = Math.min(toReturn, backtrack(index + 1));

            board[pastRedX][pastRedY] = WALL;
            board[pastBlueX][pastBlueY] = WALL;
            if (board[red.x][red.y] != HOLE) {
                board[red.x][red.y] = EMPTY;
            }
            if (board[blue.x][blue.y] != HOLE) {
                board[blue.x][blue.y] = EMPTY;
            }

            red.x = pastRedX;
            red.y = pastRedY;
            blue.x = pastBlueX;
            blue.y = pastBlueY;
        }

        return toReturn;
    }

    void tiltBoard(int direction) {

        if (red.x == blue.x && (direction >= 2)) {
            if (direction == 2) {
                if (red.y < blue.y) {
                    tiltPosition(red, direction);
                    tiltPosition(blue, direction);
                } else {
                    tiltPosition(blue, direction);
                    tiltPosition(red, direction);
                }
            } else {
                if (red.y > blue.y) {
                    tiltPosition(red, direction);
                    tiltPosition(blue, direction);
                } else {
                    tiltPosition(blue, direction);
                    tiltPosition(red, direction);
                }
            }
        } else if (red.y == blue.y && (direction <= 1)) {
            if (direction == 0) {
                if (red.x < blue.x) {
                    tiltPosition(red, direction);
                    tiltPosition(blue, direction);
                } else {
                    tiltPosition(blue, direction);
                    tiltPosition(red, direction);
                }
            } else {
                if (red.x > blue.x) {
                    tiltPosition(red, direction);
                    tiltPosition(blue, direction);
                } else {
                    tiltPosition(blue, direction);
                    tiltPosition(red, direction);
                }
            }
        } else {
            tiltPosition(red, direction);
            tiltPosition(blue, direction);
        }
    }

    void tiltPosition(Position position, int direction) {
        while (canMove(position, direction)) {
            position.x += DX[direction];
            position.y += DY[direction];
            if (isOut(position)) {
                break;
            }
        }
        if (board[position.x][position.y] == EMPTY) {
            board[position.x][position.y] = WALL;
        }
    }

    boolean canMove(Position position, int direction) {
        return board[position.x + DX[direction]][position.y + DY[direction]] == EMPTY || board[position.x + DX[direction]][position.y + DY[direction]] == HOLE;
    }

    boolean isOut(Position position) {
        return board[position.x][position.y] == HOLE;
    }

    List<Integer> getMoveDirection() {
        List<Integer> moveDirection = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int newRedX = red.x + DX[i];
            int newRedY = red.y + DY[i];
            int newBlueX = blue.x + DX[i];
            int newBlueY = blue.y + DY[i];
            if (board[newRedX][newRedY] == EMPTY || board[newRedX][newRedY] == HOLE ||
                    board[newBlueX][newBlueY] == EMPTY || board[newBlueX][newBlueY] == HOLE) {
                moveDirection.add(i);
            }
        }
        return moveDirection;
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