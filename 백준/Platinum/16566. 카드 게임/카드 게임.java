import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] inputArray;
    int[] cards;
    int[] chulsu;
    int N, M, K;
    TreeMap<Integer, Integer> toBinarySearch;
    int[] parent;
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
        inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        K = inputArray[2];
        toBinarySearch = new TreeMap<>();
        cards = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(cards);
        parent = new int[M];
        for (int i = 0; i < M; i++) {
            parent[i] = i;
            toBinarySearch.put(cards[i], i);
        }

        chulsu = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    }

    void solution() throws IOException {
        for (Integer card : chulsu) {
            Integer ceilingIndex = toBinarySearch.ceilingEntry(card+1).getValue();
            int toPrintIndex = find(ceilingIndex);
            BW.write(cards[toPrintIndex] + "\n");
            if (toPrintIndex + 1 < M) {
//                System.out.println("union");
                union(toPrintIndex, toPrintIndex +1);
            }
//            for (int i : parent) {
//                System.out.print(i+" ");
//            }
//            System.out.println();
//            toBinarySearch.remove(ceiling);
        }
        BW.flush();
        BW.close();

    }

    int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            if (py > px) {
                parent[px] = py;
            } else {
                parent[py] = px;
            }
        }


    }


}

