import java.util.*;

class Solution {
    int answer;
    List<Range> ranges;
    public int solution(int[][] targets) {
        init(targets);
        solve();
        return answer;
    }
    
    void init(int[][] targets) {
        answer = 0;
        ranges = new ArrayList<>();
        for (int[] target : targets) {
            ranges.add(new Range(target[0], target[1]));
        }
        ranges.sort(Range::sortWithSAndE);
    }
    
    void solve() {
        Range current = null;
        for (Range range : ranges) {
            if (current == null || !overlap(current, range)) {
                answer += 1;
                current = range;
            } else {
                current = new Range(Math.max(current.s, range.s), Math.min(current.e, range.e));
            }
        }
        
    }
    
    boolean overlap(Range current, Range range) {
        if (current.s >= range.e || current.e <= range.s) {
            return false;
        }
        return true;
    }
    
    public static class Range {
        int s;
        int e;
        
        public Range(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        public int sortWithSAndE(Range compare) {
            if (this.s != compare.s) {
                return Integer.compare(this.s, compare.s);
            }
            return Integer.compare(this.e, compare.e);
        }
    }
}