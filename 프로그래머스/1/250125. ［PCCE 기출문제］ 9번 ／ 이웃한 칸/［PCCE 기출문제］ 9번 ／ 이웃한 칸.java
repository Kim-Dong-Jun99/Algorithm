import java.util.*;

class Solution {
    int[] DX = {0, 1, 0, -1};
    int[] DY = {1, 0, -1, 0};
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        for (int d = 0; d < 4; d++) {
            int nx = h + DX[d];
            int ny = w + DY[d];
            
            if (isInner(nx, ny, board) && board[nx][ny].equals(board[h][w])) {
                answer++;
            }
        }
        return answer;
    }
    
    boolean isInner(int x, int y, String[][] board) {
        return 0 <= x && x < board.length && 0 <= y && y < board.length;
    } 
}