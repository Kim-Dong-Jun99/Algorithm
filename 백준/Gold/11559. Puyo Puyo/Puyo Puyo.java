import java.io.*;
import java.util.*;

/*
	여러 가지 색깔의 뿌요가 존재, 중력의 영향을 받아 바닥이나 다른 뿌요까지 떨어짐,
    같은색 뿌요가 4개 이상 상하좌우로 연결되면, 연결된 같은색 뿌요가 사라짐, 1연쇄가 시작된다
    뿌요가 사라지고, 그래도 뿌요가 있으면, 중력 영향을 받아 차례대로 떨어짐,
    또 연쇄 반응 일어날 수도 있음,
    터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터지고, 여러 그룹이 터지더라도 한번의 연쇄만 추가
    필드를 보고 일어나는 연쇄 카운팅
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};

    public static final Character EMPTY = '.';
    public static final Character RED = 'R';
    public static final Character BLUE = 'B';
    public static final Character YELLOW = 'Y';
    public static final Character PURPLE = 'P';

    int[] inputArray;
    int n, m;
    char[][] board;
    boolean[][] visited;
    List<List<Position>> toRemove;
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
        n = 12;
        m = 6;
        board = new char[n][m];
        visited = new boolean[n][m];
        answer = 0;
        for (int i = 0; i < n; i++) {
            String tempString = BR.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = tempString.charAt(j);
            }
        }
    }

    void solve() throws IOException {
        while(true) {
            prepare();
            markRemove();
            if (!toRemove.isEmpty()) {
                remove();
                fall();
            } else {
                break;
            }
            answer += 1;
        }
        System.out.println(answer);
    }

    void prepare() {
        toRemove = new ArrayList<>();
        visited = new boolean[n][m];
    }

    void markRemove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != EMPTY && !visited[i][j]) {
                    List<Position> connected = new ArrayList<>();
                    List<Position> currentNodes = Collections.singletonList(new Position(i,j));
                    char color = board[i][j];
                    visited[i][j] = true;

                    while (!currentNodes.isEmpty()) {
                        List<Position> temp = new ArrayList<>();
                        for (Position cur : currentNodes) {
                            connected.add(cur);
                            for (int d = 0; d < 4; d++) {
                                int newX = cur.x + DX[d];
                                int newY = cur.y + DY[d];
                                if (canGo(newX, newY, color)) {
                                    temp.add(new Position(newX, newY));
                                    visited[newX][newY] = true;
                                }
                            }
                        }

                        currentNodes = temp;
                    }

                    if (connected.size() >= 4) {
                        toRemove.add(connected);
                    }
                }
            }
        }
    }

    void remove() {
        for (List<Position> removePositions : toRemove) {
            for (Position p : removePositions) {
                board[p.x][p.y] = EMPTY;
            }
        }
    }

    void fall() {
        for (int j = 0; j < m; j++) {
            for (int i = n-1; i >= 0; i--) {
                if (board[i][j] != EMPTY) {
                    drop(i, j, board[i][j]);
                }
            }
        }
    }

    void drop(int x, int y, char color) {
        while(canDrop(x, y)) {
            board[x][y] = EMPTY;
            x += 1;
            board[x][y] = color;
        }
    }

    boolean canDrop(int x, int y) {
        return isInner(x+1, y) && board[x+1][y] == EMPTY;
    }

    boolean canGo(int x, int y, char color) {
        return isInner(x, y) && !visited[x][y] && board[x][y] == color;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
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