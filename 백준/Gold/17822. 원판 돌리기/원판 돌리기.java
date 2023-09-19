import java.util.*;
import java.io.*;
import java.util.stream.Stream;


class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int CLOCKWISE = 0;
    int[] inputArray;
    int N, M, T;
    int[][] circles;
    Spin[] spins;
    int totalSum;
    int totalCount;

    public static void main(String[] args) {

        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    Stream<String> getInputStream() throws IOException {
        return Arrays.stream(BR.readLine().split(" "));
    }

    void init() throws IOException {
        inputArray = getInputStream().mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        T = inputArray[2];

        circles = new int[N + 1][M + 1];

        totalSum = 0;
        totalCount = N * M;

        for (int i = 1; i <= N; i++) {
            inputArray = getInputStream().mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= M; j++) {
                circles[i][j] = inputArray[j - 1];
                totalSum += circles[i][j];
            }
        }

        spins = new Spin[T];

        for (int i = 0; i < T; i++) {
            inputArray = getInputStream().mapToInt(Integer::parseInt).toArray();
            spins[i] = new Spin(inputArray[0], inputArray[1], inputArray[2]);
        }

    }

    void solution() throws IOException {
        for (Spin spin : spins) {
//            System.out.println("before spinning");
//            for (int i = 1; i <= N; i++) {
//                for (int j = 1; j <= M; j++) {
//                    System.out.print(circles[i][j] + " ");
//                }
//                System.out.println();
//            }

            int x = spin.x;
            while (x <= N) {
                int[] removed = new int[spin.k];
                int index;
                if (spin.direction == CLOCKWISE) {
                    index = 0;
                    while (index < spin.k) {
                        removed[index] = circles[x][M - index];
                        index += 1;
                    }
                    index = M;
                    while (index - spin.k >= 1) {
                        circles[x][index] = circles[x][index - spin.k];
                        index -= 1;
                    }
                    index = 0;
                    while (index < spin.k) {
                        circles[x][index + 1] = removed[spin.k - 1 - index];
                        index += 1;
                    }
                } else {
                    index = 0;
                    while (index < spin.k) {
                        removed[index] = circles[x][index + 1];
                        index += 1;
                    }
                    index = 1;
                    while (index + spin.k <= M) {
                        circles[x][index] = circles[x][index + spin.k];
                        index += 1;
                    }

                    index = 0;
                    while (index < spin.k) {
                        circles[x][M - index] = removed[spin.k - 1 - index];
                        index += 1;
                    }
                }

                x += spin.x;
            }

//            System.out.println("after spinning");
//            for (int i = 1; i <= N; i++) {
//                for (int j = 1; j <= M; j++) {
//                    System.out.print(circles[i][j] + " ");
//                }
//                System.out.println();
//            }

            boolean globalFind = false;
            List<Position> toRemove = new ArrayList<>();
            boolean[][] removed = new boolean[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (circles[i][j] != 0) {
                        List<Position> neighbors = getNeighbors(i, j);
                        boolean localFind = false;
                        for (Position neighbor : neighbors) {
                            if (circles[i][j] == circles[neighbor.x][neighbor.y]) {
                                if (!removed[neighbor.x][neighbor.y]) {
                                    removed[neighbor.x][neighbor.y] = true;
                                    toRemove.add(neighbor);
                                }
                                localFind = true;
                                globalFind = true;
                            }
                        }
                        if (localFind) {
                            if (!removed[i][j]) {
                                removed[i][j] = true;
                                toRemove.add(new Position(i, j));
                            }
                        }
                    }
                }
            }

            for (Position position : toRemove) {
                totalSum -= circles[position.x][position.y];
                totalCount -= 1;
                circles[position.x][position.y] = 0;
            }

            if (!globalFind) {
                double mean = totalSum / (double) totalCount;
//                System.out.println("totalSum = " + totalSum);
//                System.out.println("totalCount = " + totalCount);
//                System.out.println("mean = " + mean);
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= M; j++) {
                        if (circles[i][j] != 0) {
                            if (circles[i][j] > mean) {
                                circles[i][j] -= 1;
                                totalSum -= 1;
                            } else if (circles[i][j] < mean){
                                circles[i][j] += 1;
                                totalSum += 1;
                            }
                        }
                    }
                }
            }
//            System.out.println("after removing");
//            for (int i = 1; i <= N; i++) {
//                for (int j = 1; j <= M; j++) {
//                    System.out.print(circles[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }


        System.out.println(totalSum);
    }

    static class Spin {
        int x;
        int direction;
        int k;

        public Spin(int x, int direction, int k) {
            this.x = x;
            this.direction = direction;
            this.k = k;
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

    List<Position> getNeighbors(int x, int y) {
        List<Position> neighbors = new ArrayList<>();
        if (y == 1) {
            neighbors.add(new Position(x, 2));
            neighbors.add(new Position(x, M));
        }
        if (y == M) {
            neighbors.add(new Position(x, M - 1));
            neighbors.add(new Position(x, 1));
        }
        if (2 <= y && y <= M - 1) {
            neighbors.add(new Position(x, y - 1));
            neighbors.add(new Position(x, y + 1));
        }
        if (x == 1) {
            neighbors.add(new Position(2, y));
        }
        if (x == N) {
            neighbors.add(new Position(N-1, y));
        }
        if (2 <= x && x <= N - 1) {
            neighbors.add(new Position(x - 1, y));
            neighbors.add(new Position(x + 1, y));
        }
        return neighbors;
    }
}