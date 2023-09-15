import java.util.*;
import java.io.*;

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
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int DOWN = 3;

    int[] inputArray;
    int N, L;
    int[][] map;
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

    void init() throws IOException {
        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        L = inputArray[1];

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        answer = 0;
    }

    void solution() throws IOException {
//        System.out.println("left or right");
        for (int i = 0; i < N; i++) {
            boolean[] hasSlide = new boolean[N];
            boolean canGo = true;
            for (int j = 1; j < N; j++) {
                if (map[i][j - 1] < map[i][j]) {
                    if (map[i][j - 1] + 1 < map[i][j]) {
                        canGo = false;
                        break;
                    }
                    if (cantPutSlide(i, j, hasSlide, LEFT)) {
                        canGo = false;
                        break;
                    }

                    putSlide(i, j, hasSlide, LEFT);

                } else if (map[i][j - 1] > map[i][j]) {
                    if (map[i][j - 1] > map[i][j] + 1) {
                        canGo = false;
                        break;
                    }
                    if (cantPutSlide(i, j-1, hasSlide, RIGHT)) {
                        canGo = false;
                        break;
                    }

                    putSlide(i, j-1, hasSlide, RIGHT);
                }
            }
            if (canGo) {
//                System.out.println(i);
                answer += 1;
            }
        }
//        System.out.println("up or down");
        for (int j = 0; j < N; j++) {
            boolean[] hasSlide = new boolean[N];
            boolean canGo = true;
            for (int i = 1; i < N; i++) {
                if (map[i-1][j] < map[i][j]) {
                    if (map[i-1][j] + 1 < map[i][j]) {
                        canGo = false;
                        break;
                    }
                    if (cantPutSlide(i, j, hasSlide, UP)) {
                        canGo = false;
                        break;
                    }

                    putSlide(i, j, hasSlide, UP);

                } else if (map[i-1][j] > map[i][j]) {
                    if (map[i-1][j] > map[i][j] + 1) {
                        canGo = false;
                        break;
                    }
                    if (cantPutSlide(i-1, j, hasSlide, DOWN)) {
                        canGo = false;
                        break;
                    }

                    putSlide(i-1, j, hasSlide, DOWN);
                }
            }
            if (canGo) {
//                System.out.println(j);
                answer += 1;
            }
        }

        System.out.println(answer);
    }

    void putSlide(int x, int y, boolean[] hasSlide, int direction) {
        if (direction == LEFT) {
            for (int i = 1; i <= L; i++) {
                hasSlide[y - i] = true;
            }
        } else if (direction == RIGHT) {
            for (int i = 1; i <= L; i++) {
                hasSlide[y + i] = true;
            }
        } else if (direction == UP) {
            for (int i = 1; i <= L; i++) {
                hasSlide[x - i] = true;
            }
        } else {
            for (int i = 1; i <= L; i++) {
                hasSlide[x + i] = true;
            }
        }

    }

    boolean cantPutSlide(int x, int y, boolean[] hasSlide, int direction) {
        HashSet<Integer> visited = new HashSet<>();
        if (direction == LEFT) {
            for (int i = 1; i <= L; i++) {
                if (y - i < 0) {
                    return true;
                }
                if (hasSlide[y - i]) {
                    return true;
                }
                visited.add(map[x][y - i]);
                if (visited.size() > 1) {
                    return true;
                }
            }
        } else if (direction == RIGHT) {
            for (int i = 1; i <= L; i++) {
                if (y + i >= N) {
                    return true;
                }
                if (hasSlide[y + i]) {
                    return true;
                }
                visited.add(map[x][y + i]);
                if (visited.size() > 1) {
                    return true;
                }
            }
        } else if (direction == UP) {
            for (int i = 1; i <= L; i++) {
                if (x - i < 0) {
                    return true;
                }
                if (hasSlide[x - i]) {
                    return true;
                }
                visited.add(map[x - i][y]);
                if (visited.size() > 1) {
                    return true;
                }
            }
        } else {
            for (int i = 1; i <= L; i++) {
                if (x + i >= N) {
                    return true;
                }
                if (hasSlide[x + i]) {
                    return true;
                }
                visited.add(map[x + i][y]);
                if (visited.size() > 1) {
                    return true;
                }
            }
        }

        return false;
    }


}