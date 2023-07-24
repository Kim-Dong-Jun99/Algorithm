import java.io.*;
import java.util.*;

class Main {

    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    HashMap<String, Node> nodeHashMap;

    public static void main(String[] args)  {
        Main main = new Main();
        try {
            main.init();
            main.solution();
        } catch (Exception e) {
            System.out.println("exception during I/O");
        }

    }

    void init() throws Exception {
        N = Integer.parseInt(BR.readLine());
        nodeHashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] s = BR.readLine().split(" ");
            String parentValue = s[0];
            String leftValue = s[1];
            String rightValue = s[2];

            if (!nodeHashMap.containsKey(parentValue)) {
                Node root = new Node(parentValue);
                nodeHashMap.put(parentValue, root);
            }
            Node node = nodeHashMap.get(parentValue);
            if (!leftValue.equals(".")) {
                Node leftChild = new Node(leftValue);
                node.setLeftChild(leftChild);
                nodeHashMap.put(leftValue, leftChild);
            }
            if (!rightValue.equals(".")) {
                Node rightChild = new Node(rightValue);
                node.setRightChild(rightChild);
                nodeHashMap.put(rightValue, rightChild);
            }
        }

    }

    void solution() throws Exception {
        Node root = nodeHashMap.get("A");
        preorder(root);
        BW.newLine();
        inorder(root);
        BW.newLine();
        postorder(root);
        BW.newLine();
        BW.flush();
        BW.close();
    }

    static class Node {
        String value;
        Node leftChild;
        Node rightChild;

        public Node(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }

    void preorder(Node root) throws Exception {
        BW.write(root.value);
        if (root.getLeftChild() != null) {
            preorder(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            preorder(root.getRightChild());
        }
    }

    void inorder(Node root) throws Exception{
        if (root.getLeftChild() != null) {
            inorder(root.getLeftChild());
        }
        BW.write(root.value);
        if (root.getRightChild() != null) {
            inorder(root.getRightChild());
        }
    }

    void postorder(Node root) throws Exception{
        if (root.getLeftChild() != null) {
            postorder(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            postorder(root.getRightChild());
        }
        BW.write(root.value);
    }


}