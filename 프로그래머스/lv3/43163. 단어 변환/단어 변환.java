import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        HashSet<String> visited = new HashSet<>();
        int answer = 0;
        List<String> cur = new ArrayList<>();
        cur.add(begin);
        visited.add(begin);

        while (cur.size() > 0) {
            List<String> temp = new ArrayList<>();
            for (String currentString : cur) {

                for (String word : words) {
                    if (!visited.contains(word)) {
                        int diff = 0;
                        for (int i = 0; i < word.length(); i++) {
                            if (word.charAt(i) != currentString.charAt(i)) {
                                diff += 1;
                            }
                        }
                        if (diff == 1) {
                            temp.add(word);
                            visited.add(word);
                        }
                    }

                }
            }
            answer += 1;
            if (visited.contains(target)) {
                return answer;
            }
            cur = temp;
        }
        return 0;
    }
}