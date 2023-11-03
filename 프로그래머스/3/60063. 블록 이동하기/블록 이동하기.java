import java.util.*;

/*
N * N
0,0에서 출발해서 N-1, N-1로 도착해야함,
한칸 이동하거나, 회전할때마다 1초, 최단시간 구해라
*/

class Solution {
    static final int EMPTY = 0;
    static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] DY = {1, 1, 0, -1, -1, -1, 0, 1};
    int[][] board;
    int n;
    HashSet<Robot> visited;
    Position destination;

    public int solution(int[][] board) {
        init(board);
        return solve();
    }

    void init(int[][] board) {
        this.board = board;
        this.n = board.length;
        visited = new HashSet<>();
        destination = new Position(n - 1, n - 1);
    }

    int solve() {
        return bfs();
    }

    int bfs() {
        Robot startRobot = new Robot(new Position(0, 0), new Position(0, 1));
        List<Robot> robots = Collections.singletonList(startRobot);
        int round = 0;
        boolean isEnd = false;
        visited.add(startRobot);
        while (!robots.isEmpty()) {
            List<Robot> temp = new ArrayList<>();
            round += 1;
            for (Robot robot : robots) {
                for (int d = 0; d < 8; d += 2) {
                    Position newTail = new Position(robot.tail.x + DX[d], robot.tail.y + DY[d]);
                    Position newHead = new Position(robot.head.x + DX[d], robot.head.y + DY[d]);
                    Robot newRobot = new Robot(newHead, newTail);
                    if (isPossible(newRobot)) {
                        temp.add(newRobot);
                        visited.add(newRobot);
                        if (isEnd(newRobot)) {
                            isEnd = true;
                            break;
                        }
                    }
                }
                Robot cwRotated, ccwRotated;
                cwRotated = getCWRotated(robot.head, robot.tail);
                if (cwRotated != null && !visited.contains(cwRotated)) {
                    temp.add(cwRotated);
                    visited.add(cwRotated);
                    if (isEnd(cwRotated)) {
                        isEnd = true;
                    }
                }
                cwRotated = getCWRotated(robot.tail, robot.head);
                if (cwRotated != null && !visited.contains(cwRotated)) {
                    temp.add(cwRotated);
                    visited.add(cwRotated);
                    if (isEnd(cwRotated)) {
                        isEnd = true;
                    }
                }

                ccwRotated = getCCWRotated(robot.head, robot.tail);
                if (ccwRotated != null & !visited.contains(ccwRotated)) {
                    temp.add(ccwRotated);
                    visited.add(ccwRotated);
                    if (isEnd(ccwRotated)) {
                        isEnd = true;
                    }
                }
                ccwRotated = getCCWRotated(robot.tail, robot.head);
                if (ccwRotated != null & !visited.contains(ccwRotated)) {
                    temp.add(ccwRotated);
                    visited.add(ccwRotated);
                    if (isEnd(ccwRotated)) {
                        isEnd = true;
                    }
                }

                if (isEnd) {
                    break;
                }
            }
            if (isEnd) {
                break;
            }
            robots = temp;
        }
        return round;
    }

    int getDirection(Position from, Position to) {
        for (int i = 0; i < 8; i += 2) {
            int newX = from.x + DX[i];
            int newY = from.y + DY[i];
            if (newX == to.x && newY == to.y) {
                return i;
            }
        }
        return -1;
    }

    Robot getCWRotated(Position fixed, Position toMove) {
        Robot toReturn = null;
        int cwDirection, newX, newY;
        int robotDirection = getDirection(fixed, toMove);
        cwDirection = getCWDirection(robotDirection);
        newX = fixed.x + DX[cwDirection];
        newY = fixed.y + DY[cwDirection];

        if (canGo(newX, newY)) {
            cwDirection = getCWDirection(cwDirection);
            newX = fixed.x + DX[cwDirection];
            newY = fixed.y + DY[cwDirection];
            if (canGo(newX, newY)) {
                toReturn = new Robot(new Position(newX, newY), fixed);
            }
        }
        return toReturn;

    }

    Robot getCCWRotated(Position fixed, Position toMove) {
        Robot toReturn = null;
        int ccwDirection, newX, newY;
        int robotDirection = getDirection(fixed, toMove);
        ccwDirection = getCCWDirection(robotDirection);
        newX = fixed.x + DX[ccwDirection];
        newY = fixed.y + DY[ccwDirection];

        if (canGo(newX, newY)) {
            ccwDirection = getCCWDirection(ccwDirection);
            newX = fixed.x + DX[ccwDirection];
            newY = fixed.y + DY[ccwDirection];
            if (canGo(newX, newY)) {
                toReturn = new Robot(new Position(newX, newY), fixed);
            }
        }
        return toReturn;
    }

    int getCWDirection(int direction) {
        return (direction + 1) % 8;
    }

    int getCCWDirection(int direction) {
        return (direction + 8 - 1) % 8;
    }

    boolean isEnd(Robot robot) {
        return robot.head.equals(destination) || robot.tail.equals(destination);
    }

    boolean isPossible(Robot robot) {
        return canGo(robot.head) && canGo(robot.tail) && !visited.contains(robot);
    }

    boolean canGo(int x, int y) {
        return isInner(x, y) && board[x][y] == EMPTY;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    boolean canGo(Position position) {
        return isInner(position) && board[position.x][position.y] == EMPTY;
    }

    boolean isInner(Position position) {
        return 0 <= position.x && position.x < n && 0 <= position.y && position.y < n;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return this.x * 100 + this.y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Position) {
                Position p = (Position) obj;
                return this.x == p.x && this.y == p.y;
            }
            return false;
        }
    }

    static class Robot {
        Position head;
        Position tail;

        public Robot(Position head, Position tail) {
            this.head = head;
            this.tail = tail;
        }


        @Override
        public int hashCode() {
            return this.head.hashCode() * 10_000 + this.tail.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Robot) {
                Robot robot = (Robot) obj;
                return this.head.equals(robot.head) && this.tail.equals(robot.tail);
            }
            return false;
        }
    }



}


