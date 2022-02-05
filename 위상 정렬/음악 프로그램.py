import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())

indegree = [0] * (n + 1)
graph = [[] for _ in range(n + 1)]
q = deque()
for _ in range(m):
    query = list(map(int, sys.stdin.readline().split()))

    limit = query[0]
    for i in range(1, limit):
        graph[query[i]].append(query[i + 1])
        indegree[query[i + 1]] += 1


def tpsort():
    for i in range(1, n + 1):
        if indegree[i] == 0:
            q.append(i)
    if len(q) == 0:
        print(0)
    else:
        # visited = [0] * (n + 1)
        result = []
        check = True
        while q:
            cur = q.popleft()
            # if visited[cur] == 1:
            #     print(0)
            #     check = False
            #     break
            result.append(cur)
            # visited[cur] = 1
            for i in graph[cur]:
                indegree[i] -= 1
                # if indegree[i] < 0:
                #     print(0)
                #     check = False
                #     break
                if indegree[i] == 0:
                    q.append(i)
            # if check == False:
            #     break
        if len(result) != n:
            print(0)
        else:
            for i in result:
                print(i)
        # print(visited)

tpsort()