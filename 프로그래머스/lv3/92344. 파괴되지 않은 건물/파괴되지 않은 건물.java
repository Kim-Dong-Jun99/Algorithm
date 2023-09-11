import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    int answer;
    int[][] board;
    int[][] prefixSumBoard;
    int[][] skill;
    public int solution(int[][] board, int[][] skill) {
        init(board, skill);
        solve();
        return answer;
    }

    void init(int[][] board, int[][] skill) {
        this.board = board;
        this.skill = skill;
        prefixSumBoard = new int[board.length + 1][board[0].length + 1];
    }

    void solve() {
        for (int[] s : skill) {
            int r1, c1, r2, c2, degree;
            r1 = s[1];
            c1 = s[2];
            r2 = s[3];
            c2 = s[4];
            degree = s[5];
            if (s[0] == 1) {
                degree *= -1;
            }

            prefixSumBoard[r1][c1] += degree;
            prefixSumBoard[r1][c2 + 1] -= degree;
            prefixSumBoard[r2 + 1][c1] -= degree;
            prefixSumBoard[r2 + 1][c2 + 1] += degree;


        }

        for (int i = 0; i < prefixSumBoard.length; i++) {
            int prefixSum = 0;
            for (int j = 0; j < prefixSumBoard[0].length; j++) {
                prefixSum += prefixSumBoard[i][j];
                prefixSumBoard[i][j] = prefixSum;
            }
        }

        for (int j = 0; j < prefixSumBoard[0].length; j++) {
            int prefixSum = 0;
            for (int i = 0; i < prefixSumBoard.length; i++) {
                prefixSum += prefixSumBoard[i][j];
                prefixSumBoard[i][j] = prefixSum;
            }
        }
        

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + prefixSumBoard[i][j] >= 1) {

                    answer += 1;
                }
            }
        }

    }


}