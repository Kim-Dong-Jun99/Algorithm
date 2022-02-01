import sys

sys.setrecursionlimit(10 ** 9)
n, m = map(int, sys.stdin.readline().split())
graph = [sys.stdin.readline().strip() for _ in range(n)]
alpha = [0 for _ in range(26)]
maxV = 0

alpha[ord(graph[0][0]) - 65] = 1
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bt(i, j, tempV):
    global maxV
    if maxV < tempV:
        maxV = tempV
    for k in range(4):
        nx = i + dx[k]
        ny = j + dy[k]
        if 0 <= nx < n and 0 <= ny < m and alpha[ord(graph[nx][ny]) - 65] == 0:
            alpha[ord(graph[nx][ny]) - 65] = 1
            bt(nx, ny, tempV + 1)
            alpha[ord(graph[nx][ny]) - 65] = 0


bt(0, 0, 1)
print(maxV)