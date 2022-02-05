import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())

indegree = [0] * (n + 1)

graph = [[] for _ in range(n + 1)]

q = deque()
for _ in range(m):
    l, r = map(int, sys.stdin.readline().split())

    graph[l].append(r)
    indegree[r] += 1


def tpsort():
    for i in range(1, n + 1):
        if indegree[i] == 0:
            q.append(i)
    result = []

    while q:
        cur = q.popleft()
        result.append(cur)
        for i in graph[cur]:
            indegree[i] -= 1
            if indegree[i] == 0:
                q.append(i)

    for i in result:
        print(i, end=' ')


tpsort()