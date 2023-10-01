import java.util.*;
import java.io.*;

/*
4 * 4 격자에 m 개의 몬스터, 1개의 팩맨

몬스터 복제 시도
    자신과 같은 방향을 가진 몬스터를 복제하려 한다.
    복제된 몬스터는 아직은 부화되지 안흔 상태로 움직이지 못한다.

몬스터 이동
    방향대로 한칸 이동,
    움직이려는 칸에 몬스터 시체, 팩맨, 격자를 벗어나는 경우, 이동할 수 있는 점이 나올때까지 반시계 방향으로 회전하다가 이동

팩맨 이동
    총 3칸 이동, 이동마다 상하좌우 선택가능, 총 64개의 경우중 이동 도중 격자바깥을 나가는 경우를 제외하고,
    몬스터를 가장 많이 먹을 수 있는 방향으로 움직인다.
    상하좌우의 우선 순위를 가진다.
    몬스터를 먹으면 그 자리에 몬스터 사체를 남기고, 알은 먹지 않음, 움직이기 전에 있었던 몬스터 먹지 않음

몬스터 시체 소멸
    시체는 2턴동안 유지된다. 2턴이 지난 후에는 소멸

몬스터 복제 완성
    알 형태의 몬스터가 부화
 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DY = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int n = 4;
    int[] inputArray;
    int[][] monsterBoard;
    int[][] deadMonsterBoard;
    int m, t;
    Position pacman;
    List<Monster> monsters;
    List<Monster> babyMonsters;
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
        m = inputArray[0];
        t = inputArray[1];

        inputArray = getInputArray();

        pacman = new Position(inputArray[0] - 1, inputArray[1] - 1);
        monsters = new ArrayList<>();
        monsterBoard = new int[n][n];
        deadMonsterBoard = new int[n][n];

        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            monsterBoard[inputArray[0] - 1][inputArray[1] - 1] += 1;
            monsters.add(new Monster(new Position(inputArray[0] - 1, inputArray[1] - 1), inputArray[2] - 1));

        }
        round = 1;


    }


    void solution() throws IOException {
        while (round <= t) {
            duplicateMonsters();
            moveMonsters();
            movePacman();
            babyMonsterBorn();
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(monsterBoard[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            round += 1;
        }
        System.out.println(monsters.size());
    }

    void duplicateMonsters() {
        babyMonsters = new ArrayList<>();
        for (Monster monster : monsters) {
            babyMonsters.add(new Monster(new Position(monster.position.x, monster.position.y), monster.direction));
        }
    }

    int getCCWDirection(int direction) {
        return (direction + 1) % 8;
    }

     void moveMonsters() {
         for (Monster monster : monsters) {
             monsterBoard[monster.position.x][monster.position.y] -= 1;

             for (int d = 0; d < 8; d++) {
                 int newX = monster.position.x + DX[monster.direction];
                 int newY = monster.position.y + DY[monster.direction];

                 if (canGo(newX, newY)) {
                     monster.position.x = newX;
                     monster.position.y = newY;
                     break;
                 }
                 monster.direction = getCCWDirection(monster.direction);
             }

             monsterBoard[monster.position.x][monster.position.y] += 1;
         }

     }

    boolean canGo(int x, int y) {
        return isInner(x, y) && round > deadMonsterBoard[x][y] && (x != pacman.x || y != pacman.y);
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

     void movePacman() {
         BacktrackResult backtrackResult = backtrack(0, new Position(pacman.x, pacman.y), new StringBuilder(), 0);
//         System.out.println("backtracked "+backtrackResult.direction+" "+backtrackResult.captured);

         for (int i = 0; i < 3; i++) {
             int direction = Character.getNumericValue(backtrackResult.direction.charAt(i));

             pacman.x += DX[direction];
             pacman.y += DY[direction];

             monsterBoard[pacman.x][pacman.y] = 0;
         }

         List<Monster> newMonsters = new ArrayList<>();

         for (Monster monster : monsters) {
             if (monsterBoard[monster.position.x][monster.position.y] == 0) {
                 deadMonsterBoard[monster.position.x][monster.position.y] = round + 2;
             } else {
                 newMonsters.add(monster);
             }
         }
//         System.out.println("pacman " + pacman.x + " " + pacman.y);
         monsters = newMonsters;

     }

    BacktrackResult backtrack(int index, Position position, StringBuilder stringBuilder, int captured) {

        if (index == 3) {
            return new BacktrackResult(stringBuilder.toString(), captured);
        }
        BacktrackResult toReturn = new BacktrackResult("88888", 0);
        for (int i = 0; i < 8; i += 2) {
            int newX = position.x + DX[i];
            int newY = position.y + DY[i];

            if (isInner(newX, newY)) {
                int tempCaptured = monsterBoard[newX][newY];
                position.x += DX[i];
                position.y += DY[i];
                captured += tempCaptured;
                stringBuilder.append(i);
                monsterBoard[newX][newY] = 0;

                BacktrackResult backtracked = backtrack(index + 1, position, stringBuilder, captured);
                if (backtracked.captured > toReturn.captured) {
                    toReturn = backtracked;
                } else if (backtracked.captured == toReturn.captured) {
                    if (backtracked.direction.compareTo(toReturn.direction) < 0) {
                        toReturn = backtracked;
                    }
                }

                position.x -= DX[i];
                position.y -= DY[i];
                captured -= tempCaptured;
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                monsterBoard[newX][newY] = tempCaptured;
            }
        }
        return toReturn;
    }

     void babyMonsterBorn() {
         for (Monster babyMonster : babyMonsters) {
             monsterBoard[babyMonster.position.x][babyMonster.position.y] += 1;
             monsters.add(babyMonster);
         }
     }


     static class BacktrackResult {
        String direction;
        int captured;

         public BacktrackResult(String direction, int captured) {
             this.direction = direction;
             this.captured = captured;
         }
     }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Monster {
        Position position;
        int direction;

        public Monster(Position position, int direction) {
            this.position = position;
            this.direction = direction;
        }
    }

}
