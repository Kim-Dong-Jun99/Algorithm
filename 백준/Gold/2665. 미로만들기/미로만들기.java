import java.io.*;
import java.util.*;
	
/*
	바꿔야할 최소 검은 방을 구하샘,
*/
	
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};
    
    public static final Character WHITE = '1';
    public static final Character BLACK = '0';
    
    int[] inputArray;
    int n;
    char[][] board;
    int[][] dijkstra;
    
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
        n = Integer.parseInt(BR.readLine());
        board = new char[n][n];
        dijkstra = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            String inputString = BR.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = inputString.charAt(j);
                dijkstra[i][j] = n * n;
            }
        }
	}
	
	void solve() throws IOException {
        PriorityQueue<Node> heap = new PriorityQueue<>(Node::sortWithChanged);
        heap.add(new Node(0,0,0));
        while (!heap.isEmpty()) {
            Node node = heap.remove();
            if (dijkstra[node.x][node.y] <= node.changed) {
                continue;
            }
            
            dijkstra[node.x][node.y] = node.changed;
            for (int d = 0; d < 4; d++) {
                int newX = node.x + DX[d];
                int newY = node.y + DY[d];
                if (isInner(newX, newY)) {
                    if (board[newX][newY] == BLACK) {
                        heap.add(new Node(newX, newY, node.changed+1));
                    } else {
                        heap.add(new Node(newX, newY, node.changed));
                    }
                }
            }
        }
	}
    
    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    void printResult() {
        System.out.println(dijkstra[n-1][n-1]);
    }
    
    static class Node {
        int x;
        int y;
        int changed;
        
        Node(int x, int y, int changed) {
            this.x = x;
            this.y = y;
            this.changed = changed;
        }
        
        int sortWithChanged(Node compare) {
            return Integer.compare(this.changed, compare.changed);
        }
    }
}