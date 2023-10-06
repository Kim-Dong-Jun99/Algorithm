
import java.util.*;
import java.io.*;

/*
n * n 격자, m개의 원자

원자는 각각 질량, 방향, 속력, 초기 위치를 가짐, 방향은 상하좌우, 대각선,
칸 밖을 벗어나는 경우 다시 돌아옴

원자들은 1초가 지날때마다 자신의 방향으로 속력만큼 이동
이동이 끝난 뒤 한 칸에 2개 이상의 원자가 있으면 합성
    같은 칸 원자들은 질량과 속력을 모두 합한 하나의 원자로 합쳐진다
    합쳐진 원자는 4개의 원자로 나눠짐
    나누어진 원자들은 모두 해당 칸에 위치하고 질량, 속력, 방향은 다음 기준을 따라 결정된다.
        질량 - 합쳐진 원자의 질량 / 5
        속력 - 합쳐진 원자의 속력 / 원자의 개수
        방향 - 원자의 방향이 모두 상하좌우 중 하나이거나 대각선중 하나면 상하좌우 아니면 대각선
        소숫점 버림

    질량 0이면 소멸
    이동중 만나면 합성 X


*/

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] inputArray;
    int n, m, k;
    List<Atom> atoms;
    Space[][] spaces;
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

        atoms = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            inputArray = getInputArray();
            atoms.add(new Atom(new Position(inputArray[0] - 1, inputArray[1] - 1), inputArray[2], inputArray[3], inputArray[4]));
        }

        round = 0;

    }


    void solution() throws IOException {
        while (round < k) {
            moveAtoms();
            mergeAtoms();
            round += 1;
        }
        printResult();
    }

    void initSpaces() {
        spaces = new Space[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                spaces[i][j] = new Space();
            }
        }
    }

    void moveAtoms() {
        initSpaces();
        for (Atom atom : atoms) {
            Position nextPosition = getNextPosition(atom.position, atom.direction, atom.speed);
            atom.position = nextPosition;
            spaces[nextPosition.x][nextPosition.y].addAtom(atom);
        }
    }

    void mergeAtoms() {
        List<Atom> newAtoms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (spaces[i][j].atoms.size() == 1) {
                    newAtoms.add(spaces[i][j].atoms.get(0));
                } else if (spaces[i][j].atoms.size() > 1) {
                    int newMass = spaces[i][j].massSum / 5;
                    int newSpeed = spaces[i][j].speedSum / spaces[i][j].atoms.size();
                    if (newMass == 0) {
                        continue;
                    }
                    if (spaces[i][j].diagonalCount == 0 || spaces[i][j].crossCount == 0) {
                        for (int d = 0; d < 8; d += 2) {
                            newAtoms.add(new Atom(new Position(i, j), newMass, newSpeed, d));
                        }
                    } else {
                        for (int d = 1; d < 8; d += 2) {
                            newAtoms.add(new Atom(new Position(i, j), newMass, newSpeed, d));
                        }
                    }

                }
            }
        }
        atoms = newAtoms;
    }

    void printResult() {
        int massSum = 0;
        for (Atom atom : atoms) {
            massSum += atom.mass;
        }
        System.out.println(massSum);
    }

    Position getNextPosition(Position current, int direction, int speed) {
        return new Position((current.x + (DX[direction] + n) * speed) % n, (current.y + (DY[direction] + n) * speed) % n);
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Space {
        List<Atom> atoms;
        int massSum;
        int speedSum;
        int diagonalCount;
        int crossCount;

        public Space() {
            this.atoms = new ArrayList<>();
            this.massSum = 0;
            this.speedSum = 0;
            this.diagonalCount = 0;
            this.crossCount = 0;
        }

        void addAtom(Atom atom) {
            atoms.add(atom);
            massSum += atom.mass;
            speedSum += atom.speed;
            if (atom.direction % 2 == 0) {
                crossCount += 1;
            } else {
                diagonalCount += 1;
            }
        }


    }

    static class Atom {
        Position position;
        int mass;
        int speed;
        int direction;

        public Atom(Position position, int mass, int speed, int direction) {
            this.position = position;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }
    }


}
