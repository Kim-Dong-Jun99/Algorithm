import sys

T = int(sys.stdin.readline())


class Node:
    def __init__(self, left_child, right_child, value):
        self.leftChild = left_child
        self.rightChild = right_child
        self.value = value


def get_middle(left, right):
    return (left + right) // 2


def create_segment_tree(left, right):
    if left == right:
        return Node(None, None, {movies[left]})
    else:
        middle = get_middle(left, right)
        left_node = create_segment_tree(left, middle)
        right_node = create_segment_tree(middle + 1, right)
        value = left_node.value | right_node.value
        return Node(left_node, right_node, value)


def find_rank(node, left, right, left_limit, right_limit):
    if left > right_limit:
        return set()
    if right < left_limit:
        return set()
    if left_limit < left and right < right_limit:
        return node.value
    if left != right:
        middle = get_middle(left, right)
        return find_rank(node.leftChild, left, middle, left_limit, right_limit) | find_rank(node.rightChild, middle + 1,
                                                                                            right, left_limit,
                                                                                            right_limit)
    else:
        return set()


def create_segment_tree_for_init(left, right):
    if left == right:
        return Node(None, None, 0)
    else:
        middle = get_middle(left, right)
        left_v = create_segment_tree_for_init(left, middle)
        right_v = create_segment_tree_for_init(middle + 1, right)
        return Node(left_v, right_v, 0)


def sub_sum(node, left, right, left_limit, right_limit):
    if left_limit > right:
        return 0
    if right_limit < left:
        return 0
    if left_limit <= left and right <= right_limit:
        return node.value
    middle = get_middle(left, right)
    return sub_sum(node.leftChild, left, middle, left_limit, right_limit) + sub_sum(node.rightChild, middle + 1, right,
                                                                                    left_limit, right_limit)


def update_init(node, left, right, index_):
    if left > index_:
        return
    if right < index_:
        return
    node.value += 1

    if left != right:
        middle = get_middle(left, right)
        update_init(node.leftChild, left, middle, index_)
        update_init(node.rightChild, middle + 1, right, index_)


for _ in range(T):
    N, M = map(int, sys.stdin.readline().split())
    movies = list(map(int, sys.stdin.readline().split()))
    visited = [-1] * (N + 1)
    for_init = create_segment_tree_for_init(1, N)

    # print(visited)
    root = create_segment_tree(0, M - 1)
    index = 0
    for i in movies:
        if visited[i] == -1:
            sys.stdout.write(str(sub_sum(for_init, 1, N, i, N) + i - 1) + " ")
            update_init(for_init, 1, N, i)
        else:
            sys.stdout.write(str(len(find_rank(root, 0, M - 1, visited[i], index))) + " ")
        visited[i] = index
        index += 1
    print()


