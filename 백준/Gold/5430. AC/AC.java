
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase ++) {
            String p = br.readLine();

            int n = Integer.parseInt(br.readLine());
            String originalInput = br.readLine();
            String[] nums = originalInput.substring(1, originalInput.length() - 1).split(",");
            int leftIndex = 0;
            int rightIndex = n - 1;
            int head = 0;
            boolean done = true;
            int count = 0;
            for (int i = 0; i < p.length(); i ++){
                if (p.charAt(i) == 'R') {
                    if (head == leftIndex) {
                        head = rightIndex;
                    } else {
                        head = leftIndex;
                    }
                } else {
                    count += 1;
                    if (count > n) {
                        done = false;
                        break;
                    }
                    if (head == leftIndex) {
                        leftIndex += 1;
                        head += 1;
                    } else {
                        rightIndex -= 1;
                        head -= 1;
                    }


                }
            }

            if (done) {
                if (head == leftIndex) {
                    bw.write("[");
                    for (int i = leftIndex; i <= rightIndex; i++) {
                        bw.write(nums[i]);
                        if (i != rightIndex) {
                            bw.write(",");
                        }
                    }
                    bw.write("]\n");
                } else {
                    bw.write("[");
                    for (int i = rightIndex; i >= leftIndex; i--) {
                        bw.write(nums[i]);
                        if (i != leftIndex) {
                            bw.write(",");
                        }
                    }
                    bw.write("]\n");

                }
            } else {
                bw.write("error\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
