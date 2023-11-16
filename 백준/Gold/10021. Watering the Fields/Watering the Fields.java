import java.util.*;
import java.io.*;

/*
N개의 필드가 있다,
각 필드는 x,y 좌표를 가지고, 두 필드 사이 파이프를 만드는 비용은 두 필드간 유클리드 거리임

모든 필드가 연결되게 파이프를 만드는 최소 비용을 구하고 싶음,

이때 파이프의 각 비용은 C 이상이어야함 최소 비용을 출력하거나, 건설할 수 없음 -1


 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int[] inputArray;
    int N, C;

    Position[] positions;
    PriorityQueue<Road> roads;
    int[] parent;
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
        N = inputArray[0];
        C = inputArray[1];
        positions = new Position[N];
        roads = new PriorityQueue<>(Road::compareWithValue);
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;

            inputArray = getInputArray();
            positions[i] = new Position(i, inputArray[0], inputArray[1]);
        }
        initRoads();
    }

    private void initRoads() {
        for (int i = 0; i < N; i++) {
            for (int to = i + 1; to < N; to++) {
                int distance = calculateDistance(positions[i], positions[to]);
                if (distance >= C) {
                    roads.add(new Road(i, to, distance));
                }
            }
        }
    }

    private int calculateDistance(Position position, Position to) {
        int dx = position.x - to.x;
        int dy = position.y - to.y;
        return dx * dx + dy * dy;
    }

    int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[py] = px;
        }
    }


    void solution() {
        int result = 0;
        HashSet<Integer> visited = new HashSet<>();
        while (!roads.isEmpty()) {
            Road road = roads.remove();
            if (find(road.to) != find(road.from)) {
                union(road.to, road.from);
                result += road.value;
                visited.add(road.to);
                visited.add(road.from);
            }
        }
        if (visited.size() == N) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    static class Position {
        int index;
        int x;
        int y;

        public Position(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    static class Road {
        int from;
        int to;
        int value;

        public Road(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        int compareWithValue(Road compare) {
            return Integer.compare(this.value, compare.value);
        }
    }

}