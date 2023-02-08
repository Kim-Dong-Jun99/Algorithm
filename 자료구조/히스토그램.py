import sys


class Node:
    def __init__(self, left_child, right_child, value):
        self.left_child = left_child
        self.right_child = right_child
        self.value = value


def get_middle(left, right):
    return (left + right) // 2


def smaller(i, j):
    if i < j:
        return i
    return j


def create_segment_tree(left, right):
    if left == right:
        return Node(None, None, recs[left])
    else:
        middle = get_middle(left, right)
        left_child = create_segment_tree(left, middle)
        right_child = create_segment_tree(middle + 1, right)
        value = smaller(left_child.value, right_child.value)
        return Node(left_child, right_child, value)


def get_max_length_left(node, left, right, index, value):
    if index < left:
        return 0
    if left == right:
        if node.value >= value:
            return 1
        else:
            return 0
    else:
        if node.value >= value:
            if right < index:
                return right - left + 1
            else:
                return index - left + 1
        else:
            middle = get_middle(left, right)
            if index <= middle:
                left_v = get_max_length_left(node.left_child, left, middle, index, value)
                return left_v

            else:
                right_v = get_max_length_left(node.right_child, middle + 1, right, index, value)
                if right_v == index - middle:
                    left_v = get_max_length_left(node.left_child, left, middle, index, value)
                    return left_v + right_v
                else:
                    return right_v


def get_max_length_right(node, left, right, index, value):
    if right < index:
        return 0

    if left == right:
        if node.value >= value:
            return 1
        else:
            return 0
    else:
        if node.value >= value:
            if index < left:
                return right - left + 1
            else:
                return right - index + 1
        else:
            middle = get_middle(left, right)
            if middle < index:
                right_v = get_max_length_right(node.right_child, middle + 1, right, index, value)
                return right_v
            else:
                left_v = get_max_length_right(node.left_child, left, middle, index, value)
                if left_v == middle - index + 1:
                    right_v = get_max_length_right(node.right_child, middle + 1, right, index, value)
                    return right_v + left_v
                else:
                    return left_v


N = int(sys.stdin.readline())
recs = [int(sys.stdin.readline()) for _ in range(N)]

root = create_segment_tree(0, N - 1)
result = 0
for i in range(N):

    right_length = get_max_length_right(root, 0, N - 1, i, recs[i])
    left_length = get_max_length_left(root, 0, N - 1, i, recs[i])
    length = right_length + left_length - 1
    # print("%d : %d : %d : %d" % (i, recs[i], right_length, left_length))

    if result < length * recs[i]:
        result = length * recs[i]
print(result)
