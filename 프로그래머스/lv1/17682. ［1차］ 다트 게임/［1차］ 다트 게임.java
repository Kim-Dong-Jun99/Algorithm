import java.util.*;
class Solution {
    public int solution(String dartResult) throws Exception {
        int answer = 0;
        int index = -1;
        int[] scores = new int[3];
        for (int i = 0; i < dartResult.length(); i ++) {
            if (Character.isDigit(dartResult.charAt(i))) {
                index += 1;
                if (dartResult.charAt(i) == '1' && dartResult.charAt(i+1) == '0'){
                    scores[index] = 10;
                    i += 1;
                } else {
                    scores[index] = Character.getNumericValue(dartResult.charAt(i));
                }
                
                
            } else {
                if (Character.isAlphabetic(dartResult.charAt(i))) {
                    if (dartResult.charAt(i) == 'D') {
                        scores[index] = (int) Math.pow(scores[index], 2);
                    } else if (dartResult.charAt(i) == 'T') {
                        scores[index] = (int) Math.pow(scores[index], 3);
                    }
                } else {
                    if (dartResult.charAt(i) == '*') {
                        scores[index] *= 2;
                        if (index >= 1) {
                            scores[index - 1] *= 2;
                        }
                    } else {
                        scores[index] *= -1;
                    }
                }
            }
        }
                
        for (int i : scores) {
            answer += i;
        }
        return answer;
    }
}