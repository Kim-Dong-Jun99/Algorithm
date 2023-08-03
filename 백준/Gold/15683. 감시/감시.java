import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, -1, 0, 1};
    int[] OPPOSITE = {2, 3, 0, 1};
    int[] CLOCKWISE = {3, 0, 1, 2};
    int N;
    int M;
    int[][] offices;
    List<CCTV> cctvList;
    int answer;
    int spy;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    void init() throws IOException {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        offices = new int[N][M];
        answer = 0;
        spy = 0;
        for (int i = 0; i < N; i++) {
            offices[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        cctvList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (offices[i][j] != 0) {
                    if (offices[i][j] != 6) {
                        cctvList.add(new CCTV(i, j, offices[i][j]));
                    }
                    spy += 1;
                }
            }
        }

    }

    void solution() throws IOException {
        backTrack(0);
        System.out.println(N * M - answer);
    }

    void backTrack(int index) {
        if (index == cctvList.size()) {
            answer = Math.max(spy, answer);
            return;
        }
        CCTV cctv = cctvList.get(index);

        if (cctv.type == 5) {
            List<Coordinate> toRemove = new ArrayList<>();
            colorOneTime(cctv.x, cctv.y, toRemove);
            backTrack(index + 1);
            unColor(toRemove);
        } else if (cctv.type == 2) {
            for (int i = 0; i < 2; i++) {
                List<Coordinate> toRemove = new ArrayList<>();
                colorTwoTime(i, cctv.x, cctv.y, toRemove);
                backTrack(index+1);
                unColor(toRemove);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                List<Coordinate> toRemove = new ArrayList<>();
                colorFourTime(cctv.type, i, cctv.x, cctv.y, toRemove);
                backTrack(index+1);
                unColor(toRemove);
            }
        }

    }

    void colorOneTime(int x, int y, List<Coordinate> toRemove) {
        for (int i = 0; i < 4; i++) {
            int newX = x + DX[i];
            int newY = y + DY[i];
            colorOneLine(newX, newY, DX[i], DY[i], toRemove);
        }
    }

    void colorTwoTime(int index, int x, int y, List<Coordinate> toRemove) {
        for (int i = 0; i < 2; i++) {
            int newX = x;
            int newY = y;
            if (i == 0) {
                newX += DX[index];
                newY += DY[index];
                colorOneLine(newX, newY, DX[index], DY[index], toRemove);
            } else {
                newX += DX[OPPOSITE[index]];
                newY += DY[OPPOSITE[index]];
                colorOneLine(newX, newY, DX[OPPOSITE[index]], DY[OPPOSITE[index]], toRemove);
            }

        }
    }

    void colorFourTime(int type, int index, int x, int y, List<Coordinate> toRemove) {
        int newX;
        int newY;
        switch (type) {
            case 1:
                newX = x + DX[index];
                newY = y + DY[index];
                colorOneLine(newX, newY, DX[index], DY[index], toRemove);
                break;
            case 3:
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        newX = x + DX[index];
                        newY = y + DY[index];
                        colorOneLine(newX, newY, DX[index], DY[index], toRemove);
                    } else {
                        newX = x + DX[CLOCKWISE[index]];
                        newY = y + DY[CLOCKWISE[index]];
                        colorOneLine(newX, newY, DX[CLOCKWISE[index]], DY[CLOCKWISE[index]], toRemove);
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    if (i != index) {
                        newX = x + DX[i];
                        newY = y + DY[i];
                        colorOneLine(newX, newY, DX[i], DY[i], toRemove);
                    }
                }
                break;
        }
    }

    void colorOneLine(int newX, int newY, int dx, int dy, List<Coordinate> toRemove) {
        while (isInner(newX, newY) && canGo(newX, newY)) {
            if (offices[newX][newY] == 0) {
                offices[newX][newY] = -1;
                spy += 1;
                toRemove.add(new Coordinate(newX, newY));
            }
            newX += dx;
            newY += dy;
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    boolean canGo(int x, int y) {
        return offices[x][y] != 6;
    }

    void unColor(List<Coordinate> toRemove) {
        for (Coordinate coordinate : toRemove) {
            offices[coordinate.x][coordinate.y] = 0;
            spy -= 1;
        }

    }

    static class CCTV {
        int x;
        int y;
        int type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
