import java.io.*;
import java.util.*;
	
/*
	
*/
	
class Main {
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    
    int[] inputArray;
    int N, K;
    boolean[] visited;
    int[] digits;
    
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		main.solve();
	}
    
    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
	void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        K = inputArray[1];
        visited = new boolean[1_000_001];
        digits = new int[7];
        initDigits();
	}
    
    void initDigits() {
        int num = 1;
        for (int i = 0; i < 7; i++) {
            digits[i] = num;
            num *= 10;
        }
    }
	
	void solve() {
        List<Integer> cur = new ArrayList<>();
        cur.add(N);
        int changed = 0;
		while (changed < K && !cur.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            visited = new boolean[1_000_001];
            for (Integer c : cur) {
                for (Integer next : getNext(c)) {
                    if (!visited[next]) {
                        temp.add(next);
                        visited[next] = true;
                    }
                }
            }
            changed += 1;
            cur = temp;
        }
        if (cur.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(cur.stream().max(Integer::compare).get());
        }
	}
    
    List<Integer> getNext(int c) {
        List<Integer> next = new ArrayList<>();
        int len = getLength(c);
        String str = Integer.toString(c);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (str.charAt(j) == '0' && i == 0) {
                    continue;
                }
                // if (str.charAt(j) == str.charAt(i)) {
                //     continue;
                // }
                int a = Character.getNumericValue(str.charAt(i));
                int aDigit = len - i - 1;
                int b = Character.getNumericValue(str.charAt(j));
                int bDigit = len - j - 1;
                int newNum = c;
                newNum -= digits[aDigit] * a;
                newNum -= digits[bDigit] * b;
                newNum += digits[aDigit] * b;
                newNum += digits[bDigit] * a;
                next.add(newNum);
            }
        }
        
        return next;
    }
    
    
    int getLength(int c) {
        int len = 1;
        int ten = 10;
        while (c / ten > 0) {
            len += 1;
            ten *= 10;
        }
        return len;
    }
}