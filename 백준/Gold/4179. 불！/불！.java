import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final char WALL = '#';
    
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    int[] inputArray;
    int R, C;
    List<Position> currentJ;
    List<Position> currentF;
    boolean[][] visited;
    boolean[][] visitedFire;
    char[][] board;
    
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
        board = new char[R][C];
        visited = new boolean[R][C];
        visitedFire = new boolean[R][C];
        currentJ = new ArrayList<>();
        currentF = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String inputStr = BR.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = inputStr.charAt(j);
                if (board[i][j] == 'J') {
                    visited[i][j] = true;
                    currentJ.add(new Position(i, j));
                }
                if (board[i][j] == 'F') {
                    visitedFire[i][j] = true;
                    currentF.add(new Position(i, j));
                }
            }
        }
	}
	
	void solve() throws IOException {
		if (!bfs()) {
            BW.write("IMPOSSIBLE");
        }
	}
    
    boolean bfs() throws IOException {
        int answer = 0;
        while(!currentJ.isEmpty()) {
            bfsFire();
            answer += 1;
            List<Position> temp = new ArrayList<>();
            for (Position j : currentJ) {
                for (int d = 0; d < 4; d++) {
                    int newX = j.x + DX[d];
                    int newY = j.y + DY[d];
                    if (!isInner(newX, newY)) {
                        BW.write(Integer.toString(answer));
                        return true;
                    }
                    if (board[newX][newY] != WALL && !visitedFire[newX][newY] && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        temp.add(new Position(newX, newY));
                    }
                }
            }
            currentJ = temp;
        }
        return false;
    }
    
    void bfsFire() {
        List<Position> temp = new ArrayList<>();
        for (Position f : currentF) {
            for (int d = 0; d < 4; d++) {
                int newX = f.x + DX[d];
                int newY = f.y + DY[d];
                if (isInner(newX, newY) && board[newX][newY] != WALL && !visitedFire[newX][newY]) {
                    temp.add(new Position(newX, newY));
                    visitedFire[newX][newY] = true;
                }
            }
        }
        currentF = temp;
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