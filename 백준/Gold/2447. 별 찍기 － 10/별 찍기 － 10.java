import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    char[][] toPrint;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }
    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        toPrint = new char[N][N];
    }

    void solve() throws IOException {
        fillBoard(0, 0, N);
        for (char[] print :toPrint) {
            for (char c : print) {
                BW.write(c);
            }
            BW.write("\n");
        }
        BW.flush();
        BW.close();
        BR.close();
    }

    void fillBoard(int x, int y, int n) {
        int div = n / 3;
        int visited = 0;
        if (div == 1) {
            for (int i = x; i < x + n; i += div) {
                for (int j = y; j < y + n; j += div) {
                    visited += 1;
                    if (visited == 5) {
                        fillWithBlank(i, j, div);
                    } else {
                        toPrint[i][j] = '*';
                    }
                }
            }
        } else {
            for (int i = x; i < x + n; i += div) {
                for (int j = y; j < y + n; j += div) {
                    visited += 1;
                    if (visited == 5) {
                        fillWithBlank(i, j, div);
                    } else {
                        fillBoard(i, j, div);
                    }
                }
            }
        }
    }

    void fillWithBlank(int x, int y, int div) {
        for (int i = x; i < x + div; i++) {
            for (int j = y; j < y + div; j++) {
                toPrint[i][j] = ' ';
            }
        }
    }
}