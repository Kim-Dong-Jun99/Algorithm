import sys

n = int(sys.stdin.readline())

game = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

dp = [[0] * n for _ in range(n)]

dp[0][0] = 1

for i in range(n):
    for j in range(n):
        if dp[i][j] != 0 and game[i][j] != 0 and i + game[i][j] < n:
            dp[i + game[i][j]][j] += dp[i][j]
        if dp[i][j] != 0 and game[i][j] != 0 and j + game[i][j] < n:
            dp[i][j + game[i][j]] += dp[i][j]

print(dp[n - 1][n - 1])
