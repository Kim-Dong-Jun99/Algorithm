import sys


def find(x):
    if x == tree[x]:
        return x
    else:
        tree[x] = find(tree[x])
        return tree[x]


def union(x, y):
    rx = find(x)
    ry = find(y)
    if rx != ry:
        if rx > ry:
            rx, ry = ry, rx
        tree[rx] = ry
        if is_tree[rx] == 0:
            is_tree[ry] = 0
        is_tree[rx] = 0
    else:
        is_tree[rx] = 0
        is_tree[ry] = 0


index = 1
while True:
    n, m = map(int, sys.stdin.readline().split())
    if n == 0 and m == 0:
        break
    tree = {}
    is_tree = {}
    for i in range(1, n + 1):
        tree[i] = i
        is_tree[i] = 1
    for _ in range(m):
        i, j = map(int, sys.stdin.readline().split())
        union(i, j)
    result = sum(is_tree.values())
    if result > 1:
        print("Case %d: A forest of %d trees." % (index, result))
    elif result == 1:
        print("Case %d: There is one tree." % index)
    else:
        print("Case %d: No trees." % index)
    index += 1
