import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    HashSet<Character> broken;
    int result;
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
        N = Integer.parseInt(BR.readLine());
        M = Integer.parseInt(BR.readLine());
        broken = new HashSet<>();
        if (M > 0) {

            String[] brokenButtons = BR.readLine().split(" ");
            for (String brokenButton : brokenButtons) {
                broken.add(brokenButton.charAt(0));
            }
        }
        result = Math.abs(N - 100);
    }

    void solution() throws IOException {
        for (int i = 0; i <= N + result; i++) {
            String toString = Integer.toString(i);
            boolean cantGo = false;
            for (int j = 0; j < toString.length(); j++) {
                if (broken.contains(toString.charAt(j))) {
                    cantGo = true;
                    break;
                }
            }
            if (!cantGo) {
                result = Math.min(result, toString.length() + Math.abs(N - i));
            }
        }
        System.out.println(result);
    }

}
