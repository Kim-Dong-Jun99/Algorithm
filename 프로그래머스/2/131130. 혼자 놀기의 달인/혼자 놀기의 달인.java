import java.util.*;

/*
총 100장 카드, 1 ~ 100,
2 이상 100이하 자연수 하나 정함

그 수 이하 숫자 카드들을 준비하고, 숫자 카드를 각각 작은 상자에 넣고 게임 시작



*/

class Solution {
    
    int answer;
    int[] cards;
    boolean[] visited;
    
    public int solution(int[] cards) {
        init(cards);
        solve();
        return answer;
    }
    
    void init(int[] cards) {
        this.cards = new int[cards.length];
        for (int i = 0; i < cards.length; i++) {
            this.cards[i] = cards[i] - 1;
        }
        visited = new boolean[cards.length];
    }
    
    void solve() {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        heap.add(0);
        for (int i = 0; i < cards.length; i++) {
            if (!visited[i]) {
                heap.add(dfs(i));
            }
        }
        answer = heap.remove() * heap.remove();
    }
    
    int dfs(int index) {
        if (visited[index]) {
            return 0;
        }
        
        visited[index] = true;
        return 1 + dfs(cards[index]);
    }
}