import sys
N, K = map(int,sys.stdin.readline().split())

dp = [[0 for _ in range(N+1)] for _ in range(K+1)]
for i in range(N+1):
    dp[1][i] += 1
for i in range(2,K+1):
    for j in range(N+1):
        if j % 2 == 0:
            for k in range(j // 2):
                dp[i][j] += dp[i-1][j-k] + dp[i-1][k]
            dp[i][j] += dp[i-1][j//2]
        else:
            for k in range(j // 2+1):
                dp[i][j] += dp[i - 1][j - k] + dp[i - 1][k]
# for i in range(K+1):
#     print(dp[i])
print(dp[K][N] % 1000000000)