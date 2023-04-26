import sys
from collections import deque

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())
graph = [[] for _ in range(n + 1)]
# backgraph = [[] for _ in range(n + 1)]
indegree = [0] * (n + 1)

for _ in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    graph[b].append([a, c])
    # backgraph[a].append([b, c])
    indegree[a] += 1

q = deque()
primary = []
parts = [[0 for _ in range(n+1)] for _ in range(n+1)]
for i in range(1, n + 1):
    if indegree[i] == 0:
        q.append(i)
        parts[i][i] = 1
        primary.append(i)

while q:
    temp = q.popleft()
    for i, t in graph[temp]:
        indegree[i] -= 1
        # parts[i][temp] = parts[temp][temp] * t
        for j in range(1, n+1):
            parts[i][j] += parts[temp][j] * t
        if indegree[i] == 0:
            q.append(i)
# print(parts)

for i in primary:
    print('%d %d'%(i, parts[n][i]))