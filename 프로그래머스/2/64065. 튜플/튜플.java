import java.util.*;

class Solution {
    int[] answer;
    String s;
    int maxLength;
    HashMap<Integer, List<Integer>> map;
    public int[] solution(String s) {
        init(s);
        solve();
        return answer;
    }
    
    void init(String s) {
        this.s = s;
        map = new HashMap<>();
        initMap();
    }
    
    void initMap() {
        StringBuilder sb = new StringBuilder();
        List<Integer> group = new ArrayList<>();
        boolean inGroup = false;
        for (int i = 1; i < s.length()-1; i++) {
            
            if (s.charAt(i) == '{') {
                sb = new StringBuilder();
                group = new ArrayList<>();
                inGroup = true;
            }
            if (s.charAt(i) == '}') {
                inGroup = false;
                group.add(Integer.parseInt(sb.toString()));
                maxLength = Math.max(maxLength, group.size());
                map.put(group.size(), group);
            }
            if (s.charAt(i) == ',') {
                if (inGroup) {
                    group.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                } else {
                    continue;
                }
            }
            if (Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
    }
    

    void solve() {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <= maxLength; i++) {
            List<Integer> temp = map.get(i);
            for (Integer t : temp) {
                if (!visited.contains(t)) {
                    visited.add(t);
                    result.add(t);
                    break;
                }
                
            }
        }
        
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
    }
}