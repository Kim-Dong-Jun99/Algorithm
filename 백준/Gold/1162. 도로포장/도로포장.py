from heapq import heappush, heappop
from collections import deque
from math import inf

# 도시 수, 간선 수, 최대 포장 횟수
n, m, k = map(int, input().split())

graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append([b, c])
    graph[b].append([a, c])

# dp[i][j] = i 번째 도시에 도착했을 때 포장을 j 번 했을 때의 최소 거리
dp = [[inf for _ in range(k + 1)] for _ in range(n + 1)]
dp[1] = [0 for _ in range(k + 1)]

heap = []
# 거리, 노드, 마스크, 포장 횟수
heappush(heap, (0, 1, 0))

while heap:
    dist, now, cover = heappop(heap)
    if dp[now][cover] < dist:
        continue
    for next_node, next_cost in graph[now]:
        cost = next_cost + dist
        if cost < dp[next_node][cover]:
            heappush(heap, (cost, next_node, cover))
            dp[next_node][cover] = cost

        if cover < k:
            cost = dist
            if cost < dp[next_node][cover + 1]:
                heappush(heap, (cost, next_node, cover + 1))
                dp[next_node][cover + 1] = cost

print(min(dp[n]))
