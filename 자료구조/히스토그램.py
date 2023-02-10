import sys
from collections import deque

sys.setrecursionlimit(1000000)


class Node:
    def __init__(self, left_child, right_child, value):
        self.left_child = left_child
        self.right_child = right_child
        self.value = value


def get_middle(left, right):
    return (left + right) // 2


def smaller(i, j):
    if recs[i] < recs[j]:
        return i
    return j


def bigger(i, j):
    if i < j:
        return j
    return i


# 구간 내 최소 값 인덱스를 저장하는 세그먼트 트리 생성
def create_segment_tree(left, right):
    if left == right:
        return Node(None, None, left)
    else:
        middle = get_middle(left, right)
        left_child = create_segment_tree(left, middle)
        right_child = create_segment_tree(middle + 1, right)
        value = smaller(left_child.value, right_child.value)
        return Node(left_child, right_child, value)


def search_min_index(node, left, right, left_limit, right_limit):
    # print("search left : right = %d %d"%(left, right))

    if right < left_limit:
        return -1
    if right_limit < left:
        return -1
    if left_limit <= left and right <= right_limit:
        return node.value
    middle = get_middle(left, right)
    left_v = search_min_index(node.left_child, left, middle, left_limit, right_limit)
    right_v = search_min_index(node.right_child, middle + 1, right, left_limit, right_limit)
    # print("search left %d, right %d = left_v, right_v : %d %d"%(left, right, left_v, right_v))
    if left_v == -1:
        return right_v
    if right_v == -1:
        return left_v
    if recs[left_v] < recs[right_v]:
        return left_v
    return right_v


N = int(sys.stdin.readline())
recs = [int(sys.stdin.readline()) for _ in range(N)]

root = create_segment_tree(0, N - 1)
result = 0
q = deque([(0, N - 1)])
while q:
    left, right = q.popleft()
    min_index = search_min_index(root, 0, N - 1, left, right)
    temp_result = recs[min_index] * (right - left + 1)
    result = bigger(result, temp_result)

    if left <= min_index - 1:
        q.append((left, min_index - 1))
    if min_index + 1 <= right:
        q.append((min_index + 1, right))

print(result)
