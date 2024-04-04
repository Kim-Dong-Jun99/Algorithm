import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final char WALL = '#';
    static final char S = '@';
    static final char F = '*';
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    
    int[] inputArray;
    int T;
    int h, w;
    char[][] board;
    boolean[][] visited;
    boolean[][] visitedFire;
    List<Position> currentS;
    List<Position> currentF;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
        main.testCase();
		main.printResult();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
    void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            init();
            solve();
        }
    }
    
	void init() throws IOException {
        inputArray = getInputArray();
        w = inputArray[0];
        h = inputArray[1];
        board = new char[h][w];
        visited = new boolean[h][w];
        visitedFire = new boolean[h][w];
        currentS = new ArrayList<>();
        currentF = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            String inputStr = BR.readLine();
            for (int j = 0; j < w; j++) {
                board[i][j] = inputStr.charAt(j);
                if (board[i][j] == S) {
                    currentS.add(new Position(i, j));
                    visited[i][j] = true;
                }
                if (board[i][j] == F) {
                    currentF.add(new Position(i, j));
                    visitedFire[i][j] = true;
                }
            }
        }
        
	}
	
	void solve() throws IOException {
	    if (!bfs()) {
            BW.write("IMPOSSIBLE\n");
        }	
	}
    
    boolean bfs() throws IOException {
        int answer = 0;
        while (!currentS.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            answer += 1;
            bfsFire();
            for (Position s : currentS) {
                for (int d = 0; d < 4; d++) {
                    int newX = s.x + DX[d];
                    int newY = s.y + DY[d];
                    if (!isInner(newX, newY)) {
                        BW.write(Integer.toString(answer)+"\n");
                        return true;
                    }
                    if (board[newX][newY] != WALL && !visited[newX][newY] && !visitedFire[newX][newY]) {
                        temp.add(new Position(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            currentS = temp;
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
        return 0 <= x && x < h && 0 <= y && y < w;
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