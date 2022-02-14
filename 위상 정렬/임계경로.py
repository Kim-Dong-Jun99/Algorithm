import sys
from collections import deque

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

graph = [[] for _ in range(n + 1)]
indegree = [0 for _ in range(n + 1)]

for _ in range(m):
    q = list(map(int, sys.stdin.readline().split()))
    graph[q[0]].append([q[1], q[2]])
    indegree[q[1]] += 1

start, end = map(int, sys.stdin.readline().split())

maxtime = [0] * (n + 1)
# roadcount = [0] * (n+1)
roadcount = [[] for i in range(n + 1)]
q = deque()

for i in range(1, n + 1):
    if indegree[i] == 0:
        q.append(i)

while q:
    temp = q.popleft()
    if temp != start:
        for i in graph[temp]:
            indegree[i[0]] -= 1
            if indegree[i[0]] == 0:
                q.append(i[0])

q.append(start)
while q:
    temp = q.popleft()
    for i in graph[temp]:
        indegree[i[0]] -= 1
        if maxtime[temp] + i[1] > maxtime[i[0]]:
            maxtime[i[0]] = maxtime[temp] + i[1]
            # roadcount[i[0]] = roadcount[temp] + 1

        elif maxtime[temp] + i[1] == maxtime[i[0]]:

        if indegree[i[0]] == 0:
            q.append(i[0])
# 중복을 어떻게 찾고 제거할 것인가
print(maxtime[end])
print(roadcount[end])

