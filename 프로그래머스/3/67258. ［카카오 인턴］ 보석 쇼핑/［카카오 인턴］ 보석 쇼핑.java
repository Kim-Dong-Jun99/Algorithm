import java.util.*;

/*
진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매

슬라이딩 윈도우?

해쉬맵을 일단 하나 써야할듯
*/

class Solution {
    String[] gems;
    int n;
    int totalGems;
    HashMap<String, Integer> bought;

    public int[] solution(String[] gems) {
        init(gems);
        return solve();
    }

    void init(String[] gems) {
        this.gems = gems;
        bought = new HashMap<>();
        HashSet<String> tempGems = new HashSet<>(Arrays.asList(gems));
        totalGems = tempGems.size();
        n = gems.length;

    }

    int[] solve() {
        int minLength = gems.length;
        int answerLeft = 0;
        int answerRight = n-1;
        int leftIndex = 0;
        int rightIndex = 0;
        bought.put(gems[0], 1);
        while (true) {
            if (boughtAllGems()) {
                if (rightIndex + 1 - leftIndex < minLength) {
                    minLength = rightIndex + 1 - leftIndex;
                    answerLeft = leftIndex;
                    answerRight = rightIndex;


                }
                Integer gemCount = bought.getOrDefault(gems[leftIndex], 0);
                gemCount -= 1;
                if (gemCount == 0) {
                    bought.remove(gems[leftIndex]);
                } else {
                    bought.put(gems[leftIndex], gemCount);
                }
                leftIndex += 1;
            } else {
                if (rightIndex + 1 < n) {
                    rightIndex += 1;
                    Integer gemCount = bought.getOrDefault(gems[rightIndex], 0);
                    bought.put(gems[rightIndex], gemCount + 1);
                } else {
                    break;
                }
            }
        }

        return new int[]{answerLeft + 1, answerRight + 1};
    }

    boolean boughtAllGems() {
        return bought.size() == totalGems;
    }


}


