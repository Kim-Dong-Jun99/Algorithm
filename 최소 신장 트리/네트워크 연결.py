import sys

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

edges = [list(map(int, sys.stdin.readline().split())) for _ in range(m)]

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
        parent[rx] = ry


edges.sort(key=lambda a: a[2])

mst = set()
index = 0
result = 0
while index < m:
    a = edges[index][0]
    b = edges[index][1]
    if find(a) != find(b) and a != b:
        result += edges[index][2]
        union(a, b)
        mst.add(a)
        mst.add(b)
    index += 1
print(result)