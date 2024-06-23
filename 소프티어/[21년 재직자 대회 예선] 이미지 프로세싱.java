import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    int[] inputArray;
    int H, W, Q;
    int[][] board;
    Command[] cmds;
    boolean[][] visited;
    
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
        H = inputArray[0];
        W = inputArray[1];
        board = new int[H][W];
        for (int i = 0; i < H; i++) {
            board[i] = getInputArray();
        }
        Q = Integer.parseInt(BR.readLine());
        cmds = new Command[Q];
        for (int i = 0; i < Q; i++) {
            inputArray = getInputArray();
            cmds[i] = new Command(inputArray[0]-1, inputArray[1]-1, inputArray[2]);
        }
    }

    void solve() {
        for (Command cmd : cmds) {
            int c = cmd.c;
            if (c == board[cmd.x][cmd.y]) {
                continue;
            }
            int id = board[cmd.x][cmd.y];
            Queue<Position> q = new LinkedList<>();
            visited = new boolean[H][W];
            visited[cmd.x][cmd.y] = true;
            q.add(new Position(cmd.x, cmd.y));
            while (!q.isEmpty()) {
                Position p = q.remove();
                board[p.x][p.y] = c;
                for (int d = 0; d < 4; d++) {
                    int newX = p.x + DX[d];
                    int newY = p.y + DY[d];
                    if (isInner(newX, newY) && !visited[newX][newY] && board[newX][newY] == id) {
                        visited[newX][newY] = true;
                        q.add(new Position(newX, newY));
                    }
                }
            }
            
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < H && 0<= y && y < W;
    }
    
    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Command {
        int x;
        int y;
        int c;

        Command(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
