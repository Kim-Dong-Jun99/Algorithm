
import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    String[] map;
    int[][] visited;
    int[][] group;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    public static HashMap<Character, Integer> convertTable = new HashMap<>() {
        {
            put('U', 0);
            put('L', 1);
            put('D', 2);
            put('R', 3);
        }
    };
    int answer;
    int index;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("exception during I/O");
        }
    }


    void init() throws IOException {
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];

        map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = BR.readLine();
        }

        visited = new int[N][M];
        group = new int[N][M];
        answer = 0;
        index = 0;
    }

    void solution() throws IOException {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    group[i][j] = index;
                    dfs(i, j);
                    index += 1;
                }
            }
        }
        System.out.println(answer);
    }

    void dfs(int i, int j) {
        if (visited[i][j] == 1) {
            if (group[i][j] == index) {
                answer += 1;
            }
            return;
        }
        visited[i][j] = 1;
        group[i][j] = index;
        int[] nextNode = getNextNode(i, j);
        dfs(nextNode[0], nextNode[1]);

    }

    int[] getNextNode(int i, int j) {
        Integer direction = convertTable.get(map[i].charAt(j));
        return new int[]{i + dx[direction], j + dy[direction]};

    }

}