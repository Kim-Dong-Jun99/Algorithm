import sys

n = int(sys.stdin.readline())
forest = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

dp = [[1 for _ in range(n)] for _ in range(n)]


def cango(i, j):
    result = []
    if i - 1 > -1:
        if forest[i - 1][j] > forest[i][j]:
            result.append([i - 1, j])
    if i + 1 < n:
        if forest[i + 1][j] > forest[i][j]:
            result.append([i + 1, j])
    if j - 1 > -1:
        if forest[i][j - 1] > forest[i][j]:
            result.append([i, j - 1])
    if j + 1 < n:
        if forest[i][j + 1] > forest[i][j]:
            result.append([i, j + 1])
    return result


def comein(i, j):
    result = []
    if i - 1 > -1:
        if forest[i - 1][j] < forest[i][j]:
            result.append([i - 1, j])
    if i + 1 < n:
        if forest[i + 1][j] < forest[i][j]:
            result.append([i + 1, j])
    if j - 1 > -1:
        if forest[i][j - 1] < forest[i][j]:
            result.append([i, j - 1])
    if j + 1 < n:
        if forest[i][j + 1] < forest[i][j]:
            result.append([i, j + 1])
    return result


maxV = 0
visited = [[0 for _ in range(n)] for _ in range(n)]
for i in range(n):
    for j in range(n):
        if cango(i, j) == []:
            cur = [i, j]
            stack = []
            visited[i][j] = 1
            while True:
                check = True
                temp = comein(cur[0], cur[1])
                for k in temp:
                    if visited[k[0]][k[1]] == 0:
                        visited[k[0]][k[1]] = 1
                        stack.append(cur)
                        cur = k
                        check = False
                        break
                if check:
                    if temp:
                        compare = []
                        for k in temp:
                            compare.append(dp[k[0]][k[1]])
                        dp[cur[0]][cur[1]] = 1 + max(compare)
                    if stack:
                        cur = stack.pop()
                    else:
                        break
            if dp[i][j] > maxV:
                maxV = dp[i][j]

print(maxV)

