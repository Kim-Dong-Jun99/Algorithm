import sys

n = int(sys.stdin.readline())
graph = [[] for _ in range(n + 1)]
for _ in range(n - 1):
    a, b, c = map(int, sys.stdin.readline().split())
    graph[a].append([b, c])
    graph[b].append([a, c])

d = 0
# length = math.ceil(math.log2(n))
depth = [0] * (n + 1)
nextV = [1]
parent = [0 for _ in range(n + 1)]
costs = [0 for _ in range(n + 1)]
visited = [0] * (n + 1)
visited[1] = 1
while nextV:
    temp = []
    for i in nextV:
        for j, t in graph[i]:
            if visited[j] == 0:
                visited[j] = 1
                depth[j] = d
                parent[j] = i
                costs[j] = t
                temp.append(j)
    d += 1
    nextV = temp

m = int(sys.stdin.readline())

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    temp = 0
    if depth[a] > depth[b]:
        a, b = b, a
    while depth[a] != depth[b]:
        temp += costs[b]
        b = parent[b]
    while a != b:
        temp += costs[a]
        temp += costs[b]
        a = parent[a]
        b = parent[b]
    print(temp)