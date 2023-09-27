import java.util.*;
import java.io.*;


public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};
    static final int[] OPPOSITE = {2, 3, 0, 1};
    static final int[] ROTATE_CW = {1, 2, 3, 0};
    int[] inputArray;
    static int n, m, k;
    static PriorityQueue<Integer>[][] gunBoard;
    static int[][] playerBoard;
    Player[] players;
    int round;
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
        n = inputArray[0];
        m = inputArray[1];
        k = inputArray[2];

        gunBoard = new PriorityQueue[n][n];
        playerBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            inputArray = getInputArray();
            for (int j = 0; j < n; j++) {
                gunBoard[i][j] = new PriorityQueue<>(Comparator.reverseOrder());
                gunBoard[i][j].add(inputArray[j]);
                playerBoard[i][j] = -1;
            }
        }

        players = new Player[m];

        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            players[i] = new Player(i, new Position(inputArray[0] - 1, inputArray[1] - 1), inputArray[2], inputArray[3]);
            playerBoard[players[i].position.x][players[i].position.y] = i;

        }

        round = 0;

    }


    void solution() throws IOException {
        while (round < k) {
            for (Player player : players) {
                player.move();
                if (playerBoard[player.position.x][player.position.y] == -1) {
                    player.savePositionToBoard();
                    player.updateGun();
                } else {
                    fight(player);
                }

            }

            round += 1;
        }
        for (Player player : players) {
            System.out.print(player.point+" ");
        }
    }

    void fight(Player attacker) {
        Player defender = players[playerBoard[attacker.position.x][attacker.position.y]];
        int winner = getWinner(attacker, defender);
        if (winner == attacker.index) {
            playerBoard[attacker.position.x][attacker.position.y] = attacker.index;
            attacker.point += attacker.getAttackPower() - defender.getAttackPower();
            defender.retreat();
            attacker.updateGun();
        } else {
            defender.point += defender.getAttackPower() - attacker.getAttackPower();
            attacker.retreat();
            defender.updateGun();
        }
    }

    int getWinner(Player attacker, Player defender) {
        if (attacker.getAttackPower() > defender.getAttackPower()) {
            return attacker.index;
        } else if (attacker.getAttackPower() < defender.getAttackPower()) {
            return defender.index;
        } else {
            if (attacker.power > defender.power) {
                return attacker.index;
            } else {
                return defender.index;
            }
        }
    }


    static boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Player {
        int index;
        Position position;
        int direction;
        int power;
        int point;
        int gun;

        public Player(int index, Position position, int direction, int power) {
            this.index = index;
            this.position = position;
            this.direction = direction;
            this.power = power;
            this.point = 0;
            this.gun = 0;
        }

        int getAttackPower() {
            return this.gun + this.power;
        }

        void move() {
            playerBoard[position.x][position.y] = -1;
            int newX = position.x + DX[direction];
            int newY = position.y + DY[direction];

            if (isInner(newX, newY)) {
                position.x = newX;
                position.y = newY;
                return;
            }

            direction = OPPOSITE[direction];
            newX = position.x + DX[direction];
            newY = position.y + DY[direction];
            position.x = newX;
            position.y = newY;
        }

        void retreat() {
            removeGun();
            while (true) {
                int newX = position.x + DX[direction];
                int newY = position.y + DY[direction];

                if (isInner(newX, newY) && playerBoard[newX][newY] == -1) {
                    position.x = newX;
                    position.y = newY;
                    updateGun();
                    savePositionToBoard();
                    break;
                } else {
                    direction = ROTATE_CW[direction];
                }
            }

        }

        void removeGun() {
            if (this.gun != 0) {
                gunBoard[position.x][position.y].add(gun);
                this.gun = 0;
            }
        }

        void savePositionToBoard() {
            playerBoard[position.x][position.y] = index;
        }

        void updateGun() {
            if (!gunBoard[position.x][position.y].isEmpty() && gunBoard[position.x][position.y].peek() > gun) {
                gunBoard[position.x][position.y].add(gun);
                gun = gunBoard[position.x][position.y].remove();
            }
        }
    }

}
