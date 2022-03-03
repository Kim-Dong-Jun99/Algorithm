import sys
import math

n, m = map(int, sys.stdin.readline().split())
ns = list(map(int, sys.stdin.readline().split()))

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [[] for _ in range(nodenum)]


def merge(a, b, k):
    result = []
    ai = 0
    bi = 0
    while ai < len(a) and bi < len(b) and len(result) < k:
        if a[ai] < b[bi]:
            result.append(a[ai])
            ai += 1
        else:
            result.append(b[bi])
            bi += 1


    if len(result) < k:
        while ai < len(a) and len(result) < k:
            result.append(a[ai])
            ai += 1
        while bi < len(b) and len(result) < k:
            result.append(b[bi])
            bi += 1
    return result


def init(node, start, end):
    if start == end:
        tree[node] = [ns[end]]
        return tree[node]
    else:
        mid = (start + end) // 2
        tree[node] = merge(init(node * 2 + 1, start, mid), init(node * 2 + 2, mid + 1, end),n)
        return tree[node]


def getk(node, start, end, left, right,k):
    if end < left or right < start:
        return []

    if left <= start and end <= right:
        return tree[node]
    mid = (start + end) // 2
    return merge(getk(node * 2 + 1, start, mid, left, right,k), getk(node * 2 + 2, mid + 1, end, left, right,k),k)


init(0, 0, n - 1)

for _ in range(m):
    a,b,k = map(int,sys.stdin.readline().split())
    print(getk(0,0,n-1,a-1,b-1,k)[k-1])
