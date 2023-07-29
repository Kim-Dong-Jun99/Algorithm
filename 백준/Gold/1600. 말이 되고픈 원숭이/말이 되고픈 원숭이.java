import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int K;
    int W;
    int H;
    int[][] field;
    int[][][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int[] jumpX = {-2, 0, 2, 0};
    int[] jumpY = {0, -2, 0, 2};
    List<Monkey> monkeys;
    List<Monkey> tempMonkeys;
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
        K = Integer.parseInt(BR.readLine());

        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        H = inputArray[1];
        W = inputArray[0];

        field = new int[H][W];
        visited = new int[H][W][K+1];

        for (int i = 0; i < H; i++) {
            field[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        monkeys = new ArrayList<>();
        tempMonkeys = new ArrayList<>();
    }

    void solution() throws IOException {
        monkeys.add(new Monkey(0, 0, 0, 0));
        while (!monkeys.isEmpty()) {
            tempMonkeys = new ArrayList<>();
            for (Monkey monkey : monkeys) {
                if (monkey.x == H - 1 && monkey.y == W - 1) {
                    System.out.println(monkey.moveCount);
                    return;
                }
                if (monkey.jumpCount + 1 <= K) {
                    monkeyJump(monkey);
                }
                monkeyMove(monkey);
            }
            monkeys = tempMonkeys;
        }
        System.out.println(-1);

    }

    static class Monkey {
        int x;
        int y;
        int jumpCount;
        int moveCount;

        public Monkey(int x, int y, int jumpCount, int moveCount) {
            this.x = x;
            this.y = y;
            this.jumpCount = jumpCount;
            this.moveCount = moveCount;
        }
    }

    void monkeyJump(Monkey monkey) {
        for (int i = 0; i < 4; i++) {
            for (int j = -1; j < 2; j += 2) {
                int nextX = monkey.x + jumpX[i];
                int nextY = monkey.y + jumpY[i];
                if (i % 2 == 0) {
                    nextY += j;
                } else {
                    nextX += j;
                }
                if (canGo(nextX, nextY) && visited[nextX][nextY][monkey.jumpCount + 1] == 0) {
                    visited[nextX][nextY][monkey.jumpCount + 1] = 1;
                    tempMonkeys.add(new Monkey(nextX, nextY, monkey.jumpCount + 1, monkey.moveCount + 1));
                }

            }
        }
    }

    void monkeyMove(Monkey monkey) {
        for (int i = 0; i < 4; i++) {
            int nextX = monkey.x + dx[i];
            int nextY = monkey.y + dy[i];
            if (canGo(nextX, nextY) && visited[nextX][nextY][monkey.jumpCount] == 0) {
                visited[nextX][nextY][monkey.jumpCount] = 1;
                tempMonkeys.add(new Monkey(nextX, nextY, monkey.jumpCount, monkey.moveCount + 1));
            }

        }

    }

    boolean canGo(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W && field[x][y] == 0;
    }
}
