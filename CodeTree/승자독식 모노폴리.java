import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    int[] inputArray;
    List<Player> players;
    HashMap<Integer, Player> playerMap;
    PriorityQueue<Player>[][] playerHeap;
    int[][] occupiedRound;
    int[][] occupiedPlayer;
    int n, m, k;
    int round;

    public static void main(String[] args) {

        Main main = new Main();
        try {
            main.init();
            main.solution();
            main.printResult();
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

        round = 0;

        players = new ArrayList<>();
        playerMap = new HashMap<>();
        occupiedPlayer = new int[n][n];
        occupiedRound = new int[n][n];
        playerHeap = new PriorityQueue[n][n];

        for (int i = 0; i < n; i++) {
            inputArray = getInputArray();
            for (int j = 0; j < n; j++) {
                playerHeap[i][j] = new PriorityQueue<>(Player::compareWithIndex);
                if (inputArray[j] != 0) {
                    int playerIndex = inputArray[j];
                    Player player = new Player(playerIndex, new Position(i, j));
                    playerMap.put(playerIndex, player);
                    players.add(player);
                    setOccupied(i, j, playerIndex);
                }
            }
        }

        inputArray = getInputArray();
        for (int i = 0; i < m; i++) {
            Player player = playerMap.get(i + 1);
            player.d = inputArray[i] - 1;
        }

        for (int i = 0; i < m; i++) {
            Player player = playerMap.get(i + 1);
            for (int d = 0; d < 4; d++) {
                inputArray = getInputArray();
                for (int j = 0; j < 4; j++) {
                    inputArray[j] -= 1;
                }
                player.directionPriorityMap.put(d, inputArray);
            }
        }


    }

    void setOccupied(int x, int y, int playerIndex) {
        occupiedPlayer[x][y] = playerIndex;
        occupiedRound[x][y] = round + k;
    }


    void solution() {
        while (!finished()) {
            movePlayer();
            removePlayer();
            round += 1;
            updateOccupied();
        }
    }

    void movePlayer() {
        for (Player player : players) {
            boolean canMove = false;
            int[] directionPriority = player.directionPriorityMap.get(player.d);
            for (int d : directionPriority) {
                int newX = player.p.x + DX[d];
                int newY = player.p.y + DY[d];
                if (isInner(newX, newY) && occupiedRound[newX][newY] <= round) {
                    playerHeap[newX][newY].add(player);
                    player.p.x = newX;
                    player.p.y = newY;
                    player.d = d;
                    canMove = true;
                    break;
                }
            }
            if (!canMove) {
                for (int d : directionPriority) {
                    int newX = player.p.x + DX[d];
                    int newY = player.p.y + DY[d];
                    if (isInner(newX, newY) && occupiedPlayer[newX][newY] == player.index) {
                        playerHeap[newX][newY].add(player);
                        player.p.x = newX;
                        player.p.y = newY;
                        player.d = d;
                        canMove = true;
                        break;
                    }
                }
                if (!canMove) {
                    playerHeap[player.p.x][player.p.y].add(player);
                }
            }
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    void removePlayer() {
        List<Player> newPlayers = new ArrayList<>();
        PriorityQueue<Player>[][] newPlayerHeap = new PriorityQueue[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newPlayerHeap[i][j] = new PriorityQueue<>(Player::compareWithIndex);
                if (!playerHeap[i][j].isEmpty()) {
                    newPlayers.add(playerHeap[i][j].remove());
                }
            }
        }

        players = newPlayers;
        playerHeap = newPlayerHeap;
    }

    void updateOccupied() {
        for (Player player : players) {
            setOccupied(player.p.x, player.p.y, player.index);
        }

    }

    void printResult() {
        if (round > 1000) {
            System.out.println(-1);
        } else {
            System.out.println(round);
        }
    }

    boolean finished() {
        return players.size() == 1 || round > 1000;
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
        Position p;
        int d;
        HashMap<Integer, int[]> directionPriorityMap;

        public Player(int index, Position p) {
            this.index = index;
            this.p = p;
            this.directionPriorityMap = new HashMap<>();
        }

        int compareWithIndex(Player compare) {
            return Integer.compare(this.index, compare.index);
        }
    }
}
