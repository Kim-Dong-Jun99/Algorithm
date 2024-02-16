import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    String S;
    Queue<Integer> As;
    Queue<Integer> Bs;
    Queue<Integer> Cs;
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        S = BR.readLine();
        As = new ArrayDeque<>();
        Bs = new ArrayDeque<>();
        Cs = new ArrayDeque<>();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'A') {
                As.add(i);
            } else if (S.charAt(i) == 'B') {
                Bs.add(i);
            } else {
                Cs.add(i);
            }
        }
    }

    void solution() {
        long answer = 0;
        while (!Cs.isEmpty()) {
            if (!Bs.isEmpty()) {
                if (Cs.peek() > Bs.peek()) {
                    Cs.remove();
                    Bs.remove();
                    answer += 1;
                } else {
                    Cs.remove();
                }
            } else {
                break;
            }
        }

        while (!Bs.isEmpty()) {
            if (!As.isEmpty()) {
                if (Bs.peek() > As.peek()) {
                    Bs.remove();
                    As.remove();
                    answer += 1;
                } else {
                    Bs.remove();
                }
            } else {
                break;
            }
        }
        System.out.println(answer);
    }


}