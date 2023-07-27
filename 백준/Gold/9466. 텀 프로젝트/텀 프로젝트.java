

import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T;
    int n;
    int[] students;
    int[] visited;
    int answer;
    HashMap<Integer, Integer> group;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.testCase();
        } catch (Exception e) {
            System.out.println("exception during I/O");
        }
    }

    void testCase() throws IOException {

        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            try {
                init();
                solution();
            } catch (IOException e) {
                System.out.println("exception during i/o");
            }
        }

        BW.flush();
        BW.close();
    }


    void init() throws IOException {
        n = Integer.parseInt(BR.readLine());
        students = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).map(student -> student - 1).toArray();
        visited = new int[n];
    }

    void solution() throws IOException {
        answer = n;
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                group = new HashMap<>();

                int groupMembers = dfs(i, 0);
//                System.out.println("i = "+i+" groupMembers = "+groupMembers);
                if (groupMembers != -1) {
                    answer -= groupMembers;
                }
            }
        }

        BW.write(answer + "\n");
    }

    int dfs(int i, int index) {

        if (group.containsKey(i)) {
            return index - group.get(i);
        } else if (visited[i] == 1) {
            return -1;
        }
        visited[i] = 1;
        group.put(i, index);
        return dfs(students[i], index + 1);
    }


}