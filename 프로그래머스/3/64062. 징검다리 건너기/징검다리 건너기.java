import java.util.*;

/*
징검다리 규칙

디딤돌의 숫자는 한번 밟을 때마다 1씩 줄어든다.
0이 되면 더 이상 밟을 수 없다. 이때는 그 다음 디딤돌로 여러 칸을 건너 뛸 수 있다.
다음으로 밟을 수 있는 디딤돌이 여러개면, 무조건 가장 가까운 디딤돌로만 뛸 수 있음

최대 몇명이 건너뛸 수 있냐

stones 길이 <= 200_000
stones 각 원소 <= 2_000_000_000

친구는 무제한,

길이가 k 인 subList의 최대값의 최소를 구하면될듯
*/

class Solution {
    int[] stones;
    int n;
    int k;
    int leftIndex;
    int rightIndex;
    PriorityQueue<Node> heap;
    public int solution(int[] stones, int k) {
        init(stones, k);
        return solve();
    }

    void init(int[] stones, int k) {
        this.stones = stones;
        this.k = k;
        n = stones.length;
        leftIndex = 0;
        rightIndex = k - 1;
        heap = new PriorityQueue<>(Node::compareWithDescValue);
        initHeap();
    }
    
    void initHeap() {
        for (int i = 0; i < rightIndex; i++) {
            heap.add(new Node(stones[i], i));
        }
    }

    int solve() {
        PriorityQueue<Integer> result = new PriorityQueue<>();
        while (rightIndex < n) {
            heap.add(new Node(stones[rightIndex], rightIndex));
            while (!heap.isEmpty() & !isValid(leftIndex, rightIndex, heap.peek().index)) {
                heap.remove();
            }
            result.add(heap.peek().value);
            rightIndex += 1;
            leftIndex += 1;
            
        }
        return result.peek();
    }
    
    boolean isValid(int leftIndex, int rightIndex, int index) {
        return leftIndex <= index && index <= rightIndex;
    }

    static class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        int compareWithDescValue(Node compare) {
            return Integer.compare(compare.value, this.value);
        }

    }

}


