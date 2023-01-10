import sys

N, K = map(int, sys.stdin.readline().split())
dp = [[1] * (N + 1) for _ in range(N + 1)]
for i in range(2, N + 1):
    for j in range(i - 1, 0, -1):
        dp[i][j] = dp[i-1][j] + dp[i-1][j-1]

print(dp[N][K] % 10007)
