import java.util.*;
import java.io.*;

import static java.lang.Math.abs;
import static java.util.Arrays.sort;

/*

특성 값이 0을 만드는 조합을 찾아야함

 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int N;
    int[] liquids;
    int left, right, leftRightSum, optimalSum, optimalLeft, optimalRight;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        liquids = getInputArray();
        sort(liquids);
        left = 0;
        right = N-1;
        leftRightSum = liquids[left] + liquids[right];
        optimalSum = abs(leftRightSum);
        optimalLeft = left;
        optimalRight = right;
    }


    void solution() {
        while ((left + 1 < right) && optimalSum != 0) {
            if (leftRightSum > 0) {
                right -= 1;
            } else {
                left += 1;
            }
            leftRightSum = liquids[left] + liquids[right];
            if (optimalSum > abs(leftRightSum)) {
                optimalSum = abs(leftRightSum);
                optimalLeft = left;
                optimalRight = right;
            }
        }
        System.out.println(liquids[optimalLeft]+" "+liquids[optimalRight]);
    }

}