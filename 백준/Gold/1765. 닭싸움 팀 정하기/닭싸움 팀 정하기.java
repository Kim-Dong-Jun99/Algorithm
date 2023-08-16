import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int n, m;
    int[] parent;
    String[] relations;
    HashMap<Integer, List<Integer>> enemies;
    HashSet<Integer> groups;
    int p,q;
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
        n = Integer.parseInt(BR.readLine());
        m = Integer.parseInt(BR.readLine());
        parent = IntStream.range(0, n + 1).toArray();
        relations = new String[m];
        enemies = new HashMap<>();
        groups = new HashSet<>();
        for (int i = 0; i < m; i++) {
            relations[i] = BR.readLine();
        }
    }


    void solution() throws IOException {
        for (String givenRelation : relations) {
            String[] relation = givenRelation.split(" ");
            p = Integer.parseInt(relation[1]);
            q = Integer.parseInt(relation[2]);
            if (relation[0].equals("E")) {
                for (Integer enemy : enemies.getOrDefault(p, new ArrayList<>())) {
                    union(q, enemy);
                }
                for (Integer enemy : enemies.getOrDefault(q, new ArrayList<>())) {
                    union(p, enemy);
                }
                List<Integer> pEnemies = enemies.getOrDefault(p, new ArrayList<>());
                pEnemies.add(q);
                enemies.put(p, pEnemies);
                List<Integer> qEnemies = enemies.getOrDefault(q, new ArrayList<>());
                qEnemies.add(p);
                enemies.put(q, qEnemies);
            } else {
                union(p, q);
            }
        }
        for (int i = 1; i <= n; i++) {
            groups.add(find(i));
        }
        System.out.println(groups.size());
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
            parent[py] = px;
        }
    }


}

