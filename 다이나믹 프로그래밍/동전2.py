import sys

def smaller(i,j):
    if i < j:
        return i
    return j

n,k = map(int, sys.stdin.readline().split())

coins = [int(sys.stdin.readline()) for _ in range(n)]

dp = [100000] * (k+1)
dp[0] = 0

for i in range(k+1):
    if dp[i] != 100000:
        for j in coins:
            if i+j <= k:
                dp[i+j] = smaller(dp[i+j], dp[i] + 1)

if dp[k] != 100000:
    print(dp[k])
else:
    print(-1)