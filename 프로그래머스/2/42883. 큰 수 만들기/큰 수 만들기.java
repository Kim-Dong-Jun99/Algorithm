import java.util.*;

class Solution {
    String number;
    int k;
    public String solution(String number, int k) {
        init(number, k);
        return solve();
    }

    void init(String number, int k) {
        this.number = number;
        this.k = k;
    }

    String solve() {
        StringBuilder sb = new StringBuilder();
        int deleted = 0;
        for (int i = 0; i < number.length(); i++) {
            Character c = number.charAt(i);
            if (sb.length() == 0) {
                sb.append(c);
            } else {
                while (sb.length() > 0 && Character.getNumericValue(sb.charAt(sb.length() - 1)) < Character.getNumericValue(c) && deleted < k) {
                    sb.deleteCharAt(sb.length() - 1);
                    deleted += 1;
                }
                sb.append(c);

            }
        }
        while (deleted < k) {
            sb.deleteCharAt(sb.length() - 1);
            deleted += 1;
        }
        return sb.toString();
    }
}