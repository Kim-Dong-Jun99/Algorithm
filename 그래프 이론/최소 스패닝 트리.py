import sys
import heapq

v, e = map(int, sys.stdin.readline().split())
disjointSet = [i for i in range(v+1)]

mst = set()
result = 0
edges = []

def find(x):
    if disjointSet[x] == x:
        return x
    disjointSet[x] = find(disjointSet[x])
    return disjointSet[x]

def union(x,y):
    x = find(x)
    y = find(y)
    disjointSet[x] = y
for _ in range(e):
    i, j, w = map(int, sys.stdin.readline().split())
    heapq.heappush(edges, [w, i, j])

while edges:
    minE = heapq.heappop(edges)
    if find(minE[1]) != find(minE[2]):
        result += minE[0]
        union(minE[1],minE[2])
        mst.add(minE[1])
        mst.add(minE[2])

print(result)

