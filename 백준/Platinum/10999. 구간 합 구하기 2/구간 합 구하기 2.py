import sys
import math

n, m, k = map(int, sys.stdin.readline().split())

ns = [int(sys.stdin.readline()) for _ in range(n)]
# difstack = [0]*n
h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [0] * nodenum
lazy = [0] * nodenum

def init(node, start, end):
    if start == end:
        tree[node] = ns[start]
        return tree[node]
    else:
        mid = (start + end) // 2
        tree[node] = init(node * 2 + 1, start, mid) + init(node * 2 + 2, mid + 1, end)
        return tree[node]


init(0, 0, n - 1)


def subsum(node, start, end, left, right):
    if end < left or right < start:
        return 0

    tree[node] += lazy[node] * (end - start + 1)
    if start != end:
        lazy[node*2+1] += lazy[node]
        lazy[node*2+2] += lazy[node]
    lazy[node] = 0
    if left <= start and end <= right:
        return tree[node]
    mid = (start + end) // 2
    return subsum(node * 2 + 1, start, mid, left, right) + subsum(node * 2 + 2, mid + 1, end, left, right)


def update(node, start, end, left, right, diff):
    if right < start or end < left:
        return
    if left <= start and end <= right:
        lazy[node] += diff
        return
    else:
        tree[node] += diff*(min(right, end) - max(start, left)+1)
        if start != end:
            mid = (start + end) // 2
            update(node * 2 + 1, start, mid, left, right, diff)
            update(node * 2 + 2, mid + 1, end, left, right, diff)


for _ in range(m + k):
    q = list(map(int, sys.stdin.readline().split()))
    if q[0] == 1:
        update(0, 0, n - 1, q[1] - 1, q[2] - 1, q[3])
        # difstack[q[1]-1] += q[3]
        # difstack[q[2]-1] += q[3]
    else:
        print(subsum(0, 0, n - 1, q[1] - 1, q[2] - 1))