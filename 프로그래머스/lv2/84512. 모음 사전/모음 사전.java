import java.util.*;
class Solution {

    String[] defaultCharacters = new String[]{"A", "E", "I", "O", "U"};

    String[] allCases = new String[3905];
    int index = 0;

    public static void main(String[] args) {
        System.out.println(new Solution().solution("AAA"));
    }

    public int solution(String word) {
        List<String> currentCase = List.of("");

        for (int i = 0; i < 5; i++) {
            List<String> temp = new ArrayList<>();
            for (String cur : currentCase) {
                for (String defaultCharacter : defaultCharacters) {
                    StringBuilder sb = new StringBuilder(cur);
                    sb.append(defaultCharacter);
                    String toAppend = sb.toString();
                    allCases[index] = toAppend;
                    temp.add(toAppend);
                    index += 1;
                }
            }
            currentCase = temp;

        }
        Arrays.sort(allCases);
        return Arrays.binarySearch(allCases, word)+1;

    }
}