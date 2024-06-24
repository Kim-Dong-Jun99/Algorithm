import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    int[] inputArray;
    int T, N; 
    int[] memories;
    TreeMap<Integer, Integer> treeMap;
    int answer;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.test();
    }

    void test() throws IOException {
        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            init();
            solve();
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        memories = getInputArray();
        treeMap = new TreeMap<>();
        answer = 0;
        for (int m : memories) {
            if (m > 600) {
                answer += 1;
                continue;
            }
            treeMap.put(m, 1 + treeMap.getOrDefault(m, 0));
        }
    }

    void solve() {
        while (!treeMap.isEmpty()) {
            int m = treeMap.lastKey();
            int mCount = treeMap.get(m);
            if (m == 300) {
                answer += (int) Math.ceil((double) mCount / 3.0);
                treeMap.remove(m);
            } else if (m <= 450) {
                if (mCount > 1) {
                    answer += mCount / 2;
                    if (mCount % 2 == 0) {
                        treeMap.remove(m);
                    } else {
                        treeMap.put(m, 1);
                    }
                } else {
                    Integer floor = treeMap.floorKey(m-1);
                    answer += 1;
                    treeMap.remove(m);
                    if (floor != null) {
                        int fCount = treeMap.get(floor);
                        fCount -= 1;
                        if (fCount == 0) {
                            treeMap.remove(floor);
                        } else {
                            treeMap.put(floor, fCount);
                        }
                    }
                }
            } else {
                Integer floor = treeMap.floorKey(900-m);
                answer += 1;
                if (floor == null) {
                    mCount -= 1;
                    if (mCount == 0) {
                        treeMap.remove(m);
                    } else {
                        treeMap.put(m, mCount);
                    }
                } else {
                    int fCount = treeMap.get(floor);
                    mCount -= 1;
                    fCount -= 1;
                    if (mCount == 0) {
                        treeMap.remove(m);
                    } else {
                        treeMap.put(m, mCount);
                    }
                    if (fCount == 0) {
                        treeMap.remove(floor);
                    } else {
                        treeMap.put(floor, fCount);
                    }
                }
            }
        }
        
        System.out.println(answer);
    }
}
