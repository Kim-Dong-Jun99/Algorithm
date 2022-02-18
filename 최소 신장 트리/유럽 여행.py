import sys


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


n, p = map(int, sys.stdin.readline().split())
cost = [sys.maxsize]
for _ in range(n):
    cost.append(int(sys.stdin.readline()))
path = [list(map(int, sys.stdin.readline().split())) for _ in range(p)]
parent = [i for i in range(n + 1)]
mst = []
path.sort(key=lambda a: a[2]*2 + cost[a[0]] + cost[a[1]])
for i in path:
    if find(i[0]) != find(i[1]):
        union(i[0], i[1])
        mst.append(i)
        # if len(mst) == n-1:
        #     break
graph = [[] for _ in range(n+1)]
for i in mst:
    graph[i[0]].append([i[1], i[2]])
    graph[i[1]].append([i[0], i[2]])

result = 0
for i in mst:
    result += i[2]*2
for i in range(1, n+1):
    if graph[i]:
        result += cost[i]*len(graph[i])
result += min(cost)
print(result)


