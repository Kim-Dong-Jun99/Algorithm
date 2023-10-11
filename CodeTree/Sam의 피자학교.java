import java.util.*;
import java.io.*;

/*

완벽한 피자도우를 만들어야함, 처음 도우는 평평하지 않고 도우의 위치에 대한 밀가루 양이 배열로 주어짐
아래 과정을 통해 완벽한 피자도우를 만든다

1. 밀가루 양이 가장 적은 위치에 밀가루 1만큼 넣어줌, 가장 작은 위치가 여러개라면 모두 추가
2. 도우를 말아준다.
3. 도우를 꾹 눌러준다
4. 도우를 두번 반으로 접어준다
5. 3의 과정을 한번 더 진행

n <= 100

*/

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {0, -1, 0, 1};
    static final int[] DY = {-1, 0, 1, 0};
    int[] inputArray;
    int[] directionToLeft;
    int n, k;
    Pizza pizza;
    List<Position> rolledPizzaPositions;
    List<Position> flippedPizzaPositions;
    PriorityQueue<Position> rolledHeap;
    int[][] rolledPizzaBoard;
    int[][] flippedPizzaBoard;
    PriorityQueue<Position> flippedHeap;
    int flipCount;
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
        k = inputArray[1];
        pizza = new Pizza(getInputArray());
        flipCount = 0;
        directionToLeft = new int[n];
    }

    void solution() {
        while (!finished()) {
            reset();
            flipCount += 1;
            addFlour();
            rollAndPressPizza();
            flipAndPressPizza();
        }
    }

    void reset() {
        directionToLeft = new int[n];
        rolledPizzaPositions = new ArrayList<>();
        flippedPizzaPositions = new ArrayList<>();
        rolledPizzaBoard = new int[n][n];
        flippedPizzaBoard = new int[4][n / 4];
        flippedHeap = new PriorityQueue<>(Position::compareWithYAndX);
        rolledHeap = new PriorityQueue<>(Position::compareWithYAndX);
    }

    void addFlour() {
        for (int i = 0; i < n; i++) {
            if (pizza.flours[i] == pizza.minimum) {
                pizza.flours[i] += 1;
            }
        }
    }

    int getCWDirection(int direction) {
        return (direction + 1) % 4;
    }
    void rollAndPressPizza() {
        rollPizza();
        drawRolledPizzaToBoard();
        pressPizza(rolledPizzaPositions, rolledPizzaBoard, rolledHeap);
    }

    void rollPizza() {
        int flipIndex = 0;
        int flipped = 0;
        int indexPass = 1;
        while (true) {
            flipIndex += indexPass;
            if (flipIndex + indexPass + flipped % 2 <= n) {
                for (int i = flipIndex; i > 0; i--) {
                    directionToLeft[i] = getCWDirection(directionToLeft[i]);
                }

                if (flipped % 2 == 1) {
                    indexPass += 1;
                }
                flipped += 1;
            } else {
                break;
            }


        }
    }

    void drawRolledPizzaToBoard() {
        int direction;
        Position position = new Position(n - 1, n - 1);
        for (int i = n - 1; i > -1; i--) {
            rolledPizzaBoard[position.x][position.y] = pizza.flours[i];
            Position current = new Position(position.x, position.y);
            rolledHeap.add(current);
            rolledPizzaPositions.add(current);
            direction = directionToLeft[i];
            position.x += DX[direction];
            position.y += DY[direction];
        }
    }

    void pressPizza(List<Position> pizzaPositions, int[][] changePizzaBoard, PriorityQueue<Position> positionHeap) {
        int boardRow = changePizzaBoard.length;
        int boardCol = changePizzaBoard[0].length;
        int[][] newPizza = new int[boardRow][boardCol];
        for (Position position : pizzaPositions) {
            newPizza[position.x][position.y] += changePizzaBoard[position.x][position.y];

            for (int d = 0; d < 4; d++) {
                int newX = position.x + DX[d];
                int newY = position.y + DY[d];

                if (isInner(newX, newY, boardRow, boardCol) && changePizzaBoard[newX][newY] > 0) {
                    if (changePizzaBoard[position.x][position.y] > changePizzaBoard[newX][newY]) {
                        int diff = changePizzaBoard[position.x][position.y] - changePizzaBoard[newX][newY];
                        newPizza[position.x][position.y] -= diff / 5;
                        newPizza[newX][newY] += diff / 5;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            Position position = positionHeap.remove();
            pizza.flours[i] = newPizza[position.x][position.y];
        }
    }

    boolean isInner(int x, int y, int boardRow, int boardCol) {
        return 0 <= x && x < boardRow && 0 <= y && y < boardCol;
    }

    void flipAndPressPizza() {
        drawFlippedPizzaToBoard();
        pressPizza(flippedPizzaPositions, flippedPizzaBoard, flippedHeap);

    }
    
    void drawFlippedPizzaToBoard() {
        int col = n / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < col; j++) {
                if (i % 2 == 0) {
                    int right = col * (Math.abs(2-i) + 1);
                    flippedPizzaBoard[i][j] = pizza.flours[right - 1 - j];
                } else {
                    int left = col * i;
                    flippedPizzaBoard[i][j] = pizza.flours[left + j];
                }
                flippedPizzaPositions.add(new Position(i, j));
                flippedHeap.add(new Position(i, j));
            }
        }
    }
    boolean finished() {
        int minimum = 3001;
        int maximum = 0;
        for (int flour : pizza.flours) {
            minimum = Math.min(minimum, flour);
            maximum = Math.max(maximum, flour);
        }
        pizza.minimum = minimum;
        return maximum - minimum <= k;
    }



    void printResult() throws IOException {
        BW.write(Integer.toString(flipCount));
        BW.newLine();
        BW.flush();
        BW.close();
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int compareWithYAndX(Position compare) {
            if (this.y != compare.y) {
                return Integer.compare(this.y, compare.y);
            }
            return Integer.compare(compare.x, this.x);
        }
    }

    static class Pizza {
        int[] flours;
        int minimum;

        public Pizza(int[] flours) {
            this.flours = flours;
        }
    }
}
