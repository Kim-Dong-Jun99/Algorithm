import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    
    int[] inputArray;
    int a, b, c;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        while (true) {
            inputArray = getInputArray();
            a = inputArray[0];
            b = inputArray[1];
            c = inputArray[2];
            if (a == 0 && b == 0 && c == 0) {
                break;
            }
            solve();
        }
	}
	
	void solve() {
		if (a == b && b == c) {
            System.out.println("Equilateral");
        } else if (a == b || b == c || a == c) {
            if (invalid()) { 
                System.out.println("Invalid");
            } else  {
                System.out.println("Isosceles");
            }
        } else {
            if (invalid()) { 
                System.out.println("Invalid");
            } else {
                System.out.println("Scalene");
            }
        }
	}
    
    boolean invalid() {
        if (a > b) {
            int temp = b;
            b = a;
            a = temp;
        }
        if (b > c) {
            int temp = c;
            c = b;
            b = temp;
        }
        if (a > b) {
            int temp = b;
            b = a;
            a = temp;
        }
        if (c < a+b) {
            return false;
        } 
        return true;    
    }
	
}