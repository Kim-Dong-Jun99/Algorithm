import java.util.*;

class Solution {
    int answer;
    int n;
    Reservation[] reservations;
    PriorityQueue<Reservation> heap;

    
    public int solution(String[][] book_time) {
        init(book_time);
        solve();
        return answer;
    }
    
    void init(String[][] bookTime) {
        n = bookTime.length;
        reservations = new Reservation[n];
        heap = new PriorityQueue<>(Reservation::sortWithEnd);
        for (int i = 0; i < n; i++) {
            String[] book = bookTime[i];
            reservations[i] = new Reservation(strToInt(book[0]), strToInt(book[1])+10);
        }
        Arrays.sort(reservations, Reservation::sortWithStart);
    }
    
    void solve() {
        for (Reservation reservation : reservations) {
            while (!heap.isEmpty() && reservation.start >= heap.peek().end) {
                heap.remove();
            }
            heap.add(reservation);
            answer = Math.max(answer, heap.size());
        }
    }
    
    int strToInt(String str) {
        String[] strArray = str.split(":");
        return 60*Integer.parseInt(strArray[0]) + Integer.parseInt(strArray[1]);
    }
    
    static class Reservation {
        int start;
        int end;
        
        Reservation(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        int sortWithStart(Reservation compare) {
            return Integer.compare(this.start, compare.start);
        }
        
        int sortWithEnd(Reservation compare) {
            return Integer.compare(this.end, compare.end);
        }
    }
}