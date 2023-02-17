import sys
import math
import operator

sys.setrecursionlimit(1000000)


def smaller(i, j):
    if operator.lt(i, j):
        return i
    return j


def bigger(i, j):
    if operator.gt(i, j):
        return j
    return i

def get_middle(left, right):
    return operator.floordiv(operator.add(left, right), 2)


def create_segment_tree(index, left, right):
    if left == right:
        tree[index] = numbers[left]
    else:
        middle = get_middle(left, right)
        left_c = operator.add(operator.mul(index, 2), 1)
        right_c = operator.add(operator.mul(index, 2), 2)
        create_segment_tree(left_c, left, middle)
        create_segment_tree(right_c, middle + 1, right)
        tree[index] = operator.xor(tree[left_c], tree[right_c])


def query(index, left, right, left_limit, right_limit):
    if right < left_limit:
        return -1
    if right_limit < left:
        return -1
    middle = get_middle(left, right)
    left_c = operator.add(operator.mul(index, 2), 1)
    right_c = operator.add(operator.mul(index, 2), 2)
    if lazy[index] != -1:
        sub = operator.sub(right, left)
        add = operator.add(sub, 1)
        mod = operator.mod(add, 2)
        if mod == 1:
            tree[index] = operator.xor(tree[index], lazy[index])
        if left_c < node_num:
            if lazy[left_c] == -1:
                lazy[left_c] = lazy[index]
            else:
                lazy[left_c] = operator.xor(lazy[left_c], lazy[index])
        if right_c < node_num:
            if lazy[right_c] == -1:
                lazy[right_c] = lazy[index]
            else:
                lazy[right_c] = operator.xor(lazy[right_c], lazy[index])
    lazy[index] = -1
    if operator.le(left_limit, left) and operator.le(right, right_limit):
        return tree[index]
    lr = query(left_c, left, middle, left_limit, right_limit)
    rr = query(right_c, middle + 1, right, left_limit, right_limit)
    if lr == -1:
        return rr
    elif rr == -1:
        return lr

    return operator.xor(lr, rr)


def update(index, left, right, left_limit, right_limit, k):
    if right < left_limit:
        return
    if right_limit < left:
        return

    middle = get_middle(left, right)
    if operator.le(left_limit, left) and operator.le(right, right_limit):
        if lazy[index] == -1:
            lazy[index] = k
        else:
            lazy[index] ^= k
        return
    left_c = operator.add(operator.mul(index, 2), 1)
    right_c = operator.add(operator.mul(index, 2), 2)
    sub = operator.sub(smaller(right, right_limit), bigger(left, left_limit))
    add = operator.add(sub, 1)
    mod = operator.mod(add, 2)

    if mod == 1:
        tree[index] = operator.xor(tree[index], k)
    update(left_c, left, middle, left_limit, right_limit, k)
    update(right_c, operator.add(middle, 1), right, left_limit, right_limit, k)


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
M = int(sys.stdin.readline())

for _ in range(M):
    q = list(map(int, sys.stdin.readline().split()))

    if q[0] == 1:
        update(0, 0, N - 1, q[1], q[2], q[3])
    else:
        sys.stdout.write(str(query(0, 0, N - 1, q[1], q[2])) + "\n")
