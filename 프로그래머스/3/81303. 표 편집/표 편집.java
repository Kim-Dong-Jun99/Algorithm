
import java.util.*;

/*
표의 행을 선택, 삭제, 복구하는 프로그램을 작성해야함

표의 행 개수 <= 1,000,000

cmd <= 200,000

doubly linked list


*/

class Solution {
    static final String DOWN = "D";
    static final String UP = "U";
    static final String REMOVE = "C";
    static final String ROLL_BACK = "Z";
    String answer;
    int n, k;
    String[] cmd;
    Stack<Node> deletedStack;
    boolean[] isDeleted;
    Node[] nodes;
    Node current;
    public String solution(int n, int k, String[] cmd) {
        init(n, k, cmd);
        return solve();
    }

    void init(int n, int k, String[] cmd) {
        this.n = n;
        this.k = k;
        this.cmd = cmd;

        deletedStack = new Stack<>();
        nodes = new Node[n];
        isDeleted = new boolean[n];


        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        connectNodes();

        current = nodes[k];
    }

    void connectNodes() {
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                nodes[i].setUp(nodes[i-1]);
                nodes[i - 1].setDown(nodes[i]);
        }
            if (i != n - 1) {
                nodes[i].setDown(nodes[i + 1]);
                nodes[i + 1].setUp(nodes[i]);
            }
        }
    }

    String solve() {
        for (String c : cmd) {
            String[] cmdArray = c.split(" ");
            String orderType = cmdArray[0];

            if (orderType.equals(DOWN)) {
                int distance = Integer.parseInt(cmdArray[1]);
                while (distance-- > 0) {
                    current = current.down;
                }

            }
            if (orderType.equals(UP)) {
                int distance = Integer.parseInt(cmdArray[1]);
                while (distance-- > 0) {
                    current = current.up;
                }
            }
            if (orderType.equals(REMOVE)) {
                Node next, down, up;
                if (current.down != null) {
                    next = current.down;
                } else {
                    next = current.up;
                }
                down = current.down;
                up = current.up;

                isDeleted[current.index] = true;
                if (down != null) {
                    down.setUp(up);
                }
                if (up != null) {
                    up.setDown(down);
                }
                deletedStack.push(current);
                current = next;


            }
            if (orderType.equals(ROLL_BACK)) {
                Node down, up;
                Node toAdd = deletedStack.pop();
                down = toAdd.down;
                up = toAdd.up;
                isDeleted[toAdd.index] = false;
                if (down != null) {
                    down.setUp(toAdd);
                }
                if (up != null) {
                    up.setDown(toAdd);
                }
                
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (isDeleted[i]) {
                sb.append("X");
            } else {
                sb.append("O");
            }
        }
        return sb.toString();

    }

    static class Node {
        int index;
        Node up;
        Node down;

        public Node(int index) {
            this.index = index;
        }

        public void setUp(Node up) {
            this.up = up;
        }

        public void setDown(Node down) {
            this.down = down;
        }
    }

}