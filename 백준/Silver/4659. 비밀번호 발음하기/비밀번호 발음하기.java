import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    
    String password;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
	}
    
	void init() throws IOException {
        while (true) {
            password = BR.readLine();
            if (password.equals("end")) {
                break;
            }
            solve();
        }
        
	}
	
	void solve() {
		if (valid()) {
            System.out.println("<"+password+"> is acceptable.");
        } else {
            System.out.println("<"+password+"> is not acceptable.");
        }
	}
	
    boolean valid() {
        if (containsVowel()) {
            if (shorterThanTriple()) {
                if (noDouble()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    boolean shorterThanTriple() {
       int index = 0;
        while (index < password.length()) {
            boolean v = isVowel(password.charAt(index));
            int sameIndex = index;
            int length = 0;
            while (sameIndex < password.length() && v == isVowel(password.charAt(sameIndex))) {
                length += 1;
                sameIndex += 1;
            }
            if (length >=3) {
                return false;
            }
            index = sameIndex;
        }
        return true;
    }
    
    boolean noDouble() {
        for (int i = 0; i < password.length()-1; i++) {
            if (password.charAt(i) == password.charAt(i+1)) {
                if (password.charAt(i) == 'e' || password.charAt(i) == 'o') {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    boolean containsVowel() {
        for (int i = 0; i < password.length(); i++) {
            if (isVowel(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}