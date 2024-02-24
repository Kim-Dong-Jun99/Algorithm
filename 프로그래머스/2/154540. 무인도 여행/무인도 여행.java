import java.util.*;

class Solution {
    static final Character SEA = 'X';
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    char[][] board;
    boolean[][] visited;
    int[] answer;
    int n, m;
    public int[] solution(String[] maps) {
        init(maps);
        solve();
        return answer;
    }
    
    void init(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        board = new char[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = maps[i].charAt(j);
            }
        }
    }
    
    void solve() {
        List<Integer> islands = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != SEA && !visited[i][j]) {
                    islands.add(getDuration(i, j));
                }
            }
        }
        
        
        if (islands.isEmpty()) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[islands.size()];
            for (int i = 0; i < islands.size(); i++) {
                answer[i] = islands.get(i);
            }
            Arrays.sort(answer);
        }
    }
    
    int getDuration(int x, int y) {
        int result = 0;
        List<Position> positions = Collections.singletonList(new Position(x, y));
        visited[x][y] = true;
        while (!positions.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position position : positions) {
                result += Character.getNumericValue(board[position.x][position.y]);
                for (int d = 0; d < 4; d++) {
                    int newX = position.x + DX[d];
                    int newY = position.y + DY[d];
                    if (isInner(newX, newY) && !visited[newX][newY] && board[newX][newY] != SEA) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            
            
            positions = temp;
        }
        
        return result;
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