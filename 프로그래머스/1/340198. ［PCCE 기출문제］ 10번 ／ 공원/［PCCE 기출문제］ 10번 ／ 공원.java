import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        Arrays.sort(mats);
        for (int i = mats.length-1; i >= 0; i--) {
            int mat = mats[i];
            for (int x = 0; x < park.length; x++) {
                for (int y = 0; y < park[0].length; y++) {
                    if (canPut(x, y, mat, park)) {
                        return mat;
                    }
                }
            }
        }
        return -1;
    }
    
    boolean canPut(int x, int y, int mat, String[][] park) {
        for (int i = x; i < x+mat; i++) {
            for (int j = y; j < y+mat; j++) {
                if (!isInner(i, j, park) || !park[i][j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    boolean isInner(int x, int y, String[][] park) {
        return 0 <= x && x < park.length && 0 <= y && y < park[0].length;
    }
}