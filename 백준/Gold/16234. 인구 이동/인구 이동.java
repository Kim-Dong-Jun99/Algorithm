import java.util.*;
import java.io.*;


public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    int[] inputArray;
    int N, L, R;
    int[][] population;
    boolean[][] visited;
    int days;

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
        L = inputArray[1];
        R = inputArray[2];

        population = new int[N][N];

        for (int i = 0; i < N; i++) {
            population[i] = getInputArray();
        }
        days = 0;
    }


    void solution() throws IOException {
        while (true) {
            visited = new boolean[N][N];
            List<Neighbor> unions = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;

                        Neighbor neighbors = getNeighbors(i, j);

                        if (neighbors.cities.size() > 1) {
                            unions.add(neighbors);
                        }

                    }
                }
            }
            if (unions.isEmpty()) {
                break;
            }
            updateUnion(unions);
            days += 1;
        }

        System.out.println(days);
    }

    Neighbor getNeighbors(int x, int y) {
        List<Position> cities = new ArrayList<>();
        int populationSum = 0;
        List<Position> current = Arrays.asList(new Position(x, y));
        while (!current.isEmpty()) {
            List<Position> temp = new ArrayList<>();
            for (Position position : current) {
                cities.add(position);
                populationSum += population[position.x][position.y];
                for (int i = 0; i < 4; i++) {
                    if (canGo(position.x, position.y, i)) {
                        visited[position.x + DX[i]][position.y + DY[i]] = true;
                        temp.add(new Position(position.x + DX[i], position.y + DY[i]));
                    }
                }
            }
            current = temp;
        }

        return new Neighbor(cities, populationSum);

    }

    void updateUnion(List<Neighbor> neighbors) {
        for (Neighbor neighbor : neighbors) {
            int newPopulation = neighbor.populationSum / neighbor.cities.size();
            for (Position city : neighbor.cities) {
                population[city.x][city.y] = newPopulation;
            }
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

    static class Neighbor {
        List<Position> cities;
        int populationSum;

        public Neighbor(List<Position> cities, int populationSum) {
            this.cities = cities;
            this.populationSum = populationSum;
        }
    }


    boolean canGo(int x, int y, int direction) {
        int newX = x + DX[direction];
        int newY = y + DY[direction];
        if (!isInner(newX, newY)) {
            return false;
        }
        int populationDiff = Math.abs(population[newX][newY] - population[x][y]);
        return L <= populationDiff && populationDiff <= R && !visited[newX][newY];
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}