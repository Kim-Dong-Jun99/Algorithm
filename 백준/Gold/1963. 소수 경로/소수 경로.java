import java.io.*;
import java.util.*;

/*

 */

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[] DIGIT = {1, 10, 100, 1000};
    int[] inputArray;
    int T;
    int left, right;
    boolean[] isPrime;
    boolean[] visited;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.testCase();
        main.printResult();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        initPrimes();
        while (T-- > 0) {
            init();
            solve();
        }
    }

    void initPrimes() {
        isPrime = new boolean[10_000];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < 10_000; i++) {
            if (isPrime[i]) {
                int notPrime = i + i;
                while (notPrime < 10_000) {
                    isPrime[notPrime] = false;
                    notPrime += i;
                }
            }
        }
    }

    void init() throws IOException {
        inputArray = getInputArray();
        left = inputArray[0];
        right = inputArray[1];
        visited = new boolean[10_000];
        visited[left] = true;
    }

    void solve() throws IOException {
        int answer = 0;
        List<Integer> current = Collections.singletonList(left);
        while (!current.isEmpty()) {
            if (visited[right]) {
                BW.write(Integer.toString(answer)+"\n");
                return;
            }
            List<Integer> temp = new ArrayList<>();
            for (Integer prime : current) {
                for (int d = 0; d < 4; d++) {
                    for (int i = 0; i < 10; i++) {
                        if (d == 3 && i == 0) {
                            continue;
                        }
                        int digit = DIGIT[d];
                        int nextPrime = getNextPrime(digit, prime, i);
                        if (!visited[nextPrime] && isPrime[nextPrime]) {
                            visited[nextPrime] = true;
                            temp.add(nextPrime);
                        }
                    }
                }
            }
            answer += 1;
            current = temp;
        }
        BW.write("Impossible\n");
    }

    int getNextPrime(int digit, int prime, int i) {
        int div = (prime / (digit * 10)) * digit * 10;
        int newDigit = digit * i;
        int mod = prime % digit;
        return div + newDigit + mod;
    }

    void printResult() throws IOException {
        BW.flush();
        BW.close();
        BR.close();
    }
}