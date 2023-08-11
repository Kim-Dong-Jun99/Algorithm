import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, -1, 0, 1};
    int N;
    int M;
    int[][] lab;
    List<Position> viruses;
    int answer;
    int[][] visited;
    int mustVisit;
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
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        lab = new int[N][N];
        for (int i = 0; i < N; i++) {
            lab[i] = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        viruses = new ArrayList<>();
        mustVisit = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lab[i][j] == 2 ) {
                    viruses.add(new Position(i, j));
                }
                if (lab[i][j] == 0) {
                    mustVisit += 1;
                }
            }
        }
        answer = Integer.MAX_VALUE;
    }

    void solution() throws IOException {
        bfs(0, 0, new Stack<>());
        if (answer != Integer.MAX_VALUE) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }

    void bfs(int virusCount, int index, Stack<Position> selectedViruses) {
        if (virusCount == M) {
            visited = new int[N][N];
            List<Position> currentPosition = new ArrayList<>(selectedViruses);
            for (Position cur : currentPosition) {
                visited[cur.x][cur.y] = 1;
//                System.out.print("("+cur.x+", "+cur.y+") ");
            }
            int distance = 0;
            int infected = 0;
            while (!currentPosition.isEmpty()) {
                List<Position> temp = new ArrayList<>();
                for (Position position : currentPosition) {
                    if (lab[position.x][position.y] == 0) {
                        infected += 1;
                    }
                    for (int i = 0; i < 4; i++) {
                        int newX = position.x + DX[i];
                        int newY = position.y + DY[i];
                        if (canGo(newX, newY)) {
                            visited[newX][newY] = 1;
                            temp.add(new Position(newX, newY));

                        }
                    }
                }

                if (infected == mustVisit) {
                    answer = Math.min(answer, distance);
                    break;
                }
                distance += 1;
                currentPosition = temp;

            }
//            System.out.println(distance);
            return;
        }
        for (int i = index; i < viruses.size(); i++) {
            selectedViruses.add(viruses.get(i));
            bfs(virusCount + 1, i + 1, selectedViruses);
            selectedViruses.pop();
        }


    }

    boolean canGo(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N && lab[i][j] != 1 && visited[i][j] == 0;
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
