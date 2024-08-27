import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final char WALL = 'x';
    static final char DIRTY = '*';
    
    
    int[] inputArray;
    int w, h;
    char[][] board;
    int[][] index;
    int id;
    boolean[][][] visited;
    int sx, sy;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
        main.test();
	}
    
    void test() throws IOException {
        while(true) {
            inputArray = getInputArray();
            w = inputArray[0];
            h = inputArray[1];
            if (h == 0 && w == 0) {
                return;
            }
            init();
            solve();
        }
    }
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        board = new char[h][w];
        index = new int[h][w];
        id = 0;
        for (int i = 0; i < h; i++) {
            String str = BR.readLine();
            for (int j = 0; j < w; j++) {
                board[i][j] = str.charAt(j);
                index[i][j] = -1;
                if (board[i][j] == DIRTY) {
                    index[i][j] = id;
                    id += 1;
                }
                if (board[i][j] == 'o') {
                    sx = i;
                    sy = j;
                }
            }
        }
        visited = new boolean[h][w][1 << id];
	}
	
	void solve() {
		visited[sx][sy][0] = true;
        int moved = 0;
        List<Node> cur = new ArrayList<>();
        cur.add(new Node(sx, sy, 0));
        while (!cur.isEmpty()) {
            List<Node> temp = new ArrayList<>();
            for (Node n : cur) {
                if (n.visited == (1 << id) - 1) {
                    System.out.println(moved);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = n.x + DX[d];
                    int ny = n.y + DY[d];
                    if (isInner(nx, ny) && board[nx][ny] != WALL && !visited[nx][ny][n.visited]) {
                        visited[nx][ny][n.visited] = true;
                        if (index[nx][ny] != -1) {
                            temp.add(new Node(nx, ny, n.visited | (1 << index[nx][ny])));
                        } else {
                            temp.add(new Node(nx, ny, n.visited));
                        }
                        
                    }
                }
            }
            moved += 1;
            cur = temp;
        }
        System.out.println(-1);
	}
    
    boolean isInner(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }
    
    static class Node {
        int x;
        int y;
        int visited;
        
        Node(int x, int y, int visited) {
            this.x = x;
            this.y = y;
            this.visited = visited;
        }
    }
	
}