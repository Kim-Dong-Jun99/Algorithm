import java.util.*;
import java.io.*;

public class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static final int WALL = -1;
    public static final int GOAL = 1;
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};
    
    int[] inputArray;
    int n, m;
    int[][] board;
    GameStatus initStatus;
    boolean[][][][] visited;
    
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
        n = inputArray[0];
        m = inputArray[1];
        board = new int[n][m];
        visited = new boolean[n][m][n][m];

        Position red = null;
        Position blue = null;
        for (int i = 0; i < n; i++) {
            String inputString = BR.readLine();
            for (int j = 0; j < m; j++) {
                char c = inputString.charAt(j);
                if (c == '#') {
                    board[i][j] = WALL;
                }
                if (c == 'O') {
                    board[i][j] = GOAL;
                }
                if (c == 'R') {
                    red = new Position(i, j);
                }
                if (c == 'B') {
                    blue = new Position(i, j);
                }
            }
        }

        visited[red.x][red.y][blue.x][blue.y] = true;
        initStatus = new GameStatus(red, blue);

    }

    void solve() throws IOException {
        int result = 0;
        List<GameStatus> statusList = Collections.singletonList(initStatus);
        boolean isDone = false;
        while (!statusList.isEmpty() && result < 11) {

            List<GameStatus> temp = new ArrayList<>();
            for (GameStatus status : statusList) {
                for (int d = 0; d < 4; d++) {
                    GameStatus newStatus = new GameStatus(new Position(status.red.x, status.red.y), new Position(status.blue.x, status.blue.y));
                    if (tiltRedFirst(newStatus, d)) {
                        tiltRed(newStatus, d);
                        tiltBlue(newStatus, d);
                    } else {
                        tiltBlue(newStatus, d);
                        tiltRed(newStatus, d);
                    }
                    if (!visited[newStatus.red.x][newStatus.red.y][newStatus.blue.x][newStatus.blue.y]) {
                        visited[newStatus.red.x][newStatus.red.y][newStatus.blue.x][newStatus.blue.y] = true;

                        if (board[newStatus.red.x][newStatus.red.y] == GOAL) {
                            if (board[newStatus.blue.x][newStatus.blue.y] != GOAL) {
                                isDone = true;
                            }
                        } else {
                            temp.add(newStatus);
                        }
                    }
                }
            }
            result += 1;
            if (isDone) {
                break;
            }
            statusList = temp;
        }

        if (result <= 10 && isDone) {
            BW.write("1");
        } else {
            BW.write("0");
        }
        BW.flush();
        BW.close();
        BR.close();
    }

    boolean tiltRedFirst(GameStatus gameStatus, int d) {
        if (d == 0) {
            return gameStatus.red.y > gameStatus.blue.y;
        } else if (d == 1) {
            return gameStatus.red.x > gameStatus.blue.x;
        } else if (d == 2) {
            return gameStatus.red.y < gameStatus.blue.y;
        } else {
            return gameStatus.red.x < gameStatus.blue.x;
        }

    }


    void tiltRed(GameStatus status, int d) {
        Position red = status.red;
        Position blue = status.blue;

        while (true) {
            if (board[red.x][red.y] == GOAL) {
                break;
            }
            int newX = red.x + DX[d];
            int newY = red.y + DY[d];

            if (board[newX][newY] == WALL || (newX == blue.x && newY == blue.y && !(board[blue.x][blue.y] == GOAL))) {
                break;
            }
            red.x = newX;
            red.y = newY;
        }
    }

    void tiltBlue(GameStatus status, int d) {
        Position red = status.red;
        Position blue = status.blue;

        while (true) {
            if (board[blue.x][blue.y] == GOAL) {
                break;
            }
            int newX = blue.x + DX[d];
            int newY = blue.y + DY[d];

            if (board[newX][newY] == WALL || (newX == red.x && newY == red.y && !(board[red.x][red.y] == GOAL))) {
                break;
            }
            blue.x = newX;
            blue.y = newY;
        }

    }
    public class Position {

        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class GameStatus {

        Position red;
        Position blue;

        public GameStatus(Position red, Position blue) {
            this.red = red;
            this.blue = blue;
        }




    }
}