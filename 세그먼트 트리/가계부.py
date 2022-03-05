import sys
import math

n, q = map(int, sys.stdin.readline().split())
ns = [0 for _ in range(n)]
h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [0 for _ in range(nodenum)]


def update(node, start, end, index, v):
    if index < start or end < index:
        return
    tree[node] += v
    if start != end:
        mid = (start + end) // 2
        update(node * 2 + 1, start, mid, index, v)
        update(node * 2 + 2, mid + 1, end, index, v)


def subsum(node, start, end, left, right):
    if left > end or right < start:
        return 0
    if left <= start and end <= right:
        return tree[node]
    mid = (start + end) // 2
    return subsum(node * 2 + 1, start, mid, left, right) + subsum(node * 2 + 2, mid + 1, end, left, right)


for _ in range(q):
    a, b, c = map(int, sys.stdin.readline().split())
    if a == 1:
        update(0, 0, n - 1, b - 1, c)
    else:
        print(subsum(0, 0, n - 1, b - 1, c - 1))

