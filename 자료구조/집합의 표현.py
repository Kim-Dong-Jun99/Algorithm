import sys

sys.setrecursionlimit(100000)
n, m = map(int, sys.stdin.readline().split())
groups = [i for i in range(0, n + 1)]


def find(x):
    if groups[x] == x:
        return x
    groups[x] = find(groups[x])
    return groups[x]


def union(x, y):
    x = find(x)
    y = find(y)
    if x != y:
        groups[x] = y


for i in range(m):
    query = list(map(int, sys.stdin.readline().split()))
    if query[0] == 0:
        union(query[1], query[2])
    else:
        temp1 = find(query[1])
        temp2 = find(query[2])
        if temp1 == temp2:
            print('yes')
        else:
            print('no')

