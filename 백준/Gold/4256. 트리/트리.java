import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int T;
    int N;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> indexMap;
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.testCase();
        } catch (IOException e) {
            System.out.println("Exception during I/O");
        }
    }

    void testCase() throws IOException {
        T = Integer.parseInt(BR.readLine());
        while (T-- > 0) {
            try {
                init();
                solution();
            } catch (IOException e) {
                System.out.println("Exception during I/O");
            }
        }
        BW.flush();
        BW.close();

    }

    void init() throws IOException {
        N = Integer.parseInt(BR.readLine());
        preorder = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        inorder = Arrays.stream(BR.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        indexMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            indexMap.put(inorder[i], i);
        }
    }

    void solution() throws IOException {
        Node root = reconstruct(0, N - 1, 0, N - 1);
        postOrder(root);
        BW.newLine();
    }

    Node reconstruct(int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        Node tempRoot = new Node(preorder[preorderLeft]); // 3
        int indexOfTempRoot = indexMap.get(preorder[preorderLeft]); // 4
        int numberOfNodeInLeftSubTree = indexOfTempRoot - inorderLeft; // 4
        int numberOfNodeInRightSubTree = inorderRight - indexOfTempRoot; // 3
//        System.out.println(preorder[preorderLeft] + " leftSubTree " + numberOfNodeInLeftSubTree + " rightSubTree" + numberOfNodeInRightSubTree);
        if (numberOfNodeInLeftSubTree > 0) {
            Node leftChild = reconstruct(preorderLeft + 1, preorderLeft + numberOfNodeInLeftSubTree, inorderLeft, indexOfTempRoot - 1);
            tempRoot.setLeftChild(leftChild);
        }
        if (numberOfNodeInRightSubTree > 0) {
            Node rightChild = reconstruct(preorderRight - numberOfNodeInRightSubTree + 1, preorderRight, indexOfTempRoot + 1, inorderRight);
            tempRoot.setRightChild(rightChild);
        }


        return tempRoot;
    }

    void postOrder(Node node) throws IOException {

        if (node.leftChild != null) {
            postOrder(node.leftChild);
        }
        if (node.rightChild != null) {
            postOrder(node.rightChild);
        }
        BW.write(Integer.toString(node.value) + " ");
    }

    static class Node {
        int value;
        Node leftChild;
        Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }

}
