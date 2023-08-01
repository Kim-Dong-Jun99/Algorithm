import java.util.*;
import java.io.*;
/*
공백을 나눠서, 공백마다 bfs한번 실행한다음, 공백의 칸 의 개수를 hashmap에 저장하면될듯?
 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, -1, 0, 1};
    int N;
    int M;
    int[][] map;
    int colorIndex;
    int[][] visited;
    HashMap<Integer, Integer> spaceSize;

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
        map = new int[N][M];
        visited = new int[N][M];
        spaceSize = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String tempInput = BR.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(tempInput.charAt(j));
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
        colorIndex = 2;
        initSpaceSize();
    }

    void initSpaceSize() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && visited[i][j] == 0) {
                    visited[i][j] = 1;
                    int tempSize = 1;
                    List<Coordinate> currentNodes = List.of(new Coordinate(i, j));
                    while (!currentNodes.isEmpty()) {
                        List<Coordinate> temp = new ArrayList<>();
                        for (Coordinate currentNode : currentNodes) {
                            map[currentNode.x][currentNode.y] = colorIndex;
                            for (int k = 0; k < 4; k++) {
                                int newX = currentNode.x + DX[k];
                                int newY = currentNode.y + DY[k];
                                if (isInner(newX, newY) && map[newX][newY] == 0 && visited[newX][newY] == 0) {
                                    visited[newX][newY] = 1;
                                    temp.add(new Coordinate(newX, newY));
                                    tempSize += 1;
                                }
                            }
                        }

                        currentNodes = temp;
                    }
                    spaceSize.put(colorIndex, tempSize);
                    colorIndex += 1;
                }
            }
        }
    }

    boolean isInner(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < M;
    }

    void solution() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    HashSet<Integer> temp = new HashSet<>();
                    int toPrint = 1;
                    for (int k = 0; k < 4; k++) {
                        int newX = i + DX[k];
                        int newY = j + DY[k];
                        if (isInner(newX, newY) && map[newX][newY] != 1) {
                            temp.add(map[newX][newY]);
                        }
                    }
                    for (Integer key : temp) {
                        toPrint += spaceSize.get(key);
                    }
                    BW.write(Integer.toString(toPrint % 10));
                } else {
                    BW.write(Integer.toString(0));
                }
            }
            BW.newLine();
        }
        BW.flush();
        BW.close();
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
