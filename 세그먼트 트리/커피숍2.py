import sys
import math

n, q = map(int, sys.stdin.readline().split())

ns = list(map(int, sys.stdin.readline().split()))

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [0] * nodenum


def init(node, start, end):
    if start == end:
        tree[node] = ns[start]
        return tree[node]
    else:
        mid = (start + end) // 2
        tree[node] = init(node * 2 + 1, start, mid) + init(node * 2 + 2, mid + 1, end)
        return tree[node]


init(0, 0, n - 1)


def update(node, start, end, index, diff):
    if index < start or index > end:
        return

    tree[node] += diff

    if start != end:
        mid = (start + end) // 2
        update(node * 2 + 1, start, mid, index, diff)
        update(node * 2 + 2, mid + 1, end, index, diff)


def subsum(node, start, end, left, right):
    if end < left or right < start:
        return 0

    if left <= start and end <= right:
        return tree[node]
    mid = (start + end) // 2
    return subsum(node * 2 + 1, start, mid, left, right) + subsum(node * 2 + 2, mid + 1, end, left, right)


for _ in range(q):
    x, y, a, b = map(int, sys.stdin.readline().split())
    if x > y:
        x, y = y, x
    print(subsum(0, 0, n - 1, x - 1, y - 1))
    d = b - ns[a - 1]
    ns[a - 1] = b
    update(0, 0, n - 1, a - 1, d)


