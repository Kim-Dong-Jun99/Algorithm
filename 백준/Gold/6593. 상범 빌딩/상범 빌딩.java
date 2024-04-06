import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1, 0, 0};
    static final int[] DY = {1, 0, -1, 0, 0, 0};
    static final int[] DZ = {0, 0, 0, 0, 1, -1};
    static final char WALL = '#';
    static final char START = 'S';
    static final char EXIT = 'E';
    int[] inputArray;
    int L, R, C;
    char[][][] board;
    boolean[][][] visited;
    List<Position> currentPosition;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.testCase();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void testCase() throws IOException {
        while (true) {
            inputArray = getInputArray();
            L = inputArray[0];
            R = inputArray[1];
            C = inputArray[2];
            if (L == 0 && R == 0 && C == 0) {
                break;
            }
            init();
            solve();
        }
    }

    void init() throws IOException {
        board = new char[L][R][C];
        visited = new boolean[L][R][C];
        currentPosition = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                String inputStr = BR.readLine();
                for (int k = 0; k < C; k++) {
                    board[i][j][k] = inputStr.charAt(k);
                    if (board[i][j][k] == START) {
                        currentPosition.add(new Position(i, j, k));
                        visited[i][j][k] = true;
                    }
                }
            }
            BR.readLine();
        }
    }

    void solve() throws IOException {
        int answer = 0;
        while (!currentPosition.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position p : currentPosition) {
                if (board[p.z][p.x][p.y] == EXIT) {
                    BW.write("Escaped in "+Integer.toString(answer)+" minute(s).\n");
                    return;
                }
                for (int d = 0; d < 6; d++) {
                    int newX = p.x + DX[d];
                    int newY = p.y + DY[d];
                    int newZ = p.z + DZ[d];
                    if (isInner(newZ, newX, newY) && !visited[newZ][newX][newY] && board[newZ][newX][newY] != WALL) {
                        temp.add(new Position(newZ, newX, newY));
                        visited[newZ][newX][newY] = true;
                    }
                }
            }
            answer += 1;
            currentPosition = temp;
        }
        BW.write("Trapped!\n");
    }

    boolean isInner(int z, int x, int y) {
        return 0 <= z && z < L && 0 <= x && x < R && 0 <= y && y < C;
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Position {
        int z;
        int x;
        int y;

        Position(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}