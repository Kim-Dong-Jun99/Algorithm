import java.io.*;

/*
N개의 문자열 쌍 S, T가 주어짐
S에서 글자 x가 등장할 위치에서 T 글자를 읽으면 된다

 */

public class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final Character x = 'x';
    public static final Character X = 'X';

    int N;
    String[] stringCouples;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        stringCouples = new String[N];
        for (int i = 0; i < N; i++) {
            stringCouples[i] = BR.readLine();
        }
    }

    void solve() throws IOException {
        for (String stringCouple : stringCouples) {
            String[] stringArray = stringCouple.split(" ");
            int length = stringArray[0].length();
            String s = stringArray[0];
            String t = stringArray[1];
            for (int i = 0; i < length; i++) {
                if (isP(s, i)) {
                    char toPrint = t.charAt(i);
                    BW.write(Character.toString(toPrint).toUpperCase());
                }
            }

        }
        BW.flush();
        BW.close();
    }


    boolean isP(String s, int index) {
        return s.charAt(index) == X || s.charAt(index) == x;
    }
}
