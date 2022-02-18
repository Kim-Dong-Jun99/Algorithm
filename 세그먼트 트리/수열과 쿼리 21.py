import sys
import math

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))
m = int(sys.stdin.readline())

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [0] * nodenum
lazy = [0] * nodenum


def init(node, start, end):
    if start == end:
        tree[node] = ns[end]
        return tree[node]
    else:
        mid = (start + end) // 2
        tree[node] = init(node * 2 + 1, start, mid) + init(node * 2 + 2, mid + 1, end)
        return tree[node]


init(0, 0, n - 1)


def update(node, start, end, left, right, diff):
    if start > right or end < left:
        return

    if left <= start and end <= right:
        lazy[node] += diff
        return
    else:
        mid = (start + end) // 2
        tree[node] += diff * (min(right, end) - max(start, left) - 1)
        if start != end:
            update(node * 2 + 1, start, mid, left, right, diff)
            update(node * 2 + 2, mid + 1, end, left, right, diff)


def getV(node, start, end, index):
    if index < start or end < index:
        return 0

    tree[node] += (end - start + 1) * lazy[node]

    if start != end:
        lazy[node * 2 + 1] += lazy[node]
        lazy[node * 2 + 2] += lazy[node]
    lazy[node] = 0
    if start == end and start == index:
        return tree[node]
    else:
        mid = (start + end) // 2
        return getV(node * 2 + 1, start, mid, index) + getV(node * 2 + 2, mid + 1, end, index)


for _ in range(m):
    q = list(map(int, sys.stdin.readline().split()))
    if q[0] == 1:
        update(0, 0, n - 1, q[1] - 1, q[2] - 1, q[3])
    else:
        print(getV(0, 0, n - 1, q[1] - 1))


