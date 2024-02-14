import java.util.*;

/*
비 내림차순으로 정렬된 수열이 주어짐, 

연속된 부분 수열의 합이 k인 가장 짧은 부분 수열의 왼쪽, 오른쪽 인덱스를 리턴
*/

class Solution {
    int n;
    int[] answer;
    int[] sequence;
    int k, minLen, subSum;
    int left, right;
    
    public int[] solution(int[] sequence, int k) {
        init(sequence, k);
        solve();
        return answer;
    }
    
    void init(int[] sequence, int k) {
        answer = new int[2];
        this.sequence = sequence;
        n = sequence.length;
        this.k = k;
        left = 0;
        right = 0;
        minLen = n;
        subSum = sequence[0];
    }
    
    void solve() {
        while (right < n) {
            if (subSum == k) {
                int tempLen = right - left;
                if (tempLen < minLen) {
                    minLen = tempLen;
                    answer[0] = left;
                    answer[1] = right;
                }
                right += 1;
                if (right < n) {
                    subSum += sequence[right];
                }
            } else if (subSum < k) {
                right += 1;
                if (right < n) {
                    subSum += sequence[right];
                }
            } else {
                if (left < right) {
                    subSum -= sequence[left];
                    left += 1;
                } else {
                    break;
                }
                
            }
        }
    }
}