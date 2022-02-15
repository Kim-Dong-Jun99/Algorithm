import sys
from collections import deque

n, m, x = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(n + 1)]
backgraph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    backgraph[b].append([a, c])
    graph[a].append([b, c])

gohome = [sys.maxsize for _ in range(n + 1)]
gohome[x] = 0
# visited = set()
# visited.add(x)
nextV = deque()
nextV.append(x)
while nextV:
    temp = nextV.popleft()
    for i, t in graph[temp]:
        if gohome[i] > gohome[temp] + t:
            gohome[i] = gohome[temp] + t
            nextV.append(i)

fromhome = [sys.maxsize for _ in range(n + 1)]
fromhome[x] = 0
# visited = set()
nextV = deque()
nextV.append(x)
while nextV:
    temp = nextV.popleft()
    for i, t in backgraph[temp]:
        if fromhome[i] > fromhome[temp] + t:
            fromhome[i] = fromhome[temp] + t
            nextV.append(i)

result = 0
for i in range(1,n+1):
    if gohome[i]+fromhome[i] > result:
        result = gohome[i] + fromhome[i]
print(result)
# print(gohome)
# print(fromhome)