import sys
import time
import math

sys.setrecursionlimit(1000000)

# start = time.time()


def get_middle(left, right):
    return (left + right) // 2


def create_segment_tree(index, left, right):
    if left == right:
        tree[index] = numbers[left]
    else:
        middle = get_middle(left, right)
        create_segment_tree(index * 2 + 1, left, middle)
        create_segment_tree(index * 2 + 2, middle + 1, right)
        tree[index] = tree[index * 2 + 1] ^ tree[index * 2 + 2]


def query(index, left, right, left_limit, right_limit):
    if right < left_limit:
        return -1
    if right_limit < left:
        return -1
    middle = get_middle(left, right)
    if lazy[index] != -1:
        if (right - left + 1) % 2 == 1:
            tree[index] ^= lazy[index]
        if index * 2 + 1 < node_num:
            if lazy[index * 2 + 1] == -1:
                lazy[index * 2 + 1] = lazy[index]
            else:
                lazy[index * 2 + 1] ^= lazy[index]
        if index * 2 + 2 < node_num:
            if lazy[index * 2 + 2] == -1:
                lazy[index * 2 + 2] = lazy[index]
            else:
                lazy[index * 2 + 2] ^= lazy[index]
    lazy[index] = -1
    if left_limit <= left and right <= right_limit:
        return tree[index]
    lr = query(index * 2 + 1, left, middle, left_limit, right_limit)
    rr = query(index * 2 + 2, middle + 1, right, left_limit, right_limit)
    if lr == -1:
        return rr
    elif rr == -1:
        return lr

    return lr ^ rr


def update(index, left, right, left_limit, right_limit, k):
    if right < left_limit:
        return
    if right_limit < left:
        return

    middle = get_middle(left, right)
    if left_limit <= left and right <= right_limit:
        if lazy[index] == -1:
            lazy[index] = k
        else:
            lazy[index] ^= k
        return

    if (min(right, right_limit) - max(left, left_limit) + 1) % 2 == 1:
        tree[index] ^= k
    update(index * 2 + 1, left, middle, left_limit, right_limit, k)
    update(index * 2 + 2, middle + 1, right, left_limit, right_limit, k)


N = int(sys.stdin.readline())

H = math.log2(N)

if int(H) == math.ceil(H):
    node_num = 2 * N - 1
else:
    node_num = 2 ** (math.ceil(H) + 1) - 1

numbers = list(map(int, sys.stdin.readline().split()))
tree = [0] * node_num
lazy = [-1] * node_num
create_segment_tree(0, 0, N - 1)
# tree_create_time = time.time()
# print("tree time : %.5f" % (tree_create_time - start))

M = int(sys.stdin.readline())

for _ in range(M):
    q = list(map(int, sys.stdin.readline().split()))

    if q[0] == 1:
        update(0, 0, N - 1, q[1], q[2], q[3])
    else:
        sys.stdout.write(str(query(0, 0, N - 1, q[1], q[2])) + "\n")

# end = time.time()
# print(f"{end - start:.5f} sec")
