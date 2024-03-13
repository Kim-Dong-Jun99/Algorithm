
import java.io.*;
import java.util.*;

/*
정확히 장애물 3개 설치해서 모든 학생이 선생 피할 수 있는지,
선생의 수는 5 이하, 전체 학생의 수는 30이하,
그냥 빈칸인 점들을 다 채우는 경우를 구하고, T에서 bfs 돌려서 S 방문가능한지 확인하면 될듯?
 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {1, 0, -1, 0};
    public static final char S = 'S';
    public static final char T = 'T';
    public static final char X = 'X';
    public static final char O = 'O';
    int N;
    char[][] board;
    List<Position> teachers;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }
    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String[] inputStr = BR.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = inputStr[j].charAt(0);
            }
        }
        teachers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == T) {
                    teachers.add(new Position(i, j));
                }
            }
        }
    }

    void solve() throws IOException {
        dfs(0, 0);
        System.out.println("NO");

    }

    void dfs(int blocked, int index) {
        if (blocked == 3) {
            boolean meetStudent = false;
            for (Position t : teachers) {
                for (int d = 0; d < 4; d++) {
                    int newX = t.x + DX[d];
                    int newY = t.y + DY[d];
                    while (isInner(newX, newY)) {
                        if (board[newX][newY] == O) {
                            break;
                        }
                        if (board[newX][newY] == S) {
                            meetStudent = true;
                            break;
                        }
                        newX += DX[d];
                        newY += DY[d];
                    }
                    if (meetStudent) {
                        break;
                    }
                }
                if (meetStudent) {
                    break;
                }
            }

            if (!meetStudent) {
                System.out.println("YES");
                System.exit(0);

            }
            return;

        }
        for (int i = index; i < N * N; i++) {
            int x = i / N;
            int y = i % N;
            if (board[x][y] == X) {
                board[x][y] = O;
                dfs(blocked + 1, i + 1);
                board[x][y] = X;
            }
        }


    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }


    static class Position {
        int x;
        int y;
        Position (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}