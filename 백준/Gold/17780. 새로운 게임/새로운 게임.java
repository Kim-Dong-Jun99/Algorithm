import java.util.*;
import java.io.*;

/*
N*N 체스판,
K 개의 체스말,
하나의 말 위에 다른 말을 올릴 수 있음,

체스판은 W, R, B 중 하나

체스판 위에 말 K개를 놓고 시작, 이동 방향도 미리 정해져있음

턴 한번은 1번 말부터 K번 말까지 순서대로 이동시키는 것

한말이 이동할때, 위에 올려져있는 말도 함께 이동, 가장 아래에 있는 말만 이동 가능,

말의 이동 방향에 있는 칸에 따라서 말의 이동이 다르고, 턴이 진행되는 중에 말이 4개 이상 쌓이면 끝

이동하려는 칸이
    W 인 경우
        그 칸으로 이동, 말이 이미 있는 경우 가장 위에 올려둠
    R 인 경우
        이동한 후 A 번 말과 위에 있는 말들의 순서를 변경
    B 인 경우
        이동 방향을 반대로하고 한칸 이동, 방향을 반대로 한 후 이동하려는 칸이 파란색인 경우, 이동하지 않고 방향만 바꿈,
        체스판을 벗어나는 경우도 파란색과 같이 취급

게임이 종료되는 턴의 번호 출력, 1000보다 크면 -1 출력
 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    static final int[] DX = {0, 0, -1, 1};
    static final int[] DY = {1, -1, 0, 0};
    static final int[] OPPOSITE = {1, 0, 3, 2};
    int[][] board;
    Queue<Piece>[][] pieceBoard;
    Piece[] pieces;
    int N, K;
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
        N = inputArray[0];
        K = inputArray[1];
        board = new int[N + 2][N + 2];
        pieceBoard = new Queue[N + 2][N + 2];
        pieces = new Piece[K + 1];
        for (int i = 0; i <= N + 1; i++) {
            Arrays.fill(board[i], BLUE);
            for (int j = 0; j <= N + 1; j++) {
                pieceBoard[i][j] = new ArrayDeque<>();
            }
        }
        for (int i = 0; i < N; i++) {
            inputArray = getInputArray();
            for (int j = 0; j < N; j++) {
                board[i + 1][j + 1] = inputArray[j];
            }
        }
        for (int i = 1; i <= K; i++) {
            inputArray = getInputArray();
            Piece piece = new Piece(i, inputArray[0], inputArray[1], inputArray[2] - 1);
            pieces[i] = piece;
            pieceBoard[piece.x][piece.y].add(piece);
        }
    }


    void solution() {
        int turn = 0;
        while (turn <= 1000) {
            turn += 1;
            boolean isDone = false;
            for (int index = 1; index <= K; index++) {
                Piece piece = pieces[index];
                Piece peekPiece = pieceBoard[piece.x][piece.y].peek();
                if (piece.equals(peekPiece)) {
                    int newX = piece.x + DX[piece.d];
                    int newY = piece.y + DY[piece.d];
                    if (board[newX][newY] == WHITE) {
                        moveToWhite(pieceBoard[piece.x][piece.y], newX, newY);
                    }
                    if (board[newX][newY] == RED) {
                        moveToRed(pieceBoard[piece.x][piece.y], newX, newY);
                    }
                    if (board[newX][newY] == BLUE) {
                        piece.d = OPPOSITE[piece.d];
                        int oppositeX = piece.x + DX[piece.d];
                        int oppositeY = piece.y + DY[piece.d];
                        if (board[oppositeX][oppositeY] == WHITE) {
                            moveToWhite(pieceBoard[piece.x][piece.y], oppositeX, oppositeY);
                        }
                        if (board[oppositeX][oppositeY] == RED) {
                            moveToRed(pieceBoard[piece.x][piece.y], oppositeX, oppositeY);
                        }
                        if (board[oppositeX][oppositeY] == BLUE) {
                            piece.d = OPPOSITE[piece.d];
                        }
                    }
                    if (pieceBoard[piece.x][piece.y].size() >= 4) {
                        isDone = true;
                        break;
                    }
                }
            }
            if (isDone) {
                break;
            }
        }
        if (turn > 1000) {
            System.out.println(-1);
        } else {
            System.out.println(turn);
        }
    }

    void moveToWhite(Queue<Piece> queue, int newX, int newY) {
        while (!queue.isEmpty()) {
            Piece piece = queue.remove();
            piece.x = newX;
            piece.y = newY;
            pieceBoard[newX][newY].add(piece);
        }
    }

    void moveToRed(Queue<Piece> queue, int newX, int newY) {
        Stack<Piece> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        while (!stack.isEmpty()) {
            Piece piece = stack.pop();
            piece.x = newX;
            piece.y = newY;
            pieceBoard[newX][newY].add(piece);
        }
    }



    static class Piece {
        int index;
        int x;
        int y;
        int d;

        public Piece(int index, int x, int y, int d) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Piece piece = (Piece) o;
            return index == piece.index;
        }

        @Override
        public int hashCode() {
            return this.index;
        }
    }

}