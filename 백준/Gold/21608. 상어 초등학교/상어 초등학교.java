import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    int[] inputArray;
    int N;
    int[][] board;
    int[][] favor;
    int satisfaction;
    HashMap<Integer, HashSet<Integer>> likeMap;
    HashSet<Integer> likes;
    PriorityQueue<Possible> heap;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        board = new int[N][N];
        favor = new int[N * N][5];
        likeMap = new HashMap<>();
        satisfaction = 0;
        for (int i = 0; i < N * N; i++) {
            favor[i] = getInputArray();
        }
    }

    void solve() throws IOException {
        fillBoard();
        calculateSatisfaction();
    }

    void fillBoard() {
        for (int[] f : favor) {
            likes = initLikes(f);
            likeMap.put(f[0], likes);
            heap = new PriorityQueue<>(Possible::sort);
            addPossible();
            Possible removed = heap.remove();
            board[removed.x][removed.y] = f[0];
        }
    }

    void addPossible() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    addPossibleToHeap(i, j);
                }
            }
        }
    }

    void addPossibleToHeap(int x, int y) {
        int fav = 0;
        int empty = 0;
        for (int d = 0; d < 4; d++) {
            int newX = x + DX[d];
            int newY = y + DY[d];
            if (isInner(newX, newY)) {
                if (likes.contains(board[newX][newY])) {
                    fav += 1;
                }
                if (board[newX][newY] == 0) {
                    empty += 1;
                }
            }
        }
        heap.add(new Possible(fav, empty, x, y));
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    HashSet<Integer> initLikes(int[] f) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i < 5; i++) {
            set.add(f[i]);
        }
        return set;
    }

    void calculateSatisfaction() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                satisfaction += getSatisfaction(i, j);
            }
        }
        BW.write(Integer.toString(satisfaction));
    }

    int getSatisfaction(int x, int y) {
        int like = 0;
        for (int d = 0; d < 4; d++) {
            int newX = x + DX[d];
            int newY = y + DY[d];
            if (isInner(newX, newY) && likeMap.get(board[x][y]).contains(board[newX][newY])) {
                like += 1;
            }
        }
        if (like == 0) {
            return 0;
        }
        return (int) Math.pow(10, like - 1);
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }

    static class Possible {
        int fav;
        int empty;
        int x;
        int y;

        Possible(int fav, int empty, int x, int y) {
            this.fav = fav;
            this.empty = empty;
            this.x = x;
            this.y = y;
        }

        int sort(Possible compare) {
            if (this.fav != compare.fav) {
                return Integer.compare(compare.fav, this.fav);
            }
            if (this.empty != compare.empty) {
                return Integer.compare(compare.empty, this.empty);
            }
            if (this.x != compare.x) {
                return Integer.compare(this.x, compare.x);
            }
            return Integer.compare(this.y, compare.y);
        }
    }
}