import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    int K;
    int L;
    // 보드
    int[][] board;
    // 현재 뱀의 위치를 저장하기 위한 배열
    int[][] visited;
    Queue<int[]> snake = new ArrayDeque<>();
    int[] direction;
    int[] head;
    int length = 1;
    int time = 0;
    Map<Integer, String> timeToTurn;

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    void solution() throws Exception {
        N = Integer.parseInt(BR.readLine());
        board = new int[N][N];
        visited = new int[N][N];
        Arrays.stream(board).forEach(intArray -> {
            Arrays.fill(intArray,0);
        });
        Arrays.stream(visited).forEach(intArray -> {
            Arrays.fill(intArray, 0);
        });

        K = Integer.parseInt(BR.readLine());
        for (int i = 0; i < K; i++) {
            int[] apples = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            board[apples[0]-1][apples[1]-1] = 1;
        }

        L = Integer.parseInt(BR.readLine());
        timeToTurn = new HashMap<>();
        for (int i = 0; i < L; i++) {
            String[] turnOrders = BR.readLine().split(" ");
            timeToTurn.put(Integer.parseInt(turnOrders[0]), turnOrders[1]);
        }

        visited[0][0] = 1;
        head = new int[]{0, 0};
        direction = new int[]{0, 1};
        snake.add(new int[]{0, 0});
        while (true) {
            head = move(head, direction);
            time += 1;
//            System.out.println("head = "+head[0]+", "+head[1]+" time = "+time+" length = "+length);
            if (head[0] < 0 || head[0] >= N || head[1] < 0 || head[1] >= N) {
                break;
            }
            if (visited[head[0]][head[1]] == 1) {
                break;
            }


            addToSnake(head);

            direction = rotate(direction, time);

        }
//        BW.write(time);
//        BW.flush();
//        BW.close();
        System.out.println(time);
    }

    void addToSnake(int[] head) {
        visited[head[0]][head[1]] = 1;
        snake.add(head);
        if (board[head[0]][head[1]] == 1) {
            length += 1;
            board[head[0]][head[1]] = 0;
        }
        if (snake.size() > length) {
            int[] removed = snake.remove();
            visited[removed[0]][removed[1]] = 0;
        }
    }

    int[] move(int[] head, int[] direction) {
        return new int[]{head[0] + direction[0], head[1] + direction[1]};
    }

    int[] rotate(int[] direction, int time) {
        if (timeToTurn.containsKey(time)) {
            String leftOrRight = timeToTurn.get(time);
            if (Objects.equals(leftOrRight, "L")) {
                if (direction[0] == 0 && direction[1] == 1) {
                    // right to up
                    return new int[]{-1, 0};
                } else if (direction[0] == 1 && direction[1] == 0) {
                    // down to right
                    return new int[]{0, 1};
                } else if (direction[0] == -1 && direction[1] == 0) {
                    // up to left
                    return new int[]{0, -1};
                } else {
                    // left to down
                    return new int[]{1, 0};
                }
            } else {
                if (direction[0] == 0 && direction[1] == 1) {
                    // right to down
                    return new int[]{1, 0};
                } else if (direction[0] == 1 && direction[1] == 0) {
                    // down to left
                    return new int[]{0, -1};
                } else if (direction[0] == -1 && direction[1] == 0) {
                    // up to right
                    return new int[]{0, 1};
                } else {
                    // left to up
                    return new int[]{-1, 0};
                }
            }
        } else {
            return direction;
        }
    }
}
