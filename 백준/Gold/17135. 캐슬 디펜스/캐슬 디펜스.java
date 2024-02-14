import java.io.*;
import java.util.*;

/*
	성을 향해 몰려오는 적을 잡는 턴제 게임, N * M 격자판에서 게임이 진행된다,
    각 칸에 포함된 적의 수는 최대 하나,
    격자판 N번행 바로 아래의 모든 칸에는 성이 있음,

    성을 지키기 위해 궁수 3명을 배치함, 성이 있는 칸에 배치 가능,

    거리 D 이하 중 가장 가까운 적에게 공격, 거리가 가까운 적이 여럿이면, 가장왼쪽 공격,
    같은 적이 여러 궁수에게 공격 당할 수 있음,


*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final int ENEMY = 1;
    public static final int EMPTY = 0;

    int[] inputArray;
    int N, M, D;
    int[][] originalBoard;
    int[][] board;
    Position[] archers;
    Position[] toRemove;
    int answer;

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
        N = inputArray[0];
        M = inputArray[1];
        D = inputArray[2];
        originalBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            originalBoard[i] = getInputArray();
        }
        archers = new Position[3];
        archers[0] = new Position(N, 0);
        archers[1] = new Position(N, 0);
        archers[2] = new Position(N, 0);
        answer = 0;
    }

    void solve() throws IOException {
        dfs(0);
        System.out.println(answer);
    }

    void dfs(int index) {
        if (index == 3) {
            simulate();
            return;
        }

        if (index == 0) {
            for (int i = 0; i < M; i++) {
                archers[index].y = i;
                dfs(index + 1);
            }
        } else {
            for (int i = archers[index-1].y+1; i < M; i++) {
                archers[index].y = i;
                dfs(index + 1);
            }
        }
    }

    void simulate() {
        board = copyBoard();
        int tempAnswer = 0;
        while (markEnemy() != 0) {
            for (Position p : toRemove) {
                if (p != null && board[p.x][p.y] == ENEMY) {
                    tempAnswer += 1;
                    board[p.x][p.y] = EMPTY;
                }
            }
            moveEnemy();
        }

        answer = Math.max(answer, tempAnswer);
    }

    int markEnemy() {
        int count = 0;
        toRemove = new Position[3];
        int[] distances = {D+1, D+1, D+1};
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                if (board[i][j] == ENEMY) {
                    count += 1;
                    for (int a = 0; a < 3; a++) {
                        Position p = archers[a];
                        int d = calculateDistance(i, j, p.x, p.y);
                        if (d < distances[a]) {
                            toRemove[a] = new Position(i, j);
                            distances[a] = d;
                        }
                    }
                }
            }
        }

        return count;
    }

    int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    void moveEnemy() {
        for (int j = 0; j < M; j++) {
            Queue<Integer> q = new LinkedList<>();
            for (int i = N-1; i > -1; i--) {
                if (i == N-1) {
                    continue;
                }
                q.add(board[i][j]);
            }
            for (int i = N-1; i > -1; i--) {
                if (!q.isEmpty()) {
                    board[i][j] = q.remove();
                } else {
                    board[i][j] = EMPTY;
                }
            }
        }
    }

    int[][] copyBoard() {
        int[][] newBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newBoard[i][j] = originalBoard[i][j];
            }
        }
        return newBoard;
    }

    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}