import java.util.*;
import java.io.*;

/*
R * C
상하좌우 인접한 4칸 중 하나로 이동, 같은 알파벳이 적힌 칸을 두번 지날 수 없음, 좌측 상단에서 시작해서 말이 최대 몇칸을 지날 수 있는지 구하셈

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 0, 1, -1};
    static final int[] DY = {1, -1, 0, 0};
    int R, C;
    Character[][] board;
    int[] inputArray;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        R = inputArray[0];
        C = inputArray[1];
        board = new Character[R][C];
        for (int i = 0; i < R; i++) {
            String inputString = BR.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = inputString.charAt(j);
            }
        }
    }


    void solution() {
        System.out.println(backtrack(0, 0, new HashSet<>()));
    }

    boolean canGo(int x, int y, HashSet<Character> visited) {
        return isInner(x, y) && !visited.contains(board[x][y]);
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    int backtrack(int x, int y, HashSet<Character> visited) {
        visited.add(board[x][y]);
        int result = visited.size();
        for (int d = 0; d < 4; d++) {
            int newX = x + DX[d];
            int newY = y + DY[d];
            if (canGo(newX, newY, visited)) {
                result = Math.max(result, backtrack(newX, newY, visited));
            }

        }
        visited.remove(board[x][y]);
        return result;
    }



}