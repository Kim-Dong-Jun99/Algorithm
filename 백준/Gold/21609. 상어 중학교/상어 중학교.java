import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final int BLACK = -1;
    static final int RAINBOW = 0;
    static final int EMPTY = -2;

    int[] inputArray;
    int N, M;
    int[][] board;
    int score;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        M = inputArray[1];
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = getInputArray();
        }
        score = 0;
    }

    void solve() throws IOException {
        while (true) {
            Group group = getGroup();
            if (group == null) {
                break;
            }
            updateScore(group);
            gravity();
            rotate();
            gravity();
        }
        BW.write(Integer.toString(score));
    }

    Group getGroup() {
        PriorityQueue<Group> heap = new PriorityQueue<>(Group::sort);
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0 && !visited[i][j]) {
                    Position head = new Position(i, j);
                    Group group = new Group(head);
                    int color = board[i][j];
                    visited[i][j] = true;
                    Queue<Position> queue = new LinkedList<>();
                    queue.add(head);
                    while (!queue.isEmpty()) {
                        Position p = queue.remove();
                        group.addPosition(p);
                        if (board[p.x][p.y] == RAINBOW) {
                            group.addRainbowCount();
                        }
                        for (int d = 0; d < 4; d++) {
                            int newX = p.x + DX[d];
                            int newY = p.y + DY[d];
                            if (isInner(newX, newY) && (board[newX][newY] == RAINBOW || board[newX][newY] == color) && !visited[newX][newY]) {
                                queue.add(new Position(newX, newY));
                                visited[newX][newY] = true;
                            }
                        }
                    }
                    if (group.positions.size() >= 2) {
                        heap.add(group);
                    }
                    for (Position p : group.positions) {
                        if (board[p.x][p.y] == RAINBOW) {
                            visited[p.x][p.y] = false;
                        }
                    }
                }
            }
        }
        return heap.poll();
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    void updateScore(Group group) {
        score += group.positions.size() * group.positions.size();
        for (Position p : group.positions) {
            board[p.x][p.y] = EMPTY;
        }
    }

    boolean canSwap(int x, int y) {
        return isInner(x + 1, y) && board[x + 1][y] == EMPTY;
    }

    void gravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i > -1; i--) {
                if (board[i][j] >= 0) {
                    int current = i;
                    while (canSwap(current, j)) {
                        board[current + 1][j] = board[current][j];
                        board[current][j] = EMPTY;
                        current += 1;
                    }
                }
            }
        }

    }

    void rotate() {
        int[][] newBoard = new int[N][N];
        for (int j = N - 1; j > -1; j--) {
            for (int i = 0; i < N; i++) {
                newBoard[N - 1 - j][i] = board[i][j];
            }
        }
        board = newBoard;
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Group {
        Position head;
        int rainbowCount;
        List<Position> positions;

        Group(Position head) {
            this.head = head;
            this.positions = new ArrayList<>();
            this.rainbowCount = 0;
        }

        void addPosition(Position p) {
            this.positions.add(p);
        }

        void addRainbowCount() {
            this.rainbowCount += 1;
        }

        int sort(Group compare) {
            if (this.positions.size() != compare.positions.size()) {
                return Integer.compare(compare.positions.size(), this.positions.size());
            }
            if (this.rainbowCount != compare.rainbowCount) {
                return Integer.compare(compare.rainbowCount, this.rainbowCount);
            }
            if (this.head.x != compare.head.x) {
                return Integer.compare(compare.head.x, this.head.x);
            }
            return Integer.compare(compare.head.y, this.head.y);
        }
    }
}