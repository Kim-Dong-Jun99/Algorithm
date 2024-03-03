import java.util.*;

class Solution {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final char STRAIGHT = 'S';
    static final char LEFT = 'L';
    static final char RIGHT = 'R';
    int[] answer;
    int n, m;
    char[][] grid;
    boolean[][][] visited;
    public int[] solution(String[] grid) {
        init(grid);
        solve();
        return answer;
    }
    
    void init(String[] grid) {
        this.n = grid.length;
        this.m = grid[0].length();
        this.grid = new char[n][m];
        this.visited = new boolean[n][m][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.grid[i][j] = grid[i].charAt(j);
            }
        }
    }
    
    void solve() {
        List<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[i][j][d]) {
                        answerList.add(bfs(i, j, d));
                    }
                }
            }
        }
        
        Collections.sort(answerList);
        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
    }
    
    int bfs(int x, int y, int d) {
        List<Light> lights = Collections.singletonList(new Light(x, y, d));
        visited[x][y][d] = true;
        int answer = 0;
        while (!lights.isEmpty()) {
            List<Light> temp = new ArrayList<>();
            for (Light light : lights) {
                Light nextLight = getNextLight(light.x, light.y, light.d);
                rotate(nextLight);
                if (!visited[nextLight.x][nextLight.y][nextLight.d]) {
                    temp.add(nextLight);
                    visited[nextLight.x][nextLight.y][nextLight.d] = true;
                }
            }
            lights = temp;
            answer += 1;
        }
        return answer;
    }
    
    Light getNextLight(int x, int y, int d) {
        int newX = x + DX[d];
        int newY = y + DY[d];
        if (0 > newX){
            newX = n-1;
        }
        if (newX == n) {
            newX = 0;
        }
        if (0 > newY) {
            newY = m-1;
        }
        if (newY == m) {
            newY = 0;
        }
        return new Light(newX, newY, d);
        
    }
    
    void rotate(Light light) {
        if (grid[light.x][light.y] == LEFT) {
            light.d = getLeft(light.d);
        } 
        if (grid[light.x][light.y] == RIGHT) {
            light.d = getRight(light.d);
        }
        
    }
    
    int getLeft(int d) {
        return (d + 3) % 4;
    }
    
    int getRight(int d) {
        return (d + 1) % 4;
    }
    
    static class Light {
        int x;
        int y;
        int d;
        Light(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}