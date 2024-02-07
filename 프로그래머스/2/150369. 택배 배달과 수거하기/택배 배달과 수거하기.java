import java.util.*;

class Solution {
    
    long answer;
    int cap, n;
    
    PriorityQueue<Delivery> deliveryHeap;
    PriorityQueue<Delivery> pickupHeap;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        init(cap, n, deliveries, pickups);
        solve();
        return answer;
    }
    
    void init(int cap, int n, int[] deliveries, int[] pickups) {
        this.cap = cap;
        this.n = n;
        this.answer = 0l;
        deliveryHeap = new PriorityQueue<>(Delivery::compareWithDistance);
        pickupHeap = new PriorityQueue<>(Delivery::compareWithDistance);
        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) {
                deliveryHeap.add(new Delivery(i + 1, deliveries[i]));
            }
            if (pickups[i] != 0) {
                pickupHeap.add(new Delivery(i + 1, pickups[i]));
            }
        }
    }
    
    void solve() {
        while(!deliveryHeap.isEmpty() || !pickupHeap.isEmpty()) {
            int delivered = 0;
            int pickuped = 0;
            int maxDistance = 0;
            while (delivered < cap && !deliveryHeap.isEmpty()) {
                Delivery delivery = deliveryHeap.remove();
                delivered += delivery.box;
                maxDistance = Math.max(maxDistance, delivery.distance);
                if (delivered > cap) {
                    deliveryHeap.add(new Delivery(delivery.distance, delivered - cap));
                    delivered = cap;
                }
            }
            
            while (pickuped < cap && !pickupHeap.isEmpty()) {
                Delivery delivery = pickupHeap.remove();
                pickuped += delivery.box;
                maxDistance = Math.max(maxDistance, delivery.distance);
                if (pickuped > cap) {
                    pickupHeap.add(new Delivery(delivery.distance, pickuped - cap));
                    pickuped = cap;
                }
            }
            
            answer += maxDistance * 2;
        }
    }
    
    public static class Delivery {
        int distance;
        int box;
        
        public Delivery(int distance, int box) {
            this.distance = distance;
            this.box = box;
        }
        
        public static int compareWithDistance(Delivery d1, Delivery d2) {
            return Integer.compare(d2.distance, d1.distance);
        }
    }
}