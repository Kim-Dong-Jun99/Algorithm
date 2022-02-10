import sys

n = int(sys.stdin.readline())
graph = [[] for _ in range(n + 1)]
for _ in range(n-1):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

depth = [0] * (n + 1)
parent = [0] * (n + 1)
nextV = [1]
visited = [0] * (n + 1)
visited[1] = 1
while nextV:
    temp = []
    for i in nextV:
        for j in graph[i]:
            if visited[j] == 0:
                temp.append(j)
                depth[j] = depth[i] + 1
                parent[j] = i
                visited[j] = 1
    nextV = temp
m = int(sys.stdin.readline())
for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    while depth[a] != depth[b]:
        if depth[a] > depth[b]:
            a = parent[a]
        else:
            b = parent[b]
    if a == b:
        print(a)
    else:
        while a != b:
            a = parent[a]
            b = parent[b]
        print(a)



