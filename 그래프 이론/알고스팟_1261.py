import sys

def smaller(i, j):
    if i < j:
        return i
    return j

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


def bfs(a, b):
    visited[a][b] = 1
    go = {(a, b) :0}
    temp_result = 100000
    while go:
        temp = {}
        for i, j in go.keys():
            v = go[(i, j)]
            for k, l in get_neighbor(i, j):
                if k == M-1 and l == N-1:
                    if temp_result > v:
                        temp_result = v
                else:
                    # visited[k][l] = 1
                    key = (k, l)
                    if graph[k][l] == '1':
                        if key in temp:
                            temp[key] = smaller(temp[key], v + 1)
                        else:
                            temp[key] = v + 1
                    else:
                        if key in temp:
                            temp[key] = smaller(temp[key], v)
                        else:
                            temp[key] = v
        for i, j in temp:
            visited[i][j] = 1
        go = temp
    return temp_result

N, M = map(int, sys.stdin.readline().split())

graph = [sys.stdin.readline().rstrip() for _ in range(M)]
visited = [[0 for _ in range(N)] for _ in range(M)]

result = bfs(0, 0)
print(result)
