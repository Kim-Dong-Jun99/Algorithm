import sys
import math
sys.setrecursionlimit(10**6)
n, m, k = map(int, sys.stdin.readline().split())
ns = [int(sys.stdin.readline()) for _ in range(n)]

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [1 for _ in range(nodenum)]


def init(node, start, end):
    if start == end:
        tree[node] = ns[start]
        return tree[node]
    else:
        tree[node] = init(node * 2 + 1, start, (start + end) // 2) * init(node * 2 + 2, (start + end) // 2 + 1, end)
        return tree[node]


def update(node, start, end, index, diff):
    if index < start or end < index:
        return 0

    tree[node] *= diff

    if start != end:
        update(node * 2 + 1, start, (start + end) // 2, index, diff)
        update(node * 2 + 2, (start + end) // 2 + 1, end, index, diff)


def updatezero(node, start, end, index):
    itr = node
    s = start
    e = end
    while s != index or e != index:
        if s <= index <= (s+e)//2:
            itr = itr*2+1
            e = (s+e)//2
        else:
            itr = itr*2+2
            s = (s+e)//2+1

    tree[itr] = ns[index]
    # print(itr)
    # parent = int(child/2)-1
    parent = int(itr/2)-1
    while parent >= 0:
        print(parent)
        tree[parent] = tree[parent*2+1] * tree[parent*2+2]
        parent = math.ceil(parent/2)-1



def getresult(node, start, end, left, right):
    if end < left or right < start:
        return 1
    if left <= start and end <= right:
        return tree[node]
    return getresult(node * 2 + 1, start, (start + end) // 2, left, right) * getresult(node * 2 + 2,(start + end) // 2 + 1, end,left, right)


init(0, 0, n - 1)
for _ in range(m + k):
    query = list(map(int, sys.stdin.readline().split()))
    # print(tree)
    # print(ns)
    if query[0] == 1:
        index = query[1] - 1
        if ns[index] == 0:
            ns[index] = query[2]
            updatezero(0, 0, n - 1, index)
        else:
            diff = query[2] / ns[index]
            ns[index] = query[2]
            update(0, 0, n - 1, index, diff)
    else:
        print(int(getresult(0, 0, n - 1, query[1] - 1, query[2] - 1)))