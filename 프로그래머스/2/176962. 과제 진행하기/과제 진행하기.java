import java.util.*;

/*
시작하기로 한 시각이 되면 과제 시작,

새로운 과제를 시작할 시각에, 진행중이던 과제가 있다면, 멈추고 새로운 과제,

진행중이던 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면, 멈춰둔 과제를 이어서, 

과제 끝난 순서대로 이름을 answer에 담기
*/

class Solution {
    
    String[] answer;
    int n;
    Work[] works;
    Stack<Work> workStack;
    Stack<Integer> timeStack;
    int index;
    
    
    public String[] solution(String[][] plans) {
        init(plans);
        solve();
        return answer;
    }
    
    void init(String[][] plans) {
        n = plans.length;
        works = new Work[n];
        for (int i = 0; i < n; i++) {
            String[] plan = plans[i];
            works[i] = new Work(plan[0], strToInt(plan[1]), Integer.parseInt(plan[2]));
        }
        Arrays.sort(works, Work::orderByStart);
        answer = new String[n];
        workStack = new Stack<>();
        timeStack = new Stack<>();
        index = 0;
    }
    
    void solve() {
        for (Work work : works) {
            if (workStack.isEmpty()) {
                workStack.push(work);
                timeStack.push(work.start);
            } else {
                int timeGap = work.start - timeStack.peek();
                while (!workStack.isEmpty()) {
                    if (timeGap >= workStack.peek().play) {
                        timeGap -= workStack.peek().play;
                        answer[index] = workStack.peek().name;
                        workStack.pop();
                        index += 1;
                    } else {
                        workStack.peek().play -= timeGap;
                        break;
                    }
                }
                workStack.push(work);
                timeStack.push(work.start);
            }
        }
        while (!workStack.isEmpty()) {
            Work work = workStack.pop();
            answer[index] = work.name;
            index += 1;
         }
    }
    
    int strToInt(String time) {
        String[] splitTime = time.split("\\:");
        return Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
        
    }
    
    public static class Work {
        String name;
        int start;
        int play;
        
        public Work(String name, int start, int play) {
            this.name = name;
            this.start = start;
            this.play = play;
        }
        
        int orderByStart(Work compare) {
            return Integer.compare(this.start, compare.start);
        }
    }
}