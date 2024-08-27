import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final int[] OPPOSITE = {2, 3, 0, 1};
    
    int[] inputArray;
    int R, C;
    char[][] board;
    int blocks;
    int mx, my, zx, zy, ax, ay, ad;
    boolean[][] visited;
    
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
        R = inputArray[0];
        C = inputArray[1];
        board = new char[R][C];
        visited = new boolean[R][C];
        blocks = 0;
        for (int i = 0; i < R; i++) {
            String str = BR.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] != 'M' && board[i][j] != 'Z' && board[i][j] != '.') {
                    blocks += 1;
                }
                if (board[i][j] == 'M') {
                    mx = i;
                    my = j;
                }
                if (board[i][j] == 'Z') {
                    zx = i;
                    zy = j;
                }
            }
        }
	}
	
    
	void solve() {
        StringBuilder sb = new StringBuilder();
        int nx, ny;
        int traveled = 0;
		int d = getDirection(mx, my);
        nx = mx + DX[d];
        ny = my + DY[d];
        while (board[nx][ny] != '.') {
            if (!visited[nx][ny]) {
                traveled += 1;
                visited[nx][ny] = true;
            } 
            if (board[nx][ny] == '-' || board[nx][ny] == '|' || board[nx][ny] == '+') {
                nx += DX[d];
                ny += DY[d];
            } else {
                if (board[nx][ny] == '1') {
                    if (d == 2) {
                        d = 1;
                    } else {
                        d = 0;
                    }
                } else if (board[nx][ny] == '2') {
                    if (d == 2) {
                        d = 3;
                    } else {
                        d = 0;
                    }
                } else if (board[nx][ny] == '3') {
                    if (d == 0) {
                        d = 3;
                    } else {
                        d = 2;
                    }
                } else {
                    if (d == 0) {
                        d = 1;
                    } else {
                        d = 2;
                    }
                }
                nx += DX[d];
                ny += DY[d];
            }
        }
        ax = nx;
        ay = ny;
        ad = d;
        sb.append(Integer.toString(ax+1)).append(" ").append(Integer.toString(ay+1)).append(" ");
        
        d = getDirection(zx, zy);
        nx = zx + DX[d];
        ny = zy + DY[d];
        while (board[nx][ny] != '.') {
            if (!visited[nx][ny]) {
                traveled += 1;
                visited[nx][ny] = true;
            } 
            if (board[nx][ny] == '-' || board[nx][ny] == '|' || board[nx][ny] == '+') {
                nx += DX[d];
                ny += DY[d];
            } else {
                if (board[nx][ny] == '1') {
                    if (d == 2) {
                        d = 1;
                    } else {
                        d = 0;
                    }
                } else if (board[nx][ny] == '2') {
                    if (d == 2) {
                        d = 3;
                    } else {
                        d = 0;
                    }
                } else if (board[nx][ny] == '3') {
                    if (d == 0) {
                        d = 3;
                    } else {
                        d = 2;
                    }
                } else {
                    if (d == 0) {
                        d = 1;
                    } else {
                        d = 2;
                    }
                }
                nx += DX[d];
                ny += DY[d];
            }
        }
        // System.out.println(blocks+" "+traveled);
        if (OPPOSITE[d] == ad) {
            if (blocks <= traveled) {
                if (d % 2 == 0) {
                    sb.append("-");
                } else {
                    sb.append("|");
                }
            } else {
                sb.append("+");
            }
        } else {
            if (blocks > traveled) {
                sb.append("+");
            } else {
                d = OPPOSITE[d];
                if (ad == 0) {
                    if (d == 1) {
                        sb.append("4");
                    } else {
                        sb.append("3");
                    }
                } else if (ad == 1) {
                    if (d == 0) {
                        sb.append("2");
                    } else {
                        sb.append("3");
                    }
                } else if (ad == 2) {
                    if (d == 1) {
                        sb.append("1");
                    } else {
                        sb.append("2");
                    }
                } else {
                    if (d == 0) {
                        sb.append("1");
                    } else {
                        sb.append("4");
                    }
                }
            }
            
        }
        
        System.out.println(sb);
	}
    
    boolean isInner(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
    
    int getDirection(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + DX[d];
            int ny = y + DY[d];
            if (isInner(nx, ny) && board[nx][ny] != '.') {
                if (board[nx][ny] == '+') {
                    return d;
                }
                if (board[nx][ny] == '|' && d % 2 == 1) {
                    return d;
                }
                if (board[nx][ny] == '-' && d % 2 == 0) {
                    return d;
                }
                if (board[nx][ny] == '1' && (d == 2 || d == 3)) {
                    return d;
                }
                if (board[nx][ny] == '2' && (d == 2 || d == 1)) {
                    return d;
                }
                if (board[nx][ny] == '3' && (d == 0 || d == 1)) {
                    return d;
                }
                if (board[nx][ny] == '4' && (d == 0 || d == 3)) {
                    return d;
                }
            }
        }
        return -1;
    }
	
}