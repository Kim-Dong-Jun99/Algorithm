import sys
import bisect


class Node:

    def __init__(self, left_child, right_child, value):
        self.leftChild = left_child
        self.rightChild = right_child
        self.value = value


def get_middle(left, right):
    return (left + right) // 2


def merge(left, right):
    result = []
    l = len(left)
    r = len(right)
    i = 0
    j = 0
    while True:
        if i == l:
            for k in range(j, r):
                result.append(right[k])
            break
        if j == r:
            for k in range(i, l):
                result.append(left[k])
            break

        if left[i] < right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    return result


def create_segment_tree(left, right):
    if left == right:
        return Node(None, None, [numbers[left]])
    else:
        middle = get_middle(left, right)
        left_node = create_segment_tree(left, middle)
        right_node = create_segment_tree(middle + 1, right)
        value = merge(left_node.value, right_node.value)
        return Node(left_node, right_node, value)


def query(node, left, right, left_limit, right_limit, v):
    if right < left_limit:
        return 0
    if right_limit < left:
        return 0
    if left_limit <= left and right <= right_limit:
        return len(node.value) - bisect.bisect_right(node.value, v)
    middle = get_middle(left, right)
    return query(node.leftChild, left, middle, left_limit, right_limit, v) + query(node.rightChild, middle + 1, right, left_limit, right_limit, v)



N = int(sys.stdin.readline())

numbers = list(map(int, sys.stdin.readline().split()))

root = create_segment_tree(0, N - 1)

Q = int(sys.stdin.readline())

for _ in range(Q):
    i, j, k = map(int, sys.stdin.readline().split())
    sys.stdout.write(str(query(root, 0, N-1, i - 1, j - 1, k))+"\n")
