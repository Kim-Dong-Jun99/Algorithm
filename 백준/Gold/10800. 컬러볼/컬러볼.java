import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    // 입력 받은 2중 배열을 저장하는 배열
    int[][] colorAndSizes;
    // 입력 받은 전체 공에 대하여 공 크기별 사로 잡을 수 있는 공들의 크기의 합
    HashMap<Integer, Long> totalSubSums;
    // 색깔 별 공 크기의 누적 합, N * 2이중 배열, 0번 인덱스에 공 크기, 1번 인덱스에 크기 누적합 저장
    long[][] colorSubSums;
    // 사로잡을 수 있는 전체 공 크기의 합에서 뺄 같은 색깔이면서 자신보다 작은 크기의 공들 크기의 합
    HashMap<Integer, HashMap<Integer, Long>> sumToMinus;
    // 출력할 값 저장
    long[] toPrint;


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        main.solution();
    }

    void init() throws Exception {
        N = Integer.parseInt(BR.readLine());
        colorAndSizes = new int[N][3];
        colorSubSums = new long[N][2];
        sumToMinus = new HashMap<>();
        toPrint = new long[N];
        totalSubSums = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int[] array = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            colorAndSizes[i][0] = array[0];
            colorAndSizes[i][1] = array[1];
            colorAndSizes[i][2] = i;
            Arrays.fill(colorSubSums[i], 0L);
            sumToMinus.put(i, new HashMap<>());
        }

    }

    void solution() throws Exception{
        int currentBallSize = 0;
        long subSums = 0;
        Arrays.sort(colorAndSizes, Comparator.comparingInt(ball -> ball[1]));
        for (int[] colorAndSize : colorAndSizes) {
            int color = colorAndSize[0];
            int size = colorAndSize[1];
            if (currentBallSize < size) {
                currentBallSize = size;
                totalSubSums.put(currentBallSize, subSums);
            }
            if (colorSubSums[color - 1][0] < size) {
                colorSubSums[color - 1][0] = size;
                HashMap<Integer, Long> subSumsPerColor = sumToMinus.get(color - 1);
                subSumsPerColor.put(size, colorSubSums[color - 1][1]);
            }
            subSums += size;
            colorSubSums[color - 1][1] += size;
        }

        for (int[] colorAndSize : colorAndSizes) {
            int color = colorAndSize[0];
            int size = colorAndSize[1];
            int index = colorAndSize[2];

            Long totalSubSum = totalSubSums.get(size);
            Long sumToIgnore = sumToMinus.get(color - 1).get(size);

            toPrint[index] = totalSubSum - sumToIgnore;

        }

        for (long l : toPrint) {
            BW.write(Long.toString(l));
            BW.newLine();
        }
        BW.flush();
        BW.close();
    }


}