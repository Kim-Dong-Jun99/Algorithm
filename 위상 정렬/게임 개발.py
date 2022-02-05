import sys
from collections import deque

n = int(sys.stdin.readline())

ctime = [0] * (n + 1)

graph = [[] for _ in range(n + 1)]
indegree = [0] * (n + 1)

q = deque()
for _ in range(1, n + 1):
    query = list(map(int, sys.stdin.readline().split()))
    ctime[_] = query[0]

    for i in range(1, len(query) - 1):
        graph[query[i]].append(_)
        indegree[_] += 1


def tsort():
    for i in range(1, n + 1):
        if indegree[i] == 0:
            q.append(i)
    beforetime = [0] * (n + 1)
    result = [0] * (n + 1)
    while q:
        cur = q.popleft()
        result[cur] = ctime[cur] + beforetime[cur]

        for i in graph[cur]:
            indegree[i] -= 1
            if beforetime[i] < result[cur]:
                beforetime[i] = result[cur]
            if indegree[i] == 0:
                q.append(i)
    for i in range(1, n + 1):
        print(result[i], end=' ')


tsort()