import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, -1, 0, 1};
    static final int[] DY = {1, 0, -1, 0};

    int[] inputArray;
    int N, T;
    int[][][] signals;
    boolean[][][][] visited;
    int time;
    boolean[][] unique;
    int answer;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        T = inputArray[1];

        signals = new int[N][N][4];
        visited = new boolean[N][N][4][4];
        unique = new boolean[N][N];
        answer = 0;
        time = 0;
        for (int i = 0; i < N*N; i++) {
            int x = i / N;
            int y = i % N;
            signals[x][y] = getInputArray();
            for (int d = 0; d < 4; d++) {
                signals[x][y][d] -= 1;
            }
        }
    }

    void solve() {
        List<Car> current = new ArrayList<>();
        visited[0][0][0][1] = true;
        current.add(new Car(0,0, 1));
        while (time <= T && !current.isEmpty()) {
            List<Car> temp = new ArrayList<>();
            for (Car c : current) {
                if (!unique[c.x][c.y]) {
                    unique[c.x][c.y] = true;
                    answer += 1;
                }
                int signal = signals[c.x][c.y][time % 4];
                int d = signal % 4;
                if (c.d != d) {
                    continue;
                }
                if (isInner(c.x+DX[d], c.y+DY[d]) && !visited[c.x+DX[d]][c.y+DY[d]][(time+1) % 4][d]) {
                    visited[c.x+DX[d]][c.y+DY[d]][(time+1) % 4][d] = true;
                    temp.add(new Car(c.x+DX[d], c.y+DY[d], d));
                }
                if (canGoLeft(signal)) {
                    int left = (d + 1) % 4;
                    if (isInner(c.x+DX[left], c.y+DY[left]) && !visited[c.x+DX[left]][c.y+DY[left]][(time+1) % 4][left]) {
                        visited[c.x+DX[left]][c.y+DY[left]][(time+1) % 4][left] = true;
                        temp.add(new Car(c.x+DX[left], c.y+DY[left], left));
                    }
                }
                if (canGoRight(signal)) {
                    int right = (d + 3) % 4;
                    if (isInner(c.x+DX[right], c.y+DY[right]) && !visited[c.x+DX[right]][c.y+DY[right]][(time+1) % 4][right]) {
                        visited[c.x+DX[right]][c.y+DY[right]][(time+1) % 4][right] = true;
                        temp.add(new Car(c.x+DX[right], c.y+DY[right], right));
                    }
                }
            }
            current = temp;
            time += 1;
        }
        System.out.println(answer);
    }

    boolean canGoLeft(int signal) {
        return signal / 4 <= 1;
    }

    boolean canGoRight(int signal) {
        return signal / 4 == 0 || signal / 4 == 2;
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Car {
        int x;
        int y;
        int d;
        Car(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
