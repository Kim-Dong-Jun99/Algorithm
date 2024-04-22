import java.util.*;
import java.io.*;


public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    int N;
    int[][] board;
    int answer;
    Stack<Integer> visited;
    public static void main(String[] args) throws IOException {

        Main main = new Main();
        
        main.init();
        main.solution();

    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = getInputArray();
        }

    }

    void solution() throws IOException {
        answer = backtrack(0);
        System.out.println(answer);
    }

    int backtrack(int index) {
        if (index == 5) {
            return getMaxNumber();
        }
        int toReturn = 0;

        int[][] pastBoard = copyBoard();

        for (int i = 0; i < 4; i++) {
            board = tiltBoard(pastBoard, i);

            toReturn = Math.max(toReturn, backtrack(index + 1));

            board = pastBoard;
        }
        return toReturn;
    }

    int[][] tiltBoard(int[][] pastBoard, int direction) {
        int[][] newBoard = new int[N][N];
        if (direction == 0) {
            for (int y = 0; y < N; y++) {
                visited = new Stack<>();
                for (int x = 0; x < N; x++) {
                    if (pastBoard[x][y] != 0) {
                        moveBlock(newBoard, pastBoard,  x, y, direction);
                        visited.push(pastBoard[x][y]);
                    }
                }
            }

        } else if (direction == 1) {
            for (int y = 0; y < N; y++) {
                visited = new Stack<>();
                for (int x = N - 1; x > -1; x--) {
                    if (pastBoard[x][y] != 0) {
                        moveBlock(newBoard, pastBoard,  x, y, direction);
                        visited.push(pastBoard[x][y]);
                    }
                }
            }
        } else if (direction == 2) {
            for (int x = 0; x < N; x++) {
                visited = new Stack<>();
                for (int y = 0; y < N; y++) {
                    if (pastBoard[x][y] != 0) {
                        moveBlock(newBoard, pastBoard, x, y, direction);
                        visited.push(pastBoard[x][y]);
                    }
                }
            }
        } else {
            for (int x = 0; x < N; x++) {
                visited = new Stack<>();
                for (int y = N - 1; y > -1; y--) {
                    if (pastBoard[x][y] != 0) {
                        moveBlock(newBoard, pastBoard, x, y, direction);
                        visited.push(pastBoard[x][y]);
                    }
                }
            }
        }

        return newBoard;
    }

    void moveBlock(int[][] newBoard, int[][] pastBoard, int x, int y, int direction) {
        newBoard[x][y] = pastBoard[x][y];
        while (canMove(x, y, direction)) {
            int newX = x + DX[direction];
            int newY = y + DY[direction];
            if (newBoard[newX][newY] == 0) {
                newBoard[newX][newY] = newBoard[x][y];
                newBoard[x][y] = 0;
            } else if (newBoard[newX][newY] == newBoard[x][y]) {
                if (visited.peek() == newBoard[x][y]) {
                    newBoard[newX][newY] += newBoard[x][y];
                    newBoard[x][y] = 0;
                }
                break;
            } else {
                break;
            }


            x += DX[direction];
            y += DY[direction];
        }
    }

    boolean canMove(int x, int y, int direction) {
        int newX = x + DX[direction];
        int newY = y + DY[direction];

        return isInner(newX, newY);
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    int[][] copyBoard() {
        int[][] pastBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, pastBoard[i], 0, N);
        }
        return pastBoard;
    }

    int getMaxNumber() {
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxNum = Math.max(maxNum, board[i][j]);
            }
        }
        return maxNum;
    }

}