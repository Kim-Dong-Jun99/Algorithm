import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final char EMPTY = '.';
    static final char WALL = '*';
    
    int[] inputArray;
    int W, H;
    char[][] board;
    int[][][] distance;
    int sx, sy, gx, gy;
    
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
        W = inputArray[0];
        H = inputArray[1];
        board = new char[H][W];
        distance = new int[H][W][4];
        sx = -1;
        sy = -1;
        gx = -1;
        gy = -1;
        for (int i = 0; i < H; i++) {
            String str = BR.readLine();
            for (int j = 0; j < W; j++) {
                board[i][j] = str.charAt(j);
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
                if (board[i][j] == 'C') {
                    if (sx == -1) {
                        sx = i;
                        sy = j;
                    } else {
                        gx = i;
                        gy = j;
                    }
                }
            }
        }
	}
    
    int getCW(int d) {
        return (d + 1) % 4;
    }
    
    int getCCW(int d) {
        return (d + 3) % 4;
    }
	
	void solve() {
		dijkstra();
        System.out.println(Arrays.stream(distance[gx][gy]).min().getAsInt());
	}
    
    void dijkstra() {
        PriorityQueue<Node> heap = new PriorityQueue<>(Node::compare);
        Arrays.fill(distance[sx][sy], 0);
        for (int d = 0; d < 4; d++) {
            int nx = sx + DX[d];
            int ny = sy + DY[d];
            if (isInner(nx, ny) && board[nx][ny] != WALL) {
                heap.add(new Node(d, nx, ny, 0));
            }
        }
        
        while (!heap.isEmpty()) {
            Node n = heap.remove();
            if (n.distance > distance[n.x][n.y][n.d]) {
                continue;
            }
            distance[n.x][n.y][n.d] = n.distance;
            int nx = n.x + DX[n.d];
            int ny = n.y + DY[n.d];
            if (isInner(nx, ny) && board[nx][ny] != WALL && distance[nx][ny][n.d] > n.distance) {
                heap.add(new Node(n.d, nx, ny, n.distance));
            }
            int cw = getCW(n.d);
            int ccw = getCCW(n.d);
            nx = n.x + DX[cw];
            ny = n.y + DY[cw];
            if (isInner(nx, ny) && board[nx][ny] != WALL && distance[nx][ny][cw] > n.distance + 1) {
                heap.add(new Node(cw, nx, ny, n.distance + 1));
            }
            nx = n.x + DX[ccw];
            ny = n.y + DY[ccw];
            if (isInner(nx, ny) && board[nx][ny] != WALL && distance[nx][ny][ccw] > n.distance + 1) {
                heap.add(new Node(ccw, nx, ny, n.distance + 1));
            }
        }
    }
    
    boolean isInner(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }
    
    static class Node {
        int d;
        int x;
        int y;
        int distance;
        
        Node(int d, int x, int y, int distance) {
            this.d = d;
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
        
        int compare(Node n) {
            return Integer.compare(this.distance, n.distance);
        }
    }
	
}