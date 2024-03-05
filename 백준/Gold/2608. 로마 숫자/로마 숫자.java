import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final char M = 'M';
    static final char D = 'D';
    static final char C = 'C';
    static final char L = 'L';
    static final char X = 'X';
    static final char V = 'V';
    static final char I = 'I';
    int[] inputArray;
    String a, b;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        a = BR.readLine();
        b = BR.readLine();
    }

    void solve() throws IOException {
        int sum = stringToInt(a) + stringToInt(b);
        System.out.println(sum);
        System.out.println(intToString(sum));
    }

    int stringToInt(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == M) {
                sum += 1000;
            }
            if (c == D) {
                sum += 500;
            }
            if (c == C) {
                if (i + 1 < str.length() && (str.charAt(i + 1) == D || str.charAt(i + 1) == M)) {
                    if (str.charAt(i + 1) == D) {
                        sum += 400;
                    } else {
                        sum += 900;
                    }
                    i += 1;
                } else {
                    sum += 100;
                }
            }
            if (c == L) {
                sum += 50;
            }
            if (c == X) {
                if (i + 1 < str.length() && (str.charAt(i + 1) == L || str.charAt(i + 1) == C)) {
                    if (str.charAt(i+1) == L) {
                        sum += 40;
                    } else {
                        sum += 90;
                    }
                    i += 1;
                } else {
                    sum += 10;
                }
            }
            if (c == V) {
                sum += 5;
            }
            if (c == I) {
                if (i + 1 < str.length() && (str.charAt(i + 1) == V || str.charAt(i + 1) == X)) {
                    if (str.charAt(i+1) == V) {
                        sum += 4;
                    } else {
                        sum += 9;
                    }
                    i += 1;
                } else {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    String intToString(int number) {
        String str = Integer.toString(number);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int digit = str.length() - i;
            int value = Character.getNumericValue(str.charAt(i));
            if (digit == 4) {
                for (int j = 0; j < value; j++) {
                    sb.append(M);
                }
                continue;
            }
            if (digit == 3) {
                if (value == 0) {
                    continue;
                }
                if (value == 4 || value == 9) {
                    sb.append(C);
                    if (value == 4) {
                        sb.append(D);
                    } else {
                        sb.append(M);
                    }
                } else {
                    if (value >= 5) {
                        sb.append(D);
                        value -= 5;
                    }
                    for (int j = 0; j < value; j++) {
                        sb.append(C);
                    }
                }
                continue;
            }
            if (digit == 2) {
                if (value == 0) {
                    continue;
                }
                if (value == 4 || value == 9) {
                    sb.append(X);
                    if (value == 4) {
                        sb.append(L);
                    } else {
                        sb.append(C);
                    }
                } else {
                    if (value >= 5) {
                        sb.append(L);
                        value -= 5;
                    }
                    for (int j = 0; j < value; j++) {
                        sb.append(X);
                    }
                }
                continue;
            }
            if (digit == 1) {
                if (value == 0) {
                    continue;
                }
                if (value == 4 || value == 9) {
                    sb.append(I);
                    if (value == 4) {
                        sb.append(V);
                    } else {
                        sb.append(X);
                    }
                } else {
                    if (value >= 5) {
                        sb.append(V);
                        value -= 5;
                    }
                    for (int j = 0; j < value; j++) {
                        sb.append(I);
                    }
                }
            }
        }
        return sb.toString();
    }
}