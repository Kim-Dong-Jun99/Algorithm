import java.util.*;

class Solution {
    static final int[] MULTIPLE = {2, 3, 4};
    long answer;
    HashMap<Integer, Long> weightMaps;
    public long solution(int[] weights) {
        init(weights);
        solve();
        return answer;
    }
    
    void init(int[] weights) {
        weightMaps = new HashMap<>();
        for (int weight : weights) {
            weightMaps.putIfAbsent(weight, 0l);
            weightMaps.put(weight, weightMaps.get(weight) + 1);
        }
        answer = 0l;
    }
    
    void solve() {
        answer += countSameKind();
        answer += countDifferentKind();
    }
    
    long countSameKind() {
        long result = 0l;
        for (int key : weightMaps.keySet()) {
            long value = weightMaps.get(key);
            if (value > 1) {
                result += value * (value - 1) / 2;
            }
        }
        
        return result;
    }
    
    long countDifferentKind() {
        long result = 0l;
        for (int key : weightMaps.keySet()) {
            long count =  weightMaps.get(key);
            HashSet<Integer> possible = getPossibleSet(key);
            for (int weight : weightMaps.keySet()) {
                if (key >= weight) {
                    continue;
                }
                for (int m : MULTIPLE) {
                    if (possible.contains(m * weight)) {
                        result += count * weightMaps.get(weight);
                        break;
                    }
                }
            }
        }
        
        return result;
    }
    
    HashSet<Integer> getPossibleSet(int key) {
        HashSet<Integer> result = new HashSet<>();
        for (int m : MULTIPLE) {
            result.add(key * m);
        } 
        return result;
    }
}