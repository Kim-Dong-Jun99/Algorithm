
import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int n;
    Star[] stars;
    int[] parent;
    PriorityQueue<Edge> forKruskal;
    double answer = 0;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("exception during I/O");
        }
    }


    void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        parent = new int[n];
        stars = new Star[n];
        for (int i = 0; i < n; i++) {
            double[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            stars[i] = new Star(inputArray[0], inputArray[1]);
            parent[i] = i;
        }
        forKruskal = new PriorityQueue<>(Edge::compareTo);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double calculatedDistance = calculateDistance(stars[i], stars[j]);
                Edge edge = new Edge(calculatedDistance, i, j);
                forKruskal.add(edge);
            }
        }
    }

    void solution() throws IOException {

        while (!forKruskal.isEmpty()) {
            Edge peek = forKruskal.remove();
            if (find(peek.from) != find(peek.to)) {
                answer += peek.value;
                union(peek.from, peek.to);
            }

        }
        System.out.println(answer);
    }

    static class Star {
        double x;
        double y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    double calculateDistance(Star a, Star b) {
        double xDiff = a.x - b.x;
        double yDiff = a.y - b.y;
        return Math.pow((Math.pow(xDiff, 2) + Math.pow(yDiff, 2)), 0.5);
    }

    static class Edge {
        double value;
        int from;
        int to;

        public Edge(double value, int from, int to) {
            this.value = value;
            this.from = from;
            this.to = to;
        }

        public int compareTo(Edge compare) {
            return Double.compare(this.value, compare.value);
        }
    }


    public int find(int index) {
        if (parent[index] == index) {
            return parent[index];
        }
        parent[index] = find(parent[index]);
        return parent[index];
    }

    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            parent[px] = py;
        }
    }
}