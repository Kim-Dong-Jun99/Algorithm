import sys


def can_go(i, j):
    temp = []
    if i - 1 >= 0:
        temp.append([i - 1, j])
    if i + 1 < M:
        temp.append([i + 1, j])
    if j - 1 >= 0:
        temp.append([i, j - 1])
    if j + 1 < N:
        temp.append([i, j + 1])
    return temp


N, M = map(int, sys.stdin.readline().split())

graph = [sys.stdin.readline().rstrip() for _ in range(M)]
max_v = 1000000
min_distance = [[max_v for _ in range(N)] for _ in range(M)]

go = [[0, 0]]
min_distance[0][0] = 0
while go:
    temp = []
    for i, j in go:
        for k, l in can_go(i, j):
            if graph[k][l] == '1':
                if min_distance[i][j] + 1 < min_distance[k][l]:
                    min_distance[k][l] = min_distance[i][j] + 1
                    temp.append([k, l])
            else:
                if min_distance[i][j] < min_distance[k][l]:
                    min_distance[k][l] = min_distance[i][j]
                    temp.append([k, l])
    go = temp
print(min_distance[M - 1][N - 1])
