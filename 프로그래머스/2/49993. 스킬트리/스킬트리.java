import java.util.*;
class Solution {
    int answer;
    String skill;
    String[] skillTrees;
    public int solution(String skill, String[] skill_trees) {
        init(skill, skill_trees);
        solve();
        return answer;
    }
    
    void init(String skill, String[] skill_trees) {
        answer = 0;
        this.skill = skill;
        this.skillTrees = skill_trees;
    }
    
    void solve() {
        HashSet<Character> mustLearn = new HashSet<>();
        for (int i = 0; i < skill.length(); i++) {
            mustLearn.add(skill.charAt(i));
        }
        for (String skillTree : skillTrees) {
            boolean breaked = false;
            int learnIndex = 0;
            for (int i = 0; i < skillTree.length(); i++) {
                if (mustLearn.contains(skillTree.charAt(i))) {
                    if (skillTree.charAt(i) != skill.charAt(learnIndex)) {
                        breaked = true;
                        break;
                    } else {
                        learnIndex += 1;
                    }
                }
            }
            if (!breaked) {
                answer += 1;
            }
            
        }
    }
}