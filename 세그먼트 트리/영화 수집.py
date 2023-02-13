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
        # value에는 dvd 번호가 들어감
        return Node(None, None, left)
    else:
        middle = get_middle(left, right)
        left_node = create_segment_tree(left, middle)
        right_node = create_segment_tree(middle + 1, right)
        # 더 위에 있는 dvd 번호를 저장
        if dvds[left_node.value] < dvds[right_node.value]:
            value = left_node.value
        else:
            value = right_node.value
        return Node(left_node, right_node, value)


def update(node, left, right, index):
    # 구간 최소값이 인덱스보다 적으면 실행 될 이유 없음!
    if dvds[node.value] >= index:
        return
    if left == right:
        dvds[node.value] += 1
        return
    middle = get_middle(left, right)
    update(node.leftChild, left, middle, index)
    update(node.rightChild, middle + 1, right, index)





for _ in range(T):
    N, M = map(int, sys.stdin.readline().split())
    movies = list(map(int, sys.stdin.readline().split()))
    # i 번 dvd 위에 몇개의 dvd가 있는지를 저장하는 리스트
    dvds = [i - 1 for i in range(N+1)]
    dvds[0] = sys.maxsize
    root = create_segment_tree(1, N)
    for j in movies:
        sys.stdout.write(str(dvds[j])+" ")

        # j번째 높이보다 낮으면 +1
        update(root, 1, N, dvds[j])
        # print(dvds)
        dvds[j] = 0

    print()