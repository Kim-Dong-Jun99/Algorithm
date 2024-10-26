class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        while (!fit(wallet, bill)) {
            fold(bill);
            answer++;
        }
        return answer;
    }
    
    boolean fit(int[] wallet, int[] bill) {
        return Math.min(wallet[0], wallet[1]) >= Math.min(bill[0], bill[1]) &&
            Math.max(wallet[0], wallet[1]) >= Math.max(bill[0], bill[1]);
    }
    
    void fold(int[] bill) {
        int tmp1 = bill[0];
        int tmp2 = bill[1];
        bill[0] = Math.min(tmp1, tmp2);
        bill[1] = Math.max(tmp1, tmp2) / 2;
    }
}