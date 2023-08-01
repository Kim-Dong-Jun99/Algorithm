import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    SpaceShip[] spaceShips;
    int[] parent;
    double answer;
    PriorityQueue<Path> paths;
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
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        spaceShips = new SpaceShip[N + 1];
        parent = IntStream.rangeClosed(0, N).toArray();
        for (int i = 0; i < N; i++) {
            int[] tempInputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            spaceShips[i + 1] = new SpaceShip(tempInputArray[0], tempInputArray[1]);
        }
        for (int i = 0; i < M; i++) {
            int[] tempInputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            union(tempInputArray[0], tempInputArray[1]);
        }
        answer = 0;
        paths = new PriorityQueue<>(Path::compareTo);
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                paths.add(new Path(i, j, getDistance(spaceShips[i], spaceShips[j])));
            }
        }
    }

    void solution() throws IOException {
        while (!paths.isEmpty()) {
            Path removed = paths.remove();
            if (find(removed.from) != find(removed.to)) {
                union(removed.from, removed.to);
                answer += removed.value;
            }
        }
        String toPrint = String.format("%.2f", answer);
        System.out.println(toPrint);
    }

    int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[px] = py;
        }
    }

    double getDistance(SpaceShip i, SpaceShip j) {
        return Math.pow((Math.pow(i.x - j.x, 2) + Math.pow(i.y - j.y, 2)), 0.5);
    }

    static class SpaceShip {
        int x;
        int y;

        public SpaceShip(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Path {
        int from;
        int to;
        double value;

        public Path(int from, int to, double value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        public int compareTo(Path compare) {
            return Double.compare(this.value, compare.value);
        }
    }

}
