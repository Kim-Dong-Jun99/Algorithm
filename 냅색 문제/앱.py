import sys

n, m = map(int, sys.stdin.readline().split())

byte = [0] + list(map(int, sys.stdin.readline().split()))
cost = [0] + list(map(int, sys.stdin.readline().split()))

dp = [[0 for _ in range(sum(cost) + 1)] for _ in range(n + 1)]
result = sum(cost)
val = sum(cost)
for i in range(1, n + 1):
    b = byte[i]
    c = cost[i]

    for j in range(1, val + 1):
        if j < c:
            dp[i][j] = dp[i - 1][j]
        else:
            dp[i][j] = max(b + dp[i - 1][j - c], dp[i - 1][j])
        if dp[i][j] >= m:
            result = min(result, j)

if m != 0:
    print(result)
else:
    print(0)