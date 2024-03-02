import java.util.*;

class Solution {
    int[] answer;
    int n, diff;
    int[] info;
    
    public int[] solution(int n, int[] info) {
        init(n, info);
        solve();
        return answer;
    }
    
    void init(int n, int[] info) {
        this.n = n;
        this.info = info;
        this.diff = 0;
        this.answer = new int[] {-1};
    }
    
    void solve() {
        dfs(n, new int[11], 0);
    }
    
    void dfs(int arrow, int[] hit, int index) {
        boolean canShoot = false;
        for (int i = index; i < 11; i++) {
            if (arrow > info[i]) {
                canShoot = true;
                hit[i] = info[i] + 1;
                dfs(arrow - info[i] - 1, hit, i + 1);
                hit[i] = 0;
            }
        }
        
        if (!canShoot) {
            int apeach = 0;
            int ryan = 0;
            for (int i = 0; i < 11; i++ ){
                if (Math.max(hit[i], info[i]) != 0) {
                    if (hit[i] > info[i]) {
                        ryan += 10 - i;
                    } else {
                        apeach += 10 - i;
                    }
                }
            }
            if (diff < ryan - apeach) {
                diff = ryan - apeach;
                answer = Arrays.copyOf(hit, 11);
                if (arrow != 0) {
                    for (int i = 10; i > -1; i--) {
                        if (answer[i] == 0) {
                            answer[i] = arrow;
                            break;
                        }
                    }
                }
            } else if (diff > 0 && diff == ryan - apeach) {
                int[] tempAnswer = Arrays.copyOf(hit, 11);
                for (int i = 10; i > -1; i--) {
                    if (Math.max(tempAnswer[i], answer[i]) != 0) {
                        if (tempAnswer[i] > answer[i]) {
                            answer = tempAnswer;
                            break;
                        } else if(answer[i] > tempAnswer[i]) {
                            break;
                        }
                    }
                    
                }
            }
        }
        
    }
}