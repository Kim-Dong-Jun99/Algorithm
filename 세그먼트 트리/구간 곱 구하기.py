import sys
import math

div = 1000000007
sys.setrecursionlimit(10 ** 6)
n, m, k = map(int, sys.stdin.readline().split())
ns = [int(sys.stdin.readline()) for _ in range(n)]

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [0 for _ in range(nodenum)]


def init(node, start, end):
    if start == end:
        tree[node] = ns[start]
        return tree[node]
    else:
        tree[node] = init(node * 2 + 1, start, (start + end) // 2) * init(node * 2 + 2, (start + end) // 2 + 1,end) % div
        return tree[node]


def update(node, start, end, index, value):
    # print('%d %d %d %d'%(node,start,end,index))
    if index < start or end < index:
        return
    if start == end:
        tree[node] = value
        # print(node)
        # print(start)
        return
    update(node * 2 + 1, start, (start + end) // 2, index, value)
    update(node * 2 + 2, (start + end) // 2 + 1, end, index, value)

    tree[node] = tree[node * 2 + 1] * tree[node * 2 + 2] % div


def getvalue(node, start, end, left, right):
    if end < left or right < start:
        return 1
    if left <= start and end <= right:

        return tree[node]

    return getvalue(node * 2 + 1, start, (start + end) // 2, left, right) * getvalue(node * 2 + 2,(start + end) // 2 + 1, end, left,right) % div


init(0, 0, n - 1)
# print(tree)
for _ in range(m + k):
    query = list(map(int, sys.stdin.readline().split()))
    if query[0] == 1:
        update(0, 0, n - 1, query[1] - 1, query[2])
    else:
        # print(tree)
        print(getvalue(0, 0, n - 1, query[1] - 1, query[2] - 1))