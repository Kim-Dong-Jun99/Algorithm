import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/*
턴 1번은 1번 말부터 K번 말까지 순서대로 이동시키는 것이다.
한 말이 이동할때 위에 올려져 있는 말도 함께 이동한다. 말의 이동 방향에 있는 칸에 따라서 말의 이동이 다르다

말이 4개 이상 쌓이게되면, 게임은 종료된다.

말이 이동하려는 칸이 흰색인 경우, 해당 칸으로 이동하고, 해당 칸에 말이 이미 있는 경우에는 가장위에 말을 올려놓는다.
A 번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.

말이 이동하려는 칸이 빨간색인 경우, 이동한 후에 A번말과 그 위에 잇는 모든 말의 쌓여있는 순서를 반대로 변경한다.

파란색인 경우, A번 말의 이동 방향을 반대로 하고, 한칸 이동한다. 방향을 반대로 바꾼 후 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.

체스판을 벗어나는 경우에는 파란색과 같이 동작한다.

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final Integer WHITE = 0;
    static final Integer RED = 1;
    static final Integer BLUE = 2;
    static final int[] DX = {0, 0, -1, 1};
    static final int[] DY = {1, -1, 0, 0};
    static final int[] OPPOSITE = {1, 0, 3, 2};
    int[] inputArray;
    int N, K, turn;
    int[][] board;
    Horse[] horses;
    List<Integer>[][] horsesBoard;
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
        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        K = inputArray[1];

        turn = 0;

        board = new int[N][N];
        horses = new Horse[K];
        horsesBoard = new List[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                horsesBoard[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < K; i++) {
            inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            horses[i] = new Horse(i,inputArray[0] - 1, inputArray[1] - 1, inputArray[2] - 1);
            horsesBoard[inputArray[0] - 1][inputArray[1] - 1].add(i);
        }

    }

    void solution() throws IOException {
        while (turn <= 1000) {
            if (isEnd()) {
                break;
            }

            for (Horse horse : horses) {
                int newX = horse.x + DX[horse.direction];
                int newY = horse.y + DY[horse.direction];

                if (isInner(newX, newY) && board[newX][newY] != BLUE) {
                    moveHorse(horse, newX, newY);
                } else {
                    horse.direction = OPPOSITE[horse.direction];
                    newX = horse.x + DX[horse.direction];
                    newY = horse.y + DY[horse.direction];

                    if (isInner(newX, newY) && board[newX][newY] != BLUE) {
                        moveHorse(horse, newX, newY);
                    }
                }
                if (isEnd()) {
                    break;
                }
            }

            turn += 1;
        }
        if (turn <= 1000) {
            System.out.println(turn);
        } else {
            System.out.println(-1);
        }

    }

    void moveHorse(Horse horse, int newX, int newY) {
        List<Integer> remain = new ArrayList<>();
        List<Integer> toMove = new ArrayList<>();

        boolean moveStart = false;
        for (Integer horseIndex : horsesBoard[horse.x][horse.y]) {
            if (horseIndex == horse.index) {
                moveStart = true;
            }
            if (moveStart) {
                toMove.add(horseIndex);
            } else {
                remain.add(horseIndex);
            }
        }

        horsesBoard[horse.x][horse.y] = remain;

        if (board[newX][newY] == WHITE) {
            for (Integer horseIndex : toMove) {
                horsesBoard[newX][newY].add(horseIndex);
                horses[horseIndex].x = newX;
                horses[horseIndex].y = newY;
            }
        } else {
            for (int i = toMove.size() - 1; i > -1; i--) {
                int horseIndex = toMove.get(i);
                horsesBoard[newX][newY].add(horseIndex);
                horses[horseIndex].x = newX;
                horses[horseIndex].y = newY;
            }
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }


    boolean isEnd() {
        for (Horse horse : horses) {
            if (horsesBoard[horse.x][horse.y].size() >= 4) {
                return true;
            }
        }
        return false;
    }


    static class Horse {
        int index;
        int x;
        int y;
        int direction;

        public Horse(int index, int x, int y, int direction) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

}