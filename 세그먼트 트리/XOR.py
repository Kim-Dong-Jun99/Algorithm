import sys
import math
import operator


def smaller(i, j):
    if operator.lt(i, j):
        return i
    return j


def bigger(i, j):
    if operator.lt(i, j):
        return j
    return i


def get_middle(left, right):
    return operator.floordiv(operator.add(left, right), 2)


def propagate(index, left, right):
    if lazy[index] != 0:
        if (right - left + 1) % 2 == 1:
            tree[index] = operator.xor(tree[index], lazy[index])
        if left != right:
            left_c = index * 2 + 1
            lazy[left_c] = operator.xor(lazy[left_c], lazy[index])
            right_c = index * 2 + 2
            lazy[right_c] = operator.xor(lazy[right_c], lazy[index])
        lazy[index] = 0


def query(index, left, right, left_limit, right_limit):
    propagate(index, left, right)
    if operator.lt(right, left_limit):
        return 0
    if operator.lt(right_limit, left):
        return 0
    middle = get_middle(left, right)
    mul = operator.mul(index, 2)
    left_c = operator.add(mul, 1)
    right_c = operator.add(mul, 2)

    if operator.le(left_limit, left) and operator.le(right, right_limit):
        return tree[index]
    return query(left_c, left, middle, left_limit, right_limit) ^ query(right_c, operator.add(middle, 1), right,
                                                                        left_limit, right_limit)


def update(index, left, right, left_limit, right_limit, k):
    propagate(index, left, right)
    if operator.lt(right, left_limit):
        return
    if operator.lt(right_limit, left):
        return

    middle = get_middle(left, right)
    if operator.le(left_limit, left) and operator.le(right, right_limit):
        lazy[index] ^= k
        propagate(index, left, right)
        return
    left_c = operator.add(operator.mul(index, 2), 1)
    right_c = operator.add(operator.mul(index, 2), 2)
    update(left_c, left, middle, left_limit, right_limit, k)
    update(right_c, operator.add(middle, 1), right, left_limit, right_limit, k)
    tree[index] = tree[index * 2]
    if index * 2 + 2 < node_num:
        tree[index] = tree[index * 2 + 1] ^ tree[index * 2 + 2]


N = int(sys.stdin.readline())

H = math.log2(N)

if int(H) == math.ceil(H):
    node_num = 2 * N - 1
else:
    node_num = 2 ** (math.ceil(H) + 1) - 1

numbers = list(map(int, sys.stdin.readline().split()))
tree = [0] * node_num
lazy = [0] * node_num
for i_ in range(N):
    update(0, 0, N - 1, i_, i_, numbers[i_])
M = int(sys.stdin.readline())

for _ in range(M):
    q = list(map(int, sys.stdin.readline().split()))

    if q[0] == 1:
        update(0, 0, N - 1, q[1], q[2], q[3])
    else:
        sys.stdout.write(str(query(0, 0, N - 1, q[1], q[2])) + "\n")

