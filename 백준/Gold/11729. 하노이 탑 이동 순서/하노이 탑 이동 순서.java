import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N;
    int answer;
    int[] position;
    List<Result> results;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        position = new int[N+1];
        Arrays.fill(position, 1);
        results = new ArrayList<>();
        answer = 0;
    }

    void solve() throws IOException {
        move(N, 3);
        BW.write(answer + "\n");
        for (Result result : results) {
            BW.write(result.from + " " + result.to);
            BW.newLine();
        }
        BW.flush();
        BW.close();
        BR.close();
    }

    void move(int toMove, int to) throws IOException{
        int from = position[toMove];
        int empty = getEmpty(from, to);
        if (toMove > 1) {
            move(toMove-1, empty);
        }
        answer += 1;
        results.add(new Result(from, to));
        position[toMove] = to;
        if (toMove > 1) {
            move(toMove-1, to);
        }
    }

    int getEmpty(int from, int to) {
        if (from == 1) {
            if (to == 2) {
                return 3;
            } else {
                return 2;
            }
        } else if(from == 2) {
            if (to == 1) {
                return 3;
            } else {
                return 1;
            }
        } else {
            if (to == 1) {
                return 2;
            } else {
                return 1;
            }
        }
    }

    static class Result {
        int from;
        int to;

        Result(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}