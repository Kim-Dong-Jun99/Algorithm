import java.util.*;

/*
N*N 크기의 정사각형 격자 형태이며, 격자는 1 * 1 크기이다.
0 - 비어있음
1 - 채워져있음

0,0 에서 출발해서 N-1, N-1로 가야함

상하좌우로 인접한 두 빈칸을 연결하여 건설할 수 있으며, 벽이 있는 칸에는 경주로를 건설할 수 없음

직선 도로 = 100, 코너 = 500 비용 발생

N <= 25

dijkstra

*/

class Solution {
    static final int MAX_VALUE = 1_000_000;
    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, -1, 0, 1};
    static final int[] OPPOSITE = {2, 3, 0, 1};
    static final int EMPTY = 0;
    int[][][] dijkstra;
    int n;
    int[][] board;

    public int solution(int[][] board) {
        init(board);
        return solve();
    }

    void init(int[][] board) {
        this.board = board;
        n = board.length;
        dijkstra = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dijkstra[i][j], MAX_VALUE);
            }
        }

    }

    int solve() {
        PriorityQueue<DijkstraNode> heap = new PriorityQueue<>(DijkstraNode::compareWithValue);
        heap.add(new DijkstraNode(new Position(0, 0), 0, 0));
        
        while (!heap.isEmpty()) {
            DijkstraNode node = heap.remove();
            if (dijkstra[node.to.x][node.to.y][node.toDirection] < node.value) {
                continue;
            }
            if (node.to.x == 0 && node.to.y == 0) {
                Arrays.fill(dijkstra[0][0], 0);
                for (int d = 0; d < 4; d++) {
                    int newX = node.to.x + DX[d];
                    int newY = node.to.y + DY[d];

                    if (canGo(newX, newY) && dijkstra[newX][newY][d] > node.value + 100) {
                        heap.add(new DijkstraNode(new Position(newX, newY), d, node.value + 100));
                    }
                }
            } else {
                dijkstra[node.to.x][node.to.y][node.toDirection] = node.value;
                for (int d = 0; d < 4; d++) {
                    int newX = node.to.x + DX[d];
                    int newY = node.to.y + DY[d];
                    int newValue = node.value + 100;
                    if (isCorner(node.toDirection, d)) {
                        newValue += 500;
                    }
                    

                    if (canGo(newX, newY) && dijkstra[newX][newY][d] >newValue) {
                        heap.add(new DijkstraNode(new Position(newX, newY), d, newValue));
                    }
                }
            }
        }
        return Arrays.stream(dijkstra[n - 1][n - 1]).min().getAsInt();
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    boolean canGo(int x, int y) {
        return isInner(x, y) && board[x][y] == EMPTY;
    }

    boolean isCorner(int pastDirection, int nextDirection) {
        int pastMod = pastDirection % 2;
        int nextMod = nextDirection % 2;

        return pastMod != nextMod;
    }


    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    

    static class DijkstraNode {
        Position to;
        int toDirection;
        int value;

        public DijkstraNode(Position to, int toDirection, int value) {
            this.to = to;
            this.toDirection = toDirection;
            this.value = value;
        }

        int compareWithValue(DijkstraNode compare) {
            return Integer.compare(this.value, compare.value);
        }
    }




}


