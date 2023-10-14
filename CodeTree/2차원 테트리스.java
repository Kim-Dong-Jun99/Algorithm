import java.util.*;
import java.io.*;

/*
3개의 타일,

노란색 블록은 행이 다 차면 지워짐, 빨간색은 열이 다 차면 지워짐,

지워지는 행이나 열 한 줄당 1점, 여러 줄이 한꺼번에 지워질 수 있다. 지워지면 위에 있는 블록은 한칸씩 아래로 내려감

연한 블록에 블록이 놓이면 그만큼 밀린다

명령을 k번만큼 실행하고, 획득한 점수와, 노란색 블록, 빨간색 블록에 있는 칸의수를 출력

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int[] inputArray;
    int k;
    int score;
    Tile[] tiles;
    boolean[][] yellow;
    boolean[][] red;

    public static void main(String[] args) {

        Main main = new Main();
        try {
            main.init();
            main.solution();
            main.printResult();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        k = Integer.parseInt(BR.readLine());
        score = 0;
        tiles = new Tile[k];
        yellow = new boolean[6][4];
        red = new boolean[4][6];
        for (int i = 0; i < k; i++) {
            inputArray = getInputArray();
            tiles[i] = new Tile(inputArray[0], new Position(inputArray[1], inputArray[2]));
        }
    }

    void solution() {
        for (Tile tile : tiles) {
            addTileToRed(tile);
            addTileToYellow(tile);
            updateRedScore();
            updateYellowScore();
            handleRedGrayArea();
            handleYellowGrayArea();
        }
    }

    void addTileToRed(Tile tile) {
        List<Position> tilePositions = new ArrayList<>();
        if (tile.type == 1) {
            tilePositions.add(new Position(tile.p.x, 1));
        } else if (tile.type == 2) {
            tilePositions.add(new Position(tile.p.x, 1));
            tilePositions.add(new Position(tile.p.x, 0));
        } else {
            tilePositions.add(new Position(tile.p.x, 1));
            tilePositions.add(new Position(tile.p.x + 1, 1));
        }
        int d = 1;
        while (d <= 4) {
            boolean cantMark = false;
            for (Position tilePosition : tilePositions) {
                if (red[tilePosition.x][tilePosition.y + 1]) {
                    cantMark = true;
                    break;
                }
            }
            if (cantMark) {
                break;
            }
            for (Position tilePosition : tilePositions) {
                tilePosition.y += 1;
            }
            d += 1;
        }
        for (Position tilePosition : tilePositions) {
            red[tilePosition.x][tilePosition.y] = true;
        }
    }

    void addTileToYellow(Tile tile) {
        List<Position> tilePositions = new ArrayList<>();
        if (tile.type == 1) {
            tilePositions.add(new Position(1, tile.p.y));
        } else if (tile.type == 3) {
            tilePositions.add(new Position(0, tile.p.y));
            tilePositions.add(new Position(1, tile.p.y));
        } else {
            tilePositions.add(new Position(1, tile.p.y));
            tilePositions.add(new Position(1, tile.p.y + 1));
        }
        int d = 1;
        while (d <= 4) {
            boolean cantMark = false;
            for (Position tilePosition : tilePositions) {
                if (yellow[tilePosition.x + 1][tilePosition.y]) {
                    cantMark = true;
                    break;
                }
            }
            if (cantMark) {
                break;
            }
            for (Position tilePosition : tilePositions) {
                tilePosition.x += 1;
            }
            d += 1;
        }
        for (Position tilePosition : tilePositions) {
            yellow[tilePosition.x][tilePosition.y] = true;
        }
    }

    void updateRedScore() {
        while (true) {
            int colToRemove = canUpdateRedScore();
            if (colToRemove == -1) {
                break;
            }
            score += 1;
            for (int x = 0; x < 4; x++) {
                red[x][colToRemove] = false;
            }
            for (int y = colToRemove; y > 0; y--) {
                for (int x = 0; x < 4; x++) {
                    red[x][y] = red[x][y - 1];
                }
            }
            for (int x = 0; x < 4; x++) {
                red[x][0] = false;
            }
        }
    }

    int canUpdateRedScore() {
        for (int y = 2; y < 6; y++) {
            int filled = 0;
            for (int x = 0; x < 4; x++) {
                if (red[x][y]) {
                    filled += 1;
                }
            }
            if (filled == 4) {
                return y;
            }
        }
        return -1;
    }

    void updateYellowScore() {
        while (true) {
            int rowToRemove = canUpdateYellowScore();
            if (rowToRemove == -1) {
                break;
            }
            score += 1;
            for (int y = 0; y < 4; y++) {
                yellow[rowToRemove][y] = false;
            }
            for (int x = rowToRemove; x > 0; x--) {
                for (int y = 0; y < 4; y++) {
                    yellow[x][y] = yellow[x - 1][y];
                }
            }
            for (int y = 0; y < 4; y++) {
                yellow[0][y] = false;
            }
        }
    }

    int canUpdateYellowScore() {
        for (int x = 2; x < 6; x++) {
            int filled = 0;
            for (int y = 0; y < 4; y++) {
                if (yellow[x][y]) {
                    filled += 1;
                }
            }
            if (filled == 4) {
                return x;
            }
        }
        return -1;
    }

    void handleRedGrayArea() {
        while (tileInRedGray()) {
            for (int y = 5; y > 0; y--) {
                for (int x = 0; x < 4; x++) {
                    red[x][y] = red[x][y - 1];
                }
            }
            for (int x = 0; x < 4; x++) {
                red[x][0] = false;
            }
        }
    }

    boolean tileInRedGray() {
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 4; x++) {
                if (red[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

    void handleYellowGrayArea() {
        while (tileInYellowGray()) {
            for (int x = 5; x > 0; x--) {
                for (int y = 0; y < 4; y++) {
                    yellow[x][y] = yellow[x - 1][y];
                }
            }
            for (int y = 0; y < 4; y++) {
                yellow[0][y] = false;
            }
        }
    }

    boolean tileInYellowGray() {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 4; y++) {
                if (yellow[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

    void printResult() {
        System.out.println(score);
        System.out.println(calculateFilled());
    }

    int calculateFilled() {
        int filled = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (yellow[i + 2][j]) {
                    filled += 1;
                }
                if (red[i][j + 2]) {
                    filled += 1;
                }
            }
        }
        return filled;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Tile {
        int type;
        Position p;

        public Tile(int type, Position p) {
            this.type = type;
            this.p = p;
        }
    }

}
