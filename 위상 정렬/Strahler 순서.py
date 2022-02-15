import sys
from collections import deque

tc = int(sys.stdin.readline())

for _ in range(tc):
    k, m, p = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(m + 1)]
    indegree = [0] * (m + 1)
    sorders = [[] for _ in range(m + 1)]
    sorder = [0] * (m + 1)
    q = deque()
    for __ in range(p):
        a, b = map(int, sys.stdin.readline().split())
        graph[a].append(b)
        indegree[b] += 1
    for i in range(1, m + 1):
        if indegree[i] == 0:
            q.append(i)
            sorders[i].append(1)

    while q:
        temp = q.popleft()
        sorder[temp] = max(sorders[temp])
        if sorders[temp].count(sorder[temp]) >= 2:
            sorder[temp] += 1
        for i in graph[temp]:
            indegree[i] -= 1
            sorders[i].append(sorder[temp])
            if indegree[i] == 0:
                q.append(i)
    print('%d %d' % (k, sorder[m]))