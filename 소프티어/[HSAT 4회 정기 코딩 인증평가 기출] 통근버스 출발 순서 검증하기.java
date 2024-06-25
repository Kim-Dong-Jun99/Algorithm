import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[] nums;
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        nums = getInputArray();
    }

    void solve() {
        long answer = 0;
        for (int i = 0; i < N; i++) {
            long smaller = getSmaller(i);
            for (int j = i + 1; j < N; j++) {
                if (nums[i] < nums[j]) {
                    answer += smaller;
                } else {
                    smaller -= 1;
                }
            }
        }
        System.out.println(answer);
    }

    long getSmaller(int i) {
        long count = 0;
        for (int j = i + 1; j < N; j++) {
            if (nums[j] < nums[i]) {
                count += 1;
            }
        }
        return count;
    }
}
