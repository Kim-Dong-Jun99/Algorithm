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

tree = [sys.maxsize for _ in range(nodenum)]


def init(node, start, end):
    if start == end:
        tree[node] = end
        return tree[node]
    else:
        mid = (start + end) // 2
        l = init(node * 2 + 1, start, mid)
        r = init(node * 2 + 2, mid + 1, end)
        if ns[l] > ns[r]:
            tree[node] = r
        elif ns[l] < ns[r]:
            tree[node] = l
        else:
            tree[node] = min(r, l)
        return tree[node]


def getV(node, start, end, left, right):
    if end < left or right < start:
        return -1
    if left <= start and end <= right:
        return tree[node]
    mid = (start + end) // 2
    l = getV(node * 2 + 1, start, mid, left, right)
    r = getV(node * 2 + 2, mid + 1, end, left, right)
    if l == -1:
        return r
    elif r == -1:
        return l
    else:
        if ns[l] > ns[r]:
            return r
        elif ns[l] < ns[r]:
            return l
        else:
            return min(r, l)


def update(node, start, end, index, v):
    if start > index or end < index:
        return -1
    if start != end:
        mid = (start + end) // 2
        l = update(node * 2 + 1, start, mid, index, v)
        r = update(node * 2 + 2, mid + 1, end, index, v)
        if l == -1:
            if ns[tree[node]] > ns[r]:
                tree[node] = m
            elif ns[tree[node]] == ns[r]:
                tree[node] = min(tree[node], r)
        elif r == -1:
            if ns[tree[node]] > ns[l]:
                tree[node] = l
            elif ns[tree[node]] == ns[r]:
                tree[node] = min(tree[node], l)
        else:
            if ns[r] > ns[l]:
                if ns[tree[node]] > ns[l]:
                    tree[node] = l
                elif ns[tree[node]] == ns[r]:
                    tree[node] = min(tree[node], l)
            elif ns[r] < ns[l]:
                if ns[tree[node]] > ns[r]:
                    tree[node] = r
                elif ns[tree[node]] == ns[r]:
                    tree[node] = min(tree[node], r)
            else:
                if ns[tree[node]] > ns[l]:
                    tree[node] = min(l, r)
                elif ns[tree[node]] == ns[r]:
                    tree[node] = min(tree[node], l)
        return tree[node]
    else:
        ns[index] = v
        return index


init(0, 0, n - 1)
print(tree)
for _ in range(m):
    # print(tree)
    a, b, c = map(int, sys.stdin.readline().split())
    if a == 1:
        update(0, 0, n - 1, b - 1, c)
        print(tree)
    else:
        print(getV(0, 0, n - 1, b - 1, c - 1) + 1)
