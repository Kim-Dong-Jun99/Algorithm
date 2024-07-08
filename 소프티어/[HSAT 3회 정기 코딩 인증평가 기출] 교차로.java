import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    int[] inputArray;
    int N;
    Queue[] queues;
    int[] answer;

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.init();
        m.solve();
    }

    public void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        queues = new Queue[4];
        for (int i = 0; i < 4; i++) {
            queues[i] = new LinkedList<Car>();
        }
        for (int i = 0; i < N; i++) {
            String[] temp = BR.readLine().split(" ");
            int t = Integer.parseInt(temp[0]);
            char w = temp[1].charAt(0);
            queues[getCarIndex(w)].add(new Car(i, t));
        }
        answer = new int[N];
        Arrays.fill(answer, -1);
    }

    int getCarIndex(char w) {
        return w - 'A';
    }

    public void solve() {
        int currentTime = -1;
        boolean[] isWaiting = new boolean[4];
        while (!isEnd()) {
            int minTime = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                if (!queues[i].isEmpty()) {
                    int time = ((Car)queues[i].peek()).time;
                    minTime = Math.min(minTime, time);
                    if (time <= currentTime) {
                        isWaiting[i] = true;
                    }
                }
            }
            int waitingCar = getWaitingCar(isWaiting);
            if (waitingCar == 4) {
                break;
            }
            if (waitingCar == 0) {
                currentTime = minTime;
                continue;
            }
            for (int i = 0; i < 4; i++) {
                if (isWaiting[i] && !isWaiting[(i+3)%4]) { 
                    Car c = (Car) queues[i].poll();
                    answer[c.index] = currentTime;
                }
            }
            Arrays.fill(isWaiting, false);
            currentTime += 1;
        }
        for (int i = 0; i < N; i++) {
            System.out.println(answer[i]);
        }
    }

    boolean isEnd() {
        return queues[0].isEmpty() && queues[1].isEmpty() && queues[2].isEmpty() && queues[3].isEmpty();
    }

    int getWaitingCar(boolean[] isWaiting) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (isWaiting[i]) {
                sum += 1;
            }
        }
        return sum;
    } 

    static class Car {
        int time;
        int index;

        Car(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}
