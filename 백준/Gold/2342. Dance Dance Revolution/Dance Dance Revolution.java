import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] OPPOSITE = {0, 3, 4, 1, 2};
    int[] commands;
    int[][][] dp;
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
        commands = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[commands.length][5][5];
        for (int i = 0; i < commands.length; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        answer = Integer.MAX_VALUE;
    }

    void solution() throws IOException {
        dp[0][0][0] = 0;
        for (int index = 0; index < commands.length-1; index++) {
            for (int i = 0; i < 5; i++) {
                for (int j = i; j < 5; j++) {
                    if (dp[index][i][j] != Integer.MAX_VALUE) {
//                        System.out.println(i + " " + j + " " + dp[index][i][j]);
                        int iDistance = calculateDistance(i, commands[index]);
                        int jDistance = calculateDistance(j, commands[index]);
                        int newI = Math.min(j, commands[index]);
                        int newJ = Math.max(j, commands[index]);
                        dp[index + 1][newI][newJ] = Math.min(iDistance + dp[index][i][j], dp[index + 1][newI][newJ]);

                        newI = Math.min(i, commands[index]);
                        newJ = Math.max(i, commands[index]);
                        dp[index + 1][newI][newJ] = Math.min(jDistance + dp[index][i][j], dp[index + 1][newI][newJ]);
//                        if (iDistance < jDistance) {
//                            int newI = Math.min(j, commands[index]);
//                            int newJ = Math.max(j, commands[index]);
//                            dp[index + 1][newI][newJ] = Math.min(iDistance + dp[index][i][j], dp[index + 1][newI][newJ]);
//                        } else if (iDistance > jDistance) {
//                            int newI = Math.min(i, commands[index]);
//                            int newJ = Math.max(i, commands[index]);
//                            dp[index + 1][newI][newJ] = Math.min(jDistance + dp[index][i][j], dp[index + 1][newI][newJ]);
//                        } else {
//
//                        }
                    }
                }
            }
//            System.out.println();
        }
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                if (dp[commands.length-1][i][j] < answer) {
                    answer = dp[commands.length-1][i][j];
                }
            }
        }
        System.out.println(answer);
    }

    int calculateDistance(int i, int j) {
        if (i == j) {
            return 1;
        }
        if (OPPOSITE[i] == j) {
            return 4;
        }
        if (i == 0) {
            return 2;
        }
        return 3;
    }

}
