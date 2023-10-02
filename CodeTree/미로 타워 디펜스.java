import java.util.*;
import java.io.*;

/*
7 <= n <= 25
m <= 100

공격해서 몬스터 없앤다
    - 간단하다 그냥 board에서 0으로 만들면된다.
몬스터를 땡겨오면서 4번 겹치는 몬스터 제거
    - 타워에서부터 빙글빙글 돌면서 board에서 0이 아닌 점에만 관심을 가짐, 4개가 연속되지 않는다고 판단되면
    - board에 추가할 배열에 추가
기존 몬스터 배열을 변경
    - 위 단계에서 list로 좌표랑 좌표에 board 값이 있을 것임
    - 거기서부터 새로이 추가할 리스트를 만듬, 전체 칸보다 작을땍자ㅣ
    - 빙글빙글돌면서 board값 업데이트

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {0, 1, 0, -1, -1, -1, 1, 1};
    static final int[] DY = {1, 0, -1, 0, -1, 1, -1, 1};
    int[] inputArray;
    int n, m;
    int[][] board;
    boolean[][] turnBoard;
    Attack[] attacks;
    int score;
    Position center;
    List<Monster> survivingMonsters;
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

        center = new Position(n / 2, n / 2);

        board = new int[n][n];
        turnBoard = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = getInputArray();
        }

        attacks = new Attack[m];

        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            attacks[i] = new Attack(inputArray[0], inputArray[1]);
        }
        score = 0;

        initTurnBoard();

    }

    void initTurnBoard() {
        Position current = new Position(0, 0);
        int currentDirection = 0;
        boolean[][] visited = new boolean[n][n];

        while (current.x != n / 2 || current.y != n / 2) {
            visited[current.x][current.y] = true;
            int newX = current.x + DX[currentDirection];
            int newY = current.y + DY[currentDirection];

            if (!isInner(newX, newY) || visited[newX][newY]) {
                turnBoard[current.x][current.y] = true;
                currentDirection = getCWDirection(currentDirection);
                newX = current.x + DX[currentDirection];
                newY = current.y + DY[currentDirection];
            }

            current.x = newX;
            current.y = newY;

        }
        
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    void solution() throws IOException {
        for (Attack attack : attacks) {
            attackFromCenter(attack);
            removeDuplicateMonsters();
            addNewMonsters();
        }
        System.out.println(score);
    }

    void attackFromCenter(Attack attack) {
        for (int i = 1; i <= attack.p; i++) {
            int attackedX = center.x + DX[attack.d] * i;
            int attackedY = center.y + DY[attack.d] * i;
            score += board[attackedX][attackedY];
            board[attackedX][attackedY] = 0;
        }
    }


    void createSurviveMonsters() {
        survivingMonsters = new ArrayList<>();
        Stack<Monster> monsterStack = new Stack<>();
        int currentDirection = 2;
        Position current = new Position(center.x, center.y);
        while (isInner(current.x, current.y)) {
            if (board[current.x][current.y] != 0) {
                if (monsterStack.isEmpty()) {
                    monsterStack.push(new Monster(1, board[current.x][current.y]));
                } else {
                    if (monsterStack.peek().type != board[current.x][current.y]) {
                        if (monsterStack.peek().size >= 4) {
                            score += monsterStack.peek().size * monsterStack.peek().type;
                            monsterStack.pop();
                            monsterStack.push(new Monster(1, board[current.x][current.y]));
                        } else {
                            monsterStack.push(new Monster(1, board[current.x][current.y]));
                        }
                    } else {
                        monsterStack.peek().size += 1;
                    }
                }
            }

            current.x += DX[currentDirection];
            current.y += DY[currentDirection];

            if (isInner(current.x, current.y) && turnBoard[current.x][current.y]) {
                currentDirection = getCCWDirection(currentDirection);
            }
        }

        if (!monsterStack.isEmpty() && monsterStack.peek().size >= 4) {
            score += monsterStack.peek().size * monsterStack.peek().type;
            monsterStack.pop();
        }

        survivingMonsters.addAll(monsterStack);
    }

    void removeDuplicateMonsters() {
        createSurviveMonsters();
        while (true) {
            int removed = 0;
            Stack<Monster> monsterStack = new Stack<>();
            for (Monster monster : survivingMonsters) {
                if (monsterStack.isEmpty()) {
                    monsterStack.push(monster);
                } else {
                    if (monsterStack.peek().type != monster.type) {
                        if (monsterStack.peek().size >= 4) {
                            removed += 1;
                            score += monsterStack.peek().size * monsterStack.peek().type;
                            monsterStack.pop();
                        }
                        monsterStack.push(monster);
                    } else {
                        monsterStack.peek().size += monster.size;
                    }
                }
            }

            if (!monsterStack.isEmpty() && monsterStack.peek().size >= 4) {
                score += monsterStack.peek().size * monsterStack.peek().type;
                monsterStack.pop();
            }

            survivingMonsters = new ArrayList<>(monsterStack);
            if (removed == 0) {
                break;
            }
        }

    }

    void addNewMonsters() {
        List<Integer> valueToMark = new ArrayList<>();
        for (Monster monster : survivingMonsters) {
            valueToMark.add(monster.size);
            valueToMark.add(monster.type);
        }

        Position current = new Position(center.x, center.y - 1);
        int currentDirection = 1;
        int index = 0;
        while (isInner(current.x, current.y)) {
            if (index < valueToMark.size()) {
                board[current.x][current.y] = valueToMark.get(index);
            } else {
                board[current.x][current.y] = 0;
            }

            current.x += DX[currentDirection];
            current.y += DY[currentDirection];
            if (isInner(current.x, current.y) && turnBoard[current.x][current.y]) {
                currentDirection = getCCWDirection(currentDirection);
            }
            index += 1;
        }

    }

    int getCCWDirection(int d) {
        return (d - 1 + 4) % 4;
    }

    int getCWDirection(int d) {
        return (d + 1) % 4;
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
        int size;
        int type;

        public Monster(int size, int type) {
            this.size = size;
            this.type = type;
        }
    }


    static class Attack {
        int d;
        int p;

        public Attack(int d, int p) {
            this.d = d;
            this.p = p;
        }
    }


}
