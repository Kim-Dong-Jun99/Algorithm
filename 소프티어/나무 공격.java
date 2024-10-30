import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int m;
    int[] inputArray;
    int[][] board;
    int l, r;
    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
    void solve() throws IOException {
        inputArray = getInputArray();
        n = inputArray[0];
        m = inputArray[1];

        board = new int[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            board[i] = getInputArray();
            for (int j = 0; j < m; j++) {
                count += board[i][j];
            }
        }

        for (int t = 0; t < 2; t++) {
            inputArray = getInputArray();
            l = inputArray[0]-1;
            r = inputArray[1]-1;
            for (int i = l; i <= r; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1) {
                        board[i][j] = 0;
                        count -= 1;
                        break;
                    }
                }
            }
        }
        
        System.out.println(count);
    }
}
