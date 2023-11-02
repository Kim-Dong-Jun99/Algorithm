import sys
from collections import deque

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

graph = [[] for _ in range(n+1)]
backgraph = [[] for _ in range(n+1)]
q = deque()
indegree = [0]* (n+1)
result = [0] * (n+1)
check = [0] * (n+1)

for _ in range(m):
    a,b,c = map(int, sys.stdin.readline().split())
    
    graph[a].append((b,c))
    backgraph[b].append((a,c))
    indegree[b] += 1
start, end = map(int, sys.stdin.readline().split())
q.append(start)
while q:
    cur = q.popleft()
    for i,t in graph[cur]:
        indegree[i] -= 1
        result[i] = max(result[i],result[cur] + t)
        if indegree[i] == 0:
            q.append(i)
            
count = 0
q.append(end)
while q:
    cur = q.popleft()
    for i,t in backgraph[cur]:
        if result[cur] - result[i] == t:
            count += 1
            if check[i] == 0:
                q.append(i)
                check[i] = 1
print(result[end])
print(count)