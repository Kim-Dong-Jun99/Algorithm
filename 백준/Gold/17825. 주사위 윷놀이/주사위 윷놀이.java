import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final Position[] dices = {
            new Position(0, 0),
            new Position(0, 0),
            new Position(0, 0),
            new Position(0, 0)
    };
    int[][] points;
    int[] commands;
    HashMap<Integer, Integer> turn;
    int answer;
    int scoreSum;
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
        commands = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        points = new int[6][20];
        turn = new HashMap<>();
        for (int i = 1; i < 20; i++) {
            points[0][i] = i * 2;
        }
        points[1] = new int[]{10, 13, 16, 19};
        points[2] = new int[]{20, 22, 24};
        points[3] = new int[]{30, 28, 27, 26};
        points[4] = new int[]{25, 30, 35};
        points[5] = new int[]{40, 0, 0, 0, 0, 0};
        turn.put(5, 1);
        turn.put(10, 2);
        turn.put(15, 3);
        answer = 0;
        scoreSum = 0;
    }

    void solution() throws IOException {
        backTrack(0);
        System.out.println(answer);
    }

    void backTrack(int index) {
        if (index == 10) {
            answer = Math.max(scoreSum, answer);

            return;
        }
        for (int i = 0; i < 4; i++) {
            if (canGo(dices[i]) && notOccupied(i, index)) {
                Position toRollback = new Position(dices[i].line, dices[i].index);
                dices[i].index += commands[index];
                while (dices[i].index >= points[dices[i].line].length) {
                    dices[i].index = dices[i].index - points[dices[i].line].length;
                    if (dices[i].line == 0) {
                        dices[i].line = 5;
                    } else if (dices[i].line == 4) {
                        dices[i].line = 5;
                    } else {
                        dices[i].line = 4;
                    }
                }
                if (dices[i].line == 0 && turn.containsKey(dices[i].index)) {
                    dices[i].line = turn.get(dices[i].index);
                    dices[i].index = 0;
                }
                int toAdd = points[dices[i].line][dices[i].index];
                scoreSum += toAdd;
                backTrack(index + 1);
                scoreSum -= toAdd;
                dices[i] = toRollback;
            }
        }

    }

    boolean canGo(Position currentPosition) {
        return currentPosition.index == 0 || points[currentPosition.line][currentPosition.index] != 0;
    }

    boolean notOccupied(int i, int index) {
        int newLine = dices[i].line;
        int newIndex = dices[i].index + commands[index];
        while (newIndex >= points[newLine].length) {
            newIndex = newIndex - points[newLine].length;
            if (newLine == 0) {
                newLine = 5;
            } else if (newLine == 4) {
                newLine = 5;
            } else {
                newLine = 4;
            }
        }
        if (newLine == 0 && turn.containsKey(newIndex)) {
            newLine = turn.get(newIndex);
            newIndex = 0;
        }
        for (int j = 0; j < 4; j++) {
            if (i != j) {
                if (dices[j].line == newLine && dices[j].index == newIndex && canGo(dices[j])) {
                    return false;
                }
            }
        }
        return true;
    }
    static class Position {
        int line;
        int index;

        public Position(int line, int index) {
            this.line = line;
            this.index = index;
        }

        static boolean equals(Position x, Position y) {
            return x.line == y.line && x.index == y.index;
        }
    }


}
