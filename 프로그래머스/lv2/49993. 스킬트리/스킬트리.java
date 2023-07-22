import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        if (skill.length() == 1) {
            return skill_trees.length;
        }

        int answer = 0;
        for (int i = 0; i < skill_trees.length; i++) {
            String tmp = skill_trees[i].replaceAll("[^" + skill + " ]", "");

            if (tmp.equals("")) {
                answer++;
                continue;
            }

            for (int j = 1; j <= skill.length(); j++) {
                if (skill.substring(0, j).equals(tmp)) {
                    answer++;
                }
            }

        }

        return answer;
    }
}