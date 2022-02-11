import sys
import math
from collections import deque

n = int(sys.stdin.readline())
l = int(math.log2(n)+1)
graph = [[] for _ in range(n+1)]

for _ in range(n-1):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

parent = [0 for _ in range(n+1)]
depth = [0 for _ in range(n+1)]

visited = [0 for _ in range(n+1)]

q = deque()

q.append(1)
while q:
    cur = q.popleft()
    visited[cur] = 1
    for i in graph[cur]:
        if visited[i] == 0:
            q.append(i)
            parent[i] = cur
            depth[i] = depth[cur] + 1

dp = [[0]*l for _ in range(n+1)]
for i in range(n+1):
    dp[i][0] = parent[i]

for j in range(1,l):
    for i in range(1,n+1):
        dp[i][j] = dp[dp[i][j-1]][j-1]

m = int(sys.stdin.readline())
for _ in range(m):
    a,b = map(int, sys.stdin.readline().split())
    if depth[a] > depth[b]:
        a,b = b,a
    tempd = depth[b] - depth[a]

    for i in range(l):
        if tempd & 1 << i:
            b = dp[b][i]
    if a == b:
        print(a)
        continue

    for i in range(l-1,-1,-1):
        if dp[a][i] != dp[b][i]:
            a = dp[a][i]
            b = dp[b][i]

    print(dp[b][0])