import java.util.*;
import java.io.*;

/*
4 * 4 격자

술래 말 하나만 사용하여 도둑말을 잡고, 술래말을 잡을 때마다 도둑말의 방향을 갖게된다.
말은 8 방향으로 이동가능하다,

1.
    0,0에 있는 도둑말을 잡으며 시작
2.
    도둑말은 번호가 작은 순서대로 본인이 가지고 있는 이동 방향대로 이동, 한번의 이동에 한칸을 이동, 빈칸이나 다른 도둑말이 있는 칸은 이동할 수 있는 칸
    술래말, 격자를 벗어나는 곳 이동 불가, 반시계 방향으로 회전하며 이동할 수 있는 칸으로 이동, 이동할 수 있는 칸이 없으면 이동 X, 해당 칸에 다른 도둑말이 있으면 위치를 서로 바꿈
3.
    도둑 말의 이동이 모두 끝나면 술래말이 이동한다, 술래말은 이동가능한 방향의 어느 칸이나 이동할 수 있다, 한번에 여러 개의 칸도 이동 가능, 도둑말이 없는 곳으로 이동 불가,

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DY = {0, -1, -1, -1, 0, 1, 1, 1};
    int[] inputArray;
    int[][] pieceBoard;
    int[][] directionBoard;
    int answer;
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
        answer = 0;
        pieceBoard = new int[4][4];
        directionBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            inputArray = getInputArray();
            for (int j = 0; j < 4; j++) {
                int index = inputArray[j * 2];
                int direction = inputArray[j * 2 + 1] - 1;
                pieceBoard[i][j] = index;
                directionBoard[i][j] = direction;
            }
        }
        answer += pieceBoard[0][0];
        pieceBoard[0][0] = -1;
//        printPieceBoard();
//        printDirectionBoard();
    }


    int getCCWDirection(int d) {
        return (d + 1) % 8;
    }

    void solution() {
        answer += backtrack(0);
    }

    int backtrack(int captured) {
        int[][] oldPieceBoard = pieceBoard;
        int[][] oldDirectionBoard = directionBoard;
        HashMap<Integer, Piece> pieceMap = new HashMap<>();
        PriorityQueue<Piece> pieceHeap = new PriorityQueue<>(Piece::compareWithIndex);


        Position attacker = copyBoardAndReturnAttacker(pieceMap, pieceHeap);
//        System.out.println("attacker " + attacker.x + " " + attacker.y);
        movePlayer(pieceMap, pieceHeap);
//        printPieceBoard();
//        printDirectionBoard();
        int toReturn = captured;
        for (int d = 1; d <= 3; d++) {
            int newX = attacker.x + DX[directionBoard[attacker.x][attacker.y]] * d;
            int newY = attacker.y + DY[directionBoard[attacker.x][attacker.y]] * d;
            if (isInner(newX, newY)) {
                if (pieceBoard[newX][newY] > 0) {
                    int playerIndex = pieceBoard[newX][newY];
                    pieceBoard[attacker.x][attacker.y] = 0;
                    pieceBoard[newX][newY] = -1;

                    toReturn = Math.max(toReturn, backtrack(captured + playerIndex));

                    pieceBoard[attacker.x][attacker.y] = -1;
                    pieceBoard[newX][newY] = playerIndex;
                }
            } else {
                break;
            }
        }

        pieceBoard = oldPieceBoard;
        directionBoard = oldDirectionBoard;
        return toReturn;



    }

    void printPieceBoard() {
        System.out.println("pieceBoard");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(pieceBoard[i][j]+" ");
            }
            System.out.println();
        }
    }

    void printDirectionBoard() {
        System.out.println("directionBoard");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(directionBoard[i][j]+" ");
            }
            System.out.println();
        }
    }

    boolean isInner(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }

    Position copyBoardAndReturnAttacker(HashMap<Integer, Piece> pieceMap, PriorityQueue<Piece> pieceHeap) {

        int[][] newPieceBoard = new int[4][4];
        int[][] newDirectionBoard = new int[4][4];
        Position attacker = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (pieceBoard[i][j] == -1) {
                    attacker = new Position(i, j);
                }
                if (pieceBoard[i][j] > 0) {
                    Piece piece = new Piece(pieceBoard[i][j], new Position(i, j));
                    pieceMap.put(pieceBoard[i][j], piece);
                    pieceHeap.add(piece);
                }
                newPieceBoard[i][j] = pieceBoard[i][j];
                newDirectionBoard[i][j] = directionBoard[i][j];
            }
        }
        pieceBoard = newPieceBoard;
        directionBoard = newDirectionBoard;
        return attacker;
    }

    void movePlayer(HashMap<Integer, Piece> pieceMap, PriorityQueue<Piece> pieceHeap) {
        while (!pieceHeap.isEmpty()) {
            Piece piece = pieceHeap.remove();
//            System.out.println("piece "+piece.index);
            int direction = directionBoard[piece.p.x][piece.p.y];
            for (int i = 0; i < 8; i++) {
                int newX = piece.p.x + DX[direction];
                int newY = piece.p.y + DY[direction];
                if (isInner(newX, newY) && pieceBoard[newX][newY] != -1) {

//                    System.out.println("to move "+pieceBoard[newX][newY]);
                    directionBoard[piece.p.x][piece.p.y] = direction;
                    int tempX = piece.p.x;
                    int tempY = piece.p.y;
                    if (pieceBoard[newX][newY] > 0) {
                        Piece toChange = pieceMap.get(pieceBoard[newX][newY]);
                        toChange.p.x = tempX;
                        toChange.p.y = tempY;
                    }
                    int tempIndex = pieceBoard[newX][newY];
                    int tempDirection = directionBoard[newX][newY];
                    pieceBoard[newX][newY] = pieceBoard[piece.p.x][piece.p.y];
                    directionBoard[newX][newY] = directionBoard[piece.p.x][piece.p.y];

                    pieceBoard[piece.p.x][piece.p.y] = tempIndex;
                    directionBoard[piece.p.x][piece.p.y] = tempDirection;

                    piece.p.x = newX;
                    piece.p.y = newY;



                    break;
                }
                direction = getCCWDirection(direction);
            }
//            printPieceBoard();
//            printDirectionBoard();
        }

    }

    void printResult() {
        System.out.println(answer);
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Piece {
        int index;
        Position p;


        public Piece(int index, Position p) {
            this.index = index;
            this.p = p;
        }

        int compareWithIndex(Piece compare) {
            return Integer.compare(this.index, compare.index);
        }
    }
}
