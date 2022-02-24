import sys

n, m = map(int, sys.stdin.readline().split())

v = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

dp = [[[v[i][j], v[i][j], v[i][j]] for j in range(m)] for i in range(n)]
# 왼, 위, 오

for i in range(n):
    for j in range(m):
        if i == 0:
            if j - 1 > -1:
                dp[i][j][1] += dp[i][j - 1][0]
                dp[i][j][0] += dp[i][j - 1][0]
                dp[i][j][2] += dp[i][j - 1][0]
                # dp[i][j][1] += dp[i][j-1][0]
        else:
            # if j - 1 > -1:
            if j == 0:
                dp[i][j][1] += max(dp[i - 1][j][1], dp[i - 1][j][2])
                dp[i][j][0] = dp[i][j][1]
            elif j == 1:
                dp[i][j][0] += dp[i][j - 1][1]
                dp[i][j][1] += max(dp[i - 1][j])
            else:
                dp[i][j][0] += max(dp[i][j - 1][0], dp[i][j - 1][1])
                dp[i][j][1] += max(dp[i - 1][j])
    if i >= 1:
        # dp[i][m-1][0] = dp[i][m-1][1]
        # dp[i][m-1][2] = dp[i][m-1][1]
        dp[i][m-1][2] = dp[i][m-1][1]
        for j in range(m - 2, -1, -1):
            if j == m - 2:
                dp[i][j][2] += dp[i][m - 1][1]
            else:
                dp[i][j][2] += max(dp[i][j + 1][1], dp[i][j + 1][2])
# for i in dp:
#     print(i)
print(max(dp[n - 1][m - 1]))