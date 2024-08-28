import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    int[] inputArray;
    int N;
    Tile[] tiles;
    int[][] board;
    int[][] index;
    int[][] visited;
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        board = new int[N][N*2];
        index = new int[N][N*2];
        visited = new int[N][N*2];
        tiles = new Tile[N*N - N/2];
        int x = 0;
        int y = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(index[i], -1);
            Arrays.fill(visited[i], -1);
        }
        for (int i = 0; i < N*N - N/2; i++) {
            inputArray = getInputArray();
            Tile t = new Tile(i, new Position(x, y), new Position(x, y+1));
            tiles[i] = t;
            y += 2;
            if (x % 2 == 0) {
                if (y == N * 2) {
                    x += 1;
                    y = 1;
                }
            } else {
                if (y == N * 2 - 1) {
                    x += 1;
                    y = 0;
                }
            }
            board[t.a.x][t.a.y] = inputArray[0];
            board[t.b.x][t.b.y] = inputArray[1];
            index[t.a.x][t.a.y] = i;
            index[t.b.x][t.b.y] = i;
        }
	}
	
	void solve() {
		List<Tile> cur = new ArrayList<>();
        cur.add(tiles[0]);
        visit(tiles[0], 0);
        while(!cur.isEmpty()) {
            List<Tile> temp = new ArrayList<>();
            for (Tile t : cur) {
                int nx, ny;
                Tile nt;
                for (int d = 0; d < 4; d++) {
                    nx = t.a.x + DX[d];
                    ny = t.a.y + DY[d];
                    if (isInner(nx, ny) && board[nx][ny] == board[t.a.x][t.a.y] && visited[nx][ny] == -1) {
                        nt = tiles[index[nx][ny]];
                        visit(nt, t.id);
                        temp.add(nt);
                    }
                    nx = t.b.x + DX[d];
                    ny = t.b.y + DY[d];
                    if (isInner(nx, ny) && board[nx][ny] == board[t.b.x][t.b.y] && visited[nx][ny] == -1) {
                        nt = tiles[index[nx][ny]];
                        visit(nt, t.id);
                        temp.add(nt);
                    }
                }
            }
            cur = temp;
        }        
        printResult();
        // printVisited();
	}
    
    void printResult() {
        for (int i = N * N - N/2 - 1; i >= 0; i--) {
            Tile t = tiles[i];
            if (visited[t.a.x][t.a.y] != -1) {
                int cur = t.id;
                List<Integer> ids = new ArrayList<>();
                while (true) {
                    ids.add(cur+1);
                    if (cur == 0) {
                        break;
                    }
                    cur = visited[t.a.x][t.a.y];
                    t = tiles[visited[t.a.x][t.a.y]];
                    
                }
                System.out.println(ids.size());
                for (int j = ids.size()-1; j >= 0; j--) {
                    System.out.print(ids.get(j)+" ");
                }
                return;
            }
        }
    }

    // void printVisited() {
    //     for (int[] i : visited) {
    //         for (int j : i) {
    //             System.out.print(j+" ");
    //         }
    //         System.out.println();
    //     }
    // }
    
    
    
    boolean isInner(int x, int y) {
        if (x % 2 == 0) {
            return 0 <= x && x < N && 0 <= y && y < N*2;
        } else {
            return 0 <= x && x < N && 1 <= y && y < N*2-1;
        }
    }
    
    void visit(Tile tile, int id) {
        visited[tile.a.x][tile.a.y] = id;
        visited[tile.b.x][tile.b.y] = id;
    }
    
    static class Tile {
        int id;
        Position a;
        Position b;
        
        Tile(int id, Position a, Position b) {
            this.id = id;
            this.a = a;
            this.b = b;
        }
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