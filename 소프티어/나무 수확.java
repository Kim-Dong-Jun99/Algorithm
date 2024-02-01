import java.io.*;
import java.util.*;

/*
n * n 격자,
칸에 열매 수확량이 들어감

수로는 왼쪽 위에서 시작함, 오른쪽 혹은 아래쪽을 선택해서 오른쪽아래까지 가야함

수로 설치가 완료되면, 수로 중 칸 하나를 골라 스프링쿨러를 설치,

스프링 쿨러 칸은 열매 수확량 2배, 

최대 수확량 구하기

n <= 1,000
*/

public class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static final int[] DX = {1, 0};
    public static final int[] DY = {0, 1};

    int n;
    int[][] garden;
    int[][][] dp;

    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.init();
        main.solve();
    }

    void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        garden = new int[n][n];
        dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            garden[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        
    }

    void solve() throws IOException {
        dfs(0,0,0);
        dfs(0,0,1);
        System.out.println(Math.max(dp[0][0][0], dp[0][0][1]));
        
    }

    int dfs(int x, int y, int built) {
        if(x == n-1 && y == n-1) {
            if(built == 0) {
                dp[x][y][built] = garden[x][y];
            } else {
                dp[x][y][built] = garden[x][y] * 2;
            }
        }

        if (dp[x][y][built] != 0) {
            return dp[x][y][built];
        }

        int tempResult = 0;

        for (int d = 0; d < 2; d++) {
            int newX = x + DX[d];
            int newY = y + DY[d];

            if (isInner(newX, newY)) {
                if (built == 1) {
                    tempResult = Math.max(tempResult, dfs(newX, newY, 0) + garden[x][y]*2);
                    tempResult = Math.max(tempResult, dfs(newX, newY, 1) + garden[x][y]);
                } else {
                    tempResult = Math.max(tempResult, dfs(newX, newY, 0) + garden[x][y]);
                }
                
                
            }
        }

        dp[x][y][built] = tempResult;
        return dp[x][y][built];
    }

    

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


}
