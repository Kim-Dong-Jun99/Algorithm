import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int[][] city;
    int[][] border;
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
        N = Integer.parseInt(BR.readLine());
        city = new int[N][N];
        for (int i = 0; i < N; i++) {
            city[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        answer = Integer.MAX_VALUE;
    }

    void solution() throws IOException {
        for (int x = 0; x < N; x++) {
            for (int y = 1; y < N-1; y++) {
                int maxD1 = y;
                int maxD2 = N - 1 - y;
                for (int d1 = 1; d1 <= maxD1; d1++) {
                    for (int d2 = 1; d2 <= maxD2; d2++) {
                        if (canCalculate(x, y, d1, d2)) {
                            border = new int[N][N];

                            border[x][y] = 1;
                            border[x + d1][y - d1] = 1;
                            border[x + d2][y + d2] = 1;
                            border[x + d1 + d2][y - d1 + d2] = 1;

                            markBorder(x, y, x + d1, y - d1);
                            markBorder(x, y, x + d2, y + d2);
                            markBorder(x + d1, y - d1, x + d1 + d2, y - d1 + d2);
                            markBorder(x + d2, y + d2, x + d1 + d2, y - d1 + d2);

                            colorInside(x, x + d1 + d2);


//                            System.out.println(x+" "+y+" "+d1+" "+d2);
                            int[] population = new int[5];
                            for (int i = 0; i < N; i++) {
                                for (int j = 0; j < N; j++) {
                                    if (isInner(i, j)) {
//                                        System.out.print("5 ");
                                        population[4] += city[i][j];
                                    } else if (i < x + d1 && j <= y) {
//                                        System.out.print("1 ");
                                        population[0] += city[i][j];
                                    } else if (i <= x + d2 && y < j) {
//                                        System.out.print("2 ");
                                        population[1] += city[i][j];
                                    } else if (x + d1 <= i && j < y - d1 + d2) {
//                                        System.out.print("3 ");
                                        population[2] += city[i][j];
                                    } else if (x + d2 < i && y - d1 + d2 <= j) {
//                                        System.out.print("4 ");
                                        population[3] += city[i][j];
                                    }
                                }
//                                System.out.println();
                            }

//                            System.out.println();
                            Arrays.sort(population);
                            answer = Math.min(answer, population[4] - population[0]);
                        }
                    }
                }
            }
        }
        System.out.println(answer);


    }

    void markBorder(int fromX, int fromY, int toX, int toY) {
        int xDiff, yDiff;
        xDiff = 1;
        if (toY - fromY > 0) {
            yDiff = 1;
        } else {
            yDiff = -1;
        }
        fromX += xDiff;
        fromY += yDiff;
        while (fromX != toX && fromY != toY) {
            border[fromX][fromY] = 1;
            fromX += xDiff;
            fromY += yDiff;
        }
    }

    void colorInside(int bottom, int top) {
        for (int i = 0; i < N; i++) {
            if (i == bottom || i == top) {
                continue;
            }
            boolean meetBorder = false;
            for (int j = 0; j < N; j++) {
                if (border[i][j] == 1) {
                    if (meetBorder) {
                        break;
                    }
                    meetBorder = true;
                }
                if (meetBorder) {
                    border[i][j] = 1;
                }
            }
        }
    }

    boolean isInner(int i, int j) {
        return border[i][j] == 1;
    }

    boolean canCalculate(int x, int y, int d1, int d2) {
        return x + d1 + d2 < N;
    }


}