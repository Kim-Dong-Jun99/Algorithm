import java.util.*;
import java.io.*;


public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    int[] inputArray;
    int N, M;
    int[][] map;
    List<Position> viruses;
    int answer;
    int wallCount;
    public static void main(String[] args) {

        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();

        N = inputArray[0];
        M = inputArray[1];

        map = new int[N][M];

        viruses = new ArrayList<>();
        wallCount = 0;

        for (int i = 0; i < N; i++) {
            map[i] = getInputArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == VIRUS) {
                    viruses.add(new Position(i, j));
                }
                if (map[i][j] == WALL) {
                    wallCount += 1;
                }
            }
        }
        answer = 0;

    }


    void solution() throws IOException {
        answer = backtrack(0, 0, 0);
        System.out.println(answer);
    }

    int backtrack(int index, int x, int y) {
        if (index == 3) {
            return getSafeArea();
        }
        int toReturn = 0;

        for (int i = x; i < N; i++) {
            int yStart;
            if (i == x) {
                yStart = y;
            } else {
                yStart = 0;
            }
            for (int j = yStart; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = WALL;

                    toReturn = Math.max(toReturn, backtrack(index + 1, i, j));

                    map[i][j] = EMPTY;
                }
            }
        }
        return toReturn;
    }

    int getSafeArea() {
        int safeArea = N * M - wallCount - 3;
        int[][] visited = new int[N][M];
        List<Position> currentPositions = viruses;
        while (!currentPositions.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position current : currentPositions) {
                safeArea -= 1;
                for (int i = 0; i < 4; i++) {
                    int newX = current.x + DX[i];
                    int newY = current.y + DY[i];
                    if (canGo(newX, newY) && visited[newX][newY] == 0) {
                        visited[newX][newY] = 1;
                        temp.add(new Position(newX, newY));
                    }
                }
            }
            currentPositions = temp;
        }
        return safeArea;
    }

    boolean canGo(int newX, int newY) {
        return 0 <= newX && newX < N && 0 <= newY && newY < M && map[newX][newY] == EMPTY;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}