import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int M;
    HashMap<String, Royal> royalMap;
    String[] toFind;
    Royal king;
    long maxBlood;
    String toPrint;
    HashSet<String> names;

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
        int[] inputArray = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArray[0];
        M = inputArray[1];
        String kingName = BR.readLine();
        king = new Royal(kingName, (long) Math.pow(2, 50));
        royalMap = new HashMap<>();
        toFind = new String[M];
        names = new HashSet<>();
        royalMap.put(kingName, king);
        for (int i = 0; i < N; i++) {
            String[] familyRelation = BR.readLine().split(" ");
            String childName = familyRelation[0];
            String parent1Name = familyRelation[1];
            String parent2Name = familyRelation[2];

            Royal child = royalMap.getOrDefault(childName, new Royal(childName, 0));
            Royal parent1 = royalMap.getOrDefault(parent1Name, new Royal(parent1Name, 0));
            Royal parent2 = royalMap.getOrDefault(parent2Name, new Royal(parent2Name, 0));

            child.parent1 = parent1;
            child.parent2 = parent2;
            parent1.addChild(child);
            parent2.addChild(child);

            royalMap.put(childName, child);
            royalMap.put(parent1Name, parent1);
            royalMap.put(parent2Name, parent2);
        }

        for (int i = 0; i < M; i++) {
            toFind[i] = BR.readLine();
        }
        maxBlood = 0;
    }


    void solution() throws IOException {
        updateBlood();
        for (String child : toFind) {
            Royal royal = royalMap.getOrDefault(child, new Royal(child,0));
            if (royal.blood > maxBlood) {
                maxBlood = royal.blood;
                toPrint = royal.name;
            }
        }
        System.out.println(toPrint);
    }

    void updateBlood() {
        names.add(king.name);
        while (!names.isEmpty()) {
            HashSet<String> temp = new HashSet<>();
            for (String name : names) {
                Royal parent = royalMap.get(name);
                for (Royal child : parent.children) {
                    child.blood = (child.parent1.blood + child.parent2.blood) / 2;
                    temp.add(child.name);
//                    System.out.println(child.name +" "+child.blood);
                }
            }
            names = temp;
        }
    }

    static class Royal {
        String name;
        List<Royal> children;
        long blood;

        Royal parent1;
        Royal parent2;

        public Royal(String name, long blood) {
            this.name = name;
            this.blood = blood;
            this.children = new ArrayList<>();
        }

        void addChild(Royal child) {
            this.children.add(child);
        }

    }
}

