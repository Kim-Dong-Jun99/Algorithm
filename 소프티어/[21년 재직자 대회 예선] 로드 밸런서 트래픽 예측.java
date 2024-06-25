import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    long[] inputArray;
    long N, K;
    HashMap<Long, Node> nodeMap;
    int[] inDegree;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    long[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    }

    void init() throws IOException {
        inputArray = getInputArray();
        N = inputArray[0];
        K = inputArray[1];
        nodeMap = new HashMap<>();
        inDegree = new int[(int) N+1];
        for (long i = 1; i <= N; i++) {
            nodeMap.put(i, new Node(i));
        }
        for (long i = 1; i <= N; i++) {
            inputArray = getInputArray();
            if (inputArray[0] == 0L) {
                continue;
            }
            Node n = nodeMap.get(i);
            for (int j = 1; j < inputArray.length; j++) {
                n.addChild(nodeMap.get(inputArray[j]));
                inDegree[(int) inputArray[j]] += 1;
            }
        }
    }

    void solve() {
        dfs(nodeMap.get(1L), K);
        for (long i = 1L; i <= N; i++) {
            System.out.print(nodeMap.get(i).request+" ");
        }
    }

    void dfs(Node node, long request) {
        node.request += request;
        if (node.childs.isEmpty() || inDegree[(int) node.id] > 0) {
            return;
        }
        long div = node.request / (long) node.childs.size();
        long mod = node.request % (long) node.childs.size();
        for (int i = 0; i < node.childs.size(); i++) {
            Node next = node.childs.get(i);
            inDegree[(int) next.id] -= 1;
            if (i < mod) {
                dfs(next, div + 1);
            } else {
                dfs(next, div);
            }
        }
    }

    static class Node {
        long id;
        List<Node> childs;
        long request;

        Node(long id) {
            this.id = id;
            this.childs = new ArrayList<>();
            this.request = 0L;
        }

        void addChild(Node node) {
            this.childs.add(node);
        }

    }
}
