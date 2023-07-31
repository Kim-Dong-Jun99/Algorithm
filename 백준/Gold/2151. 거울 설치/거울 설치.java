import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final Character DOOR = '#';
    static final Character MIRROR = '!';
    static final Character WALL = '*';

    int N;
    String[] home;
    int[][] dijkstra;
    List<Coordinate> doors;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    HashMap<Integer, int[]> reflect;

    PriorityQueue<Coordinate> toSearch;
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
        N = Integer.parseInt(BR.readLine());
        home = new String[N];
        dijkstra = new int[N][N];
        toSearch = new PriorityQueue<>(Coordinate::compareTo);
        doors = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            home[i] = BR.readLine();
            for (int j = 0; j < N; j++) {
                if (home[i].charAt(j) == DOOR) {
//                    System.out.println("door x= " + i + " y=" + j);
                    doors.add(new Coordinate(i, j, 0, new int[]{0, 1, 2, 3}));
                }
            }
            Arrays.fill(dijkstra[i], Integer.MAX_VALUE);
        }
        reflect = new HashMap<>();
        initReflect();
    }

    void initReflect() {
        reflect.put(0, new int[]{1, 3});
        reflect.put(1, new int[]{0, 2});
        reflect.put(2, new int[]{1, 3});
        reflect.put(3, new int[]{0, 2});
    }

    void solution() throws IOException {
        dijkstra[doors.get(0).x][doors.get(0).y] = 0;
        for (int i = 0; i < 4; i++) {
            int newX = doors.get(0).x + dx[i];
            int newY =doors.get(0).y + dy[i];
            while (isInner(newX, newY)) {
//                System.out.println("loc x= " + newX + " y=" + newY);

                if (home[newX].charAt(newY) == WALL) {
                    break;
                }
                if (home[newX].charAt(newY) == MIRROR) {
//                    System.out.println("mirror x= " + newX + " y=" + newY);

                    dijkstra[newX][newY] = 0;
                    toSearch.add(new Coordinate(newX, newY, 0, reflect.get(i)));
                }
                if (home[newX].charAt(newY) == DOOR) {
                    System.out.println(0);
                    return;
                }
                newX += dx[i];
                newY += dy[i];
            }
        }
        while (!toSearch.isEmpty()) {
            Coordinate current = toSearch.remove();
            if (dijkstra[current.x][current.y] < current.mirrorCount) {
                continue;
            }
            for (int i : current.canGo) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];
                while (isInner(newX, newY)) {
                    if (home[newX].charAt(newY) == WALL) {
                        break;
                    }
                    if (home[newX].charAt(newY) == MIRROR) {
                        if (dijkstra[newX][newY] > dijkstra[current.x][current.y] + 1) {
                            dijkstra[newX][newY] = dijkstra[current.x][current.y] + 1;
                            toSearch.add(new Coordinate(newX, newY, dijkstra[newX][newY], reflect.get(i)));
                        }
                    }
                    if (home[newX].charAt(newY) == DOOR) {
                        dijkstra[newX][newY] = Math.min(dijkstra[newX][newY], dijkstra[current.x][current.y] + 1);
                    }
                    newX += dx[i];
                    newY += dy[i];
                }
            }
        }
        System.out.println(dijkstra[doors.get(1).x][doors.get(1).y]);
    }

    boolean isInner(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }

    static class Coordinate {
        int x;
        int y;
        int mirrorCount;
        int[] canGo;
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Coordinate) {
                Coordinate compare = (Coordinate) obj;
                return this.x == compare.x && this.y == compare.y;
            }
            return false;
        }

        public int compareTo(Coordinate compare) {
            return Integer.compare(this.mirrorCount, compare.mirrorCount);
        }

        public Coordinate(int x, int y, int mirrorCount, int[] canGo) {
            this.x = x;
            this.y = y;
            this.mirrorCount = mirrorCount;
            this.canGo = canGo;
        }
    }


}
