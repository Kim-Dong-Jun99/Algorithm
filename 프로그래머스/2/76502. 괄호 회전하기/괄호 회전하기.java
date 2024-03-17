import java.util.*;

class Solution {
    int answer, n;
    String s;
    public int solution(String s) {
        init(s);
        solve();
        return answer;
    }
    
    void init(String s) {
        this.answer = 0;
        this.s = s;
        this.n = s.length();
    }
    
    void solve() {
        for (int i = 0; i < n; i++) {
            if (isRight()) {
                answer += 1;
            } 
            s = s.substring(1) + s.substring(0,1);
        }
    }
    
    boolean isRight() {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.add(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character removed = stack.pop();
                if (c == ')' && removed == '(') {
                    continue;
                }
                if (c == ']' && removed == '[') {
                    continue;
                }
                if (c == '}' && removed == '{') {
                    continue;
                }
                return false;
            }
            
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}