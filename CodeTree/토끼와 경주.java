import java.util.*;
import java.io.*;


public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final int[] OPPOSITE = {1, 0, 3, 2};
    int[] inputArray;
    int Q, N, M, P, K, S, L;
    int orderCount;
    HashMap<Integer, Rabbit> rabbitMap;
    PriorityQueue<Rabbit> rabbits;


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
        Q = Integer.parseInt(BR.readLine());

        inputArray = getInputArray();
        N = inputArray[1];
        M = inputArray[2];
        P = inputArray[3];
        rabbitMap = new HashMap<>();
        rabbits = new PriorityQueue<>(Rabbit::compareRabbit);

        for (int i = 4; i < inputArray.length; i += 2) {
            Rabbit rabbit = new Rabbit(inputArray[i], inputArray[i + 1]);
            rabbits.add(rabbit);
            rabbitMap.put(rabbit.id, rabbit);
        }

        orderCount = 1;
    }


    void solution() throws IOException {
        while (orderCount < Q) {
            inputArray = getInputArray();
            if (inputArray[0] == 200) {
                K = inputArray[1];
                S = inputArray[2];

                HashMap<Integer, Integer> toMinus = new HashMap<>();
                Rabbit chosen = null;

                int toAdd = 0;
                for (int i = 0; i < K; i++) {
                    Rabbit rabbit = rabbits.remove();
//                    System.out.println("rabbit to move : " + rabbit.id + " from :" + rabbit.position.x + " " + rabbit.position.y);
                    moveRabbit(rabbit);
//                    System.out.println("moved to " + rabbit.position.x + " " + rabbit.position.y);
                    toAdd += rabbit.getPositionSum() + 2;
                    Integer minus = toMinus.getOrDefault(rabbit.id, 0);
                    toMinus.put(rabbit.id, minus + rabbit.getPositionSum() + 2);
                    rabbits.add(rabbit);
                }

                for (Rabbit rabbit : rabbits) {
                    rabbit.score += toAdd;
                    if (toMinus.containsKey(rabbit.id)) {
                        rabbit.score -= toMinus.get(rabbit.id);
                        if (chosen == null) {
                            chosen = rabbit;
                        } else {
                            if (chosen.compareWithPosition(rabbit) < 0) {
                                chosen = rabbit;
                            }
                        }
                    }
//                    System.out.println("score :" + rabbit.id + " " + rabbit.score);
                }

                chosen.score += S;
//                System.out.println("chosen : "+chosen.id +" "+chosen.score);

            } else if (inputArray[0] == 300) {
                int id = inputArray[1];
                L = inputArray[2];
                Rabbit rabbit = rabbitMap.get(id);
                rabbit.distance *= L;

            } else {
                long maxScore = 0;
                for (Rabbit rabbit : rabbits) {
                    maxScore = Math.max(maxScore, rabbit.score);
                }
                System.out.println(maxScore);
            }
            orderCount += 1;
        }
    }


    void moveRabbit(Rabbit rabbit) {
        PriorityQueue<Position> positionHeap = new PriorityQueue<>(Position::comparePosition);

        for (int i = 0; i < 4; i++) {
            Position current = new Position(rabbit.position.x, rabbit.position.y);
            int distance = rabbit.distance;
            int d = i;
            int repeat;
            int mod;
            int from;
            if (i == 0) {
                if (distance <= current.x) {
                    current.x -= distance;
                } else {
                    distance -= current.x;
                    repeat = distance / (N - 1);
                    mod = distance % (N - 1);

                    if (repeat % 2 == 0) {
                        from = 0;
                        d = OPPOSITE[d];
                    } else {
                        from = N - 1;
                    }

                    current.x = from + mod * DX[d];
                }
            } else if (i == 1) {
                if (distance <= N - 1 - current.x) {
                    current.x += distance;
                } else {
                    distance -= N - 1 - current.x;
                    repeat = distance / (N - 1);
                    mod = distance % (N - 1);
                    if (repeat % 2 == 0) {
                        from = N - 1;
                        d = OPPOSITE[d];
                    } else {
                        from = 0;
                    }
                    current.x = from + mod * DX[d];
                }
            } else if (i == 2) {
                if (distance <= current.y) {
                    current.y -= distance;
                } else {
                    distance -= current.y;
                    repeat = distance / (M - 1);
                    mod = distance % (M - 1);
                    if (repeat % 2 == 0) {
                        from = 0;
                        d = OPPOSITE[d];
                    } else {
                        from = M - 1;
                    }
                    current.y = from + mod * DY[d];
                }
            } else {
                if (distance <= M - 1 - current.y) {
                    current.y += distance;
                } else {
                    distance -= M - 1 - current.y;
                    repeat = distance / (M - 1);
                    mod = distance % (M - 1);
                    if (repeat % 2 == 0) {
                        from = M - 1;
                        d = OPPOSITE[d];
                    } else {
                        from = 0;
                    }
                    current.y = from + mod * DY[d];
                }
            }
            positionHeap.add(current);

        }
        rabbit.position = positionHeap.peek();
        rabbit.jumpCount += 1;
    }


    boolean isInner(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getPositionSum() {
            return this.x + this.y;
        }

        int comparePosition(Position compare) {
            if (this.getPositionSum() != compare.getPositionSum()) {
                return Integer.compare(compare.getPositionSum(), this.getPositionSum());
            }
            if (this.x != compare.x) {
                return Integer.compare(compare.x, this.x);
            }
            return Integer.compare(compare.y, this.y);
        }
    }

    static class Rabbit {
        int id;
        Position position;
        int distance;
        int jumpCount;
        long score;

        public Rabbit(int id, int distance) {
            this.id = id;
            this.distance = distance;
            this.position = new Position(0, 0);
            this.jumpCount = 0;
            this.score = 0;
        }

        int compareRabbit(Rabbit compare) {
            if (this.jumpCount != compare.jumpCount) {
                return Integer.compare(this.jumpCount, compare.jumpCount);
            }
            if (this.getPositionSum() != compare.getPositionSum()) {
                return Integer.compare(this.getPositionSum(), compare.getPositionSum());
            }
            if (this.position.x != compare.position.x) {
                return Integer.compare(this.position.x, compare.position.x);
            }
            if (this.position.y != compare.position.y) {
                return Integer.compare(this.position.y, compare.position.y);
            }
            return Integer.compare(this.id, compare.id);
        }

        int compareWithPosition(Rabbit compare) {
            if (this.getPositionSum() != compare.getPositionSum()) {
                return Integer.compare(this.getPositionSum(), compare.getPositionSum());
            }
            if (this.position.x != compare.position.x) {
                return Integer.compare(this.position.x, compare.position.x);
            }
            if (this.position.y != compare.position.y) {
                return Integer.compare(this.position.y, compare.position.y);
            }
            return Integer.compare(this.id, compare.id);
        }


        int getPositionSum() {
            return this.position.getPositionSum();
        }
    }
}
