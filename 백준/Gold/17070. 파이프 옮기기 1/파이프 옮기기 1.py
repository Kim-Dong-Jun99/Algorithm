import sys

n = int(sys.stdin.readline())
graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

dp = [[[0, 0, 0] for _ in range(n)] for _ in range(n)]
# ->, 밑, 대각선 = 0, 1, 2
dp[0][1][0] = 1


def move(i, j, d):
    if d == 0:
        # if j+1 < n and graph[i][j+1] != 1:
        #     dp[i][j+1][0] = dp[i][j][0]
        #     if i+1 < n and graph[i+1][j+1] != 1:
        #         dp[i+1][j+1][2] = dp[i][j][0]
        if j+1 < n and graph[i][j+1] != 1:
            dp[i][j+1][0] = dp[i][j][0] + dp[i][j][2]
    elif d == 1:
        # if i + 1 < n and graph[i + 1][j] != 1:
        #     dp[i + 1][j][1] = dp[i][j][1]
        #     if j+1 < n and graph[i+1][j+1] != 1:
        #         dp[i+1][j+1][1] = dp[i][j][1]
        if i+1 < n and graph[i+1][j] != 1:
            dp[i+1][j][1] = dp[i][j][1] + dp[i][j][2]
    else:
        # if i+1 < n and graph[i+1][j] != 1:
        #     dp[i+1][j][1] = dp[i][j][2]
        #     if j+1 < n and graph[i+1][j] != 1:
        #         dp[i][j+1][0] = dp[i][j][2]
        #     if j+1 < n and graph[i+1][j+1] != 1:
        #         dp[i+1][j+1][2] = dp[i][j][2]
        if i+1 < n and j+1 < n and graph[i+1][j+1] != 1 and graph[i+1][j] != 1 and graph[i][j+1] != 1:
            dp[i+1][j+1][2] = sum(dp[i][j])

dp[0][1][0] = 1
for i in range(n):
    for j in range(1, n):
        for k in range(3):
            move(i, j, k)

# for i in dp:
#     print(i)
print(sum(dp[n-1][n-1]))