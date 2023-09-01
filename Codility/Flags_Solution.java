import java.util.*;

class Flags_Solution {
    int answer;
    int[] A;
    int N;
    TreeSet<Integer> peakIndexes;
    int smallest;
    int largest;
    int size;
    HashMap<Integer, Integer> cache;
    int limit;
    public int solution(int[] A) {
        init(A);
        solve();
        return answer;
    }

    void init(int[] A) {
        answer = 0;
        this.A = A;
        N = A.length;
        peakIndexes = new TreeSet<>();
        size = 0;
        cache = new HashMap<>();
        for (int i = 1; i < N - 1; i++) {
            if (isPeak(i)) {
                peakIndexes.add(i);
                size += 1;
                i += 1;
            }
        }
    }

    boolean isPeak(int i) {
        return A[i - 1] < A[i] && A[i] > A[i + 1];
    }

    void solve() {
        if (size <= 1) {
            answer = size;
            return;
        }

        smallest = peakIndexes.first();
        largest = peakIndexes.last();
        limit = (int) Math.ceil(Math.pow(largest - smallest, 0.5));
        for (int k = limit; k > 1; k--) {
            Integer current = smallest;
            int flagCount = 1;
            while (current != null && flagCount < k) {
                Integer next;
                if (cache.containsKey(current + k)) {
                    next = cache.get(current + k);
                } else {
                    next = peakIndexes.ceiling(current + k);
                    cache.put(current + k, next);
                }
                if (next != null) {
                    flagCount += 1;
                }

                current = next;
            }
            answer = Math.max(flagCount, answer);
            if (flagCount < answer) {
                break;
            }
        }
    }
}
