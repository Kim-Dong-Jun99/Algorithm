import sys

sys.setrecursionlimit(1000000)


class Node:

    def __init__(self, left_child, right_child, value):
        self.leftChild = left_child
        self.rightChild = right_child
        self.value = value
        self.toChange = -1


def get_middle(left, right):
    return (left + right) // 2


def create_segment_tree(left, right):
    if left == right:
        return Node(None, None, numbers[left])
    else:
        middle = get_middle(left, right)
        left_node = create_segment_tree(left, middle)
        right_node = create_segment_tree(middle + 1, right)
        return Node(left_node, right_node, left_node.value ^ right_node.value)


def query(node, left, right, left_limit, right_limit):
    if right < left_limit:
        return -1
    if right_limit < left:
        return -1
    middle = get_middle(left, right)
    if (right - left + 1) % 2 == 1 and node.toChange != -1:
        node.value ^= node.toChange
        if node.leftChild:
            node.leftChild.toChange ^= node.toChange
        if node.rightChild:
            node.rightChild.toChange ^= node.toChange
    node.toChange = -1
    if left_limit <= left and right <= right_limit:
        return node.value

    lr = query(node.leftChild, left, middle, left_limit, right_limit)
    rr = query(node.rightChild, middle + 1, right, left_limit, right_limit)
    if lr == -1:
        return rr
    elif rr == -1:
        return lr

    return lr ^ rr


def update(node, left, right, left_limit, right_limit, k):
    if right < left_limit:
        return
    if right_limit < left:
        return

    middle = get_middle(left, right)
    if left_limit <= left and right <= right_limit:
        if node.toChange == -1:
            node.toChange = k
        else:
            node.toChange ^= k
        return

    if (min(right, right_limit) - max(left, left_limit) + 1) % 2 == 1:
        node.value ^= k
    update(node.leftChild, left, middle, left_limit, right_limit, k)
    update(node.rightChild, middle + 1, right, left_limit, right_limit, k)


N = int(sys.stdin.readline())

numbers = list(map(int, sys.stdin.readline().split()))
root = create_segment_tree(0, N - 1)

M = int(sys.stdin.readline())

for _ in range(M):
    q = list(map(int, sys.stdin.readline().split()))

    if q[0] == 1:
        update(root, 0, N - 1, q[1], q[2], q[3])
    else:
        sys.stdout.write(str(query(root, 0, N - 1, q[1], q[2])) + "\n")
