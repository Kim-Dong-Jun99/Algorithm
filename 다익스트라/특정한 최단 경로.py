import sys
from collections import deque

n, e = map(int, sys.stdin.readline().split())
graph = [{} for _ in range(n + 1)]

for _ in range(e):
    a, b, c = map(int, sys.stdin.readline().split())
    if graph[a].get(b, sys.maxsize) > c:
        graph[a][b] = c
    if graph[b].get(a, sys.maxsize) > c:
        graph[b][a] = c

l, r = map(int, sys.stdin.readline().split())

sd = [sys.maxsize for _ in range(n + 1)]
sd[1] = 0
cur = deque()
cur.append(1)
while cur:
    temp = cur.popleft()
    for i in graph[temp].keys():
        if sd[i] > sd[temp] + graph[temp][i]:
            sd[i] = sd[temp] + graph[temp][i]
            cur.append(i)

ld = [sys.maxsize for _ in range(n + 1)]
ld[l] = 0
cur = deque()
cur.append(l)
while cur:
    temp = cur.popleft()
    for i in graph[temp].keys():
        if ld[i] > ld[temp] + graph[temp][i]:
            ld[i] = ld[temp] + graph[temp][i]
            cur.append(i)

rd = [sys.maxsize for _ in range(n + 1)]
rd[r] = 0
cur = deque()
cur.append(r)
while cur:
    temp = cur.popleft()
    for i in graph[temp].keys():
        if rd[i] > rd[temp] + graph[temp][i]:
            rd[i] = rd[temp] + graph[temp][i]
            cur.append(i)
result = min(ld[r] + sd[l] + rd[n], ld[r]+sd[r]+ld[n])
if result >= sys.maxsize:
    print(-1)
else:
    print(result)