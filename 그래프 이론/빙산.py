import sys
from collections import deque


def bfs(a, b):
    go = deque([[a, b]])
    visited[a][b] = True
    while go:
        x, y = go.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            # ices = 0
            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny]:
                if glacier[nx][ny] != 0:
                    visited[nx][ny] = True
                    go.append([nx, ny])
                else:
                    count[x][y] += 1
    return 0


N, M = map(int, sys.stdin.readline().split())
glacier = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
result = 0
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

while True:
    visited = [[False] * M for _ in range(N)]
    count = [[0] * M for _ in range(N)]
    answer = []
    for i in range(N):
        for j in range(M):
            if glacier[i][j] != 0 and visited[i][j] == 0:
                answer.append(bfs(i, j))

    for i in range(N):
        for j in range(M):
            glacier[i][j] -= count[i][j]
            if glacier[i][j] < 0:
                glacier[i][j] = 0
    if len(answer) == 0 or len(answer) >= 2:
        break

    result += 1
print(result) if len(answer) >= 2 else print(0)
