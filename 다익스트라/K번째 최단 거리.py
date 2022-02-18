import sys
from heapq import heappush, heappop

input = sys.stdin.readline
inf = sys.maxsize

n, m, k = map(int, input().split())
graph =[[] for _ in range(n + 1)]
dp = [[inf] * k for _ in range(n + 1)]
heap = []

def dijkstra(start):
    heappush(heap, [0, start])
    dp[start][0] = 0
    while heap:
        w, n = heappop(heap)
        for n_n, wei in graph[n]:
            n_w = wei + w
            if n_w < dp[n_n][k-1]:
                dp[n_n][k-1] = n_w
                dp[n_n].sort()
                heappush(heap, [n_w, n_n])

for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append([b, c])

dijkstra(1)

# print(dp)
for i in range(1, n + 1):
    if dp[i][k-1] == inf:
        print(-1)
    else:
        print(dp[i][k-1])