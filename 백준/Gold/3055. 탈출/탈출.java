import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final char EMPTY = '.';
    static final char WATER = '*';
    static final char STONE = 'X';
    static final char GOAL = 'D';
    static final char RACOON = 'S';
    
    int[] inputArray;
    int R, C;
    char[][] graph;
    Position goal;
    List<Position> racoons;
    List<Position> waters;
    boolean[][] visited;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
		main.printResult();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        inputArray = getInputArray();
        R = inputArray[0];
        C = inputArray[1];
        graph = new char[R][C];
        racoons = new ArrayList<>();
        waters = new ArrayList<>();
        visited = new boolean[R][C];
        goal = null;
        for (int i = 0; i < R; i++) {
            String tmp = BR.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = tmp.charAt(j);
                if (graph[i][j] == WATER) {
                    waters.add(new Position(i, j));
                }
                if (graph[i][j] == RACOON) {
                    racoons.add(new Position(i, j));
                    visited[i][j] = true;
                }
                if (graph[i][j] == GOAL) {
                    goal = new Position(i, j);
                }
            }
        }
        
	}
	
	void solve() throws IOException {
		int answer = 0;
        while (!racoons.isEmpty()) {
            bfsWater();
            List<Position> temp = new ArrayList<>();
            for (Position p : racoons) {
                for (int d = 0; d < 4; d++) {
                    int newX = p.x + DX[d];
                    int newY = p.y + DY[d];
                    if (isInner(newX, newY) && !visited[newX][newY] && graph[newX][newY] != STONE && graph[newX][newY] != WATER) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            answer += 1;
            if (visited[goal.x][goal.y]) {
                BW.write(Integer.toString(answer));
                return;
            }
            racoons = temp;
        }
        BW.write("KAKTUS");
	}
    
    void bfsWater() {
        List<Position> temp = new ArrayList<>();
        for (Position p : waters) {
            for (int d = 0; d < 4; d++) {
                int newX = p.x + DX[d];
                int newY = p.y + DY[d];
                if (isInner(newX, newY) && graph[newX][newY] != STONE && graph[newX][newY] != WATER && !(newX == goal.x && newY == goal.y)) {
                    graph[newX][newY] = WATER;
                    temp.add(new Position(newX, newY));
                }
            }
        }
        waters = temp;
    }
    
    boolean isInner(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
	
	void printResult() throws IOException {	
		BW.flush();
		BW.close();
		BR.close();
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