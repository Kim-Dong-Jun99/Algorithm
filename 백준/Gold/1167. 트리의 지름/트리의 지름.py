import sys
from collections import defaultdict
import heapq

result = 0


def dfs(i):
    curmax = 0
    childs = []
    for neighbor in tree[i]:
        if visited[neighbor] == 0:
            visited[neighbor] = 1
            neighbor_ = dfs(neighbor) + tree[i][neighbor]
            heapq.heappush(childs, -1 * (neighbor_))
            curmax = bigger(curmax, neighbor_)
    if len(childs) > 1:
        tempResult = (heapq.heappop(childs) + heapq.heappop(childs)) * -1
        global result
        result = bigger(tempResult, result)

    return curmax


def bigger(i, j):
    if i > j:
        return i
    return j


V = int(sys.stdin.readline())
tree = defaultdict(dict)
visited = defaultdict(int)
for _ in range(V):
    node = list(map(int, sys.stdin.readline().split()))
    for index in range(1, len(node), 2):
        if node[index] != -1:
            tree[node[0]][node[index]] = node[index + 1]
            
visited[1] = 1
dfs_result = dfs(1)
result = bigger(dfs_result, result)

print(result)
