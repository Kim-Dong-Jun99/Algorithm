import java.util.*;
import java.io.*;
class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int S;
    String[] command;
    int ALL;
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
        S = 0;
    }
    void solution() throws IOException {
        while (N -- > 0) {
            command = BR.readLine().split(" ");
            if (command.length == 1) {
                if (command[0].equals("all")) {
                    for (int i = 1; i<=20; i++) {
                        S |= 1 << i;
                    }
                } else {
                    S = 0;
                }
            } else {
                int value = Integer.parseInt(command[1]);
                if (command[0].equals("add")) {
                    S |= 1 << value;
                } else if (command[0].equals("remove")) {
                    S &= ~(1 << value);
                } else if (command[0].equals("toggle")) {
                    S ^= 1 << value;
                } else {
                    if (S == (S | 1<< value)) {
                        BW.write("1\n");
                    } else {
                        BW.write("0\n");
                    }
                }

            }
        }
        BW.flush();
        BW.close();
    }
}
