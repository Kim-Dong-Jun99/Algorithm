import sys

def get_neighbor(i, j):
    temp = []
    if i - 1 >= 0 and visited[i-1][j] == 0:
        temp.append([i-1, j])
    if i + 1 < M and visited[i+1][j] == 0:
        temp.append([i+1, j])
    if j - 1 >= 0 and visited[i][j-1] == 0:
        temp.append([i, j-1])
    if j + 1 < N and visited[i][j+1] == 0:
        temp.append([i, j+1])
    return temp

def dfs(i, j):
    visited[i][j] = 1
    if i == M-1 and j == N-1:
        visited[i][j] = 0
        return 0
    temp_result = 1000000
    for k, l in get_neighbor(i, j):
        if graph[k][l] == '1':
            temp = 1 + dfs(k, l)
        else:
            temp = dfs(k, l)
        if temp_result > temp:
            temp_result = temp
    visited[i][j] = 0
    return temp_result


N, M = map(int, sys.stdin.readline().split())

graph = [sys.stdin.readline().rstrip() for _ in range(M)]
visited = [[0 for _ in range(N)] for _ in range(M)]

result = dfs(0, 0)
print(result)

