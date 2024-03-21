import java.util.*;

class Solution {
    int answer, n;
    int cacheSize;
    String[] cities;
    HashMap<String, Integer> cache;
    public int solution(int cacheSize, String[] cities) {
        init(cacheSize, cities);
        solve();
        return answer;
    }

    void init(int cacheSize, String[] cities) {
        answer = 0;
        this.cacheSize = cacheSize;
        this.cities = cities;
        cache = new HashMap<>();
        n = cities.length;
    }

    void solve() {
        if (cacheSize == 0) {
            answer = 5 * n;
            return;
        }
        for (int i = 0; i < n; i++) {
            String city = cities[i].toLowerCase();
            if (cacheHit(city)) {
                answer += 1;
                updateCache(city, i);
            } else {
                answer += 5;
                if (cache.size() == cacheSize) {
                    removeOldest();
                }
                updateCache(city, i);
            }
        }
    }

    private void removeOldest() {
        int index = n;
        String toRemove = null;
        for (String key : cache.keySet()) {
            if (index > cache.get(key)) {
                index = cache.get(key);
                toRemove = key;
            }
        }
        cache.remove(toRemove);
    }

    private void updateCache(String city, int i) {
        cache.put(city, i);
    }

    boolean cacheHit(String city) {
        return cache.containsKey(city);
    }






}