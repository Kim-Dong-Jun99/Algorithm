import sys


def can_go(i, j):
    temp = []
    if i - 1 >= 0 and visited[i - 1][j] == 0 and graph[i - 1][j] == 'L':
        temp.append([i - 1, j])
    if i + 1 < N and visited[i + 1][j] == 0 and graph[i + 1][j] == 'L':
        temp.append([i + 1, j])
    if j - 1 >= 0 and visited[i][j - 1] == 0 and graph[i][j - 1] == 'L':
        temp.append([i, j - 1])
    if j + 1 < M and visited[i][j + 1] == 0 and graph[i][j + 1] == 'L':
        temp.append([i, j + 1])
    return temp


def bfs(a, b):
    go = [[a, b]]
    visited[a][b] = 1
    temp_result = 0
    while go:
        temp = []
        for i, j in go:
            for k, l in can_go(i, j):
                visited[k][l] = 1
                temp.append([k, l])
        go = temp
        if go:
            temp_result += 1
    return temp_result

N, M = map(int, sys.stdin.readline().split())

graph = [sys.stdin.readline().rstrip() for _ in range(N)]
result = 0
for i in range(N):
    for j in range(M):
        if graph[i][j] == 'L':
            visited = [[0 for _ in range(M)] for _ in range(N)]
            bfs1 = bfs(i, j)
            if bfs1 > result:
                result = bfs1

print(result)


