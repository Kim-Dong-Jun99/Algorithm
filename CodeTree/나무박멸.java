import java.util.*;
import java.io.*;


/*
나무 성장 - 인접한 나무의 수 만큼
나무 번식 - 인접한 칸 중 벽, 나무, 제초제 없는 칸에 번식하는데 나무수 / 번식가능 칸의 수 로 각 칸으로 번식
제초제 뿌리기 - 나무가 가장 많이 박멸되는 칸에 뿌림, 4개 대각선방향으로 4칸만큼 전파된다. 중간에 벽이 잇거나 나무가 아예 없으면 막힘, c년동안 유지,
제초제를 뿌릴 수 있는 포지션이 많으면 행 작은 순 그 다음 열 작은 순으로 제초제뿌릴 곳 선정
m년 동안 박멸한 나무의 그루 수 구하기

n <= 20
m <= 1000
k <= 20
c <= 10

일단 나무 정보를 저장한 이중 배열이 필요하겠지

제초제 정보를 저장할 이중 배열도 필요할듯?
제초제 배열에는 제초제 종료 시간을 저장, 즉 현재 시간 > 제초제 시간이면 제초제 없는 것 ㅇㅇ

성장이랑 번식은 한 메소드로 할 수 있지 않을까
newTree라는 이중 배열에 새로운 값들 번식한 값들과 성장한 값을 저장해서 갈아끼우면 될듯

문제가 제초제 선정인데,,

newTree를 만들면서 나무 위치정보만 저장해서 나무 위치 정보만을 이용해서 탐색?

만약 최악의 경우
1000 * 20 * 20 = 40만

40만에서 4대각선 보는 경우 = 최악 80
32백만이니까 세이프하겠지 10^9이하니까

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 0, 1, 0, 1, 1, -1, -1};
    static final int[] DY = {0, 1, 0, -1, 1, -1, 1, -1};
    static final int WALL = -1;
    int[] inputArray;
    int[][] tree;
    int[][] poison;
    int n, m, c, k;
    int time;
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
        inputArray = getInputArray();
        n = inputArray[0];
        m = inputArray[1];
        k = inputArray[2];
        c = inputArray[3];

        tree = new int[n][n];
        poison = new int[n][n];

        time = 1;
        answer = 0;
        for (int i = 0; i < n; i++) {
            tree[i] = getInputArray();
        }

    }


    void solution() throws IOException {
        while (time <= m) {
            growAndBreed();
            try {
                poison();
            } catch (RuntimeException e) {
                break;
            }
            time += 1;
        }
        System.out.println(answer);
    }

    void growAndBreed() {
        int[][] newTree = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tree[i][j] == -1) {
                    newTree[i][j] = -1;
                } else if (tree[i][j] > 0) {
                    List<Position> treeNeighbor = new ArrayList<>();
                    List<Position> empty = new ArrayList<>();

                    for (int d = 0; d < 4; d++) {
                        int newX = i + DX[d];
                        int newY = j + DY[d];
                        if (isInner(newX, newY)) {
                            if (canBreed(newX, newY)) {
                                empty.add(new Position(newX, newY));
                            }
                            if (tree[newX][newY] > 0) {
                                treeNeighbor.add(new Position(newX, newY));
                            }
                        }
                    }
                    newTree[i][j] = tree[i][j] + treeNeighbor.size();
                    for (Position emptyPosition : empty) {
                        newTree[emptyPosition.x][emptyPosition.y] += newTree[i][j] / empty.size();
                    }

                }
            }
        }

        tree = newTree;
    }

    boolean canBreed(int x, int y) {
        return poison[x][y] < time && tree[x][y] == 0;
    }

    boolean canPoison(int x, int y) {
        return tree[x][y] > 0;
    }
    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    Position getPoisonTarget() {
        int maxErase = 0;
        Position toReturn = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tree[i][j] > 0) {
                    int removedTree = calculateRemovedTree(i, j);
                    if (removedTree > maxErase) {
                        maxErase = removedTree;
                        toReturn = new Position(i, j);
                    }


                }
            }
        }
        return toReturn;
    }

    int calculateRemovedTree(int x, int y) {
        int removed = tree[x][y];
        for (int i = 4; i < 8; i++) {
            for (int d = 1; d <= k; d++) {
                int newX = x + DX[i] * d;
                int newY = y + DY[i] * d;
                if (isInner(newX, newY) && canPoison(newX, newY)) {
                    removed += tree[newX][newY];
                } else {
                    break;
                }
            }
        }
        return removed;

    }

    void poison() {
        Position position = getPoisonTarget();
        answer += tree[position.x][position.y];
        tree[position.x][position.y] = 0;
        poison[position.x][position.y] = time + c;
        for (int i = 4; i < 8; i++) {
            for (int d = 1; d <= k; d++) {
                int newX = position.x + DX[i] * d;
                int newY = position.y + DY[i] * d;
                if (isInner(newX, newY)) {
                    if (canPoison(newX, newY)) {
                        answer += tree[newX][newY];
                        tree[newX][newY] = 0;
                        poison[newX][newY] = time + c;
                    } else {
                        if (tree[newX][newY] == -1) {
                            break;
                        }
                        answer += tree[newX][newY];
                        tree[newX][newY] = 0;
                        poison[newX][newY] = time + c;
                        break;
                    }
                } else {
                    break;
                }

            }
        }


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
