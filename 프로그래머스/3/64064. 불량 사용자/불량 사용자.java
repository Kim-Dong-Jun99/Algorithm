import java.util.*;

/*
이벤트 응모자 아이디 목록이랑 불랼 사용자 아이디 목록이 담긴 배열이 주어질때,
당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한지

제재 아이디 별로 가능한 응모자 아이디가 있음
*/

class Solution {
    static final Character ANY = '*';
    HashMap<String,HashSet<Integer>> candidateMap;
    String[] banned;
    public int solution(String[] user_id, String[] banned_id) {
        init(user_id, banned_id);
        return solve();
    }

    void init(String[] user_id, String[] banned_id) {
        candidateMap = new HashMap<>();
        banned = banned_id;
        for (String banned : banned_id) {
            HashSet<Integer> possible = new HashSet<>();
            for (int i = 0; i < user_id.length; i++) {
                String userId = user_id[i];
                if (isPossible(banned, userId)) {
                    possible.add(i);
                }
            }
            candidateMap.put(banned, possible);
        }
    }

    boolean isPossible(String banned, String userId) {
        if (banned.length() == userId.length()) {
            int n = userId.length();
            for (int i = 0; i < n; i++) {
                if (banned.charAt(i) != ANY && banned.charAt(i) != userId.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    int solve() {
        HashSet<Integer> visited = new HashSet<>();
        /*
        dfs
         */
        dfs(0, 0, visited);

        return visited.size();
    }

    void dfs(int bannedIndex, int taken, HashSet<Integer> visited) {
        if (bannedIndex == banned.length) {
            visited.add(taken);
            return;
        }
        HashSet<Integer> possible = candidateMap.get(banned[bannedIndex]);
        for (Integer index : possible) {
            if (taken != (taken | (1 << index))) {
                dfs(bannedIndex + 1, taken | (1 << index), visited);
            }
        }
    }

}


