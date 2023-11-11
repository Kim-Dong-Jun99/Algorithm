import java.util.*;

/*

*/

class Solution {
    int bridgeLength;
    int maxWeight;
    Queue<Integer> truckWeights;
    Queue<Truck> trucks;
    int weightOnBridge;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        init(bridge_length, weight, truck_weights);
        return solve();
    }
    
    void init(int bridge_length, int weight, int[] truck_weights) {
        this.bridgeLength = bridge_length;
        this.maxWeight = weight;
        weightOnBridge = 0;
        truckWeights = new LinkedList<>();
        for (int truckWeight : truck_weights) {
            truckWeights.add(truckWeight);
        }
        trucks = new LinkedList<>();
        
    }
    
    int solve() {
        int round = 0;
        while(true) {
            round += 1;
            if (!trucks.isEmpty()) {
                if (trucks.peek().onboard + bridgeLength <= round) {
                    weightOnBridge -= trucks.peek().weight;
                    trucks.remove();
                }
            }
            if (isEnd()) {
                break;
            }
            if (!truckWeights.isEmpty()) { 
                if (weightOnBridge + truckWeights.peek() <= maxWeight) {
                    int truckWeight = truckWeights.remove();
                    weightOnBridge += truckWeight;
                    System.out.println(truckWeight + " "+round);
                    trucks.add(new Truck(truckWeight, round));
                }
            }
            
            
        }
        return round;
        
    }
    
    boolean isEnd() {
        return trucks.isEmpty() && truckWeights.isEmpty();
    }
    
    static class Truck {
        int weight;
        int onboard;
        public Truck(int weight, int onboard) {
            this.weight = weight;
            this.onboard = onboard;
        }
    }
}