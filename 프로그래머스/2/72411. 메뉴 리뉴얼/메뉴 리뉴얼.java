import java.util.*;

/*

*/

class Solution {
    String[] answer;
    String[] orders;
    int[] course;
    HashMap<String, Integer> setMap;
    public String[] solution(String[] orders, int[] course) {
        init(orders, course);
        solve();
        return answer;
    }
    
    void init(String[] orders, int[] course) {
        this.orders = orders;
        this.course = course;
        this.setMap = new HashMap<>();
    }
    
    void solve() {
        for (String order : orders) {
            addToSet(order);
        }
        List<String> answerList = new ArrayList<>();
        
        for (int size : course) {
            addToAnswerList(size, answerList);
        }
        answer = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        Arrays.sort(answer);
    }
    
    void addToSet(String order) {
        char[] cs = new char[order.length()];
        for (int i = 0; i < order.length(); i++) {
            cs[i] = order.charAt(i);
        }
        boolean[] visited = new boolean[cs.length];
        Arrays.sort(cs);
        dfs(0, "", cs, visited);
    }
    
    void dfs(int index, String s, char[] cs, boolean[] visited) {
        if (index != 0) {
            setMap.put(s, setMap.getOrDefault(s, 0) + 1);
        }
        for (int i = index; i < cs.length; i++ ){
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, s + cs[i], cs, visited);
                visited[i] = false;
            }
        }
    }
    
    void addToAnswerList(int size, List<String> answerList) {
        int temp = 2;
        List<String> toAdd = new ArrayList<>();
        for (String s : setMap.keySet()) {
            if (s.length() != size) {
                continue;
            }
            if (temp < setMap.get(s)) {
                temp = setMap.get(s);
                toAdd = new ArrayList<>();
                toAdd.add(s);
            } else if (temp == setMap.get(s)) {
                toAdd.add(s);
            }
        }
        for (String s : toAdd) {
            answerList.add(s);
        }
    }
}