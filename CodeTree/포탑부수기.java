import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1, 1, 1, -1, -1};
    static final int[] DY = {1, 0, -1, 0, 1, -1, 1, -1};
    int[] inputArray;
    int N, M, K;
    int[][] board;
    int[][] lastAttack;
    boolean[][] visited;
    boolean[][] attacked;
    StringBuilder[][] direction;
    int count, distance;
    Turret attacker, target;
    int damage;

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
        N = inputArray[0];
        M = inputArray[1];
        K = inputArray[2];

        board = new int[N][M];
        lastAttack = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = getInputArray();
        }

        count = 0;

    }


    void solution() throws IOException {
        while (count < K) {
            List<Turret> turrets = getTurrets();
            if (turrets.size() == 1) {
                break;
            }
            attacker = turrets.get(0);
            target = turrets.get(turrets.size() - 1);

            attack();

            repair();
            count += 1;
        }

        printResult();
    }

    List<Turret> getTurrets() {
        List<Turret> turrets = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    turrets.add(new Turret(lastAttack[i][j], new Position(i, j), board[i][j]));
                }
            }
        }

        turrets.sort(Turret::getAttacker);

        return turrets;
    }

    void attack() {
        lastAttack[attacker.position.x][attacker.position.y] = count + 1;
        board[attacker.position.x][attacker.position.y] += N + M;

        visited = new boolean[N][M];
        attacked = new boolean[N][M];
        direction = new StringBuilder[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                direction[i][j] = new StringBuilder();
            }
        }
        attacked[attacker.position.x][attacker.position.y] = true;
        attacked[target.position.x][target.position.y] = true;

        visited[attacker.position.x][attacker.position.y] = true;

        List<Position> currentNodes = Arrays.asList(attacker.position);
        distance = 0;
        boolean canGo = false;
        while (!currentNodes.isEmpty()) {
            distance += 1;
            List<Position> temp = new ArrayList<>();
            for (Position current : currentNodes) {
                for (int i = 0; i < 4; i++) {
                    Position nextPosition = getNextPosition(current.x, current.y, i);
                    if (board[nextPosition.x][nextPosition.y] > 0 && !visited[nextPosition.x][nextPosition.y]) {
                        temp.add(nextPosition);
                        visited[nextPosition.x][nextPosition.y] = true;
                        direction[nextPosition.x][nextPosition.y].append(direction[current.x][current.y]).append(i);
                    }
                    StringBuilder tempBuilder = new StringBuilder(direction[current.x][current.y]).append(i);
                    if (tempBuilder.length() == direction[nextPosition.x][nextPosition.y].length() && direction[nextPosition.x][nextPosition.y].toString().compareTo(tempBuilder.toString()) > 0) {
                        direction[nextPosition.x][nextPosition.y] = tempBuilder;
                    }
                }
            }
            if (visited[target.position.x][target.position.y]) {
                canGo = true;
                break;
            }

            currentNodes = temp;
        }

        if (canGo) {
            attackWithLaser();
        } else {
            attackWithCannon();
        }


    }

    void attackWithLaser() {
        damage = board[attacker.position.x][attacker.position.y] / 2;
        Position current = new Position(attacker.position.x, attacker.position.y);
        StringBuilder destination = direction[target.position.x][target.position.y];
        for (int i = 0; i < destination.length(); i++) {
            int d = Character.getNumericValue(destination.charAt(i));
            Position nextPosition = getNextPosition(current.x, current.y, d);
            attacked[nextPosition.x][nextPosition.y] = true;
            if (nextPosition.x == target.position.x && nextPosition.y == target.position.y) {
                continue;
            }
            board[nextPosition.x][nextPosition.y] -= damage;

            current = nextPosition;
        }
        board[target.position.x][target.position.y] -= board[attacker.position.x][attacker.position.y];

    }
    
    
    void attackWithCannon() {
        damage = board[attacker.position.x][attacker.position.y] / 2;
        for (int i = 0; i < 8; i++) {
            Position nextPosition = getNextPosition(target.position.x, target.position.y, i);
            if (board[nextPosition.x][nextPosition.y] > 0) {
                if (nextPosition.x == attacker.position.x && nextPosition.y == attacker.position.y) {
                    continue;
                }
                if (nextPosition.x == target.position.x && nextPosition.y == target.position.y) {
                    continue;
                }
                board[nextPosition.x][nextPosition.y] -= damage;

                attacked[nextPosition.x][nextPosition.y] = true;
            }
        }
        board[target.position.x][target.position.y] -= board[attacker.position.x][attacker.position.y];
    }

    void repair() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0 && !attacked[i][j]) {
                    board[i][j] += 1;
                }
            }
        }
    }


    Position getNextPosition(int x, int y, int direction) {
        int newX = x + DX[direction];
        int newY = y + DY[direction];
        if (isInner(newX, newY)) {
            return new Position(newX, newY);
        }
        if (newX == -1) {
            newX = N - 1;
        }
        if (newX == N) {
            newX = 0;
        }
        if (newY == -1) {
            newY = M - 1;
        }
        if (newY == M) {
            newY = 0;
        }
        return new Position(newX, newY);
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    void printResult() {
        int toPrint = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                toPrint = Math.max(toPrint, board[i][j]);
            }
        }
        System.out.println(toPrint);
    }


    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getPositionSum() {
            return x + y;
        }

    }

    static class Turret {
        int lastAttack;
        Position position;
        int value;

        public Turret(int lastAttack, Position position, int value) {
            this.lastAttack = lastAttack;
            this.position = position;
            this.value = value;
        }

        int getAttacker(Turret compare) {
            if (this.value != compare.value) {
                return Integer.compare(this.value, compare.value);
            }
            if (this.lastAttack != compare.lastAttack) {
                return Integer.compare(compare.lastAttack, this.lastAttack);
            }
            if (this.position.getPositionSum() != compare.position.getPositionSum()) {
                return Integer.compare(compare.position.getPositionSum(), this.position.getPositionSum());
            }
            return Integer.compare(compare.position.y, this.position.y);
        }
    }

}
