import java.io.*;
import java.util.*;

/*
	동희는가 만드는 떡의 종류는 1번 ~ 9번까지 있음
    호랑이는 전날 먹었던 떡과 같은 종류의 떡은 안 먹음
    N일동안 떡을 팔러 장터에 가야함
    N일동안 호랑이에게 잡아먹히지 않도록 호랑이에게 줄 떡들을 고르시오

*/

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N;
    int[] eaten;
    boolean[][] dp;
    HashMap<Integer, HashSet<Integer>> cooked;
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
        eaten = new int[N];
        cooked = new HashMap<>();
        dp = new boolean[N][10];
        for (int i = 0; i < N; i++) {
            inputArray = getInputArray();
            int m = inputArray[0];
            cooked.put(i, new HashSet<>());
            for (int j = 1; j <= m; j++) {
                cooked.get(i).add(inputArray[j]);
            }
        }

    }

    void solve() throws IOException {
        for (Integer cook : cooked.get(0)) {
            dfs(0, cook);
        }
        System.out.println(-1);

    }

    void dfs(int index, int eat) {
        if (dp[index][eat]) {
            return;
        }
        eaten[index] = eat;
        dp[index][eat] = true;
        if (index + 1 == N) {
            for (int e : eaten) {
                System.out.println(e);
            }
            System.exit(0);

        }
        for (Integer cook : cooked.get(index+1)) {
            if (cook != eat) {
                dfs(index + 1, cook);
            }

        }
    }
}