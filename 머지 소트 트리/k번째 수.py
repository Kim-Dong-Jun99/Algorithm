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
    result_ = []
    l = len(left)
    r = len(right)
    i, j = 0, 0
    while True:
        if i == l:
            for k in range(j, r):
                result_.append(right[k])
            break
        if j == r:
            for k in range(i, l):
                result_.append(left[k])
            break
        if left[i] < right[j]:
            result_.append(left[i])
            i += 1
        else:
            result_.append(right[j])
            j += 1
    return result_


def create_segment_tree(left, right):
    if left == right:
        return Node(None, None, [numbers[left]])
    else:
        middle = get_middle(left, right)
        left_node = create_segment_tree(left, middle)
        right_node = create_segment_tree(middle + 1, right)
        return Node(left_node, right_node, merge(left_node.value, right_node.value))


def query(node, left, right, left_limit, right_limit, v):
    if right < left_limit:
        return 0
    if left > right_limit:
        return 0
    if left_limit <= left and right <= right_limit:
        to_return = bisect.bisect_left(node.value, v)
        global visited
        if to_return < len(node.value) and node.value[to_return] == v:
            visited = True
        return to_return
    middle = get_middle(left, right)
    return query(node.leftChild, left, middle, left_limit, right_limit, v) + query(node.rightChild, middle + 1, right,
                                                                                   left_limit, right_limit, v)


N, Q = map(int, sys.stdin.readline().split())

numbers = list(map(int, sys.stdin.readline().split()))

root = create_segment_tree(0, N - 1)

for _ in range(Q):
    # print("index = %d" % _)
    i, j, k = map(int, sys.stdin.readline().split())
    left_ = 0
    right_ = N - 1
    while left_ <= right_:
        middle_ = get_middle(left_, right_)
        visited = False
        temp_v = root.value[middle_]
        temp_result = query(root, 0, N - 1, i - 1, j - 1, temp_v)
        # print(temp_v)
        # print(temp_result)
        if temp_result == k - 1:
            if visited:
                sys.stdout.write(str(temp_v) + "\n")
                break
            else:
                left_ = middle_ + 1
        elif temp_result > k - 1:
            right_ = middle_ - 1
        else:
            left_ = middle_ + 1
