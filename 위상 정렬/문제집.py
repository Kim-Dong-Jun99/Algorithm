import sys
# from collections import deque
import heapq

n,m = map(int,sys.stdin.readline().split())

q = []
indegree = [0]*(n+1)

graph = [[] for _ in range(n+1)]

for _ in range(m):
    l,r = map(int,sys.stdin.readline().split())
    graph[l].append(r)
    indegree[r] += 1

def tpsort():
    result = []

    for i in range(1,n+1):
        if indegree[i] == 0:
            heapq.heappush(q,i)
    while q:
        cur = heapq.heappop(q)
        result.append(cur)

        for i in graph[cur]:
            indegree[i] -= 1
            if indegree[i] == 0:
                heapq.heappush(q,i)

    for i in result:
        print(i, end =' ')

tpsort()