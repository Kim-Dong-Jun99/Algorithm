import sys
import math

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [sys.maxsize for _ in range(nodenum)]


def init(node, start, end):
    if start == end:
        tree[node] = ns[end]
        return tree[node]
    else:
        mid = (start + end) // 2
        temp = min(init(node * 2 + 1, start, mid), init(node * 2 + 2, mid + 1, end))
        tree[node] = temp
        return tree[node]


def getMin(node, start, end, left, right):
    if end < left or start > right:
        return sys.maxsize

    if left <= start and end <= right:
        return tree[node]
    mid = (start + end) // 2
    return min(getMin(node * 2 + 1, start, mid, left, right), getMin(node * 2 + 2, mid + 1, end, left, right))


def update(node, start, end, index, v):
    if index < start or end < index:
        return tree[node]
    if start == end:

        tree[node] = v
        return tree[node]
    else:
        mid = (start + end) // 2
        tree[node] = min(update(node * 2 + 1, start, mid, index, v), update(node * 2 + 2, mid + 1, end, index, v))
        return tree[node]


init(0, 0, n - 1)

m = int(sys.stdin.readline())
for _ in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    if a == 1:
        update(0, 0, n - 1, b - 1, c)
    else:
        print(getMin(0, 0, n - 1, b - 1, c - 1))



