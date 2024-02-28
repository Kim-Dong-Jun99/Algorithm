import java.io.*;
import java.util.*;

/*
	4 * 4 공간
    물고기는 번호와 방향을 가지고 있다, 번호는 1보다 크거나 같고, 16보다 작거나 같고, 같은 번호 X
    방향은 상하좌우 대각선 중 하나
    청소년 상어가 이 공간에 들어가 물고기를 먹으려함,
    0,0에 들어가서 0,0 물고기를 먹고, 0,0 물고기 방향으로 이동

    이후 물고기 이동, 번호가 작은 물고기부터 이동
    물고기는 한칸 이동 가능하고,
    이동할 수 있는 칸은 빈칸, 다른 물고기가 있는 칸
    이동할 수 없는 칸은 상어가 있는 칸, 공간의 바깥

    각 물고기는 방향이 이동할 수 있는 칸을 향할때까지 45도 반시계로 회전
    만약 이동할 수 있는 칸이 없으면 이동 X
    그외의 경우, 그 칸으로 이동, 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동

    물고기의 이동이 모두 끝나면 상어가 이동
    방향에 있는 칸으로 이동 가능한데, 한번에 여러개의 칸을 이동 가능
    상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 물고기의 방향을 가지게 된다.
    이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. 물고기가 없는 칸으로는 이동 불가

    상어가 먹을 수 있는 물고기 번호의 합의 최댓값 구하기
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] DY = {0, -1, -1, -1, 0, 1, 1, 1};

    int[] inputArray;
    int n, m;
    Fish[][] board;
    int answer;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        n = 4;
        m = 4;
        board = new Fish[n][m];
        for (int i = 0; i < 4; i++) {
            inputArray = getInputArray();
            for (int j = 0; j < 8; j += 2) {
                board[i][j/2] = new Fish(inputArray[j], inputArray[j+1]-1);
            }
        }
    }

    void solve() throws IOException {
        dfs(0, 0, 0, board);
        System.out.println(answer);
    }

    void dfs(int x, int y, int caught, Fish[][] fishBoard) {
        Fish toCatch = fishBoard[x][y];
        caught += toCatch.index;
        answer = Math.max(answer, caught);
        int direction = toCatch.direction;
        fishBoard[x][y] = null;
        Fish[][] moved = moveFish(fishBoard, x, y);
        int newX = x + DX[direction];
        int newY = y + DY[direction];
        while (isInner(newX, newY)) {
            if (moved[newX][newY] != null) {
                dfs(newX, newY, caught, moved);
            }
            newX += DX[direction];
            newY += DY[direction];
        }

        fishBoard[x][y] = toCatch;
    }

    Fish[][] moveFish(Fish[][] fishBoard, int x,int y) {
        Fish[][] newFishBoard = new Fish[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (fishBoard[i][j] != null) {
                    Fish fish = fishBoard[i][j];
                    newFishBoard[i][j] = new Fish(fish.index, fish.direction);
                }
            }
        }

        for (int index = 1; index <= 16; index++) {
            boolean moved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!moved && newFishBoard[i][j] != null && newFishBoard[i][j].index == index) {
                        Fish toMove = newFishBoard[i][j];
                        int newX = i + DX[toMove.direction];
                        int newY = j + DY[toMove.direction];
                        for (int d = 0; d < 8; d++) {
                            if (isInner(newX, newY) && !(newX == x && newY == y)) {
                                Fish fixed = newFishBoard[newX][newY];
                                newFishBoard[newX][newY] = toMove;
                                newFishBoard[i][j] = fixed;
                                moved = true;
                                break;
                            }
                            toMove.direction = getCCW(toMove.direction);
                            newX = i + DX[toMove.direction];
                            newY = j + DY[toMove.direction];
                        }
                    }
                }
            }
        }

        return newFishBoard;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    int getCCW(int index) {
        return (index + 1) % 8;
    }

    public static class Fish {
        int index;
        int direction;

        Fish(int index, int direction) {
            this.index = index;
            this.direction = direction;
        }

    }
}