import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final String IN = "In";
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    int[] inputArray;
    int N, M, Q;
    String[][] cmds;
    boolean[][] seated;
    HashMap<Integer, Position> seatedMap;
    HashSet<Integer> leaved;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        M = inputArray[1];
        Q = inputArray[2];

        seatedMap = new HashMap<>();
        leaved = new HashSet<>();
        seated = new boolean[N][M];

        cmds = new String[Q][2];
        for (int i = 0; i < Q; i++) {
            cmds[i] = BR.readLine().split(" ");
        }
    }

    void solve() {
        StringBuilder sb = new StringBuilder();
        for (String[] cmd : cmds) {
            int id = Integer.parseInt(cmd[1]);
            if (cmd[0].equals(IN)) {
                if (seatedMap.containsKey(id)) {
                    sb.append(Integer.toString(id));
                    sb.append(" already seated.\n");
                    continue;
                }
                if (leaved.contains(id)) {
                    sb.append(Integer.toString(id));
                    sb.append(" already ate lunch.\n");
                    continue;
                }
                Position p = getPosition();
                if (p == null) {
                    sb.append("There are no more seats.\n");
                } else {
                    sb.append(Integer.toString(id));
                    sb.append(" gets the seat (");
                    sb.append(Integer.toString(p.x+1));
                    sb.append(", ");
                    sb.append(Integer.toString(p.y+1));
                    sb.append(").\n");
                    seatedMap.put(id, p);
                    seated[p.x][p.y] = true;
                }
            } else {
                if (leaved.contains(id)) {
                    sb.append(Integer.toString(id));
                    sb.append(" already left seat.\n");
                    continue;
                }
                if (!seatedMap.containsKey(id)) {
                    sb.append(Integer.toString(id));
                    sb.append(" didn't eat lunch.\n");
                    continue;
                }
                Position p = seatedMap.get(id);
                sb.append(Integer.toString(id));
                sb.append( " leaves from the seat (");
                sb.append(Integer.toString(p.x+1));
                sb.append(", ");
                sb.append(Integer.toString(p.y+1));
                sb.append(").\n");
                seatedMap.remove(id);
                leaved.add(id);
                seated[p.x][p.y] = false;
            }
        }
        System.out.print(sb.toString());
    }

    Position getPosition() {
        if (seatedMap.isEmpty()) {
            return new Position(0, 0, 0);
        }
        PriorityQueue<Position> heap = new PriorityQueue<>(Position::sort);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!seated[i][j] && notNeighbor(i, j)) {
                    int c = Integer.MAX_VALUE;
                    for (Position p : seatedMap.values()) {
                        c = Math.min(c, (int) Math.pow(p.x - i, 2) + (int) Math.pow(p.y - j, 2));
                    }
                    heap.add(new Position(i, j, c));
                }
            }
        }
        return heap.poll();
    }

    boolean notNeighbor(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int newX = x + DX[d];
            int newY = y + DY[d];
            if (isInner(newX, newY) && seated[newX][newY]) {
                return false;
            }
        }
        return true;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Position {
        int x;
        int y;
        int c;

        Position(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        int sort(Position compare) {
            if (this.c != compare.c) {
                return Integer.compare(compare.c, this.c);
            }
            if (this.x != compare.x) {
                return Integer.compare(this.x, compare.x);
            }
            return Integer.compare(this.y, compare.y);
        }
    }
}
