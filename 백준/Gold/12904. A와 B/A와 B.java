import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final char A = 'A';
    static final char B = 'B';
    int[] inputArray;
    String S, T;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        S = BR.readLine();
        T = BR.readLine();
	}
	
	void solve() throws IOException {
        int leftIndex = 0;
        int rightIndex = T.length() - 1;
        boolean flipped = false;
        for (int i = T.length(); i > S.length(); i--) {
            if (flipped) {
                if (T.charAt(rightIndex) == A) {
                    rightIndex += 1;
                } else {
                    flipped = false;
                    rightIndex += 1;
                    int temp = rightIndex;
                    rightIndex = leftIndex;
                    leftIndex = temp;
                }
            } else {
                if (T.charAt(rightIndex) == A) {
                    rightIndex -= 1;
                } else {
                    flipped = true;
                    rightIndex -= 1;
                    int temp = rightIndex;
                    rightIndex = leftIndex;
                    leftIndex = temp;
                }
            }
        }
        
        if (flipped) {
            for (int i = leftIndex; i >= rightIndex; i--) {
                if (S.charAt(leftIndex - i) != T.charAt(i)) {
                    System.out.println(0);
                    return;
                }
            }
        } else {
            for (int i = leftIndex; i <= rightIndex; i++) {
                if (S.charAt(i - leftIndex) != T.charAt(i)) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
	}
}