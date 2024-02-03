import java.io.*;
import java.util.*;

/*
m 명의 친구, 
n * n 격자, 나무마다 가능한 열매 수확량이 존재

상하좌우 인접한 칸, 3초동안 최대로 얻을 수 있는 열매 수확량의 합


*/

public class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};

    int[] inputArray;
    int n, m;
    int[][] garden;
    Position[] positions;
    int result;
    int[][] visited;
    int[][] possibleDirection;
    int possibleCombination;
    
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
        n = inputArray[0];
        m = inputArray[1];
        garden = new int[n][n];
        positions = new Position[m];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            garden[i] = getInputArray();
        }

        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            positions[i] = new Position(inputArray[0]-1, inputArray[1]-1);
        }

        result = 0;

        possibleDirection = new int[4*4*4][3];
        possibleCombination = 0;
        initPossibleDirection(0, new int[3]);
    }

    void initPossibleDirection(int index, int[] combination) {
        if (index == 3) {
            possibleDirection[possibleCombination][0] = combination[0];
            possibleDirection[possibleCombination][1] = combination[1];
            possibleDirection[possibleCombination][2] = combination[2];
            possibleCombination += 1;
            return;
        }
        for (int i = 0; i < 4; i++) {
            combination[index] = i;
            initPossibleDirection(index + 1, combination);
        }
        
    }

    void solve() throws IOException {
        dfs(0, 0);
        
        BW.write(Integer.toString(result));
        BW.flush();
        BW.close();
        BR.close();
    }

    void dfs(int index, int prefixSum) {
        if (index == m) {
            result = Math.max(result, prefixSum);
            return;
        }
        Position p = positions[index];
        
        for (int i = 0; i < possibleDirection.length; i++) {
            int x = p.x;
            int y = p.y;
            visited[x][y] += 1;
            if (visited[x][y] == 1) {
                prefixSum += garden[x][y];
            }
            for (int j = 0; j < possibleDirection[0].length; j++) {
                x += DX[possibleDirection[i][j]];
                y += DY[possibleDirection[i][j]];

                if(!isInner(x, y)) {
                    break;
                }
                visited[x][y] += 1;
                if (visited[x][y] == 1) {
                    prefixSum += garden[x][y];
                }
            }

            dfs(index + 1, prefixSum);

            x = p.x;
            y = p.y;
            visited[x][y] -= 1;
            if (visited[x][y] == 0) {
                prefixSum -= garden[x][y];
            }
            for (int j = 0; j < possibleDirection[0].length; j++) {
                x += DX[possibleDirection[i][j]];
                y += DY[possibleDirection[i][j]];

                if(!isInner(x, y)) {
                    break;
                }
                visited[x][y] -= 1;
                if (visited[x][y] == 0) {
                    prefixSum -= garden[x][y];
                }
            }

            
        }
    }

    

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    

    public class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
