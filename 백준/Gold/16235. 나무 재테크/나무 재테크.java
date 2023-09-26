import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 0, 0, 1, 1, 1, -1, -1};
    static final int[] DY = {0, -1, 1, 0, 1, -1, 1, -1};
    int[] inputArray;
    int N, M, K;
    int[][] givenEnergy;
    int[][] treeEnergy;
    PriorityQueue<Integer>[][] treeBoard;
    PriorityQueue<Integer>[][] newTreeBoard;
    int year;
    List<Position> breedingTrees;

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
        M = inputArray[1];
        K = inputArray[2];

        givenEnergy = new int[N][N];
        treeEnergy = new int[N][N];
        for (int i = 0; i < N; i++) {
            givenEnergy[i] = getInputArray();
        }

        treeBoard = new PriorityQueue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                treeBoard[i][j] = new PriorityQueue<>();
            }
        }

        for (int i = 0; i < M; i++) {
            inputArray = getInputArray();
            treeBoard[inputArray[0] - 1][inputArray[1] - 1].add(inputArray[2]);
        }

        year = 0;
    }


    void solution() throws IOException {
        while (year < K) {
            springAndSummer();
            fall();
            winter();
        }
        printResult();

    }

    void printResult() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += treeBoard[i][j].size();
            }
        }
        System.out.println(answer);
    }


    void springAndSummer() {
        newTreeBoard = new PriorityQueue[N][N];
        breedingTrees = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> newTrees = new PriorityQueue<>();
                if (!treeBoard[i][j].isEmpty()) {
                    int remainingEnergy = 5 + givenEnergy[i][j] * year - treeEnergy[i][j];
                    PriorityQueue<Integer> trees = treeBoard[i][j];

                    while (!trees.isEmpty()) {
                        int tree = trees.remove();
                        if (remainingEnergy - tree >= 0) {
                            remainingEnergy -= tree;
                            treeEnergy[i][j] += tree;
                            if ((tree + 1) % 5 == 0) {
                                breedingTrees.add(new Position(i, j));
                            }
                            newTrees.add(tree + 1);
                        } else {
                            treeEnergy[i][j] -= tree / 2;
                        }
                    }
                }
                newTreeBoard[i][j] = newTrees;
            }
        }
        treeBoard = newTreeBoard;
    }


    void fall() {
        for (Position tree : breedingTrees) {
            for (int i = 0; i < 8; i++) {
                int newX = tree.x + DX[i];
                int newY = tree.y + DY[i];
                if (isInner(newX, newY)) {
                    treeBoard[newX][newY].add(1);
                }
            }
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    void winter() {
        year += 1;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}