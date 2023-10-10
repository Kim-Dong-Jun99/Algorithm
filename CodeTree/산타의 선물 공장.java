import java.util.*;
import java.io.*;

/*
q개의 명령

1. 공장 설립
m개의 벨트 설치, 벨트위 n / m 개의 물건들이 놓여져 총 n 개의 물건을 준비한다.
각 물건에는 고유 아이디, 무게가 있음, 무게가 동일한 상자 존재가능

2. 물건하차
산타가 원하는 상자의 최대 무게인 w_max가 주어진다. 1 ~ m까지 순서대로 벨트를 보며
벨트의 맨 앞 선물이 w_max 이하라면 하차, 그렇지 않으면 해당 선물을 맨뒤로 보냄, 하차된 무게의 총합 출력

3. 물건 제거
산타가 제거하기를 원하는 물건의 아이디가 주어짐, 해당 물건이 있다면 제거하고, 없다면 -1

4. 물건 확인
확인을 원하는 물건 고유 번호가 주어짐, 상자가 있는 벨트 번호를 출력함, 벨트에 없으면 -1, 있다면 벨트번호를 출력하고 맨앞으로 댕겨옴
-> 물건 확인은, 유니온 파인드 써야겠다

물건제거만 되고 다시 벨트로 올라가는 경우가 없음, 제거되었는지 여부를 판단하기위해서 뭐 해쉬셋하나 만들어놓으면될듯?

5. 벨트 고장
고장이 발생한 벨트 번호가 주어진다. 해당 벨트가 고장 나면 해당 벨트는 다시는 사용할 수 없다. 그리고 해당 벨트 오른쪽 벨트로 짐을 옮긴다.
이미 망가져있다면 -1을 출력하고 아니면 벨트 번호를 출력
*/

public class Main {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int UNLOAD_GIFT = 200;
    static final int REMOVE_GIFT = 300;
    static final int CHECK_GIFT = 400;
    static final int BROKEN_BELT = 500;
    int[] inputArray;
    HashMap<Integer, Gift> giftMap;
    HashSet<Integer> removedGift;
    HashMap<Integer, Integer> parent;
    HashMap<Integer, Belt> parentBeltMap;

    Belt[] belts;
    boolean[] brokenBelt;
    int q, n, m;
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
        inputArray = getInputArray();
        n = inputArray[1];
        m = inputArray[2];
        q -= 1;


        giftMap = new HashMap<>();
        belts = new Belt[m];
        brokenBelt = new boolean[m];
        removedGift = new HashSet<>();
        parent = new HashMap<>();
        parentBeltMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            belts[i] = new Belt(i + 1);
        }

        int initialBeltSize = n / m;
        for (int i = 3; i < 3 + n; i++) {
            int id = inputArray[i];
            int weight = inputArray[i + n];
            int bNum = (i - 3) / initialBeltSize;

            Belt belt = belts[bNum];
            Gift gift = new Gift(id, weight);
            giftMap.put(id, gift);
            parent.put(id, id);

            addGiftToBelt(belt, gift);
            setParentBeltMap(belt, gift);

        }
//        printGiftAndBelt();

    }

    void printGiftAndBelt() {
        System.out.println("=========== belt and gift ========");
        for (int id : giftMap.keySet()) {
            System.out.println("id " + id);
            int parentId = find(id);
            System.out.println("belt " + parentBeltMap.get(parentId));
        }
        System.out.println("==================================");
        System.out.println();
    }

    void printBeltInfo() {
        for (Belt belt : belts) {
            System.out.println("belt " + belt.id);
            Gift current = belt.head;
            while (current != null) {
                System.out.print("id "+current.id+" w "+current.weight+" ");
                current = current.right;
            }
            System.out.println();
        }
    }

    void printBeltHead() {
        System.out.println(":::::::::::::print belt head :::::::::");
        for (Belt belt : belts) {
            if (belt.head == null) {
                System.out.println("belt id "+belt.id+" head null");
            } else {

                System.out.println("belt id "+belt.id+" head "+belt.head.id);
            }
        }
    }

    void addGiftToBelt(Belt belt, Gift gift) {
        if (belt.head == null) {
            belt.head = gift;
        } else {
            belt.tail.connectRight(gift);
            gift.connectLeft(belt.tail);
        }
        belt.tail = gift;
    }

    void setParentBeltMap(Belt belt, Gift gift) {
        union(gift.id, belt.head.id);
        int parentId = find(belt.head.id);
        parentBeltMap.put(parentId, belt);
    }

    int find(int giftId) {
        if (giftId == parent.get(giftId)) {
            return giftId;
        }
        parent.put(giftId, find(parent.get(giftId)));
        return parent.get(giftId);
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent.put(px, py);
        }
    }


    void solution() throws IOException {
        while (q-- > 0) {
            inputArray = getInputArray();
            int commandType = inputArray[0];
            int wMAx, rId, fId, bNum;

//            System.out.println("before command " + commandType + " "+inputArray[1]);
//            printBeltInfo();

            if (commandType == UNLOAD_GIFT) {
                wMAx = inputArray[1];
                unloadGift(wMAx);
            }

            if (commandType == REMOVE_GIFT) {
                rId = inputArray[1];
                removeGift(rId);
            }

            if (commandType == CHECK_GIFT) {
                fId = inputArray[1];
                checkGift(fId);
            }

            if (commandType == BROKEN_BELT) {
                bNum = inputArray[1];
                brokenBelt(bNum);
//                printGiftAndBelt();
            }
//            printBeltHead();
//            System.out.println("after command " + commandType+" "+inputArray[1]);
//            printBeltInfo();
//            System.out.println();
        }
        BW.flush();
        BW.close();
    }

    void unloadGift(int weightMax) throws IOException {
        long weightSum = 0;
        for (Belt belt : belts) {
            if (belt.head == null) {
                continue;
            }
            Gift currentHead = belt.head;
            Gift newHead = currentHead.right;
            if (currentHead.weight > weightMax) {
                if (newHead != null) {
                    currentHead.unConnectRight();
                    newHead.unConnectLeft();

                    belt.tail.connectRight(currentHead);
                    currentHead.connectLeft(belt.tail);
                    belt.tail = currentHead;

                    belt.head = newHead;
                }
            } else {
                weightSum += currentHead.weight;
                removedGift.add(currentHead.id);

                if (newHead != null) {
                    newHead.unConnectLeft();
                    currentHead.unConnectRight();
                }
                belt.head = newHead;
                if (newHead == null) {
                    belt.tail = null;
                }
            }

        }
        BW.write(Long.toString(weightSum));
        BW.newLine();

    }

    void removeGift(int giftId) throws IOException {
        if (removedGift.contains(giftId)) {
            BW.write(Integer.toString(-1));
            BW.newLine();
        } else {
            /*
            removed에 추가하고 빼주기만하면된다.
             */
            Gift gift = giftMap.getOrDefault(giftId, null);
            if (gift == null) {
                BW.write(Integer.toString(-1));
                BW.newLine();
                return;
            }
            removedGift.add(giftId);
            int parentId = find(giftId);
            Belt belt = parentBeltMap.get(parentId);

            Gift currentHead = belt.head;
            Gift currentTail = belt.tail;

            Gift left = gift.left;
            Gift right = gift.right;

            gift.unConnectLeft();
            gift.unConnectRight();
            if (left != null) {
                left.connectRight(right);
            }
            if (right != null) {
                right.connectLeft(left);
            }


            if (currentHead.id == gift.id) {

                belt.head = right;
                if (right == null) {
                    emptyBelt(belt);
                }
            } else if (currentTail.id == gift.id) {
                belt.tail = left;
            }



            BW.write(Integer.toString(giftId));
            BW.newLine();
        }
    }

    void checkGift(int giftId) throws IOException {
        if (removedGift.contains(giftId)) {
            BW.write(Integer.toString(-1));
            BW.newLine();
        } else {
            /*
            find를 통해서 parentId를 받아오고 parentbeltmap으로 벨트를 구할 수 있음, 벨트를 구하면 해당 패키지 부터 테일까지 맨앞으로빼야함
             */
            if (!giftMap.containsKey(giftId)) {
                BW.write(Integer.toString(-1));
                BW.newLine();
                return;
            }
            int parentId = find(giftId);
            Belt belt =  parentBeltMap.get(parentId);

            Gift currentHead = belt.head;
            Gift currentTail = belt.tail;
            Gift toMove = giftMap.get(giftId);
//            if (currentHead == null) {
//                BW.write(Integer.toString(-1));
//                BW.newLine();
//                return;
//            }
            if (currentHead.id != toMove.id) {
                Gift left = toMove.left;

                toMove.unConnectLeft();
                left.unConnectRight();

                currentTail.connectRight(currentHead);
                currentHead.connectLeft(currentTail);

                belt.head = toMove;
                belt.tail = left;
            }

            BW.write(Integer.toString(belt.id));
            BW.newLine();
        }

    }

    void brokenBelt(int beltNum) throws IOException {
        if (brokenBelt[beltNum - 1]) {
            BW.write(Integer.toString(-1));
            BW.newLine();
        } else {
            int beltIndex = beltNum - 1;
            Belt belt = belts[beltIndex];
            int toMoveIndex = getRightSideBelt(beltIndex);
            while (brokenBelt[toMoveIndex]) {
                toMoveIndex = getRightSideBelt(toMoveIndex);
            }
            /*
            tomove가 비어있을 수도 있음, 그리고 belt랑 tomove랑 union해줘야함
             */
//            System.out.println("toMoveIndex = " + toMoveIndex);
            Belt destination = belts[toMoveIndex];

//            toMove.tail.connectRight(belt.head);
            if (destination.head != null) {
                if (belt.head != null) {
                    belt.head.connectLeft(destination.tail);
                    destination.tail.connectRight(belt.head);
                    destination.tail = belt.tail;

                    union(belt.head.id, destination.head.id);

                }

            } else {
                if (belt.head != null) {
                    destination.head = belt.head;
                    destination.tail = belt.tail;

                    int parentId = find(belt.head.id);
                    parentBeltMap.put(parentId, destination);
                }
            }

            brokenBelt[beltIndex] = true;
            emptyBelt(belt);
            BW.write(Integer.toString(beltNum));
            BW.newLine();
        }
    }


    void emptyBelt(Belt belt) {
        belt.head = null;
        belt.tail = null;
    }
    int getRightSideBelt(int beltIndex) {
        return (beltIndex + 1) % m;
    }

    static class Belt {
        int id;
        Gift head;
        Gift tail;

        public Belt(int id) {
            this.id = id;
        }
    }


    static class Gift {
        int id;
        int weight;
        Gift left;
        Gift right;

        public Gift(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        void connectRight(Gift right) {
            this.right = right;
        }

        void connectLeft(Gift left) {
            this.left = left;
        }

        void unConnectRight() {
            this.right = null;
        }

        void unConnectLeft() {
            this.left = null;
        }
    }
}
