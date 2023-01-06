import sys
from collections import defaultdict


def return_small(a, b):
    if a > b:
        return b
    return a


def return_big(a, b):
    if a < b:
        return b
    return a

N = int(sys.stdin.readline())
graph = [{} for _ in range(N + 1)]
for _ in range(N - 1):
    i, j, v = map(int, sys.stdin.readline().split())
    graph[i][j] = v
    graph[j][i] = v
K = int(sys.stdin.readline())
for _ in range(K):
    i, j = map(int, sys.stdin.readline().split())
    visited = defaultdict(int)
    nextNode = [i]
    value = {i: [100, 000, 0]}
    while True:
        temp = []
        end = False
        for cur in nextNode:
            for neighbor in graph[cur]:
                if visited[neighbor] == 0:
                    if neighbor == j:
                        end = True
                    visited[neighbor] = 1
                    temp.append(neighbor)
                    value[neighbor] = [return_small(graph[cur][neighbor],value[cur][0]), return_big(graph[cur][neighbor],value[cur][1])]
        nextNode = temp
        if end:
            break
    print("%d %d"%(value[j][0], value[j][1]))