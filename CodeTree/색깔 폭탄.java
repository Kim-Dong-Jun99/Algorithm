
import java.util.*;
import java.io.*;

/*
n * n 격자

-1, 0, 그리고 1 ~ m 숫자들로 이루어짐

-1 stone
0 red
1 ~ m 다른색

더 이상 폭탄 묶음이 없을 때 까지 다음 과정을 반복

크기가 가장 큰 폭탄 묶음을 찾는다.
    폭탄 묶음 ? 2 개 이상의 폭탄으로 이루어졌고, 모두 같은 색이거나 빨간색 폭탄을 포함하여 정확히 2개의 색으로만 이루어져야함
    빨간색 폭탄으로만 이루어지면 안된다

크기가 같은 폭탄 묶음이 여러개면 다음 우선순위에 따라 선택
    1. 빨간색이 가장 적은 것
    2. 기준점점 가장 행이 큰 폭탄 묶음 -> 빨간색이 아닌 점들 중 행이 가장 큰 칸, 행이 가장 큰칸이 여러개면 열이 가장 작은 칸
    3. 기준 점 중 열이 작은 폭탄 묶음 선택

폭탄묶음 폭탄 전부 제거, 제거된 이후에는 중력이 작용하여 폭탄은 떨어지는데, 돌은 떨어지지 않음

반시계 방향으로 90만큼 격자판에 회전이 일어남

다시 중력이 작용함

round마다 폭탄 개수의 제곱만큼 점수를 획득
 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, 1, -1};
    static final int STONE = -1;
    static final int RED = 0;
    static final int EMPTY = -2;
    int[] inputArray;
    int[][] bombBoard;
    int n, m;
    int score;
    List<BombGroup> bombGroups;
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

        bombBoard = new int[n][n];

        for (int i = 0; i < n; i++) {
            bombBoard[i] = getInputArray();
        }
        score = 0;
    }


    void solution() throws IOException {
        while (true) {
            bombGroups = getBombs();
            if (bombGroups.isEmpty()) {
                break;
            }
            removeBombGroup();

            gravity();

            rotateBoardCCW();

            gravity();


        }
        System.out.println(score);
    }

    List<BombGroup> getBombs() {
        List<BombGroup> bombGroupList = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bombBoard[i][j] > 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    List<Position> currentPositions = Collections.singletonList(new Position(i, j));
                    List<Position> bombPositions = new ArrayList<>();
                    Position bottomLeftPosition = new Position(i, j);
                    int bombType = bombBoard[i][j];
                    int redCount = 0;

                    while (!currentPositions.isEmpty()) {
                        List<Position> temp = new ArrayList<>();
                        for (Position currentPosition : currentPositions) {

                            bombPositions.add(currentPosition);

                            if (bombBoard[currentPosition.x][currentPosition.y] == RED) {
                                redCount += 1;
                            } else {
                                bottomLeftPosition = updateBottomLeft(bottomLeftPosition, currentPosition);
                            }


                            for (int d = 0; d < 4; d++) {
                                int newX = currentPosition.x + DX[d];
                                int newY = currentPosition.y + DY[d];

                                if (canGo(newX, newY, bombType) && !visited[newX][newY]) {
                                    visited[newX][newY] = true;
                                    temp.add(new Position(newX, newY));
                                }

                            }
                        }

                        currentPositions = temp;
                    }

                    if (bombPositions.size() >= 2) {
                        bombGroupList.add(new BombGroup(bombPositions, bottomLeftPosition, redCount));
                    }

                    for (Position position : bombPositions) {
                        if (bombBoard[position.x][position.y] == RED) {
                            visited[position.x][position.y] = false;
                        }
                    }

                }

            }
        }
        return bombGroupList;
    }

    void removeBombGroup() {
        bombGroups.sort(BombGroup::getBiggestGroup);
        BombGroup toRemove = bombGroups.get(0);
        score += toRemove.positions.size() * toRemove.positions.size();

        for (Position position : toRemove.positions) {
            bombBoard[position.x][position.y] = EMPTY;
        }

    }

    void gravity() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i > -1; i--) {
                if (bombBoard[i][j] == RED || (1 <= bombBoard[i][j] && bombBoard[i][j] <= m)) {
                    int bombType = bombBoard[i][j];
                    bombBoard[i][j] = EMPTY;

                    Position destination = getDestination(i, j);
                    bombBoard[destination.x][destination.y] = bombType;
                }
            }
        }
    }

    Position getDestination(int x, int y) {
        Stack<Position> visited = new Stack<>();
        Position current = new Position(x, y);

        while (true) {
            if (isInner(current.x, current.y)) {
                if (bombBoard[current.x][current.y] == EMPTY) {
                    visited.push(current);
                    current = new Position(current.x + 1, current.y);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return visited.pop();
    }

    void rotateBoardCCW() {
        int[][] newBoard = new int[n][n];
        for (int j = n-1; j > -1; j--) {
            for (int i = 0; i < n; i++) {
                Position rotatedPosition = getRotatedPosition(i, j);
                newBoard[rotatedPosition.x][rotatedPosition.y] = bombBoard[i][j];
            }
        }

        bombBoard = newBoard;
    }

    Position getRotatedPosition(int x, int y) {
        return new Position(n - 1 - y, x);
    }

    Position updateBottomLeft(Position current, Position newPosition) {
        if (current.x < newPosition.x) {
            return newPosition;
        } else if (current.x > newPosition.x) {
            return current;
        } else {
            if (current.y < newPosition.y) {
                return current;
            } else {
                return newPosition;
            }
        }
    }

    boolean canGo(int x, int y, int bombType) {
        return isInner(x, y) && (bombBoard[x][y] == RED || bombBoard[x][y] == bombType);
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class BombGroup {
        List<Position> positions;
        Position bottomLeftPosition;
        int redCount;

        public BombGroup(List<Position> positions, Position bottomLeftPosition, int redCount) {
            this.positions = positions;
            this.bottomLeftPosition = bottomLeftPosition;
            this.redCount = redCount;
        }

        int getBiggestGroup(BombGroup compare) {
            if (this.positions.size() != compare.positions.size()) {
                return Integer.compare(compare.positions.size(), this.positions.size());
            } else if (this.redCount != compare.redCount) {
                return Integer.compare(this.redCount, compare.redCount);
            } else if (this.bottomLeftPosition.x != compare.bottomLeftPosition.x) {
                return Integer.compare(compare.bottomLeftPosition.x, this.bottomLeftPosition.x);
            } else {
                return Integer.compare(this.bottomLeftPosition.y, compare.bottomLeftPosition.y);
            }
        }

    }


}
