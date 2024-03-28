import java.io.*;
import java.util.*;

/*

 */

class Main {
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] inputArray;
    int N;
    int[] depth;
    HashMap<Integer, List<Integer>> depthMap;
    int[] subtreeSize;
    int[] position;
    Tree[] trees;
    int root;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.solve();
    }

    int[] getInputArray() throws IOException {
        return Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        subtreeSize = new int[N + 1];
        position = new int[N + 1];
        trees = new Tree[N + 1];
        depth = new int[N + 1];
        depthMap = new HashMap<>();
        initTree();
        root = findRoot();
    }

    void initTree() throws IOException {
        for (int i = 0; i <= N; i++) {
            trees[i] = new Tree();
        }
        for (int i = 1; i <= N; i++) {
            inputArray = getInputArray();
            int parent = inputArray[0];
            int lc = inputArray[1];
            int rc = inputArray[2];
            if (lc == -1) {
                lc = 0;
            }
            if (rc == -1) {
                rc = 0;
            }
            trees[parent].leftChild = lc;
            trees[parent].rightChild = rc;
            if (lc != 0) {
                trees[lc].parent = parent;
            }
            if (rc != 0) {
                trees[rc].parent = parent;
            }
        }
    }

    int findRoot() {
        for (int i = 1; i <= N; i++) {
            if (trees[i].parent == 0) {
                return i;
            }
        }
        return 1;
    }

    void solve() throws IOException {
        getSubTreeSize(root);
        fillDepthMap(root);
        printResult();
    }

    int getSubTreeSize(int index) {
        if (index == 0) {
            return 0;
        }
        depth[index] = depth[trees[index].parent] + 1;
        int size = 1;
        size += getSubTreeSize(trees[index].leftChild);
        size += getSubTreeSize(trees[index].rightChild);
        subtreeSize[index] = size;
        return subtreeSize[index];
    }

    void fillDepthMap(int index) {
        if (index == 0) {
            return;
        }
        if (index == root) {
            position[index] = subtreeSize[trees[index].leftChild] + 1;
        } else {
            int parent = trees[index].parent;
            if (index == trees[parent].leftChild) {
                position[index] = position[parent] - subtreeSize[trees[index].rightChild] - 1;
            } else {
                position[index] = position[parent] + subtreeSize[trees[index].leftChild] + 1;
            }
        }
        List<Integer> positionList = depthMap.getOrDefault(depth[index], new ArrayList<>());
        positionList.add(position[index]);
        depthMap.put(depth[index], positionList);
        fillDepthMap(trees[index].leftChild);
        fillDepthMap(trees[index].rightChild);

    }


    void printResult() {
        int maxWidth = 0;
        int toPrint = 0;
        for (Integer d : depthMap.keySet()) {
            List<Integer> ps = depthMap.get(d);
            Collections.sort(ps);
            if (maxWidth < ps.get(ps.size() - 1) - ps.get(0) + 1) {
                maxWidth = ps.get(ps.size() - 1) - ps.get(0) + 1;
                toPrint = d;
            }
        }
        System.out.println(toPrint + " " + maxWidth);
    }

    static class Tree {
        int parent;
        int leftChild;
        int rightChild;

        Tree() {
            parent = 0;
            leftChild = 0;
            rightChild = 0;
        }

    }
}