import java.util.*;
/*
XYZ 마트는 일정 금액을 지불하면 10일 동안 회원 자격을 부여,
회원을 대상으로 매일 한 가지 제품을 할인

원하는 제품과 수량이 할인하는 날짜와 10일 연속 일치할 경우 회원가입
*/

class Solution {
    int answer;
    int n, m;
    String[] want, discount;
    int[] number;
    
    public int solution(String[] want, int[] number, String[] discount) {
        init(want, number, discount);
        solve();
        return answer;
    }
    
    void init(String[] want, int[] number, String[] discount) {
        this.n = want.length;
        this.m = discount.length;
        this.want = want;
        this.number = number;
        this.discount = discount;
        this.answer = 0;
    }
    
    void solve() {
        for (int i = 0; i + 10 <= m; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = 0; j < 10; j++) {
                map.putIfAbsent(discount[i+j], 0);
                map.put(discount[i+j], map.get(discount[i+j]) + 1);
            }
            boolean canSignUp = true;
            for (int j = 0; j < n; j++) {
                if (number[j] != map.getOrDefault(want[j], 0)) {
                    canSignUp = false;
                    break;
                }
            }
            if (canSignUp) {
                answer += 1;
            }
        }
    }
}