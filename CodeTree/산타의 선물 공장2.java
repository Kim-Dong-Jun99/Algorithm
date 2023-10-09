
import java.util.*;
import java.io.*;

/*
1. 공장 설립
    n개의 벨트, m개의 물건을 준비,

2. 물건 모두 옮기기
    m_src 벨트에 있는 선물을 모두 m_dst 벨트로 옮긴다.
    옮겨진 선물들은 m_dst 벨트 앞에 위치, 옮긴 뒤에 m_dst 벨트에 있는 선물 개수를 출력

3. 앞물건만 교체
    m_src번째 벨트에 있는 선물 중 가장 앞 선물을 m_dst 벨트 중 가장 앞 선물을  교체, 두 벨트 중 하나에 선물이 없으면 교체하지 않고
    옮기기만 하면된다. 옮긴 후 m_dst 벨트 선물 개수를 출력하면 된다.

4. 물건 나누기
    m_src 벨트에 있는 선물의 개수가 n이면, n/2 번째까지 있는 선물을 m_dst 번째 벨트 앞으로 옮긴다.
    옮긴 뒤 m_dst 벨트에 있는 선물의 개수를 출력

5. 선물 정보 얻기
    p_num이 주어질 때, 해당 선물의 앞선물번호 a, 뒤 선물 번호 b일 때, a+2*b를 출력,
    앞 선물이 없으면 a = -1. 뒤 선물이 없으면 b= - 1

6. 벨트 정보 얻기
    b_num이 주어질 때, 벨트 맨 앞 선물 a, 맨뒤 선물 b, 선물 개수 c일때 a +2*b + 3*c 출력
    선물이 없으면 a = -1, b = -1


명령의 개수 q

q <= 100,000
n <= 100,000
m <= 100,000

물건들은 전체, 맨앞, 절반 단위로 옮겨진다.

q = 100,000 이기에, 물건을 움직이는 어느 행위도 logn 으로 들어와야함,

또 벨트에 놓은 물건의 맨앞, 맨뒤도 logn으로 알아야되고, 물건이 어느 벨트에 있고, 그 앞뒤도 logn으로 알아야함

더블리 링크드 리스트 써야되면 일단 Gift라는 클래스를 만들어서 해야할듯

그리고 Belt도 있어야돼,

*/

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int MOVE_ALL_PACKAGE = 200;
    static final int MOVE_FRONT_PACKAGE = 300;
    static final int DIVIDE_PACKAGE = 400;
    static final int GET_PACKAGE_INFO = 500;
    static final int GET_BELT_INFO = 600;
    int q, n, m;
    int[] inputArray;
    Belt[] belts;
    Gift[] gifts;

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
        q = Integer.parseInt(BR.readLine());
        q -= 1;
        inputArray = getInputArray();

        n = inputArray[1];
        m = inputArray[2];

        belts = new Belt[n + 1];
        gifts = new Gift[m + 1];

        for (int i = 0; i <= n; i++) {
            belts[i] = new Belt(i);
        }
        for (int i = 0; i <= m; i++) {
            gifts[i] = new Gift(i);
        }

        for (int i = 3; i < inputArray.length; i++) {
            int belt = inputArray[i];
            addPackageToBelt(belt, i - 2);

        }
    }


    void solution() throws IOException {
        int commandType, src, dst, gNum, bNum;
        while (q-- > 0) {
            inputArray = getInputArray();
            commandType = inputArray[0];

            if (commandType == MOVE_ALL_PACKAGE) {
                src = inputArray[1];
                dst = inputArray[2];
                moveAllPackages(src, dst);
            }
            if (commandType == MOVE_FRONT_PACKAGE) {
                src = inputArray[1];
                dst = inputArray[2];
                moveFrontPackage(src, dst);
            }
            if (commandType == DIVIDE_PACKAGE) {
                src = inputArray[1];
                dst = inputArray[2];
                dividePackage(src, dst);
            }
            if (commandType == GET_PACKAGE_INFO) {
                gNum = inputArray[1];
                getPackageInfo(gNum);
            }
            if (commandType == GET_BELT_INFO) {
                bNum = inputArray[1];
                getBeltInfo(bNum);
            }
        }
        BW.flush();
        BW.close();
    }


    void addPackageToBelt(int b, int g) {
        Belt belt = belts[b];
        belt.gifts.addGift(gifts[g]);
    }

    void moveAllPackages(int src, int dst) throws IOException {
        Belt source = belts[src];
        Belt destination = belts[dst];
        if (!source.gifts.isEmpty()) {
            source.gifts.tail.right = destination.gifts.head;
            if (destination.gifts.head != null) {
                destination.gifts.head.left = source.gifts.tail;
            } else {
                destination.gifts.tail = source.gifts.tail;
            }
            destination.gifts.head = source.gifts.head;
            destination.gifts.size += source.gifts.size;
            emptyBelt(source);
        }
        BW.write(Integer.toString(destination.gifts.size));
        BW.newLine();

    }

    void emptyBelt(Belt belt) {
        belt.gifts.size = 0;
        belt.gifts.head = null;
        belt.gifts.tail = null;
    }

    void moveFrontPackage(int src, int dst) throws IOException {
        Belt source = belts[src];
        Belt destination = belts[dst];

        Gift sourceHead = source.gifts.head;
        Gift destinationHead = destination.gifts.head;

        if (!(source.gifts.isEmpty() && destination.gifts.isEmpty())) {
            if (!source.gifts.isEmpty() && !destination.gifts.isEmpty()) {
                Gift sourceRight = sourceHead.right;
                Gift destRight = destinationHead.right;

                sourceHead.right = destRight;
                destinationHead.right = sourceRight;
                if (sourceRight == null && destRight == null) {
                    source.gifts.tail = destinationHead;
                    destination.gifts.tail = sourceHead;
                } else if (destRight == null) {
                    sourceRight.left = destinationHead;
                    destination.gifts.tail = sourceHead;
                } else if (sourceRight == null) {
                    destRight.left = sourceHead;
                    source.gifts.tail = destinationHead;
                } else {
                    destRight.left = sourceHead;
                    sourceRight.left = destinationHead;
                }
                source.gifts.head = destinationHead;
                destination.gifts.head = sourceHead;
            } else if (source.gifts.isEmpty()) {
                if (destination.gifts.size == 1) {
                    emptyBelt(destination);
                } else {
                    destination.gifts.head = destinationHead.right;
                    destination.gifts.head.left = null;
                    destination.gifts.size -= 1;
                }
                source.gifts.head = destinationHead;
                source.gifts.tail = destinationHead;
                destinationHead.right = null;

                source.gifts.size = 1;
            } else if (destination.gifts.isEmpty()) {
                if (source.gifts.size == 1) {
                    emptyBelt(source);
                } else {
                    source.gifts.head = sourceHead.right;
                    source.gifts.head.left = null;
                    source.gifts.size -= 1;
                }
                destination.gifts.head = sourceHead;
                destination.gifts.tail = sourceHead;
                sourceHead.right = null;

                destination.gifts.size = 1;
            }
        }



        BW.write(Integer.toString(destination.gifts.size));
        BW.newLine();
    }

    void dividePackage(int src, int dst) throws IOException {
        Belt source = belts[src];
        Belt destination = belts[dst];
        int toMoveSize = source.gifts.size / 2;
        int currentSize = 1;
        Gift tail = source.gifts.head;
        if (source.gifts.size > 1) {
            while (currentSize < toMoveSize) {
                tail = tail.right;
                currentSize += 1;
            }
            source.gifts.size -= toMoveSize;
            destination.gifts.size += toMoveSize;

            Gift newHead = tail.right;
            newHead.left = null;

            tail.right = destination.gifts.head;
            if (destination.gifts.head != null) {
                destination.gifts.head.left = tail;
            } else {
                destination.gifts.tail = tail;
            }
            destination.gifts.head = source.gifts.head;
            source.gifts.head = newHead;
        }


        BW.write(Integer.toString(destination.gifts.size));
        BW.newLine();
    }

    void getPackageInfo(int gNum) throws IOException {
        Gift gift = gifts[gNum];
        int a, b;
        if (gift.left == null) {
            a = -1;
        } else {
            a = gift.left.index;
        }

        if (gift.right == null) {
            b = -1;
        } else {
            b = gift.right.index;
        }

        BW.write(Integer.toString(a + 2 * b));
        BW.newLine();
    }

    void getBeltInfo(int bNum) throws IOException {
        Belt belt = belts[bNum];
        int a, b, c;
        if (belt.gifts.head == null) {
            a = -1;
        } else {
            a = belt.gifts.head.index;
        }

        if (belt.gifts.tail == null) {
            b = -1;
        } else {
            b = belt.gifts.tail.index;
        }

        c = belt.gifts.size;

        BW.write(Integer.toString(a + 2 * b + 3 * c));
        BW.newLine();

    }

    static class DoublyLinkedList {
        Gift head;
        Gift tail;
        int size;

        public DoublyLinkedList() {
            this.size = 0;
        }

        void addGift(Gift gift) {
            if (head == null) {
                head = gift;
                gift.left = null;
                gift.right = null;
            } else {
                tail.right = gift;
                gift.left = tail;
            }
            tail = gift;
            size += 1;
        }


        boolean isEmpty() {
            return head == null;
        }
    }

    static class Gift {
        int index;
        Gift left;
        Gift right;

        public Gift(int index) {
            this.index = index;
        }
    }

    static class Belt {
        int index;

        DoublyLinkedList gifts;

        public Belt(int index) {
            this.index = index;
            this.gifts = new DoublyLinkedList();

        }
    }

}
