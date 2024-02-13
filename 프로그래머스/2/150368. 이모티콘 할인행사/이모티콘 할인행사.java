import java.util.*;

/*
이모티콘 플러스 가입자 수를 늘리고, 이모티콘 판매액을 최대한 늘리는 것이 목표
1번 목표가 우선, 2번은 그 다음

n명에게 m개를 할인하여 판매,
이모티콘마다 할인율은 다를 수 있음, 10, 20, 30, 40

일정 비율 이상 할인하는 이모티콘 모두 구매, 
이모티콘 구매 비용 합이 일정 가격 이상이면, 이모티콘 플러스 가입

목표를 달성하는 가입자 수, 판매액 리턴
*/

class Solution {
    
    static final int[] DISCOUNT = {10, 20, 30, 40};
    int n;
    
    int[] answer;
    int[][] users;
    int[] emoticons;
    int[] discountRate;
    
    public int[] solution(int[][] users, int[] emoticons) {
        init(users, emoticons);
        solve();
        return answer;
    }
    
    void init(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        n = emoticons.length;
        discountRate = new int[n];
        answer = new int[2];
    }
    
    void solve() {
        dfs(0);
    }
    
    void dfs(int index) {
        if (index == n) {
            int joined = 0;
            int priceSum = 0;
            for(int[] user : users) {
                int discount = user[0];
                int threshold = user[1];
                int emoticonSum = 0;
                for (int i = 0; i < n; i++) {
                    if (discountRate[i] >= discount) {
                        int tempPrice = emoticons[i] * (100 - discountRate[i]) / 100;
                        emoticonSum += tempPrice;
                    }
                }
                if (emoticonSum >= threshold) {
                    joined += 1;
                } else {
                    priceSum += emoticonSum;
                }
            }
            
            if (joined > answer[0]) {
                answer[0] = joined;
                answer[1] = priceSum;
            } else if(joined == answer[0]) {
                answer[1] = Math.max(priceSum, answer[1]);
            }
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            discountRate[index] = DISCOUNT[i];
            dfs(index+1);
        }
    }
}