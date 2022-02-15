import sys
from collections import deque

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

graph = [[] for _ in range(n + 1)]
graphcheck = [[0 for _ in range(n+1)] for _ in range(n+1)]

for _ in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    if graphcheck[a][b] == 0 or c < graphcheck[a][b]:
        graph[a].append([b, c])
        graphcheck[a][b] = c

dep, arv = map(int, sys.stdin.readline().split())

cost = [sys.maxsize for _ in range(n + 1)]
cost[dep] = 0
nextV = deque()
nextV.append(dep)
while nextV:
    temp = nextV.popleft()
    for i, t in graph[temp]:
        if cost[i] > cost[temp] + t:
            cost[i] = cost[temp] + t
            nextV.append(i)

print(cost[arv])