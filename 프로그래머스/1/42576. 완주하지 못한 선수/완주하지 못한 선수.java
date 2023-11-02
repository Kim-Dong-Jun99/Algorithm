import java.util.*;

/*

*/

class Solution {
    HashMap<String, Integer> p;
    public String solution(String[] participant, String[] completion) {
        p = new HashMap<>();
        for (String part : participant) {
            Integer count = p.getOrDefault(part, 0);
            p.put(part, count + 1);
        }
        for (String c : completion) {
            Integer i = p.get(c);
            i -= 1;
            if (i == 0) {
                p.remove(c);
            } else {
                p.put(c, i);
            }
        }

        for (String result : p.keySet()) {
            return result;
        }
        return "";
    }

}


