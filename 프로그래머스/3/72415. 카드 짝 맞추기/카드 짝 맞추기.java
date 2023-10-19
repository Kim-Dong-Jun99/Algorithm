import java.util.*;
import java.util.Map.Entry;

/*
4 * 4 크기 격자 형태, 8가지 캐릭터 그림이 그려진 카드가 각 2장씩 화면에 무작위로 배치되어있다

게임이 시작되면 카드가 뒷면을 위로 해서 배치

유저가 카드를 2장 선택해서 앞면으로 뒤집었을 때 같은 카드면 해당 카드는 사라짐,

모든 카드가 사라지면 게임 종료

카드는 커서를 이용해 선택가능하다.

커서는 방향키 중 하나를 누르면 커서가 누른 키 방향으로 1칸 이동한다
컨트롤을 누른 상태에서 방향키를 누르면, 누른 키 방향에 있는 가장 가까운 카드로 이동
해당 방향에 카드가 하나도 없다면 그 방향의 마지막 칸으로 이동한다

누른 키 방향으로 이동 가능한 카드가 없고, 빈 공간이 없다면 커서는 움직이지 않는다.

커서가 위치한 카드를 뒤집기 위해서는 엔터 키를 입력

카드를 모두 제거하는데 필요한 키 조작 횟수의 최솟값 구하기, 엔터키나 컨트롤 눌러도 1번 누른 것으로 계산
*/

class Solution {
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, -1, 0, 1};
    static final int EMPTY = 0;
    HashMap<Integer, List<Position>> mustCatch;
    HashSet<Integer> visited;

    int[][] board;
    int r,c;

    public int solution(int[][] board, int r, int c) {
        init(board, r, c);
        return solve();
    }

    void init(int[][] board, int r, int c) {
        this.board = board;
        this.r = r;
        this.c = c;
        visited = new HashSet<>();
        initMustCatch();
    }

    void initMustCatch() {
        mustCatch = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    List<Position> positions = mustCatch.getOrDefault(board[i][j], new ArrayList<>());
                    positions.add(new Position(i, j));
                    mustCatch.put(board[i][j], positions);
                }
            }
        }
    }

    int solve() {
        return dfs(0, r, c, mustCatch, visited);
    }

    int dfs(int buttonPressed, int x, int y, HashMap<Integer, List<Position>> mustCatch, HashSet<Integer> visited) {
        if (visited.size() == mustCatch.size()) {
            return buttonPressed;
        }
        int dfsResult = Integer.MAX_VALUE;
        for (Entry<Integer, List<Position>> catchEntry : mustCatch.entrySet()) {
            Integer type = catchEntry.getKey();
            List<Position> positions = catchEntry.getValue();
            if (visited.contains(type)) {
                continue;
            }
            visited.add(type);
            for (Position p : positions) {
                board[p.x][p.y] = EMPTY;
            }

            MoveResult firstToSecond = determineMovement(x, y, positions.get(0), positions.get(1));
            dfsResult = Math.min(dfs(buttonPressed + firstToSecond.pressed, firstToSecond.p.x, firstToSecond.p.y, mustCatch, visited), dfsResult);

            MoveResult secondToFirst = determineMovement(x, y,  positions.get(1), positions.get(0));
            dfsResult = Math.min(dfs(buttonPressed + secondToFirst.pressed, secondToFirst.p.x, secondToFirst.p.y, mustCatch, visited), dfsResult);

            visited.remove(type);
            for (Position p : positions) {
                board[p.x][p.y] = type;
            }

        }
        return dfsResult;
    }

    MoveResult determineMovement(int x, int y,  Position first, Position second) {
        int pressed = 0;
        pressed += getMinimumButtonPressed(x, y, first.x, first.y);
        pressed += 1;
        pressed += getMinimumButtonPressed(first.x, first.y, second.x, second.y);
        pressed += 1;

        return new MoveResult(second, pressed);
    }


    int getMinimumButtonPressed(int x, int y, int toX, int toY) {
        if (x == toX && y == toY) {
            return 0;
        }
        int distance = 0;
        boolean[][] visited = new boolean[4][4];
        List<Position> currentNodes = Collections.singletonList(new Position(x, y));
        visited[x][y] = true;
        while (!currentNodes.isEmpty()) {
            distance += 1;
            List<Position> temp = new ArrayList<>();
            for (Position current : currentNodes) {
                for (int d = 0; d < 4; d++) {
                    int newX = current.x + DX[d];
                    int newY = current.y + DY[d];
                    if (isInner(newX, newY)) {
                        if (!visited[newX][newY]) {
                            temp.add(new Position(newX, newY));
                            visited[newX][newY] = true;
                        }
                        

                        while (isInner(newX + DX[d], newY + DY[d]) && board[newX][newY] == EMPTY && (newX != toX || newY != toY)) {
                            newX += DX[d];
                            newY += DY[d];
                        }
                        if (!visited[newX][newY]) {
                            temp.add(new Position(newX, newY));
                            visited[newX][newY] = true;
                        }
                    }
                }
            }
            if (visited[toX][toY]) {
                break;
            }
            currentNodes = temp;
        }

        return distance;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }


    static class MoveResult {
        Position p;
        int pressed;

        public MoveResult(Position p, int pressed) {
            this.p = p;
            this.pressed = pressed;
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