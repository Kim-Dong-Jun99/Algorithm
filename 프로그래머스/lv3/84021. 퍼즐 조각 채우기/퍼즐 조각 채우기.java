import java.util.*;

class Solution {
    int Answer;
    int N;
    int[][] game_board;
    int[][] table;
    int startX;
    int startY;
    int endX;
    int endY;
    String allDirections = "UDLR";
    HashMap<Character, HashMap<Character, Direction>> directionMap;
    StringBuilder sb;
    HashMap<Character, Direction> directions;
    List<Shape> shapesInTable;
    Direction U;
    Direction L;
    Direction D;
    Direction R;
    int[][] visited;
    public int solution(int[][] game_board, int[][] table) {
        init(game_board, table);
        solve();
        return Answer;
    }

    void init(int[][] game_board, int[][] table) {
        N = game_board.length;
        this.game_board = new int[N+2][N+2];
        this.table = new int[N+2][N+2];
        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(this.game_board[i], 1);

        }
        for (int i = 0; i < N; i++) {
            System.arraycopy(table[i], 0, this.table[i + 1], 1, N);
            System.arraycopy(game_board[i], 0, this.game_board[i + 1], 1, N);
        }
        Answer = 0;
        directionMap = new HashMap<>();
        shapesInTable = new ArrayList<>();
        initDirectionMap();
        initShapesInTable();
    }

    void solve() {
        /*
        4 방향에서 game_board를 바라보면 해결될듯?
        같은 도형인지는 어떻게 판단하면 될까?
        이동방향을 string으로 기록해서 같으면 같은 도형,
         */
        for (int i = 0; i < 4; i++) {
//            System.out.println("looking direction = " + allDirections.charAt(i));
            search(allDirections.charAt(i));
        }

    }

    void initDirectionMap() {
        for (int i = 0; i < 4; i++) {
            HashMap<Character, Direction> tempDirection = new HashMap<>();
            for (int j = 0; j < 4; j++) {
                Direction direction = createDirection(allDirections.charAt(i), allDirections.charAt(j));
                tempDirection.put(allDirections.charAt(j), direction);
            }

            directionMap.put(allDirections.charAt(i), tempDirection);

        }
    }

    void initShapesInTable() {
        initStartAndEndCoordinate('U');
        initDirectionsByLookingDirection('U');
        int i = 0;
        while (i != N+2) {
            int j = 0;
            while (j != N+2) {
                if (table[i][j] == 1) {
                    addShapeToList(i, j);
                }
                j += R.y;
            }
            i += D.x;
        }
    }

    void addShapeToList(int x, int y) {
        sb = new StringBuilder();
        Coordinate cur = new Coordinate(x, y);
        Shape newShape = new Shape();
        List<Coordinate> currentNodes = List.of(cur);
        table[x][y] = 0;
        while (!currentNodes.isEmpty()) {
            List<Coordinate> temp = new ArrayList<>();
            for (Coordinate currentNode : currentNodes) {
//                newShape.coordinates.add(currentNode);
                for (int i = 0; i < 4; i++) {
                    Direction direction = directions.get(allDirections.charAt(i));
                    int newX = currentNode.x + direction.x;
                    int newY = currentNode.y + direction.y;
                    if (canGo(newX, newY) && table[newX][newY] == 1) {
                        table[newX][newY] = 0;
                        temp.add(new Coordinate(newX, newY));
                        sb.append(allDirections.charAt(i));
                    } else {
                        sb.append("X");
                    } 
                }
            }
            sb.append(" ");
            currentNodes = temp;
        }
        newShape.setValue(sb.toString());
//        System.out.println("newShape size = " + newShape.coordinates.size() + " value = " + newShape.value);
        shapesInTable.add(newShape);
    }

    boolean canGo(int i, int j) {
        return 0 <= i && i < N+2 && 0 <= j && j < N+2;
    }

    void search(char lookingDirection) {
        visited = new int[N+2][N+2];
        initStartAndEndCoordinate(lookingDirection);
        initDirectionsByLookingDirection(lookingDirection);
        if (lookingDirection == 'U' || lookingDirection == 'D') {
            int i = startX;
            while (i != endX) {
                int j = startY;
                while (j != endY) {
                    if (game_board[i][j] == 0 && visited[i][j] == 0) {
                        bfsGameBoard(i, j);
                    }
                    j += R.y;
                }
                i += D.x;
            }
        } else {
            int j = startY;
            while (j != endY) {
                int i = startX;
                while (i != endX) {
                    if (game_board[i][j] == 0 && visited[i][j] == 0) {
                        bfsGameBoard(i, j);
                    }
                    i += R.x;
                }
                j += D.y;
            }
        }


    }

    void bfsGameBoard(int x, int y) {
        sb = new StringBuilder();
        visited[x][y] = 1;
        Shape newShape = new Shape();
        Coordinate cur = new Coordinate(x, y);
        List<Coordinate> currentNodes = List.of(cur);
        while (!currentNodes.isEmpty()) {
            List<Coordinate> temp = new ArrayList<>();
            for (Coordinate currentNode : currentNodes) {
                newShape.coordinates.add(currentNode);
                for (int i = 0; i < 4; i++) {
                    Direction direction = directions.get(allDirections.charAt(i));
                    int newX = currentNode.x + direction.x;
                    int newY = currentNode.y + direction.y;
                    if (canGo(newX, newY) && game_board[newX][newY] == 0 && visited[newX][newY] == 0) {
                        visited[newX][newY] = 1;
                        temp.add(new Coordinate(newX, newY));
                        sb.append(allDirections.charAt(i));
                    } else {
                        sb.append("X");
                    }
                }
            }
            sb.append(" ");
            currentNodes = temp;
        }
        newShape.setValue(sb.toString());
        List<Shape> tempShapes = new ArrayList<>();
        boolean removed = false;
        for (Shape shape : shapesInTable) {
            if (shape.equals(newShape) && !removed) {
                removed = true;
                Answer += newShape.coordinates.size();
            } else {
                tempShapes.add(shape);
            }
        }
        shapesInTable = tempShapes;
        if (removed) {
            eraseShapeInGameBoard(newShape);
        }

    }

    void eraseShapeInGameBoard(Shape shape) {
        for (Coordinate coordinate : shape.coordinates) {
            game_board[coordinate.x][coordinate.y] = 1;
        }
    }

    void initStartAndEndCoordinate(char lookingDirection) {
        switch (lookingDirection) {
            case 'U':
                startX = 0;
                startY = 0;
                endX = N+2;
                endY = N+2;
                break;
            case 'D':
                startX = N+1;
                startY = N+1;
                endX = -1;
                endY = -1;
                break;
            case 'L':
                startX = N+1;
                startY = 0;
                endX = -1;
                endY = N+2;
                break;
            default:
                startX = 0;
                startY = N+1;
                endX = N+2;
                endY = -1;
                break;
        }
    }

    void initDirectionsByLookingDirection(char lookingDirection) {
        directions = directionMap.get(lookingDirection);
        U = directions.get('U');
        D = directions.get('D');
        L = directions.get('L');
        R = directions.get('R');
    }


    Direction createDirection(char lookingDirection, char toMove) {
        switch (lookingDirection) {
            case 'U':
                switch (toMove) {
                    case 'U':
                        return new Direction(-1, 0);
                    case 'D':
                        return new Direction(1, 0);
                    case 'L':
                        return new Direction(0, -1);
                    default:
                        return new Direction(0, 1);
                }
            case 'D':
                switch (toMove) {
                    case 'U':
                        return new Direction(1, 0);
                    case 'D':
                        return new Direction(-1, 0);
                    case 'L':
                        return new Direction(0, 1);
                    default:
                        return new Direction(0, -1);
                }
            case 'L':
                switch (toMove) {
                    case 'U':
                        return new Direction(0, -1);
                    case 'D':
                        return new Direction(0, 1);
                    case 'L':
                        return new Direction(1, 0);
                    default:
                        return new Direction(-1, 0);
                }
            default:
                switch (toMove) {
                    case 'U':
                        return new Direction(0, 1);
                    case 'D':
                        return new Direction(0, -1);
                    case 'L':
                        return new Direction(-1, 0);
                    default:
                        return new Direction(1, 0);
                }
        }
    }

    static class Shape {
        String value;
        List<Coordinate> coordinates;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Shape) {
                Shape temp = (Shape) obj;
                return value.equals(temp.value);
            }
            return false;
        }

        public Shape() {
            this.coordinates = new ArrayList<>();
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Direction {
        int x;
        int y;

        public Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}