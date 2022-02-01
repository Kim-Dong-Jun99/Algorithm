import sys

n, m = map(int, sys.stdin.readline().split())
graph = [sys.stdin.readline().strip() for _ in range(n)]
alpha = set()
maxV = 0
alpha.add(graph[0][0])
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bt(i, j, tempV):
    global maxV
    if maxV < tempV:
        maxV = tempV
    for k in range(4):
        nx = i + dx[k]
        ny = j + dy[k]
        if 0 <= nx < n and 0 <= ny < m and not graph[nx][ny] in alpha:
            alpha.add(graph[nx][ny])
            bt(nx, ny, tempV + 1)
            alpha.remove(graph[nx][ny])


bt(0, 0, 1)
print(maxV)