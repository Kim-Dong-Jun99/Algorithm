import java.util.*;

/*
시작 위치에서 목표 위치로 최소 몇번만에 도달할 수 있는지??

*/

class Solution {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    
    
    static final Character EMPTY = '.';
    static final Character ROBOT = 'R';
    static final Character WALL = 'D';
    static final Character GOAL = 'G';
    
    int answer;
    int n, m;
    char[][] board;
    boolean[][] visited;
    Position start, goal;
    public int solution(String[] board) {
        init(board);
        solve();
        return answer;
    }
    
    void init(String[] board) {
        n = board.length;
        m = board[0].length();
        
        this.board = new char[n][m];
        this.visited = new boolean[n][m];
        answer = -1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.board[i][j] = board[i].charAt(j);
                if (this.board[i][j] == ROBOT) {
                    start = new Position(i, j);
                }
                if (this.board[i][j] == GOAL) {
                    goal = new Position(i, j);
                }
            }
        }
    }
    
    void solve() {
        List<Position> positions = Collections.singletonList(start);
        int move = 0;
        while (!positions.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position p : positions) {
                for (int d = 0; d < 4; d++) {
                    Position newPosition = moveTillEnd(p, d);
                    if (!visited[newPosition.x][newPosition.y]) {
                        visited[newPosition.x][newPosition.y] = true;
                        temp.add(newPosition);
                    }
                }
            }
            move += 1;
            if (visited[goal.x][goal.y]) {
                answer = move;
                break;
            }
            positions = temp;
        }
    }
    
    Position moveTillEnd(Position p, int d) {
        int newX = p.x;
        int newY = p.y;
        while (canPush(newX, newY, d)) {
            newX += DX[d];
            newY += DY[d];
        }
        return new Position(newX, newY);
    }
    
    boolean canPush(int x, int y, int d) {
        return isInner(x+DX[d], y+DY[d]) && board[x+DX[d]][y+DY[d]] != WALL;
    }
    
    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    
    static class Position {
        int x;
        int y;
        
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}