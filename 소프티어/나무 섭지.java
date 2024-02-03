import java.io.*;
import java.util.*;

/*
n*m 격자
남우, 출구, 유령, 빈칸, 벽
남우 -> 상하좌우로 이동하며 출구로 가야함, 유령과 마주치면 안된다. 유령은 벽도 통과 가능
*/

public class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final String SUCCESS = "Yes";
    public static final String FAIL = "No";
    public static final Character GHOST = 'G';
    public static final Character EXIT = 'D';
    public static final Character NAMWOO = 'N';
    public static final Character WALL = '#';
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};

    int[] inputArray;
    int n, m;
    int[][] board;
    boolean[][] visited;
    boolean[][] ghostVisited;
    List<Position> currentNamwoos;
    List<Position> currentGhosts;
    int exitX;
    int exitY;
    boolean out;
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

        currentNamwoos = new ArrayList<>();
        currentGhosts = new ArrayList<>();

        board = new int[n][m];
        visited = new boolean[n][m];
        ghostVisited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String inputString = BR.readLine();
            for (int j = 0; j < m; j++) {
                if (inputString.charAt(j) == GHOST) {
                    board[i][j] += 1;
                    ghostVisited[i][j] = true;
                    currentGhosts.add(new Position(i, j));
                }
                if (inputString.charAt(j) == EXIT) {
                    exitX = i;
                    exitY = j;
                } 
                if (inputString.charAt(j) == NAMWOO) {
                    visited[i][j] = true;
                    currentNamwoos.add(new Position(i, j));
                }
                if (inputString.charAt(j) == WALL) {
                    board[i][j] += 1;
                }
            }
        }
        
        out = false;
        
    }

    void solve() throws IOException {

        while (!currentNamwoos.isEmpty()) {
            bfsGhost();
            bfsNamwoo();
            if (isOut()) {
                out = true;
                break;
            }
        }

        

        if (out) {
            BW.write(SUCCESS);
        } else {
            BW.write(FAIL);
        }

        BW.flush();
        BW.close();
        BR.close();
        
    }

    void bfsGhost() {
        List<Position> temp = new ArrayList<>();
        for (Position currentGhost : currentGhosts) {
            for (int d = 0; d < 4; d++) {
                int newX = currentGhost.x + DX[d];
                int newY = currentGhost.y + DY[d];
                if (isInner(newX, newY) && !ghostVisited[newX][newY]) {
                    ghostVisited[newX][newY] = true;
                    board[newX][newY] += 1;
                    temp.add(new Position(newX, newY));
                }
            }
        }
        currentGhosts = temp;
    }

    void bfsNamwoo() {
        List<Position> temp = new ArrayList<>();
        for (Position currentNamwoo : currentNamwoos) {
            for (int d = 0; d < 4; d++) {
                int newX = currentNamwoo.x + DX[d];
                int newY = currentNamwoo.y + DY[d];
                if(isInner(newX, newY) && !visited[newX][newY] && board[newX][newY] == 0) {
                    visited[newX][newY] = true;
                    temp.add(new Position(newX, newY));
                }
            }
        }

        currentNamwoos = temp;
        
    }

    boolean isOut() {
        return visited[exitX][exitY];
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
