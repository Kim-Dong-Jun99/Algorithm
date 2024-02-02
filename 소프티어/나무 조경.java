import java.io.*;
import java.util.*;

/*
정원을 가꾸려고 함,n * n 격자,

상하좌우로 맞닿아 있음 인접한 경우

최대 4번 인접해있는 두 나무를 묶을 것임,

묶은 나무끼리는 겹쳐서는 안되고, 묶은 쌍의 아름다움의 합을 최대,

최대 4개의 쌍을 겹치지 않게 잘 골라 얻을 수 있는 아름다움의 최대 구하기

*/

public class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final int[] DX = {0, 1};
    public static final int[] DY = {1, 0};
    
    int n;
    int[][] garden;
    boolean[][] visited;
    int result;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        garden = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            garden[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        BR.close();
    }

    void solve() throws IOException {
        result = 0;

        dfs(0, 0, 0);
        
        BW.write(Integer.toString(result));
        BW.flush();
        BW.close();
        
    }

    void dfs(int index, int count, int sum) {
        result = Math.max(result, sum);
        if (count == 4) {
            
            return;
        }

        for (int i = index; i < n*n; i++) {
            int x = i / n;
            int y = i % n;
            if (!visited[x][y]) {
                for (int d = 0; d < 2; d++) {
                    int newX = x + DX[d];
                    int newY = y + DY[d];
                    if (isInner(newX, newY) && !visited[newX][newY]) {
                        visited[x][y] = true;
                        visited[newX][newY] = true;

                        dfs(i+1, count + 1, sum + garden[x][y] + garden[newX][newY]);

                        
                        visited[x][y] = false;
                        visited[newX][newY] = false;
                    }
                }
            }
        }
        
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0<= y && y < n;
    }

    
}
