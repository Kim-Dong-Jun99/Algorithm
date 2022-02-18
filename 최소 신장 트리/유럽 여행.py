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


# def dfs(cur):
#     visited[cur] = 1
#     global temp
#     temp += cost[cur]
#     for i in graph[cur]:
#         if visited[i[0]] == 0:
#             temp += i[1]
#             dfs(i[0])
#             temp += cost[cur]


n, p = map(int, sys.stdin.readline().split())

cost = [0]
for _ in range(n):
    cost.append(int(sys.stdin.readline()))

path = [list(map(int, sys.stdin.readline().split())) for _ in range(p)]

parent = [i for i in range(n + 1)]

mst = []
path.sort(key=lambda a: a[2] + cost[a[0]] + cost[a[1]])
for i in path:
    if find(i[0]) != find(i[1]):
        union(i[0], i[1])
        mst.append(i)

graph = [[] for _ in range(n+1)]
for i in mst:
    graph[i[0]].append([i[1], i[2]])
    graph[i[1]].append([i[0], i[2]])

result = sys.maxsize
for i in range(1, n+1):
    temp = 0
    visited = [0]*(n+1)
    # dfs(i)
    cur = i
    stack = []
    visited[cur] = 1
    while True:
        temp += cost[cur]
        check = True
        for j in graph[cur]:
            if visited[j[0]] == 0:
                visited[j[0]] = 1
                temp += j[1]
                stack.append([cur, j[1]])
                cur = j[0]
                check = False
                break
        if check:
            if stack:
                cur, v = stack.pop()
                temp += v
            else:
                break
    if temp < result:
        result = temp

print(result)