import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())
query = [list(map(int, sys.stdin.readline().split())) for i in range(m)]
graph = [deque() for i in range(n + 1)]
for i in query:
    graph[i[0]].append(i[1])
    graph[i[1]].append(i[0])
visited = {}
group = 0
for i in range(1, n + 1):
    try:
        check = visited[i]
    except:
        group += 1
        nextV = deque([i])
        samegroup = deque([i])
        visited[i] = 1
        while nextV:
            temp = []
            for j in nextV:
                for k in graph[j]:
                    if (k not in samegroup):
                        temp.append(k)
                        samegroup.append(k)
                        visited[k] = 1
            nextV = temp
print(group)
