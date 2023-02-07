import sys

def find(x):
    if parent[x] == x:
        return x
    else:
        parent[x] = find(parent[x])
        return parent[x]

def union(x, y):
    px = find(x)
    py = find(y)

    if px != py:
        parent[px] = py


N, M = map(int, sys.stdin.readline().split())
roads = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]

parent = {}
for i in range(1,N+1):
    parent[i] = i

roads.sort(key= lambda x : x[2])
# print(roads)
edges = 0
result = 0
for i, j, v in roads:
    if edges < N - 2:
        if find(i) != find(j):
            union(i, j)
            result += v
            edges += 1
    else:
        break

print(result)
