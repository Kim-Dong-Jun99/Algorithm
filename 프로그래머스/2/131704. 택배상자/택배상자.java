import java.util.*;

/*
n개의 상자, 번호 증가하는 순서대로 컨테이너 벨트
기사님이 알려주신 순서대로 택배상자를 실어야함,

*/

class Solution {
    int answer;
    int n;
    int[] order;
    Queue<Integer> boxes;
    Stack<Integer> stack;
    public int solution(int[] order) {
        init(order);
        solve();
        return answer;
    }
    
    void init(int[] order) {
        this.n = order.length;
        this.order = order;
        this.boxes = new LinkedList<>();
        this.stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            boxes.add(i+1);
        }
    }
    
    void solve() {
        for (int box : order) {
            if (!stack.isEmpty() && stack.peek() == box) {
                answer += 1;
                stack.pop();
                continue;
            }
            boolean packed = false;
            while (!boxes.isEmpty()) {
                int removed = boxes.remove();
                if (box == removed) {
                    answer += 1;
                    packed = true;
                    break;
                }
                stack.add(removed);
            }
            if (!packed) {
                break;
            }
        }
        
    }
}