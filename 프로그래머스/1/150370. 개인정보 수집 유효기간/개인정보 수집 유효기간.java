import java.util.*;

/*
개인 정보 n 개,약관 종류는 여러가지, 약관 마다 개인 정보 보관 유효기간이 존재

오늘 날짜로 파기해야할 개인 정보 번호들 구하기

모든 달은 28일까지 있다고 가정


*/

class Solution {
    int today;
    HashMap<String, Integer> termMap;
    String[] privacies;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        init(today, terms, privacies);
        return solve();
    }
    
    void init(String today, String[] terms, String[] privacies) {
        this.today = stringDateToInt(today);
        termMap = new HashMap<>();
        for(String term : terms) {
            String[] termArray = term.split(" ");
            termMap.put(termArray[0], Integer.parseInt(termArray[1]) * 28);
        }
        this.privacies = privacies;
    }
    
    int[] solve() {
        List<Integer> resultList = new ArrayList<>();
        
        for(int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] privacyArray = privacy.split(" ");
            
            int date = stringDateToInt(privacyArray[0]);
            int validTime = termMap.get(privacyArray[1]);
            
            if (date + validTime <= today) {
                resultList.add(i+1);
            }
            
            
        }
        
        
        
        int[] result = new int[resultList.size()];
        for(int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
        
    }
    
    int stringDateToInt(String date) {
        int result = 0;
        String[] dateArray = date.split("\\.");
        result += Integer.parseInt(dateArray[0]) * 12 * 28;
        result += Integer.parseInt(dateArray[1]) * 28;
        result += Integer.parseInt(dateArray[2]);
        
        
        
        return result;
    }
    
    
}