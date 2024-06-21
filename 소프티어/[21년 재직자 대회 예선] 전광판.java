import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[][] NUMBER = {
        {1, 0, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 1, 0},
        {1, 1, 1, 0, 1, 0, 1},
        {0, 1, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 0, 0, 1},
        {1, 1, 1, 1, 0, 1, 1},
        {1, 0, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 0, 1}
    };
    static final int[] EMPTY = {0, 0, 0, 0, 0, 0, 0};
    int T;
    String[] inputArray;
    String[][] numbers;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
		main.printResult();
	}
    
    String[] getInputArray() throws IOException {
        return BR.readLine().split(" ");
    }
    
	void init() throws IOException {
        T = Integer.parseInt(BR.readLine());
        numbers = new String[T][2];
        for (int i = 0; i < T; i++)  {
            String[] temp = getInputArray();
            numbers[i][0] = toFormat(temp[0]);
            numbers[i][1] = toFormat(temp[1]);
        }
	}

    String toFormat(String str) {
        String temp = "";
        while (temp.length() + str.length() < 5) {
            temp += ".";
        }
        return temp + str;
    }
	
	void solve() throws IOException {
		for (String[] number : numbers) {
            String from = number[0];
            String to = number[1];
            int answer = 0;
            for (int i = 0; i < 5; i++) {
                answer += getDiff(from.charAt(i), to.charAt(i));
            }
            System.out.println(answer);
        }
	}

    int getDiff(char a, char b) {
        int press = 0;
        int[] A, B;
        if (a == '.') {
            A = EMPTY;
        } else {
            A = NUMBER[Character.getNumericValue(a)];
        }
    
        if (b == '.') {
            B = EMPTY;
        } else {
            B = NUMBER[Character.getNumericValue(b)];
        }
        for (int d = 0; d < 7; d++) {
            if (A[d] != B[d]) {
                press += 1;
            }
        }
        return press;
    }
	
	void printResult() throws IOException {	
		BW.flush();
		BW.close();
		BR.close();
	}
}
