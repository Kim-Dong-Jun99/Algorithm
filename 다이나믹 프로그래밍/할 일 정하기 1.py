import sys

n = int(sys.stdin.readline())

jobs = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

dp = [[sys.maxsize for _ in range(2 ** n)] for _ in range(n)]
for i in range(n):
    dp[0][1 << i] = jobs[i][0]

for i in range(n - 1):
    for j in range(2 ** n):
        if dp[i][j] != sys.maxsize:
            for k in range(n):
                if j & (1 << k) != (1 << k):
                    dp[i + 1][j | (1 << k)] = min(dp[i + 1][j | (1 << k)], dp[i][j] + jobs[k][i + 1])

print(dp[-1][-1])

