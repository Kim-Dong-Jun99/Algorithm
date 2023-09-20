import java.util.*;
import java.io.*;
import java.util.stream.Stream;


class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, -1, 0, 1};
    static final int[] DY = {1, 0, -1, 0};
    static final int[] ROTATE = {3, 0, 1, 2};
    int[] inputArray;
    int N;
    DragonCurve[] dragonCurves;
    boolean[][] square;
    int answer;

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
        N = Integer.parseInt(BR.readLine());
        dragonCurves = new DragonCurve[N];
        for (int i = 0; i < N; i++) {
            inputArray = getInputArray();
            dragonCurves[i] = new DragonCurve(new Position(inputArray[1], inputArray[0]), inputArray[2], inputArray[3]);
        }
        square = new boolean[101][101];
        answer = 0;
    }

    void solution() throws IOException {
        for (DragonCurve dragonCurve : dragonCurves) {
            List<Position> curve = new ArrayList<>();
            curve.add(dragonCurve.position);
            curve.add(new Position(dragonCurve.position.x + DX[dragonCurve.direction], dragonCurve.position.y + DY[dragonCurve.direction]));
            for (int i = 1; i <= dragonCurve.generation; i++) {
                List<Position> newCurve = new ArrayList<>(curve);
                for (int j = curve.size() - 1; j > 0; j--) {
                    int direction = getRotatedDirection(curve.get(j), curve.get(j - 1));
                    int newDirection = ROTATE[direction];
                    newCurve.add(new Position(newCurve.get(newCurve.size() - 1).x + DX[newDirection], newCurve.get(newCurve.size() - 1).y + DY[newDirection]));
                }
                curve = newCurve;
            }
            for (Position position : curve) {
                if (isInner(position)) {
                    square[position.x][position.y] = true;
                }
            }
        }

        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (canMakeSquare(i, j)) {
                    if (square[i][j] && square[i + 1][j] && square[i][j + 1] && square[i + 1][j + 1]) {
                        answer += 1;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    int getRotatedDirection(Position from, Position to) {
        for (int i = 0; i < 4; i++) {
            if (from.x + DX[i] == to.x && from.y + DY[i] == to.y) {
                return i;
            }
        }
        return -1;
    }

    boolean canMakeSquare(int x, int y) {
        return x + 1 <= 100 && y + 1 <= 100;
    }

    boolean isInner(Position position) {
        return 0 <= position.x && position.x <= 100 && 0 <= position.y && position.y <= 100;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class DragonCurve {
        Position position;
        int direction;
        int generation;

        public DragonCurve(Position position, int direction, int generation) {
            this.position = position;
            this.direction = direction;
            this.generation = generation;
        }
    }
}