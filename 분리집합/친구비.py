import sys

n, m, k = map(int, sys.stdin.readline().split())

ff = [0]+list(map(int, sys.stdin.readline().split()))

parent = [i for i in range(n + 1)]


def find(x):
    if x == parent[x]:
        return parent[x]
    else:
        parent[x] = find(parent[x])
        return parent[x]


def union(x, y):
    rx = find(x)
    ry = find(y)

    if rx != ry:
        if ff[rx] < ff[ry]:
            parent[ry] = rx
        else:
            parent[rx] = ry


for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    union(a, b)

# print(parent)

table = {}
m = 0
for i in range(1, n+1):
    temp = find(parent[i])
    if table.get(temp, -1) == -1:
        m += ff[temp]
        table[temp] = 1

if m > k:
    print('Oh no')
else:
    print(m)