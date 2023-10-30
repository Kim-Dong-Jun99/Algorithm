import java.util.*;
import java.io.*;

/*
L * L 크기 체스판,

왼쪽 상단 1,1

칸들은 빈칸, 함정, 벽으로 구성되고, 체스판 밖도 벽임

왕실의 기사들은 자신의 마력으로 상대방을 밀쳐낼 수 있다. 각 기사의 초기위치는 (r,c)로 주어지며,
그들은 방패를 들고 있기 때문에, r,c를 좌측상단으로 하여 h * w 크기의 직사각형 형태를 띄고 있고, 각 기사들의 체력은 k로 주어진다.

기사이동
    상하좌우 중 한칸이동, 이동하려는 위치에 다른 기사가 있다면 그 기사도 연쇄적으로 한칸 밀려남,
    이동하려는 방향 끝에 벽이 있다면모든 기사 이동 X,

대결 대미지
    명령을 받은 기사가 다른 기사를 밀치면 밀려난 기사들은 피해를 입게된다.
    각 기사들은 해당 기사가 이동한 곳에서 w * h 직사각형 내에 놓여있는 함정의 수만큼만 피해를 받는다.
    각 기사마다 피해를 받은 만큼 체력이 깎이고, 현재 체력 이상의 대미지를 받을 경우, 기사는 체스판에서 사라진다.
    명령을 받은 기사는 피해를 입지 않고, 기사들은 모두 밀린 이후에 대미지를 입게된다.
    밀렸는데, 밀쳐진 위치에 함정이 없으면 피해 0,



2차원 누적합쓰면되겠다 ㅇㅇ 함정위치는 안변하니까,
 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};
    static final int EMPTY = 0;
    static final int WALL = -1;
    int L, N, Q;
    Knight[] knights;
    Command[] commands;
    int[] inputArray;
    int[][] trapSubSum, board;


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
        inputArray = getInputArray();
        L = inputArray[0];
        N = inputArray[1];
        Q = inputArray[2];

        board = new int[L][L];
        trapSubSum = new int[L + 1][L + 1];
        knights = new Knight[N + 1];
        commands = new Command[Q];


        for (int i = 0; i < L; i++) {
            board[i] = getInputArray();
            for (int j = 0; j < L; j++) {
                if (board[i][j] == 1) {
                    trapSubSum[i + 1][j + 1] = 1;
                    board[i][j] = EMPTY;
                }
                if (board[i][j] == 2) {
                    board[i][j] = WALL;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            inputArray = getInputArray();
            Knight knight = new Knight(i + 1, inputArray[0] - 1, inputArray[1] - 1, inputArray[2] - 1, inputArray[3] - 1, inputArray[4]);
            knights[i + 1] = knight;
            for (int dx = 0; dx <= knight.h; dx++) {
                for (int dy = 0; dy <= knight.w; dy++) {
                    board[knight.x + dx][knight.y + dy] = knight.index;
                }
            }

        }

        for (int i = 0; i < Q; i++) {
            inputArray = getInputArray();
            commands[i] = new Command(inputArray[0], inputArray[1]);
        }

        initTrapSubSum();

    }


    void initTrapSubSum() {
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= L; j++) {
                trapSubSum[i][j] += trapSubSum[i][j - 1];
            }
        }
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= L; j++) {
                trapSubSum[i][j] += trapSubSum[i - 1][j];
            }
        }
    }

    int getTrapSubSum(int fromX, int fromY, int toX, int toY) {
        return trapSubSum[toX + 1][toY + 1] - trapSubSum[toX + 1][fromY] - trapSubSum[fromX][toY + 1] + trapSubSum[fromX][fromY];
    }


    void solution() {

        for (Command command : commands) {
            Knight knight = knights[command.i];
            if (knight.k > knight.damage && canPush(knight, command.d)) {
                List<Knight> pushKnights = pushKnights(knight, command.d);
                attackKnights(pushKnights);
            }
        }
    }

    List<Knight> pushKnights(Knight pushKnight, int d) {
        List<Knight> toPush = Collections.singletonList(pushKnight);
        List<Knight> pushed = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        visited[pushKnight.index] = true;
        while (!toPush.isEmpty()) {
            List<Knight> temp = new ArrayList<>();
            for (Knight knight : toPush) {

                for (int dx = 0; dx <= knight.h; dx++) {
                    for (int dy = 0; dy <= knight.w; dy++) {
                        board[knight.x + dx][knight.y + dy] = EMPTY;
                        int newX = knight.x + dx + DX[d];
                        int newY = knight.y + dy + DY[d];
                        if (board[newX][newY] != EMPTY && !visited[board[newX][newY]]) {
                            temp.add(knights[board[newX][newY]]);
                            visited[board[newX][newY]] = true;
                            pushed.add(knights[board[newX][newY]]);
                        }
                    }
                }
            }

            toPush = temp;
        }

        pushKnight.x += DX[d];
        pushKnight.y += DY[d];
        for (int dx = 0; dx <= pushKnight.h; dx++) {
            for (int dy = 0; dy <= pushKnight.w; dy++) {
                board[pushKnight.x + dx][pushKnight.y + dy] = pushKnight.index;

            }
        }
        for (Knight knight : pushed) {
            knight.x += DX[d];
            knight.y += DY[d];
            for (int dx = 0; dx <= knight.h; dx++) {
                for (int dy = 0; dy <= knight.w; dy++) {
                    board[knight.x + dx][knight.y + dy] = knight.index;

                }
            }
        }

        return pushed;


    }

    void attackKnights(List<Knight> pushedKnights) {
        for (Knight pushedKnight : pushedKnights) {
            int damage = getTrapSubSum(pushedKnight.x, pushedKnight.y, pushedKnight.x + pushedKnight.h, pushedKnight.y + pushedKnight.w);
            pushedKnight.damage += damage;
            if (pushedKnight.k <= pushedKnight.damage) {
                for (int dx = 0; dx <= pushedKnight.h; dx++) {
                    for (int dy = 0; dy <= pushedKnight.w; dy++) {
                        board[pushedKnight.x+dx][pushedKnight.y+dy] = EMPTY;
                    }
                }
            }

        }

    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < L && 0 <= y && y < L;
    }

    boolean canPush(Knight pushKnight, int d) {
        List<Knight> toPush = Collections.singletonList(pushKnight);
        boolean[] visited = new boolean[N + 1];
        visited[pushKnight.index] = true;
        while (!toPush.isEmpty()) {
            List<Knight> temp = new ArrayList<>();
            for (Knight knight : toPush) {
                for (int dx = 0; dx <= knight.h; dx++) {
                    for (int dy = 0; dy <= knight.w; dy++) {
                        int newX = knight.x + dx + DX[d];
                        int newY = knight.y + dy + DY[d];
                        if (!isInner(newX, newY) || board[newX][newY] == WALL) {
                            return false;
                        }
                        if (board[newX][newY] != EMPTY && !visited[board[newX][newY]]) {
                            temp.add(knights[board[newX][newY]]);
                            visited[board[newX][newY]] = true;
                        }
                    }
                }
            }

            toPush = temp;
        }
        return true;
    }


    void printResult() {
        int damage = 0;
        for (int i = 1; i <= N; i++) {
            Knight knight = knights[i];
            if (knight.k > knight.damage) {
                damage += knight.damage;
            }
        }
        System.out.println(damage);
    }


    static class Knight {
        int index;
        int x;
        int y;
        int h;
        int w;
        int k;
        int damage;

        public Knight(int index, int x, int y, int h, int w, int k) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
            this.k = k;
            this.damage = 0;
        }
    }

    static class Command {
        int i;
        int d;

        public Command(int i, int d) {
            this.i = i;
            this.d = d;
        }
    }



}
