import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int p;
    int w;
    int c;
    int v;
    int[] inputArray;
    Road[] roads;
    int[] parent;
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
        p = inputArray[0];
        w = inputArray[1];
        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        c = inputArray[0];
        v = inputArray[1];
        roads = new Road[w];
        parent = IntStream.range(0, p).toArray();
        for (int i = 0; i < w; i++) {
            inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            roads[i] = new Road(inputArray[0], inputArray[1], inputArray[2]);
        }
        Arrays.sort(roads, Road::compareWithValueDesc);

    }


    void solution() throws IOException {
        for (Road road : roads) {
//            System.out.println(road.value+" "+road.start+" "+road.end);
            union(road.start, road.end);
            if (find(c) == find(v)) {
                System.out.println(road.value);
                return;
            }
        }

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
            parent[py] = px;
        }
    }

    static class Road {
        int start;
        int end;
        int value;

        public Road(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        public int compareWithValueDesc(Road compare) {
            return Integer.compare(compare.value, this.value);
        }
    }

}

