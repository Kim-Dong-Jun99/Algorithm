import java.util.*;

class Solution {
    int answer, temperature, lowerBound, upperBound, same, diff, smallest, biggest;
    int[] onboard;
    int[][] temperaturePrices;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        init(temperature, t1, t2, a, b, onboard);
        solve();
        return answer;
    }
    
    void init(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        this.temperature = temperature + 10;
        this.lowerBound = t1 + 10;
        this.upperBound = t2 + 10;
        this.same = b;
        this.diff = a;
        this.onboard = onboard;
        temperaturePrices = new int[onboard.length][51];
        for (int i = 0; i < onboard.length; i++) {
            Arrays.fill(temperaturePrices[i], Integer.MAX_VALUE);

        }
        answer = Integer.MAX_VALUE;
    }

    void solve() {
        temperaturePrices[0][temperature] = 0;
        for (int i = 0; i < onboard.length; i++) {
            setRange(i);
            if (i != onboard.length - 1) {
                for (int j = smallest; j <= biggest; j++) {
                    if (temperaturePrices[i][j] != Integer.MAX_VALUE) {
                        updatePrice(i, j);
                    }
                }
            } else {
                for (int j = smallest; j <= biggest; j++) {
                    answer = Math.min(answer, temperaturePrices[i][j]);
                }
            }
        }
    }

    private void updatePrice(int i, int j) {
        maintainCurrentPrice(i, j);
        if (j != 0) {
            temperaturePrices[i + 1][j - 1] = Math.min(temperaturePrices[i + 1][j - 1], temperaturePrices[i][j] + diff);
        }
        if (j != 50) {
            temperaturePrices[i + 1][j + 1] = Math.min(temperaturePrices[i + 1][j + 1], temperaturePrices[i][j] + diff);
        }
        if (j != temperature) {
            if (j > temperature) {
                temperaturePrices[i + 1][j - 1] = Math.min(temperaturePrices[i + 1][j - 1], temperaturePrices[i][j]);
            } else {
                temperaturePrices[i + 1][j + 1] = Math.min(temperaturePrices[i + 1][j + 1], temperaturePrices[i][j] );
            }
        }
    }

    private void maintainCurrentPrice(int i, int j) {
        if (j == temperature) {
            temperaturePrices[i + 1][j] = Math.min(temperaturePrices[i + 1][j], temperaturePrices[i][j]);
        } else {
            temperaturePrices[i + 1][j] = Math.min(temperaturePrices[i + 1][j], temperaturePrices[i][j] + same);
        }
    }

    private void setRange(int i) {
        if (onboard[i] == 1) {
            smallest = lowerBound;
            biggest = upperBound;
        } else {
            smallest = 0;
            biggest = 50;
        }
    }


}