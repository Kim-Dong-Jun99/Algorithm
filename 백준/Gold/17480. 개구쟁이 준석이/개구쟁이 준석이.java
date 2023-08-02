import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int alphabetNumber;
    int[] combinationToSearch;
    int[] combinationToCompare;
    String givenString;
    int toCompareStringLength;
    HashSet<String> visited;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    void init() throws IOException {
        alphabetNumber = Integer.parseInt(BR.readLine());
        combinationToSearch = new int[26];
        String[] inputArray = BR.readLine().split(" ");
        toCompareStringLength = 0;
        for (int i = 0; i < 2 * alphabetNumber; i += 2) {
            combinationToSearch[inputArray[i].charAt(0) - 97] = Integer.parseInt(inputArray[i + 1]);
            toCompareStringLength += Integer.parseInt(inputArray[i + 1]);
        }
        givenString = BR.readLine();
        visited = new HashSet<>();
    }

    void solution() throws IOException {
        for (int i = 0; i < givenString.length(); i++) {
            if (i + toCompareStringLength <= givenString.length()) {
                combinationToCompare = new int[26];
                String toCompare = givenString.substring(i, i + toCompareStringLength);
                for (int j = 0; j < toCompareStringLength; j++) {
                    combinationToCompare[toCompare.charAt(j) - 97] += 1;
                }
                if (Arrays.equals(combinationToCompare, combinationToSearch)) {
                    getPossibleCombinations(toCompare, 0, toCompareStringLength);
                }
            } else {
                break;
            }
        }
        System.out.println(visited.size());

    }

    void getPossibleCombinations(String toCompare, int start, int end) {
        if (end - start <= 2) {
            visited.add(toCompare);
            return;
        }
        int middle = (start + end) / 2;

        String left = flip(toCompare, start, middle);
        String right = flip(toCompare, middle, end);

        getPossibleCombinations(left, middle, end);
        getPossibleCombinations(right, start, middle);

        if ((start + end) % 2 != 0) {
            String leftWithMiddle = flip(toCompare, start, middle + 1);
            String rightWithOutMiddle = flip(toCompare, middle + 1, end);

            getPossibleCombinations(leftWithMiddle, middle + 1, end);
            getPossibleCombinations(rightWithOutMiddle, start, middle + 1);
        }
    }

    String flip(String toFlip, int start, int end) {
        StringBuilder sb = new StringBuilder();
        StringBuilder toReverse = new StringBuilder();
        sb.append(toFlip, 0, start);
        toReverse.append(toFlip, start, end);
        toReverse.reverse();
        sb.append(toReverse.toString());
        if (end < toFlip.length()) {
            sb.append(toFlip.substring(end));
        }
        return sb.toString();
    }


}
