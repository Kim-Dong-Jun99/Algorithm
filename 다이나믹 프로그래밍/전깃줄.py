import sys
n = int(sys.stdin.readline())
lines = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

lines.sort()
dp = [0] * n
dp[0] = 1

for i in range(1, n):
    temp = 0
    for j in range(i):
        if lines[i][1] > lines[j][1] and dp[j] > temp:
            temp = dp[j]
    dp[i] = temp + 1
print(n - max(dp))