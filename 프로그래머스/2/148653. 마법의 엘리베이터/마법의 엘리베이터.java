import java.util.*;

class Solution {
    int answer;
    int storey;
    
    public int solution(int storey) {
        init(storey);
        solve();
        return answer;
    }
    
    void init(int storey) {
        this.storey = storey;
        this.answer = 0;
    }
    
    void solve() {
        while (storey > 0) {
            int digit = storey % 10;
            storey = storey / 10;
            if (digit == 5) {
                if (storey % 10 >= 5) {
                    answer = answer + (10 - digit);
                    storey++;
                }
                else {
                    answer = answer + digit;    
                }
            }
            else if (digit > 5) {
                answer = answer + (10 - digit);
                storey++;
            }
            else {
                answer = answer + digit;
            }

        }
    }
}