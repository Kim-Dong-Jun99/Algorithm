import java.util.*;
import java.io.*;

/*
N개의 동영상이 올라가 있다,

모든 동영상에 대한 연관 동영상 리스트를 만들기로 했다.

동영상들은 그래프 구조를 가짐, 임의의 두쌍의 유사도는 그 경로의 모든 연결의 유사도 중 최솟값이다.

어떤 동영상이 주어지고 그 동영상과 유사도가 k 이상인 모든 동영상을 추천해주려고한다.
동영상이 너무 많아지면 안되기에 k 값을 잘 정해야함


 */

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    int[][] usado;
    int[] inputArray;
    int N, Q;
    List<Road>[] roads;
    Query[] queries;

    public static void main(String[] args) {

        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }

    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        Q = inputArray[1];
        roads = new List[N + 1];
        usado = new int[N + 1][N + 1];
        queries = new Query[Q];
        for (int i = 0; i <= N; i++) {
            roads[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            inputArray = getInputArray();
            int p = inputArray[0];
            int q = inputArray[1];
            int r = inputArray[2];

            roads[p].add(new Road(q, r));
            roads[q].add(new Road(p, r));

        }
        for (int i = 0; i < Q; i++) {
            inputArray = getInputArray();
            queries[i] = new Query(inputArray[0], inputArray[1]);
        }
        setUsado();
    }

    void setUsado() {
        for (int i = 0; i <= N; i++) {
            Arrays.fill(usado[i], Integer.MAX_VALUE);
            if (i != 0) {
                initUsado(i);
            }
        }


    }

    void initUsado(int node) {
        boolean[] visited = new boolean[N + 1];
        visited[node] = true;
        List<UsadoNode> nodes = Collections.singletonList(new UsadoNode(node, 1_000_000_000));
        while (!nodes.isEmpty()) {
            List<UsadoNode> temp = new ArrayList<>();
            for (UsadoNode currentNode : nodes) {
                for (Road road : roads[currentNode.to]) {
                    if (!visited[road.to]) {
                        visited[road.to] = true;
                        usado[node][road.to] = Math.min(road.value, currentNode.u);
                        temp.add(new UsadoNode(road.to, Math.min(road.value, currentNode.u)));
                    }
                }
            }

            nodes = temp;
        }
//        System.out.println(Arrays.toString(usado[node]));
    }


    void solution() {
        for (Query query : queries) {
            int result = 0;
            for (int i = 1; i <= N; i++) {
                if (i != query.v && usado[query.v][i] >= query.k) {
                    result += 1;
                }
            }
            System.out.println(result);
        }
    }

    static class Road {
        int to;
        int value;

        public Road(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

    static class Query {
        int k;
        int v;

        public Query(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    static class UsadoNode {
        int to;
        int u;

        public UsadoNode(int to, int u) {
            this.to = to;
            this.u = u;
        }

    }


}