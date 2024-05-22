import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final char COIN = 'o';
    static final char EMPTY = '.';
    static final char WALL = '#';
    
    int[] inputArray;
    int N, M;
    char[][] board;
    List<Coin> coins;
    boolean[][][][] visited;
    int answer;
    
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
        N = inputArray[0];
        M = inputArray[1];
        board = new char[N][M];
        coins = new ArrayList<>();
        answer = 0;
        visited = new boolean[N][M][N][M];
        
        int coinIndex = 0;
        int[] coinLoc = new int[4];
        
        for (int i = 0; i < N; i++) {
            String temp = BR.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = temp.charAt(j);
                if (board[i][j] == COIN) {
                    coinLoc[coinIndex] = i;
                    coinIndex += 1;
                    coinLoc[coinIndex] = j;
                    coinIndex += 1;
                }
            }
        }
        coins.add(new Coin(coinLoc[0], coinLoc[1], coinLoc[2], coinLoc[3]));
        visited[coinLoc[0]][coinLoc[1]][coinLoc[2]][coinLoc[3]] = true;
        
	}
	
	void solve() throws IOException {
		while (!coins.isEmpty() && answer < 10) {
            List<Coin> temp = new ArrayList<>();
            answer += 1;
            for (Coin c : coins) {
                for (int d = 0; d < 4; d++) {
                    int newAX = c.ax + DX[d];
                    int newAY = c.ay + DY[d];
                    int newBX = c.bx + DX[d];
                    int newBY = c.by + DY[d];
                    if (!isInner(newAX, newAY) && isInner(newBX, newBY)) {
                        BW.write(Integer.toString(answer));
                        return;
                    }
                    if (isInner(newAX, newAY) && !isInner(newBX, newBY)) {
                        BW.write(Integer.toString(answer));
                        return;
                    }
                    if (!isInner(newAX, newAY) && !isInner(newBX, newBY)) {
                        continue;
                    }
                    if (board[newAX][newAY] == WALL) {
                        newAX = c.ax;
                        newAY = c.ay;
                    }
                    if (board[newBX][newBY] == WALL) {
                        newBX = c.bx;
                        newBY = c.by;
                    }
                    if (!visited[newAX][newAY][newBX][newBY]) {
                        visited[newAX][newAY][newBX][newBY] = true;
                        temp.add(new Coin(newAX, newAY, newBX, newBY));
                    }
                }
            }
            coins = temp;
        }
        BW.write("-1");
	}
    
    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y &&y < M;
    }
	
	void printResult() throws IOException {	
		BW.flush();
		BW.close();
		BR.close();
	}
    
    static class Coin {
        int ax;
        int ay;
        int bx;
        int by;
        
        Coin(int ax, int ay, int bx, int by) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
        }
    }
}