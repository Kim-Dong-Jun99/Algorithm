import java.io.*;
import java.util.*;

/*
	N 명이 모여서 축구하려함, 두 팀으로 나눠야함
    인원수는 같지 않아도된다

    두 팀의 능력치 차이의 최소를 구하시오
*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N;
    int[][] power;
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
        N = Integer.parseInt(BR.readLine());
        power = new int[N][N];
        for (int i = 0; i < N; i++) {
            power[i] = getInputArray();
        }
        answer = 100 * N * N;
    }

    void solve() throws IOException {
        dfs(new int[N], 0);
        System.out.println(answer);
    }

    void dfs(int[] group, int index) {
        if (index == N) {
            int zero = 0;
            int one = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (group[i] == 0 && group[j] == 0) {
                        zero += power[i][j];
                    }
                    if (group[i] == 1 && group[j] == 1){
                        one += power[i][j];
                    }
                }
            }

            answer = Math.min(answer, Math.abs(zero - one));
            return;
        }

        group[index] = 0;
        dfs(group, index + 1);
        group[index] = 1;
        dfs(group, index + 1);
    }
}