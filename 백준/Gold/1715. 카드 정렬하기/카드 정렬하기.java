import java.util.*;
import java.io.*;
class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    PriorityQueue<Integer> cards;
    int answer;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch(IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        cards = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            cards.add(Integer.parseInt(BR.readLine()));
        }
        answer = 0;
    }
    void solution() throws IOException {
        while (cards.size() > 1) {
            int smallest = cards.remove();
            smallest += cards.remove();
            answer += smallest;
            cards.add(smallest);
        }
        System.out.println(answer);
    }


}
