import java.util.*;

class Solution {
    int answer;
    String s;
    public int solution(String s) {
        init(s);
        solve();
        return answer;
    }
    
    void init(String s) {
        this.s = s;
        answer = s.length();
    }
    
    void solve() {
        for (int i = 1; i <= s.length()/2; i++) {
            int length = 0;
            int index = 0;
            while (index < s.length()) {
                if (canZip(index, i, 1)) {
                    int multi = 1;
                    while (canZip(index, i, multi)) {
                        multi += 1;
                    }
                    length += Integer.toString(multi).length() + i;
                    index += multi * i;
                    
                } else {
                    index += i;
                    length += i;
                }
            }
            if (index > s.length()) {
                length -= index - s.length();
            }
            answer = Math.min(answer, length);
        }
    }
    
    boolean canZip(int index, int length, int multi) {
        int compareIndex = index + length * multi;
        for (int i = 0; i < length; i++) {
            if (isInner(index) && isInner(compareIndex)) {
                if (s.charAt(index) != s.charAt(compareIndex)) {
                    return false;
                }
            } else {
                return false;
            }
            index += 1;
            compareIndex += 1;
        }
        
        return true;
    }
    
    boolean isInner(int index) {
        return 0 <= index && index < s.length();
    }
}