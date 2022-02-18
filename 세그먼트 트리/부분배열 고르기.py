import sys
import math
sys.setrecursionlimit(10**6)
n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))

h = math.log2(n)
if int(h) == math.ceil(h):
    nodenum = 2 * n - 1
else:
    nodenum = 2 ** (math.ceil(h) + 1) - 1

tree = [[] for _ in range(nodenum)]


def init(node, start, end):
    if start == end:
        tree[node].append(end)
        tree[node].append(ns[end])
        return tree[node]
    else:
        mid = (start + end) // 2
        a = init(node * 2 + 1, start, mid)
        b = init(node * 2 + 2, mid + 1, end)
        if ns[a[0]] > ns[b[0]]:
            tree[node].append(b[0])
        else:
            tree[node].append(a[0])
        tree[node].append(a[1] + b[1])
        return tree[node]


init(0, 0, n - 1)


def subsum(node, start, end, left, right):
    if left > end or right < start:
        return [sys.maxsize, 0]
    if left <= start and end <= right:
        return tree[node]
    mid = (start + end) // 2
    l = subsum(node * 2 + 1, start, mid, left, right)
    r = subsum(node * 2 + 2, mid + 1, end, left, right)
    if l[0] == sys.maxsize:
        return [r[0], l[1]+r[1]]
    elif r[0] == sys.maxsize:
        return [l[0], l[1] + r[1]]
    else:
        if ns[l[0]] > ns[r[0]]:
            return [r[0], l[1] + r[1]]
        else:
            return [l[0], l[1] + r[1]]


result = 0

# print(tree)
def calcsum(left, right):
    if left <= right:
        temp = subsum(0, 0, n - 1, left, right)
        v = ns[temp[0]] * temp[1]
        global result
        if v > result:
            result = v
        calcsum(left, temp[0] - 1)
        calcsum(temp[0] + 1, right)

calcsum(0, n-1)
print(result)

