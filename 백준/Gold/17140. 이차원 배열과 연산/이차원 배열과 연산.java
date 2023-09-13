import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int r, c, k;
    List<List<Integer>> A;
    int[] inputArray;
    int answer;

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
        r = inputArray[0];
        c = inputArray[1];
        k = inputArray[2];

        A = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            A.add(Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
        }

        answer = 0;
    }

    void solution() throws IOException {
        while (answer <= 100) {
//            for (List<Integer> integers : A) {
//                for (Integer a : integers) {
//                    System.out.print(a+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            if (A.size() >= r && A.get(0).size() >= c && A.get(r-1).get(c-1) == k) {
                break;
            }

            if (A.size() >= A.get(0).size()) {
                updateA();
            } else {
                A = rotateClockwise(A);
                updateA();
                A = rotateClockwise(A);
            }

            answer += 1;

//            for (List<Integer> integers : A) {
//                for (Integer a : integers) {
//                    System.out.print(a+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();

        }
        if (answer == 101) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }


    }

    void updateA() {
        List<List<Integer>> newA = new ArrayList<>();
        int maxLength = 0;
        for (List<Integer> integers : A) {
            List<Target> targets = new ArrayList<>();
            List<Integer> toAdd = new ArrayList<>();
            HashSet<Integer> added = new HashSet<>();
            for (Integer j : integers) {
                if (!added.contains(j) && j != 0) {
                    added.add(j);
                    targets.add(new Target(j, Collections.frequency(integers, j)));
                }
            }
            targets.sort(Target::compareWithCount);
            for (Target target : targets) {
                if (toAdd.size() == 100) {
                    break;
                }
                toAdd.add(target.value);
                if (toAdd.size() == 100) {
                    break;
                }
                toAdd.add(target.count);
            }
            maxLength = Math.max(maxLength, toAdd.size());
            newA.add(toAdd);
        }
        for (List<Integer> a : newA) {
            while (a.size() < maxLength) {
                a.add(0);
            }
        }
        A = newA;
    }

    List<List<Integer>> rotateClockwise(List<List<Integer>> toRotate) {
        List<List<Integer>> newA = new ArrayList<>();
        for (int j = 0; j < toRotate.get(0).size(); j++) {
            List<Integer> toAdd = new ArrayList<>();
            for (int i = 0; i < toRotate.size(); i++) {
                toAdd.add(toRotate.get(i).get(j));
            }
            newA.add(toAdd);
        }

        return newA;
    }

    static class Target {
        int value;
        int count;

        public Target(int value, int count) {
            this.value = value;
            this.count = count;
        }

        int compareWithCount(Target compare) {
            if (this.count != compare.count) {
                return Integer.compare(this.count, compare.count);
            }
            return Integer.compare(this.value, compare.value);
        }
    }

}