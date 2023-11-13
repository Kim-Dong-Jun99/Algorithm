import java.util.*;

class Solution {
    boolean[] isPrime;
    String numbers;
    public int solution(String numbers) {
        init(numbers);
        return solve();
    }
    
    void init(String numbers) {
        this.numbers = numbers;
        isPrime = new boolean[10_000_000];
        boolean[] visited = new boolean[10_000_000];
        for (int i = 2; i < 10_000_000; i++) {
            if (!visited[i]) {
                isPrime[i] = true;
                int n = i+i;
                while (n < 10_000_000) {
                    visited[n] = true;
                    n += i;
                }
            }
        }
               
    }
    
    int solve() {
        boolean[] visited = new boolean[numbers.length()];
        boolean[] added = new boolean[10_000_000];
        return dfs(visited, new StringBuilder(), added);
        
        
    }
    
    int dfs(boolean[] visited, StringBuilder sb, boolean[] added) {
        if (sb.length() == numbers.length()) {
            int value = Integer.parseInt(sb.toString());
            if (isPrime[value] && !added[value]) {
                added[value] = true;
                return 1;
            }
            return 0;
        }
        
        int result = 0;
        for(int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(numbers.charAt(i));
                if (isPrime[Integer.parseInt(sb.toString())] && !added[Integer.parseInt(sb.toString())]) {
                    result += 1;
                    added[Integer.parseInt(sb.toString())] = true;
                }
                result += dfs(visited, sb, added);
                sb.deleteCharAt(sb.length()-1);
                visited[i] = false;
            }
        }
        return result;
        
    }
    
}