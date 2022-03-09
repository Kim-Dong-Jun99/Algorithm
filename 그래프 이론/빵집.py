import sys

r, c = map(int, sys.stdin.readline().split())
pipes = [sys.stdin.readline().strip() for _ in range(r)]
visited = [[sys.maxsize for _ in range(c)] for _ in range(r)]


def cango(i, j):
    if i < r and j < c and pipes[i][j] != 'x':
        return True
    return False


def dfs(i, j):
    if j == c - 1:
        return 1
    temp = [[1, 1], [0, 1], [-1, 1]]
    for k in temp:
        if cango(i + k[0], j + k[1]):
            if i == 0 or visited[i - 1][j] >= k[0]:
                visited[i][j] = k[0]
                r = dfs(i + k[0], j + k[1])
                if r == 1:
                    return 1
                else:
                    visited[i][j] = sys.maxsize
                    return 0
    return 0


result = 0
for i in range(r):
    result += dfs(i, 0)

print(result)



